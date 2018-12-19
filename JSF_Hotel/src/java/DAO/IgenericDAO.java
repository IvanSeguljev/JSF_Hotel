/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;

/**
 *
 * @author werfawf
 * @param <T>
 */
public interface IgenericDAO<T> {

    public ArrayList<T> vratiSve();

    public T pronadjiPoId(int Id);

    public boolean izmeni(T zaIzmenu);

    public void obrisi(int Id);

    public boolean dodaj(T zaDodavanje);
    
    public ArrayList<T> pronadjiPoPolju(String nazivPolja, String vrednostPolja);

}
