/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lePationator.domain;
import java.awt.*;
import java.util.ArrayList;
/**
 *
 * @author Douch Ayoub
 */
public class SectionPoteau implements java.io.Serializable{

    private Color color;
    private ArrayList<PieceBois> listePoteau;
    private boolean visible = true;
    private boolean transparent = false;
    
    public SectionPoteau() {
        listePoteau = new ArrayList<PieceBois>();
        color = new Color(51, 51, 0);
    }
    private float[][] cloneDoubleTab(float tab[][]) {
        float[][] tab2 = new float[8][3];
        for (int i = 0; i < tab.length; i++) {
            tab2[i] = tab[i].clone();
        }
        return tab2;
    }
    public void ajouterPoteaux(InformationPatio infoPatio) {
        this.clear();
        int x = 0, y = 1, z = 2;
        float epaisseur = infoPatio.getEpaisseurPoteau();
        float largeur = infoPatio.getLargeurPoteau();
        float longueur = infoPatio.getLongueurPoteau();
        int nombrePoteauParPoutre=infoPatio.getNbPoteauParPoutre();
        float distanceEntrePoteauxPourUnePoutre=infoPatio.getLongueurPoutre()/(nombrePoteauParPoutre-1);
        float[][] posXYZPoteauBas = new float[8][3];

        for (int i = 0; i < 4; i++) {

            posXYZPoteauBas[i][z] = infoPatio.getLongueurPoteau();
        }
        for (int i = 4; i < 8; i++) {

            posXYZPoteauBas[i][z] = 0;
        }
        
        for(int j=0;j<infoPatio.getNbPoutre()-1;j++){
            if(j==0){
                posXYZPoteauBas[0][x] = posXYZPoteauBas[3][x] = posXYZPoteauBas[4][x] = posXYZPoteauBas[7][x] = 
                        infoPatio.getEspacementPoutre()-(largeur/2)+((infoPatio.getNbPliesPoutreSimple_1()*infoPatio.getEpaisseurPoutre())/2) ;
                posXYZPoteauBas[1][x] = posXYZPoteauBas[2][x] = posXYZPoteauBas[5][x] = posXYZPoteauBas[6][x] = 
                        infoPatio.getEspacementPoutre()+ (largeur/2)+((infoPatio.getNbPliesPoutreSimple_1()*infoPatio.getEpaisseurPoutre())/2) ;
            }
            else{
                posXYZPoteauBas[0][x] = posXYZPoteauBas[3][x] = posXYZPoteauBas[4][x] = posXYZPoteauBas[7][x] += infoPatio.getEspacementPoutre();
                posXYZPoteauBas[1][x] = posXYZPoteauBas[2][x] = posXYZPoteauBas[5][x] = posXYZPoteauBas[6][x] +=
                        infoPatio.getEspacementPoutre();
            }
            for(int i=0;i<nombrePoteauParPoutre;i++){
                posXYZPoteauBas[2][y] = posXYZPoteauBas[3][y] = posXYZPoteauBas[6][y] = posXYZPoteauBas[7][y] =distanceEntrePoteauxPourUnePoutre*i + (largeur / 2);
                posXYZPoteauBas[0][y] = posXYZPoteauBas[1][y] = posXYZPoteauBas[4][y] = posXYZPoteauBas[5][y] =distanceEntrePoteauxPourUnePoutre*i - (largeur / 2);
                ajouterUnPoteau(new PieceBois(cloneDoubleTab(posXYZPoteauBas),
                    epaisseur, largeur, longueur, NomPiece.POTEAU));
            }
        }

    }
    public void ajouterUnPoteau(PieceBois p){
       listePoteau.add(p);
    }
    
    public void clear(){
        if(listePoteau != null){
            listePoteau.clear();
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<PieceBois> getListePoteau() {
        return listePoteau;
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
