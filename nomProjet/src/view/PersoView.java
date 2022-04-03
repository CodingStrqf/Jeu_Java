package view;

import Manager.GameManager;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Personnage;

import java.net.URL;
import java.util.ResourceBundle;

public class PersoView implements Initializable {

    public ImageView jImageView;

    private Personnage joueur = GameManager.getInstance().getJoueur();
    private Image jImage= new Image("Images/"+joueur.getImage());

    /**
     * Charge l'image du joueur
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jImageView.setImage(jImage);
    }

}

