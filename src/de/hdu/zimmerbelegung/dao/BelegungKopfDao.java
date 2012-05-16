package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.BelegungKopf;

public abstract class BelegungKopfDao extends HibernateDaoSupport {
	public BelegungKopf get(int id) {
		return getHibernateTemplate().load(BelegungKopf.class, id);
	}
	
	public void saveOrUpdate(BelegungKopf belegungKopf) {
		getHibernateTemplate().saveOrUpdate(belegungKopf);
	}

	public void delete(BelegungKopf belegungKopf) {
		getHibernateTemplate().delete(belegungKopf);
	}

	@SuppressWarnings("unchecked")
	public List<BelegungKopf> getAll() {
		return getHibernateTemplate().find("FROM BelegungKopf");
	}
}