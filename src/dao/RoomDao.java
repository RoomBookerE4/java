package dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import model.CoordinateModel;
import model.DateTime;
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
            preparedStatement = connection.prepareStatement("INSERT INTO Room(name, idNumber, timeOpen, timeClose, isBookable, maxTime, idEstablishment, floor) "
            												+ "VALUES(?, ?, ?, ?, ?, ?, (SELECT id FROM Establishment WHERE name = '?', ?);");
            
            preparedStatement.setString(1, room.getName());
            preparedStatement.setString(2, room.getNumber());
            preparedStatement.setString(3, room.getOpeningTime().toString());
            preparedStatement.setString(4, room.getClosingTime().toString());
            preparedStatement.setString(5, String.valueOf(room.isBookable));
            preparedStatement.setString(6, room.getMaxBookingTime().toString());
            preparedStatement.setString(7, establishment.getName().toString());
            
            this.daoFactory.getCoordinateDao().addCoordinates(room.getCoordinates(), room.getNumber());

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
            result = statement.executeQuery("SELECT Room.name AS roomName, idNumber, Room.timeOpen AS roomTimeOpen, Room.timeClose AS roomTimeClose, isBookable, maxTime, floor, "
											+ "Establishment.timeOpen AS establishmentTimeOpen, Establishment.timeClose AS establishmentTimeClose, GROUP_CONCAT(JSON_OBJECT('x',Coordinate.x,'y',Coordinate.y,'line',Coordinate.line)) AS coordinates\n"
											+ "FROM Room\n"
											+ "INNER JOIN Coordinate\n"
											+ "ON Room.id = Coordinate.idRoom\n"
											+ "INNER JOIN Establishment\n"
											+ "ON Establishment.id = Room.idEstablishment GROUP BY 1;");

            if(result.first() && result.getString("idNumber") == null)
            	return rooms;
            result.beforeFirst();
            
            while (result.next()) {
                String name = result.getString("roomName");
                String idNumber = result.getString("idNumber");
                Time openingTime = result.getTime("roomTimeOpen") != null ? result.getTime("roomTimeOpen") : result.getTime("establishmentTimeOpen");
                Time closingTime = result.getTime("roomTimeClose") != null ? result.getTime("roomTimeClose") : result.getTime("establishmentTimeClose");
                boolean isBookable = result.getBoolean("isBookable");
                Time maxTime = result.getTime("maxTime");
                int floor = result.getInt("floor");
                JSONArray coordinatesJson = new JSONArray("["+result.getString("coordinates")+"]");
                
                List<CoordinateModel> coordinates = new ArrayList<CoordinateModel>();
                if(coordinatesJson.length() > 1 && coordinatesJson.get(0) != null) {
	                for(int i=0; i<coordinatesJson.length(); i++) {
	                	JSONObject json = coordinatesJson.getJSONObject(i);
	                	coordinates.add(new CoordinateModel(json.getInt("x"), json.getInt("y"), json.getInt("line")));
	                }
	                coordinates.sort(Comparator.comparing(CoordinateModel::getLine));
                }
                
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
            result = statement.executeQuery("SELECT idNumber, Room.timeOpen AS roomTimeOpen, Room.timeClose AS roomTimeClose, isBookable, maxTime, floor, "
											+ "Establishment.timeOpen AS establishmentTimeOpen, Establishment.timeClose AS establishmentTimeClose, GROUP_CONCAT(JSON_OBJECT('x',Coordinate.x,'y',Coordinate.y,'line',Coordinate.line)) AS coordinates\n"
											+ "FROM Room\n"
											+ "INNER JOIN Coordinate\n"
											+ "ON Room.id = Coordinate.idRoom\n"
											+ "INNER JOIN Establishment\n"
											+ "ON Establishment.id = Room.idEstablishment\n"
											+ "WHERE Room.name = '" + name + "' GROUP BY 1;");

            if(result.first() && result.getString("idNumber") == null)
            	return rooms;
            result.beforeFirst();
            
            while (result.next()) {
            	String idNumber = result.getString("idNumber");
                Time openingTime = result.getTime("roomTimeOpen") != null ? result.getTime("roomTimeOpen") : result.getTime("establishmentTimeOpen");
                Time closingTime = result.getTime("roomTimeClose") != null ? result.getTime("roomTimeClose") : result.getTime("establishmentTimeClose");
                boolean isBookable = result.getBoolean("isBookable");
                Time maxTime = result.getTime("maxTime");
                int floor = result.getInt("floor");
                JSONArray coordinatesJson = new JSONArray("["+result.getString("coordinates")+"]");
                
                List<CoordinateModel> coordinates = new ArrayList<CoordinateModel>();
                if(coordinatesJson.length() > 1 && coordinatesJson.get(0) != null) {
	                for(int i=0; i<coordinatesJson.length(); i++) {
	                	JSONObject json = coordinatesJson.getJSONObject(i);
	                	coordinates.add(new CoordinateModel(json.getInt("x"), json.getInt("y"), json.getInt("line")));
	                }
	                coordinates.sort(Comparator.comparing(CoordinateModel::getLine));
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
            result = statement.executeQuery("SELECT Room.name AS roomName, Room.timeOpen AS roomTimeOpen, Room.timeClose AS roomTimeClose, isBookable, maxTime, floor, "
											+ "Establishment.timeOpen AS establishmentTimeOpen, Establishment.timeClose AS establishmentTimeClose, GROUP_CONCAT(JSON_OBJECT('x',Coordinate.x,'y',Coordinate.y,'line',Coordinate.line)) AS coordinates\n"
											+ "FROM Room\n"
											+ "INNER JOIN Coordinate\n"
											+ "ON Room.id = Coordinate.idRoom\n"
											+ "INNER JOIN Establishment\n"
											+ "ON Establishment.id = Room.idEstablishment\n"
											+ "WHERE Room.idNumber = '" + number + "' GROUP BY 1;");

            if(result.first() && result.getString("idNumber") == null)
            	return rooms;
            result.beforeFirst();
            
            while (result.next()) {
            	String name = result.getString("roomName");
                Time openingTime = result.getTime("roomTimeOpen") != null ? result.getTime("roomTimeOpen") : result.getTime("establishmentTimeOpen");
                Time closingTime = result.getTime("roomTimeClose") != null ? result.getTime("roomTimeClose") : result.getTime("establishmentTimeClose");
                boolean isBookable = result.getBoolean("isBookable");
                Time maxTime = result.getTime("maxTime");
                int floor = result.getInt("floor");
                JSONArray coordinatesJson = new JSONArray("["+result.getString("coordinates")+"]");
                
                List<CoordinateModel> coordinates = new ArrayList<CoordinateModel>();
                if(coordinatesJson.length() > 1 && coordinatesJson.get(0) != null) {
	                for(int i=0; i<coordinatesJson.length(); i++) {
	                	JSONObject json = coordinatesJson.getJSONObject(i);
	                	coordinates.add(new CoordinateModel(json.getInt("x"), json.getInt("y"), json.getInt("line")));
	                }
	                coordinates.sort(Comparator.comparing(CoordinateModel::getLine));
                }
                
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
            result = statement.executeQuery("SELECT Room.name AS roomName, idNumber, Room.timeOpen AS roomTimeOpen, Room.timeClose AS roomTimeClose, isBookable, maxTime, floor, "
											+ "Establishment.timeOpen AS establishmentTimeOpen, Establishment.timeClose AS establishmentTimeClose, GROUP_CONCAT(JSON_OBJECT('x',Coordinate.x,'y',Coordinate.y,'line',Coordinate.line)) AS coordinates\n"
											+ "FROM Room\n"
											+ "INNER JOIN Coordinate\n"
											+ "ON Room.id = Coordinate.idRoom\n"
											+ "INNER JOIN Establishment\n"
											+ "ON Establishment.id = Room.idEstablishment\n"
            								+ "WHERE Establishment.name = '" + establishment.getName() + "' GROUP BY 1;");

            if(result.first() && result.getString("idNumber") == null)
            	return rooms;
            result.beforeFirst();
            
            while (result.next()) {
            	String name = result.getString("roomName");
            	String idNumber = result.getString("idNumber");
                Time openingTime = result.getTime("roomTimeOpen") != null ? result.getTime("roomTimeOpen") : result.getTime("establishmentTimeOpen");
                Time closingTime = result.getTime("roomTimeClose") != null ? result.getTime("roomTimeClose") : result.getTime("establishmentTimeClose");
                boolean isBookable = result.getBoolean("isBookable");
                Time maxTime = result.getTime("maxTime");
                int floor = result.getInt("floor");
                JSONArray coordinatesJson = new JSONArray("["+result.getString("coordinates")+"]");
                
                List<CoordinateModel> coordinates = new ArrayList<CoordinateModel>();
                if(coordinatesJson.length() > 1 && coordinatesJson.get(0) != null) {
	                for(int i=0; i<coordinatesJson.length(); i++) {
	                	JSONObject json = coordinatesJson.getJSONObject(i);
	                	coordinates.add(new CoordinateModel(json.getInt("x"), json.getInt("y"), json.getInt("line")));
	                }
	                coordinates.sort(Comparator.comparing(CoordinateModel::getLine));
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
     * @param establishment the establishment for which one the list of room is expected 
     * 
     * @param floor the floor for which one the list of room is expected
     * 
     * @return a list of RoomModel containing all the room of the floor and establishment in the bdd
     */
    public List<RoomModel> searchByFloor(int establishmentId, int floor) {
        List<RoomModel> rooms = new ArrayList<RoomModel>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT Room.name AS roomName, idNumber, Room.timeOpen AS roomTimeOpen, Room.timeClose AS roomTimeClose, isBookable, maxTime, floor, "
											+ "Establishment.timeOpen AS establishmentTimeOpen, Establishment.timeClose AS establishmentTimeClose, GROUP_CONCAT(JSON_OBJECT('x',Coordinate.x,'y',Coordinate.y,'line',Coordinate.line)) AS coordinates\n"
											+ "FROM Room\n"
											+ "INNER JOIN Coordinate\n"
											+ "ON Room.id = Coordinate.idRoom\n"
											+ "INNER JOIN Establishment\n"
											+ "ON Establishment.id = Room.idEstablishment\n"
            								+ "WHERE Establishment.id = '" + establishmentId + "' "
            								+ "AND Room.floor = " + floor + " GROUP BY 1;");

            if(result.first() && result.getString("idNumber") == null)
            	return rooms;
            result.beforeFirst();
            
            while (result.next()) {
            	String name = result.getString("roomName");
            	String idNumber = result.getString("idNumber");
                Time openingTime = result.getTime("roomTimeOpen") != null ? result.getTime("roomTimeOpen") : result.getTime("establishmentTimeOpen");
                Time closingTime = result.getTime("roomTimeClose") != null ? result.getTime("roomTimeClose") : result.getTime("establishmentTimeClose");
                boolean isBookable = result.getBoolean("isBookable");
                Time maxTime = result.getTime("maxTime");
                JSONArray coordinatesJson = new JSONArray("["+result.getString("coordinates")+"]");
                
                List<CoordinateModel> coordinates = new ArrayList<CoordinateModel>();
                if(coordinatesJson.length() > 1 && coordinatesJson.get(0) != null) {
                	for(int i=0; i<coordinatesJson.length(); i++) {
	                	JSONObject json = coordinatesJson.getJSONObject(i);
	                	coordinates.add(new CoordinateModel(json.getInt("x"), json.getInt("y"), json.getInt("line")));
                	}
                	coordinates.sort(Comparator.comparing(CoordinateModel::getLine));
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
            result = statement.executeQuery("SELECT Room.name AS roomName, idNumber, Room.timeOpen AS roomTimeOpen, Room.timeClose AS roomTimeClose, isBookable, maxTime, floor, "
            								+ "Establishment.timeOpen AS establishmentTimeOpen, Establishment.timeClose AS establishmentTimeClose, GROUP_CONCAT(JSON_OBJECT('x',Coordinate.x,'y',Coordinate.y,'line',Coordinate.line)) AS coordinates\n"
            								+ "FROM Room\n"
            								+ "INNER JOIN Coordinate\n"
            								+ "ON Room.id = Coordinate.idRoom\n"
            								+ "INNER JOIN Establishment\n"
            								+ "ON Establishment.id = Room.idEstablishment\n"
            								+ "WHERE Room.id = " + id + " GROUP BY 1;");

            if(result.next() && result.getString("idNumber") != null) {
            	String name = result.getString("roomName");
            	String idNumber = result.getString("idNumber");
                Time openingTime = result.getTime("roomTimeOpen") != null ? result.getTime("roomTimeOpen") : result.getTime("establishmentTimeOpen");
                Time closingTime = result.getTime("roomTimeClose") != null ? result.getTime("roomTimeClose") : result.getTime("establishmentTimeClose");
                boolean isBookable = result.getBoolean("isBookable");
                Time maxTime = result.getTime("maxTime");
                int floor = result.getInt("floor");
                JSONArray coordinatesJson = new JSONArray("["+result.getString("coordinates")+"]");
                
                List<CoordinateModel> coordinates = new ArrayList<CoordinateModel>();
                if(coordinatesJson.length() > 1 && coordinatesJson.get(0) != null) {
                	for(int i=0; i<coordinatesJson.length(); i++) {
	                	JSONObject json = coordinatesJson.getJSONObject(i);
	                	coordinates.add(new CoordinateModel(json.getInt("x"), json.getInt("y"), json.getInt("line")));
                	}
                	coordinates.sort(Comparator.comparing(CoordinateModel::getLine));
                }

                room = maxTime != null ? 
            			new RoomModel(name, idNumber, floor, openingTime, closingTime, maxTime, isBookable, coordinates):
            			new RoomModel(name, idNumber, floor, openingTime, closingTime, isBookable, coordinates);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }
    
    public  List<RoomModel> searchByEstablishmentId(int idEstablishment) {
    	 Connection connection = null;
         Statement statement = null;
         ResultSet result = null;
         List<RoomModel> rooms = new ArrayList<RoomModel>();
         try {
             connection = daoFactory.getConnection();
             statement = connection.createStatement();
             result = statement.executeQuery("SELECT Room.name AS roomName, idNumber, Room.timeOpen AS roomTimeOpen, Room.timeClose AS roomTimeClose, isBookable, maxTime, floor "
            		 						+ "FROM Room "
             								+ "WHERE idEstablishment = " + idEstablishment + ";");

             while (result.next()) {
             	String name = result.getString("roomName");
            	String idNumber = result.getString("idNumber");
                Time openingTime = result.getTime("roomTimeOpen");
                Time closingTime = result.getTime("roomTimeClose") ;
                boolean isBookable = result.getBoolean("isBookable");
                Time maxTime = result.getTime("maxTime");
                int floor = result.getInt("floor");
                
                RoomModel room = new RoomModel(name, idNumber, floor, openingTime, closingTime, maxTime, isBookable);
                rooms.add(room);
             }
     
         } catch (SQLException e) {
             e.printStackTrace();
         }
    	return rooms;
    }
    
    
    public HashMap<RoomModel, Boolean> listRoomWithStatus(int establishmentId, int floor, DateTime time) {
        HashMap<RoomModel, Boolean> availability = new HashMap<RoomModel, Boolean>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT\n"
            		+ "    Room.name AS roomName,\n"
            		+ "    idNumber,\n"
            		+ "    Room.timeOpen AS roomTimeOpen,\n"
            		+ "    Room.timeClose AS roomTimeClose,\n"
            		+ "    isBookable,\n"
            		+ "    maxTime,\n"
            		+ "    FLOOR,\n"
            		+ "    Establishment.timeOpen AS establishmentTimeOpen,\n"
            		+ "    Establishment.timeClose AS establishmentTimeClose,\n"
            		+ "    GROUP_CONCAT(\n"
            		+ "        JSON_OBJECT(\n"
            		+ "            'x',\n"
            		+ "            Coordinate.x,\n"
            		+ "            'y',\n"
            		+ "            Coordinate.y,\n"
            		+ "            'line',\n"
            		+ "            Coordinate.line\n"
            		+ "        )\n"
            		+ "    ) AS coordinates,\n"
            		+ "    COUNT(Reservation.id) AS isBooked\n"
            		+ "FROM\n"
            		+ "    Room\n"
            		+ "INNER JOIN Coordinate ON Room.id = Coordinate.idRoom\n"
            		+ "INNER JOIN Establishment ON Establishment.id = Room.idEstablishment\n"
            		+ "LEFT OUTER JOIN Reservation ON Reservation.idRoom = Room.id AND timeEnd > '" + time.toString() + "' AND timeStart < '" + time.toString() + "'\n"
            		+ "WHERE\n"
            		+ "    Establishment.id = " + establishmentId + " AND Room.floor = " + floor + "\n"
            		+ "GROUP BY 1;");
            
            while (result.next()) {
            	String name = result.getString("roomName");
            	String idNumber = result.getString("idNumber");
                Time openingTime = result.getTime("roomTimeOpen") != null ? result.getTime("roomTimeOpen") : result.getTime("establishmentTimeOpen");
                Time closingTime = result.getTime("roomTimeClose") != null ? result.getTime("roomTimeClose") : result.getTime("establishmentTimeClose");
                boolean isBookable = result.getBoolean("isBookable");
                Time maxTime = result.getTime("maxTime");
                JSONArray coordinatesJson = new JSONArray("["+result.getString("coordinates")+"]");
                boolean isBooked = result.getInt("isBooked") == 0 ? false : true;
                
                List<CoordinateModel> coordinates = new ArrayList<CoordinateModel>();
                if(coordinatesJson.length() > 1 && coordinatesJson.get(0) != null) {
                	for(int i=0; i<coordinatesJson.length(); i++) {
	                	JSONObject json = coordinatesJson.getJSONObject(i);
	                	coordinates.add(new CoordinateModel(json.getInt("x"), json.getInt("y"), json.getInt("line")));
                	}
                	coordinates.sort(Comparator.comparing(CoordinateModel::getLine));
                }
                
                RoomModel room = maxTime != null ? 
                			new RoomModel(name, idNumber, floor, openingTime, closingTime, maxTime, isBookable, coordinates):
                			new RoomModel(name, idNumber, floor, openingTime, closingTime, isBookable, coordinates);
                
                availability.put(room, isBooked);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availability;	
    }
    
    
    public boolean addRoom(RoomModel room, int establishment_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Room(name, idNumber, timeOpen, timeClose, isBookable, maxTime, idEstablishment, floor) "
            												+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
            
            preparedStatement.setString(1, room.getName());
            preparedStatement.setString(2, room.getNumber());
            preparedStatement.setString(3, room.getOpeningTime().toString());
            preparedStatement.setString(4, room.getClosingTime().toString());
            preparedStatement.setBoolean(5, room.isBookable);
            preparedStatement.setString(6, room.getMaxBookingTime().toString());
            preparedStatement.setInt(7, establishment_id);
            preparedStatement.setInt(8, room.getFloor());
            
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    
    public boolean editRoom(RoomModel room, String roomName) {
    	 Connection connection = null;
         PreparedStatement preparedStatement = null;
         boolean done= true;

         try {
             connection = daoFactory.getConnection();
             preparedStatement = connection.prepareStatement("UPDATE Room"
             		+ " SET name = ? , idNumber = ?, timeOpen = ? ,  timeClose = ? , isBookable= ?, maxTime = ?, floor =?"
             		+ " WHERE name =  ?;");
             preparedStatement.setString(1, room.getName());
             preparedStatement.setString(2, room.getNumber());
             preparedStatement.setString(3, room.getOpeningTime().toString());
             preparedStatement.setString(4, room.getClosingTime().toString());
             preparedStatement.setBoolean(5, room.isBookable);
             preparedStatement.setString(6, room.getMaxBookingTime().toString());
             preparedStatement.setInt(7, room.getFloor());
             preparedStatement.setString(8, roomName);
             preparedStatement.executeUpdate();
             return done;
         } catch (SQLException e) {
        	 done = false;
             e.printStackTrace();
             return done;
         }
    }
	
    
    public static void main(String[] args) {
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	RoomDao roomDao = daoFactory.getRoomDao();
    	  
    	RoomModel room = roomDao.searchById(1);
    	//for(RoomModel room : rooms) {
    		System.out.println(room.toString());
    	//}
    }
}