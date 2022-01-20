package controller.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.UserModel;
import type.AdminOption;
import view.EstablishmentView;
import view.MenuAdminView;
import view.RoomView;
import view.panel.MenuOptionAdminView;

public class MenuAdminAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JFrame frame;
	public UserModel user;
	public String action;
	
	


	
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
			
			frame.getContentPane().add(panel, BorderLayout.NORTH);
			frame.getContentPane().add(ev, BorderLayout.CENTER);
			ev.setVisible(true);
			
			MenuOptionAdminView.comboBox.setSelectedIndex(0);
			
			//System.out.println(comboBox.getSelectedIndex());
			
			
			
		}else if(MenuOptionAdminView.comboBox.getSelectedItem().toString().equals(AdminOption.room)) {
			
			
			RoomView rv = new RoomView(user, action, frame);
			
			MenuOptionAdminView panel = new MenuOptionAdminView(frame, user);
			
			frame.getContentPane().removeAll();
			frame.repaint();
			
			frame.getContentPane().add(panel, BorderLayout.NORTH);
			frame.getContentPane().add(rv, BorderLayout.CENTER);
			rv.setVisible(true);
			
			MenuOptionAdminView.comboBox.setSelectedIndex(1);
			//System.out.println(MenuOptionAdminView.comboBox);
		}
		
		//this.frame.add(new JLabel("clique"));
		this.frame.setVisible(true);
		
	}

}
