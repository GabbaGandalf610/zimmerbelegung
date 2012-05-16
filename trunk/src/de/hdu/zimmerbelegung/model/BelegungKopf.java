package de.hdu.zimmerbelegung.model;

import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Art", discriminatorType = DiscriminatorType.STRING)
public abstract class BelegungKopf {
	@Id
	@GeneratedValue
	private int id;

	@OneToMany
	@OrderBy("datum")
	private List<Belegung> belegungen;

	public BelegungKopf() {
		super();
	}

	public int getId() {
		return id;
	}
}
