/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lePationator.domain;

/**
 *
 * @author francoisgagnon
 */
public class InformationPatio implements java.io.Serializable{

    public InformationPatio() {
        calculPrixTotal();
    }
    private float longueurPatio = (float)328;
    private float largeurPatio = (float)86.75;
    private float hauteurPatio = (float)35;
    private float longueurPoteau = (float)15;
    private float largeurPoteau = (float)5.5;
    private float epaisseurPoteau = (float)5.5;
    private float espacementPoteau = (float)86.75;
    private int nbPoteauParPoutre = 2;
    private float longueurPoutre = (float)86.75;
    private float largeurPoutre = (float)9.25;
    private float epaisseurPoutre = (float)1.5;
    private int nbPliesPoutreSimple_1 = 2;
    private int nbPliesPoutreSimple_2 = 2;
    private int nbPliesPoutreDouble = 3;
    private float espacementPoutre = (float)158.75;
    private int nbPoutre = 3;
    private float longueurSolive = (float)158.75;
    private float longueurSoliveMaison = (float)162.5;
    private float longueurSoliveCentre = (float)163.25;
    private float solivePorteAFaux = (float)168.5;
    private float largeurSolive = (float)9.25;
    private float epaisseurSolive = (float)1.5;
    private float espacementSolive = (float)12.25;
    private int nbSolive = 14;
    private float longueurPlanche = (float)86.75;
    private float largeurPlanche = (float)5.5;
    private float largeurPremierePlanche = (float)5.5;
    private float epaisseurPlanche = (float)1.5;
    private float espacementPlanche = (float)2;
    private int nbPlanche = 44;
    private float PorteAFaux = (float) 7.5;
    private float PorteAFauxPourNbPoutre = (float) 7.5;
    private float prix2x4 = (float) 4;
    private float prix2x6 = (float) 6;
    private float prix2x8 = (float) 8;
    private float prix2x10 = (float) 10;
    private float prix2x12 = (float) 12;
    private float prix5Quartx6 = (float) 3;
    private float prixTotal;
    private boolean soliveValide = true;
    private boolean porteAFauxValide = true;
    private boolean poutreSimpleValide = true;
    private boolean poutreDoubleValide = true;
    private boolean plieSimpleValide = true;
    private boolean plieDoubleValide = true;
    private boolean poteauxValide = true;

    public void setLongueurPatio(float longueur){
        this.longueurPatio = longueur;
    }

    public void setLargeurPatio(float largeur){
        this.largeurPatio = largeur;
    }

    public void setHauteurPatio(float hauteur){
        this.hauteurPatio = hauteur;
    }

    public void setSolivePorteAFaux(float PAF){
        this.solivePorteAFaux = PAF;
    }

    public void setLongueurPoteau(float longueur){
        this.longueurPoteau = longueur;
    }

    public void setLargeurPoteau(float largeur){
        this.largeurPoteau = largeur;
    }

    public void setEpaisseurPoteau(float epaisseur){
        this.epaisseurPoteau = epaisseur;
    }

    public void setEspacementPoteau(float espacement){
        espacementPoteau = espacement;
    }

    public void setNbPoteauParPoutre(int nombre){
        this.nbPoteauParPoutre = nombre;
    }

    public void setLongueurPoutre(float longueur){
        this.longueurPoutre = longueur;
    }

    public void setLargeurPoutre(float largeur){
        this.largeurPoutre = largeur;
    }

    public void setEpaisseurPoutre(float epaisseur){
        this.epaisseurPoutre = epaisseur;
    }

    public void setNbPliesPoutreSimple_1(int nombre){
        this.nbPliesPoutreSimple_1 = nombre;
    }

    public void setNbPliesPoutreSimple_2(int nombre){
        this.nbPliesPoutreSimple_2 = nombre;
    }

    public void setNbPliesPoutreDouble(int nombre){
        this.nbPliesPoutreDouble = nombre;
    }

    public void setEspacementPoutre(float espacement){
        espacementPoutre = espacement;
    }

    public void setLongueurSolive(float longueur){
        this.longueurSolive = longueur;
    }

    public void setLargeurSolive(float largeur){
        this.largeurSolive = largeur;
    }

    public void setEpaisseurSolive(float epaisseur){
        this.epaisseurSolive = epaisseur;
    }

    public void setEspacementSolive(float espacement){
        espacementSolive = espacement;
    }

    public void setNbSolive(int nombre){
        this.nbSolive = nombre;
    }

    public void setLongueurPlanche(float longueur){
        this.longueurPlanche = longueur;
    }

    public void setLargeurPlanche(float largeur){
        this.largeurPlanche = largeur;
    }

    public void setEpaisseurPlanche(float epaisseur){
        this.epaisseurPlanche = epaisseur;
    }

    public void setEspacementPlanche(float espacement){
        espacementPlanche = espacement;
    }

