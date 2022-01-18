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
    public void add(RoomModel room) {
    	//TODO
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
            result = statement.executeQuery("SELECT Room.id AS id, Room.name AS roomName, idNumber, Room.timeOpen AS roomTimeOpen, Room.timeClose AS roomTimeClose, isBookable, maxTime, "
											+ "floor, Establishment.timeOpen AS establishmentTimeOpen, Establishment.timeClose AS establishmentTimeClose "
            								+ "FROM Room, Establishment "
            								+ "WHERE Room.idEstablishment = Establishment.id;");

            while (result.next()) {
            	int id = result.getInt("id");
                String name = result.getString("name");
                String idNumber = result.getString("idNumber");
                Time openingTime = result.getTime("roomTimeOpen") != null ? result.getTime("roomTimeOpen") : result.getTime("establishmentTimeOpen");
                Time closingTime = result.getTime("roomTimeClose") != null ? result.getTime("roomTimeClose") : result.getTime("establishmentTimeClose");
                boolean isBookable = result.getBoolean("isBookable");
                Time maxTime = result.getTime("maxTime");
                int floor = result.getInt("floor");

                CoordinateDao coordinateDao = daoFactory.getCoordinateDao();
                List<CoordinateModel> coordinates = coordinateDao.searchByRoomId(id);
                
                RoomModel room = maxTime != null ? 
                			new RoomModel(name != null ? name : "(Unknown Name)", idNumber, floor, openingTime, closingTime, maxTime, isBookable, coordinates):
                			new RoomModel(name != null ? name : "(Unknown Name)", idNumber, floor, openingTime, closingTime, isBookable, coordinates);
                
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
        List<RoomModel> rooms = new ArrayList<RoomModel>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT Room.id AS id, idNumber, Room.timeOpen AS roomTimeOpen, Room.timeClose AS roomTimeClose, isBookable, maxTime, "
											+ "floor, Establishment.timeOpen AS establishmentTimeOpen, Establishment.timeClose AS establishmentTimeClose "
            								+ "FROM Room, Establishment "
            								+ "WHERE Room.name = '" + name + "' "
            								+ "AND Room.idEstablishment = Establishment.id;");

            while (result.next()) {
            	int id = result.getInt("id");
            	String idNumber = result.getString("idNumber");
                Time openingTime = result.getTime("roomTimeOpen") != null ? result.getTime("roomTimeOpen") : result.getTime("establishmentTimeOpen");
                Time closingTime = result.getTime("roomTimeClose") != null ? result.getTime("roomTimeClose") : result.getTime("establishmentTimeClose");
                boolean isBookable = result.getBoolean("isBookable");
                Time maxTime = result.getTime("maxTime");
                int floor = result.getInt("floor");

                CoordinateDao coordinateDao = daoFactory.getCoordinateDao();
                List<CoordinateModel> coordinates = coordinateDao.searchByRoomId(id);
                
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
     * @param number the number of the room searched in the bdd
     * 
     * @return a list of RoomModel containing all the room in the bdd matching the number
     */
    public List<RoomModel> searchByNumber(String number) {
        List<RoomModel> rooms = new ArrayList<RoomModel>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT Room.id AS id, Room.name AS roomName, idNumber, Room.timeOpen AS roomTimeOpen, Room.timeClose AS roomTimeClose, isBookable, maxTime, "
											+ "floor, Establishment.timeOpen AS establishmentTimeOpen, Establishment.timeClose AS establishmentTimeClose "
            								+ "FROM Room, Establishment "
            								+ "WHERE Room.idNumber = '" + number + "' "
            								+ "AND Room.idEstablishment = Establishment.id;");

            while (result.next()) {
            	int id = result.getInt("id");
            	String name = result.getString("roomName");
                Time openingTime = result.getTime("roomTimeOpen") != null ? result.getTime("roomTimeOpen") : result.getTime("establishmentTimeOpen");
                Time closingTime = result.getTime("roomTimeClose") != null ? result.getTime("roomTimeClose") : result.getTime("establishmentTimeClose");
                boolean isBookable = result.getBoolean("isBookable");
                Time maxTime = result.getTime("maxTime");
                int floor = result.getInt("floor");

                CoordinateDao coordinateDao = daoFactory.getCoordinateDao();
                List<CoordinateModel> coordinates = coordinateDao.searchByRoomId(id);
                
                RoomModel room = maxTime != null ? 
                			new RoomModel(name, number, floor, openingTime, closingTime, maxTime, isBookable, coordinates):
                			new RoomModel(name, number, floor, openingTime, closingTime, isBookable, coordinates);
                
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
    
    /**
     * @param establishment the establishment for which one the list of room is expected
     * 
     * @return a list of RoomModel containing all the room of the establishment in the bdd
     */
    public List<RoomModel> searchByEstablishment(EstablishmentModel establishment) {
        List<RoomModel> rooms = new ArrayList<RoomModel>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT Room.id AS id, Room.name AS roomName, idNumber, Room.timeOpen AS roomTimeOpen, Room.timeClose AS roomTimeClose, isBookable, maxTime, "
											+ "floor, Establishment.timeOpen AS establishmentTimeOpen, Establishment.timeClose AS establishmentTimeClose "
            								+ "FROM Room, Establishment "
            								+ "WHERE Room.idEstablishment = Establishment.id "
            								+ "AND Establishment.name = '" + establishment.getName() + "';");

            while (result.next()) {
            	int id = result.getInt("id");
            	String name = result.getString("name");
            	String idNumber = result.getString("idNumber");
                Time openingTime = result.getTime("roomTimeOpen") != null ? result.getTime("roomTimeOpen") : result.getTime("establishmentTimeOpen");
                Time closingTime = result.getTime("roomTimeClose") != null ? result.getTime("roomTimeClose") : result.getTime("establishmentTimeClose");
                boolean isBookable = result.getBoolean("isBookable");
                Time maxTime = result.getTime("maxTime");
                int floor = result.getInt("floor");

                CoordinateDao coordinateDao = daoFactory.getCoordinateDao();
                List<CoordinateModel> coordinates = coordinateDao.searchByRoomId(id);
                
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
     * @param establishment the establishment for which one the list of room is expected 
     * 
     * @param floor the floor for which one the list of room is expected
     * 
     * @return a list of RoomModel containing all the room of the floor and establishment in the bdd
     */
    public List<RoomModel> searchByFloor(EstablishmentModel establishment, int floor) {
        List<RoomModel> rooms = new ArrayList<RoomModel>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT Room.id AS id, Room.name AS roomName, idNumber, Room.timeOpen AS roomTimeOpen, Room.timeClose AS roomTimeClose, isBookable, maxTime, "
											+ "Establishment.timeOpen AS establishmentTimeOpen, Establishment.timeClose AS establishmentTimeClose "
            								+ "FROM Room, Establishment "
            								+ "WHERE Room.idEstablishment = Establishment.id "
            								+ "AND Establishment.name = '" + establishment.getName() + "' "
            								+ "AND Room.floor = " + floor + ";");

            while (result.next()) {
            	int id = result.getInt("id");
            	String name = result.getString("name");
            	String idNumber = result.getString("idNumber");
                Time openingTime = result.getTime("roomTimeOpen") != null ? result.getTime("roomTimeOpen") : result.getTime("establishmentTimeOpen");
                Time closingTime = result.getTime("roomTimeClose") != null ? result.getTime("roomTimeClose") : result.getTime("establishmentTimeClose");
                boolean isBookable = result.getBoolean("isBookable");
                Time maxTime = result.getTime("maxTime");

                CoordinateDao coordinateDao = daoFactory.getCoordinateDao();
                List<CoordinateModel> coordinates = coordinateDao.searchByRoomId(id);
                
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
     * @param id the id for which one we want the room
     * 
     * @return the room that correspond to the unique id
     */
    public RoomModel searchById(int id) {
    	RoomModel room = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT Room.name AS roomName, idNumber, Room.timeOpen AS roomTimeOpen, Room.timeClose AS roomTimeClose, isBookable, maxTime, "
											+ "floor, Establishment.timeOpen AS establishmentTimeOpen, Establishment.timeClose AS establishmentTimeClose "
            								+ "FROM Room, Establishment "
            								+ "WHERE Room.idEstablishment = Establishment.id "
            								+ "AND Room.id = " + id + ";");

            if(result.next()) {
            	String name = result.getString("name");
            	String idNumber = result.getString("idNumber");
                Time openingTime = result.getTime("roomTimeOpen") != null ? result.getTime("roomTimeOpen") : result.getTime("establishmentTimeOpen");
                Time closingTime = result.getTime("roomTimeClose") != null ? result.getTime("roomTimeClose") : result.getTime("establishmentTimeClose");
                boolean isBookable = result.getBoolean("isBookable");
                Time maxTime = result.getTime("maxTime");
                int floor = result.getInt("floor");

                CoordinateDao coordinateDao = daoFactory.getCoordinateDao();
                List<CoordinateModel> coordinates = coordinateDao.searchByRoomId(id);
                
                room = maxTime != null ? 
            			new RoomModel(name, idNumber, floor, openingTime, closingTime, maxTime, isBookable, coordinates):
            			new RoomModel(name, idNumber, floor, openingTime, closingTime, isBookable, coordinates);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }
    
    public static void main(String[] args) {
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	RoomDao roomDao = daoFactory.getRoomDao();
    	
    	EstablishmentModel establishment = new EstablishmentModel("ESEO Angers", "07:00:00", "18:00:00");
    	
    	List<RoomModel> rooms = roomDao.searchByFloor(establishment, 1);
    	for(RoomModel room : rooms) {
    		System.out.println(room.toString());
    	}
    }
}