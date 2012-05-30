/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Gast {
	@Id
	@GeneratedValue
	private int id;
	private String vorname;
	private String name;
	private String firma;
	private String strasse;
	private String plz;
	private String ort;
	private String land;
	private String telefon;
	private String mobil;
	private String fax;
	private String email;
	private String kommentar;
	@OneToMany(targetEntity = BelegungKopf.class, mappedBy = "gastid", fetch = FetchType.EAGER)
	@OrderBy("id DESC")
	private Set<BelegungKopf> belegungKopf;
	@OneToMany(targetEntity = Belegung.class, mappedBy = "gast", fetch = FetchType.EAGER)
	private Set<Belegung> belegung;

	public Set<BelegungKopf> getBelegungKopf() {
		return belegungKopf;
	}

	public void setBelegungKopf(Set<BelegungKopf> belegungKopf) {
		this.belegungKopf = belegungKopf;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getMobil() {
		return mobil;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKommentar() {
		return kommentar;
	}

	public Gast() {

	}

	public Gast(int id, String vorname, String name, String strasse,
			String plz, String ort, String land, String telefon, String mobil,
			String fax, String email, String kommentar) {
		super();
		this.id = id;
		this.vorname = vorname;
		this.name = name;
		this.strasse = strasse;
		this.plz = plz;
		this.ort = ort;
		this.land = land;
		this.telefon = telefon;
		this.mobil = mobil;
		this.fax = fax;
		this.email = email;
		this.kommentar = kommentar;
	}

	public Gast(String vorname, String name, String strasse, String plz,
			String ort, String land, String telefon, String mobil, String fax,
			String email, String kommentar) {
		super();
		this.vorname = vorname;
		this.name = name;
		this.strasse = strasse;
		this.plz = plz;
		this.ort = ort;
		this.land = land;
		this.telefon = telefon;
		this.mobil = mobil;
		this.fax = fax;
		this.email = email;
		this.kommentar = kommentar;
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

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public String toString() {
		return this.vorname + " " + this.name;
	}

	public Set<Belegung> getBelegung() {
		return belegung;
	}

	public void setBelegung(Set<Belegung> belegung) {
		this.belegung = belegung;
	}

}
