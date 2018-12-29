/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author werfawf
 */
public class Rezervacija {

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the korisnik
     */
    public String getKorisnik() {
        return korisnik;
    }

    /**
     * @param korisnik the korisnik to set
     */
    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    
   

    /**
     * @return the hotel
     */
    public String getHotel() {
        return hotel;
    }

    /**
     * @param hotel the hotel to set
     */
    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    private String korisnik;
    private int id;
    private int korisnik_id;
    private int soba_id;
    private float cena;
    private int poeni;
    private int ostaje_dana;
    private String komentar;
    private Date datum_iznajmljeno;
    private String hotel;
    private String status;

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
     * @return the korisnik_id
     */
    public int getKorisnik_id() {
        return korisnik_id;
    }

    /**
     * @param korisnik_id the korisnik_id to set
     */
    public void setKorisnik_id(int korisnik_id) {
        this.korisnik_id = korisnik_id;
    }

    /**
     * @return the soba_id
     */
    public int getSoba_id() {
        return soba_id;
    }

    /**
     * @param soba_id the soba_id to set
     */
    public void setSoba_id(int soba_id) {
        this.soba_id = soba_id;
    }

    /**
     * @return the cena
     */
    public float getCena() {
        return cena;
    }

    /**
     * @param cena the cena to set
     */
    public void setCena(float cena) {
        this.cena = cena;
    }

    /**
     * @return the poeni
     */
    public int getPoeni() {
        return poeni;
    }

    /**
     * @param poeni the poeni to set
     */
    public void setPoeni(int poeni) {
        this.poeni = poeni;
    }

    /**
     * @return the ostaje_dana
     */
    public int getOstaje_dana() {
        return ostaje_dana;
    }

    /**
     * @param ostaje_dana the ostaje_dana to set
     */
    public void setOstaje_dana(int ostaje_dana) {
        this.ostaje_dana = ostaje_dana;
    }

    /**
     * @return the komentar
     */
    public String getKomentar() {
        return komentar;
    }

    /**
     * @param komentar the komentar to set
     */
    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    /**
     * @return the datum_iznajmljeno
     */
    public Date getDatum_iznajmljeno() {
        return datum_iznajmljeno;
    }

    /**
     * @param datum_iznajmljeno the datum_iznajmljeno to set
     */
    public void setDatum_iznajmljeno(Date datum_iznajmljeno) {
        this.datum_iznajmljeno = datum_iznajmljeno;
    }

    /**
     * @return the datum_dolaska
     */
   

   

}
