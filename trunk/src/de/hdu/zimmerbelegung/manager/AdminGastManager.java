package de.hdu.zimmerbelegung.manager;

import java.util.List;

import de.hdu.zimmerbelegung.dao.GastDao;
import de.hdu.zimmerbelegung.model.Gast;

public class AdminGastManager {

	private GastDao gastDao;
	
	public GastDao getGastDao() {
		return gastDao;
	}

	public void setGastDao(GastDao gastDao) {
		this.gastDao = gastDao;
	}

	public List<Gast> getAllGast() {
		return gastDao.getAll();
	}
	
	public void add(Gast gast) {
		gastDao.saveOrUpdate(gast);
	}
	
	public void update(Gast gast) {
		gastDao.saveOrUpdate(gast);
	}
	
	public void delete(Gast gast) {
		gastDao.delete(gast);
	}
	
}
