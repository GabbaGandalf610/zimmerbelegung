package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.Gast;

public class GastDao extends HibernateDaoSupport {
	public Gast getbyId(int id) {
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
					.find("select g from Gast g where g.name like ? or g.vorname like ? or g.firma like ? or g.ort like ? or g.land like ?",
							gastSuche + "%", gastSuche + "%", "%" + gastSuche + "%",
							gastSuche + "%", gastSuche + "%");
		}
	}
	
	public void deleteAll() {
		List<Gast> myGastList = this.getAll();
		for (Gast tempGast : myGastList) {
			getHibernateTemplate().delete(tempGast);	
		}
	}
	
	public void save(Gast gast) {
		getHibernateTemplate().save(gast);
	} 
	
	public void update(Gast gast) {
		getHibernateTemplate().update(gast);
	}

}
