/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Connection;

import java.util.ArrayList;

/**
 *
 * @author werfawf
 */
public interface InTable<T> {
   public ArrayList<T> vratiSve();
   
   public T pronadjiPoId(int Id);
   public void izmeni(T zaIzmenu, int Id);
   public void obrisi(int Id);
   public boolean dodaj(T zaDodavanje);
   
   
}
