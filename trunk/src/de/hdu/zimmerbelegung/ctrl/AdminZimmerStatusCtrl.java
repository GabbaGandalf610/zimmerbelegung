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

public class AdminZimmerStatusCtrl {
	LocalDate datumBis = new LocalDate().plusDays(10);
	LocalDate datumVon = new LocalDate();

	@DependsOn("datumVon")
	public LocalDate getDatumBis() {
		datumBis = datumVon.plusDays(10);
		return datumBis;
	}

	public LocalDate getDatumVon() {
		return datumVon;
	}

	public void setDatumBis(LocalDate datumBis) {
		this.datumBis = datumVon.plusDays(10);
	}

	public void setDatumVon(LocalDate datumVon) {
		this.datumVon = datumVon;
	}



}
