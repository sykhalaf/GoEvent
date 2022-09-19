/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Categorie_event;

import Base.Bd;
import Base.Statics;
import Model.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;
import sprint1.Run;

/**
 * FXML Controller class
 *
 * @author Ce Pc
 */
public class CategorieController implements Initializable {

    @FXML
    private ImageView logo;
    @FXML
    private TextField recherche;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private TextFlow txtarea;
    @FXML
    private Button enstock;
    @FXML
    private Button horsstock;
    @FXML
    private Label showstock;
    @FXML
    private Button addcart;
    @FXML
    private TextField quantite;
    @FXML
    private TextField idproduit;
    @FXML
    private TextField txtimage;
    @FXML
    private TextField txtcat;
    @FXML
    private TextField nomproduit;
    @FXML
    private TextField txtdes;
    @FXML
    private TextField prix;
    @FXML
    private ImageView img;
    @FXML
    private Button totalpanier;
    @FXML
    private Button register;
    @FXML
    private Button login;
    @FXML
    private Label labelnom;
    @FXML
    private Button btnSignout;
    @FXML
    private ImageView image;
    @FXML
    private Button btnAboutus;
    @FXML
    private Button btnContact;
    @FXML
    private Button idcat;
    @FXML
    private HBox avishbox;
    @FXML
    private Button avis;
    @FXML
    private HBox profilehbox;
    @FXML
    private Button btnprofile;
    @FXML
    private HBox histbox;
    @FXML
    private Button historique;
    @FXML
    private HBox quizhbox;
    @FXML
    private Button quiz;
    @FXML
    private HBox adresshbox;
    @FXML
    private Button btnadresse;
    @FXML
    private Button btnAccueil;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    Connection cnx = Bd.getInstance().getCnx();
    private List<Event> e = new ArrayList<>();
    private MyListener myListener;

    /**
     * Initializes the controller class.
     */
    private List<Event> getData() throws SQLException {
        List<Event> e = new ArrayList<>();
        Event event;
        String tt = "SELECT * FROM `evenement`";

        Statement statement;

        statement = cnx.createStatement();
        ResultSet queryoutput = statement.executeQuery(tt);
        while (queryoutput.next()) {
            event = new Event();
            event.setNom_event(queryoutput.getString("nom_event"));
            event.setPrix(Integer.parseInt(queryoutput.getString("prix")));
            event.setDate(queryoutput.getString("date"));

            event.setStock_ticket(Integer.parseInt(queryoutput.getString("stock_tiket")));
            event.setDescription(queryoutput.getString("description"));
            event.setId_event(Integer.parseInt(queryoutput.getString("id_event")));
            event.setLieux_event(queryoutput.getString("lieux_event"));
            event.setType_event(queryoutput.getString("type_event"));
            event.setId_categorie(Integer.valueOf(queryoutput.getString("id_categorie")));

            e.add(event);

        }

        return e;
    }

