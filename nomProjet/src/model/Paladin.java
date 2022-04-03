package model;

public class Paladin extends Personnage{

    public Paladin(String pseudo) {
        super(pseudo, 200, 100, 80,"Gaillard toujours prêt à servir.\n200 de vie,100 de defense,20 d'attaque","paladin.png");
    }

    /**
     * Parle lors de l'attaque
     * @param p : Le personnage adverse
     */
    @Override
    public void AttaquerParole(Personnage p) {
        System.out.println(dial()+"J'attaque avec toute ma force "+p.interpelation()+" !!!");
    }

    /**
     * La vie remonte sans depasser son maximum de vie
     */
    @Override
    public void Defense(){
        if(this.getVie()>180){
            this.setVie(200);
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
        this.setVie(200);
    }
}
