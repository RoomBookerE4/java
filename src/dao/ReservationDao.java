package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.CoordinateModel;
import model.EstablishmentModel;
import model.ReservationModel;
import model.RoomModel;

public class ReservationDao {

	private DaoFactory daoFactory;

	public ReservationDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * TODO still in progress -> issue to many connection
     * 
     * @return a list of ReservationModel containing all the reservations in the bdd
     */
    public List<ReservationModel> list() {
        List<ReservationModel> reservations = new ArrayList<ReservationModel>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT timeStart, timeEnd, idRoom "
            								+ "FROM Reservation;");

            while (result.next()) {
            	Date timeStart = result.getDate("timeStart");
                Date timeEnd = result.getDate("timeEnd");
                int idRoom = result.getInt("idRoom");

                RoomDao roomDao = daoFactory.getRoomDao();
                RoomModel room = roomDao.searchById(idRoom);
                
                ReservationModel reservation = new ReservationModel(room, timeStart, timeEnd);
                
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

	
    public static void main(String[] args) {
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	ReservationDao reservationDao = daoFactory.getReservationDao();
    	
    	List<ReservationModel> reservations = reservationDao.list();
    	for(ReservationModel reservation : reservations) {
    		System.out.println(reservation.toString());
    	}
    }
}
