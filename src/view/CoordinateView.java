package view;

import java.awt.Font;
import java.awt.Polygon;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import model.CoordinateModel;


public class CoordinateView {
	
	private static final int PANEL_WIDTH = 600;
    private static final int PANEL_HEIGHT = 600;
    
    public static class Drawing extends JPanel {

        private static final Font FONT = new Font("Arial", Font.PLAIN, 12);

        //rivate List<Polygon> polygons = new ArrayList<Polygon>();
        
        private HashMap< String, Polygon> polygonRoom= new HashMap<>();

        private Polygon currentPolygon = new Polygon();
        
        public static JTextField text;
        
      
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

        public Drawing() {
            addMouseListener(mouseListener);
            JButton button = new JButton("Insert database"); 
            
            
            button.setBounds(50, 50, 50, 50);
            button.addActionListener((ActionListener) new ActionListener(){  
           
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				addAll();
    				
    			}  
            });  
            
             text = new JTextField("room name");
            
            
            JButton button2 = new JButton("Create"); 
            
            
           // button2.setBounds(150, 50, 50, 50);
            button2.addActionListener((ActionListener) new ActionListener(){  
           
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				createPolygon();
    				
    				
    			}  
            });  
            
            
            this.add(button);
            this.add(text);
            this.add(button2);
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
                polygonRoom.put(text.getText(), currentPolygon);
            }
            // key value
           
           text.setText("");
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
        	
        	List<CoordinateModel> coordinates = new ArrayList<>();
        	
        		polygonRoom.forEach((k, p) -> {
        	    //System.out.format("key: %s, value: %d%n", k, v);
        	    
        	    	int x[] = p.xpoints;
                    int y[] = p.ypoints;
                    
                    for(int i=0; i< x.length;i++) {
                    	//if(x[i]!=0 &&  y[i] != 0){
                    		coordinates.add(new CoordinateModel(x[i], y[i], i+1, k));
                    		System.out.println("X "+ x[i]);
                    		System.out.println("Y "+ y[i]);
                    		System.out.println("ORDER "+ (i+1));
                    		System.out.println("ROOM "+ k);
                    		System.out.println("------------------------");
                    	}
                    	
                    //}
        	    	
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

    protected static void initUI() {
    	
        JFrame frame = new JFrame("test");
        
        
        
        frame.add(new Drawing());
        
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                initUI();
            }
        });
    }
    
    
}
