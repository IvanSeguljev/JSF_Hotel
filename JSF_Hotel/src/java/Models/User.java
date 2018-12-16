/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DB_Connection.DBConnection;
import DB_Connection.InTable;
import Helpers.PasswordHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author werfawf
 */

public class User extends GenericEntity implements InTable<User> {

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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the uloga
     */
    public String getUloga() {
        return uloga;
    }

    /**
     * @param uloga the uloga to set
     */
    public void setUloga(String uloga) {
        this.uloga = uloga;
    }
/*Polja*/
    private int id;
    private String username;
    private String password;
    private String email;
    private String uloga;
    /*Kveriji*/
    
    
    public User()
    {
        this.tableName = "User";
        this.getAllQuery = "SELECT * FROM `"+tableName+"`";
        
        this.insertQuery = "INSERT INTO "+tableName+" ( `username`, `password`, `email`, `role`) VALUES (? ,? ,? ,?)";
        this.updateQuery = "UPDATE "+tableName+" SET `username`= '?',`password`= '?',`email`= '?',`role`= '?' WHERE id = '?'";
        this.deleteQuery = "DELETE FROM "+tableName+" WHERE id = '?'";
    }
   
    @Override
    public ArrayList<User> vratiSve() {
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public ArrayList<User> pronadjiPoPolju(String nazivPolja,String vrednostPolja) {
        try {
            conn = new DBConnection().connect();
            String kveri = this.getAllQuery + " WHERE " + nazivPolja + " = ?";
            PreparedStatement ps = conn.prepareStatement(kveri);
           
            ps.setString(1, vrednostPolja);
            ResultSet rs = ps.executeQuery();
            
            ArrayList<User> foundUsers = new ArrayList<User>();
            while(rs.next())
            {
                User u = new User();
                u.id = rs.getInt("id");
                u.username = rs.getString("username");
                u.password = rs.getString("password");
                u.email = rs.getString("email");
                u.uloga = rs.getString("role");
                foundUsers.add(u);
            }
            conn.close();
            return foundUsers;
            
            
          
        } catch (Exception ex) {
            
        }
       
        return null;
       
    }

    @Override
    public void izmeni(User zaIzmenu, int Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void obrisi(int Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            zaDodavanje.password = PasswordHelper.getSaltedHash(password);
            PreparedStatement ps = conn.prepareStatement(this.insertQuery);
            zaDodavanje.uloga="Klijent";
            ps.setString(1, zaDodavanje.username);
            ps.setString(2, zaDodavanje.password);
            ps.setString(3, zaDodavanje.email);
            ps.setString(4, zaDodavanje.uloga);
            
            int i = ps.executeUpdate();
            conn.close();
            if(i!=0)
            {
                return true;
            }
          
        } catch (Exception ex) {
            
        }
        
        return false;
    }

    @Override
    public User pronadjiPoId(int Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
