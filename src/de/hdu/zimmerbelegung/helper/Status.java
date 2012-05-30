/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.helper;

public enum Status {
	FREI ("frei"),
	BELEGT ("belegt"),
	TEILWEISE_BELEGT ("teilweise belegt"),
	RESERVIERT ("reserviert"),
	GEBUCHT ("gebucht");
	
	private final String name;
	Status(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.name;
	}
}
