/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Panier;

import Model.Panier;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sprint1.Run;

/**
 * FXML Controller class
 *
 * @author Elife-Kef-108
 */
public class ItemControllerController {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    @FXML
    private Label quantite;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(p);
    }

    private Panier p;
    private Mypanier myListener;

    public void setData(Panier p, Mypanier myListener) {
        this.p = p;
        this.myListener = myListener;
        nameLabel.setText(p.getNom_event());
        priceLable.setText(p.getPrix() + "TND");
        quantite.setText(String.valueOf(p.getStock_ticket()));
//        String path = fruit.getImage();
//        Image aa = new Image("file:" + path);
//
//        img.setImage(aa);
    }

}
