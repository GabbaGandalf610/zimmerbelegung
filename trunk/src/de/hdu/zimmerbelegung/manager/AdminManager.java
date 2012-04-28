package de.hdu.zimmerbelegung.manager;

import java.util.List;

import de.hdu.zimmerbelegung.dao.BuchungDao;
import de.hdu.zimmerbelegung.dao.GastDao;
import de.hdu.zimmerbelegung.dao.ReservierungDao;
import de.hdu.zimmerbelegung.dao.TagStatusDao;
import de.hdu.zimmerbelegung.dao.ZeitraumStatusDao;
import de.hdu.zimmerbelegung.dao.ZimmerDao;
import de.hdu.zimmerbelegung.model.Buchung;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.Reservierung;
import de.hdu.zimmerbelegung.model.TagStatus;
import de.hdu.zimmerbelegung.model.ZeitraumStatus;
import de.hdu.zimmerbelegung.model.Zimmer;

public class AdminManager {
	
	private ZimmerDao zimmerDao;
	private BuchungDao buchungDao;
	private ReservierungDao reservierungDao;
	
	

	
		

	public List<Zimmer> getAllZimmer() {
		return zimmerDao.getAll();
	}
	
	public void add(Zimmer zimmer) {
		zimmerDao.saveOrUpdate(zimmer);
	}

	public void update(Zimmer zimmer) {
		zimmerDao.saveOrUpdate(zimmer);
	}
	
	public void delete(Zimmer zimmer) {
		zimmerDao.delete(zimmer);
	}
	

	public List<Buchung> getAllBuchung() {
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
	
	
	public List<Reservierung> getAllReservierung() {
		return reservierungDao.getAll();
	}
	
	public void add(Reservierung reservierung) {
		reservierungDao.saveOrUpdate(reservierung);
	}

	public void update(Reservierung reservierung) {
		reservierungDao.saveOrUpdate(reservierung);
	}
	
	public void delete(Reservierung reservierung) {
		reservierungDao.delete(reservierung);
	}
	
	
	
	


	
}
