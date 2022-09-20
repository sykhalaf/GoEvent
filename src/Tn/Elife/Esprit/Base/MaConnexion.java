/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tn.Elife.Esprit.Base;

/**
 *
 * @author siccaPrint
 */
import java.sql.*;

public class MaConnexion {
    public final String url ="jdbc:mysql://localhost:3306/goevent";
    public final String user ="root";
    public final String pwd ="";
    private Connection cnx;
    public static MaConnexion ct;

    private MaConnexion() {
        try {
            cnx = DriverManager.getConnection(url, user, pwd);
            System.out.println("connexion etablie !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public static MaConnexion getInstance(){
        if(ct==null)
            ct = new MaConnexion();
        return ct;
        
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    
}

