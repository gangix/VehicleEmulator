package com.example.demo;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.eventman.HeartBeatEvent;
import com.example.demo.eventman.IncidentDetectedEvent;

@Configuration
public class EmuConfiguration {
	
	@Autowired
	private SystemCounter systemCounter;

	@Scheduled(fixedRate = 5_000,initialDelay=5_000)
	public void heartBeat() {
		ApplicationEventMulticaster aem = applicationEventMulticaster();
		long index = systemCounter.increase();
		HeartBeatEvent heartBeatEvent = new HeartBeatEvent(index);
		aem.multicastEvent(heartBeatEvent);
	}
	
	@Scheduled(fixedRate = 30_000,initialDelay=30_000)
	public void incidentControl() {
		ApplicationEventMulticaster aem = applicationEventMulticaster();
		IncidentDetectedEvent incidentDetectedEvent = new IncidentDetectedEvent();
		aem.multicastEvent(incidentDetectedEvent);
	}
	
	@Bean
	public Executor execForMulticastEvent() {
		return Executors.newFixedThreadPool(10);
	}

	@Bean
	public ApplicationEventMulticaster applicationEventMulticaster() {
		SimpleApplicationEventMulticaster applicationEventMulticasterLoc = new SimpleApplicationEventMulticaster();
		applicationEventMulticasterLoc.setTaskExecutor(this.execForMulticastEvent());
		return applicationEventMulticasterLoc;
	}


}
