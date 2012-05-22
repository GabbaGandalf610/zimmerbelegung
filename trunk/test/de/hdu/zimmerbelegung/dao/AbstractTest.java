package de.hdu.zimmerbelegung.dao;

import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/datenbank-test.xml", "/datenbank-test.xml"})
public abstract class AbstractTest {

	@Autowired
	private GastDao gastDao;
	@Autowired
	private BelegungDao belegungDao;
	@Autowired
	private ZimmerDao zimmerDao;
	
	protected SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory factory) {
		this.sessionFactory = factory;
	}
	
	
	public void deleteAll(){
		belegungDao.deleteAll();
		zimmerDao.deleteAll();
		gastDao.deleteAll();
	}
	
	
	
//	public void deleteTables(){
//		getHibernateTemplate().delete(GastDao.class);
//		
//	}
	
	
	
}
