package de.hdu.zimmerbelegung.dao;

//import java.util.Date;
import java.util.List;
 
import org.hibernate.Session;
import org.zkoss.zkplus.hibernate.HibernateUtil;
import de.hdu.zimmerbelegung.model.Gast;

	public class GastDao {
    Session currentSession() {
        return HibernateUtil.currentSession();
    }
    
    public void saveOrUpdate(Gast anEvent, String name, String vorname, String kommentar) {
        Session sess =  currentSession();
        anEvent.setName(name);
        anEvent.setVorname(vorname);
        anEvent.setKommentar(kommentar);
        sess.saveOrUpdate(anEvent);
    }    

    public void delete(Gast anEvent) {
        Session sess =  currentSession();
        sess.delete(anEvent);
    }
    
    public Gast findById(String name) {
        Session sess =  currentSession();
        return (Gast) sess.load(Gast.class, name);
    } 
    
    public List findAll() {
        Session sess =  currentSession();
        return sess.createQuery("from Gast").list();
    }    
    
}
