package view.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.io.File;
import java.io.IOException;
import model.UserModel;
import view.panel.MenuOptionView;


public class MenuView extends JFrame{
		
	private static final long serialVersionUID = 1L;

	public JFrame frame;
	public UserModel user;
	public JLabel picLabel;
	
	public MenuView(UserModel user){
		this.user = user;
		lauchMenu();
	}
	
	public void lauchMenu(){

		this.frame = new JFrame("Main menu");
		
		((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLocationRelativeTo(null);
		frame.setSize(500,250);
		frame.setVisible(true);
		
		MenuOptionView mOption = new MenuOptionView(frame, this, user);
		mOption.setVisible(true);
		BufferedImage myPicture = null;

		try {
			myPicture = ImageIO.read(new File("ressources/images/id"+this.user.getEstablishment()+"/"+String.valueOf(mOption.comboBox.getSelectedItem())+".png"));
		} catch(IOException e) {
            e.printStackTrace();
		}
		Image dimg = myPicture.getScaledInstance(myPicture.getWidth()/2, myPicture.getHeight()/2,
		        Image.SCALE_SMOOTH);
		this.picLabel = new JLabel(new ImageIcon(dimg));
		frame.getContentPane().add(picLabel, BorderLayout.CENTER);
		frame.getContentPane().add(mOption, BorderLayout.NORTH);
		
		Dimension frameDim = new Dimension((int)picLabel.getMaximumSize().getWidth() + (int)mOption.getMinimumSize().getWidth()+20, (int)picLabel.getMaximumSize().getHeight()+36);
		frame.setSize(frameDim);
		frame.setMinimumSize(frameDim);
		frame.setMaximumSize(frameDim);
		frame.pack();
		
		
	}
	
	
}