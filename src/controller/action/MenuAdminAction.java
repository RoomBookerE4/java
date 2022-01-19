package controller.action;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.UserModel;
import type.AdminOption;
import view.EstablishmentView;
import view.MenuAdminView;
import view.panel.MenuOptionAdminView;

public class MenuAdminAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JFrame frame;
	public UserModel user;
	public String action;
	public JPanel panel;
	

	public MenuAdminAction(JFrame frame, JPanel panel, UserModel user, String action) {
		super();
		this.frame = frame;
		this.user = user;
		this.action = action;
		this.panel = panel;
	}
	
	public MenuAdminAction(JFrame frame,  UserModel user, String action) {
		super();
		this.frame = frame;
		this.user = user;
		this.action = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(MenuOptionAdminView.comboBox.getSelectedItem().toString().equals(AdminOption.establishment)) {
			
			EstablishmentView ev = new EstablishmentView(user, action, frame);
			MenuOptionAdminView panel = new MenuOptionAdminView(frame, user);
			
			frame.getContentPane().removeAll();
			frame.repaint();
			
			this.frame.add(panel, java.awt.BorderLayout.NORTH);
			this.frame.add(ev);
			ev.setVisible(true);
		}
		
		//this.frame.add(new JLabel("clique"));
		this.frame.setVisible(true);
		
	}

}
