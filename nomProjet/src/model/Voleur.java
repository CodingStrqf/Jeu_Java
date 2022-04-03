package model;
import java.util.Random;

public class Voleur extends Personnage{

    private int chance;
    public Voleur(String pseudo) {
        super(pseudo, 100, 20, 80, "Un vil parmis les vils.\n100 de vie,20 de defense,50 d'attaque","voleur.jpg");
        chance = 50;
    }

    /**
     * Reduit la vie du personnage p
     * Suivant un nombre au hazard le vole de la vie ou rate
     * @param p : Le personnage adverse
     */
    @Override
    public void AttaquerParole(Personnage p) {
        int r = getRand(0,100);
        if(r >chance){
            parler(p.interpelation()+" voici ma lame rouillé préféré");
            if (getVie() < 100){
                setVie((getVie()+20)%100);
                parler("et voilà mon dut !");
            }
        }else {
            parler("mince j'aurais pas du essayer de voler "+p.interpelation()+" en meme temps");
        }

    }

    /**
     *
     * @param min : valeur minimale
     * @param max : valeur maximale
     * @return int : un nombre au hazard
     */
    public int getRand(int min, int max){
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();
    }

    /**
     * La vie remonte sans depasser son maximum de vie
     */
    @Override
    public void Defense(){
        if(this.getVie()>80){
            this.setVie(100);
        }
        else{
            this.setVie(this.getVie() + 20);
            parler("J'utilise une potion de soin");
        }
    }

    /**
     * Reinitialise la vie au maximum
     */
    @Override
    public void reset(){
        this.setVie(100);
    }
}
