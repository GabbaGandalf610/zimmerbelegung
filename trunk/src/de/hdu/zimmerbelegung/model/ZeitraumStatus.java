package de.hdu.zimmerbelegung.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Zeitraum")
public class ZeitraumStatus extends TagStatus {
	public ZeitraumStatus(int id, String name) {
		super(id, name);
	}
}
