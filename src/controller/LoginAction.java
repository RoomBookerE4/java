package src.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LoginAction extends javax.swing.AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NOM_ACTION = "Login";
	
	 String username= "test";
	 String password = "aa";
	
	
	public LoginAction() {
		 super(NOM_ACTION);
	}
	
	public LoginAction(String username, String password) {
		 super(NOM_ACTION);
		 //this.password = password;
		 this.username = username;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.print(username);
		 int reponse = JOptionPane.showConfirmDialog(new JPanel(), this.password ,
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
