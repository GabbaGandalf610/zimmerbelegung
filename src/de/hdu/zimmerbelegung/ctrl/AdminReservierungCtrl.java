package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.NotifyChangeDisabled;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.dao.GastDao;
import de.hdu.zimmerbelegung.dao.ReservierungDao;
import de.hdu.zimmerbelegung.dao.ZimmerDao;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.Reservierung;
import de.hdu.zimmerbelegung.model.Zimmer;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class AdminReservierungCtrl {
	ListModelList<Reservierung> reservierungList;
	ListModelList<Gast> gastList;
	ListModelList<Zimmer> zimmerList;
	Reservierung reservierungSelected;
	boolean neueReservierung = false; 
	
	public boolean isNeueReservierung() {
		return neueReservierung;
	}
	public Reservierung getReservierungSelected() {
		return reservierungSelected;
	}
	public void setReservierungSelected(Reservierung reservierungSelected) {
		neueReservierung = false;
		this.reservierungSelected = reservierungSelected;
	}
	
	public ListModel<Reservierung> getReservierungList() {
		if (reservierungList == null) {
			// Liste initialisieren
			reservierungList = new ListModelList<Reservierung>();
			ReservierungDao reservierungDao = ServiceLocator.getReservierungDao();
			reservierungList.addAll(reservierungDao.getAll());
		}
		return reservierungList;
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
	@NotifyChange({ "reservierungSelected", "reservierungList", "neueReservierung" })
	public void doNew() {
		reservierungSelected = new Reservierung();
		neueReservierung = true;
	}
	
	@Command
	@NotifyChange({ "reservierungSelected", "reservierungList", "neueReservierung" })
	public void doSave() {
		ReservierungDao reservierungDao = ServiceLocator.getReservierungDao();
		reservierungDao.save(reservierungSelected);
		if (!reservierungList.contains(reservierungSelected))
			reservierungList.add(reservierungSelected);
		neueReservierung = false;
	}
}
