/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package de.hdu.zimmerbelegung.ctrl;

import org.joda.time.LocalDate;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.SimpleDateConstraint;

import de.hdu.zimmerbelegung.dao.BelegungKopfDao;
import de.hdu.zimmerbelegung.dao.GastDao;
import de.hdu.zimmerbelegung.dto.ZimmerZeitraumBelegungDto;
import de.hdu.zimmerbelegung.helper.BelegungArt;
import de.hdu.zimmerbelegung.helper.Status;
import de.hdu.zimmerbelegung.helper.ZimmerZeitraumBelegung;
import de.hdu.zimmerbelegung.model.Belegung;
import de.hdu.zimmerbelegung.model.BelegungKopf;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class IndexCtrl {
	LocalDate datumBis = new LocalDate();
	LocalDate datumVon = new LocalDate();
	ListModelList<Gast> gastList;
	Gast gastSelected;
	String gastSuche;
	ListModelList<Belegung> belegungList;
	ListModelList<BelegungKopf> belegungKopfList;
	BelegungKopf gastZimmerZeitSelected;

	public BelegungKopf getGastZimmerZeitSelected() {
		return gastZimmerZeitSelected;
	}

	public void setGastZimmerZeitSelected(BelegungKopf gastZimmerZeitSelected) {
		this.gastZimmerZeitSelected = gastZimmerZeitSelected;
	}

	public BelegungArt[] getAllBelegungArt() {
		return BelegungArt.getAll();
	}

	private final int MAX_BUCHUNGSLAENGE = 1;

	ListModelList<ZimmerZeitraumBelegung> zimmerZeitraumBelegungList;

	ZimmerZeitraumBelegung zimmerZeitraumBelegungSelected;

	@DependsOn({ "gastSelected", "datumVon" })
	@NotifyChange({ "zimmerZeitraumBelegungSelected" })
	public ListModel<BelegungKopf> getBelegungKopfList() {
		if ((gastSelected == null) || (gastSelected.getId() == 0)) {
			return null;
		}
		{
			// Liste initialisieren
			belegungKopfList = new ListModelList<BelegungKopf>();
			for (BelegungKopf belegungsKopf : this.gastSelected
					.getBelegungKopf()) {
				belegungKopfList.add((BelegungKopf) belegungsKopf);
			}
		}
		return belegungKopfList;
	}

	@NotifyChange({ "gastSelected", "zimmerZeitraumBelegungList" })
	@Command
	public void doBuchen() throws Exception {
		if (!isZusammenfassungAnzeigen() && gastZimmerZeitSelected == null)
			return;

		BelegungKopfDao belegungKopfDao = ServiceLocator.getBelegungKopfDao();

		if (gastZimmerZeitSelected != null && !isZusammenfassungAnzeigen()) {
			belegungKopfDao.setArt(gastZimmerZeitSelected, BelegungArt.BUCHUNG);
			zimmerZeitraumBelegungSelected = null;
		} else {
			belegungKopfDao.create(BelegungArt.BUCHUNG, datumVon, datumBis,
					zimmerZeitraumBelegungSelected.getZimmer(), gastSelected);
			GastDao gastDao = ServiceLocator.getGastDao();
			gastDao.saveOrUpdate(gastSelected);
			zimmerZeitraumBelegungSelected = null;
		}

	}

	@NotifyChange({ "gastSelected", "zimmerZeitraumBelegungList" })
	@Command
	public void doReservieren() throws Exception {
		System.out.println(gastZimmerZeitSelected);
		if (!isZusammenfassungAnzeigen() && gastZimmerZeitSelected == null)
			return;

		BelegungKopfDao belegungKopfDao = ServiceLocator.getBelegungKopfDao();

		if (gastZimmerZeitSelected != null && !isZusammenfassungAnzeigen()) {
			belegungKopfDao.setArt(gastZimmerZeitSelected,
					BelegungArt.RESERVIERUNG);
			zimmerZeitraumBelegungSelected = null;
		} else {

			belegungKopfDao.create(BelegungArt.RESERVIERUNG, datumVon,
					datumBis, zimmerZeitraumBelegungSelected.getZimmer(),
					gastSelected);
			GastDao gastDao = ServiceLocator.getGastDao();
			gastDao.saveOrUpdate(gastSelected);
			zimmerZeitraumBelegungSelected = null;
		}
	}

	@NotifyChange({ "gastSelected", "zimmerZeitraumBelegungList" })
	@Command
	public void doStorno() throws Exception {
		if (gastZimmerZeitSelected != null) {
			BelegungKopfDao belegungKopfDao = ServiceLocator
					.getBelegungKopfDao();
			belegungKopfDao.storno(gastZimmerZeitSelected);
			// belegungKopfList.remove(gastZimmerZeitSelected);
			this.getBelegungKopfList();

		} else {
			throw new UiException("Keine Belegung ausgewählt!");
		}
	}

	@Command
	@NotifyChange({ "gastSelected", "gastList" })
	public void doDelete() {
		GastDao gastDao = ServiceLocator.getGastDao();
		gastDao.delete(gastSelected);
		gastList.remove(gastSelected);
		gastSelected = null;
	}

	@Command
	public void doGuestSearch() {
		gastList.clear();
		GastDao gastDao = ServiceLocator.getGastDao();
		System.out.println(gastSuche);
		gastList.addAll(gastDao.getAllFilteredUser(gastSuche));
		gastSelected = null;
	}

	@Command
	@NotifyChange({ "gastSelected", "gastList" })
	public void doNew() {
		gastSelected = new Gast();
		System.out.println(gastSelected.getId());
	}

	@Command
	@NotifyChange({ "gastSelected", "gastList" })
	public void doSave() {
		if (gastSelected.getId() == 0)
			gastList.add(gastSelected);
		GastDao gastDao = ServiceLocator.getGastDao();
		gastDao.saveOrUpdate(gastSelected);

	}

	@DependsOn("datumVon")
	public LocalDate getDatumBis() {
		if (datumBis.isBefore(datumVon)
				|| datumBis.isAfter(datumVon.plusMonths(MAX_BUCHUNGSLAENGE))) {
			datumBis = datumVon;
		}
		return datumBis;
	}

	@DependsOn("datumVon")
	public SimpleDateConstraint getDatumBisConstraint() {
		return new SimpleDateConstraint("between "
				+ datumVon.toString("yyyMMdd") + " and "
				+ datumVon.plusMonths(MAX_BUCHUNGSLAENGE).toString("yyyMMdd"));
	}

	@DependsOn({ "datumVon", "datumBis" })
	public ListModel<ZimmerZeitraumBelegung> getZimmerZeitraumBelegungList() {
		zimmerZeitraumBelegungSelected = null;
		if (zimmerZeitraumBelegungList == null) {
			zimmerZeitraumBelegungList = new ListModelList<ZimmerZeitraumBelegung>();
		} else {
			zimmerZeitraumBelegungList.clear();
		}
		ZimmerZeitraumBelegungDto zimmerZeitraumBelegungDto = ServiceLocator
				.getZimmerZeitraumBelegungDto();
		zimmerZeitraumBelegungList.addAll(zimmerZeitraumBelegungDto.getAll(
				datumVon, datumBis));
		return zimmerZeitraumBelegungList;
	}

	@DependsOn({ "datumVon", "datumBis" })
	public ZimmerZeitraumBelegung getZimmerZeitraumBelegungSelected() {
		return zimmerZeitraumBelegungSelected;
	}

	@DependsOn({ "datumVon", "datumBis", "zimmerZeitraumBelegungSelected",
			"gastSelected" })
	public boolean isZusammenfassungAnzeigen() {
		if (datumVon == null || datumBis == null
				|| zimmerZeitraumBelegungSelected == null
				|| gastSelected == null || gastSelected.getId() == 0) {
			return false;
		}
		if (zimmerZeitraumBelegungSelected.getStatus() != Status.FREI) {
			return false;
		}
		return true;
	}

	public ListModel<Gast> getItems() {
		if (gastList == null) {
			gastList = new ListModelList<Gast>();
			GastDao gastDao = ServiceLocator.getGastDao();
			gastList.addAll(gastDao.getAll());
		}
		return gastList;
	}

	public void setDatumBis(LocalDate datumBis) {
		this.datumBis = datumBis;
	}

	public void setDatumVon(LocalDate datumVon) {
		this.datumVon = datumVon;
	}

	public void setGastSelected(Gast gastSelected) {
		this.gastSelected = gastSelected;
	}

	public Gast getGastSelected() {
		return gastSelected;
	}

	public LocalDate getDatumVon() {
		return datumVon;
	}

	public String getGastSuche() {
		return gastSuche;
	}

	public void setGastSuche(String gastSuche) {
		this.gastSuche = gastSuche;
	}

	public void setZimmerZeitraumBelegungSelected(
			ZimmerZeitraumBelegung zimmerZeitraumBelegungSelected) {
		this.zimmerZeitraumBelegungSelected = zimmerZeitraumBelegungSelected;
	}

}