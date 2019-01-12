package com.example.demo.eventman;

import org.springframework.context.ApplicationEvent;

import com.example.demo.model.Vehicle;

public class ResetRemainingGasEvent extends ApplicationEvent{

	private static final long serialVersionUID = 4939990986618611229L;

	private Vehicle firstVehicle;
	private Vehicle secondVehicle;
	
	public ResetRemainingGasEvent(Vehicle firstVehicle,Vehicle secondVehicle) {
		super(ResetRemainingGasEvent.class);
		this.firstVehicle=firstVehicle;
		this.secondVehicle=secondVehicle;
	}

	public Vehicle getFirstVehicle() {
		return firstVehicle;
	}

	public Vehicle getSecondVehicle() {
		return secondVehicle;
	}

}
