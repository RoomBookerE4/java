package view;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuOptionView extends JPanel{

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
		JComboBox comboBox = new JComboBox(floors);
		
		this.add(new JLabel("Étage"));
		
		//this.add(Box.createRigidArea(new Dimension(5,500)));
		//this.add(Box.createVerticalStrut(1));
		this.add(comboBox);
		
		//this.add(new JLabel("Test"));
		//this.setLayout(new GridLayout(20, 2));
		
		
	}
	
}
