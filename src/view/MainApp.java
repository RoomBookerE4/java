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
		//String name, String surname, String role, String email, Integer establishment
		new MenuView(new UserModel("zara ","marks", "administrator", "zara@gmail.com", 1));
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
