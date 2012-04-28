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
	private GastDao gastDao;
	private ZimmerDao zimmerDao;
	private BuchungDao buchungDao;
	private ReservierungDao reservierungDao;
	private TagStatusDao tagStatusDao;
	private ZeitraumStatusDao zeitraumStatusDao;

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
	
	
	public List<TagStatus> getAllTagStatus() {
		return tagStatusDao.getAll();
	}
	
	public void add(TagStatus tagStatus) {
		tagStatusDao.saveOrUpdate(tagStatus);
	}

	public void update(TagStatus tagStatus) {
		tagStatusDao.saveOrUpdate(tagStatus);
	}
	
	public void delete(TagStatus tagStatus) {
		tagStatusDao.delete(tagStatus);
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
