package de.hdu.zimmerbelegung.dao;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import de.hdu.zimmerbelegung.model.Reservierung;

public class ReservierungDao extends HibernateDaoSupport {
	public Reservierung get(int id) {
		return getHibernateTemplate().load(Reservierung.class, id);
	}

	public void save(Reservierung reservierung) {
		getHibernateTemplate().save(reservierung);
	}

	@SuppressWarnings("unchecked")
	public List<Reservierung> getAll() {
		return getHibernateTemplate().find("FROM Reservierung");
	}
}