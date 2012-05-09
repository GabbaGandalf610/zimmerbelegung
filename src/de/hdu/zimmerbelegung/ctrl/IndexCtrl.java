package de.hdu.zimmerbelegung.ctrl;

import org.joda.time.LocalDate;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.dto.ZimmerZeitraumBelegungDto;
import de.hdu.zimmerbelegung.helper.ZimmerZeitraumBelegung;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class IndexCtrl {
	ListModelList<ZimmerZeitraumBelegung> zimmerZeitraumBelegungList;
	ZimmerZeitraumBelegung zimmerZeitraumBelegungSelected;
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

	public ListModel<ZimmerZeitraumBelegung> getZimmerZeitraumBelegungList() {
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