package com.kyle.ie.appliance.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.kyle.ie.appliance.marshaller.EnumMarshaller;
import com.kyle.ie.appliance.models.constants.ControlType;
import com.kyle.ie.appliance.models.constants.EnergyRating;
import com.kyle.ie.appliance.models.constants.LoadingType;

public class WashingMachine extends Appliance {

	// rpm
	private int spinRate;

	private int numberOfProgrammes;

	private LoadingType loadingType;

	// db
	private int washNoiseLevel;
	private int spinNoiseLevel;

	private boolean quickWash;

	// litres
	private int annualWaterConsumption;

	// kwh
	private int annualEnergyConsumption;

	private EnergyRating energyRating;

	private boolean inBuiltHeater;

	private ControlType controlType;

	// kwh
	private int voltageMin;
	private int voltageMax;

	@DynamoDBAttribute
	public int getSpinRate() {
		return spinRate;
	}

	public void setSpinRate(int spinRate) {
		this.spinRate = spinRate;
	}

	@DynamoDBAttribute
	public int getNumberOfProgrammes() {
		return numberOfProgrammes;
	}

	public void setNumberOfProgrammes(int numberOfProgrammes) {
		this.numberOfProgrammes = numberOfProgrammes;
	}

	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public LoadingType getLoadingType() {
		return loadingType;
	}

	public void setLoadingType(LoadingType loadingType) {
		this.loadingType = loadingType;
	}

	@DynamoDBAttribute
	public int getWashNoiseLevel() {
		return washNoiseLevel;
	}

	@DynamoDBAttribute
	public void setWashNoiseLevel(int washNoiseLevel) {
		this.washNoiseLevel = washNoiseLevel;
	}

	@DynamoDBAttribute
	public int getSpinNoiseLevel() {
		return spinNoiseLevel;
	}

	public void setSpinNoiseLevel(int spinNoiseLevel) {
		this.spinNoiseLevel = spinNoiseLevel;
	}

	@DynamoDBAttribute
	public boolean getQuickWash() {
		return quickWash;
	}

	public void setQuickWash(boolean quickWash) {
		this.quickWash = quickWash;
	}

	@DynamoDBAttribute
	public int getAnnualWaterConsumption() {
		return annualWaterConsumption;
	}

	public void setAnnualWaterConsumption(int annualWaterConsumption) {
		this.annualWaterConsumption = annualWaterConsumption;
	}
	
	@DynamoDBAttribute
	public int getAnnualEnergyConsumption() {
		return annualEnergyConsumption;
	}

	public void setAnnualEnergyConsumption(int annualEnergyConsumption) {
		this.annualEnergyConsumption = annualEnergyConsumption;
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
	public boolean getInBuiltHeater() {
		return inBuiltHeater;
	}

	public void setInBuiltHeater(boolean inBuiltHeater) {
		this.inBuiltHeater = inBuiltHeater;
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

}
