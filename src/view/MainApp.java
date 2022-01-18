package view;

import model.UserModel;

public class MainApp {

	public MainApp() {
		launchApp();
	}
	
	
	public void launchApp() {
		//LANCER AVEC LOGIN
		//new LoginView();
		
		//email : zara@gmail.com
		//pass: zara123
		
		//LANCER SANS LE LOGIN
		new MenuView(new UserModel());
	}
	
	 public static void main(String[] args){
	      javax.swing.SwingUtilities.invokeLater(new Runnable(){
	         @Override
	         public void run(){
	            new MainApp();
	         }
	      });
	   }
}
