package de.hdu.zimmerbelegung.service;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.hdu.zimmerbelegung.dao.BuchungDao;
import de.hdu.zimmerbelegung.dao.GastDao;
import de.hdu.zimmerbelegung.dao.ReservierungDao;
import de.hdu.zimmerbelegung.dao.TagStatusDao;
import de.hdu.zimmerbelegung.dao.ZeitraumStatusDao;
import de.hdu.zimmerbelegung.dao.ZimmerDao;
import de.hdu.zimmerbelegung.manager.AdminManager;
import de.hdu.zimmerbelegung.manager.BelegungManager;

public class ServiceLocator {
	private static ApplicationContext ctx;

	static {
		ctx = new ClassPathXmlApplicationContext("spring-hibernate.xml");
	}

	private ServiceLocator() {
	}

	public static SessionFactory getSessionFactory() {
		return (SessionFactory) ctx.getBean("factory", SessionFactory.class);
	}

	public static BuchungDao getBuchungDao() {
		return (BuchungDao) ctx.getBean("buchungDao", BuchungDao.class);
	}

	public static GastDao getGastDao() {
		return (GastDao) ctx.getBean("gastDao", GastDao.class);
	}

	public static ZimmerDao getZimmerDao() {
		return (ZimmerDao) ctx.getBean("zimmerDao", ZimmerDao.class);
	}

	public static BelegungManager getBuchungManager() {
		return (BelegungManager) ctx.getBean("buchungManager", BelegungManager.class);
	}
	
	public static ReservierungDao getReservierungDao() {
		return (ReservierungDao) ctx.getBean("reservierungDao", ReservierungDao.class);
	}

	public static AdminManager getAdminManager() {
		return (AdminManager) ctx.getBean("AdminManager", AdminManager.class);
	}


	public static TagStatusDao getTagStatusDao() {
		return (TagStatusDao) ctx.getBean("tagStatusDao", TagStatusDao.class);
	}

	public static ZeitraumStatusDao getZeitraumStatusDao() {
		return (ZeitraumStatusDao) ctx.getBean("zeitraumStatusDao", ZeitraumStatusDao.class);

	}
}