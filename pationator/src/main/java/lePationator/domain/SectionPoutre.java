/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lePationator.domain;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Douch Ayoub
 */
public class SectionPoutre implements java.io.Serializable{

    private Color color;
    private ArrayList<PieceBois> listePoutre;
    private boolean visible = true;
    private boolean transparent = false;
    private ArrayList<PieceBois> listeDePliePoutre;
    
    public SectionPoutre() {
        listePoutre = new ArrayList<PieceBois>();
        listeDePliePoutre = new ArrayList<PieceBois>();
        color = new Color(204, 204, 0);
    }
    private float[][] cloneDoubleTab(float tab[][]) {
        float[][] tab2 = new float[8][3];
        for (int i = 0; i < tab.length; i++) {
            tab2[i] = tab[i].clone();
        }
        return tab2;
    }
    public void ajouterPoutres(InformationPatio infoPatio){
        clear();
        int x=0, y=1, z=2;
        int nb = infoPatio.getNbPoutre();
        float largeur = infoPatio.getLargeurPoutre();
        float espacementPoutre = infoPatio.getEspacementPoutre();
        float epaisseur = infoPatio.getEpaisseurPoutre();
        float longueur = infoPatio.getLongueurPoutre();
        int nbPoutreDouble = nb-2;
        float[][] posXYZPoutre_1= new float[8][3];
        float[][] posXYZPoutre_2= new float[8][3];
        float[][] posXYZPoutre_Double =new float[8][3];
        
        //initialiser les point y et z 
        posXYZPoutre_1[1][y] = posXYZPoutre_1[2][y] = posXYZPoutre_1[5][y] = posXYZPoutre_1[6][y] = longueur;
        posXYZPoutre_2[1][y] = posXYZPoutre_2[2][y] = posXYZPoutre_2[5][y] = posXYZPoutre_2[6][y] = longueur;
        posXYZPoutre_Double[1][y] = posXYZPoutre_Double[2][y] =
                posXYZPoutre_Double[5][y] = posXYZPoutre_Double[6][y] = longueur;
        for (int i = 0; i < 8; i++) {
            if(i<4){
                posXYZPoutre_1[i][z] = infoPatio.getLongueurPoteau() + largeur;
                posXYZPoutre_2[i][z] = infoPatio.getLongueurPoteau() + largeur;
                posXYZPoutre_Double[i][z] = infoPatio.getLongueurPoteau() + largeur;
            }else{
                posXYZPoutre_1[i][z]=infoPatio.getLongueurPoteau();
                posXYZPoutre_2[i][z]=infoPatio.getLongueurPoteau();
                posXYZPoutre_Double[i][z]=infoPatio.getLongueurPoteau();
            }
        }
        // ajouter les plies de poutres collée à la maison
        posXYZPoutre_1[2][x] = posXYZPoutre_1[3][x] = posXYZPoutre_1[6][x] = posXYZPoutre_1[7][x] -= epaisseur;
        for (int i = 0; i < infoPatio.getNbPliesPoutreSimple_1(); i++) {
            posXYZPoutre_1[2][x] = posXYZPoutre_1[3][x] = posXYZPoutre_1[6][x] = posXYZPoutre_1[7][x] += epaisseur;
            posXYZPoutre_1[0][x] = posXYZPoutre_1[1][x] = posXYZPoutre_1[4][x] = posXYZPoutre_1[5][x] += epaisseur;
            ajouterUnePlieDePoutre(new PieceBois
                    (cloneDoubleTab(posXYZPoutre_1), epaisseur, largeur,  longueur, NomPiece.POUTRE));
        }
        //ajouter une seule poutre collée à la maison incluant les plies
        ajouterUnePoutre(new PieceBois
                (cloneDoubleTab(posXYZPoutre_1), epaisseur*infoPatio.getNbPliesPoutreSimple_1(), largeur,  longueur, NomPiece.POUTRE));
        //ajouter les poutres doubles
        float pointInit = espacementPoutre - ((epaisseur * infoPatio.getNbPliesPoutreDouble())/2)+
                ((epaisseur * infoPatio.getNbPliesPoutreSimple_1())/2);
        //ajouter les plies de poutre double
        for (int i = 0; i < nbPoutreDouble; i++) {
            posXYZPoutre_Double[2][x] = posXYZPoutre_Double[3][x] = 
                    posXYZPoutre_Double[6][x] = posXYZPoutre_Double[7][x] = pointInit - epaisseur;
            posXYZPoutre_Double[0][x] = posXYZPoutre_Double[1][x] = 
                    posXYZPoutre_Double[4][x] = posXYZPoutre_Double[5][x] = pointInit;
            //ajouter les plies
            for (int j = 0; j < infoPatio.getNbPliesPoutreDouble(); j++) {
                posXYZPoutre_Double[2][x] = posXYZPoutre_Double[3][x] = 
                        posXYZPoutre_Double[6][x] = posXYZPoutre_Double[7][x] += epaisseur;
                posXYZPoutre_Double[0][x] = posXYZPoutre_Double[1][x] = 
                        posXYZPoutre_Double[4][x] = posXYZPoutre_Double[5][x] += epaisseur;
                ajouterUnePlieDePoutre(new PieceBois(cloneDoubleTab(posXYZPoutre_Double),
                        epaisseur, largeur,  longueur, NomPiece.POUTRE_DOUBLE));
            }
            ajouterUnePoutre(new PieceBois(cloneDoubleTab(posXYZPoutre_Double),
                        epaisseur*infoPatio.getNbPliesPoutreDouble(), largeur,  longueur, NomPiece.POUTRE_DOUBLE));
            pointInit = ((posXYZPoutre_Double[0][x] - (epaisseur * infoPatio.getNbPliesPoutreDouble())/2)+espacementPoutre)
                    - (epaisseur * infoPatio.getNbPliesPoutreDouble())/2;
        }
        
        
        // ajouter la dernière plies de poutre
        if(nbPoutreDouble == 0){
            pointInit = espacementPoutre - ((epaisseur * infoPatio.getNbPliesPoutreSimple_2())/2)+
                ((epaisseur * infoPatio.getNbPliesPoutreSimple_1())/2);
        }else{
            pointInit = ((posXYZPoutre_Double[0][x] - (epaisseur * infoPatio.getNbPliesPoutreDouble())/2)+espacementPoutre)
                    - (epaisseur * infoPatio.getNbPliesPoutreSimple_2())/2;
        }
        posXYZPoutre_2[2][x] = posXYZPoutre_2[3][x] = 
                posXYZPoutre_2[6][x] = posXYZPoutre_2[7][x] = pointInit - epaisseur;
        posXYZPoutre_2[0][x] = posXYZPoutre_2[1][x] = 
                posXYZPoutre_2[4][x] = posXYZPoutre_2[5][x] = pointInit;
        for (int i = 0; i < infoPatio.getNbPliesPoutreSimple_2(); i++) {
            posXYZPoutre_2[2][x] = posXYZPoutre_2[3][x] = 
                    posXYZPoutre_2[6][x] = posXYZPoutre_2[7][x] += epaisseur;
            posXYZPoutre_2[0][x] = posXYZPoutre_2[1][x] = 
                    posXYZPoutre_2[4][x] = posXYZPoutre_2[5][x] +=epaisseur;
            ajouterUnePlieDePoutre(new PieceBois(cloneDoubleTab(posXYZPoutre_2),
                    epaisseur, largeur,  longueur, NomPiece.POUTRE_PAFAUT));
        }
        ajouterUnePoutre(new PieceBois(cloneDoubleTab(posXYZPoutre_2),
                epaisseur*infoPatio.getNbPliesPoutreSimple_2(), largeur,  longueur, NomPiece.POUTRE_PAFAUT));
    }
    public void ajouterUnePoutre(PieceBois p){
       listePoutre.add(p);
    }
    public void ajouterUnePlieDePoutre(PieceBois p){
       listeDePliePoutre.add(p);
    }
    
    public void clear(){
        if(listePoutre != null){
            listePoutre.clear();
        }
        if(listeDePliePoutre != null){
            listeDePliePoutre.clear();
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public ArrayList<PieceBois> getListePoutre() {
        return listePoutre;
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

    public ArrayList<PieceBois> getListeDePliePoutre() {
        return listeDePliePoutre;
    }
}
