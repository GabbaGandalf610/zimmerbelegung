package de.hdu.zimmerbelegung.ctrl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import de.hdu.zimmerbelegung.helper.ZimmerStatus;
import de.hdu.zimmerbelegung.model.Belegung;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.ZeitraumStatus;
import de.hdu.zimmerbelegung.model.Zimmer;

@SuppressWarnings("serial")
public class BelegungCtrl extends GenericForwardComposer implements Composer {
	private Date datumVon;
	private Date datumBis;
	private Gast gast;

	public Date getDatumVon() {
		return datumVon;
	}

	public void setDatumVon(Date datumVon) {
		this.datumVon = datumVon;
	}

	public Date getDatumBis() {
		return datumBis;
	}

	public void setDatumBis(Date datumBis) {
		this.datumBis = datumBis;
	}

	public Gast getGast() {
		return gast;
	}

	public void setGast(Gast gast) {
		this.gast = gast;
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		// getAllZimmerKategorien();
	}

	public List<ZimmerStatus> getAllZimmerStatus() {
		// IZimmerKategorieManager manager =
		// ServiceLocator.getZimmerKategorieManager();
		// return manager.getAll();
		List<ZimmerStatus> liste = new ArrayList<ZimmerStatus>();
		liste.add(new ZimmerStatus(new Zimmer(1, "11", "25m²"),
				new ZeitraumStatus(1, "frei")));
		return liste;
	}

	public List<String[]> getAllTagStatus() {
		return new ArrayList<String[]>();
	}

	public List<Gast> getAllGast() {
		return new ArrayList<Gast>();
	}

	public List<Belegung> getAllBelegung() {
		return new ArrayList<Belegung>();
	}

/*	public void onClick$add() {
		if (null == current) {
			current = new ZimmerKategorie();
		}
		IZimmerKategorieManager manager = ServiceLocator
				.getZimmerKategorieManager();
		manager.add(current);
		current = null;

	}

	public void onClick$update() {
		IZimmerKategorieManager manager = ServiceLocator
				.getZimmerKategorieManager();
		manager.update(current);
		current = null;
	}

	public void onClick$delete() {
		IZimmerKategorieManager manager = ServiceLocator
				.getZimmerKategorieManager();
		manager.delete(current);
		current = null;
	}*/
}