package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Personnage {

    private StringProperty pseudo;
    private IntegerProperty vie;
    private int def;
    private int attaque;
    private String description;
    private String image;

    public Personnage(String pseudo, int vie, int def, int attaque, String description,String photo) {
        this.pseudo = new SimpleStringProperty();
        this.pseudo.setValue(pseudo);
        this.vie = new SimpleIntegerProperty();
        this.vie.setValue(vie);
        this.def = def;
        this.attaque = attaque;
        this.description = description;
        this.image = photo;
    }

    /**
     * Detaille toutes ses statistiques
     * @return String : Une phrase contenant tout
     */
    @Override
    public String toString() {
        return dial() + "\n" +
                "\t pseudo :"+this.getPseudo()+"\n"+
                "\t vie : "+vie+"\n"+
                "\t def : "+def+"\n"+
                "\t attaque : "+attaque+"\n"+
                "\t type : "+this.getClass().getSimpleName()+"\n"+
                "\t description : "+description;
    }

    /**
     * utilise pour les dialogues
     * @return : le pseudo suivit d'un tab
     */
    public String dial(){
        return this.getPseudo()+" : \t";
    }

    /**
     * Fait parler le parsonnage
     * @param s : message
     */
    public void parler(String s){
        System.out.println(dial()+s);
    }

    /**
     * Permet d'ajouter la classe du personnage au dialogue
     * @return
     */
    public String interpelation(){
        return  getClass().getSimpleName() + " "+this.getPseudo();
    }

    /**
     * Utilise lorsque un personnage en attaque un autre
     * @param p : victime de l'attaque
     */
    public void Attaquer(Personnage p){
        BaisseDeVie(this.getAttaque(), p);
        AttaquerParole(p);

    }

    /**
     * Fait une phrase pour dire que le personnage attaque
     * @param p
     */
    public void AttaquerParole(Personnage p){
        System.out.println(dial()+"HAAAA non je t'en supplie "+p.interpelation()+" je n'ai pas appris a me battre !!!");
    }

    /**
     * Baisse la vie de la victime avec les degats
     * @param degats : degats a infliger
     * @param victime : reçoit l'attaque
     */
    public final void BaisseDeVie(int degats, Personnage victime){
        if (victime.getVie() >= degats){
            victime.setVie(victime.getVie() - degats);
        }else{
            victime.setVie(0);
        }
    }

    /**
     * A redefinire pour mettre la defence voulu
     */
    public void Defense(){
        System.out.println("Je me ravitaille");
    }
    public int getAttaque() {
        return this.attaque;
    }
    public String getDescription() {
        return description;
    }
    public String getImage(){return image;}
    public String getPseudo(){return pseudo.getValue();}
    public StringProperty getPseudoP(){return this.pseudo;}

    public int getVie(){
        return this.vie.getValue();
    }

    /**
     * On final pour ne pas laisser la possibiliter de surcharger
     * @return IntegerProperty : sert a bind dessus
     */
    public final IntegerProperty getVieInteger(){return this.vie;}
    public void setVie(int vie) {this.vie.setValue(vie);}
    public void reset(){ System.out.println("je peux rien faire");}
}
