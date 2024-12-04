package com.pcwk.ehr.aspectj.around;

import static org.junit.jupiter.api.Assertions.*;

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

import com.pcwk.ehr.aspectj.Member;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/com/pcwk/ehr/aspectj/around_applicationContext.xml" })
class AroundAspectJTest {
	final Logger log = LogManager.getLogger(getClass());

	@Autowired // 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 주입된다.
	ApplicationContext context;

	@Autowired
	Member member;

	@BeforeEach
	void setUp() throws Exception {
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ setUp()                                                 │");
		log.debug("└─────────────────────────────────────────────────────────┘");
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
		assertNotNull(context);
		assertNotNull(member);

		log.debug("context:{}", context);
		log.debug("member:{}", member);
	}

	// @Disabled
	@Test
	void aroundAspectJ() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **aroundAspectJ()**               │");
		log.debug("└───────────────────────────────────┘");

		member.doSave();
		member.delete();
		member.doUpdate();
	}

}
