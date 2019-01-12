package com.example.demo.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;

import com.example.demo.eventman.DefectDetectedEvent;
import com.example.demo.eventman.EmptyTankEvent;
import com.example.demo.eventman.HeartBeatEvent;

public abstract class Vehicle implements ApplicationListener<HeartBeatEvent> {

	private static final Logger logger = LoggerFactory.getLogger(Vehicle.class);
	private ApplicationEventMulticaster aem;
	private EVehicleType vehicleType;
	private float remainingGas;
	private boolean fillInProgress = false;
	private long fillUntilIndex = -1L;
	private String name;
	
	public Vehicle(String name, EVehicleType vechileTypeParam, ApplicationEventMulticaster aemParam) {
		this.name = name;
		this.vehicleType = vechileTypeParam;
		this.remainingGas = vehicleType.getTankSize();
		this.aem = aemParam;
		this.aem.addApplicationListener(this);
	}

	@Override
	public void onApplicationEvent(HeartBeatEvent event) {
		if(this.remainingGas==0) {
			if (logger.isDebugEnabled()) {
				logger.debug("Vechile : " + name + " remaining gas is Zero. it s out of game.");
			}
			return;
		}
		long index = event.getIndex();
		if (!fillInProgress) {
			remainingGas -= vehicleType.getAvarageConsumption();
			if (logger.isDebugEnabled()) {
				logger.debug("Vechile : " + name + " remaining gas : " + String.format("%.2f", remainingGas));
			}
			if (remainingGas < (vehicleType.getTankSize() / 10)) {
				EmptyTankEvent emptyTankEvent = new EmptyTankEvent();
				emptyTankEvent.setRemainingGas(remainingGas);
				emptyTankEvent.setVechile(this);
				aem.multicastEvent(emptyTankEvent);
			}
		} else {
			if (index >= fillUntilIndex) {
				fillInProgress = false;
				remainingGas = vehicleType.getTankSize();
			} else if (logger.isDebugEnabled()) {
				logger.debug("Vechile : " + name + " fill in progress and current index : " + index + " fillUntil : "
						+ fillUntilIndex);
			}
		}
		if (index % 360 == 0) {
			sendVehicleTypeAlert(this.vehicleType);
		}
	}
	
	public void sendVehicleTypeAlert(EVehicleType eVehicleType) {
		DefectDetectedEvent defectDetectedBean = new DefectDetectedEvent();
		defectDetectedBean.setType(eVehicleType);
		aem.multicastEvent(defectDetectedBean);
	}

	public boolean isFillInProgress() {
		return fillInProgress;
	}

	public void setFillInProgress(boolean fillInProgress) {
		this.fillInProgress = fillInProgress;
	}

	public long getFillUntilIndex() {
		return fillUntilIndex;
	}

	public void setFillUntilIndex(long fillUntilIndex) {
		this.fillUntilIndex = fillUntilIndex;
	}

	public EVehicleType getVechileType() {
		return vehicleType;
	}

	public String getName() {
		return name;
	}

	public float getRemainingGas() {
		return remainingGas;
	}
	public void setRemainingGas(float remainingGas) {
		this.remainingGas = remainingGas;
	}



}
