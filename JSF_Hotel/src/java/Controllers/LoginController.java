/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.HotelDAO;
import Helpers.SessionUtils;
import DAO.UserDAO;
import Helpers.RedirectHelper;
import Models.User;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author werfawf
 */
@Named(value = "loginController")
@ViewScoped

public class LoginController implements Serializable {

    /**
     * @return the loggedUsername
     */
    

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

    private String username;
    private String password;

    

    public LoginController() {
    }

    public String login() {
        UserDAO u = new UserDAO();
        User user = u.validiraj(username, password);
        if (user != null) {
            HttpSession sesija = SessionUtils.getSession();
            sesija.setAttribute("username", user.getUsername());
            sesija.setAttribute("role", user.getUloga());
            sesija.setAttribute("uid",user.getId());
            if("Menadzer".equals(user.getUloga()))
            {
                HotelDAO h = new HotelDAO();
                String naziv = h.nazivHotelaZaSesiju(user.getId());
                sesija.setAttribute("menadzer_hotela",(naziv == null)?"nema":naziv);
                
            }

            RedirectHelper.redirect("");

        } else {
            FacesContext.getCurrentInstance().addMessage("login:name", new FacesMessage("Uneli ste pogresne login podatke, molimo vas pokusajte ponovo"));
            return "/account/login.xhtml";
        }
        return "";
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        RedirectHelper.redirect("/account/login.xhtml");
        return "";
    }
    public void redirectLogin()
    {
        RedirectHelper.redirect("/account/login.xhtml");
    }

}
