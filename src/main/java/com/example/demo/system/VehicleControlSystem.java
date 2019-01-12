package com.example.demo.system;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.demo.SystemCounter;
import com.example.demo.eventman.DefectDetectedEvent;
import com.example.demo.eventman.EmptyTankEvent;
import com.example.demo.eventman.ResetRemainingGasEvent;
import com.example.demo.model.DefectAlertTransaction;
import com.example.demo.model.EVehicleType;
import com.example.demo.model.FuelTransaction;
import com.example.demo.model.IncidentTransaction;
import com.example.demo.model.Vehicle;
import com.example.demo.service.DefectAlertTransactionService;
import com.example.demo.service.FuelTransactionService;
import com.example.demo.service.IncidentTransactionService;

@Component
public class VehicleControlSystem {

	private Logger logger = LoggerFactory.getLogger(VehicleControlSystem.class);

	@Autowired
	private SystemCounter sysCounter;
	@Autowired
	private FuelTransactionService fuelTransactionService;
	@Autowired
	private DefectAlertTransactionService defectTransactionService;
	@Autowired
	private IncidentTransactionService incidentTransactionService;

	@EventListener(classes = EmptyTankEvent.class)
	public void handleEvent(EmptyTankEvent appEventParam) {
		Vehicle vechile = appEventParam.getVechile();
		if (!vechile.isFillInProgress()) {
			vechile.setFillInProgress(true);
			vechile.setFillUntilIndex(sysCounter.getIndex() + 2);

			saveFuelTransaction(vechile);
		}
	}

	private void saveFuelTransaction(Vehicle vehicle) {
		String typeName = vehicle.getVechileType().getTypeName();
		String vehicleName = vehicle.getName();
		BigDecimal remainingGas = BigDecimal.valueOf(vehicle.getRemainingGas()).setScale(2, RoundingMode.HALF_UP);
		String explanation = "Vehicle name :" + vehicle.getName() + ", Remaining Gas: " + remainingGas
				+ " so entered refueling";

		FuelTransaction fuelTransaction = new FuelTransaction(typeName, vehicleName, remainingGas, explanation,
				LocalDateTime.now());

		if (logger.isDebugEnabled()) {
			logger.debug(explanation);
		}
		try {
			fuelTransactionService.save(fuelTransaction);
		} catch (Exception e) {
			logger.debug("Exception while saving fuelTransaction " + e.getMessage());
		}

	}

	@EventListener(classes = DefectDetectedEvent.class)
	public void handleEvent(DefectDetectedEvent appEventParam) {
		logger.debug("Defect Detected");
		EVehicleType vehicleType = appEventParam.getType();
		float oldTankSize = vehicleType.getTankSize();
		float newTankSize = oldTankSize - (oldTankSize * 3 / 100);
		vehicleType.setTankSize(newTankSize);

		saveDefectAlertTransaction(vehicleType, oldTankSize);
	}

	private void saveDefectAlertTransaction(EVehicleType vehicleType, float oldTankSize) {
		String typeName = vehicleType.getTypeName();
		BigDecimal oldTankSizeFormatted = BigDecimal.valueOf(oldTankSize).setScale(2, RoundingMode.HALF_UP);

		BigDecimal newTankSizeFormatted = BigDecimal.valueOf(vehicleType.getTankSize()).setScale(2,
				RoundingMode.HALF_UP);

		String explanation = "Vechile : " + vehicleType.getTypeName() + " total tank size decreased as %3: " + "from "
				+ oldTankSizeFormatted + " to " + newTankSizeFormatted;

		DefectAlertTransaction defectAlertTransaction = new DefectAlertTransaction(typeName, oldTankSizeFormatted,
				newTankSizeFormatted, explanation, LocalDateTime.now());

		if (logger.isDebugEnabled()) {
			logger.debug(explanation);
		}
		try {
			defectTransactionService.save(defectAlertTransaction);
		} catch (Exception e) {
			logger.debug("Exception while saving defectTransaction " + e.getMessage());
		}
	}

	@EventListener(classes = ResetRemainingGasEvent.class)
	public void handleEvent(ResetRemainingGasEvent appEventParam) {
		logger.debug("Incident Detected");
		Vehicle vehicle1 = appEventParam.getFirstVehicle();
		Vehicle vehicle2 = appEventParam.getSecondVehicle();
		if (!(vehicle1.getRemainingGas() < (vehicle1.getVechileType().getTankSize() / 4)
				&& vehicle2.getRemainingGas() < (vehicle2.getVechileType().getTankSize() / 4))) {
			return;
		}
		BigDecimal oldRemainingGasOfFirst = BigDecimal.valueOf(vehicle1.getRemainingGas()).setScale(2, RoundingMode.HALF_UP);
		BigDecimal oldRemainingGasOfSecond = BigDecimal.valueOf(vehicle2.getRemainingGas()).setScale(2, RoundingMode.HALF_UP);
				
		vehicle1.setRemainingGas(0);
		vehicle2.setRemainingGas(0);

		saveIncidentReport(vehicle1, vehicle2, oldRemainingGasOfFirst, oldRemainingGasOfSecond);
	}

	private void saveIncidentReport(Vehicle firstVehicle, Vehicle secondVehicle, BigDecimal oldRemainingGasOfFirst,
			BigDecimal oldRemainingGasOfSecond) {
		String firstVehicleName = firstVehicle.getName();
		String secondVehicleName = secondVehicle.getName();
		String explanation = "Vehicles " + firstVehicle.getName() + " and " + secondVehicle.getName()
				+ "incident reported. Remaining gas updated as ZERO";
		IncidentTransaction incidentTransaction = new IncidentTransaction(firstVehicleName, secondVehicleName,
				oldRemainingGasOfFirst, oldRemainingGasOfSecond, explanation, LocalDateTime.now());

		try {
			incidentTransactionService.save(incidentTransaction);
		} catch (Exception e) {
			logger.debug("Exception while saving incidentTransaction " + e.getMessage());
		}
		if (logger.isDebugEnabled()) {
			logger.debug(explanation);
		}
	}

}
