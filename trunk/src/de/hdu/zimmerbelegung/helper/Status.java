package de.hdu.zimmerbelegung.helper;

public enum Status {
	FREI ("frei"),
	BELEGT ("belegt"),
	TEILWEISE_BELEGT ("teilweise belegt"),
	RESERVIERT ("reserviert"),
	GEBUCHT ("gebucht");
	
	private final String name;
	Status(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String toString() {
		return this.name;
	}
}
