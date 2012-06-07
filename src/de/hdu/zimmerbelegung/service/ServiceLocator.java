/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.hdu.zimmerbelegung.dao.BelegungDao;
import de.hdu.zimmerbelegung.dao.BelegungKopfDao;
import de.hdu.zimmerbelegung.dao.GastDao;
import de.hdu.zimmerbelegung.dao.ZimmerDao;
import de.hdu.zimmerbelegung.dto.ZimmerZeitraumBelegungDto;

public class ServiceLocator {
	private static ApplicationContext ctx;

	/**
	 * switch between development and live database
	 * if the development database is unreachable (in live case)
	 * the connection to the live database will be initialized 
	 */
	static {
		try {
			// Versuche Verbindung zum MSSQL-Server
			ctx = new ClassPathXmlApplicationContext(
					"datenbank-produktiv.xml");
		} catch (BeanCreationException e) {
			// Verbindung konnte nicht hergestellt werden
			// => Versuche Entwicklungssystem MySQL-Datenbank
			ctx = new ClassPathXmlApplicationContext("datenbank-entwicklung.xml");
		}
	}
	
	/**
	 * constructor for ServiceLocator
	 */
	private ServiceLocator() {
	}

	/**
	 * services the BelegungDao-bean
	 * @return bean BelegungDao
	 */
	public static BelegungDao getBelegungDao() {
		return ctx.getBean("belegungDao", BelegungDao.class);
	}

	/**
	 * services the BelegungKopfDao-bean
	 * @return bean BelegungKopfDao
	 */
	public static BelegungKopfDao getBelegungKopfDao() {
		return ctx.getBean("belegungKopfDao", BelegungKopfDao.class);
	}

	/**
	 * services the GastDao-bean
	 * @return bean GastDao
	 */
	public static GastDao getGastDao() {
		return ctx.getBean("gastDao", GastDao.class);
	}

	/**
	 * services the SessionFactory-bean
	 * @return bean SessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return ctx.getBean("factory", SessionFactory.class);
	}

	/**
	 * services the ZimmerDao-bean
	 * @return bean ZimmerDao
	 */
	public static ZimmerDao getZimmerDao() {
		return ctx.getBean("zimmerDao", ZimmerDao.class);
	}

	/**
	 * services the ZimmerZeitraumBelegungDto-bean
	 * @return bean ZimmerZeitraumBelegungDto
	 */
	public static ZimmerZeitraumBelegungDto getZimmerZeitraumBelegungDto() {
		return ctx.getBean("zimmerZeitraumBelegungDto",
				ZimmerZeitraumBelegungDto.class);
	}


}