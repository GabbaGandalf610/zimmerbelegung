package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.dao.TagStatusDao;
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
			TagStatusDao tagStatusDao = ServiceLocator.getTagStatusDao();
			tagStatusList.addAll(tagStatusDao.getAll());
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
		TagStatusDao tagStatusDao = ServiceLocator.getTagStatusDao();
		tagStatusDao.saveOrUpdate(tagStatusSelected);
		if(!tagStatusList.contains(tagStatusSelected))
			tagStatusList.add(tagStatusSelected);
	}

	@Command
	@NotifyChange({ "tagStatusSelected", "tagStatusList" })
	public void doDelete() {
		TagStatusDao tagStatusDao = ServiceLocator.getTagStatusDao();
		tagStatusDao.saveOrUpdate(tagStatusSelected);
		tagStatusList.remove(tagStatusSelected);
		tagStatusSelected = null;
	}
	
}
