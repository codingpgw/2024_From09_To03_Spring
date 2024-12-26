package com.pcwk.ehr.login;

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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserVO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class LoginControllerTest {
	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	WebApplicationContext webApplicationContext;
	
	MockMvc mockMvc;

	UserVO userVO01;
	
	@Autowired
	UserDao userDao;
	
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		userVO01 = new UserVO("james01", "이상무01", "4321", "사용하지 않음", 1, 0, Level.BASIC, "jamesol@paran.com");
	}
	
	@Test
	void login() throws Exception{
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **login()**                       │");
		log.debug("└───────────────────────────────────┘");
		
		userDao.deleteAll();
		assertEquals(0, userDao.getCount());
		
		userDao.doSave(userVO01);
		assertEquals(1, userDao.getCount());
		
		MockHttpServletRequestBuilder requestBuilder
		= MockMvcRequestBuilders.post("/login/login.do")
		.param("userId", userVO01.getUserId())
		.param("password", userVO01.getPassword());
		
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk());
		
		MockHttpSession session = (MockHttpSession)resultActions.andReturn()
				.getRequest()
				.getSession();
		
		UserVO outVO = (UserVO)session.getAttribute("user");
		assertNotNull(outVO);
		
		String returnBody = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		log.debug("returnBody:{}", returnBody);
		MessageVO resultMessage = new Gson().fromJson(returnBody, MessageVO.class);
		
		assertEquals(30, resultMessage.getMessageId());
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
		assertNotNull(userDao);
		log.debug("webApplicationContext:{}", webApplicationContext);
		log.debug("mockMvc:{}", mockMvc);
		log.debug("userDao:{}", userDao);
	}

}
