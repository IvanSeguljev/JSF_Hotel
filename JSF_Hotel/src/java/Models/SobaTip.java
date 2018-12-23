/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author werfawf
 */
public class SobaTip {

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the hotel_Id
     */
    public int getHotel_Id() {
        return hotel_Id;
    }

    /**
     * @param hotel_Id the hotel_Id to set
     */
    public void setHotel_Id(int hotel_Id) {
        this.hotel_Id = hotel_Id;
    }

    /**
     * @return the kreveti
     */
    public int getKreveti() {
        return kreveti;
    }

    /**
     * @param kreveti the kreveti to set
     */
    public void setKreveti(int kreveti) {
        this.kreveti = kreveti;
    }

    /**
     * @return the slika
     */
    public String getSlika() {
        return slika;
    }

    /**
     * @param slika the slika to set
     */
    public void setSlika(String slika) {
        this.slika = slika;
    }

    /**
     * @return the brojSoba
     */
    public int getBrojSoba() {
        return brojSoba;
    }

    /**
     * @param brojSoba the brojSoba to set
     */
    public void setBrojSoba(int brojSoba) {
        this.brojSoba = brojSoba;
    }

    /**
     * @return the brojSlobodnih
     */
    public int getBrojSlobodnih() {
        return brojSlobodnih;
    }

    /**
     * @param brojSlobodnih the brojSlobodnih to set
     */
    public void setBrojSlobodnih(int brojSlobodnih) {
        this.brojSlobodnih = brojSlobodnih;
    }

    /**
     * @return the Opis
     */
    public String getOpis() {
        return Opis;
    }

    /**
     * @param Opis the Opis to set
     */
    public void setOpis(String Opis) {
        this.Opis = Opis;
    }
    private int Id;
    private int hotel_Id;
    private int kreveti;
    private String slika;
    private int brojSoba;
    private int brojSlobodnih;
    private String Opis;
}
