/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connection.DBConnection;
import Helpers.PasswordHelper;
import Models.Hotel;
import Models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.Part;

/**
 *
 * @author werfawf
 */
public class HotelDAO extends GenericEntity implements IgenericDAO<Hotel> {

    public HotelDAO() {
        this.tableName = "Hotel";
        this.getAllQuery = "SELECT * FROM `" + tableName + "`";
        this.insertQuery = "INSERT INTO " + tableName + " (`naziv`, `slika`, `opis`, `menadzer_id`, `adresa`, `telefon`) VALUES (?, ?, ?, ?, ?, ?)";
        this.updateQuery = "UPDATE " + tableName + " SET `naziv`= ?,`slika`= ?,`opis`= ?,`menadzer_id`=?,`adresa`=?,`telefon`=? WHERE id = ?";
        this.deleteQuery = "DELETE FROM " + tableName + " WHERE id = ?";
    }

    @Override
    public ArrayList<Hotel> vratiSve() {
        try {
            conn = new DBConnection().connect();
            PreparedStatement ps = conn.prepareStatement(this.getAllQuery);

            ResultSet rs = ps.executeQuery();

            ArrayList<Hotel> foundHotels = new ArrayList<Hotel>();
            while (rs.next()) {
                Hotel h = new Hotel();
                h.setId(rs.getInt("id"));
                h.setNaziv(rs.getString("naziv"));
                h.setOpis(rs.getString("opis"));
                h.setMenadzer_id(rs.getInt("menadzer_id"));
                h.setAdresa(rs.getString("adresa"));
                h.setTelefon(rs.getString("telefon"));
                foundHotels.add(h);
            }
            ps.close();
            conn.close();
            return foundHotels;
        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri vracanju svih hotela: " + ex.getMessage());
        }

        return null;
    }

    @Override
    public Hotel pronadjiPoId(int Id) {
        try {
            conn = new DBConnection().connect();
            String kveri = "select * from " + this.tableName + " where id = ?";
            PreparedStatement ps = conn.prepareStatement(kveri);

            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Hotel h = new Hotel();
                h.setId(rs.getInt("id"));
                h.setNaziv(rs.getString("naziv"));
                h.setOpis(rs.getString("opis"));
                h.setMenadzer_id(rs.getInt("menadzer_id"));
                h.setAdresa(rs.getString("adresa"));
                h.setTelefon(rs.getString("telefon"));
                h.setSlika(rs.getString("slika"));
                ps.close();
                conn.close();
                return h;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri pronalazenju hotela po Id: " + ex.getMessage());
        }

        return null;
    }

    @Override
    public boolean izmeni(Hotel zaIzmenu) {
        try {
            conn = new DBConnection().connect();
            PreparedStatement ps = conn.prepareStatement(this.updateQuery);
            ps.setString(1, zaIzmenu.getNaziv());
            ps.setString(2, zaIzmenu.getSlika());
            ps.setString(3, zaIzmenu.getOpis());
            ps.setInt(4, zaIzmenu.getMenadzer_id());
            ps.setString(5, zaIzmenu.getAdresa());
            ps.setString(6, zaIzmenu.getTelefon());
            ps.setInt(7, zaIzmenu.getId());

            int i = ps.executeUpdate();
            ps.close();
            conn.close();
            if (i != 0) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri dodavanju Hotela: " + ex.getMessage());
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
            System.out.println("Doslo je do greske pri brisanju hotela: " + ex.getMessage());
        }
    }

    @Override
    public boolean dodaj(Hotel zaDodavanje) {
        try {
            conn = new DBConnection().connect();
            PreparedStatement ps = conn.prepareStatement(this.insertQuery);
            ps.setString(1, zaDodavanje.getNaziv());
            ps.setString(2, zaDodavanje.getSlika());
            ps.setString(3, zaDodavanje.getOpis());
            ps.setInt(4, zaDodavanje.getMenadzer_id());
            ps.setString(5, zaDodavanje.getAdresa());
            ps.setString(6, zaDodavanje.getTelefon());

            int i = ps.executeUpdate();
            ps.close();
            conn.close();
            if (i != 0) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri dodavanju Hotela: " + ex.getMessage());
        }

        return false;
    }

    @Override
    public ArrayList<Hotel> pronadjiPoPolju(String nazivPolja, String vrednostPolja) {
        try {
            conn = new DBConnection().connect();
            String kveri = this.getAllQuery + " WHERE " + nazivPolja + " = ?";
            PreparedStatement ps = conn.prepareStatement(kveri);

            ps.setString(1, vrednostPolja);
            ResultSet rs = ps.executeQuery();

            ArrayList<Hotel> foundHotels = new ArrayList<Hotel>();
            while (rs.next()) {
                Hotel h = new Hotel();
                h.setId(rs.getInt("id"));
                h.setNaziv(rs.getString("naziv"));
                h.setOpis(rs.getString("opis"));
                h.setMenadzer_id(rs.getInt("menadzer_id"));
                h.setAdresa(rs.getString("adresa"));
                h.setTelefon(rs.getString("telefon"));
                h.setSlika(rs.getString("slika"));
                foundHotels.add(h);
            }
            ps.close();
            conn.close();
            return foundHotels;

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri pronalazenju hotela: " + ex.getMessage());
        }

        return null;
    }

    public Hotel pronadjiPoNazivu(String naziv) {
        try {
            conn = new DBConnection().connect();
            String kveri = "select * from " + this.tableName + " where naziv = ?";
            PreparedStatement ps = conn.prepareStatement(kveri);

            ps.setString(1, naziv);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Hotel h = new Hotel();
                h.setId(rs.getInt("id"));
                h.setNaziv(rs.getString("naziv"));
                h.setOpis(rs.getString("opis"));
                h.setMenadzer_id(rs.getInt("menadzer_id"));
                h.setAdresa(rs.getString("adresa"));
                h.setTelefon(rs.getString("telefon"));
                h.setSlika(rs.getString("slika"));

                ps.close();
                conn.close();
                return h;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri pronalazenju hotela po nazivu: " + ex.getMessage());
        }

        return null;
    }

    public String nazivHotelaZaSesiju(int menadzerId) {
        try {
            conn = new DBConnection().connect();
            String kveri = "select * from " + this.tableName + " where menadzer_id = ?";
            PreparedStatement ps = conn.prepareStatement(kveri);

            ps.setInt(1, menadzerId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String naziv = rs.getString("naziv");
                ps.close();
                conn.close();
                return naziv;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri pronalazenju hotela po nazivu: " + ex.getMessage());
        }

        return null;
    }

}
