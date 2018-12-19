/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.servlet.http.Part;

/**
 *
 * @author werfawf
 */
public class Hotel {

    private int id;
    private String naziv;
    private Part slika;
    private String opis;
    private int menadzer_id;
    private String adresa;
    private String telefon;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the naziv
     */
    public String getNaziv() {
        return naziv;
    }

    /**
     * @param naziv the naziv to set
     */
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

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
     * @return the opis
     */
    public String getOpis() {
        return opis;
    }

    /**
     * @param opis the opis to set
     */
    public void setOpis(String opis) {
        this.opis = opis;
    }

    /**
     * @return the menadzer_id
     */
    public int getMenadzer_id() {
        return menadzer_id;
    }

    /**
     * @param menadzer_id the menadzer_id to set
     */
    public void setMenadzer_id(int menadzer_id) {
        this.menadzer_id = menadzer_id;
    }

    /**
     * @return the adresa
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * @param adresa the adresa to set
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    /**
     * @return the telefon
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * @param telefon the telefon to set
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

}
