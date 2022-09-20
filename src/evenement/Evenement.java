/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evenement;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Ce Pc
 */
public class Evenement extends Application {
    
    @Override
    public void start(Stage primaryStage) {
      try {
    
            Parent root = FXMLLoader.load(getClass().getResource("/CrudEvenement/AffichEvent.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Event");
         //   primaryStage.getIcons().add(new Image("/img/foody.png"));
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
           Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
