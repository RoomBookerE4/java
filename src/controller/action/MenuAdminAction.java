package controller.action;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import model.UserModel;
import type.AdminOption;
import type.TypeAction;
import view.CoordinateAddView;
import view.EstablishmentView;
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
			//rv.setVisible(true);
			
			MenuOptionAdminView.comboBox.setSelectedIndex(1);
			//System.out.println(MenuOptionAdminView.comboBox);
		}else if(MenuOptionAdminView.comboBox.getSelectedItem().toString().equals(AdminOption.coordinate)) {
			
			
			if(action.equals(TypeAction.add)) {
				try {
					CoordinateAddView.initUI();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//System.out.println(MenuOptionAdminView.comboBox);
		}
		
		//this.frame.add(new JLabel("clique"));
		this.frame.setVisible(true);
		
	}

}
