/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Register;

import Base.Bd;
import Model.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.DatatypeConverter;

/**
 * FXML Controller class
 *
 * @author Ce Pc
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtmail;
    @FXML
    private Button registerButton;
    @FXML
    private Button registerButton1;
    @FXML
    private PasswordField txtpasseword;
    @FXML
    private TextField txtnum;
    @FXML
    private Button uploid;
    @FXML
    private TextField txtprenom;
    String filename = null;
    File xxx = null;
    Connection cnx = Bd.getInstance().getCnx();
    @FXML
    private ComboBox<String> comborole;
    ObservableList<String> dataList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showrole();
    }

    public void showrole() {

        String a = "Client";
        String b = "entrepreneur";
        dataList.add(a);
        dataList.add(b);
        comborole.setItems(dataList);
    }

    @FXML
    private void register(ActionEvent event) throws SQLException {
        if (isValidated()) {
            PreparedStatement ps;
            ResultSet rs;
            String s = comborole.getSelectionModel().getSelectedItem();
            String yy = "SELECT * FROM user WHERE mail ='" + txtmail.getText() + "'";
            String req = "INSERT INTO `user`(`nom`, `prenom`, `mail`,`password`,`num-tel`,`image`,`role`) VALUES ( ?,?, ?,?, ?,?,?)";

            ps = cnx.prepareStatement(yy);

            rs = ps.executeQuery();

            if (rs.next()) {
                showMessageDialog(null, "email deja existe");
            } else {
                String nom = txtnom.getText();
                String prenom = txtprenom.getText();
                String pass = txtpasseword.getText();
                String email = txtmail.getText();
                String tel = txtnum.getText();
                PreparedStatement x = cnx.prepareStatement(req);
                x.setString(1, nom);
                x.setString(2, prenom);
                x.setString(3, email);
                x.setString(4, getHash(pass.getBytes(), "SHA-1"));
                x.setString(5, tel);
                x.setString(6, String.valueOf(xxx));
                x.setString(7, s);

                //  fis = new FileInputStream(file);
                x.executeUpdate();
                System.out.println("PS : compte ajoutée avec succés!");

                showMessageDialog(null, "compte creer avec succes");
                txtnom.clear();
                txtmail.clear();
                txtprenom.clear();
                txtpasseword.clear();
                txtnum.clear();

               
            }
//        
        }

    }

    public static String getHash(byte[] inputBytes, String algorithme) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithme);
            messageDigest.update(inputBytes);
            byte[] digesteBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digesteBytes).toLowerCase();

        } catch (Exception e) {

        }
        return hashValue;
    }

    private boolean isValidated() {

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        String number = "[0-9]+";
        Pattern x = Pattern.compile(number);

        if (txtnom.getText().equals("")) {

            showMessageDialog(null, "nom text field cannot be blank.");
            txtnom.requestFocus();
        } else if (comborole.getSelectionModel().isSelected(-1)) {
            showMessageDialog(null, "role  must be selected");
            comborole.requestFocus();
        } else if (txtnum.getText().length() < 8 || txtnum.getText().length() > 8) {

            showMessageDialog(null, "Error , numero must be 8 number");
            txtnum.requestFocus();

        } else if (txtprenom.getText().equals("")) {

            showMessageDialog(null, "prenom text field cannot be blank. ");
            txtprenom.requestFocus();

        } else if (txtnum.getText().equals("")) {

            showMessageDialog(null, "numero text field cannot be blank.");
            txtnum.requestFocus();
        } else if (txtmail.getText().equals("")) {

            showMessageDialog(null, "email text field cannot be blank.");
            txtmail.requestFocus();
        } else if (!x.matcher(txtnum.getText()).matches()) {

            showMessageDialog(null, "numero contains only number ");
            txtnum.requestFocus();
        } else if (!pattern.matcher(txtmail.getText()).matches()) {

            showMessageDialog(null, "email invalid");
            txtmail.requestFocus();
        } else if (txtpasseword.getText().equals("")) {

            showMessageDialog(null, " password text field cannot be blank.");
            txtpasseword.requestFocus();

        } else {
            return true;
        }
        return false;
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        
        Stage stage = (Stage) txtmail.getScene().getWindow();
        stage.close();

        Parent root = FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));

        Scene scene = new Scene(root);

        stage.centerOnScreen();

        stage.setScene(scene);
        stage.setTitle("Login");
        //  stage.getIcons().add(new Image("/img/mm.png"));
        stage.show();
    }

    @FXML
    private void uploidimage(ActionEvent event) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter fd = new FileNameExtensionFilter("PNG JPG", "png", "jpg");
        chooser.addChoosableFileFilter(fd);

        int response = chooser.showOpenDialog(null); //select file to open
        //int response = fileChooser.showSaveDialog(null); //select file to save

        if (response == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            if (fd.accept(f)) {
                filename = f.getAbsolutePath();

                String newpath = "uploids/profile/";
                File dir = new File(newpath);
                if (!dir.exists()) {
                    // folder wa7dd ken barchaa mkdirs
                    dir.mkdirs();
                }
                File sourceFile = null;
                //  File destinationFile = null;
                String extension = filename.substring(filename.lastIndexOf('.') + 1);
                sourceFile = new File(filename);
                xxx = new File(newpath + randomStringforimage() + "." + extension);
                Files.copy(sourceFile.toPath(), xxx.toPath());
                //   System.out.println(destinationFile);
            } else {
                showMessageDialog(null, "invalid extension");
            }

        } else {
            showMessageDialog(null, "you must select photo");
        }
    }

    public static String randomStringforimage() {
        //   String  randomString  =null;
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 10;

        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }
        String randomString = sb.toString();

        return randomString;
    }
}
