package de.hdu.zimmerbelegung.ctrl;


import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.manager.AdminBuchungManager;
import de.hdu.zimmerbelegung.model.Buchung;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class AdminBuchungCtrl {
	// the search condition
	String filter;
	// the search result
	ListModelList<Buchung> items;
	// the selected item
	Buchung selected;

	public void setSelected(Buchung selected) {
		this.selected = selected;
	}
	public Buchung getSelected() {
		return selected;
	}
	public ListModel<Buchung> getItems() {
		if (items == null) {
			// Liste initialisieren
			items = new ListModelList<Buchung>();
			AdminBuchungManager manager = ServiceLocator.getAdminBuchungManager();
			items.addAll(manager.getAll());
		}
		return items;
	}
	
	@Command
	@NotifyChange({ "selected", "items" })
	public void doUpdate() {
		AdminBuchungManager manager = ServiceLocator.getAdminBuchungManager();
		manager.update(selected);
	}
	
	@Command
	@NotifyChange({ "selected", "items" })
	public void doDelete() {
		AdminBuchungManager manager = ServiceLocator.getAdminBuchungManager();
		manager.delete(selected);
		items.remove(selected);
		selected = null;
	}
}