package com.pcwk.ehr.aspectj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

public class AroundAdvice {
	final Logger log = LogManager.getLogger(getClass());
	
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		Object retObj = null;
		log.debug("┌─────────────────────────────────────────────────────────┐");
		log.debug("│ aroundLog()                                             │");
		
		//method 명
		String methodName = pjp.getSignature().getName();
		
		//클래스명
		String className = pjp.getTarget().getClass().getName();
		
		retObj = pjp.proceed();
		
		log.debug("│ className                                               │"+className);
		log.debug("│ methodName                                              │"+methodName);
		log.debug("└─────────────────────────────────────────────────────────┘");
		
		return retObj;
	}
}
