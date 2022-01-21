package view.panel;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.toedter.calendar.JDateChooser;

import controller.action.AdminAction;
import controller.action.MenuOptionAction;
import dao.DaoFactory;
import model.UserModel;
import type.AdminOption;
import type.UserRole;
import view.menu.MenuView;

import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class MenuOptionView extends JPanel {

	/**
	 * MENU D'OPTIONS
	 */
	private static final long serialVersionUID = 1L;

	public JFrame frame;
	public MenuView menuView;
	public UserModel user;

	public JComboBox<?> comboBox;
	public JDateChooser dateChooser;
	public JFormattedTextField timeTextField;

	public MenuOptionView(JFrame frame, MenuView menuView, UserModel user) {
		// super();
		this.frame = frame;
		this.menuView = menuView;
		this.user = user;
		initComponents();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initComponents() {


		DaoFactory dao = DaoFactory.getInstance();
		List<String> floorList = new ArrayList<String>();
		for (int floor : dao.getEstablishmentDao().getFloors(this.user.getEstablishment())) {
			floorList.add("Floor " + floor);
		}
		String floors[] = new String[floorList.size()];
		floors = floorList.toArray(floors);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel label_1 = new JLabel("Étage");
		this.add(label_1);
		this.comboBox = new JComboBox(floors);
		this.add(comboBox);

		this.dateChooser = new JDateChooser();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		dateChooser.setDate(Date.valueOf(formatter.format(date)));
		add(dateChooser);

		JButton btnNewButton = new JButton("Valider");

		JLabel lblTime = new JLabel("Time");
		add(lblTime);

		Format timeFormat = new SimpleDateFormat("HH:mm");
		this.timeTextField = new JFormattedTextField(timeFormat);
		this.timeTextField.setHorizontalAlignment(SwingConstants.CENTER);
		this.timeTextField.setText(timeFormat.format(date));
		add(this.timeTextField);
		add(btnNewButton);
		btnNewButton.addActionListener((ActionListener) new MenuOptionAction(frame, this));

		JButton btnNewButton_1 = new JButton("Administrator");
		btnNewButton_1.setBounds(620, 121, 100, 29);

		if (user.getRole().equals(UserRole.ADM)) {
			add(btnNewButton_1);
		}

		btnNewButton_1.addActionListener((ActionListener) new AdminAction(frame, this, user, AdminOption.oAdm));
	}

}
