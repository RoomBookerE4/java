package controller;

import java.util.HashMap;
import java.util.List;

import dao.DaoFactory;
import dao.EstablishmentDao;
import dao.RoomDao;
import model.DateTime;
import model.EstablishmentModel;
import model.RoomModel;

public class ReservationController {

	public static HashMap<RoomModel, Boolean> getAvailabilities(int establishmentId, int floor, DateTime time) {
		DaoFactory daoFactory = DaoFactory.getInstance();
		
		List<RoomModel> roomList = daoFactory.getRoomDao().searchByFloor(establishmentId, floor);
		for(RoomModel room : roomList) {
		}
		
		HashMap<RoomModel, Boolean> availabilityMap = new HashMap<RoomModel, Boolean>();
		
		for(RoomModel room : roomList) {
			boolean isBooked = daoFactory.getReservationDao().checkIfBooked(room, time);
			availabilityMap.put(room, isBooked);
		}

		return availabilityMap;
	}

}
