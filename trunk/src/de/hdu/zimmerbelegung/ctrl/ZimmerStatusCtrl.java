/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/

package de.hdu.zimmerbelegung.ctrl;

import org.joda.time.LocalDate;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.dto.ZimmerZeitraumBelegungDto;
import de.hdu.zimmerbelegung.helper.ZimmerZeitraumBelegung;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class ZimmerStatusCtrl {
	LocalDate datumBis = new LocalDate().plusDays(10);
	LocalDate datumVon = new LocalDate();
	ListModelList<ZimmerZeitraumBelegung> zimmerZeitraumBelegungList;

	@DependsOn("datumVon")
	public LocalDate getDatumBis() {
		datumBis = datumVon.plusDays(10);
		return datumBis;
	}
	public LocalDate getDatumVon() {
		return datumVon;
	}
	public void setDatumVon(LocalDate datumVon) {
		this.datumVon = datumVon;
	}
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
				datumVon, datumBis));
		return zimmerZeitraumBelegungList;
	}
}
