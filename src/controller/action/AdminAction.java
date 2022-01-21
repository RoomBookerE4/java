package controller.action;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.UserModel;
import type.AdminOption;
import type.TypeAction;
import view.menu.MenuAdminView;
import view.menu.MenuView;

public class AdminAction extends javax.swing.AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public JFrame frame;
	public UserModel user;
	public String action;
	public JPanel panel;
	
	

	public AdminAction(JFrame frame, JPanel panel, UserModel user, String action) {
		super();
		this.frame = frame;
		this.user = user;
		this.action = action;
		this.panel = panel;
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
			
		if(this.action.equals(AdminOption.oAdm)) {
			this.frame.dispose();
			MenuAdminView av = new MenuAdminView(user);
			this.setFrame(av);
			
		}
			
		if(this.action.equals(TypeAction.back)) {
			new MenuView(user);
			this.frame.dispose();
		}
		
	}


	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}


	public UserModel getUser() {
		return user;
	}


	public void setUser(UserModel user) {
		this.user = user;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public JPanel getPanel() {
		return panel;
	}


	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	


}
