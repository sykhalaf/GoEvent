/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;


import Base.Bd;
import Base.Statics;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.mail.MessagingException;
import static javax.swing.JOptionPane.showMessageDialog;


/**
 * FXML Controller class
 *
 * @author bouden
 */
public class VerifMail implements Initializable {

    Window window;
    private TextField email;
    private Label msg;
    Connection cnx = Bd.getInstance().getCnx();
    private Label grren;
    @FXML
    private TextField txtemail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnemail(ActionEvent event) throws SQLException, IOException, MessagingException {
        String pass = txtemail.getText();
        if (this.isValidated()) {
            PreparedStatement ps;
            ResultSet rs;
            String query = "select * from user WHERE mail = ?";

            ps = cnx.prepareStatement(query);
            ps.setString(1, txtemail.getText());

            rs = ps.executeQuery();

            if (rs.next()) {
                Random rand = new Random(); //instance of random class
                int upperbound = 100000;
                int int_random = rand.nextInt(upperbound);
                PreparedStatement pss;
                ResultSet rss;
                int xx = rs.getInt("id_user");
                String yy = "update   user set  code ='" + int_random + "' where id_user= '" + xx + "' ";
                pss = cnx.prepareStatement(yy);
                pss.execute();

                //   String x = rs.getString("code");
               
//                motdepasseoublier a = new motdepasseoublier();
//                a.sendemailwelcom(pass, int_random, rs.getString("nom"));
                Login.Mail mail = new Login.Mail();
                mail.setupServerProperties();
                mail.draftEmail(pass, rs.getString("nom"), int_random);
                mail.sendEmail();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("confirmation");
                alert.setHeaderText("code");
                alert.setContentText("nous avons envoyé votre code par e-mail");
                if (alert.showAndWait().get() == ButtonType.OK) {

                }
                Stage stage = (Stage) txtemail.getScene().getWindow();
                stage.close();
                Statics.current_user.setMail(txtemail.getText());
                Parent root = FXMLLoader.load(getClass().getResource("/login/VerifCode.fxml"));

                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.setTitle("Verifcode");
//                stage.getIcons().add(new Image("/img/mm.png"));
                stage.show();

            } else {
             
  showMessageDialog(null, "nous ne trouvons pas d'utilisateur avec cette adresse e-mail.");
            }
        }
    }

    private boolean isValidated() {

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        if (txtemail.getText().equals("")) {
          
             showMessageDialog(null, "mail text field cannot be blank.");
            email.requestFocus();

        } else if (!pattern.matcher(txtemail.getText()).matches()) {
          
     
 showMessageDialog(null, "email invalid.");
            txtemail.requestFocus();
        } else {
            return true;
        }
        return false;
    }

//    public void init() {
//
//        ObservableList<user> mList = FXCollections.observableArrayList(this.afficherRole());
//        combox.setItems(mList);
//        
//        
//    }
    private void home(ActionEvent event) throws IOException {
        Stage stage = (Stage) email.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("../../gui/login/Login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("login");
        stage.getIcons().add(new Image("/img/mm.png"));
        stage.show();
    }
}
