/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//import org.apache.log4j.Logger;

public class Startup {
private String defaultDBurl;
	//	private static Logger log = Logger.getLogger(Startup.class);
	private String driverClass;
	private String newDB;
	private String password;
	private String user;

	public void initDB() {
		// attempt to create the user specified database
		try {
//			log.info("Try to create database " + newDB);
			Class.forName(driverClass);
			Connection db = DriverManager.getConnection(defaultDBurl, user, password);
			Statement st = db.createStatement();
			// create the user specified database. Assigned ownership to
			// user specified super user
			String command = "CREATE DATABASE " + newDB 
//					+ "\" WITH OWNER = \"" + user + "\" ENCODING = 'UTF-8' TABLESPACE = pg_default"
					;
//			log.info(command);
			st.execute(command);
			st.close();
			db.close();
//			log.info("Creating database " + newDB + " successful!");
		} catch (Exception e) {
//			log.info("Create database " + newDB + " failed - it probably already exists.");
		}
	}

	public void setDefaultDBurl(String defaultDBurl) {
		this.defaultDBurl = defaultDBurl;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public void setNewDB(String newDB) {
		this.newDB = newDB;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
