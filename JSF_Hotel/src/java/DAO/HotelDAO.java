/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connection.DBConnection;
import Helpers.PasswordHelper;
import Models.Hotel;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author werfawf
 */
public class HotelDAO extends GenericEntity implements IgenericDAO<Hotel> {
    
    public HotelDAO()
    {
        this.tableName = "Hotel";
        this.getAllQuery = "SELECT * FROM `" + tableName + "`";
        this.insertQuery = "INSERT INTO " + tableName + " (`naziv`, `slika`, `opis`, `menadzer_id`, `adresa`, `telefon`) VALUES (?, ?, ?, ?, ?, ?)";
        this.updateQuery = "UPDATE " + tableName + " SET `naziv`= ?,`slika`= ?,`opis`= ?,`menadzer_id`=?,`adresa`=?,`telefon`=? WHERE id = ?";
        this.deleteQuery = "DELETE FROM " + tableName + " WHERE id = ?";
    }

    @Override
    public ArrayList<Hotel> vratiSve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hotel pronadjiPoId(int Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean izmeni(Hotel zaIzmenu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void obrisi(int Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean dodaj(Hotel zaDodavanje) {
         try {
            conn = new DBConnection().connect();
            PreparedStatement ps = conn.prepareStatement(this.insertQuery);
            ps.setString(1, zaDodavanje.getNaziv());
            ps.setBlob(2, zaDodavanje.getSlika().getInputStream());
            ps.setString(3, zaDodavanje.getOpis());
            ps.setInt(4, zaDodavanje.getMenadzer_id());
            ps.setString(5, zaDodavanje.getAdresa());
            ps.setString(6, zaDodavanje.getTelefon());
            
            int i = ps.executeUpdate();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
