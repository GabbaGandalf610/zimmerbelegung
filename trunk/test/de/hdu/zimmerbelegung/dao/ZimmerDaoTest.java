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

import de.hdu.zimmerbelegung.model.Zimmer;

public class ZimmerDaoTest extends AbstractDataAccessTest {
	private ZimmerDao zimmerDao;
	private String tableName = "Zimmer";

	/**
	 * Gets the {@link ZimmerDao} object injected form the bean belegungDaoTest.
	 * @param belegungDao a {@link ZimmerDao} object that injects the corresponding bean.
	 */
	public void setZimmerDao(ZimmerDao zimmerDao) {
		this.zimmerDao = zimmerDao;
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
	 * testFindAll is already included in testFindById
	 */
	@Test
	public void testCreate() {
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
	
	/**
	 * testDeleteAll Test, if a object can be deleted
	 */
	@Test
	public void testDelete(){
		zimmerDao.saveOrUpdate(new Zimmer(2, "Beschreibung2", 23.34f));
		List<Zimmer> alleZimmer = zimmerDao.getAll();
		for (Zimmer z : alleZimmer) {
			zimmerDao.delete(zimmerDao.getbyId(z.getId()));
		}
		alleZimmer = zimmerDao.getAll();
		assertEquals("Die Tabelle darf keine Zeile enthalten", 0, alleZimmer.size());
	}

	/**
	 * testDeleteAll Test, if all objects can be deleted
	 */
	@Test
	public void testDeleteAll(){
		zimmerDao.saveOrUpdate(new Zimmer(2, "Beschreibung2", 23.34f));
		List<Zimmer> alleZimmer = zimmerDao.getAll();
		zimmerDao.deleteAll();
		alleZimmer = zimmerDao.getAll();
		assertEquals("Die Tabelle darf keine Zeile enthalten", 0, alleZimmer.size());
	}
	
}
