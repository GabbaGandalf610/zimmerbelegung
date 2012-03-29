/*******************************************************************************
 * Copyright (c) 2012 Stefan Feilmeier, Franz Wagner, Roland Kühnel.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package de.hdu.zimmerbelegung.ctrl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import de.hdu.zimmerbelegung.dao.BuchungDao;

@Controller
public class BuchungCtrl {

	private static Logger log = Logger.getLogger(BuchungCtrl.class);
	private BuchungDao buchungDao = null;

	public void setBuchungDao(BuchungDao buchungDao) {
		this.buchungDao = buchungDao;
	}

		/**
	     * <p> Boot form request.</p>
	     * 
	     * <p>Expected HTTP GET and request '/EditBoot.html'.</p>
	     * @return 
	     */
	    @RequestMapping(value="/EditBoot.html", method=RequestMethod.GET)
	    public ModelAndView edit(@RequestParam(required=false) Integer id) {
	    	log.debug("EditBoot.html");
	    	ModelAndView mv = new ModelAndView();
	    	if (id == null) {
	    		mv.addObject(new Boot());
	    	} else { 
	    		mv.addObject(bootDao.findById(id));
	    	}
	    	mv.setViewName("edit-boot");
	    	return mv;
	    }

	    /**
	     * <p>Saves a {@link Boot}.</p>
	     * 
	     * <p>Expected HTTP POST and request '/SaveBoot.html'.</p>
	     * @return 
	     */
	    @RequestMapping(value="/SaveBoot.html", method=RequestMethod.POST)
	    public String save(Boot boot, Model model) {
	        if (boot.getCreated() == null) {
	            boot.setCreated(new Date());
	        }
	        bootDao.save(boot);
	        return "redirect:AlleBoote.html";
	    }

	    /**
	     * <p>Deletes a Boot.</p>
	     * 
	     * <p>Expected HTTP POST and request '/LoescheBoot.html'.</p>
	     */
	    @RequestMapping(value="/LoescheBoot.html", method=RequestMethod.GET)
	    public ModelAndView delete(Boot boot) {
	        try {
				bootDao.delete(boot);
		        return new ModelAndView("redirect:AlleBoote.html");
			} catch (DaoException e) {
				ModelAndView mv = new ModelAndView("error");
				mv.addObject("message", e.getMessage());
				return mv;
			}
	    }

	    /**
	     * <p>Searches for all boats and returns them in a 
	     * <code>Collection</code>.</p>
	     * 
	     * <p>Expected HTTP GET and request '/AlleBoote.html'.</p>
	     */
	    @RequestMapping(value="/AlleBoote.html", method=RequestMethod.GET)
	    public ModelAndView findAll() {
			ModelAndView mv = new ModelAndView();
			mv.addObject("title", "Boote");
			mv.addObject("message", "Alle Boote des Vereins");
			mv.addObject("boote", bootDao.findAll());
			mv.addObject("edit", true);
			mv.setViewName("list-boote");
			return mv;
	    }

	    /**
	     * <p>Searches for all free foats and returns them in a 
	     * <code>Collection</code>.</p>
	     * 
	     * <p>Expected HTTP GET and request '/FreieBoote.html'.</p>
	     */
	    @RequestMapping(value="/FreieBoote.html", method=RequestMethod.GET)
	    public ModelAndView freieBoote() {
			ModelAndView mv = new ModelAndView();
			mv.addObject("title", "Fahrt beginnen");
			mv.addObject("message", "Folgende Boote sind für eine Fahrt verfügbar. Wählen Sie ein Boot aus:");
			mv.addObject("boote", bootDao.findFreie());
			mv.addObject("edit", false);
			mv.setViewName("list-boote");
			return mv;
	    }

	}

