/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Franz Wagner, Roland Kühnel.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.joda.time.LocalDate;

@Entity
@DiscriminatorValue("Reservierung")
public class Reservierung extends Belegung {
	
	public Reservierung(){
		super();
	}
	
	public Reservierung(LocalDate datum, Zimmer zimmer, Gast gast, BuchungKopf buchungKopf) {
		super(datum, zimmer, gast, buchungKopf);
	}
}
