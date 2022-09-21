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
 * @author Elife-Kef-139
 */
public class Connexionbd {
    
    //DB
    final String URL = "jdbc:mysql://localhost:3306/goevent";
    final String USR  = "root";
    final String PWD = "";
    
    //Var
    Connection cnx;
    
    //1 : instance
    private static Connexionbd instance = null;
    
    //const
    //2: private constructor
    public Connexionbd(){
    
        try {
            cnx = DriverManager.getConnection(URL, USR, PWD);
            System.out.println("Connexion établie avec succés!");
             showMessageDialog(null, "Connexion établie avec succés!");
        } catch (SQLException ex) {
          showMessageDialog(null, " erreur de Connexion !");
          
        }
        
       
        
        
    }
    
 //3 : get instance
    public static Connexionbd getInstance() {
        if(instance == null)
            instance = new Connexionbd();
        
        return instance;
    }

    public Connection getCnx() {
        
        return cnx;
    }
    
    
    
    
}
