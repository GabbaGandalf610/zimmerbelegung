package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;


import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import de.hdu.zimmerbelegung.manager.AdminZimmerManager;
import de.hdu.zimmerbelegung.model.Zimmer;

import de.hdu.zimmerbelegung.service.ServiceLocator;

public class AdminZimmerCtrl extends SelectorComposer<Component> {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = -5492437630185952581L;
	@Wire
	private Listbox adminZimmerList;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
//		AdminZimmerManager manager = ServiceLocator.getAdminZimmerManager();
//		adminZimmerList.setModel(new ListModelList<Zimmer>(manager.getAllZimmer()));
		adminZimmerList.setItemRenderer(new ListitemRenderer<Zimmer>() {
        	public void render(Listitem item, Zimmer zimmer, int index)
    				throws Exception {
    	    	new Listcell(String.valueOf(zimmer.getId())).setParent(item);
//    	    	new Listcell(String.valueOf(zimmer.getName())).setParent(item);
//    	    	new Listcell(String.valueOf(zimmer.getKurzbeschreibung())).setParent(item);
    		}
		});
	}

	private Zimmer zimmer;

	public Zimmer getZimmer() {
		return zimmer;
	}

	public void setZimmer(Zimmer zimmer) {
		this.zimmer = zimmer;
	}

	// public List<Zimmer> getAllZimmer() {
	// AdminZimmerManager manager = ServiceLocator.getAdminZimmerManager();
	// return manager.getAllZimmer();
	// }

	// @Override
	// public void doAfterCompose(Component comp) throws Exception {
	// super.doAfterCompose(comp);
	// getAllZimmer();
	// }
	//
	// public void onClick$addZimmer() {
	// if (null == zimmer) {
	// zimmer = new Zimmer();
	// }
	// AdminZimmerManager manager = ServiceLocator.getAdminZimmerManager();
	// manager.add(zimmer);
	// zimmer = null;
	// }
	//
	// public void onClick$updateZimmer() {
	// AdminZimmerManager manager = ServiceLocator.getAdminZimmerManager();
	// manager.update(zimmer);
	// zimmer = null;
	// }
	//
	// public void onClick$deleteZimmer() {
	// AdminZimmerManager manager = ServiceLocator.getAdminZimmerManager();
	// manager.delete(zimmer);
	// zimmer = null;
	// }

}
