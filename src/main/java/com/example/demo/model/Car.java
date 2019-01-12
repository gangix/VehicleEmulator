package com.example.demo.model;

import org.springframework.context.event.ApplicationEventMulticaster;

public class Car extends Vehicle {

	public Car(ApplicationEventMulticaster aemParam, String name) {
		super(name, EVehicleType.CAR, aemParam);
	}

}
