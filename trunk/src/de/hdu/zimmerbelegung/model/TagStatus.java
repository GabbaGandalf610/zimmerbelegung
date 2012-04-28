package de.hdu.zimmerbelegung.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TagStatus {
	@Id
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public TagStatus() {
		
	}
	
	public TagStatus(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}