package com.pcwk.ehr;

import static org.junit.Assert.*;
import org.apache.logging.log4j.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitLifeCycle {
	final static Logger log = LogManager.getLogger(JUnitLifeCycle.class);
	
	@BeforeClass
	public static void before() {
		log.debug("****@BeforeClass");
	}
	
	@AfterClass
	public static void after() {
		log.debug("****@AfterClass");
	}
	
	
	@Before //@Test 메서드 전에 실행
	public void setUp() throws Exception {
		log.debug("===========================================");
		log.debug("@before");
		log.debug("===========================================");
	}

	@After //@Test 메서드 수행 이후 실행
	public void tearDown() throws Exception {
		log.debug("===========================================");
		log.debug("@after");
		log.debug("===========================================");
	}

	@Test
	public void test() {
		log.debug("********************************************");
		log.debug("test");
		log.debug("********************************************");
	}
	
	@Test
	public void test2() {
		log.debug("********************************************");
		log.debug("test2");
		log.debug("********************************************");
	}


}
