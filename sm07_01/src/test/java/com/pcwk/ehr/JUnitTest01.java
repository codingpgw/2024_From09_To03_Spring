package com.pcwk.ehr;

import static org.junit.Assert.*;
import org.apache.logging.log4j.*;

import org.junit.Test;

public class JUnitTest01 {
	final Logger log = LogManager.getLogger(getClass());
	
	@Test
	public void testSubtraction() {
		int x = 13;
		int y = 15;
		
		int result = x-y;
		
		assertTrue(result == -2);//조건이 true인지 확인
	}
	
	@Test
	public void testAddition() {
		int x = 13;
		int y = 15;
		
		int result = x+y;
		
		assertEquals(28, result);
	}
	
	@Test
	public void test() {
		log.debug("--------------------------------------------");
		log.debug("test");
		log.debug("--------------------------------------------");
	}
	
	@Test
	public void pcwkTest() {
		log.debug("--------------------------------------------");
		log.debug("pcwkTest");
		log.debug("--------------------------------------------");
	}

}
