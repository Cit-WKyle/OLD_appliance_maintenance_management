package com.kyle.ie.appliance.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.kyle.ie.appliance.marshaller.EnumMarshaller;
import com.kyle.ie.appliance.models.constants.ControlType;

public class Microwave extends Appliance {

	//litres
	private double capacity;
	
	private ControlType controlType;
	private boolean defrostFunction;
	
	private boolean led;
	private boolean lcd;
	
	private String cavitySurfaceMaterial;
	
	// kwh
	private int voltageMin;
	private int voltageMax;
	
	// W
	private int power;
	
	private int powerLevelNo;
	private int grillPower;
	
	//mm
	private double turntableSize;
	
	private int autoFunctions;

	@DynamoDBAttribute
	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public ControlType getControlType() {
		return controlType;
	}

	public void setControlType(ControlType controlType) {
		this.controlType = controlType;
	}

	@DynamoDBAttribute
	public boolean getDefrostFunction() {
		return defrostFunction;
	}

	public void setDefrostFunction(boolean defrostFunction) {
		this.defrostFunction = defrostFunction;
	}

	@DynamoDBAttribute
	public boolean getLed() {
		return led;
	}

	public void setLed(boolean led) {
		this.led = led;
	}

	@DynamoDBAttribute
	public boolean getLcd() {
		return lcd;
	}

	public void setLcd(boolean lcd) {
		this.lcd = lcd;
	}

	@DynamoDBAttribute
	public String getCavitySurfaceMaterial() {
		return cavitySurfaceMaterial;
	}

	public void setCavitySurfaceMaterial(String cavitySurfaceMaterial) {
		this.cavitySurfaceMaterial = cavitySurfaceMaterial;
	}

	@DynamoDBAttribute
	public int getVoltageMin() {
		return voltageMin;
	}

	public void setVoltageMin(int voltageMin) {
		this.voltageMin = voltageMin;
	}

	@DynamoDBAttribute
	public int getVoltageMax() {
		return voltageMax;
	}

	public void setVoltageMax(int voltageMax) {
		this.voltageMax = voltageMax;
	}

	@DynamoDBAttribute
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	@DynamoDBAttribute
	public int getPowerLevelNo() {
		return powerLevelNo;
	}

	public void setPowerLevelNo(int powerLevelNo) {
		this.powerLevelNo = powerLevelNo;
	}

	@DynamoDBAttribute
	public int getGrillPower() {
		return grillPower;
	}

	public void setGrillPower(int grillPower) {
		this.grillPower = grillPower;
	}

	@DynamoDBAttribute
	public double getTurntableSize() {
		return turntableSize;
	}

	public void setTurntableSize(double turntableSize) {
		this.turntableSize = turntableSize;
	}

	@DynamoDBAttribute
	public int getAutoFunctions() {
		return autoFunctions;
	}

	public void setAutoFunctions(int autoFunctions) {
		this.autoFunctions = autoFunctions;
	}
	
}
