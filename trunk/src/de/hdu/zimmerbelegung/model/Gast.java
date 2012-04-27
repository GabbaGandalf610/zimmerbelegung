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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Gast {
	@Id
	@GeneratedValue
	private int id;
	private String vorname;
	private String name;
	private String kommentar;
	public String getKommentar() {
		return kommentar;
	}
	public Gast(String vorname, String name, String kommentar,
			Set<Belegung> belegungen) {
		super();
		this.vorname = vorname;
		this.name = name;
		this.kommentar = kommentar;
		this.belegungen = belegungen;
	}
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	@OneToMany(targetEntity=Belegung.class)
	private Set<Belegung> belegungen;
	public Set<Belegung> getBelegung() {
		return belegungen;
	}
	public void setBuchungen(Set<Belegung> belegungen) {
		this.belegungen = belegungen;
	}
}
