/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Base.Connexion;
import Interface.InterQuiz;
import Model.Quiz;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Ce Pc
 */
public class ServiceQuiz implements InterQuiz {

    Connection cnx = Connexion.getInstance().getCnx();

    @Override
    public void AjouterEvent(Quiz e) {

        String requete = "INSERT INTO `quiz`(`titre_quiz`,`option1`,`option2`,`option3`,`bonne_reponse`) VALUES ( ?,?,?,?,?)";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);

            ps.setString(1, e.getTitre_quiz());
            ps.setString(2, e.getOption1());
            ps.setString(3, e.getOption2());
            ps.setString(4, e.getOption3());
            ps.setString(5, e.getBonne_reponse());

            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    private static InterQuiz interQuiz;

    public static InterQuiz getInstance() {
        if (interQuiz == null) {
            interQuiz = new ServiceQuiz();
        }
        return interQuiz;
    }

}
