package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import model.EstablishmentModel;
import model.LoginModel;
import model.RoomModel;
import model.UserModel;

public class LoginDao {

	private DaoFactory daoFactory;

	public LoginDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
	
	/**
     * @param loginModel RoomModel object to add to the bdd
     */
    public  UserModel verify(LoginModel loginModel) {
    	
    	Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        try {
        	 
        	connection = daoFactory.getConnection();
        	preparedStatement = connection.prepareStatement("SELECT name,surname, role, email, establishment_id "
        			+ "FROM User "
        			+ "WHERE email = ? AND password = ? ;");
        	preparedStatement.setString(1, loginModel.getEmail());  
        	preparedStatement.setString(2, loginModel.getPassword());
        	
        	result =  preparedStatement.executeQuery();
            // TODO deal with the idEstablishment

           if(result.next()) {
        	   String name = result.getString("name");
               String surname = result.getString("surname");
               String role = result.getString("role");
               String email = result.getString("email");
               int establishmentId = result.getInt("establishment_id");
               
               UserModel userModel = new UserModel(name, surname, role, email, establishmentId);
               
        	   return userModel;
           }else {
        	   return null;
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
        
    }
}
