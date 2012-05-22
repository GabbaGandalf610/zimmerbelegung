package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.junit.Test;

import de.hdu.zimmerbelegung.model.Zimmer;

public class ZimmerDaoTest extends AbstractDataAccessTest {
	private ZimmerDao zimmerDao;
	private String tableName = "Zimmer";

	public void setZimmerDao(ZimmerDao zimmerDao) {
		this.zimmerDao = zimmerDao;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testFindById() {
		String[] tables = { tableName };
		// delete all rows from db table
		deleteFromTables(tables);
		// create new line in table
		zimmerDao.saveOrUpdate(new Zimmer(1, "Beschreibung", 12.23f));
		zimmerDao.saveOrUpdate(new Zimmer(2, "Beschreibung2", 23.34f));
		List<Zimmer> alleZimmer = zimmerDao.getAll();
		for (Zimmer z : alleZimmer) {
			int id = z.getId();
			Zimmer found = zimmerDao.getbyId(id);
			assertEquals(z.getZimmerbeschreibung(),
					found.getZimmerbeschreibung());
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
