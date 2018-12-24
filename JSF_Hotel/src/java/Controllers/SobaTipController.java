/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.HotelDAO;
import DAO.SobaTipDAO;
import Helpers.RedirectHelper;
import Models.SobaTip;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;

/**
 *
 * @author werfawf
 */
@Named(value = "sobaTipController")
@ViewScoped
public class SobaTipController implements Serializable {

    /**
     * @return the slika
     */
    public Part getSlika() {
        return slika;
    }

    /**
     * @param slika the slika to set
     */
    public void setSlika(Part slika) {
        this.slika = slika;
    }

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
    private Part slika;
    private SobaTipDAO dao;
    
    public SobaTipController() {
        this.dao = new SobaTipDAO();
    }
    
     private void snimiSlikuSobe(Part slika) {
        String fileName = Instant.now().toString() + slika.getSubmittedFileName();
        String folderPath = ("/home/werfawf/NetBeansProjects/Projekat_JSF_Hotel/JSF_Hotel/web/resources/images/uploads/sobeSlike/");

        this.soba.setSlika(fileName);
        try {
            InputStream is = slika.getInputStream();
            Files.copy(is, new File(folderPath, fileName).toPath());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void obrisiSliku(String slika) {
        String folderPath = ("/home/werfawf/NetBeansProjects/Projekat_JSF_Hotel/JSF_Hotel/web/resources/images/uploads/sobeSlike/");
        try {
            Files.deleteIfExists(new File(folderPath, slika).toPath());
        } catch (IOException ex) {
            System.out.println("Doslo je do greske pri brisanju slike: " + ex.getMessage());
        }
    }
    
    
    
    public ArrayList<SobaTip> prikaziSobeHotela(int hotelId)
    {
        return this.dao.pronadjiSobeHotela(hotelId);
        
    }
    
    public String dodajSobu()
    {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String naziv = parameterMap.get("naziv");
        
        this.snimiSlikuSobe(this.getSlika());
        boolean dodato = this.dao.dodaj(this.soba);
        if (!dodato) {
            FacesContext.getCurrentInstance().addMessage("dodajSobuForm:opis", new FacesMessage("Doslo je do greske pri dodavanju!"));
            return "";
        } else {
            RedirectHelper.redirect("/hoteli/detalji.xhtml?naziv="+naziv);
            return ("");
        }
    }
    public String obrisi()
    {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int id = Integer.parseInt(parameterMap.get("id"));
        String naziv = parameterMap.get("naziv");
        this.soba = this.dao.pronadjiPoId(id);
        this.obrisiSliku(this.soba.getSlika());
        this.dao.obrisi(id);
        RedirectHelper.redirect("/hoteli/detalji.xhtml?naziv="+naziv);
        return "";
    }
    public void izmeniSobu() {
       
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
       String naziv = parameterMap.get("naziv");
        if (this.slika != null) {
            this.obrisiSliku(this.soba.getSlika());
            this.snimiSlikuSobe(this.getSlika());
        }
        SobaTip stara = this.dao.pronadjiPoId(this.soba.getId());
        if((stara.getBrojSoba() - stara.getBrojSlobodnih() )>this.soba.getBrojSoba())
        {
             FacesContext.getCurrentInstance().addMessage("izmeniSobu:brojSoba", new FacesMessage("Broj soba ne moze biti manji od broja trenutno iznajmljenih soba! (" + (stara.getBrojSoba()-stara.getBrojSlobodnih()) + ")"));
             
        }
        else{
            this.soba.setBrojSlobodnih(stara.getBrojSlobodnih() + (this.soba.getBrojSoba()-stara.getBrojSoba()));
        boolean izmenjeno = this.dao.izmeni(this.soba);
        if (izmenjeno) {
            
            RedirectHelper.redirect("/hoteli/detalji.xhtml?naziv="+naziv);
           
            
        } 
        else {
            FacesContext.getCurrentInstance().addMessage("dodajHotel:naziv", new FacesMessage("Doslo je do greske pri izmeni!"));
            
        }} 
    }
    
    public void setujSobuHotela()
    {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int id = (parameterMap.get("soba") == null) ? 0 : Integer.parseInt(parameterMap.get("soba"));
       if(id != 0)
       {
        this.soba = this.dao.pronadjiPoId(id);
        if(this.soba == null)
            RedirectHelper.returnError(404, "Soba nije nadjena");
       }
       else RedirectHelper.returnError(404, "Soba nije nadjena");
        
        
    }
    
    
}
