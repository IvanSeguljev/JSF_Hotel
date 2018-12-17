/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connection.DBConnection;
import java.sql.Connection;
import DB_Connection.IgenericDao;

/**
 *
 * @author werfawf
 */
public abstract class GenericEntity  {
    protected String tableName;
    protected Connection conn;
    protected String getAllQuery;
   
    protected String insertQuery;
    protected String updateQuery;
    protected String deleteQuery;
     
}
