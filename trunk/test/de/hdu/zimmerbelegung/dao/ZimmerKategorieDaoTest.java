package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.junit.Test;

import de.hdu.zimmerbelegung.model.ZimmerKategorie;

public class ZimmerKategorieDaoTest extends AbstractDataAccessTest {
	private ZimmerKategorieDao zimmerKategorieDao;
	private String tableName = "ZimmerKategorie";

	public void setZimmerKategorieDao(ZimmerKategorieDao zimmerKategorieDao) {
		this.zimmerKategorieDao = zimmerKategorieDao;
	}
	@SuppressWarnings("deprecation")
	@Test
	public void testFindById() {
		String[] tables = {tableName};
		// delete all rows from db table
		deleteFromTables(tables);
		// create new line in table
		zimmerKategorieDao.create("eins");
		zimmerKategorieDao.create("zwei");
		List<ZimmerKategorie> alleZimmerKategorien = zimmerKategorieDao.findAll();
		for (ZimmerKategorie zk : alleZimmerKategorien) {
			int id = zk.getId();
			ZimmerKategorie found = zimmerKategorieDao.findById(id);
			assertEquals(zk.getName(), found.getName());
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
