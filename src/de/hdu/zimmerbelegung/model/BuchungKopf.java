package de.hdu.zimmerbelegung.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
@DiscriminatorValue("Buchung")
public class BuchungKopf extends BelegungKopf {
	public BuchungKopf() {
		super();
	}
	
	@OneToMany
	@OrderBy("datum")
	private List<Buchung> buchungen;

	public List<Buchung> getBuchungen() {
		return buchungen;
	}
}