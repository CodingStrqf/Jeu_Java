package view;

import Manager.GameManager;
import javafx.animation.TranslateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Personnage;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Duel implements Initializable {
    private int tempsBase = 1000;
    private int tempsMouv = tempsBase ;
    private int tempsDesac = tempsMouv*2;
    private long heureDesactivation;
    private long heureReactivation;

    private boolean fin = false;
    private volatile boolean attaqueJ = false;

    private Thread boucleJeu;

    @FXML
    public Label namebot;
    public Label namejoueur;
    public AnchorPane joueur;
    public AnchorPane robot;

    public Button attaquer;
    public Button defense;
    public Button x1;
    public Button x2;
    public Button x4;
    public Label txt;

    @FXML
    private ProgressBar viebot;
    @FXML
    private ProgressBar viejoueur;

    private Personnage bot = GameManager.getInstance().getAdversaire();
    private Personnage j = GameManager.getInstance().getJoueur();

    private int viemax;
    private int botmax;
    private boolean apreDef = false;

    private boolean auBot = false;

    /**
     * Thread qui met en place la boucle de jeux
     * 1er if : le joueur attaque
     * 2eme if : le bot fait une action def/attaque
     * 3eme if : reactive les boutons et la possibilite au joueur de jouer
     * Dans se thread que le duel se passe
     */
    private Runnable deplacementBoucle = new Thread( () -> {

        while (!fin) {

            if (attaqueJ) {
                j.Attaquer(bot);
                attaqueJ = false;
                heureDesactivation = System.nanoTime();
                heureReactivation = heureDesactivation + (tempsMouv * 2000000l);
                activationDesactivation(true);
                deplacementJoueur();
                auBot = true;
            }

            if (((System.nanoTime() >= heureDesactivation+ (tempsMouv * 2010000l)) && auBot )|| apreDef){        // le bot attaque au bout de 2seconde apres le joueur
                // sépare l'attaque et la défence
                int r;
                r = (int) Math.floor(Math.random() * (2 - 1 + 1)) + 1;
                if (r == 1) {
                    activationDesactivation(true);
                    heureReactivation = heureReactivation + (tempsMouv * 2000000l);
                    deplacementBot();
                    attaqueBot();
                }
                if (r == 2) {
                    defenceBot();

                }
                auBot = false;
                apreDef = false;
            }

            if (heureReactivation <= System.nanoTime()){
                activationDesactivation(false);
            }
        }
    }
    );

    /**
     * Initialise la page et les personnages
     * Intègre la vie du joueur et bot aux Listener pour le bind
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        j.reset();
        viemax = j.getVie();
        bot.reset();
        botmax = bot.getVie();
        viejoueur.setProgress(viemax);
        viebot.setProgress(botmax);
        namebot.setText(bot.getPseudo());
        namejoueur.setText(j.getPseudo());

        j.getVieInteger().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                viejoueur.setProgress((double)j.getVie() / viemax);
            }
        });

        bot.getVieInteger().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                viebot.setProgress((double)bot.getVie() / botmax);
            }
        });

        boucleJeu = new Thread(deplacementBoucle);
        boucleJeu.start();
    }

    /**
     * Affiche un message en popup
     * @param msg : message a afficher
     */
    public void popUp(String msg){
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, msg);
    }

    /**
     * Charge la page de choix de niveau après que le bot soit tombe a 0 HP
     * incremente la possibiliter de choisir un niveau supperieur
     * met fin a la boucle de jeux
     */
    private void victoire(){
        heureDesactivation = 0;
        auBot = false;
        popUp("Bravo tu as réussi ce niveau !!");
        GameManager.getInstance().setNiveauDebloque(GameManager.getInstance().getNiveauDebloque()+1);
        fin = true;
        try {
            GameManager.getInstance().ChoixNiveau();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge la page de choix de niveau après que le joueur soit tombe a 0 HP
     * met fin a la boucle de jeux
     */
    private void defaite(){
        fin = true;
        heureReactivation = 0;
        popUp("Dommage re tentes la prochaine fois !");
    }

    /**
     * Action du bouton attaquer du joueur
     * Baisse la vie du bot
     * Informe que le joueur attaque pour le thread de boucle de jeu
     * Donne la victoire au joueur si le bot a 0 de vie
     */
    public void attaquer(){
        attaqueJ = true;
        if(bot.getVie() == 0){
            j.Attaquer(bot);
            victoire();
        }
    }

    /**
     * Action du bouton defense du joueur
     * Augmente la vie du joueur
     * Informe que le joueur a fait son action pour le thread de boucle de jeu
     */
    public void defense() {
        if (j.getVie() != 0 && bot.getVie() != 0) {
            txt.setText(j.getPseudo() +" bois une potion");
            j.Defense();
        }
        heureDesactivation = System.nanoTime()-(tempsMouv * 200000l);
        apreDef = true;
    }

    /**
     * Le bot attaque
     * Baisse la vie du joueur
     * Donne la victoire au bot si le joueur a 0 de vie
     */
    private void attaqueBot(){
        bot.Attaquer(j);
        if(j.getVie() == 0){
            defaite();
        }
    }

    /**
     * Augmente la vie du bot
     */
    private void defenceBot(){
        tempsDesac = tempsMouv*2;
        bot.Defense();
    }

    /**
     * Deplace la vue du bot
     */
    private void deplacementBot(){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(robot);
        translate.setDuration(Duration.millis(tempsMouv));
        translate.setCycleCount(2);
        translate.setByX(600);
        translate.setAutoReverse(true);
        translate.play();
    }

    /**
     * Deplace la vue du joueur
     */
    private void deplacementJoueur(){
        //txt.setText(j.getPseudo() + " attaque de toute ces forces");
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(joueur);
        translate.setDuration(Duration.millis(tempsMouv));
        translate.setCycleCount(2);
        translate.setByX(-600);
        translate.setAutoReverse(true);
        translate.play();
    }

    /**
     * Active ou desactive les boutons grace au veux, ce qui permet d'attendre que le Personnage joueur et bot finissent leurs actions
     * @param veux
     */
    private void activationDesactivation(boolean veux){
        attaquer.setDisable(veux);
        defense.setDisable(veux);
        x1.setDisable(veux);
        x2.setDisable(veux);
        x4.setDisable(veux);
    }

    /**
     * Retablie la vitesse de jeux normale
     */
    public void x1() {
        tempsMouv = tempsBase ;
    }

    /**
     * Double la vitesse de jeux
     */
    public void x2() {
        tempsMouv = tempsBase ;
        tempsMouv = tempsMouv/2;
    }

    /**
     * Quadruple la vitesse de jeux
     */
    public void x4() {
        tempsMouv = tempsBase ;
        tempsMouv = tempsMouv/4;
    }

    public void abandonner(){
        try {
            GameManager.getInstance().ChoixNiveau();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}