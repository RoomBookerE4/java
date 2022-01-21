package view.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JPanel;

import model.CoordinateModel;
import model.DateTime;
import model.RoomModel;

import javax.imageio.ImageIO;


public class DrawRooms extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int floorNbr;
	public DateTime dateTime;
	public HashMap<RoomModel, Boolean> availabilityMap;
	public MenuOptionView menuOptionView;

	public DrawRooms(int floorNbr, DateTime dateTime, HashMap<RoomModel, Boolean> availabilityMap,
			MenuOptionView menuOptionView) {
		super();
		this.floorNbr = floorNbr;
		this.dateTime = dateTime;
		this.availabilityMap = availabilityMap;
		this.menuOptionView = menuOptionView;
		setLayout(null);

		init();
	}

	public void init() {
		repaint();
		this.setOpaque(false);
		// this.setBackground(Color.BLACK);

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage myPicture = null;
		Graphics2D g2d = (Graphics2D) g.create();

		try {
			myPicture = ImageIO.read(new File("ressources/images/id" + menuOptionView.user.getEstablishment() + "/"
					+ String.valueOf(menuOptionView.comboBox.getSelectedItem()) + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = myPicture.getScaledInstance(myPicture.getWidth() / 2, myPicture.getHeight() / 2,
				Image.SCALE_SMOOTH);
		if (dimg != null) {
			g.drawImage(dimg, 7, 0, this);
		}
		for (RoomModel room : availabilityMap.keySet()) {
			boolean isBooked = availabilityMap.get(room);

			Polygon poly = new Polygon();

			for (CoordinateModel coordinate : room.getCoordinates()) {
				poly.addPoint(coordinate.getX(), coordinate.getY());
			}

			g2d.setColor(isBooked ? Color.RED : Color.GREEN);
			g2d.drawPolygon(poly);

		}
		g2d.dispose();
	}
}
