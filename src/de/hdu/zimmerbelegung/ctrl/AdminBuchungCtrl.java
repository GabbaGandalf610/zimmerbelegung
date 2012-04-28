package de.hdu.zimmerbelegung.ctrl;

import java.util.Date;

import org.zkoss.zul.Label;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import de.hdu.zimmerbelegung.manager.AdminBuchungManager;
import de.hdu.zimmerbelegung.model.Buchung;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class AdminBuchungCtrl extends SelectorComposer<Component> {
	private static final long serialVersionUID = -8889573485448848014L;
		
	@Wire
	private Listbox adminBuchungList;
	
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        AdminBuchungManager manager = ServiceLocator.getAdminBuchungManager();
        adminBuchungList.setModel(new ListModelList<Buchung>(manager.getAll()));
        adminBuchungList.setItemRenderer(new ListitemRenderer<Buchung>() {
        	public void render(Listitem item, Buchung buchung, int index)
    				throws Exception {
    	    	new Listcell(String.valueOf(buchung.getId())).setParent(item);
    	    	new Listcell(String.valueOf(buchung.getDatum())).setParent(item);
    		}	  	
        });
    }
    

	private Buchung buchung;
	private Date datumVon;

	public Buchung getBuchung() {
		return buchung;
	}

	public void setBuchung(Buchung buchung) {
		this.buchung = buchung;
	}

	public Date getDatumVon() {
		return datumVon;
	}

	public void setDatumVon(Date datumVon) {
		this.datumVon = datumVon;
	}

//	@Override
//	public void doAfterCompose(Component comp) throws Exception {
//		super.doAfterCompose(comp);
//		getAllBuchung();
//	}

//	public List<Buchung> getAllBuchung() {
//		AdminBuchungManager manager = ServiceLocator.getAdminBuchungManager();
//		return manager.getAll();
//	}
//	
//	public void onClick$addBuchung() {
//		if (null == buchung) {
//			buchung = new Buchung();
//		}
//		AdminBuchungManager manager = ServiceLocator.getAdminBuchungManager();
//		manager.add(buchung);
//		buchung = null;
//	}
//	
//	public void onClick$updateBuchung() {
//		AdminBuchungManager manager = ServiceLocator.getAdminBuchungManager();
//		manager.update(buchung);
//		buchung = null;
//	}
//	
//	public void onClick$deleteBuchung() {
//		AdminBuchungManager manager = ServiceLocator.getAdminBuchungManager();
//		manager.delete(buchung);
//		buchung = null;
//	}
}