package src.model;

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
}
