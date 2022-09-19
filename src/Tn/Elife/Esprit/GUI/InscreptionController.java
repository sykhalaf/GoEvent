/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tn.Elife.Esprit.GUI;

import Tn.Elife.Esprit.Base.MaConnexion;
import Tn.Elife.Esprit.Entities.Utilisateurs;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author siccaPrint
 */
public class InscreptionController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField tfpwd;
    @FXML
    private TextField tftel;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnannuler;
    @FXML
    private CheckBox checclient;
    @FXML
    private CheckBox checentrepreneur;
    @FXML
    private Button btnannuler1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
Connection cnx = MaConnexion.getInstance().getCnx();
    @FXML
    private void add(ActionEvent event) throws IOException {
        
        
        if (isValidated()) {
             if (checclient.isSelected()) {
                   

            Utilisateurs e = new Utilisateurs();
            e.setNom(tfnom.getText());
            e.setPrenom(tfprenom.getText());
            e.setEmail(tfemail.getText());
            e.setPwd(tfpwd.getText());
            //e.setNum_tel(tftel.getText());
            tftel.setText(String.valueOf(e.getNum_tel()));
        
         String requete = "INSERT INTO `user`(`nom`,`prenom`,`email`,`pwd`,`num_tel`,`role`) VALUES ( ?,?,?,?,?,'client')";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getPwd());
            ps.setString(5, String.valueOf(e.getNum_tel()));
           
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
            
            
               
            
            
            
            FXMLLoader Loader;
                 Loader = new FXMLLoader(getClass() 
                         .getResource("../GUI/Bienvenue_Client.fxml"));
            Parent root = Loader.load ();
            
            Bienvenue_ClientController Bienvenue_ClientController = Loader.getController();
            
            Bienvenue_ClientController.showInformation(tfnom.getText(),tfprenom.getText());
            
            
            Scene scene = new Scene(root);
          
          
        Stage stage = new Stage();
        stage.setScene(scene);
       
        stage.setTitle("Bienvenue Client");
        stage.show();
            
                tfnom.clear();
                tfprenom.clear();
                tfemail.clear();
                tfpwd.clear();
                tftel.clear();
            
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
        
         } else if (checentrepreneur.isSelected()){
                        
              Utilisateurs e = new Utilisateurs();
            e.setNom(tfnom.getText());
            e.setPrenom(tfprenom.getText());
            e.setEmail(tfemail.getText());
            e.setPwd(tfpwd.getText());
            //e.setNum_tel(tftel.getText());
            tftel.setText(String.valueOf(e.getNum_tel()));
            
            String requete = "INSERT INTO `user`(`nom`,`prenom`,`email`,`pwd`,`num_tel`,`role`) VALUES ( ?,?,?,?,?,'entrepreneur')";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getPwd());
            ps.setString(5, String.valueOf(e.getNum_tel()));
           
            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
            
            
               
            
            
            
            
            
            
            
            
            FXMLLoader Loader;
                 Loader = new FXMLLoader(getClass() 
                         .getResource("../GUI/Bienvenue_entrepreneur.fxml"));
            Parent root = Loader.load ();
            
            Bienvenue_entrepreneurController Bienvenue = Loader.getController();
            
            Bienvenue.showInformation(tfnom.getText(),tfprenom.getText());
                    
            
            
            Scene scene = new Scene(root);
          
          
        Stage stage = new Stage();
        stage.setScene(scene);
       
        stage.setTitle("Bienvenue_entrepreneur");
        stage.show();
            
            
               tfnom.clear();
                tfprenom.clear();
                tfemail.clear();
                tfpwd.clear();
                tftel.clear();
            
            
            
            
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
        
         
            
            
            
            
            
            
            
            
             
             
             }
                            
            

            
        }
        

        
        
    }

    @FXML
    private void annuler(ActionEvent event) {
        
                tfnom.clear();
                tfprenom.clear();
                tfemail.clear();
                tfpwd.clear();
                tftel.clear();
                
        
        
        
        
    }
    
    
  private boolean isValidated() {
//       
       
        if (tfnom.getText().equals("")) {

            showMessageDialog(null, "Le champ Nom ne peut pas être vide !!!");
            tfnom.requestFocus();
        } else if (tfprenom.getText().equals("")) {
            showMessageDialog(null, "Le champ Prénom ne peut pas être vide !!!");
            tfprenom.requestFocus();

        } else if (tfemail.getText().equals("")) {
            showMessageDialog(null, "Le champ E-mail ne peut pas être vide !!!");
            tfemail.requestFocus();
          } else if (tfpwd.getText().equals("")) {
            showMessageDialog(null, "Le champ Password ne peut pas être vide !!!");
            tfpwd.requestFocus();
           } else if (tftel.getText().equals("")) {
            showMessageDialog(null, "Le champ Téléphone ne peut pas être vide !!!");
            tftel.requestFocus();  
            
            

        } else {
            return true;
        }
        return false;
    }
  

  

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass()
                    .getResource("../GUI/GoEvent.fxml"));
 
          Scene scene = new Scene(root);
          
          
        Stage stage = new Stage();
        stage.setScene(scene);
       
        stage.setTitle("GoEvent");
        stage.show();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}



