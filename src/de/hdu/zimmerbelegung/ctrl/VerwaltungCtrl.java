/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package de.hdu.zimmerbelegung.ctrl;

import org.joda.time.LocalDate;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.NotifyChange;
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

/**
 * Controller for "verwaltung.zul" which is the main application view. This
 * class manages the whole process of selecting the timespan (datumVon,
 * datumBis), the room (Zimmer) and the guest (Gast) to place a booking
 * (Buchung) or reservation (Reservierung)
 * 
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 */
public class VerwaltungCtrl {
	ListModelList<BelegungKopf> belegungKopfList;
	BelegungKopf belegungKopfSelected;

	ListModelList<Belegung> belegungList;
	LocalDate datumBis = new LocalDate();
	LocalDate datumVon = new LocalDate();
	ListModelList<Gast> gastList;
	Gast gastSelected;
	String gastSuche;
	private final int MAX_BUCHUNGSLAENGE = 1;

	ListModelList<ZimmerZeitraumBelegung> zimmerZeitraumBelegungList;

	ZimmerZeitraumBelegung zimmerZeitraumBelegungSelected;

	/**
	 * Use the current Zimmer, Gast and timespan to create a "Buchung"
	 */
	@NotifyChange({ "gastSelected", "zimmerZeitraumBelegungList" })
	@Command
	public void doBuchen() throws Exception {
		if (!isZusammenfassungAnzeigen() && belegungKopfSelected == null)
			return;

		BelegungKopfDao belegungKopfDao = ServiceLocator.getBelegungKopfDao();

		if (belegungKopfSelected != null && !isZusammenfassungAnzeigen()) {
			belegungKopfDao.setArt(belegungKopfSelected, BelegungArt.BUCHUNG);
			zimmerZeitraumBelegungSelected = null;
		} else {
			belegungKopfDao.create(BelegungArt.BUCHUNG, datumVon, datumBis,
					zimmerZeitraumBelegungSelected.getZimmer(), gastSelected);
			GastDao gastDao = ServiceLocator.getGastDao();
			gastDao.saveOrUpdate(gastSelected);
			zimmerZeitraumBelegungSelected = null;
		}

	}

	/**
	 * Turn the selected "Reservierung" into a "Buchung"
	 * 
	 * @param belegungKopf
	 *            The Belegung which is supposed to be changed
	 * @throws Exception
	 */
	@NotifyChange({ "gastSelected", "zimmerZeitraumBelegungList",
			"zimmerZeitraumBelegungSelected", "belegungKopfList" })
	@Command
	public void doChangeToBuchung(
			@BindingParam("belegungKopf") BelegungKopf belegungKopf)
			throws Exception {
		BelegungKopfDao belegungKopfDao = ServiceLocator.getBelegungKopfDao();
		belegungKopfDao.setArt(belegungKopf, BelegungArt.BUCHUNG);
	}

	/**
	 * Turn the selected "Buchung" into a "Reservierung"
	 * 
	 * @param belegungKopf
	 *            The Belegung which is supposed to be changed
	 * @throws Exception
	 */
	@NotifyChange({ "gastSelected", "zimmerZeitraumBelegungList",
			"zimmerZeitraumBelegungSelected", "belegungKopfList" })
	@Command
	public void doChangeToReservierung(
			@BindingParam("belegungKopf") BelegungKopf belegungKopf)
			throws Exception {
		BelegungKopfDao belegungKopfDao = ServiceLocator.getBelegungKopfDao();
		belegungKopfDao.setArt(belegungKopf, BelegungArt.RESERVIERUNG);
	}

	/**
	 * Delete selected "Gast"
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
	 * Apply a filter on the "Gast"-list
	 */
	@Command
	@NotifyChange({ "gastSelected" })
	public void doGuestSearch() {
		gastList.clear();
		GastDao gastDao = ServiceLocator.getGastDao();
		gastList.addAll(gastDao.getAllFilteredUser(gastSuche));
		gastSelected = null;
	}

	/**
	 * Create new "Gast"
	 */
	@Command
	@NotifyChange({ "gastSelected", "gastList" })
	public void doNew() {
		gastSelected = new Gast();
	}

	/**
	 * Use the current Zimmer, Gast and timespan to create a "Reservierung"
	 */
	@NotifyChange({ "gastSelected", "zimmerZeitraumBelegungList",
			"zimmerZeitraumBelegungSelected" })
	@Command
	public void doReservieren() throws Exception {
		if (!isZusammenfassungAnzeigen())
			return;

		BelegungKopfDao belegungKopfDao = ServiceLocator.getBelegungKopfDao();

		belegungKopfDao.create(BelegungArt.RESERVIERUNG, datumVon, datumBis,
				zimmerZeitraumBelegungSelected.getZimmer(), gastSelected);
		GastDao gastDao = ServiceLocator.getGastDao();
		gastDao.saveOrUpdate(gastSelected);
		zimmerZeitraumBelegungSelected = null;
	}

	/**
	 * Save selected "Gast"
	 */
	@Command
	@NotifyChange({ "gastSelected", "gastList" })
	public void doSave() {
		if (gastSelected.getId() == 0)
			gastList.add(gastSelected);
		GastDao gastDao = ServiceLocator.getGastDao();
		gastDao.saveOrUpdate(gastSelected);

	}

