package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.manager.AdminGastManager;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.service.ServiceLocator;

@SuppressWarnings("serial")
public class AdminGastCtrl extends GenericForwardComposer implements Composer {

	// the search condition
	String filter;
	// the search result
	ListModelList<Gast> items;
	// the selected item
	Gast selected;

	public void setSelected(Gast selected) {
		this.selected = selected;
	}

	public Gast getSelected() {
		return selected;
	}

	public ListModel<Gast> getItems() {
		if (items == null) {
			// Liste initialisieren
			items = new ListModelList<Gast>();
			AdminGastManager manager = ServiceLocator.getAdminGastManager();
			items.addAll(manager.getAllGast());
		}
		return items;
	}

	@Command
	public void doInsert() {
		Gast neuerGast = new Gast();
		items.add(neuerGast);
		selected = neuerGast;
	}

	@Command
	@NotifyChange({ "selected", "items" })
	public void doUpdate() {
		AdminGastManager manager = ServiceLocator.getAdminGastManager();
		manager.update(selected);
	}

	@Command
	@NotifyChange({ "selected", "items" })
	public void doDelete() {
		AdminGastManager manager = ServiceLocator.getAdminGastManager();
		manager.delete(selected);
		items.remove(selected);
		selected = null;
	}


}
