package de.hdu.zimmerbelegung.helper;

import de.hdu.zimmerbelegung.model.ZeitraumStatus;
import de.hdu.zimmerbelegung.model.Zimmer;

public class ZimmerStatus {
	private Zimmer zimmer;
	private ZeitraumStatus zeitraumStatus;
	public Zimmer getZimmer() {
		return zimmer;
	}
	public void setZimmer(Zimmer zimmer) {
		this.zimmer = zimmer;
	}
	public ZeitraumStatus getZeitraumStatus() {
		return zeitraumStatus;
	}
	public void setZeitraumStatus(ZeitraumStatus zeitraumStatus) {
		this.zeitraumStatus = zeitraumStatus;
	}
	public ZimmerStatus(Zimmer zimmer, ZeitraumStatus zeitraumStatus) {
		super();
		this.zimmer = zimmer;
		this.zeitraumStatus = zeitraumStatus;
	}
	
}
