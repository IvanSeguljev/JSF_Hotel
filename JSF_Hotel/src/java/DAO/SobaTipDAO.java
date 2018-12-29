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
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author werfawf
 */
public class SobaTipDAO extends GenericEntity<SobaTip> {

    public SobaTipDAO()
    {
        this.tableName = "soba_tip";
        this.getAllQuery = "SELECT * FROM `" + tableName + "`";
        this.insertQuery = "INSERT INTO " + tableName + " (`hotel_id`, `kreveti`, `slika`, `broj_soba`, `broj_slobodnih`, `opis`, `cena`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        this.updateQuery = "UPDATE " + tableName + " SET `hotel_id`= ?,`kreveti`= ?,`slika`= ?,`broj_soba`=?,`broj_slobodnih`=?,`opis`=?, `cena`=? WHERE id = ?";
        this.deleteQuery = "DELETE FROM " + tableName + " WHERE id = ?";
    }
    
    @Override
    public ArrayList<SobaTip> vratiSve() {
        throw new UnsupportedOperationException("Ni u jednom slucaju nece biti potrebno"); 
    }

    @Override
    public SobaTip pronadjiPoId(int Id) {
        try {
            conn = new DBConnection().connect();
            String kveri = "select * from " + this.tableName + " where id = ?";
            PreparedStatement ps = conn.prepareStatement(kveri);

            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();

            
            if (rs.next()) {
                SobaTip s = new SobaTip();
                s.setId(rs.getInt("id"));
                s.setBrojSlobodnih(rs.getInt("broj_slobodnih"));
                s.setOpis(rs.getString("opis"));
                s.setBrojSoba(rs.getInt("broj_soba"));
                s.setHotel_Id(rs.getInt("hotel_id"));
                s.setSlika(rs.getString("slika"));
                s.setKreveti(rs.getInt("kreveti"));
                s.setCena(rs.getFloat("cena"));
                ps.close();
            conn.close();
                return s;
            }
           

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri pronalazenju sobe po Id: " + ex.getMessage());
        }

        return null;
    }

    @Override
    public boolean izmeni(SobaTip zaIzmenu) {
        try {
            conn = new DBConnection().connect();
            PreparedStatement ps = conn.prepareStatement(this.updateQuery);
            ps.setInt(1, zaIzmenu.getHotel_Id());
            ps.setInt(2, zaIzmenu.getKreveti());
            ps.setString(3, zaIzmenu.getSlika());
            ps.setInt(4, zaIzmenu.getBrojSoba());
            ps.setInt(5, zaIzmenu.getBrojSlobodnih());
            ps.setString(6, zaIzmenu.getOpis());
            ps.setFloat(7, zaIzmenu.getCena());
            ps.setInt(8, zaIzmenu.getId());
            
            int i = ps.executeUpdate();
            ps.close();
            conn.close();
            if (i != 0) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri izmeni sobe: " + ex.getMessage());
        }

        return false;
    }

    @Override
    public void obrisi(int Id) {
         try {
            conn = new DBConnection().connect();
            PreparedStatement ps = conn.prepareStatement(this.deleteQuery);
            ps.setInt(1, Id);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Doslo je do greske pri brisanju sobe: " + ex.getMessage());
        }
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
            ps.setFloat(7, zaDodavanje.getCena());
            int i = ps.executeUpdate();
            ps.close();
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

            ArrayList<SobaTip> nadjeneSobe = new ArrayList<SobaTip>();
            while (rs.next()) {
                SobaTip s = new SobaTip();
                s.setId(rs.getInt("id"));
                s.setBrojSlobodnih(rs.getInt("broj_slobodnih"));
                s.setOpis(rs.getString("opis"));
                s.setBrojSoba(rs.getInt("broj_soba"));
                s.setHotel_Id(rs.getInt("hotel_id"));
                s.setSlika(rs.getString("slika"));
                s.setKreveti(rs.getInt("kreveti"));
                s.setCena(rs.getFloat("cena"));
                nadjeneSobe.add(s);
            }
            ps.close();
            conn.close();
            return nadjeneSobe;

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
                h.setCena(rs.getFloat("cena"));
                sobe.add(h);
            }
            ps.close();
            conn.close();
            return sobe;

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri pronalazenju soba hotela: " + ex.getMessage());
        }

        return null;
    }
    
}
