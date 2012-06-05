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

import de.hdu.zimmerbelegung.model.Zimmer;

/**
 * The Data access class for rooms. All Interaction with the database regarding
 * the entity bean Zimmer should be handled by this class!
 * 
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 */
public class ZimmerDao extends HibernateDaoSupport {

	/**
	 * Deletes the specified {@link Zimmer} object from the database.
	 * 
	 * @param zimmer
	 *            the {@link Zimmer} to be deleted.
	 */
	public void delete(Zimmer zimmer) {
		getHibernateTemplate().delete(zimmer);
	}

	/**
	 * Deletes all {@link Zimmer} objects from the database.
	 */
	public void deleteAll() {
		List<Zimmer> myZimmerList = this.getAll();
		for (Zimmer tempZimmer : myZimmerList) {
			getHibernateTemplate().delete(tempZimmer);
		}
	}

	/**
	 * Returns all rooms from the database.
	 * 
	 * @return a list of {@link Zimmer}
	 * @see Zimmer
	 */
	@SuppressWarnings("unchecked")
	public List<Zimmer> getAll() {
		return getHibernateTemplate().find("from Zimmer order by zimmernummer");
	}

	/**
	 * Returns a single room by its primary db key
	 * 
	 * @param id
	 *            the primary key of a {@link Zimmer}
	 * @return a single Zimmer
	 */
	public Zimmer getbyId(int id) {
		return getHibernateTemplate().load(Zimmer.class, id);
	}

	/**
	 * Saves the {@link Zimmer} specified by the parameter in the database.
	 * 
	 * @param zimmer
	 *            a {@link Zimmer} object that should be saved in the db.
	 */
	public void saveOrUpdate(Zimmer zimmer) {
		getHibernateTemplate().saveOrUpdate(zimmer);
		getHibernateTemplate().refresh(zimmer);
	}
}
