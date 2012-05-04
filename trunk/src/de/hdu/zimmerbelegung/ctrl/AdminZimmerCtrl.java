package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.dao.ZimmerDao;
import de.hdu.zimmerbelegung.model.Zimmer;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class AdminZimmerCtrl {

	ListModelList<Zimmer> items;
	Zimmer selected;
	private boolean addingNewItemMode = false;
	private boolean showSpecialButtons = true;

	@Init
	public ListModel<Zimmer> getItems() {
		if (items == null) {
			items = new ListModelList<Zimmer>();
			ZimmerDao zimmerDao = ServiceLocator.getZimmerDao();
			items.addAll(zimmerDao.getAll());
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
		ZimmerDao zimmerDao = ServiceLocator.getZimmerDao();
		zimmerDao.saveOrUpdate(selected);
		if (addingNewItemMode == true) {
			items.add(selected);
			setAddingNewItemMode(false);
			setShowSpecialButtons(true);
		}
	}

	@Command("delete")
	@NotifyChange({ "selected", "items", "showSpecialButtons"  })
	public void removeSelected() {
		ZimmerDao zimmerDao = ServiceLocator.getZimmerDao();
		zimmerDao.delete(selected);
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
