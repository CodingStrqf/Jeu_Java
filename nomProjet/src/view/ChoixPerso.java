package view;


import Manager.GameManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class ChoixPerso {

    public ImageView imagePerso;
    @FXML
    private Label description;
    @FXML
    private TextField pseudo;

    @FXML
    public Button Bchimiste;
    public Button Bmagicien;
    public Button Bpaladin;
    public Button Bvoleur;
    public Button Bvalider;

    private String path = "Images/";
    private Personnage p;

    /**
     * Affiche un message en popup
     * @param msg : message a afficher
     */
    public void popUp(String msg){
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, msg);
    }

    /**
     * Verifie si le pseudo du joueur est renseigne
     * Il faut renseigne le pseudo avant de choisir un personnage
     * @return true : si il est renseigne, false : si il n'est pas renseigne
     */
    public boolean verificationPseudo(){
        String name = pseudo.getText();
        if(name.isEmpty()) {
            popUp("Le pseudo doit etre choisi");
            return false;
        }
        return true;
    }

    /**
     * Affiche l'image et la description suivant le personnage choisit
     * @param p : personnage choisit
     */
    public void Afficher(Personnage p){
        description.setText(p.getDescription());
        imagePerso.setImage(new Image(path+p.getImage()));
        //
    }

    /**
     * Action du bouton pour afficher le Personnage chimiste
     * et le cree
     */
    public void chimiste(){
        if (verificationPseudo()) {
            p =new Chimiste(pseudo.getText());
            Afficher(p);
        }
    }

    /**
     * Action du bouton pour afficher le Personnage magicien
     * et le cree
     */
    public void magicien(){
        if (verificationPseudo()) {
            p = new Magicien(pseudo.getText());
            Afficher(p);
        }
    }

    /**
     * Action du bouton pour afficher le Personnage paladin
     * et le cree
     */
    public void paladin(){
        if (verificationPseudo()) {
            p = new Paladin(pseudo.getText());
            Afficher(p);
        }
    }

    /**
     * Action du bouton pour afficher le Personnage voleur
     * et le cree
     */
    public void voleur(){
        if (verificationPseudo()) {
            p = new Voleur(pseudo.getText());
            Afficher(p);
        }
    }

    /**
     * Verifie si on a choisit un personnage puis le sauvegarde en temps que joueur et charge la page de choix de niveau.
     * Sinon utilise popUp
     * @throws IOException
     */
    public void valider() throws IOException {
        if (Objects.isNull(p)){
            popUp("Veuillez choisir un personnage");
        }else{
            GameManager.getInstance().setJoueur(p);
            GameManager.getInstance().ChoixNiveau();
        }
    }

    /**
     * Charge la page d'acceuil
     */
    public void retour() {
        try{
            GameManager.getInstance().Accueil();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}