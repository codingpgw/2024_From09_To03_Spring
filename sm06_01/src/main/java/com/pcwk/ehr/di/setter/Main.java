package com.pcwk.ehr.di.setter;

public class Main {

	public static void main(String[] args) {
		Engine engine = new Engine();
		
		Car car = new Car();
		car.setEngine(engine);
		
		car.drive();
	}

}
