/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 */
@Entity
public class Zimmer {

	@Id
	@GeneratedValue
	private int id;
	private String zimmerbeschreibung;
	private int zimmernummer;
	private float zimmerpreis;

	/**
	 * (default-) constructor for class Zimmer 
	 */
	public Zimmer() {

	}
	
	/**
	 * constructor for class Zimmer
	 * @param id
	 * @param zimmernummer
	 * @param zimmerbeschreibung
	 * @param zimmerpreis
	 */
	public Zimmer(int id, int zimmernummer, String zimmerbeschreibung, float zimmerpreis) {
		super();
		this.id = id;
		this.zimmernummer = zimmernummer;
		this.zimmerbeschreibung = zimmerbeschreibung;
		this.zimmerpreis = zimmerpreis;
	}
	
	
	/**
	 * constructor for class Zimmer
	 * @param zimmernummer
	 * @param zimmerbeschreibung
	 * @param zimmerpreis
	 */
	public Zimmer(int zimmernummer, String zimmerbeschreibung, float zimmerpreis) {
		super();
		this.zimmernummer = zimmernummer;
		this.zimmerbeschreibung = zimmerbeschreibung;
		this.zimmerpreis = zimmerpreis;
	}
	
	/**
	 * equals to test if a variable is a Zimmer-object
	 * @param obj
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Zimmer) {
			return this.id == ((Zimmer)obj).id;
		} else {
			return super.equals(obj);
		}
	}

	public int getId() {
		return id;
	}
	
	public String getZimmerbeschreibung() {
		return zimmerbeschreibung;
	}

	public int getZimmernummer() {
		return zimmernummer;
	}
	
	public float getZimmerpreis() {
		return zimmerpreis;
	}

	public void setId(int id){
		this.id = id;
	}

	public void setZimmerbeschreibung(String zimmerbeschreibung) {
		this.zimmerbeschreibung = zimmerbeschreibung;
	}

	public void setZimmernummer(int zimmernummer) {
		this.zimmernummer = zimmernummer;
	}

	public void setZimmerpreis(float zimmerpreis) {
		this.zimmerpreis = zimmerpreis;
	}
	
	/**
	 * toString overrides the toString-method with custom description
	 * @return custom description of a Zimmer
	 */
	@Override
	public String toString() {
		return  this.zimmernummer + " (" + this.zimmerbeschreibung + ") " + this.zimmerpreis;
	}
}
