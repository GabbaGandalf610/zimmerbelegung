/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.dao;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.helper.BelegungArt;
import de.hdu.zimmerbelegung.model.Belegung;
import de.hdu.zimmerbelegung.model.BelegungKopf;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.Zimmer;
import de.hdu.zimmerbelegung.service.ServiceLocator;

/**
 * The Data access class for BelegungKopf. All Interaction with the database
 * regarding the entity bean BelegungKopf should be handled by this class!
 * 
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 */
public class BelegungKopfDao extends HibernateDaoSupport {

	/**
	 * Creates a BelegungKopf to the database.
	 * 
	 * @param art
	 *            the type of a Belegung
	 * @see Zimmer
	 */
	public void create(BelegungArt art, LocalDate datumVon, LocalDate datumBis,
			Zimmer zimmer, Gast gast) {
		BelegungKopf belegungKopf = new BelegungKopf(gast);
		List<Belegung> belegungen = new ArrayList<Belegung>();
		for (LocalDate datumTmp = datumVon; datumTmp.isBefore(datumBis)
				|| datumTmp.equals(datumBis); datumTmp = datumTmp.plusDays(1)) {
			Belegung belegung = new Belegung(art, datumTmp, zimmer, gast,
					belegungKopf);
			belegungen.add(belegung);
		}
		belegungKopf.setBelegungen(belegungen);
		// BelegungKopfDao belegungKopfDao =
		// ServiceLocator.getBelegungKopfDao();
		this.saveOrUpdate(belegungKopf);
	}

	/**
	 * Deletes the specified {@link BelegungKopf} object from the database.
	 * 
	 * @param belegungKopf
	 *            the {@link BelegungKopf} to be deleted.
	 */
	public void delete(BelegungKopf belegungKopf) {
		getHibernateTemplate().delete(belegungKopf);
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
		getHibernateTemplate().refresh(belegungKopf.getGast());
		getHibernateTemplate().refresh(belegungKopf.getZimmer());
	}

	/**
	 * Returns a single BelegungKopf by its primary db key
	 * 
	 * @param id
	 *            the primary key of a {@link BelegungKopf}
	 * @return a single BelegungKopf
	 */
	public BelegungKopf get(int id) {
		return getHibernateTemplate().load(BelegungKopf.class, id);
	}

	/**
	 * Returns all BelegungKopf from the database.
	 * 
	 * @return a list of {@link BelegungKopf}
	 * @see BelegungKopf
	 */
	@SuppressWarnings("unchecked")
	public List<BelegungKopf> getAll() {
		return getHibernateTemplate().find("FROM BelegungKopf");
	}

	/**
	 * Saves the {@link BelegungKopf} specified by the parameter in the
	 * database.
	 * 
	 * @param zimmer
	 *            a {@link BelegungKopf} object that should be saved in the db.
	 */
	public void saveOrUpdate(BelegungKopf belegungKopf) {
		getHibernateTemplate().saveOrUpdate(belegungKopf);
		getHibernateTemplate().refresh(belegungKopf);
	}

	public void setArt(BelegungKopf belegungKopf, BelegungArt belegungArt)
			throws Exception {
		List<Belegung> belegungen = belegungKopf.getBelegungen();
		for (Belegung belegung : belegungen) {
			belegung.setArt(belegungArt);
		}
		this.saveOrUpdate(belegungKopf);
	}

	public void storno(BelegungKopf belegungKopf) throws Exception {
		List<Belegung> belegungen = belegungKopf.getBelegungen();
		BelegungDao belegungDao = ServiceLocator.getBelegungDao();
		for (Belegung belegung : belegungen) {
			belegungDao.delete(belegung);
		}
		this.delete(belegungKopf);
	}
}