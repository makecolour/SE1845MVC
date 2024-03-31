/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quyen
 */
public class DBConnect {
    Connection con = null;
    public DBConnect(String url, String userName, String password) {
     
        try {
            //Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try {
                //connection
                con = DriverManager.getConnection(url, userName, password);
                System.out.println("connected");
            } catch (SQLException ex) {
                Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } 
       
    }
    
    public DBConnect(){
        this("jdbc:sqlserver://localhost:1433;databaseName=SE1845", "sa", "123456");
    }
    
    public static void main(String[] args) {
        
    }
}
