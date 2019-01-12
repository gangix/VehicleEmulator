package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
@Entity
public class FuelTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String vehicleType;
	@Column
	private String vehicleName;
	@Column(precision = 5, scale = 2)
	private BigDecimal currentRemaining;
	@Column
	private String explanation;
	@NotNull
	@Column
	private LocalDateTime localDateTime;
	
	public FuelTransaction(String vehicleType, String vehicleName, BigDecimal currentRemaining, String explanation, LocalDateTime localDateTime) {
		super();
		this.vehicleType = vehicleType;
		this.vehicleName = vehicleName;
		this.currentRemaining = currentRemaining;
		this.explanation = explanation;
		this.localDateTime = localDateTime;
	}
	
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public BigDecimal getCurrentRemaining() {
		return currentRemaining;
	}
	public void setCurrentRemaining(BigDecimal currentRemaining) {
		this.currentRemaining = currentRemaining;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	
}
