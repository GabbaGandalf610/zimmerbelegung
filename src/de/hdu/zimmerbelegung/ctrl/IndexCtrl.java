package de.hdu.zimmerbelegung.ctrl;

import java.util.List;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class IndexCtrl extends SelectorComposer<Window> {
/*	private ZimmerKategorieDao zimmerKategorieDao = null;
    public void setZimmerKategorieDao(ZimmerKategorieDao zimmerKategorieDao) {
		this.zimmerKategorieDao = zimmerKategorieDao;
    }*/
	private static final long serialVersionUID = 8380561958492646209L;
	@Wire
    Textbox input;
    @Wire
    Label output;
     
    @Listen("onClick=#ok")
    public void submit() {
    	//List<ZimmerKategorie> zk = zimmerKategorieDao.findAll();
    	//output.setValue(zk.toString());
    	output.setValue(input.getValue());
    }
    @Listen("onClick=#cancel")
    public void cancel() {
        output.setValue("");
    }
}
