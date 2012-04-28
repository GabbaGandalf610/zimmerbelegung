package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.zul.Label;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;

import de.hdu.zimmerbelegung.manager.AdminBuchungManager;
import de.hdu.zimmerbelegung.model.Buchung;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class AdminBuchungCtrl extends SelectorComposer<Component> {
	private static final long serialVersionUID = -8889573485448848014L;

	@Wire
	private Listbox adminBuchungList;

	private Buchung buchung;
	@Wire
	private Label id;
	@Wire
	private Textbox datum;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		AdminBuchungManager manager = ServiceLocator.getAdminBuchungManager();
		adminBuchungList.setModel(new ListModelList<Buchung>(manager.getAll()));
		adminBuchungList.setItemRenderer(new ListitemRenderer<Buchung>() {
			public void render(Listitem item, Buchung buchung, int index)
					throws Exception {
				new Listcell(String.valueOf(buchung.getId())).setParent(item);
				new Listcell(String.valueOf(buchung.getDatum()))
						.setParent(item);
			}
		});
	}

	@Listen("onSelect = #adminBuchungList")
	public void getBuchung(SelectEvent<Listbox, Buchung> e) {
		buchung = e.getSelectedObjects().iterator().next();
		id.setValue(String.valueOf(buchung.getId()));
		datum.setValue(String.valueOf(buchung.getDatum()));
	}

	@Listen("onClick=#adminBuchungUpdate")
	public void adminBuchungUpdate() {
		// TODO: buchung.setDatum(Date.parse((datum.getValue()));
		AdminBuchungManager manager = ServiceLocator.getAdminBuchungManager();
		manager.update(buchung);
	}
	
	@Listen("onClick=#adminBuchungDelete")
	public void adminBuchungDelete() {
		// TODO: buchung.setDatum(Date.parse((datum.getValue()));
		AdminBuchungManager manager = ServiceLocator.getAdminBuchungManager();
		manager.delete(buchung);
		adminBuchungList.setModel(new ListModelList<Buchung>(manager.getAll()));
	}

	// public void onClick$addBuchung() {
	// if (null == buchung) {
	// buchung = new Buchung();
	// }
	// AdminBuchungManager manager = ServiceLocator.getAdminBuchungManager();
	// manager.add(buchung);
	// buchung = null;
	// }
	//
	// public void onClick$deleteBuchung() {
	// AdminBuchungManager manager = ServiceLocator.getAdminBuchungManager();
	// manager.delete(buchung);
	// buchung = null;
	// }
}