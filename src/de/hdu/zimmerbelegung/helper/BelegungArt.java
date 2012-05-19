package de.hdu.zimmerbelegung.helper;


public enum BelegungArt {
	BUCHUNG ("buchung"),
	RESERVIERUNG ("reservierung");
	
	public static BelegungArt[] getAll() {
		return new BelegungArt[] { BUCHUNG, RESERVIERUNG };
	}
	
	private final String name;
	BelegungArt(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public String toString() {
		return this.name;
	}
}