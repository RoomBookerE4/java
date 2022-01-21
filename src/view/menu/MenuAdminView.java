package view.menu;

import javax.swing.JFrame;

import model.UserModel;
import view.panel.MenuOptionAdminView;

public class MenuAdminView extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		
	
	}

}