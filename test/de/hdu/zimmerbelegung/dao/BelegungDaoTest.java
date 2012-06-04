/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.dao;

import java.util.List;


import org.joda.time.LocalDate;
import org.junit.Test;

import de.hdu.zimmerbelegung.helper.BelegungArt;
import de.hdu.zimmerbelegung.model.Belegung;
import de.hdu.zimmerbelegung.model.BelegungKopf;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.Zimmer;

/**
 * The test class for BelegungDao.
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 */
public class BelegungDaoTest extends AbstractDataAccessTest {

	private BelegungDao belegungDao;
	private BelegungKopfDao belegungKopfDao;
	private GastDao gastDao;
	private String tableName = "Belegung";
	private ZimmerDao zimmerDao;

	/**
	 * Gets the {@link BelegungDao} object injected form the bean belegungDaoTest.
	 * @param belegungDao a {@link BelegungDao} object that injects the corresponding bean.
	 */
	public void setBelegungDao(BelegungDao belegungDao) {
		this.belegungDao = belegungDao;
	}

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
//		deleteFromTables(tables);
		// create new line in table
		Gast gastTest = new Gast("Hans", "Dampf", "Musterstraße", "94555",
				"Musterstadt", "Deutschland", "08541", "0160", "08541", "f.g@h.ij",
				"etwas längerer Kommentar");
		gastDao.saveOrUpdate(gastTest);
		Zimmer zimmerTest = new Zimmer(1, "Beschreibung", 12.23f);
		zimmerDao.saveOrUpdate(zimmerTest);
		BelegungKopf belegungKopfTest = new BelegungKopf(gastTest);
		belegungKopfDao.saveOrUpdate(belegungKopfTest);
		Belegung belegungTest = new Belegung(BelegungArt.BUCHUNG,
				LocalDate.now(), zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);

		List<Belegung> alleBelegung = belegungDao.getAll();
		for (Belegung z : alleBelegung) {
			int id = z.getId();
			Belegung found = belegungDao.get(id);
			assertEquals(z.getArt(), found.getArt());
		}
		// delete all rows from db table
		deleteFromTables(tables);
	}

	/**
	 * Simple test that checks if the test class is up and running
	 */
	@Test
	public void tests(){
		System.out.println("Hallo");
	}

}
