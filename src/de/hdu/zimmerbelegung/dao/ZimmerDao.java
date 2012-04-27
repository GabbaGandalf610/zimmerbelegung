package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.Zimmer;

public class ZimmerDao extends HibernateDaoSupport {
	public Zimmer get(int id) {
		return getHibernateTemplate().load(Zimmer.class, id);
	}

	public void saveOrUpdate(Zimmer zimmer) {
		getHibernateTemplate().saveOrUpdate(zimmer);
	}

	public void delete(Zimmer zimmer) {
		getHibernateTemplate().delete(zimmer);
	}

	@SuppressWarnings("unchecked")
	public List<Zimmer> getAll() {
		return getHibernateTemplate().find("FROM Zimmer");
	}
}
