package de.hdu.zimmerbelegung.helper;

import java.util.Date;
import java.util.HashMap;

import org.joda.time.LocalDate;

import de.hdu.zimmerbelegung.model.Zimmer;

public class ZimmerZeitraumBelegung extends HashMap<LocalDate, Status> {
	private static final long serialVersionUID = -7415182209220022693L;
	private Zimmer zimmer;
	public ZimmerZeitraumBelegung(Zimmer zimmer) {
		super();
		this.zimmer = zimmer;
	}
	public Zimmer getZimmer() {
		return zimmer;
	}
	public void setZimmer(Zimmer zimmer) {
		this.zimmer = zimmer;
	}
	public Status getStatus() {
		return Status.FREI;
	}
}
