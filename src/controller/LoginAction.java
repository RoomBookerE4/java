package controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.view.LoginView;

public class LoginAction extends javax.swing.AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NOM_ACTION = "Login";
	
     String  username= "test";
	 String password = "aa";
	
	
	public LoginAction() {
		 super(NOM_ACTION);
	}
	
	public LoginAction(String username, String password) {
		 super(NOM_ACTION);
		 this.password = password;
		 this.username = username;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.print(username);
		
		this.username = LoginView.loginField.getText();
		this.password =new String(LoginView.passwordField.getPassword());
		
		 int reponse = JOptionPane.showConfirmDialog(new JPanel(), username +"  "+ password ,
		         NOM_ACTION, JOptionPane.YES_NO_OPTION);
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
