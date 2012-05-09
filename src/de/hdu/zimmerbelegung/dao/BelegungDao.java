package de.hdu.zimmerbelegung.dao;

import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.Belegung;

public class BelegungDao extends HibernateDaoSupport {
	public Belegung get(int id) {
		return getHibernateTemplate().load(Belegung.class, id);
	}

	public void saveOrUpdate(Belegung belegung) {
		getHibernateTemplate().saveOrUpdate(belegung);
	}

	public void delete(Belegung belegung) {
		getHibernateTemplate().delete(belegung);
	}

	@SuppressWarnings("unchecked")
	public List<Belegung> getAll() {
		return getHibernateTemplate().find("FROM Belegung");
	}

	@SuppressWarnings("unchecked")
	public List<Belegung> getAllInZeitraum(LocalDate vonDatum,
			LocalDate bisDatum) {
		Object[] params = new Object[] { vonDatum, bisDatum };
		// TODO
		// return getHibernateTemplate()
		// .find("from Belegung b inner join b.zimmer z where b.datum between ? and ? order by z.zimmernummer",
		// params);
		return getHibernateTemplate().find(
				"from Belegung where datum between ? and ?", params);
	}
}