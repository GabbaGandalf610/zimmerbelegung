package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import de.hdu.zimmerbelegung.manager.AdminGastManager;
import de.hdu.zimmerbelegung.manager.AdminManager;
import de.hdu.zimmerbelegung.manager.AdminZeitraumStatusManager;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.ZeitraumStatus;
import de.hdu.zimmerbelegung.service.ServiceLocator;

@SuppressWarnings("serial")
public class AdminZeitraumStatusCtrl  extends GenericForwardComposer implements Composer{

	@Wire
	private Listbox adminZeitraumStatusList;
	
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        AdminZeitraumStatusManager manager = ServiceLocator.getAdminZeitraumStatusManager();
        adminZeitraumStatusList.setModel(new ListModelList<ZeitraumStatus>(manager.getAllZeitraumStatus()));
        adminZeitraumStatusList.setItemRenderer(new ListitemRenderer<ZeitraumStatus>() {
        	public void render(Listitem item, ZeitraumStatus zeitraumStatus, int index)
    				throws Exception {
    	    	new Listcell(String.valueOf(zeitraumStatus.getId())).setParent(item);
    	    	new Listcell(String.valueOf(zeitraumStatus.getName())).setParent(item);
    		}	  	
        });
    }
	
	
	
	
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
