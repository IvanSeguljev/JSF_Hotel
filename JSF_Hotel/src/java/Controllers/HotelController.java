/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import DAO.HotelDAO;
import DAO.UserDAO;
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
        boolean dodato = dataAcces.dodaj(this.hotel);
        if (dodato) {
           return("");
        } else {
            return ("");
        }
    }
    
    public ArrayList<User> vratiSlobodneMenadzere()
    {
        UserDAO dao = new UserDAO();
        return dao.vratiSlobodneMenadzere();
    }
    
}
