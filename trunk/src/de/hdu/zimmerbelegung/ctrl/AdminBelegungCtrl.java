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

public class AdminBelegungCtrl {
	ListModelList<BelegungKopf> belegungKopfList;
	ListModelList<Belegung> belegungList;
	ListModelList<Gast> gastList;
	ListModelList<Zimmer> zimmerList;
	BelegungKopf belegungKopfSelected;
	BelegungKopfDummy belegungKopfDummy;
	
	public BelegungArt[] getAllBelegungArt() {
		return BelegungArt.getAll();
	}

	public BelegungKopf getBelegungKopfSelected() {
		return belegungKopfSelected;
	}

	public void setBelegungKopfSelected(BelegungKopf belegungKopfSelected) {
		;
		this.belegungKopfSelected = belegungKopfSelected;
	}

	Belegung belegungSelected;

	public void setBelegungSelected(Belegung selected) {
		this.belegungSelected = selected;
	}

	public Belegung getBelegungSelected() {
		return belegungSelected;
	}

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

	public ListModel<Gast> getGastList() {
		if (gastList == null) {
			// Liste initialisieren
			gastList = new ListModelList<Gast>();
			GastDao gastDao = ServiceLocator.getGastDao();
			gastList.addAll(gastDao.getAll());
		}
		return gastList;
	}

	public ListModel<Zimmer> getZimmerList() {
		if (zimmerList == null) {
			// Liste initialisieren
			zimmerList = new ListModelList<Zimmer>();
			ZimmerDao zimmerDao = ServiceLocator.getZimmerDao();
			zimmerList.addAll(zimmerDao.getAll());
		}
		return zimmerList;
	}

	public BelegungKopfDummy getBelegungKopfDummy() {
		return belegungKopfDummy;
	}

	public void setBelegungKopfDummy(BelegungKopfDummy belegungKopfDummy) {
		this.belegungKopfDummy = belegungKopfDummy;
	}

	@Command
	@NotifyChange({ "belegungKopfDummy" })
	public void doNew() {
		this.belegungKopfDummy = new BelegungKopfDummy();
	}

	@Command
	public void doSave() {
		BelegungKopfDao belegungKopfDao = ServiceLocator.getBelegungKopfDao();
		belegungKopfDao.create(belegungKopfDummy.getArt(),
				belegungKopfDummy.getDatumVon(),
				belegungKopfDummy.getDatumBis(), belegungKopfDummy.getZimmer(),
				belegungKopfDummy.getGast());
	}
}