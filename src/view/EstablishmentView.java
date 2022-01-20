package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.EstablishmentController;
import controller.RoomController;
import controller.action.AdminAction;
import controller.action.EstablishmentAction;
import model.EstablishmentModel;
import model.UserModel;
import type.AdminOption;
import type.TypeAction;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EstablishmentView extends JPanel {

	private static final long serialVersionUID = 1L;

	public UserModel user;
	public String action;
	public JFrame frame;
	
	private int width = 0;
	private int height = 0;
	
	private EstablishmentController ec = new EstablishmentController();

	public  JPanel add = new JPanel();
	public  JPanel edit = new JPanel();
	
	JLabel actionLabel = new JLabel("Action");
	
	JLabel nameLabel = new JLabel("Name");
	public  JTextField nameField = new JTextField();

	JLabel addressLabel = new JLabel("Address");
	public  JTextField addressField = new JTextField();

	JLabel timeOpenLabel = new JLabel("Time open");
	public  JTextField timeOpenField = new JTextField();
	
	JLabel timeCloseLabel = new JLabel("Time close");
	public  JTextField timeCloseField = new JTextField();
	
	JButton decision ;
	 

	public EstablishmentView(UserModel user, String action, JFrame frame) {
		super();
		this.user = user;
		this.action = action;
		this.frame = frame;
		initComponents();
	}
	


	public void initComponents() {

		if (user.getEstablishment() != null) {

			if (this.action.equals(TypeAction.remove)) {
				remove();

			} else if (this.action.equals(TypeAction.edit)) {
				
				edit();

			}
			if (this.action.equals(TypeAction.view)) {
				view();
				
			}
			
			if (this.action.equals(TypeAction.add)) {
				
				JOptionPane.showConfirmDialog(new JPanel(), "remove this establishment first!", "", JOptionPane.CLOSED_OPTION);
			}

		} else if (!this.action.equals(TypeAction.add)) {
			JOptionPane.showConfirmDialog(new JPanel(), "add an establishment first!", "", JOptionPane.YES_NO_OPTION);
		}else if (this.action.equals(TypeAction.add)){
			add();
		}

	
		///this.setBackground(Color.GRAY);
		//this.setSize(400, 400);

	}

	
	public void add() {
		
		this.setName("Establishement add");
		decision = new JButton("add");
		
		timeCloseField.setText("18:00:00");
		timeOpenField.setText("08:00:00");
		addressField.setText("AA");
		nameField.setText("AA");
		
		
		actionLabel.setText("Add establishement");
		actionLabel.setBounds(350, 5, 150, 30);
		
		timeCloseLabel.setBounds(319, 206, 94, 16);
		timeCloseField.setBounds(411, 201, 130, 26);
		
		timeOpenLabel.setBounds(319, 155, 88, 16);
		timeOpenField.setBounds(411, 150, 130, 26);
		
		addressLabel.setBounds(319, 110, 61, 16);
		addressField.setBounds(411, 105, 130, 26);
		
		nameLabel.setBounds(319, 56, 61, 16);
		nameField.setBounds(411, 51, 130, 26);
		
		
		decision.setBounds(367, 252, 117, 29);
		
		
		nameField.setColumns(10);
		timeCloseField.setColumns(10);
		timeOpenField.setColumns(10);
		addressField.setColumns(10);
		
		setLayout(null);
		
		add(actionLabel);
		add(nameLabel);
		add(nameField);
		add(addressLabel);
		add(addressField);
		add(timeOpenLabel);
		add(timeOpenField);
		add(timeCloseLabel);
		add(timeCloseField);
		
		
		add(decision);
		
		decision.addActionListener(new EstablishmentAction( nameField,  addressField,  
				timeOpenField,timeCloseField, user.getEstablishment(), action, frame, user));
	
		
	}

	
	public void edit() {
		this.setName("Establishement edit");
		
		EstablishmentModel establishment= ec.searchById(user.getEstablishment());
		
		
		nameField.setText(establishment.getName());
		addressField.setText(establishment.getAddress());
		timeOpenField.setText(establishment.getOpeningTime().toString());
		timeCloseField.setText(establishment.getClosingTime().toString());
		
		
		decision = new JButton("Edit");
		
		actionLabel.setText("Edit establishement");
		actionLabel.setBounds(350, 5, 150, 30);
		
		timeCloseLabel.setBounds(319, 206, 94, 16);
		timeCloseField.setBounds(411, 201, 130, 26);
		
		timeOpenLabel.setBounds(319, 155, 88, 16);
		timeOpenField.setBounds(411, 150, 130, 26);
		
		addressLabel.setBounds(319, 110, 61, 16);
		addressField.setBounds(411, 105, 130, 26);
		
		nameLabel.setBounds(319, 56, 61, 16);
		nameField.setBounds(411, 51, 130, 26);
		
		
		decision.setBounds(367, 252, 117, 29);
		
		
		nameField.setColumns(10);
		timeCloseField.setColumns(10);
		timeOpenField.setColumns(10);
		addressField.setColumns(10);
		
		setLayout(null);
		
		
		add(nameLabel);
		add(nameField);
		add(addressLabel);
		add(addressField);
		add(timeOpenLabel);
		add(timeOpenField);
		add(timeCloseLabel);
		add(timeCloseField);
		add(actionLabel);
		add(decision);
		

		decision.addActionListener(new EstablishmentAction( nameField,  addressField,  
				timeOpenField,timeCloseField, user.getEstablishment(), action, frame, user));
	
		
	}
	
	public void view() {
		
		this.setName("Establishement view");
		
		EstablishmentModel establishment= ec.searchById(user.getEstablishment());
		
		
		nameField.setText(establishment.getName());
		addressField.setText(establishment.getAddress());
		timeOpenField.setText(establishment.getOpeningTime().toString());
		timeCloseField.setText(establishment.getClosingTime().toString());
		
		
		
		actionLabel.setText("View establishement");
		actionLabel.setBounds(350, 5, 150, 30);
		
		timeCloseLabel.setBounds(319, 206, 94, 16);
		timeCloseField.setBounds(411, 201, 130, 26);
		
		timeOpenLabel.setBounds(319, 155, 88, 16);
		timeOpenField.setBounds(411, 150, 130, 26);
		
		addressLabel.setBounds(319, 110, 61, 16);
		addressField.setBounds(411, 105, 130, 26);
		
		nameLabel.setBounds(319, 56, 61, 16);
		nameField.setBounds(411, 51, 130, 26);
		
		
		
		
		nameField.setColumns(10);
		timeCloseField.setColumns(10);
		timeOpenField.setColumns(10);
		addressField.setColumns(10);
		
		setLayout(null);
		
		
		add(nameLabel);
		add(nameField);
		add(addressLabel);
		add(addressField);
		add(timeOpenLabel);
		add(timeOpenField);
		add(timeCloseLabel);
		add(timeCloseField);
		add(actionLabel);
		
		
		
	}
	
	
	public void remove() {

		EstablishmentModel establishment= ec.searchById(user.getEstablishment());
		
		
		nameLabel.setText(establishment.getName());
		
		actionLabel.setText("Remove this establishement: ");
		decision = new JButton("Remove");
		
		nameLabel.setBounds(330, 50, 100, 20);
		decision.setBounds(300, 100, 117, 29);
		actionLabel.setBounds(280, 5, 200, 30);
		
		setLayout(null);
		
		this.add(decision);
		this.add(nameLabel);
		this.add(actionLabel);
		
		decision.addActionListener(new EstablishmentAction( nameField,  addressField,  
				timeOpenField,timeCloseField, user.getEstablishment(), action, frame, user));
	
		
	}
	
	 @Override
	    public Dimension getPreferredSize() {
	        if (isPreferredSizeSet()) {
	            return super.getPreferredSize();
	        } else {
	            return new Dimension(width, height);
	        }
	    }
}
