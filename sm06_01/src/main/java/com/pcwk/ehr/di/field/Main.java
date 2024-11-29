package com.pcwk.ehr.di.field;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext("com.pcwk.ehr");
		
		Car car = context.getBean(Car.class);
		car.drive();

	}

}
