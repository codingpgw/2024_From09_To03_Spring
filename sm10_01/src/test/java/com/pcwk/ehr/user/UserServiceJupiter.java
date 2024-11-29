/**
 * Package Name : com.pcwk.ehr.user <br/>
 * Class Name: UserServiceJupiter.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2024-11-28<br/>
 *
 * ------------------------------------------<br/>
 * @author :gy
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.user;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pcwk.ehr.user.service.UserService;

/**
 * @author gy
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
class UserServiceJupiter {
	final Logger log = LogManager.getLogger(getClass());
	
	@Autowired // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.
	ApplicationContext context;
	
	@Autowired
	UserService userService;
	
	List<UserVO> users;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		users = Arrays.asList(
					 new UserVO("james01", "이상무01", "4321", "사용하지 않음",49,0,Level.BASIC,"jamesol@paran.com")//등업 대상 아님
					,new UserVO("james02", "이상무02", "4321", "SILVER 등업",50,0,Level.BASIC,"jamesol@paran.com")//Level.BASIC -> SILVER : 등업대상
					,new UserVO("james03", "이상무03", "4321", "사용하지 않음",60,29,Level.SILVER,"jamesol@paran.com")//Level.SILVER : 등업대상 아님 
					,new UserVO("james04", "이상무04", "4321", "GOLD:등업 대상",61,30,Level.SILVER,"jamesol@paran.com")//Level.SILVER -> GOLD : 등업 대상
					,new UserVO("james05", "이상무05", "4321", "GOLD:등업 대상 아님",100,33,Level.GOLD,"jamesol@paran.com")//Level.GOLD : 등업 대상 아님
				);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ tearDown()                                              │");
		log.debug("└─────────────────────────────────────────────────────────┘");
	}

	@Test
	void test() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **Test()**                        │");
		log.debug("└───────────────────────────────────┘");
	}

}
