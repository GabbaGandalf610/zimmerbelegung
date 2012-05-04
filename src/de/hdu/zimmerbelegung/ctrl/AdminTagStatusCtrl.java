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

//@SuppressWarnings("serial")
public class AdminTagStatusCtrl {

	ListModelList<TagStatus> tagStatusList;
	// the selected item
	TagStatus tagStatusSelected;

	public void setSelected(TagStatus tagStatusSelected) {
		this.tagStatusSelected = tagStatusSelected;
	}

	public TagStatus getTagStatusSelected() {
		return tagStatusSelected;
	}

	public ListModel<TagStatus> getItems() {
		if (tagStatusList == null) {
			// Liste initialisieren
			tagStatusList = new ListModelList<TagStatus>();
			AdminTagStatusManager manager = ServiceLocator.getAdminTagStatusManager();
			tagStatusList.addAll(manager.getAllTagStatus());
		}
		return tagStatusList;
	}

	@Command
	@NotifyChange({ "tagStatusSelected", "tagStatusList" })
	public void doNew() {
		tagStatusSelected = new TagStatus();
	}

	@Command
	@NotifyChange({ "tagStatusSelected", "tagStatusList" })
	public void doSave() {
		AdminTagStatusManager manager = ServiceLocator.getAdminTagStatusManager();
		manager.update(tagStatusSelected);
	}

	@Command
	@NotifyChange({ "tagStatusSelected", "tagStatusList" })
	public void doDelete() {
		AdminTagStatusManager manager = ServiceLocator.getAdminTagStatusManager();
		manager.delete(tagStatusSelected);
		tagStatusList.remove(tagStatusSelected);
		tagStatusSelected = null;
	}
	
}
