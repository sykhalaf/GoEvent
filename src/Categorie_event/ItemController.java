package Categorie_event;

import Model.Event;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sprint1.Run;

public class ItemController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(e);
    }

    private Event e;
    private MyListener myListener;

    public void setData(Event e, MyListener myListener) {
        this.e = e;
        this.myListener = myListener;
        nameLabel.setText(e.getNom_event());
        priceLable.setText(e.getPrix() + "TND");

    }
}
