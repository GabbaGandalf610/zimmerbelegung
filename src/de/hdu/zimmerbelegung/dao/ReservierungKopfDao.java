package de.hdu.zimmerbelegung.dao;


import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.ReservierungKopf;

public class ReservierungKopfDao extends HibernateDaoSupport {
	public ReservierungKopf get(int id) {
		return getHibernateTemplate().load(ReservierungKopf.class, id);
	}
	
	public void saveOrUpdate(ReservierungKopf reservierungKopf) {
		getHibernateTemplate().saveOrUpdate(reservierungKopf);
	}

	public void delete(ReservierungKopf reservierungKopf) {
		getHibernateTemplate().delete(reservierungKopf);
	}

	@SuppressWarnings("unchecked")
	public List<ReservierungKopf> getAll() {
		return getHibernateTemplate().find("FROM BelegungKopf");
	}
}