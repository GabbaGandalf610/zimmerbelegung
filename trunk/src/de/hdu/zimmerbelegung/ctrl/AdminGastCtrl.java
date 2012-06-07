/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.dao.GastDao;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.service.ServiceLocator;

/**
 * The Data binding class for Gast. All Interaction with the frontend regarding
 * the business logic Gast should be handled by this class!
 * 
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 */
public class AdminGastCtrl {
	ListModelList<Gast> gastList;
	Gast gastSelected;
	String gastSuche;

	/**
	 * Deletes the selected Gast
	 */
	@Command
	@NotifyChange({ "gastSelected", "gastList" })
	public void doDelete() {
		GastDao gastDao = ServiceLocator.getGastDao();
		gastDao.delete(gastSelected);
		gastList.remove(gastSelected);
		gastSelected = null;
	}

	/**
	 * Updates the gastList with the specified Gast-Filter represented by the
	 * variable gastSuche
	 */
	@Command
	public void doGuestSearch() {
		gastList.clear();
		GastDao gastDao = ServiceLocator.getGastDao();
		gastList.addAll(gastDao.getAllFilteredUser(gastSuche));
		gastSelected = null;
	}

	/**
	 * Creates a new Gast
	 */
	@Command
	@NotifyChange({ "gastSelected", "gastList" })
	public void doNew() {
		gastSelected = new Gast();
	}

	/**
	 * Saves the Gast
	 */
	@Command
	@NotifyChange({ "gastSelected", "gastList" })
	public void doSave() {
		GastDao gastDao = ServiceLocator.getGastDao();
		gastDao.saveOrUpdate(gastSelected);
		if (!gastList.contains(gastSelected))
			gastList.add(gastSelected);
	}

	/**
	 * Returns the {@link Gast} object which is selected in the GUI
	 * 
	 * @return a Gast object which contains the GUI inpunts
	 */
	public Gast getGastSelected() {
		return gastSelected;
	}

	/**
	 * Returns the variable gastSuche which contains the value of the Gast-Suche
	 */
	public String getGastSuche() {
		return gastSuche;
	}

	/**
	 * Returns a list of all {@link Gast}
	 * 
	 * @return a list of all Gast
	 */
	public ListModel<Gast> getItems() {
		if (gastList == null) {
			// Liste initialisieren
			gastList = new ListModelList<Gast>();
			GastDao gastDao = ServiceLocator.getGastDao();
			gastList.addAll(gastDao.getAll());
		}
		return gastList;
	}

	/**
	 * Puts the data of a {@link Gast} object in the corresponding fields in the
	 * GUI.
	 * 
	 * @param gastSelected
	 *            a {@link Gast} object that represents the future field values.
	 */
	public void setGastSelected(Gast gastSelected) {
		this.gastSelected = gastSelected;
	}

	/**
	 * Fills the variable gastSuche with the search string entered in the GUI
	 */
	public void setGastSuche(String gastSuche) {
		this.gastSuche = gastSuche;
	}

	/**
	 * Reset filter on the "Gast"-list
	 */
	@Command
	@NotifyChange({ "gastSelected", "gastSuche" })
	public void showAllGuests() {
		gastSelected = null;
		gastSuche = "";
		this.doGuestSearch();
	}
}
