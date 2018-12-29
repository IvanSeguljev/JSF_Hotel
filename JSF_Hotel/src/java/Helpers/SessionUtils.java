/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author werfawf
 */
public class SessionUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("username");
        } else {
            return null;
        }
    }
    
    public static int getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (int) session.getAttribute("uid");
        } else {
            return 0;
        }
    }

    public static String getUserRole() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("role");
        } else {
            return null;
        }
    }
}