	/**
	 * Delete the selected "Belegung"
	 * 
	 * @param belegungKopf
	 *            The Belegung which is supposed to be deleted
	 * @throws Exception
	 */
	@NotifyChange({ "gastSelected", "zimmerZeitraumBelegungList",
			"zimmerZeitraumBelegungSelected", "belegungKopfList" })
	@Command
	public void doStorno(@BindingParam("belegungKopf") BelegungKopf belegungKopf)
			throws Exception {
		BelegungKopfDao belegungKopfDao = ServiceLocator.getBelegungKopfDao();
		belegungKopfDao.storno(belegungKopf);
		GastDao gastDao = ServiceLocator.getGastDao();
		gastDao.refresh(gastSelected);
	}

	public BelegungArt[] getAllBelegungArt() {
		return BelegungArt.getAll();
	}

	/**
	 * Get the history of "Belegungen" for the selected "Gast"
	 * 
	 * @return list of "Belegungen"
	 */
	@DependsOn({ "gastSelected", "datumVon" })
	@NotifyChange({ "zimmerZeitraumBelegungSelected" })
	public ListModel<BelegungKopf> getBelegungKopfList() {
		if ((gastSelected == null) || (gastSelected.getId() == 0)) {
			return null;
		}

		// Liste initialisieren
		belegungKopfList = new ListModelList<BelegungKopf>();
		for (BelegungKopf belegungsKopf : this.gastSelected.getBelegungKopf()) {
			belegungKopfList.add(belegungsKopf);
		}
		return belegungKopfList;
	}

	/**
	 * Get the currently selected "Belegung" in the "Gast"s history
	 * 
	 * @return Belegung
	 */
	public BelegungKopf getBelegungKopfSelected() {
		return belegungKopfSelected;
	}

	/**
	 * Get the end of the timespan
	 * 
	 * @return DatumBis
	 */
	@DependsOn("datumVon")
	public LocalDate getDatumBis() {
		if (datumBis.isBefore(datumVon)
				|| datumBis.isAfter(datumVon.plusMonths(MAX_BUCHUNGSLAENGE))) {
			datumBis = datumVon;
		}
		return datumBis;
	}

	/**
	 * Apply a constraint to limit the end of the timespan to be within one
	 * month.
	 * 
	 * @return
	 */
	@DependsOn("datumVon")
	public SimpleDateConstraint getDatumBisConstraint() {
		return new SimpleDateConstraint("between "
				+ datumVon.toString("yyyMMdd") + " and "
				+ datumVon.plusMonths(MAX_BUCHUNGSLAENGE).toString("yyyMMdd"));
	}

	/**
	 * Get the beginning of the timespan
	 * 
	 * @return DatumVon
	 */
	public LocalDate getDatumVon() {
		return datumVon;
	}

	/**
	 * Get the currently selected "Gast"
	 * 
	 * @return
	 */
	public Gast getGastSelected() {
		return gastSelected;
	}

	/**
	 * Get filter for "Gast" search
	 * 
	 * @return
	 */
	public String getGastSuche() {
		return gastSuche;
	}

	/**
	 * Get todays date
	 * 
	 * @return
	 */
	public LocalDate getHeute() {
		return new LocalDate();
	}

	/**
	 * Get complete "Gast" list
	 * 
	 * @return
	 */
	public ListModel<Gast> getItems() {
		if (gastList == null) {
			gastList = new ListModelList<Gast>();
			GastDao gastDao = ServiceLocator.getGastDao();
			gastList.addAll(gastDao.getAll());
		}
		return gastList;
	}

	/**
	 * Get list of "Belegungen" for the selected timespan
	 * 
	 * @return
	 */
	@DependsOn({ "datumBis" })
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

	/**
	 * Get currently selected "ZimmerZeitraumBelegung". This is an entry of the
	 * list on top left, showing room and status.
	 * 
	 * @return
	 */
	@DependsOn({ "datumVon", "datumBis" })
	public ZimmerZeitraumBelegung getZimmerZeitraumBelegungSelected() {
		return zimmerZeitraumBelegungSelected;
	}

	/**
	 * Decides if the summary shall be shown on the bottom. This will return
	 * true if timespan, "Zimmer" and "Gast" are valid
	 * 
	 * @return
	 */
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

	/**
	 * Set currently selected "Buchung" in the "Gast"s history
	 * 
	 * @param belegungKopfSelected
	 */
	public void setBelegungKopfSelected(BelegungKopf belegungKopfSelected) {
		this.belegungKopfSelected = belegungKopfSelected;
	}

	/**
	 * Set the timespan's beginning date
	 * 
	 * @param datumBis
	 */
	public void setDatumBis(LocalDate datumBis) {
		this.datumBis = datumBis;
	}

	/**
	 * Set the timespan's ending date
	 * 
	 * @param datumVon
	 */
	public void setDatumVon(LocalDate datumVon) {
		this.datumVon = datumVon;
	}

	/**
	 * Set currently selected "Gast"
	 * 
	 * @param gastSelected
	 */
	public void setGastSelected(Gast gastSelected) {
		this.gastSelected = gastSelected;
	}

	/**
	 * Set filter on "Gast" search
	 * 
	 * @param gastSuche
	 */
	public void setGastSuche(String gastSuche) {
		this.gastSuche = gastSuche;
	}

	/**
	 * Set currently selected "ZimmerZeitraumBelegung" (list on left top)
	 * 
	 * @param zimmerZeitraumBelegungSelected
	 */
	public void setZimmerZeitraumBelegungSelected(
			ZimmerZeitraumBelegung zimmerZeitraumBelegungSelected) {
		this.zimmerZeitraumBelegungSelected = zimmerZeitraumBelegungSelected;
	}

	/**
	 *Reset filter on the "Gast"-list
	 */
	@Command
	@NotifyChange({"gastSelected", "gastSuche"})
	public void showAllGuests(){
		gastSelected = null;
		gastSuche = "";
		this.doGuestSearch();
	}
}