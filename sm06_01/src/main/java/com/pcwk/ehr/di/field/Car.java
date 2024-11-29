package com.pcwk.ehr.di.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
	
	@Autowired
	private Engine engine;//필드 주입
	
	public void drive() {
		engine.strat();
		System.out.println("자동차 출발");
	}
	
}
