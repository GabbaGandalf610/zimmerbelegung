package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.ZimmerKategorie;

public class ZimmerKategorieDao extends HibernateDaoSupport {
	public ZimmerKategorie get(int id) {
		return getHibernateTemplate().load(ZimmerKategorie.class, id);
	}
    public void saveOrUpdate(ZimmerKategorie zimmerKategorie) {
        getHibernateTemplate().saveOrUpdate(zimmerKategorie);
    }
	public void delete(ZimmerKategorie zimmerKategorie) {
		getHibernateTemplate().delete(zimmerKategorie);
	}
	@SuppressWarnings("unchecked")
	public List<ZimmerKategorie> getAll() {
		return getHibernateTemplate().find("FROM ZimmerKategorie");
	}
}
