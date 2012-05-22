package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.Belegung;
import de.hdu.zimmerbelegung.model.Gast;

public class BelegungDao extends HibernateDaoSupport {
	public Belegung get(int id) {
		return getHibernateTemplate().load(Belegung.class, id);
	}

	public void save(Belegung belegung) {
		getHibernateTemplate().save(belegung);
	}

	@SuppressWarnings("unchecked")
	public List<Belegung> getAll() {
		return getHibernateTemplate().find("FROM Belegung");
	}

	@SuppressWarnings("unchecked")
	public List<Belegung> getAllInZeitraum(LocalDate vonDatum,
			LocalDate bisDatum) {
		Object[] params = new Object[] { vonDatum, bisDatum };
		return getHibernateTemplate()
				.find("select b from Belegung b inner join b.zimmer z where b.datum between ? and ? order by z.zimmernummer",
						params);
	}
	
	public void deleteAll() {
		List<Belegung> myBelegungList = this.getAll();
		for (Belegung tempBelegung : myBelegungList) {
			getHibernateTemplate().delete(tempBelegung);	
		}
	}
}