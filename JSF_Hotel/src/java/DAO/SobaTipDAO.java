/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connection.DBConnection;
import Models.Hotel;
import Models.SobaTip;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author werfawf
 */
public class SobaTipDAO extends GenericEntity implements IgenericDAO<SobaTip> {

    public SobaTipDAO()
    {
        this.tableName = "soba_tip";
        this.getAllQuery = "SELECT * FROM `" + tableName + "`";
        this.insertQuery = "INSERT INTO " + tableName + " (`hotel_id`, `kreveti`, `slika`, `broj_soba`, `broj_slobodnih`, `opis`) VALUES (?, ?, ?, ?, ?, ?)";
        this.updateQuery = "UPDATE " + tableName + " SET `hotel_id`= ?,`kreveti`= ?,`slika`= ?,`broj_soba`=?,`broj_slobodnih`=?,`opis`=? WHERE id = ?";
        this.deleteQuery = "DELETE FROM " + tableName + " WHERE id = ?";
    }
    
    @Override
    public ArrayList<SobaTip> vratiSve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SobaTip pronadjiPoId(int Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean izmeni(SobaTip zaIzmenu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void obrisi(int Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean dodaj(SobaTip zaDodavanje) {
        try {
            conn = new DBConnection().connect();
            PreparedStatement ps = conn.prepareStatement(this.insertQuery);
            ps.setInt(1, zaDodavanje.getHotel_Id());
            ps.setInt(2, zaDodavanje.getKreveti());
            ps.setString(3, zaDodavanje.getSlika());
            ps.setInt(4, zaDodavanje.getBrojSoba());
            ps.setInt(5, zaDodavanje.getBrojSoba());
            ps.setString(6, zaDodavanje.getOpis());
            
            int i = ps.executeUpdate();
            conn.close();
            if (i != 0) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri dodavanju Sobe: " + ex.getMessage());
        }

        return false;
    }

    @Override
    public ArrayList<SobaTip> pronadjiPoPolju(String nazivPolja, String vrednostPolja) {
        try {
            conn = new DBConnection().connect();
            String kveri = this.getAllQuery + " WHERE " + nazivPolja + " = ?";
            PreparedStatement ps = conn.prepareStatement(kveri);

            ps.setString(1, vrednostPolja);
            ResultSet rs = ps.executeQuery();

            ArrayList<SobaTip> foundHotels = new ArrayList<SobaTip>();
            while (rs.next()) {
                SobaTip h = new SobaTip();
                h.setId(rs.getInt("id"));
                h.setBrojSlobodnih(rs.getInt("broj_slobodnih"));
                h.setOpis(rs.getString("opis"));
                h.setBrojSoba(rs.getInt("broj_soba"));
                h.setHotel_Id(rs.getInt("hotel_id"));
                h.setSlika(rs.getString("slika"));
                h.setKreveti(rs.getInt("kreveti"));
                foundHotels.add(h);
            }
            conn.close();
            return foundHotels;

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri pronalazenju hotela: " + ex.getMessage());
        }

        return null;
    }
    public ArrayList<SobaTip> pronadjiSobeHotela(int hotelId)
    {
        try {
            conn = new DBConnection().connect();
            String kveri = this.getAllQuery + " WHERE hotel_id = ?";
            PreparedStatement ps = conn.prepareStatement(kveri);

            ps.setInt(1, hotelId);
            ResultSet rs = ps.executeQuery();

            ArrayList<SobaTip> sobe = new ArrayList<SobaTip>();
            while (rs.next()) {
                SobaTip h = new SobaTip();
                h.setId(rs.getInt("id"));
                h.setBrojSlobodnih(rs.getInt("broj_slobodnih"));
                h.setOpis(rs.getString("opis"));
                h.setBrojSoba(rs.getInt("broj_soba"));
                h.setHotel_Id(rs.getInt("hotel_id"));
                h.setSlika(rs.getString("slika"));
                h.setKreveti(rs.getInt("kreveti"));
                sobe.add(h);
            }
            conn.close();
            return sobe;

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri pronalazenju soba hotela: " + ex.getMessage());
        }

        return null;
    }
    
}
