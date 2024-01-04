/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;


/**
 *
 * @author PC
 */
interface DatabaseInfo {
    public static String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
     public static String url="jdbc:sqlserver://localhost:1433;databaseName=VKUStudentDB;trustServerCertificate=true";
     public static String user="sa";
     public static String pass="sa";
}
