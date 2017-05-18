package com.kyle.ie.appliance.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.kyle.ie.appliance.marshaller.EnumMarshaller;
import com.kyle.ie.appliance.models.constants.EnergyRating;

public class TumbleDryer extends Appliance {

	private boolean vented;
	private boolean condensed;

	// kg
	private int dryerCapacity;

	// db
	private int spinNoiseLevel;

	// rpm
	private int spinRate;

	private boolean delayStart;
	private boolean inbuiltHome;

	private EnergyRating energyRating;

	@DynamoDBAttribute
	public boolean getVented() {
		return vented;
	}

	public void setVented(boolean vented) {
		this.vented = vented;
	}

	@DynamoDBAttribute
	public boolean getCondensed() {
		return condensed;
	}

	public void setCondensed(boolean condensed) {
		this.condensed = condensed;
	}

	@DynamoDBAttribute
	public int getDryerCapacity() {
		return dryerCapacity;
	}

	public void setDryerCapacity(int dryerCapacity) {
		this.dryerCapacity = dryerCapacity;
	}

	@DynamoDBAttribute
	public int getSpinNoiseLevel() {
		return spinNoiseLevel;
	}

	public void setSpinNoiseLevel(int spinNoiseLevel) {
		this.spinNoiseLevel = spinNoiseLevel;
	}

	@DynamoDBAttribute
	public int getSpinRate() {
		return spinRate;
	}

	public void setSpinRate(int spinRate) {
		this.spinRate = spinRate;
	}

	@DynamoDBAttribute
	public boolean getDelayStart() {
		return delayStart;
	}

	public void setDelayStart(boolean delayStart) {
		this.delayStart = delayStart;
	}

	@DynamoDBAttribute
	public boolean getInbuiltHome() {
		return inbuiltHome;
	}

	public void setInbuiltHome(boolean inbuiltHome) {
		this.inbuiltHome = inbuiltHome;
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
