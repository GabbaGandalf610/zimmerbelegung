/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.joda.time.LocalDate;

import com.sun.istack.internal.NotNull;

import de.hdu.zimmerbelegung.helper.BelegungArt;

/**
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 */
@Entity
public class BelegungKopf {
	@OneToMany(targetEntity = Belegung.class, mappedBy = "belegungKopf", fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@OrderBy("datum")
	private List<Belegung> belegungen = new ArrayList<Belegung>();

	@ManyToOne(targetEntity=Gast.class,optional=false)
	@NotNull
	private Gast gastid;
	
	@Id
	@GeneratedValue
	private int id;
	
	
	/**
	 * (default-) constructor for class BelegungKopf 
	 */
	public BelegungKopf() {
		super();
	}
	/**
	 * constructor for class BelegungKopf
	 * @param gast creates a new BelegungKopf object with given Gast object 
	 */
	public BelegungKopf(Gast gast) {
		gastid = gast;
	}
	
	/**
	 * getArt
	 * @return returns a object of type BelegungArt, for example "frei" or "reserviert" 
	 */
	public BelegungArt getArt() {
		if (belegungen.size() > 0) {
			return belegungen.get(0).getArt();
		} else {
			return BelegungArt.RESERVIERUNG;
		}
	}

	/**
	 * getBelegungen
	 * @return returns a List of type Belegung 
	 */
	public List<Belegung> getBelegungen() throws Exception {
		pruefeKonsistenz();
		return belegungen;
	}

	/**
	 * getDatumBis
	 * @return returns the date of the last booking or reservation
	 */
	public LocalDate getDatumBis() {
		if (belegungen.size() > 0) {
			return belegungen.get(belegungen.size() - 1).getDatum();
		} else {
			return null;
		}
	}

	/**
	 * getDatumVon
	 * @return returns the date of the first booking or reservation
	 */
	public LocalDate getDatumVon() {
		if (belegungen.size() > 0) {
			return belegungen.get(0).getDatum();
		} else {
			return null;
		}
	}

	/**
	 * getGast
	 * @return returns the Gast of the first booking or reservation, should be the same for all
	 */
	public Gast getGast() {
		if (belegungen.size() > 0) {
			return belegungen.get(0).getGast();
		} else {
			return null;
		}
	}

	public Gast getGastid() {
		return gastid;
	}

	public int getId() {
		return id;
	}
	
	/**
	 * getZimmer
	 * @return returns the Zimmer of the first booking or reservation, should be the same for all
	 */
	public Zimmer getZimmer() {
		if (belegungen.size() > 0) {
			return belegungen.get(0).getZimmer();
		} else {
			return null;
		}
	}

	/**
	 * pruefeKonsistenz
	 * checks, if all guests, rooms, type of booking and timespan is not interrupted 
	 */
	private void pruefeKonsistenz() throws Exception {
		Gast gast = getGast();
		Zimmer zimmer = getZimmer();
		LocalDate datum = getDatumVon();
		BelegungArt art = getArt();
		for (Belegung belegung : belegungen) {
			if (!belegung.getGast().equals(gast))
				throw new Exception(
						"Konsistenzfehler: Unterschiedliche Gäste in Belegung ["
								+ getId() + "/" + belegung.getId() + "]");
			if (!belegung.getZimmer().equals(zimmer))
				throw new Exception(
						"Konsistenzfehler: Unterschiedliche Zimmer in Belegung ["
								+ getId() + "/" + belegung.getId() + "]");
			if (!belegung.getDatum().isEqual(datum))
				throw new Exception(
						"Konsistenzfehler: Zeitraum der Belegung ist nicht durchgängig ["
								+ getId() + "/" + belegung.getId() + "]");
			if (belegung.getArt() != art)
				throw new Exception(
						"Konsistenzfehler: Art der Belegung ist nicht durchgängig ["
								+ getId() + "/" + belegung.getId() + "]");
			datum = datum.plusDays(1);
		}
	}

	public void setBelegungen(List<Belegung> belegungen) {
		this.belegungen = belegungen;
	}

	public void setGastid(Gast gastid) {
		this.gastid = gastid;
	}


}
