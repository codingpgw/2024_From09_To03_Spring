package com.pcwk.ehr.user;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class UserDaoJUnit {
	final Logger log = LogManager.getLogger(UserDao.class);
	
	UserVO userVO01;
	UserVO userVO02;
	UserVO userVO03;
	
	@Autowired//테스트 오브젝트가 만들어지고 나면 테스트 컨텍스트에 의해 자동으로 주입된다.
	ApplicationContext context;
	
	@Autowired
	UserDao dao;
	
	@BeforeEach
	public void setUp() throws Exception {
		log.debug("context:{}",context);
		userVO01 = new UserVO("james01", "이상무01", "4321", "사용하지 않음");
		userVO02 = new UserVO("james02", "이상무02", "4321", "사용하지 않음");
		userVO03 = new UserVO("james03", "이상무03", "4321", "사용하지 않음");
	}
	
	@Test
	public void beans() {
		assertNotNull(context);
		assertNotNull(dao);
	}
	
	//메서드 예외사항 테스트:
	@Test
	public void getFailure() throws SQLException {
		
		dao.deleteAll();
		
		int count = dao.getCount();
		assertEquals(0, count);
		
		dao.doSave(userVO01);
		
		String unKnownId = userVO01.getUserId()+"_99";
		
		userVO01.setUserId(unKnownId);
		
		assertThrows(NullPointerException.class,() -> {
			UserVO outVO01 = dao.doSelectOne(userVO01);
		});
	}
	
	@Test
	public void getCount() throws SQLException {
		//매번 동일 결과가 도출되도록 작성.
		//0. 전체 삭제
		//1. 건수 조회
		//2. 한건 등록
		//3. 건수 조회
		dao.deleteAll();
		
		int count = dao.getCount();
		assertEquals(0, count);
		
		dao.doSave(userVO01);
		
		count = dao.getCount();
		assertEquals(1, count);
		//4. 한건 등록
		//5. 건수 조회
		dao.doSave(userVO02);
		
		count = dao.getCount();
		assertEquals(2, count);
		//6. 한건 등록
		//7. 건수 조회
		dao.doSave(userVO03);
		
		count = dao.getCount();
		assertEquals(3, count);
	}
	
	//@Ignore
	@Timeout(value = 7, unit = TimeUnit.SECONDS)
	@Test//addAndGet 메서드 총 수행 시간이 (입력된 수)/1000초 이내에 들어오면 성공
	public void addAndGet() throws SQLException {
		//매번 동일 결과가 도출되도록 작성.
		//0. 전체 삭제
		//1. 건수 조회
		//2. 한 건 등록
		//3. 건수 조회
		//4. 한건 조회
		//5. 등록데이터 입력데이터 비교
		
		//0
		dao.deleteAll();
		
		//1 건수 : 0건
		int count = dao.getCount();
		assertEquals(0, count);
		
		//2. 단건등록
		dao.doSave(userVO01);
		
		//3. 등록건수 조회
		count = dao.getCount();
		assertEquals(1, count);
		
		//4.
		UserVO outVO01 = dao.doSelectOne(userVO01);
		assertNotNull(outVO01);
		
		//5.
		isSameUser(outVO01, userVO01);
	}
	
	public void isSameUser(UserVO outVO01, UserVO userVO01) {
		assertEquals(outVO01.getUserId(), userVO01.getUserId());
		assertEquals(outVO01.getName(), userVO01.getName());
		assertEquals(outVO01.getPassword(), userVO01.getPassword());
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		log.debug("===========================================");
		log.debug("@after");
		log.debug("===========================================");
	}

}
