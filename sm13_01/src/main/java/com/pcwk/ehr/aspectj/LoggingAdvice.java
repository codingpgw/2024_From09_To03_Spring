package com.pcwk.ehr.aspectj;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class LoggingAdvice {
	
	final Logger log = LogManager.getLogger(LoggingAdvice.class);
	
	public void logging(JoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		
		String methodName = signature.getName();//메서드 이름
		
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **logging()**                     │");
		log.debug("└───────────────────────────────────┘");
		
		
	}
}
