package com.pcwk.ehr.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UppercaseHandler implements InvocationHandler {
	final Logger log = LogManager.getLogger(getClass());
	
	Object target;
	//다이나믹 프록시로부터 전달 받은 요청을 다시 타겟 오브젝트
	public UppercaseHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		log.debug("┌───────────────────────────────────┐");
		log.debug("│ **invoke()**                      │");
		log.debug("└───────────────────────────────────┘");
		
		log.debug("method : {}",method.getName());
		log.debug("args : {}",args.toString());
		
		//타겟으로 위임, 인터페이스의 모든 메서드 호출에 적용
		
		Object ret = method.invoke(target, args);
		log.debug("ret : {}",ret);
		//sayH 시작되는 메서드에만 부가 기능 적용
		
		if(ret instanceof String && method.getName().startsWith("sayH")) {
			return ((String) ret).toUpperCase();
		}else {
			return ret;
		}
	}

}
