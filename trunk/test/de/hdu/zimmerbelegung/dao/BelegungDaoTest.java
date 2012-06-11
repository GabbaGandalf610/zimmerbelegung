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
 * 
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 */
public class BelegungDaoTest extends AbstractDataAccessTest {

	private BelegungDao belegungDao;
	private GastDao gastDao;
	private ZimmerDao zimmerDao;
	private BelegungKopfDao belegungKopfDao;
	private String tableName = "Belegung";

	/**
	 * Gets the {@link BelegungDao} object injected form the bean
	 * belegungDaoTest.
	 * 
	 * @param belegungDao
	 *            a {@link BelegungDao} object that injects the corresponding
	 *            bean.
	 */
	public void setBelegungDao(BelegungDao belegungDao) {
		this.belegungDao = belegungDao;
	}

	/**
	 * Gets the {@link GastDao} object injected form the bean belegungDaoTest.
	 * 
	 * @param belegungDao
	 *            a {@link GastDao} object that injects the corresponding bean.
	 */
	public void setGastDao(GastDao gastDao) {
		this.gastDao = gastDao;
	}

	/**
	 * Gets the {@link ZimmerDao} object injected form the bean belegungDaoTest.
	 * 
	 * @param belegungDao
	 *            a {@link ZimmerDao} object that injects the corresponding
	 *            bean.
	 */
	public void setZimmerDao(ZimmerDao zimmerDao) {
		this.zimmerDao = zimmerDao;
	}

	/**
	 * Gets the {@link BelegungKopfDao} object injected form the bean
	 * belegungDaoTest.
	 * 
	 * @param belegungDao
	 *            a {@link BelegungKopfDao} object that injects the
	 *            corresponding bean.
	 */
	public void setBelegungKopfDao(BelegungKopfDao belegungKopfDao) {
		this.belegungKopfDao = belegungKopfDao;
	}

