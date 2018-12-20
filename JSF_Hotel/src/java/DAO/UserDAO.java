/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connection.DBConnection;
import Helpers.PasswordHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.User;

/**
 *
 * @author werfawf
 */
public class UserDAO extends GenericEntity implements IgenericDAO<User> {

    /*Kveriji*/
    public UserDAO() {
        this.tableName = "User";
        this.getAllQuery = "SELECT * FROM `" + tableName + "`";
        this.insertQuery = "INSERT INTO " + tableName + " ( `username`, `password`, `email`, `role`) VALUES (? ,? ,? ,?)";
        this.updateQuery = "UPDATE " + tableName + " SET `username`= ?,`email`= ?,`role`= ?,`poeni`=? WHERE id = ?";
        this.deleteQuery = "DELETE FROM " + tableName + " WHERE id = ?";
    }

    @Override
    public ArrayList<User> vratiSve() {
        try {
            conn = new DBConnection().connect();
            PreparedStatement ps = conn.prepareStatement(this.getAllQuery);

            ResultSet rs = ps.executeQuery();

            ArrayList<User> foundUsers = new ArrayList<User>();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setUloga(rs.getString("role"));
                foundUsers.add(u);
            }
            conn.close();
            return foundUsers;

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri vracanju svih korisnika: " + ex.getMessage());
        }

        return null;
    }

    public ArrayList<User> pronadjiPoPolju(String nazivPolja, String vrednostPolja) {
        try {
            conn = new DBConnection().connect();
            String kveri = this.getAllQuery + " WHERE " + nazivPolja + " = ?";
            PreparedStatement ps = conn.prepareStatement(kveri);

            ps.setString(1, vrednostPolja);
            ResultSet rs = ps.executeQuery();

            ArrayList<User> foundUsers = new ArrayList<User>();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setUloga(rs.getString("role"));
                u.setPoeni(rs.getInt("poeni"));
                foundUsers.add(u);
            }
            conn.close();
            return foundUsers;

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri pronalazenju korisnika: " + ex.getMessage());
        }

        return null;

    }

    @Override
    public boolean izmeni(User zaIzmenu) {
        try {
            conn = new DBConnection().connect();
            zaIzmenu.setPassword(PasswordHelper.getSaltedHash(zaIzmenu.getPassword()));
            PreparedStatement ps = conn.prepareStatement(this.updateQuery);
            
            ps.setString(1, zaIzmenu.getUsername());            
            ps.setString(2, zaIzmenu.getEmail());
            ps.setString(3, zaIzmenu.getUloga());
            ps.setInt(4, zaIzmenu.getPoeni());
            ps.setInt(5, zaIzmenu.getId());

            int i = ps.executeUpdate();
            conn.close();
            if (i != 0) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri izmeni korisnika: " + ex.getMessage());
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
        } catch (SQLException ex) {
            System.out.println("Doslo je do greske pri brisanju korisnika: " + ex.getMessage());
        }
    }

    /**
     *
     * @param zaDodavanje
     * @return
     */
    @Override
    public boolean dodaj(User zaDodavanje) {
        try {
            conn = new DBConnection().connect();
            zaDodavanje.setPassword(PasswordHelper.getSaltedHash(zaDodavanje.getPassword()));
            PreparedStatement ps = conn.prepareStatement(this.insertQuery);
            zaDodavanje.setUloga("Klijent");
            ps.setString(1, zaDodavanje.getUsername());
            ps.setString(2, zaDodavanje.getPassword());
            ps.setString(3, zaDodavanje.getEmail());
            ps.setString(4, zaDodavanje.getUloga());

            int i = ps.executeUpdate();
            conn.close();
            if (i != 0) {
                return true;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri dodavanju korisnika: " + ex.getMessage());
        }

        return false;
    }

    @Override
    public User pronadjiPoId(int Id) {
        try {
            conn = new DBConnection().connect();
            String kveri = this.getAllQuery + " WHERE id = " + Id;
            PreparedStatement ps = conn.prepareStatement(kveri);

            ResultSet rs = ps.executeQuery();

            ArrayList<User> foundUsers = new ArrayList<User>();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setEmail(rs.getString("email"));
                u.setUloga(rs.getString("role"));
                u.setPoeni(rs.getInt("poeni"));
                conn.close();
                return u;
            }

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri trazenju korisnika: " + ex.getMessage());
        }

        return null;

    }

    public ArrayList<String> validiraj(String username, String password) {
        try {
            conn = new DBConnection().connect();
            String kveri = this.getAllQuery + " WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(kveri);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                boolean tacanPass = PasswordHelper.check(password, rs.getString("password"));

                if (tacanPass) {
                    ArrayList<String> userData = new ArrayList<String>();
                    userData.add(rs.getString("username"));
                    userData.add(rs.getString("role"));

                    conn.close();
                    return userData;
                }

            }
        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri validaciji korisnika: " + ex.getMessage());
            return null;
        }
        return null;
    }
    
    public ArrayList<User> vratiSlobodneMenadzere() {
        try {
            conn = new DBConnection().connect();
            String kveri = "SELECT u.username,u.id from User as u where u.id not in (SELECT h.menadzer_id from Hotel as h) and u.role = 'menadzer'";
            PreparedStatement ps = conn.prepareStatement(kveri);

            ResultSet rs = ps.executeQuery();

            ArrayList<User> foundUsers = new ArrayList<User>();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                
                foundUsers.add(u);
            }
            conn.close();
            return foundUsers;

        } catch (Exception ex) {
            System.out.println("Doslo je do greske pri pronalazenju slobodnih menadzera: " + ex.getMessage());
        }

        return null;

    }

}
