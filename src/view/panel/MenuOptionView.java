package view.panel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.toedter.calendar.JDateChooser;

import controller.ReservationController;
import controller.action.AdminAction;
import controller.action.MenuOptionAction;
import dao.DaoFactory;
import model.EstablishmentModel;
import model.UserModel;
import type.AdminOption;
import type.UserRole;
import view.MenuView;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

public class MenuOptionView extends JPanel{

	/**
	 * MENU D'OPTIONS 
	 */
	private static final long serialVersionUID = 1L;
	
	public JFrame frame;
	public MenuView menuView;
	public UserModel user;
	
	public JComboBox comboBox;
	public JDateChooser dateChooser;
	public JFormattedTextField timeTextField;
	
		
	public MenuOptionView(JFrame frame, MenuView menuView, UserModel user){
		//super();
		this.frame = frame;
		this.menuView = menuView;
		this.user = user;
		initComponents();
	}

	
	public void initComponents() {
		
		//this.setLayout((LayoutManager) new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("panel");
		DaoFactory dao = DaoFactory.getInstance();
		List<String> floorList= new ArrayList<String>();
		for(int floor : dao.getEstablishmentDao().getFloors(this.user.getEstablishment())) {
			floorList.add("Floor "+floor);
		}
		String floors[]=new String[floorList.size()];
		floors=floorList.toArray(floors);
		setLayout(null);
		JLabel label_1 = new JLabel("Étage");
		label_1.setBounds(166, 5, 34, 16);
		this.add(label_1);
		this.comboBox = new JComboBox(floors);
		comboBox.setBounds(210, 0, 82, 27);
		this.add(comboBox);
		
		this.dateChooser = new JDateChooser();
		dateChooser.setBounds(161, 32, 131, 26);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date(System.currentTimeMillis());
		dateChooser.setDate(Date.valueOf(formatter.format(date)));
		add(dateChooser);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.setBounds(183, 94, 87, 29);
		//btnNewButton.addActionListener(new MenuOptionAction(this));
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(167, 68, 31, 16);
		add(lblTime);
		
		Format timeFormat = new SimpleDateFormat("HH:mm");
		this.timeTextField = new JFormattedTextField(timeFormat);
		timeTextField.setBounds(210, 63, 82, 26);
		this.timeTextField.setHorizontalAlignment(SwingConstants.CENTER);
		this.timeTextField.setText(timeFormat.format(date));
		add(this.timeTextField);
		add(btnNewButton);
		btnNewButton.addActionListener((ActionListener) new MenuOptionAction(frame, this));
		
		//this.add(new JLabel("Test"));
		//this.setLayout(new GridLayout(20, 2));
		
		
				
				JButton btnNewButton_1 = new JButton("Administrator");
				btnNewButton_1.setBounds(170, 135, 100, 38);
				
				
				if(user.getRole().equals(UserRole.ADM)) {
					add(btnNewButton_1);
				}
				
				
				btnNewButton_1.addActionListener((ActionListener) new  AdminAction(frame, this, user, AdminOption.oAdm));
	}
	
}
