/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lePationator.domain;

/**
 *
 * @author Hassen
 */
public class Gabarit implements java.io.Serializable{

    public Gabarit(InformationPatio InfoPatio) {
        this.InfoPatio = InfoPatio;
    }

    private InformationPatio InfoPatio;

    public void updatePatio(ChampSaisie NomChamp, float valeurChamp){

        switch (NomChamp) {
            case LONGUEUR_PATIO:
                boolean nbPoutreDepartEstDeux = false;
                if (InfoPatio.getNbPoutre() == 2){
                    nbPoutreDepartEstDeux = true;
                }
                int nbSoliveParEspace = InfoPatio.getNbSolive()/(InfoPatio.getNbPoutre() - 1);
                double nbPoutreNonArrondie = valeurChamp/InfoPatio.getEspacementPoutre();
                double nbPoutreNonInt = Math.ceil(nbPoutreNonArrondie);
                int nbPoutre;
                nbPoutre = (int) nbPoutreNonInt;
                if (nbPoutre < 2){
                    nbPoutre = 2;
                    InfoPatio.setEspacementPoutre(valeurChamp - InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2());
                }
                int nbPlanche;
                float espacementPlanche = InfoPatio.getEspacementPlanche();
                float largeurPlanche = InfoPatio.getLargeurPlanche();
                float longueurPatio = valeurChamp;
                float longueurCalculee = largeurPlanche;
                for (nbPlanche = 1; longueurCalculee < longueurPatio; nbPlanche++){
                    longueurCalculee += espacementPlanche + largeurPlanche;
                }
                if (longueurCalculee != valeurChamp){
                    float depassement = longueurCalculee - valeurChamp;
                    float largeurPremierePlanche = largeurPlanche - depassement;
                    InfoPatio.setLargeurPremierePlanche(largeurPremierePlanche);
                    if (largeurPremierePlanche <= 0){
                        nbPlanche--;
                    }
                } else {
                    InfoPatio.setLargeurPremierePlanche(largeurPlanche);
                }
                int nbSolive = nbSoliveParEspace * (nbPoutre - 1);
                float longueurSansPAF;
                float PorteAFaux;
                float SolivePorteAFaux;
                if (nbPoutre == 2){
                    longueurSansPAF = (nbPoutre - 1) * InfoPatio.getEspacementPoutre()
                            + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
                    PorteAFaux = valeurChamp - longueurSansPAF;
                    SolivePorteAFaux = valeurChamp - InfoPatio.getEpaisseurSolive();
                }
                else {
                    longueurSansPAF = (nbPoutre - 1) * InfoPatio.getEspacementPoutre()
                            + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2()
                            - (InfoPatio.getEpaisseurPoutre() / 2) * - (3 - InfoPatio.getNbPliesPoutreDouble());
                    PorteAFaux = valeurChamp - longueurSansPAF;
                    SolivePorteAFaux = PorteAFaux + InfoPatio.getEspacementPoutre() + 0.75f - 0.75f * (InfoPatio.getNbPliesPoutreSimple_2() - 2) + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
                }
                if (nbPoutreDepartEstDeux){
                    float longueurSoliveMaison = InfoPatio.getEspacementPoutre() +
                            (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_1()) / 2
                            + (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble()) / 2;
                    float longueurSoliveCentre = InfoPatio.getEspacementPoutre() + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble();
                    InfoPatio.setLongueurSoliveMaison(longueurSoliveMaison);
                    InfoPatio.setLongueurSoliveCentre(longueurSoliveCentre);
                }
                InfoPatio.setLongueurPatio(valeurChamp);
                InfoPatio.setNbPlanche(nbPlanche);
                InfoPatio.setNbPoutre(nbPoutre);
                InfoPatio.setNbSolive(nbSolive);
                InfoPatio.setSolivePorteAFaux(SolivePorteAFaux);
                InfoPatio.setPorteAFaux(PorteAFaux);
                InfoPatio.setPorteAFauxPourNbPoutre(PorteAFaux);
                InfoPatio.calculPrixTotal();
                break;
            case LARGEUR_PATIO:
                int nbSoliveParEspacement;
                float espacementSolive = InfoPatio.getEspacementSolive();
                float epaisseurSolive = InfoPatio.getEpaisseurSolive();
                float largeurCalculee = epaisseurSolive;
                for (nbSoliveParEspacement = 1; largeurCalculee <= valeurChamp - epaisseurSolive * 3; nbSoliveParEspacement++){
                    largeurCalculee += espacementSolive + epaisseurSolive;
                }
                while (largeurCalculee > valeurChamp - epaisseurSolive & espacementSolive > epaisseurSolive){
                    espacementSolive = espacementSolive - 0.00390625f;
                    largeurCalculee = epaisseurSolive;
                    for (nbSoliveParEspacement = 1; largeurCalculee <= valeurChamp - epaisseurSolive * 3; nbSoliveParEspacement++){
                        largeurCalculee += espacementSolive + epaisseurSolive;
                    }
                    InfoPatio.setEspacementSolive(espacementSolive);
                }
                InfoPatio.setLargeurPatio(valeurChamp);
                InfoPatio.setLongueurPlanche(valeurChamp);
                InfoPatio.setLongueurPoutre(valeurChamp);
                InfoPatio.setEspacementPoteau(valeurChamp);
                nbSolive = nbSoliveParEspacement * (InfoPatio.getNbPoutre() - 1);
                InfoPatio.setNbSolive(nbSolive);
                InfoPatio.calculPrixTotal();
                break;
            case HAUTEUR_PATIO:
                float epaisseurPlanche = InfoPatio.getEpaisseurPlanche();
                float largeurSolive = InfoPatio.getLargeurSolive();
                float largeurPoutre = InfoPatio.getLargeurPoutre();
                float longueurPoteau = valeurChamp - epaisseurPlanche - largeurSolive - largeurPoutre;
                if (longueurPoteau < 0){
                    break;
                }
                if (longueurPoteau > 78f){
                    InfoPatio.setLargeurPoteau(5.5f);
                    InfoPatio.setEpaisseurPoteau(5.5f);
                }
                InfoPatio.setHauteurPatio(valeurChamp);
                InfoPatio.setLongueurPoteau(longueurPoteau);
                InfoPatio.calculPrixTotal();
                break;
            case DIMENSION_PLANCHE:
                InfoPatio.setEpaisseurPlanche(valeurChamp);
                updatePatio(ChampSaisie.HAUTEUR_PATIO, InfoPatio.getHauteurPatio());
                updatePatio(ChampSaisie.LONGUEUR_PATIO, InfoPatio.getLongueurPatio());
                InfoPatio.calculPrixTotal();
                break;
            case ESPACE_PLANCHE:
                espacementPlanche = valeurChamp;
                largeurPlanche = InfoPatio.getLargeurPlanche();
                longueurPatio = InfoPatio.GetLongueurPatio();
                longueurCalculee = largeurPlanche;
                for (nbPlanche = 1; longueurCalculee < longueurPatio; nbPlanche++){
                    longueurCalculee += espacementPlanche + largeurPlanche;
                }
                if (longueurCalculee != longueurPatio){
                    float depassement = longueurCalculee - longueurPatio;
                    float largeurPremierePlanche = largeurPlanche - depassement;
                    InfoPatio.setLargeurPremierePlanche(largeurPremierePlanche);
                    if (largeurPremierePlanche <= 0){
                        nbPlanche--;
                    }
                } else {
                    InfoPatio.setLargeurPremierePlanche(largeurPlanche);
                }
                InfoPatio.setEspacementPlanche(espacementPlanche);
                InfoPatio.setNbPlanche(nbPlanche);
                InfoPatio.calculPrixTotal();
                break;
            case ESPACE_POUTRE:
                if (valeurChamp > InfoPatio.getLongueurPatio() - InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2()
                        || valeurChamp <= 0){
                    break;
                }
                nbSoliveParEspace = InfoPatio.getNbSolive() / (InfoPatio.getNbPoutre() - 1);
                longueurPatio = InfoPatio.getLongueurPatio();
                float espacementPoutre = valeurChamp;
                longueurCalculee = valeurChamp;
                int nbSoliveSurLongueur;
                nbPoutre = 2;
                for (nbSoliveSurLongueur = 1; longueurCalculee <= longueurPatio - espacementPoutre - 1f; nbSoliveSurLongueur++){
                    longueurCalculee += espacementPoutre;
                    nbPoutre++;
                }
                while (longueurCalculee - longueurPatio > 8 & espacementPoutre > InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble()){
                    espacementPoutre -= 0.00390625f;
                    longueurCalculee = espacementPoutre;
                    for (nbSoliveSurLongueur = 1; longueurCalculee <= longueurPatio - espacementPoutre - 1f; nbSoliveSurLongueur++){
                        longueurCalculee += espacementPoutre;
                        nbPoutre++;
                    }
                }
                if (nbPoutre == 2){
                    longueurSansPAF = (nbPoutre - 1) * espacementPoutre
                            + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
                    PorteAFaux = InfoPatio.getLongueurPatio() - longueurSansPAF;
                    SolivePorteAFaux = InfoPatio.getLongueurPatio();
                }
                else {
                    longueurSansPAF = (nbPoutre - 1) * espacementPoutre
                            + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2()
                            - (InfoPatio.getEpaisseurPoutre() / 2) * - (3 - InfoPatio.getNbPliesPoutreDouble());
                    PorteAFaux = InfoPatio.getLongueurPatio() - longueurSansPAF;
                    SolivePorteAFaux = PorteAFaux + espacementPoutre + 0.75f - 0.75f * (InfoPatio.getNbPliesPoutreSimple_2() - 2) + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
                }
                nbSolive = nbSoliveParEspace * nbSoliveSurLongueur;
                float longueurSoliveMaison = valeurChamp +
                        (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_1()) / 2
                        + (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble()) / 2;
                float longueurSoliveCentre = valeurChamp + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble();
                InfoPatio.setNbSolive(nbSolive);
                InfoPatio.setNbPoutre(nbPoutre);
                InfoPatio.setSolivePorteAFaux(SolivePorteAFaux);
                InfoPatio.setEspacementPoutre(valeurChamp);
                InfoPatio.setLongueurSoliveMaison(longueurSoliveMaison);
                InfoPatio.setLongueurSoliveCentre(longueurSoliveCentre);
                InfoPatio.setPorteAFaux(PorteAFaux);
                InfoPatio.setPorteAFauxPourNbPoutre(PorteAFaux);
                InfoPatio.calculPrixTotal();
                break;
            case DIMENSION_SOLIVE:
                InfoPatio.setLargeurSolive(valeurChamp);
                updatePatio(ChampSaisie.HAUTEUR_PATIO, InfoPatio.getHauteurPatio());
                InfoPatio.calculPrixTotal();
                break;
            case PAFAUX_SOLIVE:
                if (valeurChamp > InfoPatio.getLongueurPatio()){
                    break;
                }
                longueurSansPAF = InfoPatio.getLongueurPatio() - valeurChamp;
                espacementPoutre = (longueurSansPAF -
                        InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2())
                        / (InfoPatio.getNbPoutre() - 1);
                InfoPatio.setEspacementPoutre(espacementPoutre);
                InfoPatio.setPorteAFaux(valeurChamp);
                longueurSoliveMaison = espacementPoutre +
                        (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_1()) / 2
                        + (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble()) / 2;
                longueurSoliveCentre = espacementPoutre + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble();
                if (InfoPatio.getNbPliesPoutreSimple_2() != 1){
                    SolivePorteAFaux = valeurChamp + InfoPatio.getEspacementPoutre() - 0.75f * (InfoPatio.getNbPliesPoutreSimple_2() - 2) + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
                }
                else{
                    SolivePorteAFaux = valeurChamp + InfoPatio.getEspacementPoutre() + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
                }
                InfoPatio.setLongueurSoliveMaison(longueurSoliveMaison);
                InfoPatio.setLongueurSoliveCentre(longueurSoliveCentre);
                InfoPatio.setSolivePorteAFaux(SolivePorteAFaux);
                InfoPatio.setPorteAFauxPourNbPoutre(valeurChamp);
                InfoPatio.calculPrixTotal();
                break;
            case ESPACE_SOLIVE:
                if (valeurChamp < InfoPatio.getEpaisseurSolive()){
                    break;
                }
                espacementSolive = valeurChamp;
                epaisseurSolive = InfoPatio.getEpaisseurSolive();
                float largeurPatio = InfoPatio.getLargeurPatio();
                largeurCalculee = epaisseurSolive;
                for (nbSoliveParEspacement = 1; largeurCalculee <= largeurPatio - epaisseurSolive * 3; nbSoliveParEspacement++){
                    largeurCalculee += espacementSolive + epaisseurSolive;
                }
                while (largeurCalculee > InfoPatio.getLongueurPlanche() - epaisseurSolive & espacementSolive > epaisseurSolive){
                    espacementSolive -= 0.00390625f;
                    largeurCalculee = epaisseurSolive;
                    for (nbSoliveParEspacement = 1; largeurCalculee <= largeurPatio - epaisseurSolive * 3; nbSoliveParEspacement++){
                        largeurCalculee += espacementSolive + epaisseurSolive;
                    }
                }
                if (largeurCalculee > InfoPatio.getLongueurPlanche()){
                    nbSoliveParEspacement--;
                }
                nbSolive = nbSoliveParEspacement * (InfoPatio.getNbPoutre() - 1);
                InfoPatio.setEspacementSolive(espacementSolive);
                InfoPatio.setNbSolive(nbSolive);
                InfoPatio.calculPrixTotal();
                break;
            case DIMENSION_POUTRE:
                InfoPatio.setLargeurPoutre(valeurChamp);
                updatePatio(ChampSaisie.HAUTEUR_PATIO, InfoPatio.getHauteurPatio());
                InfoPatio.calculPrixTotal();
                break;
            case DIMENSION_POTEAU:
                InfoPatio.setLargeurPoteau(valeurChamp);
                InfoPatio.setEpaisseurPoteau(valeurChamp);
                InfoPatio.calculPrixTotal();
                break;
        }

    }
    public void updatePatio(ChampSaisie NomChamp, int valeurChamp){

        switch (NomChamp) {
            case NOMBRE_POUTRE:
                if (valeurChamp == 1){
                    break;
                }
                float longueurInitialSansPAF = InfoPatio.getLongueurPatio() -
                        InfoPatio.getPorteAFauxPourNbPoutre() - InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
                float espacementPoutre = longueurInitialSansPAF / (valeurChamp - 1);
                int nbSoliveParEspace = InfoPatio.getNbSolive() / (InfoPatio.getNbPoutre() - 1);
                InfoPatio.setNbPoutre(valeurChamp);
                float longueurPatio = InfoPatio.getLongueurPatio();
                int nbSolive = nbSoliveParEspace * (valeurChamp - 1);
                float longueurSansPAF;
                float PorteAFaux;
                float SolivePorteAFaux;
                if (valeurChamp == 2){
                    longueurSansPAF = (valeurChamp - 1) * espacementPoutre
                            + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
                    PorteAFaux = longueurPatio - longueurSansPAF;
                    SolivePorteAFaux = PorteAFaux + espacementPoutre + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
                }
                else {
                    longueurSansPAF = (valeurChamp - 1) * espacementPoutre
                            + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2()
                            - (InfoPatio.getEpaisseurPoutre() / 2) * - (3 - InfoPatio.getNbPliesPoutreDouble());
                    PorteAFaux = longueurPatio - longueurSansPAF;
                    SolivePorteAFaux = PorteAFaux + espacementPoutre + 0.75f - 0.75f * (InfoPatio.getNbPliesPoutreSimple_2() - 2) + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
                }
                InfoPatio.setNbSolive(nbSolive);
                InfoPatio.setSolivePorteAFaux(SolivePorteAFaux);
                float longueurSoliveMaison = espacementPoutre +
                        (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_1()) / 2
                        + (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble()) / 2;
                float longueurSoliveCentre = espacementPoutre + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble();
                InfoPatio.setLongueurSoliveMaison(longueurSoliveMaison);
                InfoPatio.setLongueurSoliveCentre(longueurSoliveCentre);
                InfoPatio.setEspacementPoutre(espacementPoutre);
                InfoPatio.setPorteAFaux(PorteAFaux);
                InfoPatio.calculPrixTotal();
                break;
            case NOMBRE_POTEAU:
                InfoPatio.setNbPoteauParPoutre(valeurChamp);
                float espacementPoteau = InfoPatio.getLongueurPoutre() / (valeurChamp - 1);
                InfoPatio.setEspacementPoteau(espacementPoteau);
                InfoPatio.calculPrixTotal();
                break;
            case NB_PLIE_SIMPLE_1:
                if (valeurChamp >= 4)
                {
                    break;
                }
                if (valeurChamp == 3)
                {
                    InfoPatio.setLargeurPoteau(5.5f);
                    InfoPatio.setEpaisseurPoteau(5.5f);
                }
                if (InfoPatio.getPorteAFaux() < valeurChamp * InfoPatio.getEpaisseurPoutre() &
                        valeurChamp > InfoPatio.getNbPliesPoutreSimple_1()){
                    updatePatio(ChampSaisie.ESPACE_POUTRE, InfoPatio.getEspacementPoutre() -
                            valeurChamp * InfoPatio.getEpaisseurPoutre());
                }
                InfoPatio.setNbPliesPoutreSimple_1(valeurChamp);
                InfoPatio.setNbPliesPoutreSimple_2(valeurChamp);
                longueurSoliveMaison = InfoPatio.getEspacementPoutre() +
                        (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_1()) / 2
                        + (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble()) / 2;
                longueurSoliveCentre = InfoPatio.getEspacementPoutre() + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble();
                InfoPatio.setLongueurSoliveMaison(longueurSoliveMaison);
                InfoPatio.setLongueurSoliveCentre(longueurSoliveCentre);
                if (InfoPatio.getNbPoutre() == 2){
                    longueurSansPAF = (InfoPatio.getNbPoutre() - 1) * InfoPatio.getEspacementPoutre()
                            + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
                    PorteAFaux = InfoPatio.getLongueurPatio() - longueurSansPAF;
                    SolivePorteAFaux = InfoPatio.getLongueurPatio();
                }
                else {
                    longueurSansPAF = (InfoPatio.getNbPoutre() - 1) * InfoPatio.getEspacementPoutre()
                            + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2()
                            - (InfoPatio.getEpaisseurPoutre() / 2) * - (3 - InfoPatio.getNbPliesPoutreDouble());
                    PorteAFaux = InfoPatio.getLongueurPatio() - longueurSansPAF;
                    SolivePorteAFaux = PorteAFaux + InfoPatio.getEspacementPoutre() + 0.75f - 0.75f * (InfoPatio.getNbPliesPoutreSimple_2() - 2)
                            + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
                }
                InfoPatio.setSolivePorteAFaux(SolivePorteAFaux);
                InfoPatio.setPorteAFaux(PorteAFaux);
                InfoPatio.setPorteAFauxPourNbPoutre(PorteAFaux);
                InfoPatio.calculPrixTotal();
                break;
            case NB_PLIE_DOUBLE:
                if (valeurChamp >= 4)
                {
                    break;
                }
                if (valeurChamp == 3)
                {
                    InfoPatio.setLargeurPoteau(5.5f);
                    InfoPatio.setEpaisseurPoteau(5.5f);
                }
                InfoPatio.setNbPliesPoutreDouble(valeurChamp);
                longueurSoliveMaison = InfoPatio.getEspacementPoutre() +
                        (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_1()) / 2
                        + (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble()) / 2;
                longueurSoliveCentre = InfoPatio.getEspacementPoutre() + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble();
                InfoPatio.setLongueurSoliveMaison(longueurSoliveMaison);
                InfoPatio.setLongueurSoliveCentre(longueurSoliveCentre);
                if (InfoPatio.getNbPoutre() == 2){
                    longueurSansPAF = (InfoPatio.getNbPoutre() - 1) * InfoPatio.getEspacementPoutre()
                            + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
                    PorteAFaux = InfoPatio.getLongueurPatio() - longueurSansPAF;
                    SolivePorteAFaux = InfoPatio.getLongueurPatio();
                }
                else {
                    longueurSansPAF = (InfoPatio.getNbPoutre() - 1) * InfoPatio.getEspacementPoutre()
                            + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2()
                            - (InfoPatio.getEpaisseurPoutre() / 2) * - (3 - InfoPatio.getNbPliesPoutreDouble());
                    PorteAFaux = InfoPatio.getLongueurPatio() - longueurSansPAF;
                    SolivePorteAFaux = PorteAFaux + InfoPatio.getEspacementPoutre() + 0.75f - 0.75f * (InfoPatio.getNbPliesPoutreSimple_2() - 2) + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
                }
                InfoPatio.setSolivePorteAFaux(SolivePorteAFaux);
                InfoPatio.setPorteAFaux(PorteAFaux);
                InfoPatio.setPorteAFauxPourNbPoutre(PorteAFaux);
                InfoPatio.calculPrixTotal();
                break;
        }

    }
}
