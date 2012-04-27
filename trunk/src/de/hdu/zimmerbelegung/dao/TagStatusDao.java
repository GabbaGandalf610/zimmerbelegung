package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.model.TagStatus;

public class TagStatusDao extends HibernateDaoSupport {
	public TagStatus get(int id) {
		return getHibernateTemplate().load(TagStatus.class, id);
	}

	public void saveOrUpdate(TagStatus tagStatus) {
		getHibernateTemplate().saveOrUpdate(tagStatus);
	}

	public void delete(TagStatus tagStatus) {
		getHibernateTemplate().delete(tagStatus);
	}

	@SuppressWarnings("unchecked")
	public List<TagStatus> getAll() {
		return getHibernateTemplate().find("FROM TagStatus");
	}
}