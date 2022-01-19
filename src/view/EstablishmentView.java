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
import controller.action.AdminAction;
import model.EstablishmentModel;
import model.UserModel;
import type.AdminOption;
import type.TypeAction;

public class EstablishmentView extends JPanel {

	private static final long serialVersionUID = 1L;

	public UserModel user;
	public String action;
	public JFrame frame;
	
	private EstablishmentController ec = new EstablishmentController();

	public  JPanel add = new JPanel();
	public  JPanel edit = new JPanel();
	
	JLabel nameLabel = new JLabel("Name");
	JTextField nameField = new JTextField();

	JLabel addressLabel = new JLabel("Address");
	JTextField addressField = new JTextField();

	JLabel timeOpenLabel = new JLabel("Time open");
	JTextField timeOpenField = new JTextField();
	
	JLabel timeCloseLabel = new JLabel("Time open");
	JTextField timeCloseField = new JTextField();
	
	 JButton decision ;

	public EstablishmentView(UserModel user, String action, JFrame frame) {
		super();
		this.user = user;
		this.action = action;
		this.frame = frame;
		initComponents();
	}

	public EstablishmentView() {
		initComponents();
	}

	public void initComponents() {

		//this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
		if (this.action.equals(TypeAction.add)) {

			
			add();
			
		}

		if (user.getEstablishment() != null) {

			if (this.action.equals(TypeAction.remove)) {
				this.setName("Establishement remove by name");
				
				decision = new JButton("remove");
				nameLabel.setText("Establishment name to delete ");
				
				this.add(nameLabel);
				this.add(nameField);
				this.add(decision);
				
				

			} else if (this.action.equals(TypeAction.edit)) {
				
				edit();

			}
			if (this.action.equals(TypeAction.view)) {
				view();
				
				

			}

		} else if (!this.action.equals(TypeAction.add)) {
			JOptionPane.showConfirmDialog(new JPanel(), "add an establishment first!", "", JOptionPane.YES_NO_OPTION);
		}

	
		///this.setBackground(Color.GRAY);
		this.setSize(400, 400);
		this.setVisible(true);

	}

	public void changeTab() {

		this.removeAll();
		/*
		 * Component[] components = this.getComponents(); for (int i=0; i <
		 * components.length; i++) { components[ i ].setVisible(false); }
		 */

	}

	public JPanel add2() {


		add = new JPanel();

		JLabel nameLabel = new JLabel("Name");
		JTextField nameField = new JTextField();

		JLabel addressLabel = new JLabel("Address");
		JTextField addressField = new JTextField();

		JLabel timeOpenLabel = new JLabel("Time open");
		JTextField timeOpenField = new JTextField();

		add.add(new JLabel("Add establishment"));
	
		add.add(nameLabel);
		add.add(nameField);
		add.add(addressLabel);
		add.add(addressField);
		
		//add.setBackground(Color.green);
		add.setSize(700, 700);
		return add;
	}
	
	public void add() {
		this.setName("Establishement add");
		
		 decision = new JButton("add");
		
	
		this.add(new JLabel("add establishment"));
		this.add(nameLabel);
		this.add(nameField);
		this.add(addressLabel);
		this.add(addressField);
		this.add(decision);
		
	}

	public JPanel edit2() {

		edit = new JPanel();
		
		JLabel nameLabel = new JLabel("Name");
		JTextField nameField = new JTextField();

		JLabel addressLabel = new JLabel("Address");
		JTextField addressField = new JTextField();

		JLabel timeOpenLabel = new JLabel("Time open");
		JTextField timeOpenField = new JTextField();

		
		edit.add(new JLabel("Edit establishment"));
		
		edit.add(nameLabel);
		edit.add(nameField);
		edit.add(addressLabel);
		edit.add(addressField);
		
		//edit.setBackground(Color.red);
		edit.setSize(700, 700);

		return edit;
	}
	
	public void edit() {
		this.setName("Establishement edit");
		
		this.add(new JLabel("edit establishment"));
		
		decision = new JButton("edit");
		 
		this.add(nameLabel);
		this.add(nameField);
		this.add(addressLabel);
		this.add(addressField);
		this.add(decision);
		
	}
	
	public void view() {
		EstablishmentModel establishment= ec.searchById(user.getEstablishment());
		
		nameLabel.setText(establishment.getName());
		addressLabel.setText(establishment.getAddress());
		timeOpenLabel.setText(establishment.getOpeningTime().toString());
		timeCloseLabel.setText(establishment.getClosingTime().toString());
		
		this.add(nameLabel);
		this.add(addressLabel);
		this.add(timeOpenLabel);
		this.add(timeCloseLabel);
	}
	

}
