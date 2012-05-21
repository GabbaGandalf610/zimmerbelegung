package de.hdu.zimmerbelegung.ctrl;

import org.joda.time.LocalDate;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.SimpleDateConstraint;

import de.hdu.zimmerbelegung.dao.BelegungKopfDao;
import de.hdu.zimmerbelegung.dao.GastDao;
import de.hdu.zimmerbelegung.dto.ZimmerZeitraumBelegungDto;
import de.hdu.zimmerbelegung.helper.BelegungArt;
import de.hdu.zimmerbelegung.helper.Status;
import de.hdu.zimmerbelegung.helper.ZimmerZeitraumBelegung;
import de.hdu.zimmerbelegung.model.Belegung;
import de.hdu.zimmerbelegung.model.BelegungKopf;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class IndexCtrl {
	LocalDate datumBis = new LocalDate();
	LocalDate datumVon = new LocalDate();
	ListModelList<Gast> gastList;
	Gast gastSelected;
	String gastSuche;
	ListModelList<Belegung> belegungList;
	ListModelList<BelegungKopf> belegungKopfList;

	public BelegungArt[] getAllBelegungArt() {
		return BelegungArt.getAll();
	}

	private final int MAX_BUCHUNGSLAENGE = 1;

	ListModelList<ZimmerZeitraumBelegung> zimmerZeitraumBelegungList;

	ZimmerZeitraumBelegung zimmerZeitraumBelegungSelected;

	// Roland

	@DependsOn({ "gastSelected" })
	@NotifyChange({ "datumVon", "datumBis", "zimmerZeitraumBelegungSelected" })
	public ListModel<BelegungKopf> getBelegungKopfList() {
		if (gastSelected == null)
			return null;
		// Liste initialisieren
		belegungKopfList = new ListModelList<BelegungKopf>();
		for (BelegungKopf belegungsKopf : this.gastSelected.getBelegungKopf()) {
			belegungKopfList.add((BelegungKopf) belegungsKopf);
		}
		return belegungKopfList;
	}

	@Command
	public void doBuchen() {
		if (!isZusammenfassungAnzeigen())
			return;
		BelegungKopfDao belegungKopfDao = ServiceLocator.getBelegungKopfDao();
		belegungKopfDao.create(BelegungArt.BUCHUNG, datumVon, datumBis,
				zimmerZeitraumBelegungSelected.getZimmer(), gastSelected);
		// System.out.println(gastSelected.getBelegung());
		System.out.println(gastSelected.getBelegungKopf());
	}

	@Command
	public void doReservieren() {
		if (!isZusammenfassungAnzeigen())
			return;
		BelegungKopfDao belegungKopfDao = ServiceLocator.getBelegungKopfDao();
		belegungKopfDao.create(BelegungArt.RESERVIERUNG, datumVon, datumBis,
				zimmerZeitraumBelegungSelected.getZimmer(), gastSelected);
	}

	@Command
	@NotifyChange({ "gastSelected", "gastList" })
	public void doDelete() {
		GastDao gastDao = ServiceLocator.getGastDao();
		gastDao.delete(gastSelected);
		gastList.remove(gastSelected);
		gastSelected = null;
	}

	@Command
	public void doGuestSearch() {
		gastList.clear();
		GastDao gastDao = ServiceLocator.getGastDao();
		System.out.println(gastSuche);
		gastList.addAll(gastDao.getAllFilteredUser(gastSuche));
		gastSelected = null;
	}

	@Command
	@NotifyChange({ "gastSelected", "gastList" })
	public void doNew() {
		gastSelected = new Gast();
	}

	@Command
	@NotifyChange({ "gastSelected", "gastList" })
	public void doSave() {
		GastDao gastDao = ServiceLocator.getGastDao();
		gastDao.saveOrUpdate(gastSelected);
		if (!gastList.contains(gastSelected))
			gastList.add(gastSelected);
	}

	@DependsOn("datumVon")
	public LocalDate getDatumBis() {
		if (datumBis.isBefore(datumVon)
				|| datumBis.isAfter(datumVon.plusMonths(MAX_BUCHUNGSLAENGE))) {
			datumBis = datumVon;
		}
		return datumBis;
	}

	@DependsOn("datumVon")
	public SimpleDateConstraint getDatumBisConstraint() {
		return new SimpleDateConstraint("between "
				+ datumVon.toString("yyyMMdd") + " and "
				+ datumVon.plusMonths(MAX_BUCHUNGSLAENGE).toString("yyyMMdd"));
	}

	public LocalDate getDatumVon() {
		return datumVon;
	}

	public Gast getGastSelected() {
		return gastSelected;
	}

	public String getGastSuche() {
		return gastSuche;
	}

	public ListModel<Gast> getItems() {
		if (gastList == null) {
			gastList = new ListModelList<Gast>();
			GastDao gastDao = ServiceLocator.getGastDao();
			gastList.addAll(gastDao.getAll());
		}
		return gastList;
	}

	@DependsOn({ "datumVon", "datumBis" })
	public ListModel<ZimmerZeitraumBelegung> getZimmerZeitraumBelegungList() {
		zimmerZeitraumBelegungSelected = null;
		if (zimmerZeitraumBelegungList == null) {
			zimmerZeitraumBelegungList = new ListModelList<ZimmerZeitraumBelegung>();
		} else {
			zimmerZeitraumBelegungList.clear();
		}
		ZimmerZeitraumBelegungDto zimmerZeitraumBelegungDto = ServiceLocator
				.getZimmerZeitraumBelegungDto();
		zimmerZeitraumBelegungList.addAll(zimmerZeitraumBelegungDto.getAll(
				datumVon, datumBis));
		return zimmerZeitraumBelegungList;
	}

	@DependsOn({ "datumVon", "datumBis" })
	public ZimmerZeitraumBelegung getZimmerZeitraumBelegungSelected() {
		return zimmerZeitraumBelegungSelected;
	}

	@DependsOn({ "datumVon", "datumBis", "zimmerZeitraumBelegungSelected",
			"gastSelected" })
	public boolean isZusammenfassungAnzeigen() {
		if (datumVon == null || datumBis == null
				|| zimmerZeitraumBelegungSelected == null
				|| gastSelected == null) {
			return false;
		}
		if (zimmerZeitraumBelegungSelected.getStatus() != Status.FREI) {
			return false;
		}
		return true;
	}

	public void setDatumBis(LocalDate datumBis) {
		this.datumBis = datumBis;
	}

	public void setDatumVon(LocalDate datumVon) {
		this.datumVon = datumVon;
	}

	public void setGastSelected(Gast gastSelected) {
		this.gastSelected = gastSelected;
	}

	public void setGastSuche(String gastSuche) {
		this.gastSuche = gastSuche;
	}

	public void setZimmerZeitraumBelegungSelected(
			ZimmerZeitraumBelegung zimmerZeitraumBelegungSelected) {
		this.zimmerZeitraumBelegungSelected = zimmerZeitraumBelegungSelected;
	}

}