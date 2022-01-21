package view;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.action.AdminAction;
import model.UserModel;
import type.AdminOption;
import view.panel.MenuOptionAdminView;

public class MenuAdminView extends JFrame{
	
	public UserModel user;
	
	public MenuAdminView(UserModel user) {
		this.user = user;
		initComponents();
	}
	
	public void initComponents() {
		
		
		MenuOptionAdminView panel = new MenuOptionAdminView(this, user);
		
		((JFrame) this).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.add(panel, java.awt.BorderLayout.NORTH);
		this.setLocationRelativeTo(null);
		this.setSize(700,500);
		this.setVisible(true);
		
		
	
		//establishment.addActionListener(new  AdminAction(this,user, AdminActionType.oEstabliAdm));
		//room.addActionListener(new  AdminAction(this,user, AdminActionType.oRoomAdm));
		//coordinate.addActionListener(new  AdminAction(this,user, AdminActionType.oCoordinateAdm));
	}

}