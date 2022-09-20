/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrudQuiz;

import Base.Connexion;
import Interface.InterQuiz;
import Model.Quiz;
import Service.ServiceQuiz;
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

/**
 * FXML Controller class
 *
 * @author Elife-Kef-007
 */
public class AfficheQuizController implements Initializable {

    int index = -1;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtoption1;
    @FXML
    private TextField txtoption2;
    @FXML
    private TextField txtoption3;
    @FXML
    private TextField txttitre;
    @FXML
    private TableView<Quiz> tvquiz;
    @FXML
    private TableColumn<Quiz, Integer> colid;
    @FXML
    private TableColumn<Quiz, String> coltitre;
    @FXML
    private TableColumn<Quiz, String> coloption1;
    @FXML
    private TableColumn<Quiz, String> coloption2;
    @FXML
    private TableColumn<Quiz, String> coloption3;
    @FXML
    private TableColumn<Quiz, String> colboonereponse;
    @FXML
    private TextField txtbonnereponse;
    Connection cnx = Connexion.getInstance().getCnx();
    private final ObservableList<Quiz> dataList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showEvent();
    }

    public void showEvent() {
        dataList.clear();
        String tt = "SELECT * FROM `quiz`";

        Statement statement;

        try {
            statement = cnx.createStatement();
            ResultSet x = statement.executeQuery(tt);
            while (x.next()) {
                Integer i = x.getInt("id");
                String n = x.getString("titre_quiz");
                String d = x.getString("option1");

                String t = x.getString("option2");
                String m = x.getString("option3");
                String z = x.getString("bonne_reponse");
                dataList.add(new Quiz(i, n, d, t, m, z));

                colid.setCellValueFactory(new PropertyValueFactory<>("id"));
                coltitre.setCellValueFactory(new PropertyValueFactory<>("titre_quiz"));
                coloption1.setCellValueFactory(new PropertyValueFactory<>("option1"));
                coloption2.setCellValueFactory(new PropertyValueFactory<>("option2"));
                //   idimage.setCellValueFactory(new PropertyValueFactory<>("image"));
                coloption3.setCellValueFactory(new PropertyValueFactory<>("option3"));
                colboonereponse.setCellValueFactory(new PropertyValueFactory<>("bonne_reponse"));

                tvquiz.setItems(dataList);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void ajouter(ActionEvent event) {
        if (isValidated()) {

            Quiz e = new Quiz();
            e.setTitre_quiz(txttitre.getText());
            e.setOption1(txtoption1.getText());
            e.setOption2(txtoption2.getText());
            e.setOption3(txtoption3.getText());
            e.setBonne_reponse(txtbonnereponse.getText());

            InterQuiz xx = ServiceQuiz.getInstance();
            xx.AjouterEvent(e);

            System.out.println("PS : quiz ajoutée avec succés!");

            txttitre.clear();
            txtoption1.clear();
            txtoption2.clear();
            txtoption3.clear();
            txtbonnereponse.clear();

            showEvent();
            showMessageDialog(null, "quiz ajouter avec succes");
        }
    }

    private boolean isValidated() {
//       

        if (txttitre.getText().equals("")) {

            showMessageDialog(null, "nom text field cannot be blank.");
            txttitre.requestFocus();
        } else if (txtoption1.getText().equals("")) {
            showMessageDialog(null, "type text field cannot be blank.");
            txtoption1.requestFocus();

        } else {
            return true;
        }
        return false;
    }

    @FXML
    private void DeleteQuiz(ActionEvent event) throws SQLException {
        if (txttitre.getText().equals("") || txtoption1.getText().equals("") || txtoption2.getText().equals("") || txtoption3.getText().equals("") || txtbonnereponse.getText().equals("") || txtid.getText().equals("")) {
            showMessageDialog(null, "remplir cordonnees");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Supprimer quiz");
            alert.setHeaderText("You're about to delete quiz!");
            alert.setContentText("Do you want to delete ");
            if (alert.showAndWait().get() == ButtonType.OK) {

                PreparedStatement ps;

                Integer id = Integer.parseInt(txtid.getText());

                String yy = "delete   from  quiz where id = '" + id + "' ";
                ps = cnx.prepareStatement(yy);
                ps.execute();

                showMessageDialog(null, "quiz  supprimer avec succes");
                txttitre.clear();
                txtoption1.clear();
                txtoption2.clear();
                txtoption3.clear();
                txtbonnereponse.clear();
                showEvent();
            }
        }

    }

    @FXML
    private void UpdateQuiz(ActionEvent event) throws SQLException {
        if (txttitre.getText().equals("") || txtoption1.getText().equals("") || txtoption2.getText().equals("") || txtoption3.getText().equals("") || txtbonnereponse.getText().equals("") || txtid.getText().equals("")) {
            showMessageDialog(null, "remplir cordonnees");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("modifier quiz");
            alert.setHeaderText("You're about to update quiz!");
            alert.setContentText("Do you want to update ");
            if (alert.showAndWait().get() == ButtonType.OK) {

                PreparedStatement ps;
                // LocalDate myDate = txtdate.getValue();
                Integer id = Integer.parseInt(txtid.getText());
                String yy = "  update quiz set  titre_quiz ='" + txttitre.getText() + "' , option1 ='" + txtoption1.getText() + "',option2 ='" + txtoption2.getText() + "',option3 ='" + txtoption3.getText() + "',bonne_reponse ='" + txtbonnereponse.getText() + "'   where id = '" + id + "' ";

                ps = cnx.prepareStatement(yy);
                ps.execute();

                showMessageDialog(null, "quiz  modifier avec succes");
                txttitre.clear();
                txtoption1.clear();
                //txtdate.setValue(null);
                txtoption2.clear();
                txtoption3.clear();
                txtbonnereponse.clear();
                showEvent();
            }
        }
    }

    @FXML
    private void getselected(MouseEvent event) {
        index = tvquiz.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtid.setText(String.valueOf(colid.getCellData(index)));
        txttitre.setText(coltitre.getCellData(index));
        txtoption1.setText(coloption1.getCellData(index));
        txtoption2.setText(coloption2.getCellData(index));
        txtoption3.setText(coloption3.getCellData(index));
        txtbonnereponse.setText(colboonereponse.getCellData(index));

        //txtdate.setValue(coldate.getCellData(index));
        //  txttype.setText(coltype.getCellData(index));
    }
}

//    private void modifier(ActionEvent event) throws SQLException {
// 
//        if (txttitre.getText().equals("") || txtoption1.getText().equals("") || txtoption2.getText().equals("")
//                || txtoption3.getText().equals("")|| txtbonnereponse.getText().equals("")|||| txtid.getText().equals("")) {
//            showMessageDialog(null, "remplir cordonnees");
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("modifier quiz");
//            alert.setHeaderText("You're about to update quiz!");
//            alert.setContentText("Do you want to update ");
//            if (alert.showAndWait().get() == ButtonType.OK) {
//
//                PreparedStatement ps;
//              //  LocalDate myDate = txtdate.getValue();
//                Integer id = Integer.parseInt(txtid.getText());
//                String yy = "  update quiz set  titre_quiz ='" + txttitre.getText() + "' , option1 ='" + txtoption1.getText()+"' , option2='"+txtoption2.getText()+"' , option3 ='"+
//                        txtoption3.getText()+"' , bonne_reponse ='"+txtbonnereponse.getText()+
//                        "'  where id = '" + id + "' ";
//
//                ps = cnx.prepareStatement(yy);
//                ps.execute();
//
//                showMessageDialog(null, "event  modifier avec succes");
//                txtbonnereponse.clear();
//                txttitre.clear();
//                txtoption1.clear();
//                txtoption2.clear();
//                txtoption3.clear();
//                showEvent();
//            }
//        }
//
//    }
//    }
//
//    @FXML
////    private void supprimer(ActionEvent event) {
////
////    }
////
////}
