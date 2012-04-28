package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import de.hdu.zimmerbelegung.manager.AdminManager;
import de.hdu.zimmerbelegung.model.ZeitraumStatus;
import de.hdu.zimmerbelegung.service.ServiceLocator;

@SuppressWarnings("serial")
public class AdminZeitraumStatusCtrl  extends GenericForwardComposer implements Composer{

	private ZeitraumStatus zeitraumStatus;
	
	public ZeitraumStatus getZeitraumStatus() {
		return zeitraumStatus;
	}

	public void setZeitraumStatus(ZeitraumStatus zeitraumStatus) {
		this.zeitraumStatus = zeitraumStatus;
	}

	public void onClick$addZeitraumStatus() {
		if (null == zeitraumStatus) {
			zeitraumStatus = new ZeitraumStatus();
		}
		AdminManager manager = ServiceLocator.getAdminManager();
		manager.add(zeitraumStatus);
		zeitraumStatus = null;
	}

	public void onClick$updateZeitraumStatus() {
		AdminManager manager = ServiceLocator.getAdminManager();
		manager.update(zeitraumStatus);
		zeitraumStatus = null;
	}

	public void onClick$deleteZeitraumStatus() {
		AdminManager manager = ServiceLocator.getAdminManager();
		manager.delete(zeitraumStatus);
		zeitraumStatus = null;
	}
	
}
