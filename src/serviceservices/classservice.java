/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceservices;

import Base.Connexionbd;
import Model.servicesModel;
import interfaceservices.InterServices;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Marwen
 */
public class classservice implements InterServices {

    Connection cnx = Connexionbd.getInstance().getCnx();

    @Override
    public void AjouterServices(servicesModel e) {

        String requete = "INSERT INTO `services`(`type`,`description`) VALUES ( ?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, e.getType());

            ps.setString(2, e.getDescription());

            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }
    private static InterServices InterServices;

    public static InterServices getInstance() {
        if (InterServices == null) {
            InterServices = new classservice();
        }
        return InterServices;
    }
}