	/**
	 * Simple test that checks if the test class is up and running
	 */
	@Test
	public void tests() {
		assertEquals("simple Test", 0, 0);
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
	 * testFindById is a test case that tests all the basic functions of the
	 * Dao, new object, update, get and delete
	 * After the creation of a new Gast, Zimmer, BelegungKopf and Belegung-object
	 * the test indicates if the created Belegung-objects exists in the database
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testFindById() {
		String[] tables = { tableName };
		// delete all rows from db table
		// deleteFromTables(tables);
		// create new line in table
		Gast gastTest = new Gast("Hans", "Dampf", "Musterstraße", "94555",
				"Musterstadt", "Deutschland", "08541", "0160", "08541",
				"f.g@h.ij", "etwas längerer Kommentar");
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
	 * testAllInZeitraum tests the Dao method getAllinZeitraum
	 * After the creation of a new Gast, Zimmer, BelegungKopf and 3 Belegung-objects
	 * the test indicates if the created Belegung-objects exists in the database
	 * in the specified timeline
	 */
	@Test
	public void testAllInZeitraum() {
		LocalDate datumBis = LocalDate.now();
		Gast gastTest = new Gast("Hans", "Dampf", "Musterstraße", "94555",
				"Musterstadt", "Deutschland", "08541", "0160", "08541",
				"f.g@h.ij", "etwas längerer Kommentar");
		gastDao.saveOrUpdate(gastTest);
		Zimmer zimmerTest = new Zimmer(1, "Beschreibung", 12.23f);
		zimmerDao.saveOrUpdate(zimmerTest);
		BelegungKopf belegungKopfTest = new BelegungKopf(gastTest);
		belegungKopfDao.saveOrUpdate(belegungKopfTest);
		Belegung belegungTest = new Belegung(BelegungArt.BUCHUNG,
				LocalDate.now(), zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);
		belegungTest = new Belegung(BelegungArt.BUCHUNG, datumBis.plusDays(1),
				zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);
		belegungTest = new Belegung(BelegungArt.BUCHUNG, datumBis.plusDays(2),
				zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);
		belegungTest = new Belegung(BelegungArt.BUCHUNG, datumBis.plusDays(3),
				zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);

		List<Belegung> alleBelegung = belegungDao.getAll();
		assertEquals("Anzahl der Belegungen: ", 4, alleBelegung.size());

		alleBelegung = belegungDao.getAllInZeitraum(LocalDate.now(),
				datumBis.plusDays(2));
		assertEquals("Anzahl der Belegungen: ", 3, alleBelegung.size());
	}

	/**
	 * testDelete Test, if a object can be deleted
	 * After the creation of a new Gast, Zimmer, BelegungKopf and 3 Belegung-objects
	 * only the Belegung (and BelegungKopf)-objects is deleted.
	 * The test indicates if the deleted Belegung-objects do not exist in the database any longer
	 * The created Gast and Zimmer objects must be in the database.
	 */
	@Test
	public void testDelete() {
		LocalDate datumBis = LocalDate.now();
		Gast gastTest = new Gast("Hans", "Dampf", "Musterstraße", "94555",
				"Musterstadt", "Deutschland", "08541", "0160", "08541",
				"f.g@h.ij", "etwas längerer Kommentar");
		gastDao.saveOrUpdate(gastTest);
		Zimmer zimmerTest = new Zimmer(1, "Beschreibung", 12.23f);
		zimmerDao.saveOrUpdate(zimmerTest);
		BelegungKopf belegungKopfTest = new BelegungKopf(gastTest);
		belegungKopfDao.saveOrUpdate(belegungKopfTest);
		Belegung belegungTest = new Belegung(BelegungArt.BUCHUNG,
				LocalDate.now(), zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);
		belegungTest = new Belegung(BelegungArt.BUCHUNG, datumBis.plusDays(1),
				zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);
		belegungTest = new Belegung(BelegungArt.BUCHUNG, datumBis.plusDays(2),
				zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);
		belegungTest = new Belegung(BelegungArt.BUCHUNG, datumBis.plusDays(3),
				zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);

		List<Belegung> alleBelegung = belegungDao.getAll();

		for (Belegung z : alleBelegung) {
			belegungDao.delete(z);
		}

		alleBelegung = belegungDao.getAll();
		assertEquals("Die Tabelle darf keine Zeile enthalten", 0,
				alleBelegung.size());
		
		List<Gast> gastTestList = gastDao.getAll();		
		for (Gast gast : gastTestList) {
			assertEquals("Die Tabelle soll einen Gast enthalten", "f.g@h.ij",gast.getEmail());
		}
		
	}

	/**
	 * testDeleteAll Test, bulk-delete
	 */
	@Test
	public void testDeleteAll() {
		LocalDate datumBis = LocalDate.now();
		Gast gastTest = new Gast("Hans", "Dampf", "Musterstraße", "94555",
				"Musterstadt", "Deutschland", "08541", "0160", "08541",
				"f.g@h.ij", "etwas längerer Kommentar");
		gastDao.saveOrUpdate(gastTest);
		Zimmer zimmerTest = new Zimmer(1, "Beschreibung", 12.23f);
		zimmerDao.saveOrUpdate(zimmerTest);
		BelegungKopf belegungKopfTest = new BelegungKopf(gastTest);
		belegungKopfDao.saveOrUpdate(belegungKopfTest);
		Belegung belegungTest = new Belegung(BelegungArt.BUCHUNG,
				LocalDate.now(), zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);
		belegungTest = new Belegung(BelegungArt.BUCHUNG, datumBis.plusDays(1),
				zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);
		belegungTest = new Belegung(BelegungArt.BUCHUNG, datumBis.plusDays(2),
				zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);
		belegungTest = new Belegung(BelegungArt.BUCHUNG, datumBis.plusDays(3),
				zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);

		List<Belegung> alleBelegung = belegungDao.getAll();

		belegungDao.deleteAll();

		alleBelegung = belegungDao.getAll();
		assertEquals("Die Tabelle darf keine Zeile enthalten", 0,
				alleBelegung.size());

	}

	/**
	 * testBelegungKopfCreate Test, if a gast-, zimmer-, belegung- und
	 * belegungKopf-object can be created
	 */
	@Test
	public void testBelegungKopfCreate() {
		LocalDate datumBis = LocalDate.now();
		Gast gastTest = new Gast("Hans", "Dampf", "Musterstraße", "94555",
				"Musterstadt", "Deutschland", "08541", "0160", "08541",
				"f.g@h.ij", "etwas längerer Kommentar");
		gastDao.saveOrUpdate(gastTest);
		Zimmer zimmerTest = new Zimmer(1, "Beschreibung", 12.23f);
		zimmerDao.saveOrUpdate(zimmerTest);

		belegungKopfDao.create(BelegungArt.RESERVIERUNG, LocalDate.now(),
				datumBis.plusDays(2), zimmerTest, gastTest);

		List<BelegungKopf> alleBelegungKopf = belegungKopfDao.getAll();

		assertEquals("Es soll 1 BelegungKopf vorhanden sein", 1,
				alleBelegungKopf.size());
	}

	/**
	 * testDeleteAll Test, bulk-delete
	 * After the creation of the necessary objects the test indicates, if the right
	 * date values are in the database
	 * @throws Exception
	 */
	@Test
	public void testAlleBelegungenVorhanden() throws Exception {
		LocalDate datumBis = LocalDate.now();
		Gast gastTest = new Gast("Hans", "Dampf", "Musterstraße", "94555",
				"Musterstadt", "Deutschland", "08541", "0160", "08541",
				"f.g@h.ij", "etwas längerer Kommentar");
		gastDao.saveOrUpdate(gastTest);
		Zimmer zimmerTest = new Zimmer(1, "Beschreibung", 12.23f);
		zimmerDao.saveOrUpdate(zimmerTest);
		BelegungKopf belegungKopfTest = new BelegungKopf(gastTest);
		belegungKopfDao.saveOrUpdate(belegungKopfTest);
		Belegung belegungTest = new Belegung(BelegungArt.BUCHUNG,
				LocalDate.now(), zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);
		belegungTest = new Belegung(BelegungArt.BUCHUNG, datumBis.plusDays(1),
				zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);
		belegungTest = new Belegung(BelegungArt.BUCHUNG, datumBis.plusDays(2),
				zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);
		belegungTest = new Belegung(BelegungArt.BUCHUNG, datumBis.plusDays(3),
				zimmerTest, gastTest, belegungKopfTest);
		belegungDao.save(belegungTest);

		List<Belegung> alleBelegungen = belegungDao.getAll();
		assertEquals("Die erste Belegung muss heutiges Datum haben: ",
				alleBelegungen.get(0).getDatum(), LocalDate.now());
		assertEquals("Die zweite Belegung muss heutiges Datum + 1 haben: ",
				alleBelegungen.get(1).getDatum(), datumBis.plusDays(1));
		assertEquals("Die dritte Belegung muss heutiges Datum + 2 haben: ",
				alleBelegungen.get(2).getDatum(), datumBis.plusDays(2));
		assertEquals("Die vierte Belegung muss heutiges Datum + 3 haben: ",
				alleBelegungen.get(3).getDatum(), datumBis.plusDays(3));

	}

}
