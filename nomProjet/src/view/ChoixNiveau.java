/**
 *
 */
package view;

import Manager.GameManager;
import Persistance.RessourceManager;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Chimiste;
import model.Paladin;
import model.Personnage;
import model.Voleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChoixNiveau implements Initializable {

    public Button Bniv2;
    public Button Bniv3;
    public Button Bniv1;
    public Button BmenuPrincipale;
    public TextField Pseudo;

    private Personnage m;

    /**
     * Fonction d'initialisation de la page,
     * Active les boutons suivant les niveaux debloque
     * Bind bidirectionnellement le pseudo du joueur pour pouvoir le voir et en meme temps le changer
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pseudo.textProperty().bindBidirectional(GameManager.getInstance().getJoueur().getPseudoP());
        int niv = GameManager.getInstance().getNiveauDebloque();
        if ( niv >= 3){
            Bniv2.setDisable(false);
            Bniv3.setDisable(false);
        }else {
            if (niv == 2)
                Bniv2.setDisable(false);
        }
    }

    /**
     * Retour à l'acceuil
     */
    public void menuPrincipale() {
        try{
            GameManager.getInstance().Accueil();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Charge la page de duel
     * Avec un adversaire propre a ce niveau
     */
    public void niv1() {
        m=new Paladin("Lancelot");
        try{
            GameManager.getInstance().setAdversaire(m);
            GameManager.getInstance().Duel();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Charge la page de duel
     * Avec un adversaire propre a ce niveau
     */
    public void niv2(){
        m=new Voleur("Robin des bois");
        try{
            GameManager.getInstance().setAdversaire(m);
            GameManager.getInstance().Duel();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Charge la page de duel
     * Avec un adversaire propre a ce niveau
     */
    public void niv3(){
        m=new Chimiste("Cyanure");
        try{
            GameManager.getInstance().setAdversaire(m);
            GameManager.getInstance().Duel();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Sauvegarde le(s) niveau(x) debloque(s)
     * @throws Exception
     */
    public void btnSave() throws Exception {
        int niv = GameManager.getInstance().getNiveauDebloque();
        RessourceManager.save(niv,"save");
    }
}
