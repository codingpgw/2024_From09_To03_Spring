package com.pcwk.ehr.community;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.community.dao.CommunityDao;
import com.pcwk.ehr.community.domain.CommunityVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"
		,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"	
})
class CommunityDaoJunit {

	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.
	ApplicationContext context;
	
	@Autowired
	CommunityDao  dao;
	
	CommunityVO CommunityVO01;
	CommunityVO CommunityVO02;
	CommunityVO CommunityVO03;
	
	SearchVO search;
	
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
				
		int seq01 = dao.getBoardSequence();
		int seq02 = dao.getBoardSequence();
		int seq03 = dao.getBoardSequence();
		log.debug("seq01:"+seq01);
		log.debug("seq02:"+seq02);
		log.debug("seq03:"+seq03);
		
		
		CommunityVO01 = new CommunityVO("admin", "제목01", "내용01", "10", "10", "사용하지 않음", "사용하지 않음", 0);
		CommunityVO02 = new CommunityVO("admin", "제목02", "내용02", "10", "10", "사용하지 않음", "사용하지 않음", 0);
		CommunityVO03 = new CommunityVO("admin", "제목03", "내용03", "10", "10", "사용하지 않음", "사용하지 않음", 0);
		
		search = new SearchVO();
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	//@Disabled
	@Test
	void doRetrieve() throws SQLException {
		// 매번 동일 결과가 도출 되도록 작성.
		// 0. 전체 삭제
		// 1. saveAll()
		// 2. paging조회
		// 3. 조회 건수 10건		
		
		//0.
		dao.deleteAll();
		
		//1.
		dao.saveAll();
		
		search.setPageSize(10);
		search.setPageNo(1);
		//search.setSearchDiv("30");
		//search.setSearchWord("내용");
		
		Map<String,String> option = new HashMap<String, String>();
		option.put("cmn_div", "10");
		
		search.setOptionSearch(option);
		
		List<CommunityVO> list = dao.doRetrieve(search);
		
		// 3.
		assertEquals(10, list.size());		
	}
	
	@Disabled
	@Test
	void doUpdate() throws SQLException {
		// 매번 동일 결과가 도출 되도록 작성.
		// 0. 전체 삭제
		// 1. CommunityVO01 데이터 저장
		// 2. CommunityVO01데이터 조회
		// 3. CommunityVO01수정 & update		
		// 4. 비교
		
		// * 보완된 update: 2건 입력, 1건만 update
		
		//0.
		dao.deleteAll();
		
		//1.
		int flag = dao.doSave(CommunityVO01);
		assertEquals(1, flag);
		log.debug("99999999999999999flag: {}", flag);
		
		//2.
		CommunityVO outVO01 = dao.doSelectOne(CommunityVO01);
		isSameCommunityVO(CommunityVO01,outVO01);
		
		//3.
		String upString = "_U";
		outVO01.setTitle(outVO01.getTitle() + upString);
		outVO01.setContent(outVO01.getContent() + upString);
		outVO01.setDiv("20");
		outVO01.setMemId(outVO01.getMemId() + upString); 
		
		//4.
		flag = dao.doUpdate(outVO01);
		assertEquals(1, flag);
		
		
		CommunityVO upCommunityVO01 = dao.doSelectOne(outVO01);
		isSameCommunityVO(upCommunityVO01,outVO01);
			
	}	
	
	
	
	@Disabled
	@Test
	public void addAndGet() throws SQLException {
		// 매번 동일 결과가 도출 되도록 작성.
		// 0. 전체 삭제
		// 1. 한 건 등록
		// 2. 한건 조회
		// 3. 등록데이터 입력데터 비교
		
		//0.
		dao.deleteAll();
		
		//1.
		int flag = dao.doSave(CommunityVO01);
		assertEquals(1, flag);
		
		//2.
		CommunityVO outVO01 = dao.doSelectOne(CommunityVO01);
		
		isSameCommunityVO(CommunityVO01, outVO01);
	}	
	
	
	public void isSameCommunityVO(CommunityVO CommunityVO01, CommunityVO outVO01) {
		assertEquals(CommunityVO01.getMemId(), outVO01.getMemId());
		assertEquals(CommunityVO01.getTitle(), outVO01.getTitle());
		assertEquals(CommunityVO01.getContent(), outVO01.getContent());
		assertEquals(CommunityVO01.getCategory(), outVO01.getCategory());
		assertEquals(CommunityVO01.getDiv(), outVO01.getDiv());
		assertEquals(CommunityVO01.getView(), outVO01.getView());
	}
	

	//@Disabled
	@Test
	void beans() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ beans()                               │");
		log.debug("└───────────────────────────────────────┘");	
				
		log.debug("context:"+context);
		log.debug("dao:"+dao);
		assertNotNull(context);
		assertNotNull(dao);
	}
}