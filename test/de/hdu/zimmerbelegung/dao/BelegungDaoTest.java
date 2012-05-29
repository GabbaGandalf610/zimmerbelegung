package de.hdu.zimmerbelegung.dao;

import java.util.List;


import org.joda.time.LocalDate;
import org.junit.Test;

import de.hdu.zimmerbelegung.helper.BelegungArt;
import de.hdu.zimmerbelegung.model.Belegung;
import de.hdu.zimmerbelegung.model.BelegungKopf;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.Zimmer;

public class BelegungDaoTest extends AbstractDataAccessTest {

	private BelegungDao belegungDao;
	private GastDao gastDaoTest;
	private ZimmerDao zimmerDao;
	private BelegungKopfDao belegungKopfDao;
	private String tableName = "Belegung";

	public void setBelegungDao(BelegungDao belegungDao) {
		this.belegungDao = belegungDao;
	}

	public void setGastDaoTest(GastDao gastDao) {
		this.gastDaoTest = gastDao;
	}

	public void setZimmerDao(ZimmerDao zimmerDao) {
		this.zimmerDao = zimmerDao;
	}

	public void setGastDao(BelegungDao belegungDao) {
		this.belegungDao = belegungDao;
	}

	public void setBelegungKopfDao(BelegungKopfDao belegungKopfDao) {
		this.belegungKopfDao = belegungKopfDao;
	}

	@Test
	public void tests(){
		System.out.println("Hallo");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testFindById() {
		String[] tables = { tableName };
		// delete all rows from db table
//		deleteFromTables(tables);
		// create new line in table
		Gast gastTest = new Gast("Katharina", "Ecker", "Musterstraße", "94555",
				"Künzing", "Deutschland", "08541", "0160", "08541", "f.g@h.ij",
				"etwas längerer Kommentar");
		gastDaoTest.saveOrUpdate(gastTest);
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
