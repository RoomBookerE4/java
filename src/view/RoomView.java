package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.RoomModel;
import model.UserModel;
import type.TypeAction;

import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.RoomController;

import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

public class RoomView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserModel user;
	public String action;
	public JFrame frame;

	private JTextField nameField;
	private JTextField numberField;
	private JTextField floorField;
	private JTextField timeOpenField;
	private JTextField timeCloseField;

	private RoomController roomController = new RoomController();

	public RoomView(UserModel user, String action, JFrame frame) {
		super();
		this.user = user;
		this.action = action;
		this.frame = frame;
		setLayout(null);

		initComponents();

	}

	public void initComponents() {

		if (this.action.equals(TypeAction.add)) {
			add();
		} else if (this.action.equals(TypeAction.edit)) {
			edit();
		} else if (this.action.equals(TypeAction.view)) {
			view();
		} else if (this.action.equals(TypeAction.remove)) {
			remove();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void add() {
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(273, 67, 61, 16);
		add(nameLabel);

		JLabel actionLabel = new JLabel("Add a Room");
		actionLabel.setBounds(335, 30, 154, 16);
		add(actionLabel);

		nameField = new JTextField("A004");
		nameField.setBounds(383, 62, 130, 26);
		add(nameField);
		nameField.setColumns(10);

		JLabel numberLabel = new JLabel("Number");
		numberLabel.setBounds(273, 121, 61, 16);
		add(numberLabel);

		numberField = new JTextField("Anjou");
		numberField.setBounds(383, 116, 130, 26);
		add(numberField);
		numberField.setColumns(10);

		JLabel floorLabel = new JLabel("Floor");
		floorLabel.setBounds(273, 170, 61, 16);
		add(floorLabel);

		floorField = new JTextField(1);
		floorField.setBounds(383, 165, 130, 26);
		add(floorField);
		floorField.setColumns(10);

		JLabel maxTimeLabel = new JLabel("Maximum Reservation Time ");
		maxTimeLabel.setBounds(189, 300, 178, 16);
		add(maxTimeLabel);

		JLabel timeOpenLabel = new JLabel("Time open");
		timeOpenLabel.setBounds(273, 218, 90, 16);
		add(timeOpenLabel);

		timeOpenField = new JTextField("08:00:00");
		timeOpenField.setBounds(383, 218, 130, 16);
		add(timeOpenField);

		JLabel timeCloseLabel = new JLabel("Time close");
		add(timeCloseLabel);
		timeCloseLabel.setBounds(273, 258, 90, 16);

		timeCloseField = new JTextField("18:00:00");
		timeCloseField.setBounds(384, 258, 137, 16);
		add(timeCloseField);

		JCheckBox isBookableBox = new JCheckBox("Is bookable");
		isBookableBox.setBounds(310, 346, 128, 23);
		add(isBookableBox);

		JButton decision = new JButton("Add");
		decision.setBounds(310, 385, 117, 29);
		add(decision);

		JComboBox<?> maxTimeBox = new JComboBox();
		maxTimeBox.setModel(new DefaultComboBoxModel(new String[] { "00:15:00", "00:30:00", "00:45:00", "01:00:00", "01:30:00", "02:00:00",
				"02:30:00", "03:00:00", "03:30:00", "04:00:00", "05:00:00", "06:00:00" }));
		maxTimeBox.setBounds(385, 296, 128, 27);
		add(maxTimeBox);
		
		decision.addActionListener(new RoomController(nameField, numberField, floorField, 
				timeOpenField, timeCloseField, maxTimeBox, isBookableBox, action, user));
	

	}

	public void edit() {

		List<RoomModel> rooms = roomController.searchByEstablishmentId(user.getEstablishment());
		List<String> roomNames = new ArrayList<>();

		for (RoomModel rm : rooms) {
			roomNames.add(rm.getName());
		}

		JLabel roomEditLabel = new JLabel("Room number to change");
		roomEditLabel.setBounds(150, 10, 177, 16);
		add(roomEditLabel);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox roomsBox = new JComboBox(roomNames.toArray());
		roomsBox.setBounds(350, 10, 112, 27);
		add(roomsBox);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(273, 67, 61, 16);
		add(nameLabel);

		nameField = new JTextField();
		nameField.setBounds(383, 62, 130, 26);
		add(nameField);
		nameField.setColumns(10);

		JLabel numberLabel = new JLabel("Number");
		numberLabel.setBounds(273, 121, 61, 16);
		add(numberLabel);

		numberField = new JTextField();
		numberField.setBounds(383, 116, 130, 26);
		add(numberField);
		numberField.setColumns(10);

		JLabel floorLabel = new JLabel("Floor");
		floorLabel.setBounds(273, 170, 61, 16);
		add(floorLabel);

		floorField = new JTextField();
		floorField.setBounds(383, 165, 130, 26);
		add(floorField);
		floorField.setColumns(10);

		JLabel maxTimeLabel = new JLabel("Maximum Reservation Time ");
		maxTimeLabel.setBounds(189, 300, 178, 16);
		add(maxTimeLabel);

		JLabel timeOpenLabel = new JLabel("Time open");
		timeOpenLabel.setBounds(273, 218, 90, 16);
		add(timeOpenLabel);

		timeOpenField = new JTextField();
		timeOpenField.setBounds(383, 218, 130, 16);
		add(timeOpenField);

		JLabel timeCloseLabel = new JLabel("Time close");
		add(timeCloseLabel);
		timeCloseLabel.setBounds(273, 258, 90, 16);

		timeCloseField = new JTextField();
		timeCloseField.setBounds(384, 258, 137, 16);
		add(timeCloseField);

		JCheckBox isBookableBox = new JCheckBox("Is bookable");
		isBookableBox.setBounds(310, 346, 128, 23);
		add(isBookableBox);

		JButton decision = new JButton("Edit");
		decision.setBounds(310, 385, 117, 29);
		add(decision);

		JComboBox<?> maxTimeBox = new JComboBox();
		maxTimeBox.setModel(new DefaultComboBoxModel(new String[] { "00:15:00", "00:30:00", "00:45:00", "01:00:00", "01:30:00", "02:00:00",
				"02:30:00", "03:00:00", "03:30:00", "04:00:00", "05:00:00", "06:00:00" }));
		maxTimeBox.setBounds(385, 296, 128, 27);
		add(maxTimeBox);

		nameField.setVisible(false);
		numberField.setVisible(false);
		floorField.setVisible(false);
		timeOpenField.setVisible(false);
		timeCloseField.setVisible(false);
		maxTimeBox.setVisible(false);
		decision.setVisible(false);
		isBookableBox.setVisible(false);
		timeCloseLabel.setVisible(false);
		timeOpenLabel.setVisible(false);
		maxTimeLabel.setVisible(false);
		floorLabel.setVisible(false);
		numberLabel.setVisible(false);
		nameLabel.setVisible(false);

		roomsBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String roomName = String.valueOf(roomsBox.getSelectedItem());

				RoomModel room = rooms.stream().filter(r -> roomName.equals(r.getName())).findAny().orElse(null);

				numberField.setText(room.getNumber());
				floorField.setText(String.valueOf(room.getFloor()));
				nameField.setText(room.getName());
				timeOpenField.setText(room.getOpeningTime().toString());
				timeCloseField.setText(room.closingTime.toString());
				isBookableBox.setSelected(room.isBookable);
				// maxTimeBox.setSelectedItem(room.getMaxBookingTime().toString());

				nameField.setVisible(true);
				numberField.setVisible(true);
				floorField.setVisible(true);
				timeOpenField.setVisible(true);
				timeCloseField.setVisible(true);
				maxTimeBox.setVisible(true);
				decision.setVisible(true);
				isBookableBox.setVisible(true);
				timeCloseLabel.setVisible(true);
				timeOpenLabel.setVisible(true);
				maxTimeLabel.setVisible(true);
				floorLabel.setVisible(true);
				numberLabel.setVisible(true);
				nameLabel.setVisible(true);
			}

		});
		
		decision.addActionListener(new RoomController(nameField, numberField, floorField, 
				timeOpenField, timeCloseField, maxTimeBox, isBookableBox, action, user, roomsBox));

	}

	public void view() {
		List<RoomModel> rooms = roomController.searchByEstablishmentId(user.getEstablishment());
		List<String> roomNames = new ArrayList<>();

		for (RoomModel rm : rooms) {
			roomNames.add(rm.getName());
		}

		JLabel roomEditLabel = new JLabel("Room name to see ");
		roomEditLabel.setBounds(150, 10, 177, 16);
		add(roomEditLabel);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox roomsBox = new JComboBox(roomNames.toArray());
		roomsBox.setBounds(350, 10, 112, 27);
		add(roomsBox);

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(273, 67, 61, 16);
		add(nameLabel);

		nameField = new JTextField();
		nameField.setBounds(383, 62, 130, 26);
		add(nameField);
		nameField.setColumns(10);

		JLabel numberLabel = new JLabel("Number");
		numberLabel.setBounds(273, 121, 61, 16);
		add(numberLabel);

		numberField = new JTextField();
		numberField.setBounds(383, 116, 130, 26);
		add(numberField);
		numberField.setColumns(10);

		JLabel floorLabel = new JLabel("Floor");
		floorLabel.setBounds(273, 170, 61, 16);
		add(floorLabel);

		floorField = new JTextField();
		floorField.setBounds(383, 165, 130, 26);
		add(floorField);
		floorField.setColumns(10);

		JLabel maxTimeLabel = new JLabel("Maximum Reservation Time ");
		maxTimeLabel.setBounds(189, 300, 178, 16);
		add(maxTimeLabel);

		JLabel timeOpenLabel = new JLabel("Time open");
		timeOpenLabel.setBounds(273, 218, 90, 16);
		add(timeOpenLabel);

		timeOpenField = new JTextField();
		timeOpenField.setBounds(383, 218, 130, 16);
		add(timeOpenField);

		JLabel timeCloseLabel = new JLabel("Time close");
		add(timeCloseLabel);
		timeCloseLabel.setBounds(273, 258, 90, 16);

		timeCloseField = new JTextField();
		timeCloseField.setBounds(384, 258, 137, 16);
		add(timeCloseField);

		JCheckBox isBookableBox = new JCheckBox("Is bookable");
		isBookableBox.setBounds(310, 346, 128, 23);
		add(isBookableBox);

		JComboBox<?> maxTimeBox = new JComboBox();
		maxTimeBox.setModel(new DefaultComboBoxModel(new String[] { "00:15:00", "00:30:00", "00:45:00", "01:00:00", "01:30:00", "02:00:00",
				"02:30:00", "03:00:00", "03:30:00", "04:00:00", "05:00:00", "06:00:00" }));
		maxTimeBox.setBounds(385, 296, 128, 27);
		add(maxTimeBox);

		nameField.setVisible(false);
		numberField.setVisible(false);
		floorField.setVisible(false);
		timeOpenField.setVisible(false);
		timeCloseField.setVisible(false);
		maxTimeBox.setVisible(false);
		isBookableBox.setVisible(false);
		timeCloseLabel.setVisible(false);
		timeOpenLabel.setVisible(false);
		maxTimeLabel.setVisible(false);
		floorLabel.setVisible(false);
		numberLabel.setVisible(false);
		nameLabel.setVisible(false);

		roomsBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String roomName = String.valueOf(roomsBox.getSelectedItem());

				RoomModel room = rooms.stream().filter(r -> roomName.equals(r.getName())).findAny().orElse(null);

				numberField.setText(room.getNumber());
				floorField.setText(String.valueOf(room.getFloor()));
				nameField.setText(room.getName());
				timeOpenField.setText(room.getOpeningTime().toString());
				timeCloseField.setText(room.closingTime.toString());
				isBookableBox.setSelected(room.isBookable);
				// maxTimeBox.setSelectedItem(room.getMaxBookingTime().toString());

				nameField.setVisible(true);
				numberField.setVisible(true);
				floorField.setVisible(true);
				timeOpenField.setVisible(true);
				timeCloseField.setVisible(true);
				maxTimeBox.setVisible(true);
				isBookableBox.setVisible(true);
				timeCloseLabel.setVisible(true);
				timeOpenLabel.setVisible(true);
				maxTimeLabel.setVisible(true);
				floorLabel.setVisible(true);
				numberLabel.setVisible(true);
				nameLabel.setVisible(true);
			}

		});
	}

	public void remove() {
		List<RoomModel> rooms = roomController.searchByEstablishmentId(user.getEstablishment());
		List<String> roomNames = new ArrayList<>();

		for (RoomModel rm : rooms) {
			roomNames.add(rm.getName());
		}

		JLabel roomEditLabel = new JLabel("Room to delete ");
		roomEditLabel.setBounds(150, 100, 177, 16);
		add(roomEditLabel);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox roomsBox = new JComboBox(roomNames.toArray());
		roomsBox.setBounds(350, 100, 112, 27);
		add(roomsBox);

		JButton decision = new JButton("Delete");
		decision.setBounds(310, 185, 117, 29);
		add(decision);

		decision.setVisible(false);

		roomsBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String roomName = String.valueOf(roomsBox.getSelectedItem());

				RoomModel room = rooms.stream().filter(r -> roomName.equals(r.getName())).findAny().orElse(null);

				decision.setVisible(true);
				// maxTimeBox.setSelectedItem(room.getMaxBookingTime().toString());

			}

		});

	}
}
