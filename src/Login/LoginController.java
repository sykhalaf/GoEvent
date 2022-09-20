/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import Base.Bd;
import Base.Statics;
import static Register.RegisterController.getHash;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * FXML Controller class
 *
 * @author Ce Pc
 */
public class LoginController implements Initializable {

    Connection cnx = Bd.getInstance().getCnx();
    @FXML
    private TextField txtmail;
    @FXML
    private Button registerButton;
    @FXML
    private Button registerButton1;
    @FXML
    private PasswordField txtpasseword;
    @FXML
    private CheckBox remember;
    Preferences preference;
    boolean rememberPreference;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        remember.setSelected(true);
        preference = Preferences.userNodeForPackage(LoginController.class);
        if (preference != null) {
            if (preference.get("email", null) != null && !preference.get("email", null).isEmpty()) {
                txtmail.setText(preference.get("email", null));
                txtpasseword.setText(preference.get("password", null));
            }
        }
        txtmail.clear();
        txtpasseword.clear();
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        String pass = txtpasseword.getText();
        if (this.isValidated()) {
            PreparedStatement ps, ps2;
            ResultSet rs;
            String query = "select * from user WHERE mail = ? and password = ?";
            try {
                ps = cnx.prepareStatement(query);
                ps.setString(1, txtmail.getText());
                ps.setString(2, getHash(pass.getBytes(), "SHA-1"));

                rs = ps.executeQuery();

                if (rs.next()) {

                    if (remember.isSelected()) {
                        preference.put("email", txtmail.getText());
                        preference.put("password", pass);

                    } else {
                        preference.put("email", "");
                        preference.put("password", "");
                    }
                    Stage xx = (Stage) remember.getScene().getWindow();
                    Parent aaa = null;
                    String s1 = rs.getString("role");

                    if (s1.equalsIgnoreCase("Admin")) {

                        //   Statics.current_user.setImage(rs.getString("image"));
                        Stage stage = (Stage) txtmail.getScene().getWindow();
                        stage.close();

                        Parent root = FXMLLoader.load(getClass().getResource("../Admin/Admin.fxml"));

                        Scene scene = new Scene(root);

                        stage.setScene(scene);

                        stage.setTitle("Admin Panel");
                        //    stage.getIcons().add(new Image("/img/mm.png"));
                        stage.show();
                        showMessageDialog(null, "Login successfuly");

                    } else if (s1.equalsIgnoreCase("Client")) {
                        Statics.current_user.setNom(rs.getString("nom"));
                        Statics.current_user.setPrenom(rs.getString("prenom"));
                        Statics.current_user.setId_user(rs.getInt("id_user"));
                        Statics.current_user.setMail(rs.getString("mail"));

                        Stage stage = (Stage) txtmail.getScene().getWindow();
                        stage.close();

                        Parent root = FXMLLoader.load(getClass().getResource("../Client/Client.fxml"));

                        Scene scene = new Scene(root);

                        stage.centerOnScreen();
                        stage.setMaxHeight(900);
                        stage.setMaxWidth(1600);
                        stage.setMaximized(true);
                        stage.centerOnScreen();
                        stage.setScene(scene);
                        stage.setTitle("Client");
                        //  stage.getIcons().add(new Image("/img/mm.png"));
                        stage.show();

                    } else if (s1.equalsIgnoreCase("entrepreneur")) {
                        Statics.current_user.setNom(rs.getString("nom"));
                        Statics.current_user.setPrenom(rs.getString("prenom"));
                        Statics.current_user.setId_user(rs.getInt("id_user"));
                        Statics.current_user.setMail(rs.getString("mail"));

                        Stage stage = (Stage) txtmail.getScene().getWindow();
                        stage.close();

                        Parent root = FXMLLoader.load(getClass().getResource("../Entreprenneur/Entreprenneur.fxml"));

                        Scene scene = new Scene(root);

                        stage.centerOnScreen();

                        stage.setScene(scene);
                        stage.setTitle("Entreprenneur");
                        //  stage.getIcons().add(new Image("/img/mm.png"));
                        stage.show();

                    }

                } else {

                    showMessageDialog(null, "Invalid username and password.");
                    txtmail.requestFocus();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

    }

    private boolean isValidated() {

        if (txtmail.getText().equals("")) {

            showMessageDialog(null, "mail text field cannot be blank.");
            txtmail.requestFocus();

        } else if (txtpasseword.getText().equals("")) {

            showMessageDialog(null, "Password text field cannot be blank.");
            txtpasseword.requestFocus();

//        }else if (IsEmpty(combox.SelectedItems)){
//
//            showMessageDialog(null, "erreur");
//            combox.requestFocus();
        } else {
            return true;
        }
        return false;
    }

    @FXML
    private void register(ActionEvent event) throws IOException {

        Stage stage = (Stage) txtmail.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("../Register/Register.fxml"));

        Scene scene = new Scene(root);

        stage.centerOnScreen();

        stage.setScene(scene);
        stage.setTitle("Register");
        //  stage.getIcons().add(new Image("/img/mm.png"));
        stage.show();
    }

    @FXML
    private void rember(ActionEvent event) throws IOException {

        Stage stage = (Stage) txtmail.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("/Login/VerifMail.fxml"));

        Scene scene = new Scene(root);

        stage.centerOnScreen();

        stage.setScene(scene);
        stage.setTitle("VerifMail");
        //  stage.getIcons().add(new Image("/img/mm.png"));
        stage.show();
    }
    

}
