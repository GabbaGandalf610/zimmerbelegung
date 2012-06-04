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

public class DatumStatus implements Comparable<DatumStatus> {
	private LocalDate datum;
	private Gast gast;
	private Status status;
	public DatumStatus(LocalDate datum, Status status) {
		this(datum, status, null);
	}
	public DatumStatus(LocalDate datum, Status status, Gast gast) {
		super();
		this.datum = datum;
		this.status = status;
		this.gast = gast;
	}
	@Override
	public int compareTo(DatumStatus andererDatumStatus) {
		return this.datum.compareTo(andererDatumStatus.getDatum());
	}
	public LocalDate getDatum() {
		return datum;
	}
	public Gast getGast() {
		return gast;
	}
	public Status getStatus() {
		return status;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	public void setGast(Gast gast) {
		this.gast = gast;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		if (this.gast != null) 
			return status.name() + " durch " + gast;
		else
			return status.name();
	}
}
