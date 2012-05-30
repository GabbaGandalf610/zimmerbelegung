/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.helper;


public enum BelegungArt {
	BUCHUNG ("buchung"),
	RESERVIERUNG ("reservierung");
	
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
	public String toString() {
		return this.name;
	}
}