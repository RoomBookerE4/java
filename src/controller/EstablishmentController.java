package controller;

import dao.DaoFactory;
import dao.EstablishmentDao;
import model.EstablishmentModel;

public class EstablishmentController {
	
	
	public EstablishmentModel searchById(int id ) {
		
		DaoFactory daoFactory = DaoFactory.getInstance();
		
		return daoFactory.getEstablishmentDao().searchById(id);
	}

}
