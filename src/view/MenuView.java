package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.File;
import java.io.IOException;

import model.UserModel;

public class MenuView extends JFrame{
	

	// ICI ON AURA LA CARTE AVEC LES SALLES
	
	private static final long serialVersionUID = 1L;

	public UserModel user;
	
	public MenuView(UserModel user){
		this.user = user;
		lauchMenu();
	}
	
	public void lauchMenu(){

		JFrame frame = new JFrame("Main menu");
		
		((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{767, 127, 0};
		gridBagLayout.rowHeights = new int[]{400};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0};
		frame.getContentPane().setLayout(gridBagLayout);
		
		frame.setLocationRelativeTo(null);
		frame.setSize(500,250);
		frame.setVisible(true);
				
		MenuOptionView mOption = new MenuOptionView(frame);
		GridBagConstraints gbc_mOption = new GridBagConstraints();
		gbc_mOption.anchor = GridBagConstraints.WEST;
		gbc_mOption.gridx = 1;
		gbc_mOption.gridy = 0;
		frame.getContentPane().add(mOption, gbc_mOption);
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("/Users/marcelin/Downloads/271711240_455594239433212_4352364266861532517_n.png"));
		} catch(IOException e) {
            e.printStackTrace();
		}
		Image dimg = myPicture.getScaledInstance(myPicture.getWidth()/2, myPicture.getHeight()/2,
		        Image.SCALE_SMOOTH);
		JLabel picLabel = new JLabel(new ImageIcon(dimg));
		GridBagConstraints gbc_picLbl = new GridBagConstraints();
		gbc_picLbl.insets = new Insets(0, 0, 0, 5);
		gbc_picLbl.gridx = 0;
		gbc_picLbl.gridy = 0;
		frame.getContentPane().add(picLabel, gbc_picLbl);
		
		
		Dimension frameDim = new Dimension((int)picLabel.getMaximumSize().getWidth() + (int)mOption.getMinimumSize().getWidth(), (int)picLabel.getMaximumSize().getHeight()+26);
		frame.setSize(frameDim);
		frame.setMinimumSize(frameDim);
		//frame.setMaximumSize(frameDim);

	}

}