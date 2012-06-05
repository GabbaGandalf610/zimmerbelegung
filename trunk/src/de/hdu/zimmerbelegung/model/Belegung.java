/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.sun.istack.internal.NotNull;

import de.hdu.zimmerbelegung.helper.BelegungArt;

/**
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 */
@Entity
@Table(name = "Belegung", uniqueConstraints = { @UniqueConstraint(columnNames = {"datum", "zimmer_id"}) })
public class Belegung {
	@Enumerated(EnumType.STRING)
	@NotNull
	private BelegungArt art;
	@ManyToOne(targetEntity=BelegungKopf.class, optional=false)
	@NotNull
	private BelegungKopf belegungKopf;
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalDate")
	@NotNull
	private LocalDate datum;
	@ManyToOne(targetEntity=Gast.class, optional=false)
	@NotNull
	private Gast gast;
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(targetEntity=Zimmer.class)
	@NotNull
	private Zimmer zimmer;

	/**
	 * (default-) constructor for class Belegung 
	 */
	public Belegung() {
	}

	/**
	 * constructor for class Belegung
	 * @param art
	 * @param datum
	 * @param zimmer
	 * @param gast
	 * @param belegungKopf 
	 */
	public Belegung(BelegungArt art, LocalDate datum, Zimmer zimmer, Gast gast, BelegungKopf belegungKopf) {
		this.art = art;
		this.datum = datum;
		this.zimmer = zimmer;
		this.gast = gast;
		this.belegungKopf = belegungKopf;
	}
	
	public BelegungArt getArt() {
		return art;
	}

	public BelegungKopf getBelegungKopf() {
		return belegungKopf;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public Gast getGast() {
		return gast;
	}

	public int getId() {
		return id;
	}

	public Zimmer getZimmer() {
		return zimmer;
	}

	public void setArt(BelegungArt art) {
		this.art = art;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	
	public void setGast(Gast gast) {
		this.gast = gast;
	}
	
	public void setZimmer(Zimmer zimmer) {
		this.zimmer = zimmer;
	}
}
