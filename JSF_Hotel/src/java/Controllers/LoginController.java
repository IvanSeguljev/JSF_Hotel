/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Helpers.SessionUtils;
import DAO.UserDAO;
import Helpers.RedirectHelper;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author werfawf
 */
@Named(value = "loginController")
@SessionScoped

public class LoginController implements Serializable {

    /**
     * @return the loggedUsername
     */
    public String getLoggedUsername() {
        return loggedUsername;
    }

    /**
     * @return the loggedRole
     */
    public String getLoggedRole() {
        return loggedRole;
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

    private String username;
    private String password;

    private String loggedUsername;
    private String loggedRole;

    public LoginController() {
    }

    public String login() {
        UserDAO u = new UserDAO();
        ArrayList<String> userData = u.validiraj(username, password);
        if (userData != null) {
            HttpSession sesija = SessionUtils.getSession();
            this.loggedRole = userData.get(1);
            this.loggedUsername = userData.get(0);
            sesija.setAttribute("username", userData.get(0));
            sesija.setAttribute("role", userData.get(1));

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
