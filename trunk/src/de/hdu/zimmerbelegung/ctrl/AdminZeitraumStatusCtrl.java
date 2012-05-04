package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.manager.AdminZeitraumStatusManager;
import de.hdu.zimmerbelegung.model.TagStatus;
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
			AdminZeitraumStatusManager manager = ServiceLocator.getAdminZeitraumStatusManager();
			zeitraumStatusList.addAll(manager.getAllZeitraumStatus());
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
		AdminZeitraumStatusManager manager = ServiceLocator.getAdminZeitraumStatusManager();
		manager.update(zeitraumStatusSelected);
	}

	@Command
	@NotifyChange({ "zeitraumStatusSelected", "zeitraumStatusList" })
	public void doDelete() {
		AdminZeitraumStatusManager manager = ServiceLocator.getAdminZeitraumStatusManager();
		manager.delete(zeitraumStatusSelected);
		zeitraumStatusList.remove(zeitraumStatusSelected);
		zeitraumStatusSelected = null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
//	@Wire
//	private Listbox adminZeitraumStatusList;
//	
//    @Override
//    public void doAfterCompose(Component comp) throws Exception {
//        super.doAfterCompose(comp);
//        AdminZeitraumStatusManager manager = ServiceLocator.getAdminZeitraumStatusManager();
//        adminZeitraumStatusList.setModel(new ListModelList<ZeitraumStatus>(manager.getAllZeitraumStatus()));
//        adminZeitraumStatusList.setItemRenderer(new ListitemRenderer<ZeitraumStatus>() {
//        	public void render(Listitem item, ZeitraumStatus zeitraumStatus, int index)
//    				throws Exception {
//    	    	new Listcell(String.valueOf(zeitraumStatus.getId())).setParent(item);
//    	    	new Listcell(String.valueOf(zeitraumStatus.getName())).setParent(item);
//    		}	  	
//        });
//    }
	
	
	
	
//	private ZeitraumStatus zeitraumStatus;
//	
//	public ZeitraumStatus getZeitraumStatus() {
//		return zeitraumStatus;
//	}
//
//	public void setZeitraumStatus(ZeitraumStatus zeitraumStatus) {
//		this.zeitraumStatus = zeitraumStatus;
//	}
//
//	public void onClick$addZeitraumStatus() {
//		if (null == zeitraumStatus) {
//			zeitraumStatus = new ZeitraumStatus();
//		}
//		AdminManager manager = ServiceLocator.getAdminManager();
//		manager.add(zeitraumStatus);
//		zeitraumStatus = null;
//	}
//
//	public void onClick$updateZeitraumStatus() {
//		AdminManager manager = ServiceLocator.getAdminManager();
//		manager.update(zeitraumStatus);
//		zeitraumStatus = null;
//	}
//
//	public void onClick$deleteZeitraumStatus() {
//		AdminManager manager = ServiceLocator.getAdminManager();
//		manager.delete(zeitraumStatus);
//		zeitraumStatus = null;
//	}
	
}
