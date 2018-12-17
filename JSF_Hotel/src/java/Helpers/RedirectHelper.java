/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author werfawf
 */
public class RedirectHelper {
    public static void redirect(String url)
    {
        ExternalContext ec = FacesContext.getCurrentInstance()
        .getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath()
            + url);
        } catch (IOException e) {
            // TODO Auto-generated catch block
         e.printStackTrace();
}
    }
}
