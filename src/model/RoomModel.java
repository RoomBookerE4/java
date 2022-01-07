package src.model;

import java.lang.String;
import java.sql.Time;

public class RoomModel {
	
	public static final Time DEFAULT_MAX_BOOKING_TIME = new Time(2*3600);
	
	public String name, number;
	
	public int floor;
	
	public Time openingTime, closingTime, maxBookingTime;
	
	public boolean isBookable;
	
	public CoordinateModel[] coordinates;
	
	public RoomModel(String name, String number, int floor, 
					Time openingTime, Time closingTime, Time maxBookingTime,
					boolean isBookable, CoordinateModel[] coordinates) {
		this.name = name;
		this.number = number;
		this.floor = floor;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.maxBookingTime = maxBookingTime;
		this.isBookable = isBookable;
		this.coordinates = coordinates;
	}
	
	public RoomModel(String name, String number, int floor, 
					Time openingTime, Time closingTime,
					boolean isBookable, CoordinateModel[] coordinates) {
		this(name, number, floor, openingTime, 
			closingTime, DEFAULT_MAX_BOOKING_TIME, 
			isBookable, coordinates);
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getNumber() {
		return this.number;
	}
	
	public int getFloor() {
		return this.floor;
	}
	
	public Time getOpeningTime() {
		return this.openingTime;
	}
	
	public Time getClosingTime() {
		return this.closingTime;
	}
	
	public Time getMaxBookingTime() {
		return this.maxBookingTime;
	}
	
	public CoordinateModel[] getCoordinates() {
		return this.coordinates;
	}
	
}
