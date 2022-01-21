package controller;

import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.DaoFactory;
import dao.EstablishmentDao;
import model.EstablishmentModel;

public class EstablishmentController {
	
	
	public  JTextField nameField;

	public  JTextField addressField;

	public  JTextField timeOpenField;
	
	public  JTextField timeCloseField;
	
	DaoFactory daoFactory = DaoFactory.getInstance();
	
	public EstablishmentController() {
	}
	
	

	public EstablishmentController(JTextField nameField, JTextField addressField, JTextField timeOpenField,
			JTextField timeCloseField) {
		super();
		this.nameField = nameField;
		this.addressField = addressField;
		this.timeOpenField = timeOpenField;
		this.timeCloseField = timeCloseField;
	}



	public EstablishmentModel searchById(int id ) {
		
		return daoFactory.getEstablishmentDao().searchById(id);
	}

	public boolean createEstablishment () {

		EstablishmentModel establishment = new EstablishmentModel(nameField.getText().toString(), addressField.getText().toString(), 
				timeOpenField.getText().toString(), timeCloseField.getText().toString());
		
		 return daoFactory.getEstablishmentDao().add(establishment);
	}
	
	public boolean editEstablishment(int id) {
		
		EstablishmentModel establishment = new EstablishmentModel(nameField.getText().toString(), addressField.getText().toString(), 
				timeOpenField.getText().toString(), timeCloseField.getText().toString());
		
		
		return  daoFactory.getEstablishmentDao().editEstbalishment(id, establishment);
	}
	
}
