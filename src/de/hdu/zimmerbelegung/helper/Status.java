/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.helper;

/**
 * This type helps distinguishing the current state of a room (Zimmer) at a
 * given date/ timespan.
 * 
 * TEILWEISE_BELEGT is only valid for timespans.
 * GEBUCHT is either booked (BELEGT) or reserved (RESERVIERT)
 * 
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 * 
 */
public enum Status {
	BELEGT("belegt"), FREI("frei"), GEBUCHT("gebucht"), RESERVIERT("reserviert"), TEILWEISE_BELEGT(
			"teilweise belegt");

	private final String name;

	Status(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
