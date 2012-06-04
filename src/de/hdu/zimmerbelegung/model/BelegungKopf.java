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
	
	public BelegungKopf() {
		super();
	}

	public BelegungKopf(Gast gast) {
		gastid = gast;
	}
	public BelegungArt getArt() {
		if (belegungen.size() > 0) {
			return belegungen.get(0).getArt();
		} else {
			return BelegungArt.RESERVIERUNG;
		}
	}

	public List<Belegung> getBelegungen() throws Exception {
		pruefeKonsistenz();
		return belegungen;
	}

	public LocalDate getDatumBis() {
		if (belegungen.size() > 0) {
			return belegungen.get(belegungen.size() - 1).getDatum();
		} else {
			return null;
		}
	}

	public LocalDate getDatumVon() {
		if (belegungen.size() > 0) {
			return belegungen.get(0).getDatum();
		} else {
			return null;
		}
	}

	public Gast getGast() {
		// Gibt den Gast der ersten Belegung zurück (Sollte für jede Belegung
		// der gleiche sein)
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
	
//	public void setDatumBis(LocalDate datumBis) {
////		if (getDatumBis().isBefore(datumBis)) {
////			// Überschüssige Buchungen löschen
////			for (Belegung belegung : belegungen) {
////				if (belegung.getDatum().isAfter(datumBis)) {
////					belegungen.remove(belegung);
////				}
////			}
////		} else if (getDatumBis().isAfter(datumBis)) {
////			// Zusätzliche Buchungen erstellen
////			for (LocalDate datumTmp = getDatumBis().plusDays(1); datumTmp.isBefore(datumBis)
////					|| datumTmp.equals(datumBis); datumTmp = datumTmp
////					.plusDays(1)) {
////				Belegung belegung = new Belegung(getArt(), datumTmp, getZimmer(), getGast(), this);
////				belegungen.add(belegung);
////			}
////		}
//	}

	public Zimmer getZimmer() {
		// Gibt das Zimmer der ersten Belegung zurück (Sollte für jede Belegung
		// der gleiche sein)
		if (belegungen.size() > 0) {
			return belegungen.get(0).getZimmer();
		} else {
			return null;
		}
	}

//	public void setArt(BelegungArt art) throws Exception {
//		for (Belegung belegung : belegungen) {
//			belegung.setArt(art);
//		}
//	}

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