package com.example.demo.eventman;

import org.springframework.context.ApplicationEvent;

import com.example.demo.model.Vehicle;

public class EmptyTankEvent extends ApplicationEvent{

	private static final long serialVersionUID = 4939990986618611229L;

	private float remainingGas;
	private Vehicle vechile;
	
	public EmptyTankEvent() {
		super(EmptyTankEvent.class);
	}

	public float getRemainingGas() {
		return remainingGas;
	}

	public void setRemainingGas(float remainingGas) {
		this.remainingGas = remainingGas;
	}

	public Vehicle getVechile() {
		return vechile;
	}

	public void setVechile(Vehicle vechile) {
		this.vechile = vechile;
	}
}
