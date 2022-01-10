package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.CoordinateModel;
import model.EstablishmentModel;
import model.RoomModel;

public class RoomDao {
	
	private DaoFactory daoFactory;

	public RoomDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * @param room RoomModel object to add to the bdd
     */
    public void add(RoomModel room, EstablishmentModel establishment) {
    	
    	Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
        	connection = daoFactory.getConnection();
        	preparedStatement = connection.prepareStatement("INSERT INTO Room(name, idNumber, floor, timeOpen, timeClose, maxTime, isBookable) VALUES(?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, room.getName());
            preparedStatement.setString(2, room.getNumber());
            preparedStatement.setString(3, String.valueOf(room.getFloor()));
            preparedStatement.setString(4, room.getOpeningTime().toString());
            preparedStatement.setString(5, room.getClosingTime().toString());
            preparedStatement.setString(6, room.getMaxBookingTime().toString());
            preparedStatement.setString(7, String.valueOf(room.isBookable));
            // TODO deal with the idEstablishment

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * @return a list of RoomModel containing all the room in the bdd
     */
    public List<RoomModel> list() {
        List<RoomModel> rooms = new ArrayList<RoomModel>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT id, idEstablishment, name, idNumber, timeOpen, timeClose, isBookable, maxTime, floor FROM Room;");

            while (result.next()) {
            	int id = result.getInt("id");
            	int establishmentId = result.getInt("idEstablishment");
                String name = result.getString("name");
                String idNumber = result.getString("idNumber");
                Time openingTime = result.getTime("timeOpen");
                Time closingTime = result.getTime("timeClose");
                boolean isBookable = result.getBoolean("isBookable");
                Time maxTime = result.getTime("maxTime");
                int floor = result.getInt("floor");

                CoordinateDao coordinateDao = daoFactory.getCoordinateDao();
                List<CoordinateModel> coordinates = coordinateDao.searchByRoomId(id);
                
                if(openingTime == null || closingTime == null) {
                	EstablishmentDao establishmentDao = daoFactory.getEstablishmentDao();
                	EstablishmentModel establishment = establishmentDao.searchById(establishmentId);
                	
                	if(openingTime == null) openingTime = establishment.getOpeningTime();
                	if(closingTime == null) closingTime = establishment.getClosingTime();
                }
                
                RoomModel room = maxTime != null ? 
                			new RoomModel(name, idNumber, floor, openingTime, closingTime, maxTime, isBookable, coordinates):
                			new RoomModel(name, idNumber, floor, openingTime, closingTime, isBookable, coordinates);
                
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
    
    /**
     * @param name the name of the room searched in the bdd
     * 
     * @return a list of RoomModel containing all the room in the bdd matching the name
     */
    public List<RoomModel> searchByName(String name) {
    	//TODO
    }
    
    /**
     * @param number the number of the room searched in the bdd
     * 
     * @return a list of RoomModel containing all the room in the bdd matching the number
     */
    public List<RoomModel> searchByNumber(String number) {
    	//TODO
    }
    
    /**
     * @param establishment the establishment for which one the list of room is expected
     * 
     * @return a list of RoomModel containing all the room of the establishment in the bdd
     */
    public List<RoomModel> searchByEstablishment(EstablishmentModel establishment) {
    	//TODO
    }
    
    /**
     * @param establishment the establishment for which one the list of room is expected 
     * 
     * @param floor the floor for which one the list of room is expected
     * 
     * @return a list of RoomModel containing all the room of the floor and establishment in the bdd
     */
    public List<RoomModel> searchByFloor(EstablishmentModel establishment, int floor) {
    	//TODO
    }
}