package com.pcwk.ehr.proxy;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ProxyTest {
	final Logger log = LogManager.getLogger(getClass());
	
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
	
	@Test
	void dynamicProxy() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **dynamicProxy()**                │");
		log.debug("└───────────────────────────────────┘");
		
		//다이나믹 프록시
		//런타임 시 동적으로 만들어지는 오브젝트
		//리플렉션을 이용해서 프록시 생성
		
		Hello dynamicProxiedHello = (Hello) Proxy.newProxyInstance(
				getClass().getClassLoader(),//동적으로 구성되는 다이나믹 프록시 클래스 로딩 
				new Class[] {Hello.class},  //구현할 인터페이스 
				new UppercaseHandler(new HelloTarget())//부가 기능과 위임 코드를 담은 InvocationHandler
				);
		log.debug(dynamicProxiedHello.sayHello("Pcwk"));
		log.debug(dynamicProxiedHello.sayHi("Pcwk"));
		log.debug(dynamicProxiedHello.sayThankYou("Pcwk"));
	}
	
	@Disabled
	@Test
	void upperProxy() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **upperProxy()**                  │");
		log.debug("└───────────────────────────────────┘");
		
		Hello proxyedHello = new HelloUpperCase(new HelloTarget());
		log.debug(proxyedHello.sayHello("Pcwk"));
		log.debug(proxyedHello.sayHi("Pcwk"));
		log.debug(proxyedHello.sayThankYou("Pcwk"));
		
		assertEquals("HELLO PCWK",proxyedHello.sayHello("Pcwk"));
		assertEquals("HI PCWK",proxyedHello.sayHi("Pcwk"));
		assertEquals("THANK YOU PCWK",proxyedHello.sayThankYou("Pcwk"));
	}
	
	@Disabled
	@Test
	void simpleProxy() {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **upgradeLevels()**               │");
		log.debug("└───────────────────────────────────┘");
		
		Hello hello = new HelloTarget();
		log.debug(hello.sayHello("Pcwk"));
		log.debug(hello.sayHi("Pcwk"));
		log.debug(hello.sayThankYou("Pcwk"));
		
		assertEquals("Hello Pcwk", hello.sayHello("Pcwk"));
		assertEquals("Hi Pcwk", hello.sayHi("Pcwk"));
		assertEquals("Thank You Pcwk", hello.sayThankYou("Pcwk"));
		
	}

}
