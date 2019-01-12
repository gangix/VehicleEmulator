package com.example.demo.model;

public enum EVehicleType {

	CAR(40F, 0.1F, "CAR"), BUS(100F, 0.5F, "BUS"), TRUCK(130F, 0.7F, "TRUCK");

	private EVehicleType(float tankSize, float avarageConsumption, String typeName) {
		this.tankSize = tankSize;
		this.avarageConsumption = avarageConsumption;
		this.typeName = typeName;
	}

	private float tankSize;
	private float avarageConsumption;
	private String typeName;

	public float getTankSize() {
		return tankSize;
	}

	public float getAvarageConsumption() {
		return avarageConsumption;
	}

	public void setTankSize(float tankSize) {
		this.tankSize = tankSize;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
