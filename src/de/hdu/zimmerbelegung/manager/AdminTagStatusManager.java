package de.hdu.zimmerbelegung.manager;

import java.util.List;

import de.hdu.zimmerbelegung.dao.TagStatusDao;
import de.hdu.zimmerbelegung.model.TagStatus;

public class AdminTagStatusManager {

	private TagStatusDao tagStatusDao;
	
	public TagStatusDao getTagStatusDao() {
		return tagStatusDao;
	}

	public void setTagStatusDao(TagStatusDao tagStatusDao) {
		this.tagStatusDao = tagStatusDao;
	}

	public List<TagStatus> getAllTagStatus() {
		return tagStatusDao.getAll();
	}
	
	public void add(TagStatus tagStatus) {
		tagStatusDao.saveOrUpdate(tagStatus);
	}

	public void update(TagStatus tagStatus) {
		tagStatusDao.saveOrUpdate(tagStatus);
	}
	
	public void delete(TagStatus tagStatus) {
		tagStatusDao.delete(tagStatus);
	}
	
}
