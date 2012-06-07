/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.Gast;

/**
 * The Data access class for guests. All Interaction with the database regarding
 * the entity bean Gast should be handled by this class!
 * 
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 */
public class GastDao extends HibernateDaoSupport {

	/**
	 * Deletes the specified {@link Gast} object from the database.
	 * 
	 * @param boot
	 *            the {@link Gast} to be deleted.
	 */
	public void delete(Gast gast) {
		getHibernateTemplate().delete(gast);
	}

	/**
	 * Deletes all guests from the database table
	 */
	public void deleteAll() {
		List<Gast> myGastList = this.getAll();
		for (Gast tempGast : myGastList) {
			getHibernateTemplate().delete(tempGast);
		}
	}

	/**
	 * Returns a list of all {@link Gast}
	 * 
	 * @return a list of all Gast
	 */
	@SuppressWarnings("unchecked")
	public List<Gast> getAll() {
		return getHibernateTemplate().find("FROM Gast ORDER BY NAME");
	}

	/**
	 * Returns a list of Gast where name, vorname, firma, ort or land matches
	 * the search string
	 * 
	 * @param search
	 *            string which is compared with name, vorname, firma, ort or
	 *            land
	 * @return a list of Gast
	 */
	@SuppressWarnings("unchecked")
	public List<Gast> getAllFilteredUser(String gastSuche) {
		// Object[] params = new Object[] { gastSuche };
		if (gastSuche == "" || gastSuche == null) {
			return getHibernateTemplate().find("FROM Gast ORDER BY NAME");
		} else {
			return getHibernateTemplate()
					.find("select g from Gast g where g.name like ? or g.vorname like ? or g.firma like ? or g.ort like ? or g.land like ? ORDER BY NAME",
							gastSuche + "%", gastSuche + "%",
							"%" + gastSuche + "%", gastSuche + "%",
							gastSuche + "%");
		}
	}

	/**
	 * Returns a single Gast by its primary db key
	 * 
	 * @param id
	 *            the primary key of a {@link Gast}
	 * @return a single Gast
	 */
	public Gast getbyId(int id) {
		return getHibernateTemplate().load(Gast.class, id);
	}

	/**
	 * Saves a single guest in the db
	 * 
	 * @param object
	 *            of type {@link Gast}
	 */
	public void save(Gast gast) {
		getHibernateTemplate().save(gast);
	}

	/**
	 * Saves the {@link Gast} specified by the parameter in the database.
	 * 
	 * @param gast
	 *            a {@link Gast} object that should be saved in the db.
	 */
	public void saveOrUpdate(Gast gast) {
		getHibernateTemplate().saveOrUpdate(gast);
		getHibernateTemplate().refresh(gast);
	}

	/**
	 * Updates a specified object of type {@link Gast} in the database
	 * 
	 * @param object
	 *            of type {@link Gast}
	 * @return a single Gast
	 */
	public void update(Gast gast) {
		getHibernateTemplate().update(gast);
	}

	/**
	 * Refresh a specified object of type {@link Gast} in the database
	 * 
	 * @param object
	 *            of type {@link Gast}
	 * @return a single Gast
	 */

	public void refresh(Gast gast) {
		getHibernateTemplate().refresh(gast);
	}

}
