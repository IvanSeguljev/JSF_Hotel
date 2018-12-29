/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.RezervacijaDAO;
import DAO.SobaTipDAO;
import DAO.UserDAO;
import Helpers.RedirectHelper;
import Helpers.SessionUtils;
import Models.Rezervacija;
import Models.SobaTip;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author werfawf
 */
@Named(value = "rezervacijaController")
@ViewScoped
public class RezervacijaController implements Serializable {

    /**
     * @return the rezervacija
     */
    public Rezervacija getRezervacija() {
        if(this.rezervacija == null)
        {
            this.rezervacija = new Rezervacija();
        }
        return rezervacija;
    }

    /**
     * @param rezervacija the rezervacija to set
     */
    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    
    /**
     * Creates a new instance of IznajmljivanjeController
     */
    public RezervacijaController() {
        dao = new RezervacijaDAO();
    }
    
    private Rezervacija rezervacija;
    private RezervacijaDAO dao;
    
    public void rezervisi()
    {
        Map<String, String> parameterMap = (Map<String, String>) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int sid = Integer.parseInt(parameterMap.get("soba"));
        String naziv = parameterMap.get("naziv");
        if(SessionUtils.getUserId() != 0)
        {
            SobaTip soba = new SobaTipDAO().pronadjiPoId(sid);
            if(soba != null)
            {
                int poeni = (int)(soba.getCena()*this.rezervacija.getOstaje_dana()/100);
                this.rezervacija.setSoba_id(soba.getId());
                this.rezervacija.setCena(soba.getCena()*this.rezervacija.getOstaje_dana());
                this.rezervacija.setKorisnik_id(SessionUtils.getUserId());
                this.rezervacija.setPoeni(poeni);
                this.rezervacija.setHotel(naziv);
                boolean dodato = this.dao.dodaj(this.rezervacija);
                if(dodato)
                {
                    dao.uvecajPoene(SessionUtils.getUserId(), poeni);
                    dao.smanjiBrojSlobodnih(soba.getId());
                    RedirectHelper.redirect("/account/details.xhtml");
                }
            }
            else
            {
                RedirectHelper.returnError(404, "Soba nije nadjena!");
            }
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage("login:name", new FacesMessage("Morate biti ulogovani da biste iznajmili sobu!"));
            RedirectHelper.redirect("/account/login.xhtml");
        }
    }
    
    public ArrayList<Rezervacija> vratiRezervacijeKorisnika(String id)
    {
        
       return dao.pronadjiPoPolju("korisnik_id", id);
    }
    public ArrayList<Rezervacija> vratiRezervacijeHotela(String hotel)
    {
        
       return dao.pronadjiPoHotelu(hotel);
    }
    public void otkazi(int rezervacijaId)
    {
        Rezervacija rez =  dao.pronadjiPoId(rezervacijaId);
        dao.obrisi(rez.getId());
        dao.uvecajBrojSlobodnih(rez.getSoba_id());
        dao.sniziPoene(rez.getKorisnik_id(), rez.getPoeni());
    }
    public void korisnikStigao(int rezId)
    {
        dao.korisnikStigao(rezId);
    }
     public void korisnikOtisao(int rezId)
    {
        System.out.print(rezId);
        Rezervacija rez = dao.pronadjiPoId(rezId);
        dao.korisnikOtisao(rezId);
        dao.uvecajBrojSlobodnih(rez.getSoba_id());
        
    }
}
