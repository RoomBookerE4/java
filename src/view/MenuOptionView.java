package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.toedter.calendar.JDateChooser;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

public class MenuOptionView extends JPanel implements ActionListener{

	/**
	 * MENU D'OPTIONS 
	 */
	private static final long serialVersionUID = 1L;
	
	public JFrame frame;
		
	public MenuOptionView(JFrame frame){
		//super();
		this.frame = frame;
		initComponents();
	}

	
	public void initComponents() {
		
		//this.setLayout((LayoutManager) new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("panel");  
		String floors[]={"Floor 0 ","Floor 2","Floor 3","Floor 4"};
		setLayout(null);
		JLabel label_1 = new JLabel("Étage");
		label_1.setBounds(155, 5, 34, 16);
		this.add(label_1);
		JComboBox comboBox = new JComboBox(floors);
		comboBox.setBounds(199, 0, 104, 27);
		this.add(comboBox);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(150, 32, 153, 26);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date(System.currentTimeMillis());
		dateChooser.setDate(Date.valueOf(formatter.format(date)));
		add(dateChooser);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.setBounds(183, 94, 87, 29);
		btnNewButton.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Time");
		lblNewLabel.setBounds(156, 68, 31, 16);
		add(lblNewLabel);
		
		Format timeFormat = new SimpleDateFormat("HH:mm");
		JFormattedTextField formattedTextField = new JFormattedTextField(timeFormat);
		formattedTextField.setBounds(199, 63, 104, 26);
		formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
    	formattedTextField.setText(timeFormat.format(date));
		add(formattedTextField);
		add(btnNewButton);
		
		//this.add(new JLabel("Test"));
		//this.setLayout(new GridLayout(20, 2));
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
}
