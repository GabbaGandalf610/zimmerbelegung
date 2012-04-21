package de.hdu.zimmerbelegung.dao;

import java.util.List;

import org.hibernate.Session;
import org.zkoss.zkplus.hibernate.HibernateUtil;

import de.hdu.zimmerbelegung.model.ZimmerKategorie;

public class ZimmerKategorieDao {
    Session currentSession() {
        return HibernateUtil.currentSession();
    }
	public ZimmerKategorie findById(int id) {
		Session sess =  currentSession();
        return (ZimmerKategorie) sess.load(ZimmerKategorie.class, id);
	}
    public void saveOrUpdate(ZimmerKategorie zimmerKategorie) {
        Session sess =  currentSession();
        sess.saveOrUpdate(zimmerKategorie);
    }
	public void delete(ZimmerKategorie zimmerKategorie) {
		Session sess =  currentSession();
		sess.delete(zimmerKategorie);
	}
	@SuppressWarnings("unchecked")
	public List<ZimmerKategorie> findAll() {
		Session sess =  currentSession();
		return sess.createQuery("from ZimmerKategorie").list();
	}
}
