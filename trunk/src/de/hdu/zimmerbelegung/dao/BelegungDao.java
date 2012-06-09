/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.Belegung;

/**
 * The Data access class for Belegung. All Interaction with the database
 * regarding the entity bean Belegung should be handled by this class!
 * 
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 */
public class BelegungDao extends HibernateDaoSupport {
	public void delete(Belegung belegung) {
		getHibernateTemplate().delete(belegung);
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
	}

	/**
	 * Deletes all {@link Blegung} objects from the database.
	 */
	public void deleteAll() {
		List<Belegung> myBelegungList = this.getAll();
		for (Belegung tempBelegung : myBelegungList) {
			getHibernateTemplate().delete(tempBelegung);
		}
	}

	/**
	 * Returns a single Belegung by its primary db key
	 * 
	 * @param id
	 *            the primary key of a {@link Belegung}
	 * @return a single Belegung
	 */
	public Belegung get(int id) {
		return getHibernateTemplate().load(Belegung.class, id);
	}

	/**
	 * Returns all Belegung from the database.
	 * 
	 * @return a list of {@link Belegung}
	 * @see Belegung
	 */
	@SuppressWarnings("unchecked")
	public List<Belegung> getAll() {
		return getHibernateTemplate().find("FROM Belegung");
	}

	/**
	 * Returns all Belegung from the database in a specified timeline.
	 * 
	 * @return a list of {@link Belegung}
	 * @see Belegung
	 */
	@SuppressWarnings("unchecked")
	public List<Belegung> getAllInZeitraum(LocalDate vonDatum,
			LocalDate bisDatum) {
		Object[] params = new Object[] { vonDatum, bisDatum };
		return getHibernateTemplate()
				.find("select b from Belegung b inner join b.zimmer z where b.datum between ? and ? order by z.zimmernummer",
						params);
	}
	
	/**
	 * Saves the {@link Belegung} specified by the parameter in the database.
	 * 
	 * @param belegung
	 *            a {@link Belegung} object that should be saved in the db.
	 */
	public void save(Belegung belegung) {
		getHibernateTemplate().saveOrUpdate(belegung);
		// getHibernateTemplate().flush();
		// getHibernateTemplate().refresh(belegung);
	}

}