package controller;


import dao.DaoFactory;
import model.EstablishmentModel;

public class EstablishmentController {
	
	
	DaoFactory daoFactory = DaoFactory.getInstance();
	
	public EstablishmentController() {}
	
	
	public EstablishmentModel searchById(int id ) {
		
		return daoFactory.getEstablishmentDao().searchById(id);
	}


}
