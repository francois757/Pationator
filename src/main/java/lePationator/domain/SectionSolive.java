package lePationator.domain;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Douch Ayoub
 */
public class SectionSolive implements java.io.Serializable{

    private Color color;
    private ArrayList<PieceBois> listeSolive;
    private boolean visible = true;
    private boolean transparent = false;
    
    public SectionSolive() {
        listeSolive = new ArrayList<PieceBois>();
        color = new Color(204, 51, 0);
    }
    private float[][] cloneDoubleTab(float tab[][]) {
        float[][] tab2 = new float[8][3];
        for (int i = 0; i < tab.length; i++) {
            tab2[i] = tab[i].clone();
        }
        return tab2;
    }
    public void ajouterSolives(InformationPatio infoPatio){
        clear();
        int x=0, y=1, z=2;
        float longSolivePAFaux = infoPatio.getSolivePorteAFaux();
        float largeur = infoPatio.getLargeurSolive();
        int nb = infoPatio.getNbSolive();
        float epaisseur = infoPatio.getEpaisseurSolive();
        
        //int nbSoliveColonne = (int) ((infoPatio.getLongueurPoutre()-2*epaisseur) /
         //       (infoPatio.getEspacementSolive()+epaisseur) + 1);
        int nbColonneDeSolive = infoPatio.getNbPoutre()-1;//
        int nbSoliveColonne = nb / nbColonneDeSolive;
        float[][] posXYZSoliveBase= new float[8][3];
        float[][] posXYZ;
        
        for (int i = 0; i < 8; i++) {
            if(i<4){
                posXYZSoliveBase[i][z]=infoPatio.getLongueurPoteau() + infoPatio.getLargeurPoutre() + largeur;
            }else{
                posXYZSoliveBase[i][z]=infoPatio.getLongueurPoteau()+ infoPatio.getLargeurPoutre();
            }
        }
        //Ajouter les coord de y dans les points 1, 2, 5 et 6
        posXYZSoliveBase[1][y] = posXYZSoliveBase[2][y] = 
                posXYZSoliveBase[5][y] = posXYZSoliveBase[6][y]= 
                (nbColonneDeSolive > 1 ? infoPatio.getLongueurSoliveMaison():longSolivePAFaux);
        
        for (int i = 0; i < nbSoliveColonne ; i++) {
            //Ajouter les coord de x dans les points 0, 1, 4 et 5
            posXYZSoliveBase[0][x]= posXYZSoliveBase[4][x] = 
                    posXYZSoliveBase[1][x]= posXYZSoliveBase[5][x] = 
                    ( posXYZSoliveBase[3][x]+ (i == 0 ? 0 : infoPatio.getEspacementSolive()) );
            //Ajouter les coord de x dans les points 2, 3, 6 et 7
            posXYZSoliveBase[3][x]= posXYZSoliveBase[7][x] = 
                    posXYZSoliveBase[2][x]= posXYZSoliveBase[6][x] = epaisseur + posXYZSoliveBase[1][x];
            ajouterUneSolive(new PieceBois(cloneDoubleTab(posXYZSoliveBase),
                    epaisseur,  largeur,  (nbColonneDeSolive > 1 ? infoPatio.getLongueurSoliveMaison():longSolivePAFaux), 
                    (nbColonneDeSolive > 1 ? NomPiece.SOLIVE:NomPiece.SOLIVE_PAFAUX)));
            if(nbColonneDeSolive > 1){
                posXYZ = cloneDoubleTab(posXYZSoliveBase);
                for (int j = 2; j <= nbColonneDeSolive; j++) {
                    if(j%2 == 0){
                        for (int k = 0; k < 8; k++) {
                            posXYZ[k][x] += epaisseur;
                        }
                    }else{
                        for (int k = 0; k < 8; k++) {
                            posXYZ[k][x] -= epaisseur;
                        }
                    }
                    if(j== nbColonneDeSolive){
                        for (int k = 0; k < 8; k++) {
                            if(k==0 || k== 3 || k==4 || k==7){
                                posXYZ[k][y] += infoPatio.getLongueurSoliveCentre() - (infoPatio.getNbPliesPoutreDouble()*epaisseur);
                            }else{
                                posXYZ[k][y] += (longSolivePAFaux)- 
                                        (infoPatio.getNbPliesPoutreDouble()*epaisseur);
                            }
                        }
                        ajouterUneSolive
                            (new PieceBois(cloneDoubleTab(posXYZ),  epaisseur,  largeur, longSolivePAFaux, NomPiece.SOLIVE_PAFAUX));
                    }else{
                        for (int k = 0; k < 8; k++) {
                            if(j == 2){
                                if(k==0 || k== 3 || k==4 || k==7){
                                    posXYZ[k][y] += infoPatio.getLongueurSoliveMaison() -
                                            (infoPatio.getNbPliesPoutreDouble()*epaisseur);
                                }else{
                                    posXYZ[k][y] += (infoPatio.getLongueurSoliveCentre())- 
                                        (infoPatio.getNbPliesPoutreDouble()*epaisseur);
                                }
                            }else{
                                posXYZ[k][y] += (infoPatio.getLongueurSoliveCentre())- 
                                        (infoPatio.getNbPliesPoutreDouble()*epaisseur);
                            }
                        }
                        ajouterUneSolive
                            (new PieceBois(cloneDoubleTab(posXYZ),  epaisseur,  largeur,  infoPatio.getLongueurSoliveCentre(), NomPiece.SOLIVE));
                    }
                }
            }
        }
    }
    public void ajouterUneSolive(PieceBois p){
       listeSolive.add(p);
    }
    
    public void clear(){
        if(listeSolive != null){
            listeSolive.clear();
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<PieceBois> getListeSolive() {
        return listeSolive;
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
