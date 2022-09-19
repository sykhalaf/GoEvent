/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tn.Elife.Esprit.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author siccaPrint
 */
public class Bienvenue_AdminController implements Initializable {

    @FXML
    private Button btncompte;
    @FXML
    private Button btnuser;
    @FXML
    private Button btnservices;
    @FXML
    private Button btnevenement;
    @FXML
    private Button btnquiz;
    @FXML
    private Button btnavis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void compte(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass()
                    .getResource("../GUI/MaCompte_Admin.fxml"));
 
          Scene scene = new Scene(root);
          
          
        Stage stage = new Stage();
        stage.setScene(scene);
       
        stage.setTitle("Ma Compte");
        stage.show();
        
        
        
        
        
    }

    @FXML
    private void lienuser(ActionEvent event) {
    }

    @FXML
    private void lienservices(ActionEvent event) {
    }

    @FXML
    private void lienevenement(ActionEvent event) {
    }

    @FXML
    private void lienquiz(ActionEvent event) {
    }

    @FXML
    private void lienavis(ActionEvent event) {
    }

    @FXML
    private void logout(MouseEvent event) {
    }
    
}
