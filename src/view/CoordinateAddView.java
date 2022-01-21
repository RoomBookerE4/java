package view;

import java.awt.Font;
import java.awt.Polygon;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.RoomController;
import dao.DaoFactory;
import model.CoordinateModel;
import model.RoomModel;
import model.UserModel;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;


public class CoordinateAddView {
	
	private static final int PANEL_WIDTH = 700;
    private static final int PANEL_HEIGHT = 700;
    
    public static class Drawing extends JPanel {

        private static final Font FONT = new Font("Arial", Font.PLAIN, 12);

        //rivate List<Polygon> polygons = new ArrayList<Polygon>();
        
        private HashMap< String, Polygon> polygonRoom= new HashMap<>();

        private Polygon currentPolygon = new Polygon();
        
        public static JTextField text;
        
        public static JComboBox roomsBox;
        
        public UserModel user = new UserModel("zara ","marks", "administrator", "zara@gmail.com", 1);
        
      
        private MouseAdapter mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (e.getClickCount() == 1) {
                        addPoint(e.getX(), e.getY());
                    } else if (e.getClickCount() == 2) {
                    	clearCurrentPolygon();
                    }
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    clearCurrentPolygon();
                }
            }

        };

        public Drawing() throws IOException {
            addMouseListener(mouseListener);
            JButton button = new JButton("Insert database"); 
            
            
            button.setBounds(230, 550, 150, 30);
            button.addActionListener((ActionListener) new ActionListener(){  
           
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				addAll();
    				
    			}  
            });
            
           // text = new JTextField("room identifiant");
             RoomController roomController = new RoomController();
             
            List<RoomModel> rooms = roomController.searchByEstablishmentId(user.getEstablishment());
    		List<String> roomNumber = new ArrayList<>();
    	

    		for (RoomModel rm : rooms) {
    			roomNumber.add(rm.getNumber());
    		}
    		List<String> sortedRooms = roomNumber.stream().sorted().collect(Collectors.toList());
    		//@SuppressWarnings({ "unchecked", "rawtypes" })
    		roomsBox = new JComboBox(sortedRooms.toArray());
    		roomsBox.setBounds(400, 550, 150, 30);
    		
            
            JButton button2 = new JButton("Create"); 
            
            button2.setBounds(600, 550, 150, 30);
            button2.addActionListener((ActionListener) new ActionListener(){  
           
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				createPolygon();
    				
    				
    			}  
            });
           
            this.add(button);
           // this.add(text);
            this.add(button2);
            add(roomsBox);
            //this.setMinimumSize(new Dimension(myPicture.getWidth()/2 + 14, myPicture.getHeight()/2));
           
        }

        protected void addPoint(int x, int y) {
            currentPolygon.addPoint(x, y);
            repaint();
        }

        protected void clearCurrentPolygon() {
            currentPolygon = new Polygon();
            repaint();
        }

        protected void createPolygon() {
            if (currentPolygon.npoints > 2) {
                //polygons.add(currentPolygon);
                polygonRoom.put(String.valueOf(roomsBox.getSelectedItem()), currentPolygon);
            }
            // key value
           
           //text.setText("");
           clearCurrentPolygon();
           repaint();
        }

        
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.setFont(FONT);
           /* for (Polygon polygon : polygons) {
                drawPolygon(g, polygon);
            }*/
            
            BufferedImage myPicture = null;
    		try {
    			
    			//marcelin
    			//myPicture = ImageIO.read(new File("/Users/marcelin/Downloads/271711240_455594239433212_4352364266861532517_n.png"));
    			
    			//XXX zara
    			//ETAGE -1
    			//myPicture = ImageIO.read(new File("/Users/zaramarks/ETAGE-1.png"));
    			//ETAGE O 
    			//myPicture = ImageIO.read(new File("/Users/zaramarks/ETAGE0.png"));
    			//etage 1
    			//myPicture = ImageIO.read(new File("/Users/zaramarks/ETAGE1.png"));
    			//etage 2
    			//myPicture = ImageIO.read(new File("/Users/zaramarks/ETAGE2.png"));
    			//etage 3
    			//myPicture = ImageIO.read(new File("/Users/zaramarks/ETAGE3.png"));
    			//etage 4
    			myPicture = ImageIO.read(new File("/Users/zaramarks/ETAGE4.png"));
    			
    			//myPicture = ImageIO.read(new File("/Users/zaramarks/ETAGE2.png"));
    		} catch(IOException e) {
                e.printStackTrace();
    		}
    		Image dimg = myPicture.getScaledInstance(myPicture.getWidth()/2, myPicture.getHeight()/2,
    		        Image.SCALE_SMOOTH);
            
            if (dimg != null) {
                g.drawImage(dimg, 7, 0, this);
            }
            
            polygonRoom.forEach((k, p) -> {
            	drawPolygon(g, p);
            });
            
            g.setColor(Color.GREEN);
            drawPolygon(g, currentPolygon);
        }

        private void drawPolygon(Graphics g, Polygon polygon) {
            if (polygon.npoints < 3) {
                if (polygon.npoints == 1) {
                    g.fillOval(polygon.xpoints[0] - 2, polygon.ypoints[0] - 2, 4, 4);
                    drawNthPoint(g, polygon, 0);
                } else if (polygon.npoints == 2) {
                    g.drawLine(polygon.xpoints[0], polygon.ypoints[0], polygon.xpoints[1], polygon.ypoints[1]);
                    drawNthPoint(g, polygon, 0);
                    drawNthPoint(g, polygon, 1);
                }
            } else {
                g.drawPolygon(polygon);
                for (int i = 0; i < polygon.npoints; i++) {
                    drawNthPoint(g, polygon, i);
                }
            }
        }

        private void drawNthPoint(Graphics g, Polygon polygon, int nth) {
            // Only works 26 times!
            String name = Character.toString((char) ('A' + nth));
            int x = polygon.xpoints[nth];
            int height = g.getFontMetrics().getHeight();
            int y = polygon.ypoints[nth] < height ? polygon.ypoints[nth] + height : polygon.ypoints[nth];
            g.drawString(name, x, y);
        }
        
       
        String room="";
        public  void addAll() {
        	
        		polygonRoom.forEach((k, p) -> {
        	    //System.out.format("key: %s, value: %d%n", k, v);
        			List<CoordinateModel> coordinates = new ArrayList<>();
        	    	int x[] = p.xpoints;
                    int y[] = p.ypoints;
                    
                    for(int i=0; i< x.length;i++) {
                    	if(x[i]!=0 &&  y[i] != 0){
                    		coordinates.add(new CoordinateModel(x[i], y[i], i+1, k));
                    		System.out.println("X "+ x[i]);
                    		System.out.println("Y "+ y[i]);
                    		System.out.println("ORDER "+ (i+1));
                    		System.out.println("ROOM "+ k);
                    		System.out.println("------------------------");
                    	}
                    }
                    
                    DaoFactory daoFactory = DaoFactory.getInstance();
                    
                    if(daoFactory.getCoordinateDao().addCoordinates(coordinates, k)) {
                    	JOptionPane.showConfirmDialog(new JPanel(), "Coordinates added", "", JOptionPane.OK_OPTION);
                    }else {
                    	JOptionPane.showConfirmDialog(new JPanel(), "Failed to add coordinates", "", JOptionPane.OK_OPTION);
                    }
                    	
                    //}
                    
                    polygonRoom= new HashMap<>();
        	    	
        	});
        	
        	
        	/*for(Polygon p: polygons ) {
        		
        		int x[] = p.xpoints;
                int y[] = p.ypoints;
                
                for(int i=0; i< x.length;i++) {
                	if(x[i]!=0 &&  y[i] != 0){
                		coordinates.add(new CoordinateModel(x[i], y[i], i+1));
                	}
                	
                }
                
                //((Graphics) p).setColor(Color.BLUE);
                
        	}
        	
        	for(CoordinateModel cm : coordinates) {
        		System.out.println("X"+ cm.getX());
        		System.out.println("Y"+ cm.getY());
        		System.out.println("------------------------");
        	}
       	 
       }*/

    }
    }

    public static void initUI() throws IOException {
    	
        JFrame frame = new JFrame("test");
		((JFrame) frame).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Drawing drawing = new Drawing();
		//drawing.text.setHorizontalAlignment(SwingConstants.CENTER);
		//drawing.text.setBounds(380, 550, 150, 30);
        frame.getContentPane().add(drawing, BorderLayout.NORTH);
        drawing.setLayout(null);

        BufferedImage myPicture = null;
		try {
			
			//marcelin
			//myPicture = ImageIO.read(new File("/Users/marcelin/Downloads/271711240_455594239433212_4352364266861532517_n.png"));
			
			//XXX zara
			//ETAGE -1
			//myPicture = ImageIO.read(new File("/Users/zaramarks/ETAGE-1.png"));
			//ETAGE O 
			//myPicture = ImageIO.read(new File("/Users/zaramarks/ETAGE0.png"));
			//etage 1
			//myPicture = ImageIO.read(new File("/Users/zaramarks/ETAGE1.png"));
			//etage 2
			//myPicture = ImageIO.read(new File("/Users/zaramarks/ETAGE2.png"));
			//etage 3
			//myPicture = ImageIO.read(new File("/Users/zaramarks/ETAGE3.png"));
			//etage 4
			myPicture = ImageIO.read(new File("/Users/zaramarks/ETAGE4.png"));
			
		} catch(IOException e) {
            e.printStackTrace();
		}
        Dimension dim = new Dimension(myPicture.getWidth()/2 + 14, myPicture.getHeight()/2+100);

		frame.setMinimumSize(dim);
        frame.setMaximumSize(dim);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
					initUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
    
    
}
