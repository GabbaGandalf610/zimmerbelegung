package de.hdu.zimmerbelegung.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ZeitraumStatus {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ZeitraumStatus() {
		
	}
	
	public ZeitraumStatus(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	

}
