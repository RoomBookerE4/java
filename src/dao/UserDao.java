package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.EstablishmentModel;
import model.UserModel;

public class UserDao {

	private DaoFactory daoFactory;

	public UserDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
	
	
	
	public boolean updatenNewEstablishment(UserModel user, String name, String address) {
		 Connection connection = null;
         PreparedStatement preparedStatement = null;

         try {
             connection = daoFactory.getConnection();
             preparedStatement = connection.prepareStatement("UPDATE User "
             		+ " SET establishment_id =  "
             		+ "(SELECT id FROM Establishment "
             		+ "WHERE name = ? AND address = ? ) "
             		+ "WHERE email = ?" + ";");
             preparedStatement.setString(1, name);
             preparedStatement.setString(2, address);
             preparedStatement.setString(3, user.email);

             preparedStatement.executeUpdate();
             return true;
         } catch (SQLException e) {
             e.printStackTrace();
             return false;
         }
	}
}
