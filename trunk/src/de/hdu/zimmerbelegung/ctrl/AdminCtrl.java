package de.hdu.zimmerbelegung.ctrl;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import de.hdu.zimmerbelegung.manager.AdminManager;
import de.hdu.zimmerbelegung.model.Buchung;
import de.hdu.zimmerbelegung.model.Gast;
import de.hdu.zimmerbelegung.model.Reservierung;
import de.hdu.zimmerbelegung.model.TagStatus;
import de.hdu.zimmerbelegung.model.ZeitraumStatus;
import de.hdu.zimmerbelegung.model.Zimmer;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class AdminCtrl {

	@SuppressWarnings("serial")
	public class IndexCtrl extends GenericForwardComposer implements Composer {
		private Gast gast;
		private Zimmer zimmer;
		private Buchung buchung;
		private Reservierung reservierung;
		private TagStatus tagstatus;
		private ZeitraumStatus zeitraumstatus;
		
		public Gast getGast() {
			return gast;
		}

		public void setGast(Gast gast) {
			this.gast = gast;
		}
		
		public Zimmer getZimmer() {
			return zimmer;
		}

		public void setZimmer(Zimmer zimmer) {
			this.zimmer = zimmer;
		}
		
		public Buchung getBuchung() {
			return buchung;
		}

		public void setBuchung(Buchung buchung) {
			this.buchung = buchung;
		}
		

		public Reservierung getReservierung() {
			return reservierung;
		}

		public void setReservierung(Reservierung reservierung) {
			this.reservierung = reservierung;
		}
		
		public TagStatus getTagstatus() {
			return tagstatus;
		}

		public void setTagstatus(TagStatus tagstatus) {
			this.tagstatus = tagstatus;
		}
		
		public ZeitraumStatus getZeitstatus() {
			return zeitraumstatus;
		}

		public void setZeitraumstatus(ZeitraumStatus zeitraumstatus) {
			this.zeitraumstatus = zeitraumstatus;
		}
		
		
		public List<Gast> getAllGast(){
			AdminManager manager = ServiceLocator.getAdminManager();
			return manager.getAllGast();
		}
		
		public List<Zimmer> getAllZimmer(){
			AdminManager manager = ServiceLocator.getAdminManager();
			return manager.getAllZimmer();
		}
		
		public List<Buchung> getAllBuchung(){
			AdminManager manager = ServiceLocator.getAdminManager();
			return manager.getAllBuchung();
		}
		
		public List<Reservierung> getAllReservierung(){
			AdminManager manager = ServiceLocator.getAdminManager();
			return manager.getAllReservierung();
		}
				
		public List<TagStatus> getAllTagStatus(){
			AdminManager manager = ServiceLocator.getAdminManager();
			return manager.getAllTagStatus();
		}
		
		public List<ZeitraumStatus> getAllZeitraumStatus(){
			AdminManager manager = ServiceLocator.getAdminManager();
			return manager.getAllZeitraumStatus();
		}
		

		

		@Override
		public void doAfterCompose(Component comp) throws Exception {
			super.doAfterCompose(comp);
//			getAllZimmerKategorien();
		}


		public void onClick$addGast() {
			if (null == gast) {
				gast = new Gast();
			}
			AdminManager manager = ServiceLocator.getAdminManager();
			manager.add(gast);
			gast = null;
		}

		public void onClick$updateGast() {
			AdminManager manager = ServiceLocator.getAdminManager();
			manager.update(gast);
			gast = null;
		}

		public void onClick$deleteGast() {
			AdminManager manager = ServiceLocator.getAdminManager();
			manager.delete(gast);
			gast = null;
		}
	
		
		public void onClick$addZimmer() {
			if (null == zimmer) {
				zimmer = new Zimmer();
			}
			AdminManager manager = ServiceLocator.getAdminManager();
			manager.add(zimmer);
			zimmer = null;
		}

		public void onClick$updateZimmer() {
			AdminManager manager = ServiceLocator.getAdminManager();
			manager.update(zimmer);
			zimmer = null;
		}

		public void onClick$deleteZimmer() {
			AdminManager manager = ServiceLocator.getAdminManager();
			manager.delete(zimmer);
			zimmer = null;
		}
	
		
		public void onClick$addBuchung() {
			if (null == buchung) {
				buchung = new Buchung();
			}
			AdminManager manager = ServiceLocator.getAdminManager();
			manager.add(buchung);
			buchung = null;
		}

		public void onClick$updateBuchung() {
			AdminManager manager = ServiceLocator.getAdminManager();
			manager.update(buchung);
			buchung = null;
		}

		public void onClick$deleteBuchung() {
			AdminManager manager = ServiceLocator.getAdminManager();
			manager.delete(buchung);
			buchung = null;
		}
		
		
		public void onClick$addReservierung() {
			if (null == buchung) {
				reservierung = new Reservierung();
			}
			AdminManager manager = ServiceLocator.getAdminManager();
			manager.add(reservierung);
			reservierung = null;
		}

		public void onClick$updateReservierung() {
			AdminManager manager = ServiceLocator.getAdminManager();
			manager.update(reservierung);
			reservierung = null;
		}

		public void onClick$deleteReservierung() {
			AdminManager manager = ServiceLocator.getAdminManager();
			manager.delete(reservierung);
			reservierung = null;
		}
		
		
		public void onClick$addTagStatus() {
			if (null == tagstatus) {
				reservierung = new TagStatus();
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
		
		
		public void onClick$addZeitraumStatus() {
			if (null == buchung) {
				reservierung = new ZeitraumStatus();
			}
			AdminManager manager = ServiceLocator.getAdminManager();
			manager.add(zeitraumstatus);
			zeitraumstatus = null;
		}

		public void onClick$updateZeitraumStatus() {
			AdminManager manager = ServiceLocator.getAdminManager();
			manager.update(zeitraumstatus);
			zeitraumstatus = null;
		}

		public void onClick$deleteZeitraumStatus() {
			AdminManager manager = ServiceLocator.getAdminManager();
			manager.delete(zeitraumstatus);
			zeitraumstatus = null;
		}
	
}
