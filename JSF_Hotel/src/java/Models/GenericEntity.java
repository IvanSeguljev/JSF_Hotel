/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DB_Connection.DBConnection;
import DB_Connection.InTable;
import java.sql.Connection;

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
