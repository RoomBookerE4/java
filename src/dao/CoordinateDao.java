package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.CoordinateModel;

public class CoordinateDao {
	
	private DaoFactory daoFactory;

	public CoordinateDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
	
    public List<CoordinateModel> searchByRoomId(int roomId) {
        List<CoordinateModel> coordinates = new ArrayList<CoordinateModel>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT x, y "
										+ "FROM Coordinate "
										+ "WHERE idRoom = " + roomId + ";");
            
            while (result.next()) {
            	int x = result.getInt("x");
                int y = result.getInt("y");
                
                CoordinateModel coordinate = new CoordinateModel(x, y);

                coordinates.add(coordinate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coordinates;
    }
    
    public void addCoordinates(List<CoordinateModel> coordinateModels) {
    	 Connection connection = null;
         PreparedStatement preparedStatement = null;
    	
         try {
        		 String SQL = "INSERT INTO Coordinate(x,y, idRoom, order) "
                         + "VALUES(?,?, ?,?)";
        		 connection = daoFactory.getConnection();
                  preparedStatement = connection.prepareStatement(SQL) ;
             int count = 0;

             for (CoordinateModel cm : coordinateModels) {
            	 preparedStatement.setInt(1, cm.x);
            	 preparedStatement.setInt(2, cm.y);

            	 preparedStatement.addBatch();
                 count++;
                 // execute every 100 rows or less
                 if (count % 50 == 0 || count == coordinateModels.size()) {
                	 preparedStatement.executeBatch();
                 }
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
        
    }
}
