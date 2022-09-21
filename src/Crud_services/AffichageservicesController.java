/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud_services;

import Base.Connexionbd;
import Model.servicesModel;
import interfaceservices.InterServices;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static javax.swing.JOptionPane.showMessageDialog;
import serviceservices.classservice;

/**
 * FXML Controller class
 *
 * @author Marwen
 */
public class AffichageservicesController implements Initializable {
 int index = -1;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtdescription;
    @FXML
    private TableView<servicesModel> tableservice;
    @FXML
    private TableColumn<servicesModel, Integer> colid;
    @FXML
    private TableColumn<servicesModel, String> coldescription;
     Connection cnx = Connexionbd.getInstance().getCnx();
    private final ObservableList<servicesModel> dataList = FXCollections.observableArrayList();
    @FXML
    private TextField txttype;
    @FXML
    private TableColumn<servicesModel, String> coltype;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showEvent();
    }    
 public void showEvent() {
        dataList.clear();
        String tt = "SELECT * FROM `services`";

        Statement statement;

        try {
            statement = cnx.createStatement();
            ResultSet x = statement.executeQuery(tt);
            while (x.next()) {
                Integer i = x.getInt("id");
                String n = x.getString("type");
                String d = x.getString("description");

               
                dataList.add(new servicesModel(i, n,  d));
                colid.setCellValueFactory(new PropertyValueFactory<>("id"));
                coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
                
                //   idimage.setCellValueFactory(new PropertyValueFactory<>("image"));
                coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                tableservice.setItems(dataList);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    @FXML
    private void add(ActionEvent event) {
         if (isValidated()) {

            servicesModel e = new servicesModel();
            e.setType(txttype.getText());
            e.setDescription(txtdescription.getText());
           
            InterServices xx = classservice.getInstance();
            xx.AjouterServices(e);

            System.out.println("PS : Service ajoutée avec succés!");

            txttype.clear();
            txtdescription.clear();
           
            showEvent();
            showMessageDialog(null, "Service ajoutée avec succes");
        }
    }
     private boolean isValidated() {
//       
      if (txttype.getText().equals("")) {
            showMessageDialog(null, "type text field cannot be blank.");
            txttype.requestFocus();

        } else  if (txtdescription.getText().equals("")) {
            showMessageDialog(null, "type text field cannot be blank.");
            txtdescription.requestFocus();

        }  else {
            return true;
        }
        return false;
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        if (txttype.getText().equals("") || txtdescription.getText().equals("")  || txtid.getText().equals("")) {
            showMessageDialog(null, "remplir cordonnees");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("modifier service");
            alert.setHeaderText("t'es sure de modifier le service!");
            alert.setContentText("tu veux vraiment modifier?");
            if (alert.showAndWait().get() == ButtonType.OK) {

                PreparedStatement ps;
              
                Integer id = Integer.parseInt(txtid.getText());
                String yy = "  update services set  type ='" + txttype.getText() + "' , description ='" + txtdescription.getText() + "' where id = '" + id + "' ";

                ps = cnx.prepareStatement(yy);
                ps.execute();

                showMessageDialog(null, "service  modifier avec succes");
               
                txttype.clear();
                txtdescription.clear();
                showEvent();
            }
    }}

    @FXML
    private void delete(ActionEvent event) throws SQLException {
         if ( txttype.getText().equals("") ||txtdescription.getText().equals("")|| txtid.getText().equals("")) {
            showMessageDialog(null, "remplir cordonnees");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Supprimer event");
            alert.setHeaderText("You're about to delete event!");
            alert.setContentText("Do you want to delete ");
            if (alert.showAndWait().get() == ButtonType.OK) {

                PreparedStatement ps;

                Integer id = Integer.parseInt(txtid.getText());

                String yy = "delete   from  services where id = '" + id + "' ";
                ps = cnx.prepareStatement(yy);
                ps.execute();

                showMessageDialog(null, "Service  supprimer avec succés");
               
                txttype.clear();
                 txtdescription.clear();
                showEvent();
            }
        }
    }

    @FXML
    private void reset(ActionEvent event) {
         
        txttype.clear();
         
        txtdescription.clear();
    }

    @FXML
    private void getselected(MouseEvent event) {
          index = tableservice.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtid.setText(String.valueOf(colid.getCellData(index)));
        
        

        txttype.setText(coltype.getCellData(index));
        txtdescription.setText(coldescription.getCellData(index));

    }
    
}
