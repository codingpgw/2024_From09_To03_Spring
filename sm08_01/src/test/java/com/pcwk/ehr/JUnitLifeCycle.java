package com.pcwk.ehr;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JUnitLifeCycle {
	final static Logger log = LogManager.getLogger(JUnitLifeCycle.class);
	
	@BeforeAll
	public static void before() {
		log.debug("****@BeforeAll 시작 1회");
	}
	
	@AfterAll
	public static void after() {
		log.debug("****@AfterAll 종료 1회");
	}
	
	
	@BeforeEach //@Test 메서드 전에 실행
	public void setUp() throws Exception {
		log.debug("===========================================");
		log.debug("@BeforeEach");
		log.debug("===========================================");
	}

	@AfterEach //@Test 메서드 수행 이후 실행
	public void tearDown() throws Exception {
		log.debug("===========================================");
		log.debug("@AfterEach");
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
