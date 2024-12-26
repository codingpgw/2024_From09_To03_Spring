package com.pcwk.ehr.board;

import static org.junit.jupiter.api.Assertions.*;

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

import com.pcwk.ehr.board.dao.BoardDao;
import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.SearchVO;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"
,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"			
})
class BoardDaoJunit {
	
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired 
	ApplicationContext context;
	
	@Autowired
	BoardDao dao;
	
	BoardVO boardVO01;
	BoardVO boardVO02;
	BoardVO boardVO03;
	
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
		
		boardVO01 = new BoardVO(seq01, "제목01", "내용01", "10", 0, "ADMIN", "사용하지 않음", "ADMIN", "사용하지 않음");
		boardVO02 = new BoardVO(seq02, "제목02", "내용02", "10", 0, "ADMIN", "사용하지 않음", "ADMIN", "사용하지 않음");
		boardVO03 = new BoardVO(seq03, "제목03", "내용03", "10", 0, "ADMIN", "사용하지 않음", "ADMIN", "사용하지 않음");
		
		search = new SearchVO();
	}

	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	@Test
	void doRetrieve() throws SQLException {
		
		dao.deleteAll();
		
		dao.saveAll();
		
		search.setPageSize(10);
		search.setPageNo(1);
//		search.setSearchDiv("30");
//		search.setSearchWord("내용");
		
		Map<String, String> option = new HashMap<String, String>();
		option.put("div", "10");
		
		search.setOptionSearch(option);
		
		List<BoardVO> list = dao.doRetrieve(search);
		
		assertEquals(10,list.size());
	}
	
	@Disabled
	@Test
	void doUpdate() throws SQLException{
		
		dao.deleteAll();
		
		int flag = dao.doSave(boardVO01);
		assertEquals(1,flag);
		
		BoardVO outVO01 = dao.doSelectOne(boardVO01);
		
		String upString = "_U";
		outVO01.setTitle(outVO01.getTitle()+upString);
		outVO01.setContents(outVO01.getContents()+upString);
		outVO01.setDiv("20");
		outVO01.setModId(outVO01.getModId()+upString);
		
		dao.doUpdate(outVO01);
		assertEquals(1,flag);
		
		BoardVO upBoardVO01 = dao.doSelectOne(outVO01);
		isSameBoardVO(upBoardVO01, outVO01);
		
	} 
	
	@Disabled
	@Test
	public void addAndGet() throws SQLException {
		
		dao.deleteAll();
		
		int flag = dao.doSave(boardVO01);
		assertEquals(1, flag);
		
		BoardVO outVO01 = dao.doSelectOne(boardVO01);
		isSameBoardVO(boardVO01, outVO01);
	}
	
	public void isSameBoardVO(BoardVO boardVO01, BoardVO outVO01) {
		assertEquals(boardVO01.getSeq(), outVO01.getSeq());
		assertEquals(boardVO01.getTitle(), outVO01.getTitle());
		assertEquals(boardVO01.getContents(), outVO01.getContents());
		assertEquals(boardVO01.getDiv(), outVO01.getDiv());
		assertEquals(boardVO01.getReadCnt(), outVO01.getReadCnt());
		
		assertEquals(boardVO01.getRegId(), outVO01.getRegId());
		assertEquals(boardVO01.getModId(), outVO01.getModId());
	}
	
	@Disabled
	@Test
	void beans() {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ beans()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		log.debug("context:"+context);
		log.debug("dao:"+dao);
		assertNotNull(context);
		assertNotNull(dao);
	}

}
