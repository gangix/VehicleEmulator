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
public class IncidentTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String firstVehicleName;
	@Column
	private String secondVehicleName;
	@Column(precision = 5, scale = 2)
	private BigDecimal oldRemainingGasOfFirst;
	@Column(precision = 5, scale = 2)
	private BigDecimal oldRemainingGasOfSecond;
	@Column(precision = 5, scale = 2)
	private BigDecimal newRemainingGas;
	@Column
	private String explanation;
	@NotNull
	@Column(columnDefinition="timestamp")
	private LocalDateTime localDateTime;

	public IncidentTransaction(String firstVehicleName, String secondVehicleName, BigDecimal oldRemainingGasOfFirst,
			BigDecimal oldRemainingGasOfSecond, String explanation, LocalDateTime localDateTime) {
		super();
		this.firstVehicleName = firstVehicleName;
		this.secondVehicleName = secondVehicleName;
		this.oldRemainingGasOfFirst = oldRemainingGasOfFirst;
		this.oldRemainingGasOfSecond = oldRemainingGasOfSecond;
		this.newRemainingGas = BigDecimal.ZERO;
		this.explanation = explanation;
		this.localDateTime = localDateTime;
	}

	public String getFirstVehicleName() {
		return firstVehicleName;
	}

	public void setFirstVehicleName(String firstVehicleName) {
		this.firstVehicleName = firstVehicleName;
	}

	public String getSecondVehicleName() {
		return secondVehicleName;
	}

	public void setSecondVehicleName(String secondVehicleName) {
		this.secondVehicleName = secondVehicleName;
	}

	public BigDecimal getOldRemainingGasOfFirst() {
		return oldRemainingGasOfFirst;
	}

	public void setOldRemainingGasOfFirst(BigDecimal oldRemainingGasOfFirst) {
		this.oldRemainingGasOfFirst = oldRemainingGasOfFirst;
	}

	public BigDecimal getOldRemainingGasOfSecond() {
		return oldRemainingGasOfSecond;
	}

	public void setOldRemainingGasOfSecond(BigDecimal oldRemainingGasOfSecond) {
		this.oldRemainingGasOfSecond = oldRemainingGasOfSecond;
	}

	public BigDecimal getNewRemainingGas() {
		return newRemainingGas;
	}

	public void setNewRemainingGas(BigDecimal newRemainingGas) {
		this.newRemainingGas = newRemainingGas;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	
}
