package com.example.demo.eventman;

import org.springframework.context.ApplicationEvent;

public class IncidentDetectedEvent extends ApplicationEvent {

	private static final long serialVersionUID = 4939990986618611229L;

	public IncidentDetectedEvent() {
		super(IncidentDetectedEvent.class);
	}
}
