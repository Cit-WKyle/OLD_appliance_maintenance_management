package com.kyle.ie.appliance.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.kyle.ie.appliance.marshaller.EnumMarshaller;
import com.kyle.ie.appliance.models.constants.ControlType;
import com.kyle.ie.appliance.models.constants.DryingPerformanceClass;
import com.kyle.ie.appliance.models.constants.EnergyRating;
import com.kyle.ie.appliance.models.constants.WashingPerformanceClass;

public class DishWasher extends Appliance {

	private int placeSettings;
	private int presetCycles;

	private boolean delayStart;
	private boolean antiFloodProtection;

	// db
	private int noiseLevel;

	private ControlType controlType;
	
	private boolean interiorLight;
	private boolean lcd;
	private boolean led;

	// litres
	private int annualWaterConsumption;

	// kwh
	private int annualEnergyConsumption;

	// Hz
	private int frequency;

	// V
	private int voltageMin;
	private int voltageMax;

	private DryingPerformanceClass dryingPerformanceClass;
	private WashingPerformanceClass washingPerformanceClass;
	private EnergyRating energyRating;

	@DynamoDBAttribute
	public int getPlaceSettings() {
		return placeSettings;
	}

	public void setPlaceSettings(int placeSettings) {
		this.placeSettings = placeSettings;
	}

	@DynamoDBAttribute
	public int getPresetCycles() {
		return presetCycles;
	}

	public void setPresetCycles(int presetCycles) {
		this.presetCycles = presetCycles;
	}

	@DynamoDBAttribute
	public boolean getDelayStart() {
		return delayStart;
	}

	public void setDelayStart(boolean delayStart) {
		this.delayStart = delayStart;
	}

	@DynamoDBAttribute
	public boolean getAntiFloodProtection() {
		return antiFloodProtection;
	}

	public void setAntiFloodProtection(boolean antiFloodProtection) {
		this.antiFloodProtection = antiFloodProtection;
	}

	@DynamoDBAttribute
	public int getNoiseLevel() {
		return noiseLevel;
	}

	public void setNoiseLevel(int noiseLevel) {
		this.noiseLevel = noiseLevel;
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

	@DynamoDBAttribute
	public boolean getLcd() {
		return lcd;
	}

	public void setLcd(boolean lcd) {
		this.lcd = lcd;
	}

	@DynamoDBAttribute
	public boolean getLed() {
		return led;
	}

	public void setLed(boolean led) {
		this.led = led;
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

	@DynamoDBAttribute
	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
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
	public DryingPerformanceClass getDryingPerformanceClass() {
		return dryingPerformanceClass;
	}

	public void setDryingPerformanceClass(DryingPerformanceClass dryingPerformanceClass) {
		this.dryingPerformanceClass = dryingPerformanceClass;
	}
	
	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public WashingPerformanceClass getWashingPerformanceClass() {
		return washingPerformanceClass;
	}

	public void setWashingPerformanceClass(WashingPerformanceClass washingPerformanceClass) {
		this.washingPerformanceClass = washingPerformanceClass;
	}
	
	@DynamoDBMarshalling(marshallerClass=EnumMarshaller.class)
	@DynamoDBAttribute
	public EnergyRating getEnergyRating() {
		return energyRating;
	}

	public void setEnergyRating(EnergyRating energyRating) {
		this.energyRating = energyRating;
	}

}
