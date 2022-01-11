package view;



import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.LoginAction;

public class LoginView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public static JTextField emailField;
	public static JPasswordField passwordField;
	
	public LoginView() {
		lauchLogin();
	}
	
	public void lauchLogin() {
		
		JFrame frame = new JFrame("login");

		// TEST
		MenuOptionView mOption = new MenuOptionView(frame);
		
		
		JLabel label = new JLabel("LOGIN");  
		
		((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JLabel emailText = new JLabel("Email");  
		JLabel passwordText = new JLabel("Mot de Passe"); 
		JButton button = new JButton(); 
		
		
		emailField = new JTextField();
		passwordField = new JPasswordField();
       
		
		label.setBounds(150, 10, 50,10);
		
		emailText.setBounds(10, 30, 150, 20);
		emailField.setBounds(10, 50, 200, 50);
		
		passwordText.setBounds(10,120, 150, 50);
		passwordField.setBounds(10, 170, 200, 50);
		
		button.setBounds(125, 250,100, 50);
		
		
		button.setText("Login");  
		
		frame.add(mOption, java.awt.BorderLayout.EAST);
		frame.add(label);
		frame.add(emailText);
		frame.add(emailField);
		frame.add(button);
		frame.add(passwordField);
		frame.add(passwordText);
		//frame.add(panel);
		
		frame.setLayout(null);
		frame.setSize(350,350);
		frame.setVisible(true);
	
		
		//button.addActionListener(lc.setUsername(loginField.getText()), lc.setPassword(new String(passwordField.toString()))); 
		button.addActionListener(new  LoginAction(frame));
		
		
		
		 
	}

	
	
}
