package de.hdu.zimmerbelegung.ctrl;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import de.hdu.zimmerbelegung.model.ZimmerKategorie;
import de.hdu.zimmerbelegung.service.IZimmerKategorieManager;
import de.hdu.zimmerbelegung.service.ServiceLocator;

@SuppressWarnings("serial")
public class IndexCtrl extends GenericForwardComposer implements Composer {
	private ZimmerKategorie current;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		getAllZimmerKategorien();
	}

	public ZimmerKategorie getCurrent() {
		return current;
	}

	public void setCurrent(ZimmerKategorie zimmerKategorie) {
		this.current = zimmerKategorie;
	}

	public List<ZimmerKategorie> getAllZimmerKategorien() {
		IZimmerKategorieManager manager = ServiceLocator.getZimmerKategorieManager();
		return manager.getAll();
	}

	public void onClick$add() {
		if (null == current) {
			current = new ZimmerKategorie();
		}
		IZimmerKategorieManager manager = ServiceLocator.getZimmerKategorieManager();
		manager.add(current);
		current = null;

	}

	public void onClick$update() {
		IZimmerKategorieManager manager = ServiceLocator.getZimmerKategorieManager();
		manager.update(current);
		current = null;
	}

	public void onClick$delete() {
		IZimmerKategorieManager manager = ServiceLocator.getZimmerKategorieManager();
		manager.delete(current);
		current = null;
	}
}