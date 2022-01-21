package controller;

import javax.swing.JTextField;

import dao.DaoFactory;
import model.UserModel;

public class UserController {
	
	public  String name;

	public  String address;
	
	public UserModel user;
	
	DaoFactory daoFactory = DaoFactory.getInstance();

	
	
	public UserController(UserModel user, String name, String address) {
		super();
		this.name = name;
		this.address = address;
		this.user = user;
	}



	public boolean changeIdEstablishment() {
		
		
		return daoFactory.getUserDao().updatenNewEstablishment(user, name, address);
	}
}
