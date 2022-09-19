/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Panier;

import Base.Bd;
import Base.Statics;
import Model.Panier;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
import sprint1.Run;

/**
 * FXML Controller class
 *
 * @author Ce Pc
 */
public class PanierController implements Initializable {
    
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
    private Button btnupdate11;
    @FXML
    private Spinner<Integer> number;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnupdate1;
    @FXML
    private Button btndelete;
    @FXML
    private Label showstock;
    @FXML
    private Button addcart;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtidproduit;
    @FXML
    private TextField quantite;
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
    private List<Panier> e = new ArrayList<>();
     private Mypanier myListener;

    /**
     * Initializes the controller class.
     */
    private List<Panier> getData() throws SQLException {
        List<Panier> e = new ArrayList<>();
        Panier panier;
        String tt = "SELECT * FROM `panier`  where id_user ='" + Statics.current_user.getId_user() + "'";
        
        Statement statement;
        
        statement = cnx.createStatement();
        ResultSet queryoutput = statement.executeQuery(tt);
        while (queryoutput.next()) {
            panier = new Panier();
            panier.setNom_event(queryoutput.getString("nom_event"));
            panier.setPrix(Integer.parseInt(queryoutput.getString("prix")));
            panier.setDate(queryoutput.getString("date"));
            panier.setId(Integer.parseInt(queryoutput.getString("id")));
            panier.setStock_ticket(Integer.parseInt(queryoutput.getString("stock_ticket")));
            panier.setDescription(queryoutput.getString("description"));
            panier.setId_event(Integer.parseInt(queryoutput.getString("id_event")));
            panier.setLieux_event(queryoutput.getString("lieux_event"));
            panier.setType_event(queryoutput.getString("type_event"));
            panier.setId_categorie(Integer.valueOf(queryoutput.getString("id_categorie")));
            e.add(panier);
            
        }
        
        return e;
    }
    
    private void setChosenFruit(Panier p) {
        fruitNameLable.setText(p.getNom_event());
        fruitPriceLabel.setText(p.getPrix() + Run.CURRENCY);
        quantite.setText(String.valueOf(p.getPrix()));
        String path;
//        txtimage.setText(fruit.getImage());
        //  txtarea.setTextAlignment(TextAlignment.valueOf(fruit.getDescription()));
        txtarea.getChildren().clear();
        Text t1 = new Text("Description : " + p.getDescription());
        txtarea.getChildren().add(t1);
        
        txtidproduit.setText(String.valueOf(p.getId_event()));
//        idproduit.setText(String.valueOf(fruit.getId()));
        txtid.setText(String.valueOf(p.getId()));
        txtcat.setText(String.valueOf(p.getId_categorie()));
        nomproduit.setText(p.getNom_event());
        txtdes.setText(p.getDescription());
        prix.setText(String.valueOf(p.getPrix()));

//        path = fruit.getImage();
//        Image aa = new Image("file:" + path);
//        // System.out.println(image);
//        fruitImg.setImage(aa);
 String xx = "B0E0E6";
        chosenFruitCard.setStyle("-fx-background-color: #" +xx + ";\n"
                + "    -fx-background-radius: 30;");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            e.addAll(getData());
            if (e.size() > 0) {
                setChosenFruit(e.get(0));

                myListener = this::setChosenFruit;
                int column = 0;
                int row = 1;
                try {
                    for (int i = 0; i < e.size(); i++) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("../Panier/itemController.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();

                        ItemControllerController aa = fxmlLoader.getController();
                        aa.setData(e.get(i), myListener);

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
            } else {

            }

        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
          try {
            totalpanier.setText(String.valueOf(refreshpanier()));
            System.out.println(refreshpanier());
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }

            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
        valueFactory.setValue(1);
        number.setValueFactory(valueFactory);

        
    }
    
    @FXML
    private void rechercherproduit(ActionEvent event) {
    }
    
    @FXML
    private void updateproduit(ActionEvent event) throws SQLException, IOException {
        
          PreparedStatement ps, ps2, ps3, ps4;
        ResultSet rs, rs2 = null;

        String y = "select * from panier WHERE id = ?";
        ps4 = cnx.prepareStatement(y);
        ps4.setString(1, txtid.getText());
        rs2 = ps4.executeQuery();
        while (rs2.next()) {
            Integer s1 = rs2.getInt("stock_ticket");
            Integer x = number.getValue() + s1;
            Integer yy = number.getValue();
            String query = "select * from evenement WHERE id_event = ?";

            ps = cnx.prepareStatement(query);
            ps.setString(1, txtidproduit.getText());

            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("stock_tiket") >= yy) {
                    System.out.println("update succes");
                    String xx = "update evenement set stock_tiket = stock_tiket - ? where id_event =? ";
                    ps2 = cnx.prepareStatement(xx);
                    ps2.setInt(1, yy);
                    ps2.setString(2, txtidproduit.getText());
                    ps2.execute();
                    String xxx = "update panier set stock_ticket =?  , prix=? * ?  where id =? and id_user =?";
                    ps3 = cnx.prepareStatement(xxx);
                    ps3.setInt(1, x);
                    ps3.setInt(2, x);
                    ps3.setString(3, quantite.getText());
                    ps3.setString(4, txtid.getText());
                    ps3.setInt(5, Statics.current_user.getId_user());
                    ps3.execute();

                    totalpanier.setText(String.valueOf(refreshpanier()));
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

//        
//                    try {
//                        totalprix.setText(String.valueOf(totalprix()));
//                    } catch (SQLException ex) {
//                        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    refreshtableproduit();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Produit");

                    alert.setContentText("produit hors stock ");
                    if (alert.showAndWait().get() == ButtonType.OK) {
                        System.out.println("produit hors stock");
                    }

                }
            }
        }

        
    }
    
    @FXML
    private void deleteproduit(ActionEvent event) throws SQLException, IOException {
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(" produit");

        alert.setContentText("delete ");
        if (alert.showAndWait().get() == ButtonType.OK) {

            PreparedStatement ps2, ps3, ps4;
            ResultSet rs, rs2 = null;
            String y = "select * from panier WHERE id = ?";

            ps4 = cnx.prepareStatement(y);
            ps4.setString(1, txtid.getText());
            rs2 = ps4.executeQuery();
            while (rs2.next()) {
                Integer s1 = rs2.getInt("stock_ticket");
                System.out.println(s1);

                String xxx = "update evenement set stock_tiket =stock_tiket+? where id_event =? ";

                ps2 = cnx.prepareStatement(xxx);
                ps2.setInt(1, s1);

                ps2.setString(2, txtidproduit.getText());
                ps2.execute();

            }

            PreparedStatement ps;
            //   ResultSet rs;
            String id = txtid.getText();

            String yy = "delete   from  panier where id = '" + id + "' ";
            ps = cnx.prepareStatement(yy);
            ps.execute();

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

//        
            totalpanier.setText(String.valueOf(refreshpanier()));
//            try {
//                if (refreshpanier() == 0) {
//                    btnpaiment.setVisible(false);
//                    labeltotalprix.setVisible(false);
//                    totalprix.setVisible(false);
//                    labelcoupon.setVisible(false);
//                    combocoupoin.setVisible(false);
//                    btnpaiment.setVisible(false);
//                    btnajoutercoupon.setVisible(false);
//                    labelpaniervide.setVisible(true);
//
//                    acheterproduit.setVisible(true);
//
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
//            }

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
    private void ajouterpanier(ActionEvent event) {
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
    
}
