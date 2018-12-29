/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connection.DBConnection;
import Models.Hotel;
import Models.Rezervacija;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author werfawf
 */
public class RezervacijaDAO extends GenericEntity implements IgenericDAO<Rezervacija> {

    public RezervacijaDAO() {
        this.tableName = "korisnik_iznajmio";
        this.getAllQuery = "SELECT * FROM `" + tableName + "`";
        this.insertQuery = "INSERT INTO " + tableName + " (`korisnik_id`, `soba_id`, `cena`, `poeni`, `ostaje_dana`, `komentar`, `hotel`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        this.updateQuery = "";
        this.deleteQuery = "DELETE FROM " + tableName + " WHERE id = ?";
    }

    @Override
    public ArrayList<Rezervacija> vratiSve() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rezervacija pronadjiPoId(int Id) {
        try {
            conn = new DBConnection().connect();
            String kveri = this.getAllQuery + " WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(kveri);

            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();

            
            if (rs.next()) {
                Rezervacija r = new Rezervacija();
                r.setId(rs.getInt("id"));
                r.setCena(rs.getFloat("cena"));
                r.setDatum_iznajmljeno(rs.getDate("datum_iznajmljeno"));
                r.setKorisnik_id(rs.getInt("korisnik_id"));
                r.setSoba_id(rs.getInt("soba_id"));
                r.setKomentar(rs.getString("komentar"));
                r.setOstaje_dana(rs.getInt("ostaje_dana"));
                r.setPoeni(rs.getInt("poeni"));
                r.setHotel(rs.getString("hotel"));
                r.setStatus(rs.getString("status"));
                ps.close();
            conn.close();
            return r;
            }
            

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri pronalazenju rezervacije po Id: " + ex.getMessage());
        }

        return null;
    }

