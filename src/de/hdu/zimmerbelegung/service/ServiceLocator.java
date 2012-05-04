package de.hdu.zimmerbelegung.service;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.hdu.zimmerbelegung.dao.BuchungDao;
import de.hdu.zimmerbelegung.dao.GastDao;
import de.hdu.zimmerbelegung.dao.ZimmerDao;
import de.hdu.zimmerbelegung.manager.AdminTagStatusManager;
import de.hdu.zimmerbelegung.manager.AdminZeitraumStatusManager;
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
		return (BelegungManager) ctx.getBean("buchungManager",
				BelegungManager.class);
	}

	public static AdminManager getAdminManager() {
		return (AdminManager) ctx.getBean("AdminManager", AdminManager.class);
	}


	public static AdminTagStatusManager getAdminTagStatusManager() {
		return (AdminTagStatusManager) ctx.getBean("adminTagStatusManager",
				AdminTagStatusManager.class);
	}

	public static AdminZeitraumStatusManager getAdminZeitraumStatusManager() {
		return (AdminZeitraumStatusManager) ctx.getBean(
				"adminZeitraumStatusManager", AdminZeitraumStatusManager.class);
	}
}