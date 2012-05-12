package de.hdu.zimmerbelegung.helper;

import org.joda.time.LocalDate;

public class DatumStatus implements Comparable<DatumStatus> {
	private LocalDate datum;
	private Status status;
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public DatumStatus(LocalDate datum, Status status) {
		super();
		this.datum = datum;
		this.status = status;
	}
	@Override
	public int compareTo(DatumStatus andererDatumStatus) {
		return this.datum.compareTo(andererDatumStatus.getDatum());
	}
}
