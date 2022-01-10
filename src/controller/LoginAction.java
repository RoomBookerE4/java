package controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.DaoFactory;
import model.LoginModel;
import model.UserModel;
import view.LoginView;

public class LoginAction extends javax.swing.AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NOM_ACTION = "Login";
	
     String  email= "test";
	 String password = "aa";
	
	
	public LoginAction() {
		 super(NOM_ACTION);
	}
	
	public LoginAction(String email, String password) {
		 super(NOM_ACTION);
		 this.password = password;
		 this.email = email;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		this.email = LoginView.emailField.getText();
		this.password =new String(LoginView.passwordField.getPassword());
		
		
		 
		 LoginModel loginModel = new LoginModel(email, password);
		 
		 DaoFactory daoFactory = DaoFactory.getInstance();
		 
		 UserModel userModel = daoFactory.getLoginDao().verify(loginModel);
		
		 if(userModel !=null) {
			 int reponse = JOptionPane.showConfirmDialog(new JPanel(), "hello"  + email,
			         NOM_ACTION, JOptionPane.YES_NO_OPTION);
		 }else {
			 int reponse = JOptionPane.showConfirmDialog(new JPanel(), "user not found",
			         NOM_ACTION, JOptionPane.YES_NO_OPTION);
		 }
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
