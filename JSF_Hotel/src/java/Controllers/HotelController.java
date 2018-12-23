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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Named(value = "hotelController")
@ViewScoped
public class HotelController implements Serializable {

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

    private Hotel hotel;
    private Part slika;
    private HotelDAO dao;
    /**
     * Creates a new instance of HotelController
     */
    public HotelController() {
        this.dao = new HotelDAO();
    }

    /**
     * @return the hotel
     */
    public Hotel getHotel() {
        if (this.hotel == null) {
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

    /* 
    Ako neko drugi ovo pokrece mora da izmeni folderPath promenljivu,
    jer je absolutna putanja u mom kompjuteru!
     */
    private void snimiSlikuHotela(Part slika) {
        String fileName = Instant.now().toString() + slika.getSubmittedFileName();
        String folderPath = ("/home/werfawf/NetBeansProjects/Projekat_JSF_Hotel/JSF_Hotel/web/resources/images/uploads/hoteliSlike/");

        this.hotel.setSlika(fileName);
        try {
            InputStream is = slika.getInputStream();
            Files.copy(is, new File(folderPath, fileName).toPath());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void obrisiSliku(String slika) {
        String folderPath = ("/home/werfawf/NetBeansProjects/Projekat_JSF_Hotel/JSF_Hotel/web/resources/images/uploads/hoteliSlike/");
        try {
            Files.deleteIfExists(new File(folderPath, slika).toPath());
        } catch (IOException ex) {
            System.out.println("Doslo je do greske pri brisanju slike: " + ex.getMessage());
        }
    }

    public String dodajHotel() {
        int sameNames = this.dao.pronadjiPoPolju("naziv", this.hotel.getNaziv()).size();
        int greske = 0;
        if (sameNames != 0) {
            greske++;
            FacesContext.getCurrentInstance().addMessage("dodajHotel:naziv", new FacesMessage("Hotel sa istim imenom vec postoji!"));
            return "";
        }
        this.snimiSlikuHotela(this.getSlika());
        boolean dodato = this.dao.dodaj(this.hotel);
        if (greske == 0 && dodato) {
            RedirectHelper.redirect("/hoteli/lista.xhtml");
            return "";
        } else if (!dodato) {
            FacesContext.getCurrentInstance().addMessage("dodajHotel:naziv", new FacesMessage("Doslo je do greske pri dodavanju!"));
            return "";
        } else {
            return ("");
        }
    }

    public ArrayList<Hotel> vratiSveHotele() {
        return this.dao.vratiSve();
    }

    public ArrayList<User> vratiSlobodneMenadzere() {
        return new UserDAO().vratiSlobodneMenadzere();
    }

    public String obrisi(int Id) {
        String slika = this.dao.pronadjiPoId(Id).getSlika();
        this.obrisiSliku(slika);
        this.dao.obrisi(Id);
        return "";
    }

    public Hotel vratiPoNazivu(String naziv) {

        if (!naziv.isEmpty()) {
            Hotel hot = this.dao.pronadjiPoNazivu(naziv);
            if (hot == null) {
                RedirectHelper.returnError(404, "Hotel nije nadjen");
            }
            this.hotel = hot;
            return hot;
        } else {
            RedirectHelper.returnError(404, "Nepravilno formiran zahtev!");
        }
        return null;

    }

    public void izmeniHotel() {
        int sameNames = this.dao.pronadjiPoPolju("naziv", this.hotel.getNaziv()).size();
        Hotel h = this.dao.pronadjiPoId(this.hotel.getId());
        int greske = 0;
        if (sameNames != 0 && !this.hotel.getNaziv().equals(h.getNaziv())) {
            greske++;
            FacesContext.getCurrentInstance().addMessage("dodajHotel:naziv", new FacesMessage("Hotel sa istim imenom vec postoji!"));
        }
        if (this.slika != null) {
            this.obrisiSliku(this.hotel.getSlika());
            this.snimiSlikuHotela(this.getSlika());
        }
        boolean izmenjeno = this.dao.izmeni(this.hotel);
        if (greske == 0 && izmenjeno) {
            RedirectHelper.redirect("/hoteli/lista.xhtml");
           
        } else if (!izmenjeno) {
            FacesContext.getCurrentInstance().addMessage("dodajHotel:naziv", new FacesMessage("Doslo je do greske pri izmeni!"));
            
        } 
    }

}
