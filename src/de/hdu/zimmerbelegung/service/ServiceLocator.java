package de.hdu.zimmerbelegung.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.hdu.zimmerbelegung.dao.BelegungDao;
import de.hdu.zimmerbelegung.dao.BuchungDao;
import de.hdu.zimmerbelegung.dao.GastDao;
import de.hdu.zimmerbelegung.dao.ReservierungDao;
import de.hdu.zimmerbelegung.dao.ZimmerDao;
import de.hdu.zimmerbelegung.dto.ZimmerZeitraumBelegungDto;
import de.hdu.zimmerbelegung.manager.AdminManager;
import de.hdu.zimmerbelegung.manager.BelegungManager;

public class ServiceLocator {
	private static ApplicationContext ctx;

	static {
		try {
			// Versuche Verbindung zur MySQL-Datenbank
			ctx = new ClassPathXmlApplicationContext(
					"datenbank-entwicklung.xml");
		} catch (BeanCreationException e) {
			if (e.getBeanName() == "factory") {
				// Verbindung konnte nicht hergestellt werden
				// => Versuche produktiven MSSQL-Server
				ctx = new ClassPathXmlApplicationContext(
						"datenbank-produktiv.xml");
			}
		}
	}

	private ServiceLocator() {
	}

	public static SessionFactory getSessionFactory() {
		return (SessionFactory) ctx.getBean("factory", SessionFactory.class);
	}

	public static BelegungDao getBelegungDao() {
		return (BelegungDao) ctx.getBean("belegungDao", BelegungDao.class);
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

	public static ReservierungDao getReservierungDao() {
		return (ReservierungDao) ctx.getBean("reservierungDao",
				ReservierungDao.class);
	}

	public static AdminManager getAdminManager() {
		return (AdminManager) ctx.getBean("AdminManager", AdminManager.class);
	}

	public static ZimmerZeitraumBelegungDto getZimmerZeitraumBelegungDto() {
		return (ZimmerZeitraumBelegungDto) ctx.getBean(
				"zimmerZeitraumBelegungDto", ZimmerZeitraumBelegungDto.class);
	}
}