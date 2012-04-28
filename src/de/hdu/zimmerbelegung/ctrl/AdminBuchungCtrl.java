package de.hdu.zimmerbelegung.ctrl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.zul.Label;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import de.hdu.zimmerbelegung.manager.AdminBuchungManager;
import de.hdu.zimmerbelegung.model.Buchung;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class AdminBuchungCtrl extends SelectorComposer<Component> {
	private static final long serialVersionUID = -8889573485448848014L;
	
	private class BuchungRowRenderer implements RowRenderer<Buchung> {
	    public void render(final Row row, final Buchung data, int index) {
	    	new Label(String.valueOf(data.getId())).setParent(row);
	    	new Label(String.valueOf(data.getDatum())).setParent(row);
	    }		
	}
	
	@Wire
	private Grid adminBuchungGrid;
	
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        AdminBuchungManager manager = ServiceLocator.getAdminBuchungManager();
        adminBuchungGrid.setModel(new ListModelList<Buchung>(manager.getAll()));
        adminBuchungGrid.setRowRenderer(new BuchungRowRenderer());
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