    public void setNbPlanche(int nombre){
        this.nbPlanche = nombre;
    }

    public void setPorteAFaux(float PorteAFaux) {this.PorteAFaux = PorteAFaux;}

    public void setPorteAFauxPourNbPoutre (float PorteAFaux) {this.PorteAFauxPourNbPoutre = PorteAFaux;}

    public float GetLongueurPatio(){
        return longueurPatio;
    }

    public float getLargeurPatio(){
        return largeurPatio;
    }

    public float getHauteurPatio(){
        return hauteurPatio;
    }

    public float getSolivePorteAFaux(){
        return solivePorteAFaux;
    }

    public float getLongueurPoteau(){
        return longueurPoteau;
    }

    public float getLargeurPoteau(){
        return largeurPoteau;
    }

    public float getEpaisseurPoteau(){
        return epaisseurPoteau;
    }

    public float getEspacementPoteau(){
        return espacementPoteau;
    }

    public int getNbPoteauParPoutre(){
        return nbPoteauParPoutre;
    }

    public float getLongueurPoutre(){
        return longueurPoutre;
    }

    public float getLargeurPoutre(){
        return largeurPoutre;
    }

    public float getEpaisseurPoutre(){
        return epaisseurPoutre;
    }

    public int getNbPliesPoutreSimple_1(){
        return nbPliesPoutreSimple_1;
    }

    public int getNbPliesPoutreSimple_2(){
        return nbPliesPoutreSimple_2;
    }

    public int getNbPliesPoutreDouble(){
        return nbPliesPoutreDouble;
    }

    public float getEspacementPoutre(){
        return espacementPoutre;
    }

    public float getLongueurSolive(){
        return longueurSolive;
    }

    public float getLargeurSolive(){
        return largeurSolive;
    }

    public float getEpaisseurSolive(){
        return epaisseurSolive;
    }

    public float getEspacementSolive(){
        return espacementSolive;
    }

    public int getNbSolive(){
        return nbSolive;
    }

    public float getLongueurPlanche(){
        return longueurPlanche;
    }

    public float getLargeurPlanche(){
        return largeurPlanche;
    }

    public float getEpaisseurPlanche(){
        return epaisseurPlanche;
    }

    public float getEspacementPlanche(){
        return espacementPlanche;
    }

    public int getNbPlanche(){
        return nbPlanche;
    }

    public int getNbPoutre() {
        return nbPoutre;
    }

    public void setNbPoutre(int nbPoutre) {
        this.nbPoutre = nbPoutre;
    }

    public float getLongueurPatio() {
        return longueurPatio;
    }

    public float getLongueurSoliveMaison() {
        return longueurSoliveMaison;
    }

    public void setLongueurSoliveMaison(float longueurSoliveMaison) {
        this.longueurSoliveMaison = longueurSoliveMaison;
    }

    public float getLongueurSoliveCentre() {
        return longueurSoliveCentre;
    }

    public void setLongueurSoliveCentre(float longueurSoliveCentre) {
        this.longueurSoliveCentre = longueurSoliveCentre;
    }

    public float getPorteAFaux() {return PorteAFaux;}

    public float getPorteAFauxPourNbPoutre() {return PorteAFauxPourNbPoutre;}

    public void setPrix2x4(float prix) {
        this.prix2x4 = prix;
    }

    public float getPrix2x4() {return prix2x4;}

    public void setPrix2x6(float prix) {
        this.prix2x6 = prix;
    }

    public float getPrix2x6() {return prix2x6;}

    public void setPrix2x8(float prix) {
        this.prix2x8 = prix;
    }

    public float getPrix2x8() {return prix2x8;}

    public void setPrix2x10(float prix){
        this.prix2x10 = prix;
    }

    public float getPrix2x10() {return prix2x10;}

    public void setPrix2x12(float prix){
        this.prix2x12 = prix;
    }

    public float getPrix2x12() {return prix2x12;}

    public void setPrix5Quartx6(float prix){
        this.prix5Quartx6 = prix;
    }

    public float getPrix5Quartx6() {return prix5Quartx6;}

    private void setPrixTotal(float prix){
        this.prixTotal = prix;
    }

    public float getPrixTotal() {return prixTotal;}

    public void setSoliveValide(boolean valide){
        this.soliveValide = valide;
    }

    public boolean getSoliveValide() {return soliveValide;}

    public void setPorteAFauxValide(boolean valide){
        this.porteAFauxValide = valide;
    }

    public boolean getPorteAFauxValide() {return porteAFauxValide;}

    public void setPoutreSimpleValide(boolean valide){
        this.poutreSimpleValide = valide;
    }

    public boolean getPoutreSimpleValide() {return poutreSimpleValide;}

    public void setPoutreDoubleValide(boolean valide){
        this.poutreDoubleValide = valide;
    }

    public boolean getPoutreDoubleValide() {return poutreDoubleValide;}

