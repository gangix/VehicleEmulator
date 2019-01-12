package com.example.demo.model;

import org.springframework.context.event.ApplicationEventMulticaster;

public class Truck extends Vehicle {

	public Truck(ApplicationEventMulticaster aemParam, String name) {
		super(name, EVehicleType.TRUCK, aemParam);
	}

}
