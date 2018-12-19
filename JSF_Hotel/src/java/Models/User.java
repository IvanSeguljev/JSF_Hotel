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
public class User {

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
    /**
     * @return the id
     */

    /*Polja*/
    private int id;
    private String username;
    private String password;
    private String email;
    private String uloga;
    private int poeni;

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
}
