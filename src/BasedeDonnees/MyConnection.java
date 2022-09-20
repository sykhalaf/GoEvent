/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasedeDonnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author User
 */
public class MyConnection {
     final String URL = "jdbc:mysql://localhost:3306/goevent";
    final String USR  = "root";
    final String PWD = "";
     Connection cnx;
     private static MyConnection instance =null;
      public MyConnection(){
    
        try {
            cnx = DriverManager.getConnection(URL, USR, PWD);
            System.out.println("Connexion établie avec succés!");
             showMessageDialog(null, "Connexion établie avec succés!");
        } catch (SQLException ex) {
          showMessageDialog(null, " erreur de Connexion !");
          
        }
        
       
        
        
    }
      public static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();
        
        return instance;
    }
       public Connection getCnx() {
        
        return cnx;
    }
    
}
