package Manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Personnage;

import java.io.IOException;
import java.util.Objects;

/**
 * Classe gerant les instances de l'application
 */
public class GameManager {
    private static GameManager instance;
    private Stage stage;
    private Personnage joueur;
    private Personnage adversaire;
    private int niveauDebloque = 1;

    private Scene acceuil = null;
    private Scene choix = null;
    private Scene niv = null;
    private Scene duel = null;

    /**
     * Instance pour faire le patron singleton
     * @return l'unique instance du GameManager
     */
    public static GameManager getInstance() {
        if (instance == null){
            instance = new GameManager();
        }
        return instance;
    }

    public void setStage(Stage stage) {this.stage = stage;}
    public void setJoueur(Personnage joueur) { this.joueur = joueur;}
    public void setAdversaire(Personnage adversaire){ this.adversaire = adversaire;}
    public void setNiveauDebloque(int niveauDebloque) {this.niveauDebloque = niveauDebloque;}

    public Stage getStage() {return stage;}
    public Personnage getJoueur() {return joueur;}
    public Personnage getAdversaire(){ return adversaire;}
    public Scene getNiv() {return niv;}
    public int getNiveauDebloque(){return niveauDebloque;}

    /**
     * Cree une instance de la page d'Acceuil et la sauvegarde OU utilise la sauvegarde deja faite
     * @throws IOException
     */
    public void Accueil()throws IOException{
        if(acceuil == null){
            Parent parentAcceuil = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Accueil.fxml")));
            acceuil = new Scene(parentAcceuil, 1000, 600);
            acceuil.getStylesheets().add("style.css");
            stage = new Stage();
            stage.getIcons().add(new Image("/Images/icon.jpg"));
            stage.setResizable(false);
            stage.setScene(acceuil);
        }else{
            this.getStage().setScene(acceuil);
        }
    }

    /**
     * Cree une instance de la page de choix de personnage et la sauvegarde OU utilise la sauvegarde deja faite
     * @throws IOException
     */
    public void ChoixPersonnage()throws IOException {
        if(choix == null){
            Parent parentChoix = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("ChoixPerso.fxml")));
            choix = new Scene(parentChoix,1000,600);
            choix.getStylesheets().add("style.css");
            GameManager.getInstance().getStage().setScene(choix);
        }else{
            this.getStage().setScene(choix);
        }
    }

    /**
     * Cree une instance de la page de choix de niveau mais ne la sauvegarde pas car il y a besoins de l'update
     * @throws IOException
     */
    public void ChoixNiveau()throws  IOException{
            Parent parentNiv = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("ChoixNiveau.fxml")));
            niv = new Scene(parentNiv);
            niv.getStylesheets().add("style.css");
            GameManager.getInstance().getStage().setScene(niv);
    }

    /**
     * Cree une instance de la page de duel  mais ne la sauvegarde pas car les Personnage y participant doivent etre comme neuf a chaque fois
     * @throws IOException
     */
    public void Duel() throws  IOException{
            Parent parentDuel = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Duel.fxml")));
            duel = new Scene(parentDuel);
            duel.getStylesheets().add("style.css");
            GameManager.getInstance().getStage().setScene(duel);
    }
}
