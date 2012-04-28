package de.hdu.zimmerbelegung.ctrl;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import de.hdu.zimmerbelegung.manager.AdminGastManager;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.service.ServiceLocator;


@SuppressWarnings("serial")
public class AdminGastCtrl extends GenericForwardComposer implements Composer {

	
	private Gast gast;
	
	
	public Gast getGast() {
		return gast;
	}

	public void setGast(Gast gast) {
		this.gast = gast;
	}
	
	public List<Gast> getAllGast(){
		AdminGastManager manager = ServiceLocator.getAdminGastManager();
		return manager.getAllGast();
	}
	
	public void onClick$addGast() {
		if (null == gast) {
			gast = new Gast();
		}
		AdminGastManager manager = ServiceLocator.getAdminGastManager();
		manager.add(gast);
		gast = null;
	}

	public void onClick$updateGast() {
		AdminGastManager manager = ServiceLocator.getAdminGastManager();
		manager.update(gast);
		gast = null;
	}

	public void onClick$deleteGast() {
		AdminGastManager manager = ServiceLocator.getAdminGastManager();
		manager.delete(gast);
		gast = null;
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		getAllGast();
	}
	
}
