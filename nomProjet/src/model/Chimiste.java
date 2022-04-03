package model;

public class Chimiste extends Personnage{

    public Chimiste(String pseudo) {
        super(pseudo, 100, 20, 20,"Prêt à vous preparer des délicieux cocktail.\n100 de vie, 20 de defense, 20 d'attaque","chimiste.jpg");
    }

    /**
     * Parle lors de l'attaque
     * @param p : Le personnage adverse
     */
    @Override
    public void AttaquerParole(Personnage p) {
        parler("Voila un bon poison pour toi "+p.interpelation());
    }

    /**
     * La vie remonte sans depasser son maximum de vie
     */
    @Override
    public void Defense(){
        if(this.getVie()>80){
            this.setVie(100);
        }
        else {
            this.setVie(this.getVie() + 20);
        }
        parler("J'utilise une potion de soin");
    }

    /**
     * Reinitialise la vie au maximum
     */
    @Override
    public void reset(){
        this.setVie(100);
    }
}
