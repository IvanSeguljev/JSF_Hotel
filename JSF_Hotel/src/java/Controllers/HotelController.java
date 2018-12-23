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

    /**
     * Creates a new instance of HotelController
     */
    public HotelController() {
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
        HotelDAO dataAcces = new HotelDAO();
        int sameNames = dataAcces.pronadjiPoPolju("naziv", this.hotel.getNaziv()).size();
        int greske = 0;
        if (sameNames != 0) {
            greske++;
            FacesContext.getCurrentInstance().addMessage("dodajHotel:naziv", new FacesMessage("Hotel sa istim imenom vec postoji!"));
            return "";
        }
        this.snimiSlikuHotela(this.getSlika());
        boolean dodato = dataAcces.dodaj(this.hotel);
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
        HotelDAO dao = new HotelDAO();

        return dao.vratiSve();
    }

    public ArrayList<User> vratiSlobodneMenadzere() {
        UserDAO dao = new UserDAO();

        return dao.vratiSlobodneMenadzere();
    }

    public String obrisi(int Id) {
        HotelDAO dao = new HotelDAO();
        String slika = dao.pronadjiPoId(Id).getSlika();
        this.obrisiSliku(slika);
        dao.obrisi(Id);
        return "";
    }

    public void vratiPoNazivu(String naziv) {

        if (!naziv.isEmpty()) {
            HotelDAO h = new HotelDAO();
            Hotel hot = h.pronadjiPoNazivu(naziv);
            if (hot == null) {
                RedirectHelper.returnError(404, "Hotel nije nadjen");
            }
            this.hotel = hot;
        } else {
            RedirectHelper.returnError(404, "Nepravilno formiran zahtev!");
        }

    }

    public void izmeniHotel() {
        HotelDAO dataAcces = new HotelDAO();
        int sameNames = dataAcces.pronadjiPoPolju("naziv", this.hotel.getNaziv()).size();
        Hotel h = dataAcces.pronadjiPoId(this.hotel.getId());
        int greske = 0;
        if (sameNames != 0 && !this.hotel.getNaziv().equals(h.getNaziv())) {
            greske++;
            FacesContext.getCurrentInstance().addMessage("dodajHotel:naziv", new FacesMessage("Hotel sa istim imenom vec postoji!"));
        }
        if (this.slika != null) {
            this.obrisiSliku(this.hotel.getSlika());
            this.snimiSlikuHotela(this.getSlika());
        }
        boolean izmenjeno = dataAcces.izmeni(this.hotel);
        if (greske == 0 && izmenjeno) {
            RedirectHelper.redirect("/hoteli/lista.xhtml");
           
        } else if (!izmenjeno) {
            FacesContext.getCurrentInstance().addMessage("dodajHotel:naziv", new FacesMessage("Doslo je do greske pri izmeni!"));
            
        } 
    }

}
