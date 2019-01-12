package com.example.demo.eventman;

import org.springframework.context.ApplicationEvent;

import com.example.demo.model.EVehicleType;

public class DefectDetectedEvent extends ApplicationEvent {

	private static final long serialVersionUID = 4939990986618611229L;

	private EVehicleType vehicleType;
	
	public DefectDetectedEvent() {
		super(DefectDetectedEvent.class);
	}

	public EVehicleType getType() {
		return vehicleType;
	}

	public void setType(EVehicleType type) {
		this.vehicleType = type;
	}
	
	

}
