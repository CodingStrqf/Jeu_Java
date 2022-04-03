package launch;

import Manager.GameManager;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Classe permettant de build l'application
 */
public class Launch extends Application {

    /**
     * Initialise la vue
     * @param stage
     */
    public void start(Stage stage){
        GameManager.getInstance();
        try {
            GameManager.getInstance().Accueil();
            GameManager.getInstance().getStage().show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Lance l'application
     * @param args
     */
    public static void main(String[] args){
        launch(args);
    }
}