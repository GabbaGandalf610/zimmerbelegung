package de.hdu.zimmerbelegung.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TagStatus {
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
	
	public TagStatus() {
		
	}
	
	public TagStatus(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
