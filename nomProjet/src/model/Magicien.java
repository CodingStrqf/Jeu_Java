package model;

public class Magicien extends Personnage{

    public Magicien(String pseudo) {
        super(pseudo, 100, 20, 30,"Abracadabra.\n100 de vie, 20 de def, 30 d'attaque","mage.png");

    }

    /**
     * Parle lors de l'attaque
     * @param p : Le personnage adverse
     */
    @Override
    public void AttaquerParole(Personnage p) {
        parler(p.interpelation()+" tu vas gouter a mon sort le plus puissant");
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
            this.setVie(this.getVie() + 5);
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
