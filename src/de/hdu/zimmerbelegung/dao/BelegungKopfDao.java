package de.hdu.zimmerbelegung.dao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.helper.BelegungArt;
import de.hdu.zimmerbelegung.model.Belegung;
import de.hdu.zimmerbelegung.model.BelegungKopf;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.Zimmer;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class BelegungKopfDao extends HibernateDaoSupport {
	public BelegungKopf get(int id) {
		return getHibernateTemplate().load(BelegungKopf.class, id);
	}
	
	public void saveOrUpdate(BelegungKopf belegungKopf) {
		getHibernateTemplate().saveOrUpdate(belegungKopf);
		getHibernateTemplate().refresh(belegungKopf);
	}

	public void delete(BelegungKopf belegungKopf) {
		getHibernateTemplate().delete(belegungKopf);
	}

	@SuppressWarnings("unchecked")
	public List<BelegungKopf> getAll() {
		return getHibernateTemplate().find("FROM BelegungKopf");
	}
	
	public void create(BelegungArt art, LocalDate datumVon, LocalDate datumBis, Zimmer zimmer, Gast gast) {
		BelegungKopf belegungKopf = new BelegungKopf(gast);
		List<Belegung> belegungen = new ArrayList<Belegung>();
		for (LocalDate datumTmp = datumVon; datumTmp.isBefore(datumBis)
				|| datumTmp.equals(datumBis); datumTmp = datumTmp
				.plusDays(1)) {
			Belegung belegung = new Belegung(art, datumTmp, zimmer, gast, belegungKopf);
			belegungen.add(belegung);
		}
		belegungKopf.setBelegungen(belegungen);
		BelegungKopfDao belegungKopfDao = ServiceLocator.getBelegungKopfDao();
		belegungKopfDao.saveOrUpdate(belegungKopf);
	}
}