package model;

import java.sql.Date;
import java.sql.Time;

public class DateTime {
	
	public Date date;
	public Time time;
	
	public DateTime(Date date, Time time) {
		this.date = date;
		this.time = time;
	}
	
	public DateTime(String dateTime) {
		this.date = Date.valueOf(dateTime.split(" ")[0]);
		this.time = Time.valueOf(dateTime.split(" ")[1]);
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public Time getTime() {
		return this.time;
	}
	
	public String toString() {
		return getDate().toString() + " " + getTime().toString();
	}
	
	public static DateTime valueOf(String dateTimeString) {
		return new DateTime(dateTimeString);
	}
}
