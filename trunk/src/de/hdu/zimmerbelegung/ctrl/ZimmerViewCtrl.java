package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.manager.ZimmerManager;
import de.hdu.zimmerbelegung.model.Zimmer;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class ZimmerViewCtrl {

	ListModelList<Zimmer> items;
	Zimmer selected;
	private boolean addingNewItemMode = false;
	private boolean showSpecialButtons = true;

	@Init
	public ListModel<Zimmer> getItems() {
		if (items == null) {
			items = new ListModelList<Zimmer>();
			ZimmerManager manager = ServiceLocator.getZimmerManager();
			items.addAll(manager.getAllZimmer());
		}
		return items;
	}

	@Command("create")
	@NotifyChange({ "selected", "addingNewItemMode", "showSpecialButtons" })
	public void createNewZimmer() {
		setSelected(getInitializedZimmer());
		setAddingNewItemMode(true);
		setShowSpecialButtons(false);
	}

	@Command("selected")
	@NotifyChange("selected")
	public void selectedEditingZimmer() {
	}

	@Command("save")
	@NotifyChange({ "items", "addingNewItemMode", "showSpecialButtons" })
	public void addNewZimmer() {
		ZimmerManager manager = ServiceLocator.getZimmerManager();
		manager.update(selected);
		if (addingNewItemMode == true) {
			items.add(selected);
			setAddingNewItemMode(false);
			setShowSpecialButtons(true);
		}
	}

	@Command("delete")
	@NotifyChange({ "selected", "items", "showSpecialButtons"  })
	public void removeSelected() {
		ZimmerManager manager = ServiceLocator.getZimmerManager();
		manager.delete(selected);
		items.remove(selected);
		selected = null;
	}

	@Command("cancel")
	@NotifyChange({ "selected", "items", "addingNewItemMode",
			"showSpecialButtons" })
	public void cancelSelected() {
		setAddingNewItemMode(false);
		setShowSpecialButtons(true);
		selected = null;
	}

	public void setSelected(Zimmer selected) {
		this.selected = selected;
	}

	public Zimmer getSelected() {
		return selected;
	}

	public boolean isAddingNewItemMode() {
		return addingNewItemMode;
	}

	public void setAddingNewItemMode(boolean addingNewItemMode) {
		this.addingNewItemMode = addingNewItemMode;
	}

	public boolean isShowSpecialButtons() {
		return showSpecialButtons;
	}

	public void setShowSpecialButtons(boolean showSpecialButtons) {
		this.showSpecialButtons = showSpecialButtons;
	}

	private Zimmer getInitializedZimmer() {
		return new Zimmer(0, "", 0);
	}

}
