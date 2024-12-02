/**
 * Package Name : com.pcwk.ehr.user <br/>
 * Class Name: UserServiceJupiter.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2024-11-28<br/>
 *
 * ------------------------------------------<br/>
 * @author :gy
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.user;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;

import com.pcwk.ehr.user.service.TestUserService;
import com.pcwk.ehr.user.service.UserService;
import com.pcwk.ehr.user.service.UserServiceImpl;
import static com.pcwk.ehr.user.service.UserServiceImpl.MIN_LOGIN_COUNT_FOR_SILVER;
import static com.pcwk.ehr.user.service.UserServiceImpl.MIN_RECOMMEND_COUNT_FOR_GOLD;
/**
 * @author gy
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
class UserServiceJupiter {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.
	ApplicationContext context;
	
	@Autowired
	UserService userService;
	
	List<UserVO> users;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	@Qualifier("dummyMailSender")
	@Autowired
	MailSender mailSender;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		users = Arrays.asList(
					 new UserVO("james01", "이상무01", "4321", "사용하지 않음",MIN_LOGIN_COUNT_FOR_SILVER-1,0,Level.BASIC,"1026rjsdnd@naver.com")//등업 대상 아님 jamesol@paran.com
					,new UserVO("james02", "이상무02", "4321", "SILVER 등업",MIN_LOGIN_COUNT_FOR_SILVER,0,Level.BASIC,"1026rjsdnd@naver.com")//Level.BASIC -> SILVER : 등업대상
					,new UserVO("james03", "이상무03", "4321", "사용하지 않음",MIN_LOGIN_COUNT_FOR_SILVER+10,MIN_RECOMMEND_COUNT_FOR_GOLD-1,Level.SILVER,"1026rjsdnd@naver.com")//Level.SILVER : 등업대상 아님 
					,new UserVO("james04", "이상무04", "4321", "GOLD:등업 대상",MIN_LOGIN_COUNT_FOR_SILVER+11,MIN_RECOMMEND_COUNT_FOR_GOLD,Level.SILVER,"1026rjsdnd@naver.com")//Level.SILVER -> GOLD : 등업 대상
					,new UserVO("james05", "이상무05", "4321", "GOLD:등업 대상 아님",MIN_LOGIN_COUNT_FOR_SILVER+40,MIN_RECOMMEND_COUNT_FOR_GOLD+3,Level.GOLD,"1026rjsdnd@naver.com")//Level.GOLD : 등업 대상 아님
				);
	}
	
	@Test
	public void upgradeAllOrNothing() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **upgradeAllOrNothing()**         │");
		log.debug("└───────────────────────────────────┘");
		
		//예외를 발생시킬 4번째 사용자의 대역 Object를 생성
		String userId = users.get(3).getUserId();
		UserServiceImpl testUserService = new TestUserService(userId+"예외 없음");
		
		testUserService.setUserDao(userDao);
		//수동으로 dataSource DI
		testUserService.setTransactionManager(transactionManager);
		testUserService.setMailSender(mailSender);
		//0
		try {
			userDao.deleteAll();
			assertEquals(0, userDao.getCount());
			//1
			for(UserVO vo : users) {
				userDao.doSave(vo);
			}
			assertEquals(5, userDao.getCount());
			
			//2
			testUserService.upgradeLevels();
			//checkLevel(users.get(1),true);
			
		}catch (Exception e) {
			log.debug("┌───────────────────────────────────┐");
			log.debug("Exception : "+e.getMessage());
			log.debug("└───────────────────────────────────┘");
		} 
	}
	
	@Disabled
	@Test
	public void doSave() throws SQLException{
		//0. 전체 삭제
		//1. 등급 있는 사용자 입력, 등급 null인 사용자 등록
		//2. 데이터 조회
		//3. 비교(등급 null인 사용자 -> Level.BASIC)
		
		//0
		userDao.deleteAll();
		
		//1
		UserVO userWithLevel = users.get(4);//GOLD
		UserVO userWithOutLevel = users.get(0);//BASIC -> null
		userWithOutLevel.setGrade(null);
		
		int flag = userService.doSave(userWithLevel);
		assertEquals(1, flag);
		flag = userService.doSave(userWithOutLevel);
		assertEquals(1, flag);
		
		//2
		UserVO userWithoutLevelRead = userDao.doSelectOne(userWithOutLevel);
		UserVO userWithLevelRead = userDao.doSelectOne(userWithLevel);
		
		//log.debug(userWithoutLevelRead);
		assertEquals(userWithoutLevelRead.getGrade(), Level.BASIC);
		assertEquals(userWithLevelRead.getGrade(), userWithLevel.getGrade());
	}
	
	@Disabled
	@Test
	public void upgradeLevels() throws SQLException{
		//0. 전체 삭제
		//1. users 사용자 모두 입력
		//2. 등업
		//3. 비교
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **upgradeLevels()**               │");
		log.debug("└───────────────────────────────────┘");
		
		//0
		userDao.deleteAll();
		assertEquals(0, userDao.getCount());
		
		//1
		for(UserVO vo : users) {
			userDao.doSave(vo);
		}
		assertEquals(5, userDao.getCount());
		
		//2
		this.userService.upgradeLevels();
		
		//3
		checkLevel(users.get(0),false);
		checkLevel(users.get(1),true);
		checkLevel(users.get(2),false);
		checkLevel(users.get(3),true);
		checkLevel(users.get(4),false);
	}
	
	/**
	 * 등업 여부 확인
	 * @param user
	 * @param upgraded : true(등업), false(등업 안함)
	 * @throws NullPointerException
	 * @throws SQLException
	 */
	private void checkLevel(UserVO user, boolean upgraded) throws NullPointerException, SQLException {
		UserVO upgradeUser = userDao.doSelectOne(user);
		
		//등업
		if(true == upgraded) {
			assertEquals(upgradeUser.getGrade(), user.getGrade().nextLevel());
		//등업 안함
		}else {
			assertEquals(upgradeUser.getGrade(), user.getGrade());
		}
	}
	
	//@Disabled
	@Test
	void beans() {
		log.debug(context);
		log.debug(userDao);
		log.debug(userService);
		log.debug(transactionManager);
		
		assertNotNull(context);
		assertNotNull(userDao);
		assertNotNull(userService);
		assertNotNull(transactionManager);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}

}
