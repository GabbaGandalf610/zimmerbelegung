package de.hdu.zimmerbelegung.ctrl;

import org.joda.time.LocalDate;
import org.zkoss.bind.annotation.NotifyChangeDisabled;
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
	public void setDatumVon(LocalDate datumVon) {
		this.datumVon = datumVon;
	}
	public LocalDate getDatumBis() {
		return datumBis;
	}
	public void setDatumBis(LocalDate datumBis) {
		this.datumBis = datumBis;
	}

	@NotifyChangeDisabled
	public ListModel<ZimmerZeitraumBelegung> getZimmerZeitraumBelegungList() {
//		if (zimmerZeitraumBelegungList == null) {
//			zimmerZeitraumBelegungList = new ListModelList<ZimmerZeitraumBelegung>();
//			ZimmerZeitraumBelegungDto zimmerZeitraumBelegungDto = ServiceLocator
//					.getZimmerZeitraumBelegungDto();
//			zimmerZeitraumBelegungList.addAll(zimmerZeitraumBelegungDto.getAll(
//					datumVon, datumBis));
//		}
//		return zimmerZeitraumBelegungList;
		return new ListModelList<ZimmerZeitraumBelegung>();
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