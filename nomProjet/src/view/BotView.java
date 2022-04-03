package view;

import Manager.GameManager;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Personnage;

import java.net.URL;
import java.util.ResourceBundle;

public class BotView implements Initializable {

    public ImageView bImageView;

    private Personnage adversaire = GameManager.getInstance().getAdversaire();
    private Image jImage= new Image("Images/"+adversaire.getImage());

    /**
     * Charge l'image du bot
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bImageView.setImage(jImage);
    }

}
