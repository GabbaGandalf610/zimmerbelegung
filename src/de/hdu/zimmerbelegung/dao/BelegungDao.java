package de.hdu.zimmerbelegung.dao;

import java.util.List;

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
}
