/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.helper;

import org.joda.time.LocalDate;

import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.Zimmer;

public class BelegungKopfDummy {
	private BelegungArt art = BelegungArt.BUCHUNG;
	private LocalDate datumBis = new LocalDate();
	private LocalDate datumVon = new LocalDate();
	private Gast gast;
	private Zimmer zimmer;
	public BelegungArt getArt() {
		return art;
	}
	public LocalDate getDatumBis() {
		return datumBis;
	}
	public LocalDate getDatumVon() {
		return datumVon;
	}
	public Gast getGast() {
		return gast;
	}
	public Zimmer getZimmer() {
		return zimmer;
	}
	public void setArt(BelegungArt art) {
		this.art = art;
	}
	public void setDatumBis(LocalDate datumBis) {
		this.datumBis = datumBis;
	}
	public void setDatumVon(LocalDate datumVon) {
		this.datumVon = datumVon;
	}
	public void setGast(Gast gast) {
		this.gast = gast;
	}
	public void setZimmer(Zimmer zimmer) {
		this.zimmer = zimmer;
	}
}
