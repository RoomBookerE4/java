package controller.action;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.DaoFactory;
import model.LoginModel;
import model.UserModel;
import view.menu.MenuView;

public class LoginAction extends javax.swing.AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Frame frame;
	public UserModel userModel;
	
	private JTextField login;
	private  JPasswordField passwordField;
	
	public LoginAction(Frame frame) {
		 this.frame = frame;
	}
	


	public LoginAction(Frame frame, JTextField login, JPasswordField passwordField) {
		super();
		this.frame = frame;
		this.login = login;
		this.passwordField = passwordField;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		String email = login.getText();
		String password =new String(passwordField.getPassword());
		
		 
		 LoginModel loginModel = new LoginModel(email, password);
		 
		 DaoFactory daoFactory = DaoFactory.getInstance();
		 
		 userModel = daoFactory.getLoginDao().verify(loginModel);
		
		 if(userModel !=null) {
			 frame.dispose();
			 new MenuView(userModel);
			 
		 }else {
			 JOptionPane.showConfirmDialog(new JPanel(), "User not found",
			         "", JOptionPane.CANCEL_OPTION);
		 }
	}


}
