package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.dao.ZeitraumStatusDao;
import de.hdu.zimmerbelegung.model.ZeitraumStatus;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class AdminZeitraumStatusCtrl{
	
	// the search condition
	String filter;
	// the search result
	ListModelList<ZeitraumStatus> zeitraumStatusList;
	// the selected item
	ZeitraumStatus zeitraumStatusSelected;

	public void setZeitraumStatusSelected(ZeitraumStatus zeitraumStatusSelected) {
		this.zeitraumStatusSelected = zeitraumStatusSelected;
	}

	public ZeitraumStatus getZeitraumStatusSelected() {
		return zeitraumStatusSelected;
	}

	public ListModel<ZeitraumStatus> getItems() {
		if (zeitraumStatusList == null) {
			// Liste initialisieren
			zeitraumStatusList = new ListModelList<ZeitraumStatus>();
			ZeitraumStatusDao zeitraumStatusDao = ServiceLocator.getZeitraumStatusDao();
			zeitraumStatusList.addAll(zeitraumStatusDao.getAll());
		}
		return zeitraumStatusList;
	}

	@Command
	@NotifyChange({ "zeitraumStatusSelected", "zeitraumStatusList" })
	public void doNew() {
		zeitraumStatusSelected = new ZeitraumStatus();
	}

	@Command
	@NotifyChange({ "zeitraumStatusSelected", "zeitraumStatusList" })
	public void doSave() {
		ZeitraumStatusDao zeitraumStatusDao = ServiceLocator.getZeitraumStatusDao();
		zeitraumStatusDao.saveOrUpdate(zeitraumStatusSelected);
		if (!zeitraumStatusList.contains(zeitraumStatusSelected))
			zeitraumStatusList.add(zeitraumStatusSelected);
	}

	@Command
	@NotifyChange({ "zeitraumStatusSelected", "zeitraumStatusList" })
	public void doDelete() {
		ZeitraumStatusDao zeitraumStatusDao = ServiceLocator.getZeitraumStatusDao();
		zeitraumStatusDao.delete(zeitraumStatusSelected);
		zeitraumStatusList.remove(zeitraumStatusSelected);
		zeitraumStatusSelected = null;
	}
	
}
