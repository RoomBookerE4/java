package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.InputStream;
import java.nio.file.Paths;

import model.CoordinateModel;
import model.DateTime;
import model.RoomModel;
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
import java.awt.Graphics;
import java.awt.Graphics2D;

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
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("/source/etage.png");

		try {
			myPicture = ImageIO.read(new File("ressources/images/id"+this.user.getEstablishment()+"/"+String.valueOf(mOption.comboBox.getSelectedItem())+".png"));
		} catch(IOException e) {
            e.printStackTrace();
		}
		Image dimg = myPicture.getScaledInstance(myPicture.getWidth()/2, myPicture.getHeight()/2,
		        Image.SCALE_SMOOTH);
		this.picLabel = new JLabel(new ImageIcon(dimg));
		//frame.getContentPane().add(picLabel, BorderLayout.WEST);
		frame.getContentPane().add(mOption, BorderLayout.EAST);
		
		Dimension frameDim = new Dimension((int)picLabel.getMaximumSize().getWidth() + (int)mOption.getMinimumSize().getWidth()+20, (int)picLabel.getMaximumSize().getHeight()+36);
		//frame.setSize(frameDim);
		//frame.setMinimumSize(frameDim);
		//frame.setMaximumSize(frameDim);
		
		
	}
	
	public class DrawRooms extends JPanel {

		int floorNbr;
		DateTime dateTime;
		HashMap<RoomModel, Boolean> availabilityMap;

		public DrawRooms(int floorNbr, DateTime dateTime, HashMap<RoomModel, Boolean> availabilityMap) {
			super();
			this.floorNbr = floorNbr;
			this.dateTime = dateTime;
			this.availabilityMap = availabilityMap;
			init();
		}
		
		public void init() {
			repaint();
			this.setBackground(Color.BLACK);
		}

		
		protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
                        
            for (RoomModel room : availabilityMap.keySet()) {
				boolean isBooked = availabilityMap.get(room);
				
				Polygon poly = new Polygon();
				
	            for(CoordinateModel coordinate : room.getCoordinates()) {
					poly.addPoint(coordinate.getX(), coordinate.getY());
				}
	            g2d.setColor(isBooked?Color.RED:Color.GREEN);
	            g2d.drawPolygon(poly);
	            g2d.dispose();

			}
        }

	}

}