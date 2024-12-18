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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
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
	public void beans() {
		assertNotNull(context);
		assertNotNull(dao);
	}

	//@Disabled
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
		assertThrows(NullPointerException.class, () -> {
			UserVO outVO = dao.doSelectOne(userVO01);
		});

	}

	@Disabled
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
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
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
		int count = dao.getCount();
		assertEquals(0, count);

		// 2. 단건등록
		dao.doSave(userVO01);

		// 3. 등록건수 조회
		count = dao.getCount();
		assertEquals(1, count);

		// 4.
		UserVO outVO01 = dao.doSelectOne(userVO01);
		// Not Null 확인
		assertNotNull(outVO01);

		// 5.
		isSameUser(outVO01, userVO01);

	}

	public void isSameUser(UserVO outVO01, UserVO userVO01) {
		assertEquals(outVO01.getUserId(), userVO01.getUserId());
		assertEquals(outVO01.getName(), userVO01.getName());
		assertEquals(outVO01.getPassword(), userVO01.getPassword());
	}

	@AfterEach
	public void tearDown() throws Exception {
		log.debug("==========================================================");
		log.debug("=@After=");
		log.debug("==========================================================");
	}

}
