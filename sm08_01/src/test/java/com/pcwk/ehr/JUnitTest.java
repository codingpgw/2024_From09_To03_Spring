package com.pcwk.ehr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
public class JUnitTest {
	final Logger log = LogManager.getLogger(getClass());
	
	@Disabled //JUnit4 @Ignore
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
	
	@Disabled
	@Test
	public void test() {
		log.debug("--------------------------------------------");
		log.debug("test");
		log.debug("--------------------------------------------");
	}
	
	@Disabled
	@Test
	public void pcwkTest() {
		log.debug("--------------------------------------------");
		log.debug("pcwkTest");
		log.debug("--------------------------------------------");
	}

}
