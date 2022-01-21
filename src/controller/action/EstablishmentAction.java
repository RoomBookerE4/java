package controller.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DaoFactory;
import model.EstablishmentModel;
import model.UserModel;
import type.TypeAction;
import view.LoginView;

public class EstablishmentAction extends AbstractAction{

	public  JTextField nameField;

	public  JTextField addressField;

	public  JTextField timeOpenField;
	
	public  JTextField timeCloseField;
	
	public Integer id;
	
	public String action;
	
	public JFrame frame;
	
	private UserModel user;
	
	private DaoFactory daoFactory = DaoFactory.getInstance();
	
	
	private static final long serialVersionUID = 1L;

	
	
	public EstablishmentAction() {
	}



	public EstablishmentAction(JTextField nameField, JTextField addressField, JTextField timeOpenField,
			JTextField timeCloseField, Integer id, String action,  JFrame frame, UserModel user) {
		super();
		this.nameField = nameField;
		this.addressField = addressField;
		this.timeOpenField = timeOpenField;
		this.timeCloseField = timeCloseField;
		this.id = id;
		this.action = action;
		this.frame= frame;
		this.user = user;
	}
	


	public EstablishmentAction(Integer id) {
		super();
		this.id = id;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(action.equals(TypeAction.edit)) {
			EstablishmentModel establishment = new EstablishmentModel(nameField.getText().toString(), addressField.getText().toString(), 
					timeOpenField.getText().toString(), timeCloseField.getText().toString());
			
			
			
			if(daoFactory.getEstablishmentDao().editEstbalishment(id, establishment)) {
				JOptionPane.showConfirmDialog(new JPanel(), "Establishment edited!", "", JOptionPane.OK_OPTION);
			}else {
				JOptionPane.showConfirmDialog(new JPanel(), "Failed to edit establishemnt", "", JOptionPane.OK_OPTION);
			}
			
		}else if (action.equals(TypeAction.add)) {
			
			EstablishmentModel establishment = new EstablishmentModel(nameField.getText().toString(), addressField.getText().toString(), 
					timeOpenField.getText().toString(), timeCloseField.getText().toString());
			
			
			if( daoFactory.getEstablishmentDao().add(establishment)) {
			
				if(daoFactory.getUserDao().updatenNewEstablishment(user, nameField.getText().toString(), 
						addressField.getText().toString())) {
					
					this.frame.dispose();
					int result =  JOptionPane.showConfirmDialog(new JPanel(), "Establishment created! we will have to restart the application to save changes", "", JOptionPane.OK_OPTION);
					
					if(result == JOptionPane.OK_OPTION) {
						new LoginView();
					}
					
				}
			
			}else {
				JOptionPane.showConfirmDialog(new JPanel(), "Failed to create establishemnt", "", JOptionPane.OK_OPTION);
			}
			
			
		}else if (action.equals(TypeAction.remove)) {
			
		}
		
	}
	

	public EstablishmentModel searchById(int id ) {
		
		return daoFactory.getEstablishmentDao().searchById(id);
	}


}
