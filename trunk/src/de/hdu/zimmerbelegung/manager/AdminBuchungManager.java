package de.hdu.zimmerbelegung.manager;

import java.util.List;

import de.hdu.zimmerbelegung.dao.BuchungDao;
import de.hdu.zimmerbelegung.model.Buchung;

public class AdminBuchungManager {
	private BuchungDao buchungDao;

	public void setBuchungDao(BuchungDao buchungDao) {
		this.buchungDao = buchungDao;
	}
	
	public List<Buchung> getAll() {
		return buchungDao.getAll();
	}

	public void add(Buchung buchung) {
		buchungDao.saveOrUpdate(buchung);
	}

	public void update(Buchung buchung) {
		buchungDao.saveOrUpdate(buchung);
	}

	public void delete(Buchung buchung) {
		buchungDao.delete(buchung);
	}
}
