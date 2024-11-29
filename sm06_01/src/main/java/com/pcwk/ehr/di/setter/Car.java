package com.pcwk.ehr.di.setter;

public class Car {
	
	private Engine engine;

	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	public void drive() {
		engine.strat();
		System.out.println("자동차 출발");
	}
	
	
}
