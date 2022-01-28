package controller.action;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import dao.DaoFactory;
import model.DateTime;
import model.RoomModel;
import view.panel.DrawRooms;
import view.panel.MenuOptionView;

public class MenuOptionAction extends javax.swing.AbstractAction{

	private static final long serialVersionUID = 1L;
	
	public static final String NOM_ACTION = "ValiderAffichage";
	
	public Frame frame;
	public MenuOptionView menuOptionView;
	public DaoFactory daoFactory;
	
	public MenuOptionAction(Frame frame, MenuOptionView menuOptionView) {
		 super(NOM_ACTION);
		 this.frame = frame;
		 this.menuOptionView = menuOptionView;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		String floorName = String.valueOf(menuOptionView.comboBox.getSelectedItem());
		int floorNbr = Integer.valueOf(floorName.split(" ")[1]);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = Date.valueOf(formatter.format(menuOptionView.dateChooser.getDate()));
		Time time = Time.valueOf(menuOptionView.timeTextField.getText()+":00");
		DateTime dateTime = new DateTime(date, time);
		
		menuOptionView.menuView.frame.getContentPane().removeAll();
		menuOptionView.menuView.frame.getContentPane().repaint();
		updateImage();
		menuOptionView.menuView.frame.getContentPane().add(menuOptionView, BorderLayout.NORTH);
		
		
		daoFactory = DaoFactory.getInstance();
		HashMap<RoomModel, Boolean> availabilityMap = daoFactory.getRoomDao().listRoomWithStatus(menuOptionView.user.getEstablishment(), floorNbr, dateTime);
		DrawRooms drawRooms = new DrawRooms(floorNbr, dateTime, availabilityMap, menuOptionView);
		
		menuOptionView.menuView.frame.add(drawRooms, BorderLayout.CENTER);
	}
	
	
	
	public void updateImage() {
		BufferedImage myPicture = null;
		
		try {
			myPicture = ImageIO.read(new File("ressources/images/id"+menuOptionView.user.getEstablishment()+"/"+String.valueOf(menuOptionView.comboBox.getSelectedItem())+".png"));
		} catch(IOException e) {
            e.printStackTrace();
		}
		
		Image dimg = myPicture.getScaledInstance(myPicture.getWidth()/2, myPicture.getHeight()/2,
		        Image.SCALE_SMOOTH);
		menuOptionView.menuView.picLabel = new JLabel(new ImageIcon(dimg));
		menuOptionView.menuView.frame.getContentPane().add(menuOptionView.menuView.picLabel, 0);

		Dimension frameDim = new Dimension((int)menuOptionView.menuView.picLabel.getMaximumSize().getWidth() + (int)menuOptionView.getMinimumSize().getWidth()+20, (int)menuOptionView.menuView.picLabel.getMaximumSize().getHeight()+36);
		menuOptionView.menuView.frame.setSize(frameDim);

	}

}