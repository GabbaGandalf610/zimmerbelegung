package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.BuchungKopf;

public class BuchungKopfDao extends HibernateDaoSupport {
	public BuchungKopf get(int id) {
		return getHibernateTemplate().load(BuchungKopf.class, id);
	}
	
	public void saveOrUpdate(BuchungKopf buchungKopf) {
		getHibernateTemplate().saveOrUpdate(buchungKopf);
	}

	public void delete(BuchungKopf buchungKopf) {
		getHibernateTemplate().delete(buchungKopf);
	}

	@SuppressWarnings("unchecked")
	public List<BuchungKopf> getAll() {
		return getHibernateTemplate().find("FROM BelegungKopf");
	}
}