package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.ZeitraumStatus;

public class ZeitraumStatusDao extends HibernateDaoSupport {
	public ZeitraumStatus get(int id) {
		return getHibernateTemplate().load(ZeitraumStatus.class, id);
	}

	public void saveOrUpdate(ZeitraumStatus zeitraumStatus) {
		getHibernateTemplate().saveOrUpdate(zeitraumStatus);
	}

	public void delete(ZeitraumStatus zeitraumStatus) {
		getHibernateTemplate().delete(zeitraumStatus);
	}

	@SuppressWarnings("unchecked")
	public List<ZeitraumStatus> getAll() {
		return getHibernateTemplate().find("FROM ZeitraumStatus");
	}
}
