/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import DAO.HotelDAO;
import DAO.UserDAO;
import Helpers.RedirectHelper;
import Models.Hotel;
import Models.User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author werfawf
 */
@Named(value = "hotelController")
@ViewScoped
public class HotelController implements Serializable {

    private Hotel hotel;
    /**
     * Creates a new instance of HotelController
     */
    public HotelController() {
    }

    /**
     * @return the hotel
     */
    public Hotel getHotel() {
        if(this.hotel == null)
        {
            this.hotel = new Hotel();
        }
        return hotel;
    }

    /**
     * @param hotel the hotel to set
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    
    public String dodajHotel()
    {
        HotelDAO dataAcces = new HotelDAO();
        int sameNames = dataAcces.pronadjiPoPolju("naziv", this.hotel.getNaziv()).size();
        int greske = 0;
        if (sameNames != 0) {
            greske ++;
            FacesContext.getCurrentInstance().addMessage("dodajHotel:naziv", new FacesMessage("Hotel sa istim imenom vec postoji!"));
        }
        boolean dodato = dataAcces.dodaj(this.hotel);
        if (greske == 0 && dodato) {
           RedirectHelper.redirect("/hoteli/lista.xhtml");
           return "";
        }
        else if(!dodato)
        {
            FacesContext.getCurrentInstance().addMessage("dodajHotel:naziv", new FacesMessage("Doslo je do greske pri dodavanju!"));
            return "";
        }
        else {
            return ("");
        }
    }
    
    public ArrayList<Hotel> vratiSveHotele()
    {
        HotelDAO dao = new HotelDAO();
        
        return dao.vratiSve();
    }
    
    public ArrayList<User> vratiSlobodneMenadzere()
    {
        UserDAO dao = new UserDAO();
        return dao.vratiSlobodneMenadzere();
    }
    
    public String obrisi(int Id) {
        HotelDAO dao = new HotelDAO();
        dao.obrisi(Id);
        return "";
    }
    
}
