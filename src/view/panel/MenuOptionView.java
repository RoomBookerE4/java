package view.panel;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.action.AdminAction;
import controller.action.LoginAction;
import model.UserModel;
import type.AdminOption;
import type.UserRole;

public class MenuOptionView extends JPanel{

	/**
	 * MENU D'OPTIONS 
	 */
	private static final long serialVersionUID = 1L;
	
	public JFrame frame;
	public UserModel user;
	
	public MenuOptionView(JFrame frame,UserModel user ){
		//super();
		this.frame = frame;
		this.user = user;
		initComponents();
	}

	
	public void initComponents() {
		
		//this.setLayout((LayoutManager) new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("panel");  
		String floors[]={"Floor 0 ","Floor 2","Floor 3","Floor 4"};        
		JComboBox comboBox = new JComboBox(floors);
		
		JButton admButton = new JButton("Adm");
		
		
		if(user.getRole().equals(UserRole.ADM)) {
			this.add(admButton);
		}
		
		
		this.add(new JLabel("Étage"));
		
		//this.add(Box.createRigidArea(new Dimension(5,500)));
		//this.add(Box.createVerticalStrut(1));
		this.add(comboBox);
		
		//this.add(new JLabel("Test"));
		//this.setLayout(new GridLayout(20, 2));
		
		admButton.addActionListener(new  AdminAction(frame, this, user, AdminOption.oAdm));
		
		
	}
	
}
