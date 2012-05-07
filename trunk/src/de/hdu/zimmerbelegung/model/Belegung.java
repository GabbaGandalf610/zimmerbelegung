/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Franz Wagner, Roland Kühnel.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.model;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Art", discriminatorType = DiscriminatorType.STRING)
@Table(name = "Belegung", uniqueConstraints = { @UniqueConstraint(columnNames = {"datum", "zimmer_id"}) })
public abstract class Belegung {
	@Id
	@GeneratedValue
	private int id;
	@Type(type="org.joda.time.contrib.hibernate.PersistentLocalDate")
	private LocalDate datum;
	@ManyToOne(targetEntity = Zimmer.class)
	private Zimmer zimmer;
	@ManyToOne(targetEntity = Gast.class)
	private Gast gast;

	public Belegung() {
	}

	public Belegung(LocalDate datum, Zimmer zimmer, Gast gast) {
		super();
		this.datum = datum;
		this.zimmer = zimmer;
		this.gast = gast;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public Zimmer getZimmer() {
		return zimmer;
	}

	public void setZimmer(Zimmer zimmer) {
		this.zimmer = zimmer;
	}

	public Gast getGast() {
		return gast;
	}

	public void setGast(Gast gast) {
		this.gast = gast;
	}

	public int getId() {
		return id;
	}
}
