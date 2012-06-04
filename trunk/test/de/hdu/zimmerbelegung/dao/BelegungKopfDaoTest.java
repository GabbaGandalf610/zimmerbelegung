/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.junit.Test;

import de.hdu.zimmerbelegung.model.BelegungKopf;
import de.hdu.zimmerbelegung.model.Gast;

public class BelegungKopfDaoTest extends AbstractDataAccessTest {

	private BelegungKopfDao belegungKopfDao;
	private GastDao gastDao;
	private String tableName = "BelegungKopf";
	
	/**
	 * Gets the {@link BelegungKopfDao} object injected form the bean belegungDaoTest.
	 * @param belegungDao a {@link BelegungKopfDao} object that injects the corresponding bean.
	 */
	public void setBelegungKopfDao(BelegungKopfDao belegungKopfDao) {
		this.belegungKopfDao = belegungKopfDao;
	}

	/**
	 * Gets the {@link GastDao} object injected form the bean belegungDaoTest.
	 * @param belegungDao a {@link GastDao} object that injects the corresponding bean.
	 */
	public void setGastDao(GastDao gastDao) {
		this.gastDao = gastDao;
	}

	/**
	 * testFindAll is already included in testFindById
	 */
	@Test
	public void testCreate() {
		// already tested by testFindById()
		testFindById();
	}

	/**
	 * testFindAll is already included in testFindById
	 */
	@Test
	public void testFindAll() {
		// already tested by testFindById()
		testFindById();
	}

	/**
	 * testFindById is a test case that tests all the basic functions of the Dao, new object, update, get and delete
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testFindById() {
		String[] tables = { tableName };
		// delete all rows from db table
		deleteFromTables(tables);
		// create new line in table
		Gast gastNeu = new Gast("Hans", "Testmann", "Musterstraße 99", "92345", "Testdorf", "Deutschland", "09291 5555555", "0175", "09291", "a.b@c.de", "kurzer Kommentar");
		gastDao.saveOrUpdate(gastNeu);
		BelegungKopf belegungKopfneu = new BelegungKopf(gastNeu);
		belegungKopfDao.saveOrUpdate(belegungKopfneu);
		List<BelegungKopf> alleBelegungKopf = belegungKopfDao.getAll();
		for (BelegungKopf z : alleBelegungKopf) {
			int id = z.getId();
			BelegungKopf found = belegungKopfDao.get(id);
			assertEquals(z.getId(),
					found.getId());
		}
		// delete all rows from db table
		deleteFromTables(tables);
	}
	
	
}
