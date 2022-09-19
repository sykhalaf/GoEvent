/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Ce Pc
 */
public class Bd {
    final String URL = "jdbc:mysql://localhost:3306/goevent";
    final String USR  = "root";
    final String PWD = "";
    
    //Var
    Connection cnx;
    
    //1 : instance
    private static Bd instance = null;
    
    //const
    //2: private constructor
    public Bd(){
    
        try {
            cnx = DriverManager.getConnection(URL, USR, PWD);
            System.out.println("Connexion établie avec succés!");
             showMessageDialog(null, "Connexion établie avec succés!");
        } catch (SQLException ex) {
          showMessageDialog(null, " erreur de Connexion !");
          
        }
        
       
        
        
    }
    
 //3 : get instance
    public static Bd getInstance() {
        if(instance == null)
            instance = new Bd();
        
        return instance;
    }

    public Connection getCnx() {
        
        return cnx;
    }
    
}
