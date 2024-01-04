/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author PC
 */
public class ConnectDB implements DatabaseInfo{
     private static ConnectDB instance;
    public ConnectDB() {
     }
 
    public Connection openConnection() throws Exception {
    Class.forName(driverName);
    Connection con = DriverManager.getConnection(url, user, pass);
    return con;

    }

    //Get instance of dbms only one time
    public static ConnectDB getInstance() {
    if (instance == null) {
    instance = new ConnectDB();
    }
    return instance;
    }

}