    public void setPlieSimpleValide(boolean valide){
        this.plieSimpleValide = valide;
    }

    public boolean getPlieSimpleValide() {return plieSimpleValide;}

    public void setPlieDoubleValide(boolean valide){
        this.plieDoubleValide = valide;
    }

    public boolean getPlieDoubleValide() {return plieDoubleValide;}

    public void calculPrixTotal(){
        float prixCalculee = 0f;

        float prixPliePoutre;
        if(largeurPoutre == 3.5f){
            prixPliePoutre = prix2x4 * (longueurPoutre / 12);
        }
        else if(largeurPoutre == 5.5f){
            prixPliePoutre = prix2x6 * (longueurPoutre / 12);
        }
        else if(largeurPoutre == 7.25f){
            prixPliePoutre = prix2x8 * (longueurPoutre / 12);
        }
        else if(largeurPoutre == 9.25f){
            prixPliePoutre = prix2x10 * (longueurPoutre / 12);
        }
        else{
            prixPliePoutre = prix2x12 * (longueurPoutre / 12);
        }
        int nbPlie = nbPliesPoutreSimple_1 + nbPliesPoutreSimple_2 + (nbPoutre - 2) * nbPliesPoutreDouble;
        float prixPourLesPoutres = nbPlie * prixPliePoutre;
        prixCalculee += prixPourLesPoutres;

        float prixSoliveMaison;
        float prixSoliveCentre;
        float prixSolivePorteAFaux;
        float prixPlancheDeCote;
        if(largeurSolive == 3.5f){
            prixSoliveMaison = prix2x4 * (longueurSoliveMaison / 12);
            prixSoliveCentre = prix2x4 * (longueurSoliveCentre / 12);
            prixSolivePorteAFaux = prix2x4 * (solivePorteAFaux / 12);
            prixPlancheDeCote = prix2x4 * (longueurPlanche / 12);
        }
        else if(largeurSolive == 5.5f){
            prixSoliveMaison = prix2x6 * (longueurSoliveMaison / 12);
            prixSoliveCentre = prix2x6 * (longueurSoliveCentre / 12);
            prixSolivePorteAFaux = prix2x6 * (solivePorteAFaux / 12);
            prixPlancheDeCote = prix2x6 * (longueurPlanche / 12);
        }
        else if(largeurSolive == 7.25f){
            prixSoliveMaison = prix2x8 * (longueurSoliveMaison / 12);
            prixSoliveCentre = prix2x8 * (longueurSoliveCentre / 12);
            prixSolivePorteAFaux = prix2x8 * (solivePorteAFaux / 12);
            prixPlancheDeCote = prix2x8 * (longueurPlanche / 12);
        }
        else if(largeurSolive == 9.25f){
            prixSoliveMaison = prix2x10 * (longueurSoliveMaison / 12);
            prixSoliveCentre = prix2x10 * (longueurSoliveCentre / 12);
            prixSolivePorteAFaux = prix2x10 * (solivePorteAFaux / 12);
            prixPlancheDeCote = prix2x10 * (longueurPlanche / 12);
        }
        else{
            prixSoliveMaison = prix2x12 * (longueurSoliveMaison / 12);
            prixSoliveCentre = prix2x12 * (longueurSoliveCentre / 12);
            prixSolivePorteAFaux = prix2x12 * (solivePorteAFaux / 12);
            prixPlancheDeCote = prix2x12 * (longueurPlanche / 12);
        }
        int nbSoliveParEspacement = nbSolive / (nbPoutre - 1);
        float prixPourLesSolivesMaison = nbSoliveParEspacement * prixSoliveMaison;
        prixCalculee += prixPourLesSolivesMaison;
        float prixPourLesSolivesCentre = (nbSolive - nbSoliveParEspacement * 2) * prixSoliveCentre;
        prixCalculee += prixPourLesSolivesCentre;
        float prixPourLesSolivesPorteAFaux = nbSoliveParEspacement * prixSolivePorteAFaux;
        prixCalculee += prixPourLesSolivesPorteAFaux;
        prixCalculee += prixPlancheDeCote;

        float prixPlanche;
        if (epaisseurPlanche == 1.5f){
            prixPlanche = prix2x6 * (longueurPlanche / 12);
        }
        else{
            prixPlanche = prix5Quartx6 * (longueurPlanche / 12);
        }
        float prixPourLesPlanches = nbPlanche * prixPlanche;
        prixCalculee += prixPourLesPlanches;
        setPrixTotal(prixCalculee);
    }

    public float getLargeurPremierePlanche() {
        return largeurPremierePlanche;
    }

    public void setLargeurPremierePlanche(float largeurPremierePlanche) {
        this.largeurPremierePlanche = largeurPremierePlanche;
    }

    public void setPoteauxValide(boolean valide){
        this.poteauxValide = valide;
    }

    public boolean getPoteauxValide(){
        return poteauxValide;
    }
}
