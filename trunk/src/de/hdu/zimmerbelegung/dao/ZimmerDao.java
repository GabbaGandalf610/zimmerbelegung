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