    private void setChosenFruit(Event e) {
        fruitNameLable.setText(e.getNom_event());
        fruitPriceLabel.setText(e.getPrix() + Run.CURRENCY);
        quantite.setText(String.valueOf(e.getStock_ticket()));
        String path;
        // txtimage.setText(fruit.getImage());
        //  txtarea.setTextAlignment(TextAlignment.valueOf(fruit.getDescription()));
        txtarea.getChildren().clear();
        Text t1 = new Text("Description : " + e.getDescription());
        txtarea.getChildren().add(t1);

        idproduit.setText(String.valueOf(e.getId_event()));
        txtcat.setText(String.valueOf(e.getId_categorie()));
        nomproduit.setText(e.getNom_event());
        txtdes.setText(e.getDescription());
        prix.setText(String.valueOf(e.getPrix()));
        //  System.out.println(quantite.getText());
        if (quantite.getText().equals("0")) {
            horsstock.setVisible(true);

            enstock.setVisible(false);
            addcart.setVisible(false);
            showstock.setVisible(false);
            //    ajouterpanierr.setVisible(false);
        } else {
            enstock.setVisible(true);

            addcart.setVisible(true);
            showstock.setVisible(true);
            showstock.setText("il vous reste " + quantite.getText() + " Event");
            horsstock.setVisible(false);

            //   ajouterpanierr.setVisible(true);
        }
        //   this.img.setImage(image);
//        path = fruit.getImage();
//        Image aa = new Image("file:" + path);
        // System.out.println(image);
        //   fruitImg.setImage(aa);
        String xx = "B0E0E6";
        chosenFruitCard.setStyle("-fx-background-color: #" + xx + ";\n"
                + "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            e.addAll(getData());
            if (e.size() > 0) {
                setChosenFruit(e.get(0));

                myListener = this::setChosenFruit;

            }
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < e.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../Categorie_event/ItemController.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(e.get(i), myListener);

                    if (column == 3) {
                        column = 0;
                        row++;
                    }

                    grid.add(anchorPane, column++, row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            totalpanier.setText(String.valueOf(refreshpanier()));
            System.out.println(refreshpanier());
        } catch (SQLException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Integer refreshpanier() throws SQLException {

        int x = 0;
        Statement stmt = cnx.createStatement();
        String query = "select SUM(stock_ticket) from panier where id_user='" + Statics.current_user.getId_user() + "'";

        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        x = rs.getInt(1);
        return x;

    }

    @FXML
    private void rechercherproduit(ActionEvent event) {
    }

    @FXML
    private void ajouterpanier(ActionEvent event) throws IOException, SQLException {
        if (Statics.current_user.getNom() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("login");
            alert.setHeaderText("login required!");
            alert.setContentText("you must login  ");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Stage stage = (Stage) register.getScene().getWindow();
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("../Login/Login.fxml"));

                Scene scene = new Scene(root);
                stage.setMaxHeight(500);

                stage.setMaxWidth(600);
                stage.setScene(scene);
                stage.setResizable(true);

                stage.setTitle("Login");
                //
                stage.show();

            }
        } else {
            PreparedStatement ps, psx;
            ResultSet rs, rsx;
            String id_user = String.valueOf(Statics.current_user.getId_user());
            System.out.println(Statics.current_user.getId_user());
            String produit = nomproduit.getText();
            String desc = txtdes.getText();
            String prix = this.prix.getText();
            // String image = txtimage.getText();
            String id_produit = idproduit.getText();
            Integer quantiter = 1;
            String categorie = txtcat.getText();
            //  String xx = id.getText();
            // String yy = "delete   from  categories where name = '" + nom + "' ";
            String yy = "INSERT INTO panier(nom_event,prix,description,stock_ticket,id_user,id_event,id_categorie) VALUES ( ?,?,?,?,?,?,?)";

            // String req = "INSERT INTO `categories`(`name`) VALUES ( ?)";
            String yyy = "SELECT * FROM panier WHERE id_user ='" + id_user + "'  and  nom_event='" + produit + "'  ";
            psx = cnx.prepareStatement(yyy);

            rsx = psx.executeQuery();
            if (rsx.next()) {

                showMessageDialog(null, "produit deja ajouter au paneir");
            } else {

                ps = cnx.prepareStatement(yy);
                ps.setString(1, produit);

                ps.setString(2, prix);
                ps.setString(3, desc);

                ps.setInt(4, quantiter);

                ps.setString(5, id_user);
                ps.setString(6, id_produit);
                ps.setString(7, categorie);
                System.out.println(id_produit);
                ps.execute();

                PreparedStatement ps4, ps5;
                ResultSet rs5;

                String xxx = "update evenement set stock_tiket =stock_tiket-? where id_event =? ";

                ps5 = cnx.prepareStatement(xxx);
                ps5.setInt(1, 1);

                ps5.setString(2, id_produit);
                System.out.println(idproduit.getText());
                ps5.execute();

                showMessageDialog(null, "produit  ajouter avec succes au panier");
                try {
                    totalpanier.setText(String.valueOf(refreshpanier()));
                    System.out.println(refreshpanier());
                } catch (SQLException ex) {
                    Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                }
//                Stage stage = (Stage) register.getScene().getWindow();
//                stage.close();
//
//                Parent root = FXMLLoader.load(getClass().getResource("../../gui/Categorie_Client/Categorie_Client.fxml"));
//
//                Scene scene = new Scene(root);
//                stage.setMaxHeight(1000);
//                stage.setMaxWidth(1500);
//                stage.setScene(scene);
//                stage.setResizable(true);
//
//                stage.setTitle("Login");
//                //
//                stage.show();
            }
        }

    }

    @FXML
    private void showpanier(MouseEvent event) {
    }

    @FXML
    private void totalpanier(ActionEvent event) {
    }

    @FXML
    private void register(ActionEvent event) {
    }

    @FXML
    private void login(ActionEvent event) {
    }

    @FXML
    private void btnSignout(ActionEvent event) {
    }

    @FXML
    private void about(ActionEvent event) {
    }

    @FXML
    private void contact(ActionEvent event) {
    }

    @FXML
    private void categorie(ActionEvent event) {
    }

    @FXML
    private void avis(ActionEvent event) {
    }

    @FXML
    private void Profile(ActionEvent event) {
    }

    @FXML
    private void Historique(ActionEvent event) {
    }

    @FXML
    private void quiz(ActionEvent event) {
    }

    @FXML
    private void adresse(ActionEvent event) {
    }

    @FXML
    private void Accueil(ActionEvent event) {
    }

    @FXML
    private void gotopanier(MouseEvent event) throws IOException {
        
            Stage stage = (Stage) register.getScene().getWindow();
                stage.close();

                Parent root = FXMLLoader.load(getClass().getResource("../Panier/Panier.fxml"));

                Scene scene = new Scene(root);
                stage.setMaxHeight(1000);
                stage.setMaxWidth(1500);
                stage.setScene(scene);
                stage.setResizable(true);

                stage.setTitle("Login");
                //
                stage.show();
            }
    

}
