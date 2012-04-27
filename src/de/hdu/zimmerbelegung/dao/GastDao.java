package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.Gast;

public class GastDao extends HibernateDaoSupport {
	public Gast get(int id) {
		return getHibernateTemplate().load(Gast.class, id);
	}

	public void saveOrUpdate(Gast gast) {
		getHibernateTemplate().saveOrUpdate(gast);
	}

	public void delete(Gast gast) {
		getHibernateTemplate().delete(gast);
	}

	@SuppressWarnings("unchecked")
	public List<Gast> getAll() {
		return getHibernateTemplate().find("FROM Gast");
	}
}
