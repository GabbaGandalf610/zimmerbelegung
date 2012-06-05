/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.dao;

import org.hibernate.SessionFactory;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

@SuppressWarnings("deprecation")
public class AbstractDataAccessTest extends
		AbstractTransactionalDataSourceSpringContextTests {

	//TODO: Umstellung auf "TestContext":
	// http://static.springsource.org/spring/docs/3.0.0.RC1/reference/html/ch09s03.html#testcontext-framework
	protected SessionFactory sessionFactory;
	
	/**
	 * Reference the Spring configuration file for the test case.
	 */
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "datenbank-test.xml" };
	}

	/**
	 * Spring will automatically inject the SessionFactory instance on startup.
	 * Only necessary for Hibernate-backed DAO testing
	 */
	public void setSessionFactory(SessionFactory factory) {
		this.sessionFactory = factory;
	}

}
