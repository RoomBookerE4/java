package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.EstablishmentModel;

public class EstablishmentDao {

	private DaoFactory daoFactory;

	public EstablishmentDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * @param establishment EstablishmentModel object to add to the bdd
     */
    public void add(EstablishmentModel establishment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO Establishment(name, address, timeOpen, timeClose) VALUES(?, ?, ?, ?);");
            preparedStatement.setString(1, establishment.getName());
            preparedStatement.setString(2, establishment.getAddress());
            preparedStatement.setString(3, establishment.getOpeningTime().toString());
            preparedStatement.setString(4, establishment.getClosingTime().toString());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @return a list of EstablishmentModel containing all the establishment in the bdd
     */
    public List<EstablishmentModel> list() {
        List<EstablishmentModel> establishments = new ArrayList<EstablishmentModel>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT name, address, timeOpen, timeClose FROM Establishment;");

            while (result.next()) {
                String name = result.getString("name");
                String address = result.getString("address");
                Time openingTime = result.getTime("timeOpen");
                Time closingTime = result.getTime("timeClose");
                
                EstablishmentModel establishment = address != null ? 
                							new EstablishmentModel(name, address, openingTime, closingTime) :
                							new EstablishmentModel(name, openingTime, closingTime);

                establishments.add(establishment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return establishments;
    }
    
    /**
     * @param name the name of the establishment searched in the bdd
     * 
     * @return a list of EstablishmentModel containing all the establishment in the bdd matching the name
     */
    public List<EstablishmentModel> searchByName(String name) {
        List<EstablishmentModel> establishments = new ArrayList<EstablishmentModel>();
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT address, timeOpen, timeClose "
										+ "FROM Establishment "
										+ "WHERE name = " + name + ";");
            
            while (result.next()) {
            	String address = result.getString("address");
                Time openingTime = result.getTime("timeOpen");
                Time closingTime = result.getTime("timeClose");
                
                EstablishmentModel establishment = address != null ? 
        									new EstablishmentModel(name, address, openingTime, closingTime):
        									new EstablishmentModel(name, openingTime, closingTime);

                establishments.add(establishment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return establishments;
    }
    
    public EstablishmentModel searchById(int establishmentId) {
    	EstablishmentModel establishment = null;
    	Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            result = statement.executeQuery("SELECT name, timeOpen, timeClose "
										+ "FROM Establishment "
										+ "WHERE id = " + establishmentId + ";");
            
            while (result.next()) {
                String name = result.getString("name");
                Time openingTime = result.getTime("timeOpen");
                Time closingTime = result.getTime("timeClose");
                
                establishment = new EstablishmentModel(name, openingTime, closingTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return establishment;
    }


    
    public static void main(String[] args) {
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	EstablishmentDao establishmentDao = daoFactory.getEstablishmentDao();
    	
    	List<EstablishmentModel> establishments = establishmentDao.list();
    	for(EstablishmentModel establishment : establishments) {
    		System.out.println(establishment.toString());
    	}
    }
}