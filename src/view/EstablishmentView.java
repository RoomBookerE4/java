package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.EstablishmentController;
import controller.action.EstablishmentAction;
import model.EstablishmentModel;
import model.UserModel;
import type.TypeAction;

public class EstablishmentView extends JPanel {

	private static final long serialVersionUID = 1L;

	public UserModel user;
	public String action;
	public JFrame frame;

	public JTextField nameField = new JTextField();
	public JTextField addressField = new JTextField();
	public JTextField timeOpenField = new JTextField();
	public JTextField timeCloseField = new JTextField();

	EstablishmentAction ec = new EstablishmentAction();

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

				JOptionPane.showConfirmDialog(new JPanel(), "remove this establishment first!", "",
						JOptionPane.CLOSED_OPTION);
			}

		} else if (!this.action.equals(TypeAction.add)) {
			JOptionPane.showConfirmDialog(new JPanel(), "add an establishment first!", "", JOptionPane.YES_NO_OPTION);
		} else if (this.action.equals(TypeAction.add)) {
			add();
		}

	}

	public void add() {

		this.setName("Establishement add");
		JButton decision = new JButton("add");

		timeCloseField.setText("18:00:00");
		timeOpenField.setText("08:00:00");
		addressField.setText("AA");
		nameField.setText("AA");

		JLabel actionLabel = new JLabel("Action");
		actionLabel.setText("Add establishement");
		actionLabel.setBounds(350, 5, 150, 30);

		JLabel timeCloseLabel = new JLabel("Time close");
		timeCloseLabel.setBounds(319, 206, 94, 16);
		timeCloseField.setBounds(411, 201, 130, 26);

		JLabel timeOpenLabel = new JLabel("Time open");
		timeOpenLabel.setBounds(319, 155, 88, 16);
		timeOpenField.setBounds(411, 150, 130, 26);

		JLabel addressLabel = new JLabel("Address");
		addressLabel.setBounds(319, 110, 61, 16);
		addressField.setBounds(411, 105, 130, 26);

		JLabel nameLabel = new JLabel("Name");
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

		decision.addActionListener(new EstablishmentAction(nameField, addressField, timeOpenField, timeCloseField,
				user.getEstablishment(), action, frame, user));

	}

	public void edit() {
		this.setName("Establishement edit");

		EstablishmentModel establishment = ec.searchById(user.getEstablishment());

		nameField.setText(establishment.getName());
		addressField.setText(establishment.getAddress());
		timeOpenField.setText(establishment.getOpeningTime().toString());
		timeCloseField.setText(establishment.getClosingTime().toString());

		JButton decision = new JButton("Edit");

		JLabel actionLabel = new JLabel("Action");
		actionLabel.setText("Edit establishement");
		actionLabel.setBounds(350, 5, 150, 30);

		JLabel timeCloseLabel = new JLabel("Time close");
		timeCloseLabel.setBounds(319, 206, 94, 16);
		timeCloseField.setBounds(411, 201, 130, 26);

		JLabel timeOpenLabel = new JLabel("Time open");
		timeOpenLabel.setBounds(319, 155, 88, 16);
		timeOpenField.setBounds(411, 150, 130, 26);

		JLabel addressLabel = new JLabel("Address");
		addressLabel.setBounds(319, 110, 61, 16);
		addressField.setBounds(411, 105, 130, 26);

		JLabel nameLabel = new JLabel("Name");
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

		decision.addActionListener(new EstablishmentAction(nameField, addressField, timeOpenField, timeCloseField,
				user.getEstablishment(), action, frame, user));

	}

	public void view() {

		this.setName("Establishement view");

		EstablishmentModel establishment = ec.searchById(user.getEstablishment());

		nameField.setText(establishment.getName());
		addressField.setText(establishment.getAddress());
		timeOpenField.setText(establishment.getOpeningTime().toString());
		timeCloseField.setText(establishment.getClosingTime().toString());

		JLabel actionLabel = new JLabel("Action");
		actionLabel.setText("View establishement");
		actionLabel.setBounds(350, 5, 150, 30);

		JLabel timeCloseLabel = new JLabel("Time close");
		timeCloseLabel.setBounds(319, 206, 94, 16);
		timeCloseField.setBounds(411, 201, 130, 26);

		JLabel timeOpenLabel = new JLabel("Time open");
		timeOpenLabel.setBounds(319, 155, 88, 16);
		timeOpenField.setBounds(411, 150, 130, 26);

		JLabel addressLabel = new JLabel("Address");
		addressLabel.setBounds(319, 110, 61, 16);
		addressField.setBounds(411, 105, 130, 26);

		JLabel nameLabel = new JLabel("Name");
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

		EstablishmentModel establishment = ec.searchById(user.getEstablishment());

		JLabel nameLabel = new JLabel("Name");
		nameLabel.setText(establishment.getName());

		JLabel actionLabel = new JLabel("Action");
		actionLabel.setText("Remove this establishement: ");

		JButton decision = new JButton("Remove");

		nameLabel.setBounds(330, 50, 100, 20);
		decision.setBounds(300, 100, 117, 29);
		actionLabel.setBounds(280, 5, 200, 30);

		setLayout(null);

		this.add(decision);
		this.add(nameLabel);
		this.add(actionLabel);

		decision.addActionListener(new EstablishmentAction(nameField, addressField, timeOpenField, timeCloseField,
				user.getEstablishment(), action, frame, user));

	}

}
