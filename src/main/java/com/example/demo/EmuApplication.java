package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.eventman.IncidentApplicationListener;
import com.example.demo.model.Bus;
import com.example.demo.model.Car;
import com.example.demo.model.Truck;
import com.example.demo.model.Vehicle;

@SpringBootApplication
@EnableScheduling
public class EmuApplication implements ApplicationRunner{
	
	@Autowired
	private ApplicationEventMulticaster aem;
	
	private final Map<String,Vehicle> vehicleMap = new HashMap<>();

	public static void main(String[] args) {
		SpringApplication.run(EmuApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		for (int i=0; i<333; i++) {
			Vehicle vehicle1 = new Car(aem, "car-"+i);
			Vehicle vehicle2 = new Bus(aem, "bus-"+i);
			Vehicle vehicle3 = new Truck(aem, "truck"+i);
			vehicleMap.put("car-"+i, vehicle1);
			vehicleMap.put("bus-"+i, vehicle2);
			vehicleMap.put("truck-"+i, vehicle3);
			if(i%8==0) {
				new IncidentApplicationListener(aem, vehicle1, vehicle2);
			}
		}
		Vehicle vehicle = new Car(aem, "car-"+333);
		vehicleMap.put("car-"+333, vehicle);
	}

}

