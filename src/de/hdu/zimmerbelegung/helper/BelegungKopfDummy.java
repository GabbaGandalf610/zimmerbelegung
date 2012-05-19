package de.hdu.zimmerbelegung.helper;

import org.joda.time.LocalDate;

import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.Zimmer;

public class BelegungKopfDummy {
	private Gast gast;
	private Zimmer zimmer;
	private LocalDate datumVon = new LocalDate();
	private LocalDate datumBis = new LocalDate();
	private BelegungArt art = BelegungArt.BUCHUNG;
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
	public LocalDate getDatumVon() {
		return datumVon;
	}
	public void setDatumVon(LocalDate datumVon) {
		this.datumVon = datumVon;
	}
	public LocalDate getDatumBis() {
		return datumBis;
	}
	public void setDatumBis(LocalDate datumBis) {
		this.datumBis = datumBis;
	}
	public BelegungArt getArt() {
		return art;
	}
	public void setArt(BelegungArt art) {
		this.art = art;
	}
}
