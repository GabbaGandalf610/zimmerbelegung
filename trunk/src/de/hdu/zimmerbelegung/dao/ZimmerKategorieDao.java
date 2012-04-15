package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.ZimmerKategorie;

public class ZimmerKategorieDao extends HibernateDaoSupport {
	public void create(String name) {
		ZimmerKategorie zk = new ZimmerKategorie();
		zk.setName(name);
		save(zk);
	}
	public ZimmerKategorie findById(int id) {
		HibernateTemplate template = getHibernateTemplate();
		return (ZimmerKategorie) template.get(ZimmerKategorie.class, id);
	}

	public ZimmerKategorie save(ZimmerKategorie zimmerKategorie) {
		HibernateTemplate template = getHibernateTemplate();
		template.saveOrUpdate(zimmerKategorie);
		return zimmerKategorie;
	}
	public void delete(ZimmerKategorie person) {
		HibernateTemplate template = getHibernateTemplate();
		template.delete(person);
	}
	public List<ZimmerKategorie> findAll() {
		HibernateTemplate template = getHibernateTemplate();
		List results = template.loadAll(ZimmerKategorie.class);
		return results;
	}
}
