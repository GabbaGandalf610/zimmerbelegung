package de.hdu.zimmerbelegung.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
@DiscriminatorValue("Belegung")
public class ReservierungKopf extends BelegungKopf {
	public ReservierungKopf() {
		super();
	}
	
	@OneToMany
	@OrderBy("datum")
	private List<Reservierung> reservierungen;

	public List<Reservierung> getReservierungen() {
		return reservierungen;
	}
}
