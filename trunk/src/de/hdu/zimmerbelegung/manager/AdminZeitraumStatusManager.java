package de.hdu.zimmerbelegung.manager;

import java.util.List;

import de.hdu.zimmerbelegung.dao.ZeitraumStatusDao;
import de.hdu.zimmerbelegung.model.ZeitraumStatus;

public class AdminZeitraumStatusManager {

	private ZeitraumStatusDao zeitraumStatusDao;
	
	public ZeitraumStatusDao getZeitraumStatusDao() {
		return zeitraumStatusDao;
	}

	public void setZeitraumStatusDao(ZeitraumStatusDao zeitraumStatusDao) {
		this.zeitraumStatusDao = zeitraumStatusDao;
	}

	public List<ZeitraumStatus> getAllZeitraumStatus() {
		return zeitraumStatusDao.getAll();
	}
	
	public void add(ZeitraumStatus zeitraumStatus) {
		zeitraumStatusDao.saveOrUpdate(zeitraumStatus);
	}

	public void update(ZeitraumStatus zeitraumStatus) {
		zeitraumStatusDao.saveOrUpdate(zeitraumStatus);
	}
	
	public void delete(ZeitraumStatus zeitraumStatus) {
		zeitraumStatusDao.delete(zeitraumStatus);
	}
	
}
