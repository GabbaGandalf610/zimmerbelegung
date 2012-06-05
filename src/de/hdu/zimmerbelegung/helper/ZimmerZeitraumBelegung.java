/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdu.zimmerbelegung.model.Zimmer;

/**
 * Helper class to store a list of states for given room (ZIMMER).
 * 
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 *
 */
public class ZimmerZeitraumBelegung {
	private List<DatumStatus> datumStatusList = new ArrayList<DatumStatus>();
	private Zimmer zimmer;
	public ZimmerZeitraumBelegung(Zimmer zimmer) {
		super();
		this.zimmer = zimmer;
	}
	public void addDatumStatus(DatumStatus datumStatus) {
		this.datumStatusList.add(datumStatus);
		Collections.sort(datumStatusList);
	}
	public List<DatumStatus> getDatumStatusList() {
		return datumStatusList;
	}
	/**
	 * Receive the overall status of the "Zimmer" for the whole timespan.
	 * 
	 * @return
	 */
	public Status getStatus() {
		int anzahlFrei = 0;
		int anzahlGebucht = 0;
		int anzahlReserviert = 0; 
		for (DatumStatus datumStatus : datumStatusList) {
			switch (datumStatus.getStatus()) {
			case GEBUCHT:
				anzahlGebucht++;
				break;
			case RESERVIERT:
				anzahlReserviert++;
				break;
			case FREI:
				anzahlFrei++;
				break;
			default:
				break;
			}
		}
		if (datumStatusList.size() == anzahlFrei) {
			return Status.FREI;
		}
		if (datumStatusList.size() == anzahlGebucht) {
			return Status.GEBUCHT;
		}
		if (datumStatusList.size() == anzahlReserviert) {
			return Status.RESERVIERT;
		}
		if (datumStatusList.size() == (anzahlReserviert + anzahlGebucht)) {
			return Status.BELEGT;
		}
		return Status.TEILWEISE_BELEGT;
	}
	public Zimmer getZimmer() {
		return zimmer;
	}
	public void setDatumStatusList(List<DatumStatus> datumStatusList) {
		this.datumStatusList = datumStatusList;
	}
	public void setZimmer(Zimmer zimmer) {
		this.zimmer = zimmer;
	}
}
