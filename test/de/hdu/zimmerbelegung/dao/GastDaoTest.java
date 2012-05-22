package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.junit.Test;

import de.hdu.zimmerbelegung.model.Gast;

public class GastDaoTest extends AbstractDataAccessTest{
	private GastDao gastDao;
	private String tableName = "Gast";
	
	public void setGastDao(GastDao gastDao) {
		this.gastDao = gastDao;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testFindById() {
		String[] tables = { tableName };
		// delete all rows from db table
		deleteFromTables(tables);
		// create new line in table
		gastDao.saveOrUpdate(new Gast("Franz", "Wagner", "Kapellenstraße 66", "94527", "Aholming/Neutiefenweg", "Deutschland", "09931 5555555", "0175", "09931", "a.b@c.de", "kurzer Kommentar"));
		gastDao.saveOrUpdate(new Gast("Katharina", "Ecker", "Musterstraße", "94555", "Künzing", "Deutschland", "08541", "0160", "08541", "f.g@h.ij", "etwas längerer Kommentar"));
		List<Gast> alleGast = gastDao.getAll();
		for (Gast z : alleGast) {
			int id = z.getId();
			Gast found = gastDao.getbyId(id);
			assertEquals(z.getLand(),
					found.getLand());
		}
		// delete all rows from db table
		deleteFromTables(tables);
	}
	
	@Test
	public void testFindAll() {
		// already tested by testFindById()
		testFindById();
	}

	@Test
	public void testCreate() {
		// already tested by testFindById()
		testFindById();
	}

}
