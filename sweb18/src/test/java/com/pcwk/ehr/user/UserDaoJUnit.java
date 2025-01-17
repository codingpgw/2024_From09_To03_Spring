package com.pcwk.ehr.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"
,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"			
})
public class UserDaoJUnit {

	final Logger log = LogManager.getLogger(getClass());

	UserVO userVO01;
	UserVO userVO02;
	UserVO userVO03;
	
	SearchVO search;

	@Autowired // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.
	ApplicationContext context;

	@Autowired
	UserDao dao;

	@BeforeEach // @Test수행전 실행
	public void setUp() throws Exception {
		log.debug("context:{}", context);
		userVO01 = new UserVO("james01", "이상무01", "4321", "사용하지 않음",1,0,Level.BASIC,"jamesol@paran.com");
		userVO02 = new UserVO("james02", "이상무02", "4321", "사용하지 않음",55,10,Level.SILVER,"jamesol@paran.com");
		userVO03 = new UserVO("james03", "이상무03", "4321", "사용하지 않음",100,40,Level.GOLD,"jamesol@paran.com");

		search=new SearchVO();
	}
	
	@Disabled
	@Test
	public void login() throws SQLException {
		dao.deleteAll();
		assertEquals(0, dao.getCount());
		
		dao.doSave(userVO01);
		assertEquals(1, dao.getCount());
		
		//userVO01.setUserId(userVO01.getUserId()+"_99");
		assertEquals(1,dao.idCheck(userVO01));
		
		//userVO01.setPassword(userVO01.getPassword()+"_99");
		assertEquals(1, dao.idPassCheck(userVO01));
		
	}
	
	@Disabled
	@Test
	public void beans() {
		log.debug("context:"+context);
		log.debug("dao:"+dao);
		assertNotNull(context);
		assertNotNull(dao);
	}
	
	@Disabled
	@Test
	public void doDelete() throws SQLException {
		// 매번 동일 결과가 도출 되도록 작성.
		// 0. 전체 삭제
		// 1. userVO01 데이터 저장
		// 2. userVO01 데이터 삭제
		// 3. 건수 0건
		
		//0
		dao.deleteAll();
		assertEquals(0, dao.getCount());
		
		//1
		dao.doSave(userVO01);
		assertEquals(1, dao.getCount());
		
		//2
		int flag = dao.doDelete(userVO01);
		assertEquals(1, flag);
		
		//3
		assertEquals(0, dao.getCount());
	}
	
	@Disabled
	@Test
	void doUpdate() throws SQLException {
		// 매번 동일 결과가 도출 되도록 작성.
		// 0. 전체 삭제
		// 1. userVO01 데이터 저장
		// 2. userVO01 수정 update
		// 3. update 데이터 조회
		// 4. 비교
		
		//*보안된 update : 2건 입력, 1건만 update
		
		//0
		dao.deleteAll();
		
		//1
		int count = dao.doSave(userVO01);
		assertEquals(1, count);
		
		dao.doSave(userVO02);
		
		userVO01.setName(userVO01.getName()+"_U");
		userVO01.setPassword(userVO01.getPassword()+"_U");
		userVO01.setLogin(userVO01.getLogin()+99);
		userVO01.setRecommend(userVO01.getRecommend()+99);
		userVO01.setGrade(userVO01.getGrade().SILVER);
		userVO01.setEmail(userVO01.getEmail()+"_U");
		//2
		count = dao.doUpdate(userVO01);
		assertEquals(1, count);
		
		//3
		UserVO outVO = dao.doSelectOne(userVO01);
		
		//변경하지 않은 UserVO
		UserVO noUpdateoutVO = dao.doSelectOne(userVO02);
		
		//4
		isSameUser(userVO01, outVO);
		isSameUser(noUpdateoutVO, userVO02);
	}
	
	@Disabled
	@Test
	void doRetrieve() throws SQLException {
		// 매번 동일 결과가 도출 되도록 작성.
		// 0. 전체 삭제
		// 1. saveAll()
		// 2. paging조회
		// 3. 조회 건수 10건

		// 0
		dao.deleteAll();
		// 1.
		dao.saveAll();
		
		search.setPageNo(1);
		search.setPageSize(10);
		// 2.
		List<UserVO> list = dao.doRetrieve(search);
		// 3.
		assertEquals(10, list.size());

	}

