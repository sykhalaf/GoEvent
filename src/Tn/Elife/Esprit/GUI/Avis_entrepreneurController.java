/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tn.Elife.Esprit.GUI;

import Tn.Elife.Esprit.Base.MaConnexion;
import Tn.Elife.Esprit.Entities.Avis;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author siccaPrint
 */
public class Avis_entrepreneurController implements Initializable {

    @FXML
    private Button btncompte;
    @FXML
    private Button btnavis;
    @FXML
    private TableView<Avis> tvavis;
    @FXML
    private TableColumn<Avis, Integer> colidavis;
    @FXML
    private TableColumn<Avis, Integer> colidclient;
    @FXML
    private TableColumn<Avis, String> colc;
    @FXML
    private TableColumn<Avis, String> colnom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showComptes();
    }    

    
    
     Connection cnx = MaConnexion.getInstance().getCnx();
    private final ObservableList<Avis> dataList = FXCollections.observableArrayList();
    
     public void showComptes() {
         
    dataList.clear();
        //String tt = "SELECT * FROM `avis`";
         //String tt = "SELECT * FROM `avis`";
         String tf = "SELECT * FROM `user` INNER JOIN `avis` ON  `user`.`id_user` = `avis`.`id_client`";

        Statement statement;

        try {
          statement = cnx.createStatement();
         ResultSet x = statement.executeQuery(tf);
         while (x.next()) {
             Integer t = x.getInt("id_avis");
             Integer u = x.getInt("id_user");
             String n = x.getString("contenu");
             
             String f = x.getString("nom");
             
            
              dataList.add(new Avis(t, u, n, f));
               
             
            
             colidavis.setCellValueFactory(new PropertyValueFactory<>("id_avis"));
             colidclient.setCellValueFactory(new PropertyValueFactory<>("id_client"));
             colc.setCellValueFactory(new PropertyValueFactory<>("nom"));
             colnom.setCellValueFactory(new PropertyValueFactory<>("contenu"));
             
             
             tvavis.setItems(dataList);

        }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    

    
    
    @FXML
    private void compte(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass()
                    .getResource("../GUI/Compte_entrepreneur.fxml"));
 
          Scene scene = new Scene(root);
          
          
        Stage stage = new Stage();
        stage.setScene(scene);
       
        stage.setTitle("Ma Compte");
        stage.show();
    }

    @FXML
    private void lienavis(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass()
                    .getResource("../GUI/Avis_entrepreneur.fxml"));
 
          Scene scene = new Scene(root);
          
          
        Stage stage = new Stage();
        stage.setScene(scene);
       
        stage.setTitle("Avis Entrepreneur");
        stage.show();
    }

    @FXML
    private void logout(MouseEvent event) {
    }
    
}
