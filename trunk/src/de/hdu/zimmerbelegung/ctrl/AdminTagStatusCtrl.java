package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import de.hdu.zimmerbelegung.manager.AdminManager;
import de.hdu.zimmerbelegung.model.TagStatus;
import de.hdu.zimmerbelegung.service.ServiceLocator;

@SuppressWarnings("serial")
public class AdminTagStatusCtrl extends GenericForwardComposer implements Composer  {

	private TagStatus tagstatus;
	
	public TagStatus getTagstatus() {
		return tagstatus;
	}

	public void setTagstatus(TagStatus tagstatus) {
		this.tagstatus = tagstatus;
	}

	public void onClick$addTagStatus() {
		if (null == tagstatus) {
			tagstatus = new TagStatus();
		}
		AdminManager manager = ServiceLocator.getAdminManager();
		manager.add(tagstatus);
		tagstatus = null;
	}

	public void onClick$updateTagStatus() {
		AdminManager manager = ServiceLocator.getAdminManager();
		manager.update(tagstatus);
		tagstatus = null;
	}

	public void onClick$deleteTagStatus() {
		AdminManager manager = ServiceLocator.getAdminManager();
		manager.delete(tagstatus);
		tagstatus = null;
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		getAllTagStatus();
	}
	
}
