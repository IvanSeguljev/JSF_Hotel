/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.UserDAO;
import Helpers.RedirectHelper;
import Helpers.SessionUtils;
import Models.User;
import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author werfawf
 */
@ViewScoped
@ManagedBean(name = "userController")
public class UserController implements Serializable {

    /**
     * @return the user
     */
    public User getUser() {
        if (this.user == null) {
            this.user = new User();
        }
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    public UserController() {

    }

    public String register() {
        UserDAO dataAcces = new UserDAO();
        int sameMails = dataAcces.pronadjiPoPolju("email", user.getEmail()).size();
        int sameUsernames = dataAcces.pronadjiPoPolju("username", user.getUsername()).size();
        if (sameUsernames != 0) {
            FacesContext.getCurrentInstance().addMessage("registerForm:name", new FacesMessage("Korisnik sa istim korisnickim imenom vec postoji!"));
        }
        if (sameMails != 0) {
            FacesContext.getCurrentInstance().addMessage("registerForm:email", new FacesMessage("Korisnik sa istom mail adresom vec postoji!"));
        }
        boolean dodato = dataAcces.dodaj(this.user);
        if (dodato) {
            FacesContext.getCurrentInstance().addMessage("login:name", new FacesMessage("Uspesno ste se registrovali! Mozete se ulogovati"));
            return ("/account/login.xhtml?faces-redirect=true");
        } else {
            return ("");
        }
    }

    public void showDetails(String username) {
        if(SessionUtils.getSession().getAttribute("username")!= null)
        {
        if(!"Administrator".equals(SessionUtils.getUserRole()))
            username = SessionUtils.getUserName();
        else if(username.isEmpty())
        {
            RedirectHelper.returnError(404, "Korisnik nije nadjen");
        }
        UserDAO userData = new UserDAO();

        ArrayList<User> users = userData.pronadjiPoPolju("username", username);
        if (users.size() > 0) {
            this.user = users.get(0);
            
        } else {

            RedirectHelper.returnError(404, "Korisnik nije nadjen");
           
        }
        }
        else
        {
            RedirectHelper.redirect("/account/login.xhtml");
        }

    }

    public String izmeniKorisnika() {
        UserDAO data = new UserDAO();
        int sameMails = data.pronadjiPoPolju("email", user.getEmail()).size();
        int sameUsernames = data.pronadjiPoPolju("username", user.getUsername()).size();
        User stari = data.pronadjiPoId(this.user.getId());
        int greske = 0;
        if (sameUsernames != 0 && !stari.getUsername().equals(this.user.getUsername())) {
            greske++;
            FacesContext.getCurrentInstance().addMessage("changeForm:name", new FacesMessage("Korisnik sa istim korisnickim imenom vec postoji!"));
        }
        if (sameMails != 0 && !stari.getEmail().equals(this.user.getEmail())) {
            greske++;
            FacesContext.getCurrentInstance().addMessage("changeForm:email", new FacesMessage("Korisnik sa istom mail adresom vec postoji!"));
        }
        if (greske == 0) {
            FacesContext.getCurrentInstance().addMessage("login", new FacesMessage("Uspesno ste izmenili podatke"));

            data.izmeni(this.user);
            LoginController ls = new LoginController();

            ls.logout();
            return "/account/login.xhtml";
        } else {
            return "";
        }

    }

    public void promote(int Id) {
        UserDAO data = new UserDAO();
        User u = data.pronadjiPoId(Id);
        if (u.getUloga().equals("Klijent")) {
            u.setUloga("Menadzer");
        } else {
            u.setUloga("Administrator");
        }
        data.izmeni(u);

    }

    public void demote(int Id) {
        UserDAO data = new UserDAO();
        User u = data.pronadjiPoId(Id);
        if (u.getUloga().equals("Administrator")) {
            u.setUloga("Menadzer");
        } else {
            u.setUloga("Klijent");
        }

        data.izmeni(u);
    }

    public ArrayList<User> vratiSveKorisnike() {
        UserDAO u = new UserDAO();
        return u.vratiSve();
    }

    public String obrisi(int Id) {
        UserDAO dao = new UserDAO();
        dao.obrisi(Id);
        return "";
    }

}
