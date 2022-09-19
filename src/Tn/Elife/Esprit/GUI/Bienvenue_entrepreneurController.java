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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author siccaPrint
 */
public class Bienvenue_entrepreneurController implements Initializable {

    private Label labnom;
    private Label lbprenom;
    @FXML
    private Button btncompte;
    @FXML
    private Button btnavis;
    @FXML
    private Label labnom1;
    @FXML
    private Label lbprenom1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void showInformation(String nom,String prenom){
        labnom1.setText(nom);
        lbprenom1.setText(prenom);
    }

    @FXML
    private void compte(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass()
                    .getResource("../GUI/Compte_entrepreneur.fxml"));
 
          Scene scene = new Scene(root);
          
          
        Stage stage = new Stage();
        stage.setScene(scene);
       
        stage.setTitle("GoEvent");
        stage.show();

    }

    @FXML
    private void lienavis(ActionEvent event) {
    }

    @FXML
    private void logout(MouseEvent event) throws IOException {
Parent root = FXMLLoader.load(getClass()
                    .getResource("../GUI/GoEvent.fxml"));
 
          Scene scene = new Scene(root);
          
          
        Stage stage = new Stage();
        stage.setScene(scene);
       
        stage.setTitle("GoEvent");
        stage.show();

    }
}
