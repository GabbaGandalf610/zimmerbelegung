/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.dao.BelegungKopfDao;
import de.hdu.zimmerbelegung.dao.GastDao;
import de.hdu.zimmerbelegung.dao.ZimmerDao;
import de.hdu.zimmerbelegung.helper.BelegungArt;
import de.hdu.zimmerbelegung.helper.BelegungKopfDummy;
import de.hdu.zimmerbelegung.model.Belegung;
import de.hdu.zimmerbelegung.model.BelegungKopf;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.Zimmer;
import de.hdu.zimmerbelegung.service.ServiceLocator;

/**
 * The Data binding class for Belegung. All Interaction with the frontend
 * regarding the business logic Belegung should be handled by this class!
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 */
public class AdminBelegungCtrl {
	ListModelList<BelegungKopf> belegungKopfList;
	ListModelList<Belegung> belegungList;
	ListModelList<Gast> gastList;
	ListModelList<Zimmer> zimmerList;
	BelegungKopf belegungKopfSelected;
	BelegungKopfDummy belegungKopfDummy;
	Belegung belegungSelected;
	
	/**
	 * Returns a enum of all choosable {@link BelegungArt}
	 * @return a enum of type BelegungArt
	 */
	public BelegungArt[] getAllBelegungArt() {
		return BelegungArt.getAll();
	}

	/**
	 * Returns the {@link BelegungKof} to the selected Belegung
	 * @return BelegungKof Object
	 */
	public BelegungKopf getBelegungKopfSelected() {
		return belegungKopfSelected;
	}

	/**
	 * Puts the data of a {@link BelegungKopf} object in the corresponding fields in the GUI.
	 * @param belegungKopfSelected a {@link Belegung} object that represents the future field values.
	 */
	public void setBelegungKopfSelected(BelegungKopf belegungKopfSelected) {
		;
		this.belegungKopfSelected = belegungKopfSelected;
	}


	/**
	 * Puts the data of a {@link Belegung} object in the corresponding fields in the GUI.
	 * @param selected a {@link Belegung} object that represents the future field values.
	 */
	public void setBelegungSelected(Belegung selected) {
		this.belegungSelected = selected;
	}

	/**
	 * Returns the {@link Belegung} object which is selected in the GUI 
	 * @return a Belegung object which contains the GUI inputs
	 */
	public Belegung getBelegungSelected() {
		return belegungSelected;
	}

	/**
	 * Returns a list of {@link BelegungKopf} to a specified Belegung
	 * @return a list of all BelegungKopf
	 */
	public ListModel<BelegungKopf> getBelegungKopfList() {
		if (belegungKopfList == null) {
			// Liste initialisieren
			belegungKopfList = new ListModelList<BelegungKopf>();
			BelegungKopfDao belegungKopfDao = ServiceLocator
					.getBelegungKopfDao();
			belegungKopfList.addAll(belegungKopfDao.getAll());
		}
		return belegungKopfList;
	}

	/**
	 * Returns a list of Belegung objects to the specified {@link BelegungKopf} object.
	 * @throws TODO
	 */
	@DependsOn({ "belegungKopfSelected" })
	public ListModel<Belegung> getBelegungList() throws Exception {
		// TODO: Fehlerbehandlung vom Konsistenzcheck
		if (belegungKopfSelected == null)
			return null;
		belegungList = new ListModelList<Belegung>();
		for (Belegung belegung : this.belegungKopfSelected.getBelegungen()) {
			belegungList.add((Belegung) belegung);
		}
		return belegungList;
	}

	/**
	 * Returns a list of {@link Gast} to a specified Belegung
	 * @return a list of type Gast
	 */
	public ListModel<Gast> getGastList() {
		if (gastList == null) {
			// Liste initialisieren
			gastList = new ListModelList<Gast>();
			GastDao gastDao = ServiceLocator.getGastDao();
			gastList.addAll(gastDao.getAll());
		}
		return gastList;
	}

	/**
	 * Returns a list of {@link Zimmer} to a specified Belegung
	 * @return a list of type Zimmer
	 */
	public ListModel<Zimmer> getZimmerList() {
		if (zimmerList == null) {
			// Liste initialisieren
			zimmerList = new ListModelList<Zimmer>();
			ZimmerDao zimmerDao = ServiceLocator.getZimmerDao();
			zimmerList.addAll(zimmerDao.getAll());
		}
		return zimmerList;
	}

	/**
	 * Returns a belegungKopfDummy object
	 * @return a belegungKopfDummy object
	 */
	public BelegungKopfDummy getBelegungKopfDummy() {
		return belegungKopfDummy;
	}

	/**
	 * Sets the data of a BelegungKopfDummy object.
	 * @param selected a {@link Belegung} object that represents the future field values.
	 */
	public void setBelegungKopfDummy(BelegungKopfDummy belegungKopfDummy) {
		this.belegungKopfDummy = belegungKopfDummy;
	}

	/**
	 * Creates a new BelegungKopfDummy object
	 */
	@Command
	@NotifyChange({ "belegungKopfDummy" })
	public void doNew() {
		this.belegungKopfDummy = new BelegungKopfDummy();
	}

    /**
     * <p>Saves the BelegungKopf</p>
     */
	@Command
	public void doSave() {
		BelegungKopfDao belegungKopfDao = ServiceLocator.getBelegungKopfDao();
		belegungKopfDao.create(belegungKopfDummy.getArt(),
				belegungKopfDummy.getDatumVon(),
				belegungKopfDummy.getDatumBis(), belegungKopfDummy.getZimmer(),
				belegungKopfDummy.getGast());
	}
}