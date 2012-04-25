package de.hdu.zimmerbelegung.service;

import java.util.List;

import de.hdu.zimmerbelegung.dao.ZimmerKategorieDao;
import de.hdu.zimmerbelegung.model.ZimmerKategorie;

public class ZimmerKategorieManager implements IZimmerKategorieManager {

	private ZimmerKategorieDao zimmerKategorieDao;
	public void setZimmerKategorieDao(ZimmerKategorieDao zimmerKategorieDao) {
		this.zimmerKategorieDao = zimmerKategorieDao;
	}

	@Override
	public List<ZimmerKategorie> getAll() {
		return zimmerKategorieDao.getAll();
	}

	@Override
	public void add(ZimmerKategorie zimmerKategorie) {
		zimmerKategorieDao.saveOrUpdate(zimmerKategorie);
	}

	@Override
	public void update(ZimmerKategorie zimmerKategorie) {
		zimmerKategorieDao.saveOrUpdate(zimmerKategorie);
	}

	@Override
	public void delete(ZimmerKategorie zimmerKategorie) {
		zimmerKategorieDao.delete(zimmerKategorie);
	}

}