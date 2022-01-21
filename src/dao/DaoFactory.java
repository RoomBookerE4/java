package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    private String url;
    private String username;
    private String password;

    DaoFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    

    public static DaoFactory getInstance() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }
        
        DaoFactory instance = new DaoFactory(
                "jdbc:mariadb://mysql-calendrier2020.alwaysdata.net:3306/calendrier2020_roombooker", "204456_roombook", "roomBooker1234$");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public EstablishmentDao getEstablishmentDao() {
        return new EstablishmentDao(this);
    }
    
    public RoomDao getRoomDao() {
        return new RoomDao(this);
    }
    
    public CoordinateDao getCoordinateDao() {
        return new CoordinateDao(this);
    }

    public ReservationDao getReservationDao() {
        return new ReservationDao(this);
    }
    
    public LoginDao getLoginDao() {
        return new LoginDao(this);
    }
    
    public UserDao getUserDao() {
    	return new UserDao(this);
    }
}