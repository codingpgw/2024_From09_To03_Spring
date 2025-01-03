package com.pcwk.ehr.board;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pcwk.ehr.board.dao.BoardDao;
import com.pcwk.ehr.board.domain.BoardVO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.user.domain.UserVO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class BoardControllerTest {
	
	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	WebApplicationContext webApplicationContext;

	// 웹 브라우저 대역 객체
	MockMvc mockMvc;
	
	BoardVO boardVO01;
	
	@Autowired
	BoardDao boardDao;
	
	SearchVO search;
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		int seq01 = boardDao.getBoardSequence();
		boardVO01 = new BoardVO(seq01, "제목01", "내용01", "10", 0, "ADMIN", "사용하지 않음", "ADMIN", "사용하지 않음");
		
		search = new SearchVO();
	}
	
	@Test
	public void doRetrieve() throws Exception {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **doRetrieve()**                  │");
		log.debug("└───────────────────────────────────┘");
		
		boardDao.deleteAll();
		
		boardDao.saveAll();
		
		MockHttpServletRequestBuilder requestBuilder
		= MockMvcRequestBuilders.get("/board/doRetrieve.do")
			.param("pageSize", "10")
			.param("pageNo", "1")
			.param("searchDiv", "")
			.param("searchWord", "")
			.param("div", "10");
		
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk());

		MvcResult mvcResult = resultActions.andDo(print()).andReturn();
		
		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		List<BoardVO> list = (List<BoardVO>) model.get("list");
		
		assertEquals(10, list.size());
		
		SearchVO search = (SearchVO) model.get("search");
		assertNotNull(search);
		
		int totalCnt = (int) model.get("totalCnt");
		log.debug("totalCnt:{}",totalCnt);
		
		String viewName = mvcResult.getModelAndView().getViewName();
		log.debug("viewName:{}",viewName);
		assertEquals("board/board_list", viewName);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	@Disabled
	@Test
	void bean() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **beans()**                       │");
		log.debug("└───────────────────────────────────┘");
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		log.debug("webApplicationContext:{}", webApplicationContext);
		log.debug("mockMvc:{}", mockMvc);
		log.debug("boardDao:{}", boardDao);
	}

}
