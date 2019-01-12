package com.example.demo.eventman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;

import com.example.demo.model.Vehicle;


public class IncidentApplicationListener implements ApplicationListener<IncidentDetectedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(IncidentApplicationListener.class);

	private ApplicationEventMulticaster aem;
	private Vehicle vehicle1;
	private Vehicle vehicle2;

	public IncidentApplicationListener(ApplicationEventMulticaster aem, Vehicle vehicle1, Vehicle vehicle2) {
		super();
		this.vehicle1 = vehicle1;
		this.vehicle2 = vehicle2;
		this.aem = aem;
		aem.addApplicationListener(this);
	}

	@Override
	public void onApplicationEvent(IncidentDetectedEvent event) {
		if(vehicle1.getRemainingGas() == 0 && vehicle2.getRemainingGas() == 0) {
			return;
		}
		if (!(vehicle1.getRemainingGas() < (vehicle1.getVechileType().getTankSize() / 4)
				&& vehicle2.getRemainingGas() < (vehicle2.getVechileType().getTankSize() / 4))) {
			return;
		}
		if(logger.isDebugEnabled()) {
			logger.debug(vehicle1.getName() + " and "+vehicle2.getName()+ " had incident report");
		}
		ResetRemainingGasEvent resetRemainingGasEvent = new ResetRemainingGasEvent(vehicle1, vehicle2);
		aem.multicastEvent(resetRemainingGasEvent);

	}
}