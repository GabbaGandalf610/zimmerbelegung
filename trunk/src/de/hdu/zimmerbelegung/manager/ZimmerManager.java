package de.hdu.zimmerbelegung.manager;

import java.util.List;

import de.hdu.zimmerbelegung.dao.ZimmerDao;
import de.hdu.zimmerbelegung.model.Zimmer;

public class ZimmerManager {
	private ZimmerDao zimmerDao;

	public void setZimmerDao(ZimmerDao zimmerDao) {
		this.zimmerDao = zimmerDao;
	}

	public List<Zimmer> getAllZimmer() {
		return zimmerDao.getAll();
	}

	public void update(Zimmer zimmer) {
		zimmerDao.saveOrUpdate(zimmer);
	}
	
	public void delete(Zimmer zimmer) {
		zimmerDao.delete(zimmer);
	}
}
