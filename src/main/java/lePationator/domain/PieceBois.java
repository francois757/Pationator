package lePationator.domain;

import java.awt.Color;

/**
 *
 * @author Douch Ayoub
 */
public class PieceBois implements java.io.Serializable{

    public Rectangle getRectangleVuePlan() {
        return rectangleVuePlan;
    }

    public Rectangle getRectangleVueFace() {
        return rectangleVueFace;
    }

    public Rectangle getRectangleVueCote() {
        return rectangleVueCote;
    }
    public PieceBois(float posXYZ[][], float epaisseur, float largeur, float longueur, NomPiece nomPiece) {
        this.epaisseur = epaisseur;
        this.largeur = largeur;
        this.longueur = longueur;
        this.nomPiece = nomPiece;
        this.posXYZ = posXYZ;
        rectangleVuePlan = new Rectangle(this, Vue.PLAN);
        rectangleVueFace = new Rectangle(this, Vue.FACE);
        rectangleVueCote = new Rectangle(this, Vue.COTE);
    }
    private float epaisseur;
    private float largeur;
    private float longueur;
    private float nbPlie = 1;
    private NomPiece nomPiece;
    private float[][] posXYZ;
    private Rectangle rectangleVuePlan;
    private Rectangle rectangleVueFace;
    private Rectangle rectangleVueCote;
    private float longueurPlanche;
    
    
    public float getLongueurReelle(){
        switch(nomPiece){
            case PLANCHE :
                return longueurPlanche;
            case PLANCHECOTEE:
                return longueurPlanche;
            default:
                return (float)longueur;
        }
    }
    
    public void majPositionXYZ(float[][] posXYZ){
       
    }
    
    public String getDescriptionPiece(){
        return null;
    }

    public float getEpaisseur() {
        return epaisseur;
    }

    public void setEpaisseur(float epaisseur) {
        this.epaisseur = epaisseur;
    }

    public float getLargeur() {
        return largeur;
    }

    public void setLargeur(float largeur) {
        this.largeur = largeur;
    }

    public float getLongueur() {
        return longueur;
    }

    public void setLongueur(float longueur) {
        this.longueur = longueur;
    }

    public float getNbPlie() {
        return nbPlie;
    }

    public void setNbPlie(int nbPlie) {
        this.nbPlie = nbPlie;
    }

    public NomPiece getNomPiece() {
        return nomPiece;
    }

    public void setNomPiece(NomPiece nomPiece) {
        this.nomPiece = nomPiece;
    }

    public float[][] getPosXYZ() {
        return posXYZ;
    }

    public void setPosXYZ(float[][] posXYZ) {
        this.posXYZ = posXYZ;
    }

}
