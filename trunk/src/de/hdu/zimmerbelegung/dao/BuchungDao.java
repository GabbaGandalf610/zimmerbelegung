package de.hdu.zimmerbelegung.dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import de.hdu.zimmerbelegung.model.Buchung;

public class BuchungDao extends HibernateDaoSupport {
	public Buchung get(int id) {
		return getHibernateTemplate().load(Buchung.class, id);
	}

	public void save(Buchung buchung) {
		getHibernateTemplate().save(buchung);
	}

	@SuppressWarnings("unchecked")
	public List<Buchung> getAll() {
		return getHibernateTemplate().find("FROM Buchung");
	}
}
