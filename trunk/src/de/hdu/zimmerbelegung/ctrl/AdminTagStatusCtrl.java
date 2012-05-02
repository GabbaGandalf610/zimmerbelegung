package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.manager.AdminTagStatusManager;
import de.hdu.zimmerbelegung.model.TagStatus;
import de.hdu.zimmerbelegung.service.ServiceLocator;

@SuppressWarnings("serial")
public class AdminTagStatusCtrl extends GenericForwardComposer implements Composer  {

	
	// the search condition
	String filter;
	// the search result
	ListModelList<TagStatus> items;
	// the selected item
	TagStatus selected;

	public void setSelected(TagStatus selected) {
		this.selected = selected;
	}

	public TagStatus getSelected() {
		return selected;
	}

	public ListModel<TagStatus> getItems() {
		if (items == null) {
			// Liste initialisieren
			items = new ListModelList<TagStatus>();
			AdminTagStatusManager manager = ServiceLocator.getAdminTagStatusManager();
			items.addAll(manager.getAllTagStatus());
		}
		return items;
	}

	@Command
	public void doInsert() {
		TagStatus neuerTagStatus = new TagStatus();
		items.add(neuerTagStatus);
		selected = neuerTagStatus;
	}

	@Command
	@NotifyChange({ "selected", "items" })
	public void doUpdate() {
		AdminTagStatusManager manager = ServiceLocator.getAdminTagStatusManager();
		manager.update(selected);
	}

	@Command
	@NotifyChange({ "selected", "items" })
	public void doDelete() {
		AdminTagStatusManager manager = ServiceLocator.getAdminTagStatusManager();
		manager.delete(selected);
		items.remove(selected);
		selected = null;
	}
	
}
