package com.pcwk.ehr.community;

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

import com.pcwk.ehr.community.dao.CommunityDao;
import com.pcwk.ehr.community.domain.CommunityVO;
import com.pcwk.ehr.cmn.SearchVO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class CommunityControllerTest {

	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	// 웹브라우저 대역 객체
	MockMvc mockMvc;
	
	CommunityVO CommunityVO01;
	
	@Autowired
	CommunityDao CommunityDao;
	
	
	SearchVO search;
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		int seq01 = CommunityDao.getBoardSequence();
		CommunityVO01 = new CommunityVO("ADMIN", "제목01", "내용01", "10", "10", "사용하지 않음", "사용하지 않음", 0);
		
		search= new SearchVO();
	}

	//@Disabled
	@Test
	void doRetrieve() throws Exception {
		//1. 전체 삭제(UserDao)
		//2. 다건등록
		//3. 조회
		//4. 비교건수		
		
		//1.
		CommunityDao.deleteAll();
		
		//2. 
		CommunityDao.saveAll();
		
		//3.
		MockHttpServletRequestBuilder requestBuilder
		= MockMvcRequestBuilders.get("/board/doRetrieve.do")
			.param("pageSize", "10")
			.param("pageNo", "1")
			.param("searchDiv","")
			.param("searchWord","")	
			.param("div", "20");
		
		//3.2
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk());
		
		//3.3 
		MvcResult mvcResult  =resultActions.andDo(print()).andReturn();
		
		
		//3.4
		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		List<CommunityVO> list = (List<CommunityVO>) model.get("list");
				
		assertEquals(10, list.size());
		
		//검색
		SearchVO search=(SearchVO) model.get("search");
		assertNotNull(search);		
		
		int totalCnt = (int) model.get("totalCnt");
		log.debug("totalCnt:{}",totalCnt);
		
		assertEquals(12, totalCnt);
		
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

	//@Disabled
	@Test
	void bean() {
		log.debug("┌───────────────────────────────────────┐");
		log.debug("│ **beans()**                           │");
		log.debug("└───────────────────────────────────────┘");
		log.debug("webApplicationContext:{}",webApplicationContext);
		log.debug("mockMvc:{}",mockMvc);
		log.debug("userDao:{}",CommunityDao);
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(CommunityDao);
	}
}