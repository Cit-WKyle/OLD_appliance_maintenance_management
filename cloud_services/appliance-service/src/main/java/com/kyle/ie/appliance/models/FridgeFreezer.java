package com.kyle.ie.appliance.models;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.kyle.ie.appliance.marshaller.EnumMarshaller;
import com.kyle.ie.appliance.models.constants.ClimateClass;
import com.kyle.ie.appliance.models.constants.ControlType;
import com.kyle.ie.appliance.models.constants.CoolingAgent;
import com.kyle.ie.appliance.models.constants.DoorType;
import com.kyle.ie.appliance.models.constants.EnergyRating;
import com.kyle.ie.appliance.models.constants.FitType;

public class FridgeFreezer extends Appliance {

	private FitType fitType;

	private int fridgePercentage;
	private int freezerPercentage;

	private boolean waterDispenser;
	private boolean autoDefrost;

	private ClimateClass climateClass;

	// db
	private int noiseLevel;

	private int noOfCompressors;

	// hours
	private int powerFailureSafeStorageTime;

	// litres
	private int fridgeCapacity;

	private int fridgeShelves;

	private boolean fastFreeze;

	// kg
	private int freezingCapacity;

	// litre
	private int freezerCapacity;

	private int freezerShelves;
	private boolean frostFree;

	private ControlType controlType;

	private boolean interiorLight;

	private DoorType doorType;

	private int noOfDoors;

	// kwh
	private int annualEnergyConsumption;

	private List<String> approvedCertifications;

	private EnergyRating energyRating;

	private boolean plumbed;
	
	private CoolingAgent coolingAgent;

	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public FitType getFitType() {
		return fitType;
	}

	public void setFitType(FitType fitType) {
		this.fitType = fitType;
	}
	
	@DynamoDBAttribute
	public int getFridgePercentage() {
		return fridgePercentage;
	}

	public void setFridgePercentage(int fridgePercentage) {
		this.fridgePercentage = fridgePercentage;
	}
	
	@DynamoDBAttribute
	public int getFreezerPercentage() {
		return freezerPercentage;
	}

	public void setFreezerPercentage(int freezerPercentage) {
		this.freezerPercentage = freezerPercentage;
	}
	
	@DynamoDBAttribute
	public boolean getWaterDispenser() {
		return waterDispenser;
	}

	public void setWaterDispenser(boolean waterDispenser) {
		this.waterDispenser = waterDispenser;
	}

	public boolean getAutoDefrost() {
		return autoDefrost;
	}

	public void setAutoDefrost(boolean autoDefrost) {
		this.autoDefrost = autoDefrost;
	}

	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public ClimateClass getClimateClass() {
		return climateClass;
	}

	public void setClimateClass(ClimateClass climateClass) {
		this.climateClass = climateClass;
	}
	
	@DynamoDBAttribute
	public int getNoiseLevel() {
		return noiseLevel;
	}

	public void setNoiseLevel(int noiseLevel) {
		this.noiseLevel = noiseLevel;
	}
	
	@DynamoDBAttribute
	public int getNoOfCompressors() {
		return noOfCompressors;
	}

	public void setNoOfCompressors(int noOfCompressors) {
		this.noOfCompressors = noOfCompressors;
	}
	
	@DynamoDBAttribute
	public int getPowerFailureSafeStorageTime() {
		return powerFailureSafeStorageTime;
	}

	public void setPowerFailureSafeStorageTime(int powerFailureSafeStorageTime) {
		this.powerFailureSafeStorageTime = powerFailureSafeStorageTime;
	}
	
	@DynamoDBAttribute
	public int getFridgeCapacity() {
		return fridgeCapacity;
	}

	public void setFridgeCapacity(int fridgeCapacity) {
		this.fridgeCapacity = fridgeCapacity;
	}
	
	@DynamoDBAttribute
	public int getFridgeShelves() {
		return fridgeShelves;
	}

	public void setFridgeShelves(int fridgeShelves) {
		this.fridgeShelves = fridgeShelves;
	}
	
	@DynamoDBAttribute
	public boolean getFastFreeze() {
		return fastFreeze;
	}

	public void setFastFreeze(boolean fastFreeze) {
		this.fastFreeze = fastFreeze;
	}
	
	@DynamoDBAttribute
	public int getFreezingCapacity() {
		return freezingCapacity;
	}

	public void setFreezingCapacity(int freezingCapacity) {
		this.freezingCapacity = freezingCapacity;
	}

	@DynamoDBAttribute
	public int getFreezerCapacity() {
		return freezerCapacity;
	}

	public void setFreezerCapacity(int freezerCapacity) {
		this.freezerCapacity = freezerCapacity;
	}

	@DynamoDBAttribute
	public int getFreezerShelves() {
		return freezerShelves;
	}

	public void setFreezerShelves(int freezerShelves) {
		this.freezerShelves = freezerShelves;
	}
	
	@DynamoDBAttribute
	public boolean isFrostFree() {
		return frostFree;
	}

	public void setFrostFree(boolean frostFree) {
		this.frostFree = frostFree;
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
	public boolean getInteriorLight() {
		return interiorLight;
	}

	public void setInteriorLight(boolean interiorLight) {
		this.interiorLight = interiorLight;
	}

	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public DoorType getDoorType() {
		return doorType;
	}

	public void setDoorType(DoorType doorType) {
		this.doorType = doorType;
	}

	@DynamoDBAttribute
	public int getNoOfDoors() {
		return noOfDoors;
	}

	public void setNoOfDoors(int noOfDoors) {
		this.noOfDoors = noOfDoors;
	}

	@DynamoDBAttribute
	public int getAnnualEnergyConsumption() {
		return annualEnergyConsumption;
	}

	public void setAnnualEnergyConsumption(int annualEnergyConsumption) {
		this.annualEnergyConsumption = annualEnergyConsumption;
	}

	@DynamoDBAttribute
	public List<String> getApprovedCertifications() {
		return approvedCertifications;
	}

	public void setApprovedCertifications(List<String> approvedCertifications) {
		this.approvedCertifications = approvedCertifications;
	}

	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public EnergyRating getEnergyRating() {
		return energyRating;
	}

	public void setEnergyRating(EnergyRating energyRating) {
		this.energyRating = energyRating;
	}

	@DynamoDBAttribute
	public boolean getPlumbed() {
		return plumbed;
	}

	public void setPlumbed(boolean plumbed) {
		this.plumbed = plumbed;
	}

	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public CoolingAgent getCoolingAgent() {
		return coolingAgent;
	}

	public void setCoolingAgent(CoolingAgent coolingAgent) {
		this.coolingAgent = coolingAgent;
	}

}
