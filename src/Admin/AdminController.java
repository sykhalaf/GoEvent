/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author Ce Pc
 */
public class AdminController implements Initializable {

    @FXML
    private Button lgout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logout(ActionEvent event) throws IOException {
         Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("logout");
        alert.setHeaderText("You're about to lgout!");
        alert.setContentText("Do you want to delete ");
        if (alert.showAndWait().get() == ButtonType.OK) {
            Stage stage = (Stage) lgout.getScene().getWindow();
            stage.close(); 
            Parent root = FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("Login");
           // stage.getIcons().add(new Image("/img/mm.png"));
            stage.show();
            showMessageDialog(null, "Logut successfuly");
        }
    }
    
}
