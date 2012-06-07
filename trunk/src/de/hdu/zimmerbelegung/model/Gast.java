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

/**
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 */
@Entity
public class Gast {
	@OneToMany(targetEntity = Belegung.class, mappedBy = "gast", fetch = FetchType.EAGER)
	private Set<Belegung> belegung;
	@OneToMany(targetEntity = BelegungKopf.class, mappedBy = "gastid", fetch = FetchType.EAGER)
	@OrderBy("id DESC")
	private Set<BelegungKopf> belegungKopf;
	private String email;
	private String fax;
	private String firma;
	@Id
	@GeneratedValue
	private int id;
	private String kommentar;
	private String land;
	private String mobil;
	private String name;
	private String ort;
	private String plz;
	private String strasse;
	private String telefon;
	private String vorname;

	/**
	 * (default-) constructor for class Gast
	 */
	public Gast() {

	}

	/**
	 * constructor for class Gast
	 * 
	 * @param id
	 * @param vorname
	 * @param name
	 *            nachname
	 * @param strasse
	 * @param plz
	 * @param ort
	 * @param land
	 * @param telefon
	 * @param mobil
	 * @param fax
	 * @param email
	 * @param kommentar
	 */
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

	/**
	 * constructor for class Gast
	 * 
	 * @param vorname
	 * @param name
	 *            nachname
	 * @param strasse
	 * @param plz
	 * @param ort
	 * @param land
	 * @param telefon
	 * @param mobil
	 * @param fax
	 * @param email
	 * @param kommentar
	 */
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

	public Set<Belegung> getBelegung() {
		return belegung;
	}

	public Set<BelegungKopf> getBelegungKopf() {
		return belegungKopf;
	}

	public String getEmail() {
		return email;
	}

	public String getFax() {
		return fax;
	}

	public String getFirma() {
		return firma;
	}

	public int getId() {
		return id;
	}

	public String getKommentar() {
		return kommentar;
	}

	public String getLand() {
		return land;
	}

	public String getMobil() {
		return mobil;
	}

	public String getName() {
		return name;
	}

	public String getOrt() {
		return ort;
	}

	public String getPlz() {
		return plz;
	}

	public String getStrasse() {
		return strasse;
	}

	public String getTelefon() {
		return telefon;
	}

	public String getVorname() {
		return vorname;
	}

	public void setBelegung(Set<Belegung> belegung) {
		this.belegung = belegung;
	}

	public void setBelegungKopf(Set<BelegungKopf> belegungKopf) {
		this.belegungKopf = belegungKopf;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	/**
	 * toString overrides the toString-method with custom description
	 * 
	 * @return custom description of a Gast
	 */
	@Override
	public String toString() {
		if (this.firma != null) {
			if (this.name != null) {
				if (this.vorname != null) {
					return this.firma + ", " + this.vorname + " " + this.name;
				}
				return this.firma + ", " + this.name;
			}
			return this.firma;
		}
		if (this.name != null) {
			if (this.vorname != null) {
				return this.vorname + " " + this.name;
			}
			return this.name;
		}
		if (this.vorname != null){
			return this.vorname;
		}

		return this.id + "";
	}

}
