package de.hdu.zimmerbelegung.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

import de.hdu.zimmerbelegung.manager.ZimmerManager;
import de.hdu.zimmerbelegung.model.Zimmer;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class ZimmerViewCtrl {

	private List<Zimmer> allZimmer;
	private Zimmer selectedZimmer;
	private Zimmer previewZimmer;
	private boolean addingNewItemMode = false;

	@Init
	public void init() {
		// Preparing data
		ZimmerManager manager = ServiceLocator.getZimmerManager();
		allZimmer = new ArrayList<Zimmer>(manager.getAllZimmer());
		// Erstes Element wird beim Start ausgewählt
		if (allZimmer.isEmpty() != true){
			setSelectedZimmer(allZimmer.get(0));
			copyToTemp(selectedZimmer);
		}
		
	}

	@Command("select")
	@NotifyChange({ "previewZimmer", "addingNewItemMode" })
	public void selectZimmer() {
		copyToTemp(selectedZimmer);
		setAddingNewItemMode(false);
	}

	@Command("create")
	@NotifyChange({ "previewZimmer", "addingNewItemMode" })
	public void createNewZimmer() {
		setPreviewZimmer(getInitializedZimmer());
		setAddingNewItemMode(true);
	}

	@Command("preview")
	@NotifyChange("previewZimmer")
	public void previewEditingZimmer() {
	}

	@Command("save")
	@NotifyChange({ "zimmerList", "addingNewItemMode" })
	public void addNewZimmer() {
		getZimmerList().add(previewZimmer);
		ZimmerManager manager = ServiceLocator.getZimmerManager();
		manager.add(previewZimmer);
		setSelectedZimmer(previewZimmer);
		setAddingNewItemMode(false);
	}

	@Command("update")
	@NotifyChange({ "selectedZimmer", "zimmerList", "previewZimmer",
			"addingNewItemMode" })
	public void updateSelectedZimmer() {
		saveToSelected(previewZimmer);
		ZimmerManager manager = ServiceLocator.getZimmerManager();
		manager.update(previewZimmer);
		setSelectedZimmer(previewZimmer);
	}

	@Command("delete")
	@NotifyChange({ "selectedZimmer", "zimmerList", "previewZimmer",
			"addingNewItemMode" })
	public void removeSelectedZimmer() {
		getZimmerList().remove(selectedZimmer);
		ZimmerManager manager = ServiceLocator.getZimmerManager();
		manager.delete(selectedZimmer);
		setSelectedZimmer(null);
		setPreviewZimmer(getInitializedZimmer());
		setAddingNewItemMode(true);
	}

	public List<Zimmer> getZimmerList() {
		return allZimmer;
	}

	public void setSelectedZimmer(Zimmer selected) {
		this.selectedZimmer = selected;
	}

	public Zimmer getSelectedZimmer() {
		return selectedZimmer;
	}

	public Zimmer getPreviewZimmer() {
		return previewZimmer;
	}

	public void setPreviewZimmer(Zimmer previewZimmer) {
		this.previewZimmer = previewZimmer;
	}

	public boolean isAddingNewItemMode() {
		return addingNewItemMode;
	}

	public void setAddingNewItemMode(boolean addingNewItemMode) {
		this.addingNewItemMode = addingNewItemMode;
	}

	private Zimmer getInitializedZimmer() {
		return new Zimmer(9999, "Muster", 9999);
	}

	private void saveToSelected(Zimmer zimmer) {
		selectedZimmer.setId(zimmer.getId());
		selectedZimmer.setZimmernummer(zimmer.getZimmernummer());
		selectedZimmer.setZimmerbeschreibung(zimmer.getZimmerbeschreibung());
		selectedZimmer.setZimmerpreis(zimmer.getZimmerpreis());
	}

	private void copyToTemp(Zimmer zimmer) {
		previewZimmer = new Zimmer(zimmer.getId(), zimmer.getZimmernummer(),
				zimmer.getZimmerbeschreibung(), zimmer.getZimmerpreis());
	}

}
