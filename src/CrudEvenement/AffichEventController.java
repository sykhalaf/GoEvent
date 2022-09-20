/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrudEvenement;

import BasedeDonnees.MyConnection;
import Interface.InterEvent;
import Model.Categorie;
import Model.Event;
import Serv.ServEvent;
import java.net.URL;
import java.sql.Connection;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static javax.swing.JOptionPane.showMessageDialog;
import sun.util.resources.LocaleData;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AffichEventController implements Initializable {

    int index = -1;

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
    Connection cnx = MyConnection.getInstance().getCnx();
    private final ObservableList<Event> dataList = FXCollections.observableArrayList();

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
    private DatePicker txtdate;
    @FXML
    private TextField txttype;
    @FXML
    private TextField txtid;

    private final ObservableList<String> List = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> categorie;
    // private ComboBox<?> categorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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

            InterEvent xx = ServEvent.getInstance();
            xx.AjouterEvent(e);

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
    private void update(ActionEvent event) throws SQLException {
        if (txtnom.getText().equals("") || txtlieu.getText().equals("") || txtdescription.getText().equals("") || txtprix.getText().equals("") || txtstockticket.getText().equals("") || txttype.getText().equals("") || txtdate.getValue() == null || txtid.getText().equals("")) {
            showMessageDialog(null, "remplir cordonnees");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("modifier event");
            alert.setHeaderText("You're about to update event!");
            alert.setContentText("Do you want to update ");
            if (alert.showAndWait().get() == ButtonType.OK) {

                PreparedStatement ps;
                LocalDate myDate = txtdate.getValue();
                Integer id = Integer.parseInt(txtid.getText());
                String yy = "  update evenement set  nom_event ='" + txtnom.getText() + "', lieux_event='" + txtlieu.getText() +"', description='" + txtdescription.getText() +"', prix='" + txtprix.getText() +"', stock_tiket='" + txtstockticket.getText() + "' , type_event ='" + txttype.getText() + "', date ='" + myDate + "'  where id_event = '" + id + "' ";
             //String yy = "update   produits set  nom ='" + nom + "' , image =? , prix ='" + prix.getText() + "', Description ='" + Description.getText() + "' , quantite ='" + Quantite.getText() + "' , categorie ='" + s1 + "'  where id = '" + xx + "' ";
               // String yy = "  update evenement set  nom_event =?, lieux_event=?, description=?,"
                       // + " prix=?, stock_tiket=?, type_event =?, date =?  where id_event = ?";
                        
                ps = cnx.prepareStatement(yy);
                ps.execute();

                showMessageDialog(null, "event  modifier avec succes");
                //txtid.clear();
                txtnom.clear();
                txtlieu.clear();
                txtdescription.clear();
                txtprix.clear();
                txtstockticket.clear();
                txttype.clear();
                txtdate.setValue(null);

                showEvent();
          
            }
        }
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        if (txtnom.getText().equals("") || txtlieu.getText().equals("") || txtdescription.getText().equals("") || txtprix.getText().equals("") || txtstockticket.getText().equals("") || txttype.getText().equals("") || txtdate.getValue() == null || txtid.getText().equals("")) {
            showMessageDialog(null, "remplir cordonnees");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Supprimer event");
            alert.setHeaderText("You're about to delete event!");
            alert.setContentText("Do you want to delete ");
            if (alert.showAndWait().get() == ButtonType.OK) {

                PreparedStatement ps;

                Integer id = Integer.parseInt(txtid.getText());

                String yy = "delete   from  evenement where id_event = '" + id + "' ";
                ps = cnx.prepareStatement(yy);
                ps.execute();

                showMessageDialog(null, "event  supprimer avec succes");
                //txtid.clear();
                txtnom.clear();
                txtlieu.clear();
                txtdescription.clear();
                txtprix.clear();
                txtstockticket.clear();
                txttype.clear();
                txtdate.setValue(null);

                showEvent();
            }
        }
    }

    @FXML
    private void reset(ActionEvent event) {
        txtnom.clear();
        txtlieu.clear();
        txtdescription.clear();
        txtprix.clear();
        txtstockticket.clear();
        txttype.clear();
        txtdate.setValue(null);
    }

    @FXML
    private void getSelected(MouseEvent event) {
        index = colEvent.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtid.setText(String.valueOf(colid.getCellData(index)));
        txtnom.setText(colnom.getCellData(index));
        txtlieu.setText(collieu.getCellData(index));
        txtdescription.setText(coldescription.getCellData(index));
        txtprix.setText(String.valueOf(colprix.getCellData(index)));
        txtstockticket.setText(String.valueOf(colstockticket.getCellData(index)));
        txtdate.setValue(coldate.getCellData(index));
        txttype.setText(coltype.getCellData(index));
        String aa = colidcatégorie.getCellData(index);
        categorie.getSelectionModel().select(aa
        );
       
    }
}
