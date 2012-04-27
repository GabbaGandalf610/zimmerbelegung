package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.Reservierung;

public class ReservierungDao extends HibernateDaoSupport {
	public Reservierung get(int id) {
		return getHibernateTemplate().load(Reservierung.class, id);
	}

	public void saveOrUpdate(Reservierung reservierung) {
		getHibernateTemplate().saveOrUpdate(reservierung);
	}

	public void delete(Reservierung reservierung) {
		getHibernateTemplate().delete(reservierung);
	}

	@SuppressWarnings("unchecked")
	public List<Reservierung> getAll() {
		return getHibernateTemplate().find("FROM Reservierung");
	}
}