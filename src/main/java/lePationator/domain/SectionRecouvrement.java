package lePationator.domain;

import java.awt.Color;
import java.util.ArrayList;
/**
 *
 * @author Douch Ayoub
 */
public class SectionRecouvrement implements java.io.Serializable{

    private Color color ;
    private ArrayList<PieceBois> listePlanche;
    private boolean visible = true;
    private PieceBois plancheDecote;
    private boolean transparent = false;
    
    public SectionRecouvrement() {
        listePlanche = new ArrayList<PieceBois>();
        color = new Color(153, 102, 0);
    }
    public void ajouterPlanches(InformationPatio infoPatio){
        clear();
        float epaisseur = infoPatio.getEpaisseurPlanche();
        float largeur = infoPatio.getLargeurPlanche();
        float largeurPremier = infoPatio.getLargeurPremierePlanche();
        float longueur = infoPatio.getLongueurPlanche();
        int nb = infoPatio.getNbPlanche();
        if (largeurPremier <= 0){
            nb++;
        }
        float largeurSolive = infoPatio.getLargeurSolive();
        float hauteurPoteau = infoPatio.getLongueurPoteau();
        float x = 0;
        float y = 0;
        float z = hauteurPoteau + largeurSolive + infoPatio.getLargeurPoutre();
        float x1 = largeurPremier;
        float y1 = 0;
        float z1 = hauteurPoteau + largeurSolive + infoPatio.getLargeurPoutre();
        float x2 = 0;
        float y2 = longueur;
        float z2 = hauteurPoteau + largeurSolive + infoPatio.getLargeurPoutre();
        float x3 = largeurPremier;
        float y3 = longueur;
        float z3 = hauteurPoteau + largeurSolive + infoPatio.getLargeurPoutre();
        float x4 = 0;
        float y4 = 0;
        float z4 = hauteurPoteau + epaisseur + largeurSolive + infoPatio.getLargeurPoutre();
        float x5 = largeurPremier;
        float y5 = 0;
        float z5 = hauteurPoteau + epaisseur + largeurSolive + infoPatio.getLargeurPoutre();
        float x6 = 0;
        float y6 = longueur;
        float z6 = hauteurPoteau + epaisseur + largeurSolive + infoPatio.getLargeurPoutre();
        float x7 = largeurPremier;
        float y7 = longueur;
        float z7 = hauteurPoteau + epaisseur + largeurSolive + infoPatio.getLargeurPoutre();
        for (int i = 0; i < nb; i++){
            float[][] posXYZ = {{x, y, z}, {x1, y1, z1}, {x2, y2, z2}, {x3, y3, z3}, {x4, y4, z4}, {x5, y5, z5}, {x6, y6, z6}, {x7, y7, z7}};
            if (i == 0){
                if (largeurPremier > 0){
                    ajouterUnePlanche(new PieceBois(posXYZ.clone(), epaisseur, largeurPremier, longueur, NomPiece.PLANCHE));
                }
                x += largeurPremier + infoPatio.getEspacementPlanche();
                x1 += largeurPremier + infoPatio.getEspacementPlanche() + largeur - largeurPremier;
                x2 += largeurPremier + infoPatio.getEspacementPlanche();
                x3 += largeurPremier + infoPatio.getEspacementPlanche() + largeur - largeurPremier;
                x4 += largeurPremier + infoPatio.getEspacementPlanche();
                x5 += largeurPremier + infoPatio.getEspacementPlanche() + largeur - largeurPremier;
                x6 += largeurPremier + infoPatio.getEspacementPlanche();
                x7 += largeurPremier + infoPatio.getEspacementPlanche() + largeur - largeurPremier;
            }
            else {
                ajouterUnePlanche(new PieceBois(posXYZ.clone(), epaisseur, largeur, longueur, NomPiece.PLANCHE));
                x += largeur + infoPatio.getEspacementPlanche();
                x1 += largeur + infoPatio.getEspacementPlanche();
                x2 += largeur + infoPatio.getEspacementPlanche();
                x3 += largeur + infoPatio.getEspacementPlanche();
                x4 += largeur + infoPatio.getEspacementPlanche();
                x5 += largeur + infoPatio.getEspacementPlanche();
                x6 += largeur + infoPatio.getEspacementPlanche();
                x7 += largeur + infoPatio.getEspacementPlanche();
            }
        }
    }
    
    public void ajouterPlanchesCotee(InformationPatio infoPatio){
        float epaisseur = infoPatio.getEpaisseurSolive();
        float largeur =  infoPatio.getLargeurSolive();
        float longueur = infoPatio.getLongueurPlanche();
        float largeurSolive = infoPatio.getLargeurSolive();
        float hauteurPoteau = infoPatio.getLongueurPoteau()+ infoPatio.getLargeurPoutre();
        float longueurPatio = infoPatio.GetLongueurPatio();
        float x = longueurPatio - epaisseur;
        float y = 0;
        float z = hauteurPoteau + largeurSolive - largeur;
        float x1 = longueurPatio;
        float y1 = 0;
        float z1 = hauteurPoteau + largeurSolive - largeur;
        float x2 = longueurPatio - epaisseur;
        float y2 = longueur;
        float z2 = hauteurPoteau + largeurSolive - largeur;
        float x3 = longueurPatio;
        float y3 = longueur;
        float z3 = hauteurPoteau + largeurSolive - largeur;
        float x4 = longueurPatio - epaisseur;
        float y4 = 0;
        float z4 = hauteurPoteau + largeurSolive;
        float x5 = longueurPatio;
        float y5 = 0;
        float z5 = hauteurPoteau + largeurSolive;
        float x6 = longueurPatio - epaisseur;
        float y6 = longueur;
        float z6 = hauteurPoteau + largeurSolive;
        float x7 = longueurPatio;
        float y7 = longueur;
        float z7 = hauteurPoteau + largeurSolive;
        float[][] posXYZ = {{x, y, z}, {x1, y1, z1}, {x2, y2, z2}, {x3, y3, z3}, {x4, y4, z4}, {x5, y5, z5}, {x6, y6, z6}, {x7, y7, z7}};
        setPlancheDeCotee(new PieceBois( posXYZ,  epaisseur,  largeur,  longueur, NomPiece.PLANCHECOTEE));
    }
    public void ajouterUnePlanche(PieceBois p){
       listePlanche.add(p);
    }
    
    public void setPlancheDeCotee(PieceBois plancheDecote){
       this.plancheDecote = plancheDecote;
    }
    
    public void clear(){
        if(listePlanche != null){
            listePlanche.clear();
        }
    }

    public PieceBois getPlancheDecote() {
        return plancheDecote;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<PieceBois> getListePlanche() {
        return listePlanche;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isTransparent() {
        return transparent;
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }
    
    
}
