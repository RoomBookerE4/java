package view.panel;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.action.AdminAction;
import controller.action.MenuAdminAction;
import model.UserModel;
import type.AdminOption;
import type.TypeAction;

public class MenuOptionAdminView extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  static JComboBox<Object> comboBox;
	
	public JFrame frame;
	public UserModel user;
	
	public MenuOptionAdminView(JFrame frame,UserModel user ){
		//super();
		this.frame = frame;
		this.user = user;
		initComponents();
	}
	

	
	
	public void initComponents() {
		
		comboBox = new JComboBox();
		
		if(user.getEstablishment() == null) {
			comboBox.addItem(AdminOption.establishment) ;
		}else {
			comboBox.addItem(AdminOption.establishment);
			comboBox.addItem(AdminOption.room);
			comboBox.addItem(AdminOption.coordinate);
		}
		
		
		JButton add= new JButton("add");
		JButton remove= new JButton("remove");
		JButton edit = new JButton("edit");
		JButton view = new JButton("view");
		JButton back = new JButton("Return");
		
		this.add(comboBox);
		this.add(add);
		this.add(edit);
		this.add(view);
		this.add(remove);
		this.add(back);
		
		
		add.addActionListener(new MenuAdminAction(frame, user, TypeAction.add));
		edit.addActionListener(new MenuAdminAction(frame, user, TypeAction.edit));
		remove.addActionListener(new MenuAdminAction(frame, user, TypeAction.remove));
		view.addActionListener(new MenuAdminAction(frame, user, TypeAction.view));
		
		back.addActionListener(new AdminAction(frame,this, user, TypeAction.back));
		
		
		
	}


}
