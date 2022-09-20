/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serv;

import BasedeDonnees.MyConnection;
import Interface.InterEvent;
import Model.Event;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class ServEvent  implements InterEvent{
 Connection cnx = MyConnection.getInstance().getCnx();
 
 
 @Override
    public void AjouterEvent(Event e) {
        
String requete = "INSERT INTO `evenement`(`nom_event`,`lieux_event`,`description`,`prix`,`stock_tiket`,`date`,`type_event`,`id_categorie`) VALUES (?,?, ?,?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            
          
            ps.setString(1, e.getNom());
            ps.setString(2, e.getLieu());
            ps.setString(3, e.getDescription());
            ps.setFloat(4, e.getPrix());
            ps.setLong(5, e.getStockticket());
            ps.setDate(6, Date.valueOf(e.getDate()));
            ps.setString(7, e.getType());
            ps.setString(8, e.getId_categorie());
            
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }

    }
    
    private static InterEvent InterEvent;
    
    public static InterEvent getInstance() {
        if (InterEvent == null) {
            InterEvent = new ServEvent();
        }
        return InterEvent;
    }
    
    
}
