package model;

import java.sql.Date;

public class ReservationModel {
	
	public RoomModel room;
	
	public Date startTime, endTime;
	
	public ReservationModel(RoomModel room, Date startTime, Date endTime) {
		this.room = room;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public RoomModel getRoom() {
		return this.room;
	}
	
	public Date getStartTime() {
		return this.startTime;
	}
	
	public Date getEndTime() {
		return this.endTime;
	}
}
