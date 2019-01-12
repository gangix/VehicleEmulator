package com.example.demo.model;

import org.springframework.context.event.ApplicationEventMulticaster;

public class Bus extends Vehicle {

	public Bus(ApplicationEventMulticaster aemParam, String name) {
		super(name, EVehicleType.BUS, aemParam);
	}

}
