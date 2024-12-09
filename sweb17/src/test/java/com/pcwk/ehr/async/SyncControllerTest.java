package com.pcwk.ehr.async;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml"
,"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"			
})
class SyncControllerTest {
	
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//웹 브라우저 대역 객체
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	/**
	 * 동기 통신
	 * @throws Exception
	 */
	@Test
	public void syncResult() throws Exception {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **syncResult()**                  │");
		log.debug("└───────────────────────────────────┘");
		
		//1. url 호출, Method:GET/POST, Param:name
		MockHttpServletRequestBuilder requestBuilder
			= MockMvcRequestBuilders.post("/sync/sync_result.do")
			  .param("name", "이상무01");
		
		//2.
		ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
		
		//3 ModelAndView
		MvcResult mvcResult = resultActions.andDo(print()).andReturn();
		
		//4. Model
		java.util.Map<String, Object> model = mvcResult.getModelAndView().getModel();
		
		String returnMessage = (String) model.get("req_name");
		log.debug("returnMessage:{}",returnMessage);
		
		//5. View
		String viewName = mvcResult.getModelAndView().getViewName();
		log.debug("viewName:{}",viewName);
		
		assertEquals("이상무01", returnMessage);
		assertEquals("sync/sync_index", viewName);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}
	
	@Disabled
	@Test
	void beans() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **beans()**                       │");
		log.debug("└───────────────────────────────────┘");
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		log.debug("webApplicationContext:{}",webApplicationContext);
		log.debug("mockMvc:{}",mockMvc);
	}

}
