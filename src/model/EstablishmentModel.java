package model;

import java.sql.Time;

public class EstablishmentModel {
	
	public String name, address;
	
	public Time openingTime, closingTime;

	public EstablishmentModel(String name, String address, 
							Time openingTime, Time closingTime) {
		this.name = name;
		this.address = address;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
	}
	
	public EstablishmentModel(String name, Time openingTime, Time closingTime) {
		this(name, null, openingTime, closingTime);
	}
	
	public EstablishmentModel(String name, String address, 
							String openingTime, String closingTime) {
		this(name, address, Time.valueOf(openingTime), Time.valueOf(closingTime));
	}

	public EstablishmentModel(String name, String openingTime, String closingTime) {
		this(name, null, Time.valueOf(openingTime), Time.valueOf(closingTime));
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public Time getOpeningTime() {
		return this.openingTime;
	}
	
	public Time getClosingTime() {
		return this.closingTime;
	}
	
	public String toString() {
		return getName() + (getAddress() != null ? " " + getAddress():"") 
				+ " from " + getOpeningTime() + " to " + getClosingTime();
	}
}
