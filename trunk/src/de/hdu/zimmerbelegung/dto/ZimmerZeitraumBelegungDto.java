/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.dto;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.dao.BelegungDao;
import de.hdu.zimmerbelegung.dao.ZimmerDao;
import de.hdu.zimmerbelegung.helper.BelegungArt;
import de.hdu.zimmerbelegung.helper.DatumStatus;
import de.hdu.zimmerbelegung.helper.Status;
import de.hdu.zimmerbelegung.helper.ZimmerZeitraumBelegung;
import de.hdu.zimmerbelegung.model.Belegung;
import de.hdu.zimmerbelegung.model.Zimmer;
import de.hdu.zimmerbelegung.service.ServiceLocator;

/**
 * Data Transfer Object to manage a complete list of booking states for each
 * room in the hotel.
 * 
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 * 
 */
public class ZimmerZeitraumBelegungDto extends HibernateDaoSupport {
	/**
	 * Receive a complete list of booking states for each room in the hotel
	 * within a given timespan.
	 * 
	 * This class reads a list of all "Belegungen" (RESERVIERUNG, BUCHUNG) from
	 * the database and completes it with "FREI"-values for non existing records
	 * in the database. This way it is able to generate a complete list.
	 * 
	 * @param datumVon Beginning of the timespan
	 * @param datumBis Ending of the timespan
	 * @return List of booking states
	 */
	public List<ZimmerZeitraumBelegung> getAll(LocalDate datumVon,
			LocalDate datumBis) {
		// Belegungen im Zeitraum holen
		BelegungDao belegungDao = ServiceLocator.getBelegungDao();
		List<Belegung> belegungList = belegungDao.getAllInZeitraum(datumVon,
				datumBis);

		// Anzahl der Zimmer auslesen
		ZimmerDao zimmerDao = ServiceLocator.getZimmerDao();
		List<Zimmer> zimmerList = zimmerDao.getAll();

		// Temporäre Variablen vorbereiten
		List<ZimmerZeitraumBelegung> zimmerZeitraumBelegungList = new ArrayList<ZimmerZeitraumBelegung>();

		// Zimmer durchlaufen
		for (Zimmer zimmer : zimmerList) {
			ZimmerZeitraumBelegung zimmerZeitraumBelegung = new ZimmerZeitraumBelegung(
					zimmer);
			// Belegungen ("RESERVIERT" oder "GEBUCHT" herausfinden)
			for (Belegung belegung : belegungList) {
				if (belegung.getZimmer().equals(zimmer)) {
					if (belegung.getArt() == BelegungArt.RESERVIERUNG) {
						zimmerZeitraumBelegung.addDatumStatus(new DatumStatus(
								belegung.getDatum(), Status.RESERVIERT,
								belegung.getGast()));
					} else if (belegung.getArt() == BelegungArt.BUCHUNG) {
						zimmerZeitraumBelegung.addDatumStatus(new DatumStatus(
								belegung.getDatum(), Status.GEBUCHT, belegung
										.getGast()));
					}
				}
			}
			// Restliche Tage mit Status "FREI" auffüllen
			for (LocalDate datumTmp = datumVon; datumTmp.isBefore(datumBis)
					|| datumTmp.equals(datumBis); datumTmp = datumTmp
					.plusDays(1)) {
				boolean datumGefunden = false;
				for (DatumStatus datumStatus : zimmerZeitraumBelegung
						.getDatumStatusList()) {
					if (datumStatus.getDatum().equals(datumTmp)) {
						datumGefunden = true;
						break;
					}
				}
				if (!datumGefunden) {
					zimmerZeitraumBelegung.addDatumStatus(new DatumStatus(
							datumTmp, Status.FREI));
				}
			}
			zimmerZeitraumBelegungList.add(zimmerZeitraumBelegung);
		}
		return zimmerZeitraumBelegungList;
	}
}
