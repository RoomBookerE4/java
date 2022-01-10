package view;



import java.awt.Frame;
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
	
	public static JTextField loginField;
	public static JPasswordField passwordField;
	
	public static void main(String[] args) {
		Frame frame = new JFrame("login");

		
		JPanel panel = new JPanel();
		
		JLabel label = new JLabel("LOGIN");  
		
		
		
		JLabel loginText = new JLabel("Nom d'utilisateur");  
		JLabel passwordText = new JLabel("Mot de Passe"); 
		loginField = new JTextField();
		passwordField = new JPasswordField();
       
		JButton button = new JButton();  
		
		
		label.setBounds(150, 10, 50,10);
		
		loginText.setBounds(10, 30, 150, 20);
		loginField.setBounds(10, 50, 200, 50);
		
		passwordText.setBounds(10,120, 150, 50);
		passwordField.setBounds(10, 170, 200, 50);
		
		button.setBounds(125, 250,100, 50);
		
		
		button.setText("Login");  
		
		frame.add(label);
		frame.add(loginText);
		frame.add(loginField);
		frame.add(button);
		frame.add(passwordField);
		frame.add(passwordText);
		//frame.add(panel);
		
		frame.setLayout(null);
		frame.setSize(350,350);
		frame.setVisible(true);
	
		//button.addActionListener(lc.setUsername(loginField.getText()), lc.setPassword(new String(passwordField.toString()))); 
		button.addActionListener(new  LoginAction());
		
		/* button.addActionListener(new ActionListener() {  
            

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String data =  loginField.getText();  
                
                label.setText(data);          
			}  
          });   */
		
		 
	}

	
	
}
