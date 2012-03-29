/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Franz Wagner, Roland Kühnel.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.dao;

import java.util.List;

import de.hdu.zimmerbelegung.model.Buchung;
import de.hdu.zimmerbelegung.model.Datum;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.Zimmer;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * DAO für {@link Buchung}. Alle Datenbankzugriffe für "Buchung" werden von
 * dieser Klasse verwaltet.
 */
public class BuchungDao extends HibernateDaoSupport {

	private BuchungDao buchungDao;

	public void setBuchungDao(BuchungDao buchungDao) {
		this.buchungDao = buchungDao;
	}

	/**
	 * Erstellt eine neue Buchung
	 * 
	 * @param datumVon
	 *            Anfangsdatum der Buchung
	 * @param datumBis
	 *            Enddatum der Buchung
	 * @param zimmer
	 *            Zu bebuchendes Zimmer
	 * @param gast
	 *            Buchender Gast
	 * @param kommentar
	 *            Kommentar zur Buchung
	 */
	public void create(Datum datumVon, Datum datumBis, Zimmer zimmer,
			Gast gast, String kommentar) {
		Buchung b = new Buchung(datumVon, datumBis, zimmer, gast, kommentar);
		save(b);
	}

	/**
	 * Rückkgabe einer bestimmten Buchung
	 * 
	 * @param id
	 *            Schlüssel der Buchung
	 * @return Eine Buchung
	 */
	public Buchung findById(int id) {
		HibernateTemplate template = getHibernateTemplate();
		return (Buchung) template.get(Buchung.class, id);
	}

	/**
	 * Rückgabe aller Buchungen
	 * 
	 * @return Eine Liste aller Buchungen
	 */
	public List<Buchung> findAll() {
		HibernateTemplate template = getHibernateTemplate();
		return template.loadAll(Buchung.class);
	}

	/**
	 * Speichert die angegebene Buchung
	 * 
	 * @param buchung
	 *            Buchung, die in die Datenbank gespeichert werden soll
	 * @return Die gespeicherte Buchung
	 */
	public Buchung save(Buchung buchung) {
		HibernateTemplate template = getHibernateTemplate();
		template.saveOrUpdate(buchung);
		return buchung;
	}

	/**
	 * Löscht die angegebene Buchung
	 * 
	 * @param buchung
	 *            Buchung, die aus der Datenbank gelöscht werden soll
	 */
	public void delete(Buchung buchung) {
		HibernateTemplate template = getHibernateTemplate();
		template.delete(buchung);
	}
}
