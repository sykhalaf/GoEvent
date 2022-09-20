/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrudEvenement;

import BasedeDonnees.MyConnection;
import Model.Event;
import Model.Categorie;
import Model.Mail;
import Model.User;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EvenementsController implements Initializable {

    int index = -1;
    @FXML
    private ComboBox<Event> rechercher;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnSettings1;
    @FXML
    private TableColumn<Event, Integer> idCol;
    
   
    //private final ObservableList<Event> dataList = FXCollections.observableArrayList();
    Connection cnx = MyConnection.getInstance().getCnx();
    private final ObservableList<Event> list = FXCollections.observableArrayList();
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Event student = null;
    @FXML
    private TableView<Event> eventTable;
    private TableColumn<Event, String> Event;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOverview;
    @FXML
    private Pane pnlOrders;
    private TableColumn<Event, String> eventCol;
    private final ObservableList<Event> List = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Event, String> nomCol;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        String tt = "SELECT * FROM `evenement`";

        Statement statement;

        try {
            statement = cnx.createStatement();
            ResultSet queryoutput = statement.executeQuery(tt);
            while (queryoutput.next()) {
                String n = queryoutput.getString("nom_event");
              
                 List.add(new Event(n));
                   System.out.println(list);
                //List.add(new Event());
                
                rechercher.setItems(list);
            }
            
            nomCol.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
            idCol.setCellValueFactory(new PropertyValueFactory<>("id_event"));
            eventTable.setItems(List);
            FilteredList<Event> filteredData = new FilteredList<>(List, b -> true);
            rechercher.buttonCellProperty().addListener((observable, oldValue, newValue) -> {
            
            
                filteredData.setPredicate((Event) -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
            //        String lowerCaseFilter = newValue.toLowerCase();

              //      if (Event.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches first name.

                //    } else {
                   ///     return false; // Does not match.
                   //}
                });
            });

            // 3. Wrap the FilteredList in a SortedList. 
           // SortedList<Categories> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            //sortedData.comparatorProperty().bind(eventTable.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            //eventTable.setItems(sortedData);

        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        // TODO
    }    

    @FXML
    private void Acceuil(ActionEvent event)throws IOException {
        
        Stage stage = (Stage) pnlCustomer.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/CrudEvenement/Evenements.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
       // stage.setTitle("Admin Panel");
        //stage.getIcons().add(new Image("/img/mm.png"));
        stage.show();
    }

    @FXML
    private void Event(ActionEvent event)throws IOException {
        Stage stage = (Stage) btnOverview.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/CrudEvenement/Evenements.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Event");
       // stage.getIcons().add(new Image("/img/mm.png"));
        stage.show();
    }
    private void loadDate() throws IOException {
        refreshTable();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id_event"));
        Event.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
    }

    @FXML
     private void Categories(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnOverview.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/CrudEvenement/Evenements.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Categorie");
        //stage.getIcons().add(new Image("/img/mm.png"));
        stage.show();

    }

    @FXML
    
    private void Contact(ActionEvent event) throws  IOException, MessagingException  {
        //String tt = "SELECT * FROM `user`";
        //String yy = "SELECT * FROM `evenement`";
        

        Statement statement;
                    //Event i=new Event();
                    //User e=new User();
                    Mail mail = new Mail();
                    mail.setupServerProperties();
                  // mail.draftEmail(e.getMail(),e.getNom(),i.getNom() ,i.getLieu() ,Integer.valueOf(e.getId_user()));
                    //mail.draftEmail(mai(l.getText(), nom.getText(),nom.getText(),lieu.getText(),Integer.valueOf(id_user.getText());

                    mail.sendEmail(); 
 }
      public void chercher() {

        List.clear();
        String tt = "SELECT nom_event FROM `evenement`";

        Statement statement;
        try {
            statement = cnx.createStatement();
            ResultSet queryoutput = statement.executeQuery(tt);
            while (queryoutput.next()) {
                String n = queryoutput.getString("nom_event");
              Integer y = queryoutput.getInt("id_event");
               
             List.add(new Event( n,y));
              // List.add(new Event(n));
              
            }
            Event.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
            idCol.setCellValueFactory(new PropertyValueFactory<>("id_event"));
            eventTable.setItems(List);
            FilteredList<Event> filteredData = new FilteredList<>(List, b -> true);
            rechercher.buttonCellProperty().addListener((observable, oldValue, newValue) -> {
            
            
                filteredData.setPredicate((Event) -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    

                    if (Event.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches first name.

                    } else {
                        return false; // Does not match.
                    }
                });
            });

            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<Event> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(eventTable.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            eventTable.setItems(sortedData);

        } catch (SQLException ex) {
            Logger.getLogger(Event.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void refreshTable() throws IOException {
//         Stage stage = (Stage) btnOverview.getScene().getWindow();
//        stage.close();
//          Parent root = FXMLLoader.load(getClass().getResource("/CrudEvenement/Evenements.fxml"));
//
//        Scene scene = new Scene(root);

        List.clear();
        
        try {

            String query = "SELECT * FROM `evenement`";
            preparedStatement = cnx.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                
                List.add(new Event(
                      //  resultSet.getInt("id_event"),
                        resultSet.getString("nom_event")
                ));
                eventTable.setItems(List);

            }

        } catch (SQLException ex) {
            Logger.getLogger(Event.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    void getSelected(MouseEvent event) {
        index = eventTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        //idCol.setText(String.toString());
       
        idCol.setText(idCol.getCellData(index).toString());
        nomCol.setText(eventCol.getCellData(index));

    }

    
}
     
    

    
   

