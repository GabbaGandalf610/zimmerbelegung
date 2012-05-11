package de.hdu.zimmerbelegung.dto;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import de.hdu.zimmerbelegung.dao.BelegungDao;
import de.hdu.zimmerbelegung.dao.ZimmerDao;
import de.hdu.zimmerbelegung.helper.DatumStatus;
import de.hdu.zimmerbelegung.helper.Status;
import de.hdu.zimmerbelegung.helper.ZimmerZeitraumBelegung;
import de.hdu.zimmerbelegung.model.Belegung;
import de.hdu.zimmerbelegung.model.Buchung;
import de.hdu.zimmerbelegung.model.Reservierung;
import de.hdu.zimmerbelegung.model.Zimmer;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class ZimmerZeitraumBelegungDto extends HibernateDaoSupport {
	public List<ZimmerZeitraumBelegung> getAll(LocalDate vonDatum,
			LocalDate bisDatum) {
		// Belegungen im Zeitraum holen
		BelegungDao belegungDao = ServiceLocator.getBelegungDao();
		List<Belegung> belegungList = belegungDao.getAllInZeitraum(vonDatum,
				bisDatum);

		// Anzahl der Zimmer auslesen
		ZimmerDao zimmerDao = ServiceLocator.getZimmerDao();
		List<Zimmer> zimmerList = zimmerDao.getAll();

		// Temporäre Variablen vorbereiten
		List<ZimmerZeitraumBelegung> zimmerZeitraumBelegungList = new ArrayList<ZimmerZeitraumBelegung>();

		// Zimmer durchlaufen
		for (Zimmer zimmer : zimmerList) {
			ZimmerZeitraumBelegung zimmerZeitraumBelegung = new ZimmerZeitraumBelegung(
					zimmer);
			// Belegungen ("RESERVIERT" oder "GEBUCHT" herausfinden)
			for (Belegung belegung : belegungList) {
				if (belegung.getZimmer().equals(zimmer)) {
					if (belegung instanceof Reservierung) {
						zimmerZeitraumBelegung.addDatumStats(new DatumStatus(
								belegung.getDatum(), Status.RESERVIERT));
					} else if (belegung instanceof Buchung) {
						zimmerZeitraumBelegung.addDatumStats(new DatumStatus(
								belegung.getDatum(), Status.GEBUCHT));
					}
				}
			}
			// Restliche Tage mit Status "FREI" auffüllen
			for (LocalDate tmpDatum = vonDatum; tmpDatum.isBefore(bisDatum)
					|| tmpDatum.equals(bisDatum); tmpDatum = tmpDatum
					.plusDays(1)) {
				boolean datumGefunden = false;
				for (DatumStatus datumStatus : zimmerZeitraumBelegung
						.getDatumStatusList()) {
					if (datumStatus.getDatum().equals(tmpDatum)) {
						datumGefunden = true;
						break;
					}
				}
				if (!datumGefunden) {
					zimmerZeitraumBelegung.addDatumStats(new DatumStatus(
							tmpDatum, Status.FREI));
				}
			}
			zimmerZeitraumBelegungList.add(zimmerZeitraumBelegung);
		}
		return zimmerZeitraumBelegungList;
	}
}
