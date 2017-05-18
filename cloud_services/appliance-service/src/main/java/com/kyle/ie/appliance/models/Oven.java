package com.kyle.ie.appliance.models;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.kyle.ie.appliance.marshaller.EnumMarshaller;
import com.kyle.ie.appliance.models.constants.ConstructionType;
import com.kyle.ie.appliance.models.constants.ControlType;
import com.kyle.ie.appliance.models.constants.DoorType;
import com.kyle.ie.appliance.models.constants.EnergyRating;
import com.kyle.ie.appliance.models.constants.GrillType;
import com.kyle.ie.appliance.models.constants.PowerSource;

public class Oven extends Appliance {

	private DoorType doorType;

	private int noOfOvens;

	private boolean singleOven;
	private boolean doubleOven;

	private ConstructionType constructionType;
	private String fuelType;

	private List<String> cleaning;

	// litre
	private double capacity;

	private ControlType controlType;

	private boolean interiorLights;

	private boolean autoShutOff;

	// A
	private double electricalConnection;

	// Hz
	private int frequency;

	// W
	private int power;

	private PowerSource powerSource;

	// kwh
	private int voltageMin;
	private int voltageMax;

	private EnergyRating energyRating;
	private GrillType grillType;

	private int noOfFunctions;

	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public DoorType getDoorType() {
		return doorType;
	}

	public void setDoorType(DoorType doorType) {
		this.doorType = doorType;
	}

	@DynamoDBAttribute
	public int getNoOfOvens() {
		return noOfOvens;
	}

	public void setNoOfOvens(int noOfOvens) {
		this.noOfOvens = noOfOvens;
	}

	@DynamoDBAttribute
	public boolean getSingleOven() {
		return singleOven;
	}

	public void setSingleOven(boolean singleOven) {
		this.singleOven = singleOven;
	}

	@DynamoDBAttribute
	public boolean getDoubleOven() {
		return doubleOven;
	}

	public void setDoubleOven(boolean doubleOven) {
		this.doubleOven = doubleOven;
	}

	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public ConstructionType getConstructionType() {
		return constructionType;
	}

	public void setConstructionType(ConstructionType constructionType) {
		this.constructionType = constructionType;
	}

	@DynamoDBAttribute
	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@DynamoDBAttribute
	public List<String> getCleaning() {
		return cleaning;
	}

	public void setCleaning(List<String> cleaning) {
		this.cleaning = cleaning;
	}

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
	public boolean getInteriorLights() {
		return interiorLights;
	}

	public void setInteriorLights(boolean interiorLights) {
		this.interiorLights = interiorLights;
	}

	@DynamoDBAttribute
	public boolean getAutoShutOff() {
		return autoShutOff;
	}

	public void setAutoShutOff(boolean autoShutOff) {
		this.autoShutOff = autoShutOff;
	}

	@DynamoDBAttribute
	public double getElectricalConnection() {
		return electricalConnection;
	}

	public void setElectricalConnection(double electricalConnection) {
		this.electricalConnection = electricalConnection;
	}

	@DynamoDBAttribute
	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	@DynamoDBAttribute
	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public PowerSource getPowerSource() {
		return powerSource;
	}

	public void setPowerSource(PowerSource powerSource) {
		this.powerSource = powerSource;
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

	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public EnergyRating getEnergyRating() {
		return energyRating;
	}

	public void setEnergyRating(EnergyRating energyRating) {
		this.energyRating = energyRating;
	}

	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public GrillType getGrillType() {
		return grillType;
	}

	public void setGrillType(GrillType grillType) {
		this.grillType = grillType;
	}

	@DynamoDBAttribute
	public int getNoOfFunctions() {
		return noOfFunctions;
	}

	public void setNoOfFunctions(int noOfFunctions) {
		this.noOfFunctions = noOfFunctions;
	}

}