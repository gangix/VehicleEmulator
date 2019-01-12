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
public class DefectAlertTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String vehicleType;
	@Column(precision = 5, scale = 2)
	private BigDecimal oldTankSize;
	@Column(precision = 5, scale = 2)
	private BigDecimal newTankSize;
	@Column
	private String explanation;
	@NotNull
	@Column(name="transaction_date",columnDefinition="timestamp")
	private LocalDateTime localDateTime;

	public DefectAlertTransaction(String vehicleType, BigDecimal oldTankSize, BigDecimal newTankSize,
			String explanation, LocalDateTime localDateTime) {
		super();
		this.vehicleType = vehicleType;
		this.oldTankSize = oldTankSize;
		this.newTankSize = newTankSize;
		this.explanation = explanation;
		this.localDateTime = localDateTime;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public BigDecimal getOldTankSize() {
		return oldTankSize;
	}

	public void setOldTankSize(BigDecimal oldTankSize) {
		this.oldTankSize = oldTankSize;
	}

	public BigDecimal getNewTankSize() {
		return newTankSize;
	}

	public void setNewTankSize(BigDecimal newTankSize) {
		this.newTankSize = newTankSize;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

}