    @Override
    public boolean izmeni(Rezervacija zaIzmenu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            System.out.println("Doslo je do greske pri brisanju rezervacije: " + ex.getMessage());
        }
    }

    @Override
    public boolean dodaj(Rezervacija zaDodavanje) {
        try {
            conn = new DBConnection().connect();
            PreparedStatement ps = conn.prepareStatement(this.insertQuery);
            ps.setInt(1, zaDodavanje.getKorisnik_id());
            ps.setInt(2, zaDodavanje.getSoba_id());
            ps.setFloat(3, zaDodavanje.getCena());
            ps.setInt(4, zaDodavanje.getPoeni());
            ps.setInt(5, zaDodavanje.getOstaje_dana());
            ps.setString(6, zaDodavanje.getKomentar());
            ps.setString(7, zaDodavanje.getHotel());
            int i = ps.executeUpdate();
            ps.close();
            conn.close();
            if (i != 0) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri dodavanju rezervacije: " + ex.getMessage());
        }

        return false;
    }

    @Override
    public ArrayList<Rezervacija> pronadjiPoPolju(String nazivPolja, String vrednostPolja) {
        try {
            conn = new DBConnection().connect();
            String kveri = "SELECT * from korisnik_iznajmio o join User u on o.korisnik_id = u.id " + " WHERE " + nazivPolja + " = ?";
            PreparedStatement ps = conn.prepareStatement(kveri);

            ps.setString(1, vrednostPolja);
            ResultSet rs = ps.executeQuery();

            ArrayList<Rezervacija> rezervacije = new ArrayList<Rezervacija>();
            while (rs.next()) {
                Rezervacija r = new Rezervacija();
                r.setId(rs.getInt("id"));
                r.setCena(rs.getFloat("cena"));
                r.setDatum_iznajmljeno(rs.getDate("datum_iznajmljeno"));
                r.setKorisnik_id(rs.getInt("korisnik_id"));
                r.setSoba_id(rs.getInt("soba_id"));
                r.setKomentar(rs.getString("komentar"));
                r.setOstaje_dana(rs.getInt("ostaje_dana"));
                r.setPoeni(rs.getInt("poeni"));
                r.setHotel(rs.getString("hotel"));
                r.setStatus(rs.getString("status"));
                r.setKorisnik(rs.getString("username"));
                rezervacije.add(r);
            }
            ps.close();
            conn.close();
            return rezervacije;

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri pronalazenju rezervacije po polju: " + ex.getMessage());
        }

        return null;
    }
    public ArrayList<Rezervacija> pronadjiPoHotelu( String hotel) {
        try {
            conn = new DBConnection().connect();
            String kveri = "SELECT * from korisnik_iznajmio o join User u on o.korisnik_id = u.id  WHERE hotel = ? and o.status != 'Otisao'";
            PreparedStatement ps = conn.prepareStatement(kveri);

            ps.setString(1, hotel);
            ResultSet rs = ps.executeQuery();

            ArrayList<Rezervacija> rezervacije = new ArrayList<Rezervacija>();
            while (rs.next()) {
                Rezervacija r = new Rezervacija();
                r.setId(rs.getInt("id"));
                r.setCena(rs.getFloat("cena"));
                r.setDatum_iznajmljeno(rs.getDate("datum_iznajmljeno"));
                r.setKorisnik_id(rs.getInt("korisnik_id"));
                r.setSoba_id(rs.getInt("soba_id"));
                r.setKomentar(rs.getString("komentar"));
                r.setOstaje_dana(rs.getInt("ostaje_dana"));
                r.setPoeni(rs.getInt("poeni"));
                r.setHotel(rs.getString("hotel"));
                r.setStatus(rs.getString("status"));
                r.setKorisnik(rs.getString("username"));
                rezervacije.add(r);
            }
            ps.close();
            conn.close();
            return rezervacije;

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri pronalazenju rezervacije po hotelu: " + ex.getMessage());
        }

        return null;
    }

    public boolean uvecajPoene(int uid, int poeni) {
        String kveri = "UPDATE `User` u SET u.poeni = u.poeni + ? WHERE u.id = ?";
        try {
            conn = new DBConnection().connect();
            PreparedStatement ps = conn.prepareStatement(kveri);
            ps.setInt(1, poeni);
            ps.setInt(2, uid);

            int i = ps.executeUpdate();
            ps.close();
            conn.close();
            if (i != 0) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri uvecavanju poena: " + ex.getMessage());
        }

        return false;
    }
    public boolean smanjiBrojSlobodnih(int sobaId) {
        String kveri = "UPDATE `soba_tip` u SET u.broj_slobodnih = u.broj_slobodnih - 1 WHERE u.id = ?";
        try {
            conn = new DBConnection().connect();
            PreparedStatement ps = conn.prepareStatement(kveri);
           
            ps.setInt(1, sobaId);

            int i = ps.executeUpdate();
            ps.close();
            conn.close();
            if (i != 0) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri smanjenju broja slobodnih soba: " + ex.getMessage());
        }

        return false;
    }
    public boolean sniziPoene(int uid, int poeni) {
        String kveri = "UPDATE `User` u SET u.poeni = u.poeni - ? WHERE u.id = ?";
        try {
            conn = new DBConnection().connect();
            PreparedStatement ps = conn.prepareStatement(kveri);
            ps.setInt(1, poeni);
            ps.setInt(2, uid);

            int i = ps.executeUpdate();
            ps.close();
            conn.close();
            if (i != 0) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri uvecavanju poena: " + ex.getMessage());
        }

        return false;
    }
    public boolean uvecajBrojSlobodnih(int sobaId) {
        String kveri = "UPDATE `soba_tip` u SET u.broj_slobodnih = u.broj_slobodnih + 1 WHERE u.id = ?";
        try {
            conn = new DBConnection().connect();
            PreparedStatement ps = conn.prepareStatement(kveri);
           
            ps.setInt(1, sobaId);

            int i = ps.executeUpdate();
            ps.close();
            conn.close();
            if (i != 0) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri smanjenju broja slobodnih soba: " + ex.getMessage());
        }

        return false;
    }
    
    public boolean korisnikStigao(int rezId)
    {
         String kveri = "UPDATE `korisnik_iznajmio` ki SET ki.status =  'Stigao' WHERE ki.id = ?";
         try {
            conn = new DBConnection().connect();
            PreparedStatement ps = conn.prepareStatement(kveri);
           
            ps.setInt(1, rezId);

            int i = ps.executeUpdate();
            ps.close();
            conn.close();
            if (i != 0) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske u rezervacija dao, korisnik stigao: " + ex.getMessage());
        }

        return false;
    }

     public boolean korisnikOtisao(int rezId)
    {
         String kveri = "UPDATE `korisnik_iznajmio` ki SET ki.status =  'Otisao' WHERE ki.id = ?";
         try {
            conn = new DBConnection().connect();
            PreparedStatement ps = conn.prepareStatement(kveri);
           
            ps.setInt(1, rezId);

            int i = ps.executeUpdate();
            ps.close();
            conn.close();
            if (i != 0) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske u rezervacija dao, korisnik otisao: " + ex.getMessage());
        }

        return false;
    }
}
