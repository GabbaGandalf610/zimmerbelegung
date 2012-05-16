package de.hdu.zimmerbelegung.dto;

import org.joda.time.LocalDate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.dao.BuchungDao;
import de.hdu.zimmerbelegung.dao.BuchungKopfDao;
import de.hdu.zimmerbelegung.model.Buchung;
import de.hdu.zimmerbelegung.model.BuchungKopf;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.Zimmer;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class BuchungDto extends HibernateDaoSupport {
	public void create(LocalDate vonDatum, LocalDate bisDatum, Zimmer zimmer, Gast gast) {
		BuchungKopfDao buchungKopfDao = ServiceLocator.getBuchungKopfDao();
		BuchungDao buchungDao = ServiceLocator.getBuchungDao();
		
		BuchungKopf buchungKopf = new BuchungKopf();
		buchungKopfDao.saveOrUpdate(buchungKopf);
		
		for (LocalDate tmpDatum = vonDatum; tmpDatum.isBefore(bisDatum)
				|| tmpDatum.equals(bisDatum); tmpDatum = tmpDatum
				.plusDays(1)) {
			Buchung buchung = new Buchung(tmpDatum, zimmer, gast, buchungKopf);
			buchungDao.saveOrUpdate(buchung);
		}	
	}
}
