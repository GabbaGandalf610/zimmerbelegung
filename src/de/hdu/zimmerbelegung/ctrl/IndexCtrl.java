package de.hdu.zimmerbelegung.ctrl;

import org.joda.time.LocalDate;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.dao.GastDao;
import de.hdu.zimmerbelegung.dto.ZimmerZeitraumBelegungDto;
import de.hdu.zimmerbelegung.helper.ZimmerZeitraumBelegung;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class IndexCtrl {
	ListModelList<ZimmerZeitraumBelegung> zimmerZeitraumBelegungList;
	ZimmerZeitraumBelegung zimmerZeitraumBelegungSelected;
	ListModelList<Gast> gastList;
	Gast gastSelected;
	LocalDate datumVon = new LocalDate();
	LocalDate datumBis = new LocalDate();

	public LocalDate getDatumVon() {
		return datumVon;
	}

	@NotifyChange("zimmerZeitraumBelegungList")
	public void setDatumVon(LocalDate datumVon) {
		this.datumVon = datumVon;
	}

	public LocalDate getDatumBis() {
		return datumBis;
	}

	@NotifyChange("zimmerZeitraumBelegungList")
	public void setDatumBis(LocalDate datumBis) {
		this.datumBis = datumBis;
	}

	public ZimmerZeitraumBelegung getZimmerZeitraumBelegungSelected() {
		return zimmerZeitraumBelegungSelected;
	}
	
	public void setZimmerZeitraumBelegungSelected(
			ZimmerZeitraumBelegung zimmerZeitraumBelegungSelected) {
		this.zimmerZeitraumBelegungSelected = zimmerZeitraumBelegungSelected;
		System.out.println(zimmerZeitraumBelegungSelected.getZimmer());
	}

	@NotifyChange("zimmerZeitraumBelegungSelected")
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
		for (ZimmerZeitraumBelegung element : zimmerZeitraumBelegungList) {
			System.out.println(element.getZimmer());
		}
		return zimmerZeitraumBelegungList;
	}

	
	
	// ROLAND
	public void setGastSelected(Gast gastSelected) {
		this.gastSelected = gastSelected;
	}

	public Gast getGastSelected() {
		return gastSelected;
	}

	public ListModel<Gast> getItems() {
		if (gastList == null) {
			// Liste initialisieren
			gastList = new ListModelList<Gast>();
			GastDao gastDao = ServiceLocator.getGastDao();
			gastList.addAll(gastDao.getAll());
		}
		return gastList;
	}

	@Command
	@NotifyChange({ "gastSelected", "gastList" })
	public void doNew() {
		gastSelected = new Gast();
	}
	
	@Command
	@NotifyChange({ "gastSelected", "gastList" })
	public void doSave() {
		GastDao gastDao = ServiceLocator.getGastDao();
		gastDao.saveOrUpdate(gastSelected);
		if (!gastList.contains(gastSelected))
			gastList.add(gastSelected);
	}
	
	@Command
	@NotifyChange({ "gastSelected", "gastList" })
	public void doDelete() {
		GastDao gastDao = ServiceLocator.getGastDao();
		gastDao.delete(gastSelected);
		gastList.remove(gastSelected);
		gastSelected = null;
	}
	
	
	
	// @Command
	// @NotifyChange({ "buchungSelected", "buchungList" })
	// public void doSave() {
	// BuchungDao buchungDao = ServiceLocator.getBuchungDao();
	// buchungDao.saveOrUpdate(buchungSelected);
	// if (!buchungList.contains(buchungSelected))
	// buchungList.add(buchungSelected);
	// }
	//
	// @Command
	// @NotifyChange({ "buchungSelected", "buchungList" })
	// public void doDelete() {
	// BuchungDao buchungDao = ServiceLocator.getBuchungDao();
	// buchungDao.delete(buchungSelected);
	// buchungList.remove(buchungSelected);
	// buchungSelected = null;
	// }
}