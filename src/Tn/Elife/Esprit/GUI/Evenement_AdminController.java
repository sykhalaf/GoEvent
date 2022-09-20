/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tn.Elife.Esprit.GUI;

import Tn.Elife.Esprit.Base.MaConnexion;
import Tn.Elife.Esprit.Entities.Categorie;
import Tn.Elife.Esprit.Entities.Event;
import Tn.Elife.Esprit.Interface.InterEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author siccaPrint
 */
public class Evenement_AdminController implements Initializable {
    int index = -1;

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
    private TextField txtnom;
    @FXML
    private TextField txtlieu;
    @FXML
    private TextField txtdescription;
    @FXML
    private TextField txtprix;
    @FXML
    private TextField txtstockticket;
    @FXML
    private TextField txttype;
    @FXML
    private DatePicker txtdate;
    @FXML
    private TextField txtid;
    @FXML
    private ComboBox<String> categorie;
    @FXML
    private ImageView img;
    @FXML
    private TableView<Event> colEvent;
    @FXML
    private TableColumn<Event, Integer> colid;
    @FXML
    private TableColumn<Event, String> colnom;
    @FXML
    private TableColumn<Event, String> collieu;
    @FXML
    private TableColumn<Event, String> coldescription;
    @FXML
    private TableColumn<Event, Float> colprix;
    @FXML
    private TableColumn<Event, Integer> colstockticket;
    @FXML
    private TableColumn<Event, LocalDate> coldate;
    @FXML
    private TableColumn<Event, String> coltype;
    @FXML
    private TableColumn<Event, String> colidcatégorie;
Connection cnx = MaConnexion.getInstance().getCnx();
    private final ObservableList<Event> dataList = FXCollections.observableArrayList();
    private final ObservableList<String> List = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showEvent();
        String tt = "SELECT * FROM `categorie-event`";

        Statement statement;

        try {
            statement = cnx.createStatement();
            ResultSet queryoutput = statement.executeQuery(tt);
            while (queryoutput.next()) {
                String x = queryoutput.getString("nom");

                List.add(x);
            }
            categorie.setItems(List);
        } catch (SQLException ex) {
            Logger.getLogger(Categorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

   public void showEvent() {
        dataList.clear();
        String tt = "SELECT * FROM `evenement`";

        Statement statement;

        try {
            statement = cnx.createStatement();
            ResultSet x = statement.executeQuery(tt);

            while (x.next()) {

                Integer i = x.getInt("id_event");
                String n = x.getString("nom_event");
                String l = x.getString("lieux_event");
                String desc = x.getString("description");
                Float p = x.getFloat("prix");
                Long s = x.getLong("stock_tiket");
                String d = x.getString("date");
                String t = x.getString("type_event");
                Integer ic = x.getInt("id_categorie");
                System.out.println(ic);
                String aa = "SELECT * FROM `categorie-event` where id_categorie='" + ic + "'";
                PreparedStatement ps;
                ps = cnx.prepareStatement(aa);
                ResultSet rs;
                // cat = cnx.prepareStatement(query);
                //  cat.setString(1, s);
                // rs2 = cat.executeQuery();
                rs = ps.executeQuery();

                while (rs.next()) {
                    String xxx = rs.getString("nom");

                    dataList.add(new Event(i, n, l, desc, p, s, LocalDate.parse(d), t, xxx));

                }

                colid.setCellValueFactory(new PropertyValueFactory<>("id"));
                colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
                collieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
                coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
                colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
                colstockticket.setCellValueFactory(new PropertyValueFactory<>("stockticket"));
                coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
                //   idimage.setCellValueFactory(new PropertyValueFactory<>("image"));
                coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
                colidcatégorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
                colEvent.setItems(dataList);
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
    }  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void compte(ActionEvent event) {
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

    @FXML
    private void retour(MouseEvent event) {
    }

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void add(ActionEvent event) throws SQLException {
         if (isValidated()) {
            String s = categorie.getSelectionModel().getSelectedItem().toString();
            Event e = new Event();

            e.setNom(txtnom.getText());
            e.setLieu(txtlieu.getText());
            e.setDescription(txtdescription.getText());
            e.setPrix(Float.parseFloat(txtprix.getText()));
            e.setStockticket(Long.parseLong(txtstockticket.getText()));
            e.setType(txttype.getText());
            e.setDate(txtdate.getValue());

            String tt = "SELECT * FROM `categorie-event` where nom='" + s + "'";
            PreparedStatement ps;
            ps = cnx.prepareStatement(tt);
            ResultSet rs;
            // cat = cnx.prepareStatement(query);
            //  cat.setString(1, s);
            // rs2 = cat.executeQuery();
            rs = ps.executeQuery();

            while (rs.next()) {
                String x = rs.getString("id_categorie");
                e.setId_categorie(x);
            }

            String requete = "INSERT INTO `evenement`(`nom_event`,`lieux_event`,`description`,`prix`,`stock_tiket`,`date`,`type_event`,`id_categorie`) VALUES (?,?, ?,?,?,?,?,?)";
        try {
            PreparedStatement ps1 = cnx.prepareStatement(requete);
            
         
            ps1.setString(1, e.getNom());
            ps1.setString(2, e.getLieu());
            ps1.setString(3, e.getDescription());
            ps1.setFloat(4, e.getPrix());
            ps1.setLong(5, e.getStockticket());
            ps1.setDate(6, Date.valueOf(e.getDate()));
            ps1.setString(7, e.getType());
            ps1.setString(8, e.getId_categorie());
            
            ps1.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }


            System.out.println("PS : event ajoutée avec succés!");

            //txtid.clear();
            txtnom.clear();
            txtlieu.clear();
            txtdescription.clear();
            txtprix.clear();
            txtstockticket.clear();
            txttype.clear();
            txtdate.setValue(null);

            showEvent();
            showMessageDialog(null, "Event ajouter avec succes");
        }
    }

    
    private boolean isValidated() {
//       
        LocalDate myDate = txtdate.getValue();
        if (txtnom.getText().equals("")) {

            showMessageDialog(null, "nom text field cannot be blank.");
            txtnom.requestFocus();
        } else if (txtlieu.getText().equals("")) {
            showMessageDialog(null, "lieu text field cannot be blank.");
            txtlieu.requestFocus();

        } else if (txtdescription.getText().equals("")) {
            showMessageDialog(null, "description text field cannot be blank.");
            txtdescription.requestFocus();

        } else if (txtprix.getText().equals("")) {
            showMessageDialog(null, "prix text field cannot be blank.");
            txtprix.requestFocus();

        } else if (txtstockticket.getText().equals("")) {
            showMessageDialog(null, "stock_tiket text field cannot be blank.");
            txtstockticket.requestFocus();

        } else if (txttype.getText().equals("")) {
            showMessageDialog(null, "type text field cannot be blank.");
            txttype.requestFocus();

        } else if (myDate == null) {
            showMessageDialog(null, "date  field cannot be blank.");
            txtdate.requestFocus();

        } else if (categorie.getSelectionModel().isSelected(-1)) {
            showMessageDialog(null, "categorie  must be selected");
            categorie.requestFocus();
        } else {
            return true;
        }
        return false;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void update(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
    }

    @FXML
    private void reset(ActionEvent event) {
    }
    
}