	@Disabled
	@Test
	void getAll() throws SQLException {
		// 매번 동일 결과가 도출 되도록 작성.
		// 0. 전체 삭제
		// -------------------------------------------
		// 1. 한 건 입력
		// 2. getAll()
		// 3. size()비교
		// -------------------------------------------

		// 위 부분 3회 반복
		// 입력 데이터 3건과 조회 데이터 비교

		// 0
		this.dao.deleteAll();

		// 1
		dao.doSave(userVO01);

		// 2
		List<UserVO> user1 = dao.getAll();

		// 3
		assertEquals(user1.size(), 1);

		// 1
		dao.doSave(userVO02);

		// 2
		List<UserVO> user2 = dao.getAll();

		// 3
		assertEquals(user2.size(), 2);

		// 1
		dao.doSave(userVO03);

		// 2
		List<UserVO> user3 = dao.getAll();

		// 3
		assertEquals(user3.size(), 3);

		isSameUser(userVO03, user3.get(0));
		isSameUser(userVO02, user3.get(1));
		isSameUser(userVO01, user3.get(2));

	}

	// 메서드 예외사항 테스트:NullPointerException이 발생 하면 성공
	@Disabled
	@Test
	public void getFailure() throws SQLException {
		// 매번 동일 결과가 도출 되도록 작성.
		// 0. 전체 삭제
		// 1. 건수 조회
		// 2. 한 건 등록
		// 3. 한건 조회

		// 0.
		dao.deleteAll();

		// 1.
		int count = dao.getCount();
		assertEquals(0, count);

		// 2.
		dao.doSave(userVO01);

		// 3.
		String unKnownId = userVO01.getUserId() + "_99";

		userVO01.setUserId(unKnownId);

		// NullPointerException이 발생 하면 성공
		assertThrows(EmptyResultDataAccessException.class, () -> {
			UserVO outVO = dao.doSelectOne(userVO01);
		});

	}

	//@Disabled
	@Test
	public void getCount() throws SQLException {
		// 매번 동일 결과가 도출 되도록 작성.
		// 0. 전체 삭제
		// 1. 건수 조회
		// 2. 한 건 등록
		// 3. 건수 조회:1

		// 4. 한 건 등록
		// 5. 건수 조회:2

		// 6. 한 건 등록
		// 7. 건수 조회:3

		// 0
		dao.deleteAll();

		// 1. 삭제 건수: 0건
		int count = dao.getCount();
		assertEquals(0, count);

		// 2. 한 건 등록
		dao.doSave(userVO01);

		// 3. 건수 조회
		count = dao.getCount();
		assertEquals(1, count);

		// 2. 한 건 등록
		dao.doSave(userVO02);

		// 3.건수 조회
		count = dao.getCount();
		assertEquals(2, count);

		// 2. 한 건 등록
		dao.doSave(userVO03);

		// 3.건수 조회
		count = dao.getCount();
		assertEquals(3, count);
	}

	@Disabled // 테스트 무시
	@Timeout(value = 7000, unit = TimeUnit.MILLISECONDS)
	@Test
	public void addAndGet() throws SQLException {
		// 매번 동일 결과가 도출 되도록 작성.
		// 0. 전체 삭제
		// 1. 건수 조회
		// 2. 한 건 등록
		// 3. 건수 조회
		// 4. 한건 조회
		// 5. 등록데이터 입력데터 비교

		// 0
		dao.deleteAll();

		
		// 1. 삭제 건수: 0건 
		int count = dao.getCount(); assertEquals(0, count);
		
		// 2. 단건등록 
		dao.doSave(userVO01);
		
		// 3. 등록건수 조회 
		count = dao.getCount(); assertEquals(1, count);
		
		// 4. 
		UserVO outVO01 = dao.doSelectOne(userVO01); // Not Null 확인
		assertNotNull(outVO01);
		
		// 5. 
		isSameUser(outVO01, userVO01);
		

	}

	public void isSameUser(UserVO outVO01, UserVO userVO01) {
		assertEquals(outVO01.getUserId(), userVO01.getUserId());
		assertEquals(outVO01.getName(), userVO01.getName());
		assertEquals(outVO01.getPassword(), userVO01.getPassword());
		
		assertEquals(outVO01.getLogin(), userVO01.getLogin());
		assertEquals(outVO01.getRecommend(), userVO01.getRecommend());
		assertEquals(outVO01.getGrade(), userVO01.getGrade());
		assertEquals(outVO01.getEmail(), userVO01.getEmail());
	}

	@AfterEach
	public void tearDown() throws Exception {
		log.debug("==========================================================");
		log.debug("=@After=");
		log.debug("==========================================================");
	}

}
