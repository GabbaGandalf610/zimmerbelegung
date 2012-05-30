/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import de.hdu.zimmerbelegung.model.BelegungKopf;
import de.hdu.zimmerbelegung.model.Gast;

public class BelegungKopfDaoTest extends AbstractDataAccessTest {

	private BelegungKopfDao belegungKopfDao;
	@Resource
	private GastDao gastDao;

	private String tableName = "BelegungKopf";
	
	public void setBelegungKopfDao(BelegungKopfDao belegungKopfDao) {
		this.belegungKopfDao = belegungKopfDao;
	}
	
	@Resource
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
		Gast gastNeu = new Gast("Franz", "Wagner", "Kapellenstraße 66", "94527", "Aholming/Neutiefenweg", "Deutschland", "09931 5555555", "0175", "09931", "a.b@c.de", "kurzer Kommentar");
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
