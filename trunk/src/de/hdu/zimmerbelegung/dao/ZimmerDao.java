package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.Zimmer;

public class ZimmerDao extends HibernateDaoSupport {
	public Zimmer getbyId(int id) {
		return getHibernateTemplate().load(Zimmer.class, id);
	}

	public void saveOrUpdate(Zimmer zimmer) {
		getHibernateTemplate().saveOrUpdate(zimmer);
		getHibernateTemplate().refresh(zimmer);
	}

	public void delete(Zimmer zimmer) {
		getHibernateTemplate().delete(zimmer);
	}

	@SuppressWarnings("unchecked")
	public List<Zimmer> getAll() {
		return getHibernateTemplate().find("from Zimmer order by zimmernummer");
	}
	
	public void deleteAll() {
		List<Zimmer> myZimmerList = this.getAll();
		for (Zimmer tempZimmer : myZimmerList) {
			getHibernateTemplate().delete(tempZimmer);	
		}
	}
}
