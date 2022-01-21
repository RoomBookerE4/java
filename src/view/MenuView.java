package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import model.UserModel;
import view.panel.MenuOptionView;
import model.UserModel;
import view.panel.MenuOptionView;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.FlowLayout;

public class MenuView extends JFrame{
	

	// ICI ON AURA LA CARTE AVEC LES SALLES
	
	private static final long serialVersionUID = 1L;

	public  UserModel user;
	
	public MenuView(UserModel user){
		this.user = user;
		lauchMenu();
	}
	
	public void lauchMenu(){

		JFrame frame = new JFrame("Main menu");
		
		((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLocationRelativeTo(null);
		frame.setSize(500,250);
		frame.setVisible(true);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		BufferedImage myPicture = null;
		//zara
		/*ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("/source/etage.png");

		try {
			myPicture = ImageIO.read(input);
		} catch (IOException e) {
		    e.printStackTrace();
		}*/
		try {

			//marcelin
			//myPicture = ImageIO.read(new File("/Users/marcelin/Downloads/271711240_455594239433212_4352364266861532517_n.png"));
			
			//zara
			myPicture = ImageIO.read(new File("/Users/zaramarks/ETAGE2.png"));
		} catch(IOException e) {
            e.printStackTrace();
		}
		Image dimg = myPicture.getScaledInstance(myPicture.getWidth()/2, myPicture.getHeight()/2,
		        Image.SCALE_SMOOTH);
		JLabel picLabel = new JLabel(new ImageIcon(dimg));
		frame.getContentPane().add(picLabel);
		
		MenuOptionView mOption = new MenuOptionView(frame, user);
		frame.getContentPane().add(mOption);
		
		
		Dimension frameDim = new Dimension((int)picLabel.getMaximumSize().getWidth() + (int)mOption.getMinimumSize().getWidth()+20, (int)picLabel.getMaximumSize().getHeight()+26);
		frame.setSize(frameDim);
		frame.setMinimumSize(frameDim);
		frame.setMaximumSize(frameDim);
	}

}