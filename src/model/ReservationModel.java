package model;

public class ReservationModel {
	
	public RoomModel room;
	
	public DateTime startTime, endTime;
		
	public ReservationModel(RoomModel room, DateTime startTime, DateTime endTime) {
		this.room = room;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public ReservationModel(RoomModel room, String startTime, String endTime) {
		this.room = room;
		this.startTime = DateTime.valueOf(startTime);
		this.endTime = DateTime.valueOf(endTime);
	}
	
	public RoomModel getRoom() {
		return this.room;
	}
	
	public DateTime getStartTime() {
		return this.startTime;
	}
	
	public DateTime getEndTime() {
		return this.endTime;
	}
	
	public String toString() {
		return getRoom().toString() + " booked from " + getStartTime() + " to " + getEndTime();
	}
}
