/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Roland Kühnel, Franz Wagner.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.ctrl;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import de.hdu.zimmerbelegung.dao.ZimmerDao;
import de.hdu.zimmerbelegung.model.Zimmer;
import de.hdu.zimmerbelegung.service.ServiceLocator;

public class AdminZimmerCtrl {
	ListModelList<Zimmer> zimmerList;
	Zimmer zimmerSelected;
	
	public void setZimmerSelected(Zimmer  zimmerSelected){
		this.zimmerSelected = zimmerSelected;
	}
	
	public Zimmer getZimmerSelected(){
		return zimmerSelected;
	}
	
	public ListModel<Zimmer> getItems(){
		if(zimmerList == null){
			zimmerList = new ListModelList<Zimmer>();
			ZimmerDao zimmerDao = ServiceLocator.getZimmerDao();
			zimmerList.addAll(zimmerDao.getAll());
		}
		return zimmerList;
	}

	@Command
	@NotifyChange({ "zimmerSelected", "zimmerList" })
	public void doNew(){
		zimmerSelected = new Zimmer();
	}
	
	@Command
	@NotifyChange({ "zimmerSelected", "zimmerList" })
	public void doSave(){
		ZimmerDao zimmerdao = ServiceLocator.getZimmerDao();
		zimmerdao.saveOrUpdate(zimmerSelected);
		if (!zimmerList.contains(zimmerSelected)){
			zimmerList.add(zimmerSelected);
		}
	}
	
	@Command
	@NotifyChange({ "zimmerSelected", "zimmerList" })
	public void doDelete(){
		ZimmerDao zimmerDao = ServiceLocator.getZimmerDao();
		zimmerDao.delete(zimmerSelected);
		zimmerList.remove(zimmerSelected);
		zimmerSelected = null;
	}
}
