package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.CoordinateModel;
import model.EstablishmentModel;

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
										+ "FROM Coordinates "
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
}
