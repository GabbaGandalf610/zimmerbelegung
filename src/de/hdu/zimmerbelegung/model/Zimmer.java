/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Franz Wagner, Roland Kühnel.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Zimmer {
	@Id
	private int id;
	@ManyToOne(targetEntity=ZimmerKategorie.class)
	private ZimmerKategorie kategorie;
	@OneToMany(targetEntity=Belegung.class)
	private Set<Belegung> belegungen;
	public Set<Belegung> getBelegung() {
		return belegungen;
	}
	public void setBuchungen(Set<Belegung> belegungen) {
		this.belegungen = belegungen;
	}
	public ZimmerKategorie getKategorie() {
		return kategorie;
	}
	public void setKategorie(ZimmerKategorie kategorie) {
		this.kategorie = kategorie;
	}
	public int getId() {
		return id;
	}
}
