package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.NotifyChangeDisabled;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.dao.BuchungDao;
import de.hdu.zimmerbelegung.dao.GastDao;
import de.hdu.zimmerbelegung.dao.ZimmerDao;
import de.hdu.zimmerbelegung.model.Buchung;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.Zimmer;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class AdminBuchungCtrl {
	ListModelList<Buchung> buchungList;
	ListModelList<Gast> gastList;
	ListModelList<Zimmer> zimmerList;
	Buchung buchungSelected;
	boolean neueBuchung = false;

	public boolean isNeueBuchung() {
		return neueBuchung;
	}
	public void setBuchungSelected(Buchung selected) {
		neueBuchung = false;
		this.buchungSelected = selected;
	}
	public Buchung getBuchungSelected() {
		return buchungSelected;
	}
	public ListModel<Buchung> getBuchungList() {
		if (buchungList == null) {
			// Liste initialisieren
			buchungList = new ListModelList<Buchung>();
			BuchungDao buchungDao = ServiceLocator.getBuchungDao();
			buchungList.addAll(buchungDao.getAll());
		}
		return buchungList;
	}
    @NotifyChangeDisabled
	public ListModel<Gast> getGastList() {
		if (gastList == null) {
			// Liste initialisieren
			gastList = new ListModelList<Gast>();
			GastDao gastDao = ServiceLocator.getGastDao();
			gastList.addAll(gastDao.getAll());
		}
		return gastList;
	}
    @NotifyChangeDisabled
	public ListModel<Zimmer> getZimmerList() {
		if (zimmerList == null) {
			// Liste initialisieren
			zimmerList = new ListModelList<Zimmer>();
			ZimmerDao zimmerDao = ServiceLocator.getZimmerDao();
			zimmerList.addAll(zimmerDao.getAll());
		}
		return zimmerList;
	}
	
	@Command
	@NotifyChange({ "buchungSelected", "buchungList", "neueBuchung" })
	public void doNew() {
		buchungSelected = new Buchung();
		neueBuchung = true;
	}
	
	@Command
	@NotifyChange({ "buchungSelected", "buchungList", "neueBuchung" })
	public void doSave() {
		BuchungDao buchungDao = ServiceLocator.getBuchungDao();
		buchungDao.save(buchungSelected);
		if (!buchungList.contains(buchungSelected))
			buchungList.add(buchungSelected);
		neueBuchung = false;
	}
}