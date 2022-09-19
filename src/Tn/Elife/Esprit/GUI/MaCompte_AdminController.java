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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author siccaPrint
 */
public class MaCompte_AdminController implements Initializable {
    
    int index =-1 ;

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
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfmail;
    @FXML
    private TextField tfpwd;
    @FXML
    private TextField tftel;
    @FXML
    private TextField tfrole;
    @FXML
    private TextField tfimage;
    @FXML
    private TextField tfid;
    @FXML
    private TableView<Utilisateurs> tvcompte;
    @FXML
    private TableColumn<Utilisateurs, Integer> colid;
    @FXML
    private TableColumn<Utilisateurs, String> colnom;
    @FXML
    private TableColumn<Utilisateurs, String> colprenom;
    @FXML
    private TableColumn<Utilisateurs, String> colmail;
    @FXML
    private TableColumn<Utilisateurs, String> colpwd;
    @FXML
    private TableColumn<Utilisateurs, Integer> coltel;
    @FXML
    private TableColumn<Utilisateurs, String> colrole;
    @FXML
    private TableColumn<Utilisateurs, String> colimage;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnannuler;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showComptes();
    }    

    
    
    
  Connection cnx = MaConnexion.getInstance().getCnx();
    private final ObservableList<Utilisateurs> dataList = FXCollections.observableArrayList();
    
     public void showComptes() {
         
    dataList.clear();
        String tt = "select * from `user` WHERE `role` = 'admin' ";

        Statement statement;

        try {
          statement = cnx.createStatement();
         ResultSet x = statement.executeQuery(tt);
         while (x.next()) {
             Integer t = x.getInt("id_user");
             String n = x.getString("nom");
             String d = x.getString("prenom");
             String f = x.getString("email");
             String j = x.getString("pwd");
             Integer i = x.getInt("num_tel");
             String k = x.getString("role");
             String v = x.getString("image");
              
               dataList.add(new Utilisateurs(t, n, d, f, j, i, k ,v));
             
            
             colid.setCellValueFactory(new PropertyValueFactory<>("id_user"));
             colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
             colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
             colmail.setCellValueFactory(new PropertyValueFactory<>("email"));
             colpwd.setCellValueFactory(new PropertyValueFactory<>("pwd"));
             coltel.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
             colrole.setCellValueFactory(new PropertyValueFactory<>("role"));
             colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
             tvcompte.setItems(dataList);

        }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
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
    private void getSelect(MouseEvent event) {
        
        
        
        index = tvcompte.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        tfid.setText(String.valueOf(colid.getCellData(index)));
        tfnom.setText(String.valueOf(colnom.getCellData(index)));
        tfprenom.setText(String.valueOf(colprenom.getCellData(index)));
        tfmail.setText(String.valueOf(colmail.getCellData(index)));
        tfpwd.setText(String.valueOf(colpwd.getCellData(index)));
        tftel.setText(String.valueOf(coltel.getCellData(index)));
        tfrole.setText(String.valueOf(colrole.getCellData(index)));
        tfimage.setText(String.valueOf(colimage.getCellData(index)));
        
        
        
        
        
        
        
        
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        
        
      if (tfnom.getText().equals("") || tfprenom.getText().equals("") ||tfmail.getText().equals("") ||tfpwd.getText().equals("") ||tftel.getText().equals("") || tfrole.getText().equals("") ||tfimage.getText().equals("") || tfid.getText().equals("")) {
            showMessageDialog(null, "remplir cordonnees");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("modifier Compte");
            alert.setHeaderText("You're about to update Compte!");
            alert.setContentText("Do you want to update ");
            if (alert.showAndWait().get() == ButtonType.OK) {

                PreparedStatement ps;
                Integer id = Integer.parseInt(tfid.getText());
               String yy = "  update user set  nom ='" + tfnom.getText() + "' , prenom ='" + tfprenom.getText()  + "' , email ='" + tfmail.getText() + "' , pwd ='" + tfpwd.getText()+ "' , num_tel='" + tftel.getText() + "' , role ='" + tfrole.getText() + "' , image ='" + tfimage.getText() + "'  where id_user = '" + tfid.getText() + "' ";

                ps = cnx.prepareStatement(yy);
                ps.execute();

                showMessageDialog(null, "Compte  modifier avec succes");
                
                tfnom.clear();
                tfprenom.clear();
                tfmail.clear();
                tfpwd.clear();
                tftel.clear();
                
                tfimage.clear();
                
                showComptes();
            }
        }
   
          

        
        
        
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        
        
        
         if (tfnom.getText().equals("") || tfprenom.getText().equals("") ||tfmail.getText().equals("") ||tfpwd.getText().equals("") ||tftel.getText().equals("") || tfrole.getText().equals("") ||tfimage.getText().equals("") || tfid.getText().equals("")) {
            showMessageDialog(null, "remplir cordonnees");
        } else {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Supprimer Ma Compte");
            alert.setHeaderText("You're about to delete your Compte!");
            alert.setContentText("Do you want to delete ");
            if (alert.showAndWait().get() == ButtonType.OK) {

                PreparedStatement ps;

                Integer id = Integer.parseInt(tfid.getText());

                String yy = "delete   from  user where id_user = '" + tfid.getText() + "' ";
                ps = cnx.prepareStatement(yy);
                ps.execute();

                showMessageDialog(null, "Compte supprimer avec succes");
                 tfid.clear();
                 tfnom.clear();
                tfprenom.clear();
                tfmail.clear();
                tfpwd.clear();
                tftel.clear();
                tfrole.clear();
                tfimage.clear();
                
                showComptes();
            }
        
   }
       
        
  
        
        
    }

    @FXML
    private void logout(MouseEvent event) {
    }

    @FXML
    private void annuler(ActionEvent event) {
        
                tfnom.clear();
                tfprenom.clear();
                tfmail.clear();
                tfpwd.clear();
                tftel.clear();
                tfrole.clear();
                tfimage.clear();
                tfid.clear();
  
    }

    @FXML
    private void retour(MouseEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass()
                    .getResource("../GUI/Bienvenue_Admin.fxml"));
 
          Scene scene = new Scene(root);
          
          
        Stage stage = new Stage();
        stage.setScene(scene);
       
        stage.setTitle("Bienvenue!!");
        stage.show();
        
        
        
    }
    
}
