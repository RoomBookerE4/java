package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.DaoFactory;
import model.LoginModel;
import model.UserModel;
import view.LoginView;
import view.MenuView;

public class LoginAction extends javax.swing.AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "Login";
	public static boolean looged = false;
	
	public Frame frame;
	public UserModel userModel;
	
	
	public LoginAction(Frame frame) {
		 super(NOM_ACTION);
		 this.frame = frame;
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		String email = LoginView.emailField.getText();
		String password =new String(LoginView.passwordField.getPassword());
		
		 
		 LoginModel loginModel = new LoginModel(email, password);
		 
		 DaoFactory daoFactory = DaoFactory.getInstance();
		 
		  userModel = daoFactory.getLoginDao().verify(loginModel);
		
		 if(userModel !=null) {
			 this.looged = true;
			 frame.dispose();
			 /*int reponse = JOptionPane.showConfirmDialog(new JPanel(), "hello"  + email,
			         NOM_ACTION, JOptionPane.YES_NO_OPTION);*/
			 new MenuView(userModel);
			 
		 }else {
			 JOptionPane.showConfirmDialog(new JPanel(), "User not found",
			         NOM_ACTION, JOptionPane.YES_NO_OPTION);
		 }
	}


}
