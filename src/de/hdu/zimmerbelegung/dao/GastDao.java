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

	@SuppressWarnings("unchecked")
	public List<Gast> getAllFilteredUser(String gastSuche) {
		// Object[] params = new Object[] { gastSuche };
		if (gastSuche == "" || gastSuche == null) {
			return getHibernateTemplate().find("FROM Gast");
		} else {
			return getHibernateTemplate()
					.find("select g from Gast g where g.name like ? or g.vorname like ? or g.ort like ? or g.land like ?",
							gastSuche + "%", gastSuche + "%", gastSuche + "%",
							gastSuche + "%");
		}
	}

}