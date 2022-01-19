package view.panel;

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
	public static JComboBox comboBox;
	
	public JFrame frame;
	public UserModel user;
	
	public MenuOptionAdminView(JFrame frame,UserModel user ){
		//super();
		this.frame = frame;
		this.user = user;
		initComponents();
	}
	
	
	
	public void initComponents() {
		
		String options[] = new String[3];
		
		if(user.getEstablishment() == null) {
			options[0] = AdminOption.establishment;
		}else {
			options[0] = AdminOption.establishment;
			options[1] = AdminOption.room;
			options[2] = AdminOption.coordinate;
		}
		
		comboBox = new JComboBox<Object>(options);
		
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
