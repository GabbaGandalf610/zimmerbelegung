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

import de.hdu.zimmerbelegung.model.Gast;

public class GastDaoTest extends AbstractDataAccessTest{
	private GastDao gastDao;
	private String tableName = "Gast";
	
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
		gastDao.saveOrUpdate(new Gast("Hans", "Dampf", "Straße 1", "99997", "Testdorf", "Deutschland", "0 5555555", "0175", "01", "a.b@c.de", "kurzer Kommentar"));
		gastDao.saveOrUpdate(new Gast("Katharina", "Deckel", "Musterstraße", "98588", "Bayern", "Deutschland", "088834", "0160", "08888", "f.g@h.ij", "etwas längerer Kommentar"));
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
	
	/**
	 * testDelete Test, if a object can be deleted
	 */
	@Test
	public void testDelete(){
		gastDao.saveOrUpdate(new Gast("Katharina", "Deckel", "Musterstraße", "98588", "Bayern", "Deutschland", "088834", "0160", "08888", "f.g@h.ij", "etwas längerer Kommentar"));
		List<Gast> alleGast = gastDao.getAll();
		for (Gast z : alleGast) {
			gastDao.delete(gastDao.getbyId(z.getId()));
		}
		alleGast = gastDao.getAll();
		assertEquals("Die Tabelle darf keine Zeile enthalten", 0, alleGast.size());
	}
	
	/**
	 * testDeleteAll Test, if all objects can be deleted
	 */
	@Test
	public void testDeleteAll(){
		gastDao.saveOrUpdate(new Gast("Katharina", "Deckel", "Musterstraße", "98588", "Bayern", "Deutschland", "088834", "0160", "08888", "f.g@h.ij", "etwas längerer Kommentar"));
		gastDao.saveOrUpdate(new Gast("Katharina", "Deckel", "Musterstraße", "98588", "Bayern", "Deutschland", "088834", "0160", "08888", "f.g@h.ij", "etwas längerer Kommentar"));
		List<Gast> alleGast = gastDao.getAll();
		gastDao.deleteAll();
		alleGast = gastDao.getAll();
		assertEquals("Die Tabelle darf keine Zeile enthalten", 0, alleGast.size());
	}
	
	/**
	 * testGastFilter Test, if a object can be found
	 */
	@Test
	public void testGastFilter(){
		gastDao.saveOrUpdate(new Gast("Katharina", "Deckel", "Musterstraße", "98588", "Bayern", "Deutschland", "088834", "0160", "08888", "f.g@h.ij", "etwas längerer Kommentar"));
		List<Gast> alleGast = gastDao.getAllFilteredUser("Deck");
		for (Gast z : alleGast) {
			z.toString();
			assertEquals("Das Ergebnis darf genau ein Datensatz sein: Gast mit dem Nachnamen _Deckel_ ",z.getName(), "Deckel");
		}
	}

}
