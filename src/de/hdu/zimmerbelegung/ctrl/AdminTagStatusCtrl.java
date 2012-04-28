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
import de.hdu.zimmerbelegung.manager.AdminTagStatusManager;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.TagStatus;
import de.hdu.zimmerbelegung.service.ServiceLocator;

@SuppressWarnings("serial")
public class AdminTagStatusCtrl extends GenericForwardComposer implements Composer  {

	
	@Wire
	private Listbox adminTagStatusList;
	
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        AdminTagStatusManager manager = ServiceLocator.getAdminTagStatusManager();
        adminTagStatusList.setModel(new ListModelList<TagStatus>(manager.getAllTagStatus()));
        adminTagStatusList.setItemRenderer(new ListitemRenderer<TagStatus>() {
        	public void render(Listitem item, TagStatus tagStatus, int index)
    				throws Exception {
    	    	new Listcell(String.valueOf(tagStatus.getId())).setParent(item);
    	    	new Listcell(String.valueOf(tagStatus.getName())).setParent(item);
    		}	  	
        });
    }
	
	
	
	
//	private TagStatus tagstatus;
//	
//	public TagStatus getTagstatus() {
//		return tagstatus;
//	}
//
//	public void setTagstatus(TagStatus tagstatus) {
//		this.tagstatus = tagstatus;
//	}
//
//	public void onClick$addTagStatus() {
//		if (null == tagstatus) {
//			tagstatus = new TagStatus();
//		}
//		AdminManager manager = ServiceLocator.getAdminManager();
//		manager.add(tagstatus);
//		tagstatus = null;
//	}
//
//	public void onClick$updateTagStatus() {
//		AdminManager manager = ServiceLocator.getAdminManager();
//		manager.update(tagstatus);
//		tagstatus = null;
//	}
//
//	public void onClick$deleteTagStatus() {
//		AdminManager manager = ServiceLocator.getAdminManager();
//		manager.delete(tagstatus);
//		tagstatus = null;
//	}
//	
//	@Override
//	public void doAfterCompose(Component comp) throws Exception {
//		super.doAfterCompose(comp);
//		getAllTagStatus();
//	}
	
}
