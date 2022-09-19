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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author siccaPrint
 */
public class GoEventController implements Initializable {

    @FXML
    private Button btnconnecter;
    @FXML
    private Button btninscreption;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void lienconnecter(ActionEvent event) {
        
   
    }

    @FXML
    private void lieninscreption(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass()
                    .getResource("../GUI/Inscreption.fxml"));
 
          Scene scene = new Scene(root);
          
          
        Stage stage = new Stage();
        stage.setScene(scene);
       
        stage.setTitle("Inscreption");
        stage.show();
    }
    
}
