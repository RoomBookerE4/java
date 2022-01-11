package view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.UserModel;

public class MenuView extends JFrame{
	

	// ICI ON AURA LA CARTE AVEC LES SALLES
	
	private static final long serialVersionUID = 1L;

	public UserModel user;
	
	public MenuView(UserModel user){
		this.user = user;
		lauchMenu();
	}
	
	public void lauchMenu(){

		JFrame frame = new JFrame("Main menu");
		MenuOptionView mOption = new MenuOptionView(frame);
		
		
		((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//mOption.setBackground(Color.BLACK);
		JLabel label = new JLabel("CARTE DES SALLES");  
		label.setBounds(200, 600, 50, 50);
	
		frame.add(label);
		frame.add(mOption, java.awt.BorderLayout.EAST);
		
		frame.setLocationRelativeTo(null);
		frame.setSize(700,700);
		frame.setVisible(true);
	
	}

}