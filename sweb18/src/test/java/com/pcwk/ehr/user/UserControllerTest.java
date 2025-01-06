package com.pcwk.ehr.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.SearchVO;
import com.pcwk.ehr.user.dao.UserDao;
import com.pcwk.ehr.user.domain.Level;
import com.pcwk.ehr.user.domain.UserVO;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
class UserControllerTest {
	final Logger log = LogManager.getLogger(getClass());

	@Autowired
	WebApplicationContext webApplicationContext;

	// 웹 브라우저 대역 객체
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
	public void doRetrieve() throws Exception {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **doRetrieve()**                  │");
		log.debug("└───────────────────────────────────┘");
		
		//1.
		userDao.deleteAll();
		
		//2.
		userDao.saveAll();
		
		MockHttpServletRequestBuilder requestBuilder
		= MockMvcRequestBuilders.get("/user/doRetrieve.do")
			.param("pageSize", "10")
			.param("pageNo", "1")
			.param("searchDiv", "")
			.param("searchWord", "");
		
		ResultActions resultActions = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk());

		MvcResult mvcResult = resultActions.andDo(print()).andReturn();
		
		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		List<UserVO> list = (List<UserVO>) model.get("list");
		
		assertEquals(10, list.size());
		
		SearchVO search = (SearchVO) model.get("search");
		assertNotNull(search);
		
		String viewName = mvcResult.getModelAndView().getViewName();
		log.debug("viewName:{}",viewName);
		assertEquals("user/user_list", viewName);
	}
	
	@Disabled
	@Test
	public void doSelectOne() throws Exception{
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **doSelectOne()**                 │");
		log.debug("└───────────────────────────────────┘");
		
		userDao.deleteAll();
		userDao.doSave(userVO01);
		
		MockHttpServletRequestBuilder requestBuilder
			= MockMvcRequestBuilders.get("/user/doSelectOne.do")
			.param("userId", userVO01.getUserId());
		
		ResultActions resultActions = mockMvc.perform(requestBuilder)
							.andExpect(status().isOk());
		
		MvcResult mvcResult = resultActions.andDo(print()).andReturn();
		
		Map<String, Object> model = mvcResult.getModelAndView().getModel();
		
		UserVO outVO = (UserVO) model.get("vo");
		log.debug("outVO:{}",outVO);
		
		isSameUser(userVO01, outVO);
		
		String viewName = mvcResult.getModelAndView().getViewName();
		log.debug("viewName:{}",viewName);
		assertEquals("user/user_mng", viewName);
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

	
	@Disabled
	@Test
	public void doUpdate() throws Exception {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **doUpdate()**                    │");
		log.debug("└───────────────────────────────────┘");

		this.userDao.deleteAll();

		int flag = userDao.doSave(userVO01);
		assertEquals(1, flag);
		UserVO inVO = userDao.doSelectOne(userVO01);
		
		String upString = "_U";
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/doUpdate.do")
				.param("userId", inVO.getUserId())
				
				.param("name", "태무"+inVO.getName())
				.param("password", inVO.getPassword()+upString)
				.param("login", String.valueOf(inVO.getLogin()+99))
				.param("recommend", String.valueOf(inVO.getRecommend()+99))
				.param("grade", inVO.getGrade().nextLevel().name())
				.param("email", inVO.getEmail()+upString);

		ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));

		String returnBody = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		log.debug("returnBody:{}", returnBody);
		MessageVO resultMessage = new Gson().fromJson(returnBody, MessageVO.class);
		
		assertEquals(1, resultMessage.getMessageId());
		assertEquals("태무"+inVO.getName() + "님 정보가 수정되었습니다.", resultMessage.getMessage());
	}

	@Disabled
	@Test
	public void doDelete() throws Exception {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **doDelete()**                    │");
		log.debug("└───────────────────────────────────┘");

		// 1.전체 삭제
		this.userDao.deleteAll();
		// 2.단건 등록
		int flag = userDao.doSave(userVO01);
		assertEquals(1, flag);
		// 3.한건 삭제
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/doDelete.do")
				.param("userId", userVO01.getUserId());

		ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));

		String returnBody = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		log.debug("returnBody:{}", returnBody);
		MessageVO resultMessage = new Gson().fromJson(returnBody, MessageVO.class);
		assertEquals(1, resultMessage.getMessageId());
		assertEquals(userVO01.getUserId() + "님이 삭제되었습니다.", resultMessage.getMessage());
	}

	@Disabled
	@Test
	public void doSave() throws Exception {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **doSave()**                      │");
		log.debug("└───────────────────────────────────┘");

		this.userDao.deleteAll();

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/doSave.do")
				.param("userId", userVO01.getUserId())
				.param("name", userVO01.getName())
				.param("password", userVO01.getPassword())
				.param("login", String.valueOf(userVO01.getLogin()))
				.param("recommend", String.valueOf(userVO01.getRecommend()))
				.param("grade", userVO01.getGrade().name())
				.param("email", userVO01.getEmail());

		ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"));

		String returnBody = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();

		log.debug("returnBody:{}", returnBody);
		MessageVO resultMessage = new Gson().fromJson(returnBody, MessageVO.class);

		assertEquals(1, resultMessage.getMessageId());
		assertEquals(userVO01.getName() + "님 등록 성공하였습니다.", resultMessage.getMessage());
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
		log.debug("webApplicationContext:{}", webApplicationContext);
		log.debug("mockMvc:{}", mockMvc);
		log.debug("userDao:{}", userDao);
	}

}
