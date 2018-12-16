/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Models.User;
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
@ManagedBean(name="registerUser")
public class UserController {

    /**
     * @return the user
     */
    public User getUser() {
        if(this.user == null)
        {
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
    public String register()
    {
        int sameMails = this.user.pronadjiPoPolju("email", user.getEmail()).size();
        int sameUsernames = this.user.pronadjiPoPolju("username", user.getUsername()).size();
        if(sameUsernames != 0)
        {
            FacesContext.getCurrentInstance().addMessage("registerForm:name", new FacesMessage("Korisnik sa istim korisnickim imenom vec postoji!"));
        }
         if(sameMails != 0)
        {
            FacesContext.getCurrentInstance().addMessage("registerForm:email", new FacesMessage("Korisnik sa istom mail adresom vec postoji!"));
        }
        boolean dodato = this.user.dodaj(this.user);
        if(dodato)
        {
            return("/faces/views/index/index.xhtml");
        }
        else
        {
            return("");
        }
    }
    
}
