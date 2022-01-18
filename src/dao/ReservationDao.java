package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.DateTime;
import model.ReservationModel;
import model.RoomModel;

public class ReservationDao {

	private DaoFactory daoFactory;

	public ReservationDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * @param room the room for which one we want the list of reservations for now and the future
     * 
     * @return a list of ReservationModel
     */
    public List<ReservationModel> searchForRoom(RoomModel room) {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date = new Date(System.currentTimeMillis());
    	return searchForRoom(room, DateTime.valueOf(formatter.format(date)));
    }

	
    
    /**
     * @param room the room for which one we want the list of reservations for the time and the future
     * @param time the time after witch one we want the reservations 
     * 
     * @return a list of ReservationModel
     */
    public List<ReservationModel> searchForRoom(RoomModel room, DateTime time) {
        List<ReservationModel> reservations = new ArrayList<ReservationModel>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT timeStart, timeEnd\n"
            								+ "FROM Reservation\n"
            								+ "INNER JOIN Room\n"
            								+ "ON Room.id = Reservation.idRoom\n"
            								+ "WHERE Room.idNumber = '" + room.getNumber() + "'\n"
            								+ "AND timeEnd > '" + time.toString() + "';");

            while (result.next()) {
            	DateTime timeStart = new DateTime(result.getDate("timeStart"), result.getTime("timeStart"));
                DateTime timeEnd = new DateTime(result.getDate("timeEnd"), result.getTime("timeEnd"));
                
                ReservationModel reservation = new ReservationModel(room, timeStart, timeEnd);
                
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
    
    /**
     * @param room the room for which one we want the list of reservations for the time and the future
     * @param time the time after witch one we want the reservations 
     * 
     * @return a list of ReservationModel
     */
    public boolean checkIfBooked(RoomModel room, DateTime time) {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        boolean isBooked = false;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT count(Reservation.id) AS isBooked\n"
            								+ "FROM Reservation\n"
            								+ "INNER JOIN Room\n"
            								+ "ON Room.id = Reservation.idRoom\n"
            								+ "WHERE Room.idNumber = '" + room.getNumber() + "'\n"
            								+ "AND timeEnd > '" + time.toString() + "'\n"
            								+ "AND timeStart < '" + time.toString() + "';");

            while (result.next()) {
            	isBooked = result.getInt("isBooked") == 1 ? true : false;
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isBooked;
    }


	
    public static void main(String[] args) {
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	RoomDao roomDao = daoFactory.getRoomDao();
    	ReservationDao reservationDao = daoFactory.getReservationDao();
    	
    	RoomModel room = roomDao.searchById(2);
    	
    	boolean isBooked = reservationDao.checkIfBooked(room, DateTime.valueOf("2022-02-24 10:45:00"));
    	System.out.println(isBooked);
    }
}
