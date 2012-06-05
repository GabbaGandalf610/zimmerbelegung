/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.helper;

/**
 * This type helps distinguishing bookings (BUCHUNG) and reservations
 * (RESERVIERUNG)
 * 
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 * 
 */
public enum BelegungArt {
	BUCHUNG("buchung"), RESERVIERUNG("reservierung");

	public static BelegungArt[] getAll() {
		return new BelegungArt[] { BUCHUNG, RESERVIERUNG };
	}

	private final String name;

	BelegungArt(String name) {
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