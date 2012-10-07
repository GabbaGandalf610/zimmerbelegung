/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package de.hdu.zimmerbelegung.ctrl;

import org.joda.time.LocalDate;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.dto.ZimmerZeitraumBelegungDto;
import de.hdu.zimmerbelegung.helper.ZimmerZeitraumBelegung;
import de.hdu.zimmerbelegung.service.ServiceLocator;

/**
 * Controller for "zimmerStatus.zul" which gives an overview of the current
 * state of bookings for all rooms within a given timespan.
 * 
 * @author Stefan Feilmeier, Roland Kühnel, Franz Wagner
 * 
 */
public class ZimmerStatusCtrl {
	LocalDate datumBis = new LocalDate().plusDays(10);
	LocalDate datumVon = new LocalDate();
	ListModelList<ZimmerZeitraumBelegung> zimmerZeitraumBelegungList;

	/**
	 * Get ending of the timespan. This will be "beginning of the timespan" plus
	 * ten days.
	 * 
	 * @return
	 */
    @DependsOn("datumVon")
	public LocalDate getDatumBis() {
		datumBis = datumVon.plusDays(10);
		return datumBis;
	}

	/**
	 * Get beginning of the timespan
	 * 
	 * @return
	 */
	public LocalDate getDatumVon() {
		return datumVon;
	}

	/**
	 * Set beginning of the timespan
	 * 
	 * @param datumVon
	 */
	public void setDatumVon(LocalDate datumVon) {
		this.datumVon = datumVon;
	}

	/**
	 * Get list of "Belegungen" for the selected timespan
	 * 
	 * @return
	 */
	@DependsOn({ "datumVon", "datumBis" })
	public ListModel<ZimmerZeitraumBelegung> getZimmerZeitraumBelegungList() {
		if (zimmerZeitraumBelegungList == null) {
			zimmerZeitraumBelegungList = new ListModelList<ZimmerZeitraumBelegung>();
		} else {
			zimmerZeitraumBelegungList.clear();
		}
		ZimmerZeitraumBelegungDto zimmerZeitraumBelegungDto = ServiceLocator
				.getZimmerZeitraumBelegungDto();
		zimmerZeitraumBelegungList.addAll(zimmerZeitraumBelegungDto.getAll(
				datumVon, datumVon.plusDays(10)));
		return zimmerZeitraumBelegungList;
	}

	/**
	 * Get todays date
	 * 
	 * @return
	 */
	@Command
	@NotifyChange({ "datumVon" })
	public void gotoHeute() {
		this.datumVon = new LocalDate();
		;
	}

	/**
	 * Get todays date
	 * 
	 * @return
	 */
	public LocalDate getHeute() {
		return new LocalDate();
	}

}
