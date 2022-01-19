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

public class MenuOptionAdmView extends JPanel implements ActionListener{

	/**
	 * MENU D'OPTIONS 
	 */
	private static final long serialVersionUID = 1L;
	
	public JFrame frame;
		
	public MenuOptionAdmView(JFrame frame){
		//super();
		this.frame = frame;
		initComponents();
	}

	
	public void initComponents() {
		
		//this.setLayout((LayoutManager) new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel("panel");  
		String floors[]={"Floor 0 ","Floor 2","Floor 3","Floor 4"};        
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 49, 87, 30};
		gridBagLayout.rowHeights = new int[]{16, 27, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 1;
		JLabel label_1 = new JLabel("Étage");
		this.add(label_1, gbc);
		JComboBox comboBox = new JComboBox(floors);
		
		//this.add(Box.createRigidArea(new Dimension(5,500)));
		//this.add(Box.createVerticalStrut(1));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		this.add(comboBox, gbc_comboBox);
		
		JDateChooser dateChooser = new JDateChooser();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date(System.currentTimeMillis());
		dateChooser.setDate(Date.valueOf(formatter.format(date)));
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 2;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 2;
		add(dateChooser, gbc_dateChooser);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.addActionListener(this);
		
		JLabel lblNewLabel = new JLabel("Time");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		add(lblNewLabel, gbc_lblNewLabel);
		
		Format timeFormat = new SimpleDateFormat("HH:mm");
		JFormattedTextField formattedTextField = new JFormattedTextField(timeFormat);
		formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
    	formattedTextField.setText(timeFormat.format(date));
		GridBagConstraints gbc_formattedTextField = new GridBagConstraints();
		gbc_formattedTextField.insets = new Insets(0, 0, 5, 5);
		gbc_formattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField.gridx = 2;
		gbc_formattedTextField.gridy = 3;
		add(formattedTextField, gbc_formattedTextField);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 4;
		add(btnNewButton, gbc_btnNewButton);
		
		//this.add(new JLabel("Test"));
		//this.setLayout(new GridLayout(20, 2));
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
}
