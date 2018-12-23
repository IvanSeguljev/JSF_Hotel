/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.SobaTipDAO;
import Models.SobaTip;
import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author werfawf
 */
@Named(value = "sobaTipController")
@ViewScoped
public class SobaTipController implements Serializable {

    /**
     * @return the soba
     */
    public SobaTip getSoba() {
        if(this.soba == null)
            this.soba = new SobaTip();
        return soba;
    }

    /**
     * @param soba the soba to set
     */
    public void setSoba(SobaTip soba) {
        this.soba = soba;
    }

    private SobaTip soba;
    private SobaTipDAO dao;
    public SobaTipController() {
    }
    
    public ArrayList<SobaTip> prikaziSobeHotela(int hotelId)
    {
        return this.dao.pronadjiSobeHotela(hotelId);
        
    }
    
}
