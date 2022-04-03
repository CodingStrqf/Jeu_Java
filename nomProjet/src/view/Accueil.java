package view;

import Manager.GameManager;
import Persistance.RessourceManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Accueil {

    @FXML
    public Button Bexit;
    public Button Bcontinuer;
    public Button Bnouvelle;

    /**
     * Sort du jeux, et arrete les thread qui tournes en trop et qui ne s'arrete pas sans cela
     */
    @FXML
    public void exit(){
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        for(Thread t : threadSet){
            t.interrupt();
        }
        GameManager.getInstance().getStage().close();
    }

    /**
     * Ouvre la page du choix des personnages
     * @throws IOException
     */
    public void nouvellePartie() throws IOException {
        GameManager.getInstance().ChoixPersonnage();
    }

    /**
     * Ouvre la partie que l'on a precedement cree
     * @throws IOException
     */
    public void continuerPartie() throws Exception {
        File f =  new File("save");
        if(f.exists()) {
            int data = (int) RessourceManager.load("save");
            GameManager.getInstance().setNiveauDebloque(data);
            GameManager.getInstance().ChoixPersonnage();
        }else{
            JFrame jFrame = new JFrame();
            JOptionPane.showMessageDialog(jFrame, "Aucune partie");
        }
    }
}