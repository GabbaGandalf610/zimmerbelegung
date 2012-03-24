/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Franz Wagner, Roland Kühnel.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name="Art",
		discriminatorType=DiscriminatorType.STRING
)
public abstract class Belegung {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(targetEntity=Datum.class)
	private Datum datumVon;
	@ManyToOne(targetEntity=Datum.class)
	private Datum datumBis;
	@ManyToOne(targetEntity=Zimmer.class)
	private Zimmer zimmer;
	@ManyToOne(targetEntity=Gast.class)
	private Gast gast;
	public Gast getGast() {
		return gast;
	}
	public void setGast(Gast gast) {
		this.gast = gast;
	}
	public Zimmer getZimmer() {
		return zimmer;
	}
	public void setZimmer(Zimmer zimmer) {
		this.zimmer = zimmer;
	}
	private String kommentar;
	public Datum getDatumVon() {
		return datumVon;
	}
	public void setDatumVon(Datum datumVon) {
		this.datumVon = datumVon;
	}
	public Datum getDatumBis() {
		return datumBis;
	}
	public void setDatumBis(Datum datumBis) {
		this.datumBis = datumBis;
	}
	public String getKommentar() {
		return kommentar;
	}
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}
	public int getId() {
		return id;
	}
}
