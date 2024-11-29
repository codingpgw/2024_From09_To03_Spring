package com.pcwk.ehr.di;

public class Car {
	private Engine engine;

	public Car(Engine engine) {
		super();
		this.engine = engine;
	}
	
	public void drive() {
		engine.strat();
		System.out.println("Car 출발");
	}
}
