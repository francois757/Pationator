package lePationator.domain;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * @author Douch Ayoub
 */
public class Ingenieur {

    public Ingenieur(){
    }
    private int[][] tabGuideSolive = {
            {0, 8, 12, 16, 24, 0},
            {4, 88, 77, 70, 61, 8},
            {6, 138, 120, 109, 96, 16},
            {8, 181, 158, 144, 122, 16},
            {10, 231, 202, 182, 149, 24},
            {12, 281, 244, 211, 172, 24}
    };
    private int[][] tabGuidePoutre = {
            {0, 48, 72, 96, 0, 0, 0},
            {48, 1, 6, 1, 6, 2, 6},
            {60, 1, 6, 1, 6, 2, 6},
            {72, 1, 6, 1, 6, 2, 6},
            {84, 1, 6, 1, 6, 2, 6},
            {96, 1, 6, 2, 6, 2, 8},
            {108, 1, 6, 2, 6, 2, 8},
            {120, 1, 6, 2, 6, 2, 8},
            {132, 1, 6, 2, 6, 2, 8},
            {144, 1, 6, 2, 6, 2, 10},
            {156, 1, 6, 2, 6, 2, 10},
            {168, 1, 6, 2, 6, 2, 10},
            {180, 1, 6, 2, 8, 2, 10},
            {192, 2, 6, 2, 8, 2, 10}
    };
    private int[][] tabGuidePoutreDouble = {
            {0, 48, 72, 96, 0, 0, 0},
            {48, 1, 6, 2, 6, 2, 8},
            {60, 1, 6, 2, 6, 2, 8},
            {72, 1, 6, 2, 6, 2, 10},
            {84, 1, 6, 2, 6, 2, 10},
            {96, 2, 6, 2, 8, 2, 10},
            {108, 2, 6, 2, 8, 2, 12},
            {120, 2, 6, 2, 8, 2, 12},
            {132, 2, 6, 2, 10, 2, 12},
            {144, 2, 6, 2, 10, 3, 10},
            {156, 2, 6, 2, 10, 3, 10},
            {168, 2, 6, 2, 10, 3, 10},
            {180, 2, 6, 2, 10, 3, 12},
            {192, 2, 8, 2, 12, 3, 12}
    };

    public boolean validerPation(InformationPatio MesureChampSaisie){

        int index_ranger_tab_Guide_Solive;
        if (MesureChampSaisie.getLargeurSolive() == 3.5){
            index_ranger_tab_Guide_Solive = 1;
        }
        else if (MesureChampSaisie.getLargeurSolive() == 5.5){
            index_ranger_tab_Guide_Solive = 2;
        }
        else if (MesureChampSaisie.getLargeurSolive() == 7.25){
            index_ranger_tab_Guide_Solive = 3;
        }
        else if (MesureChampSaisie.getLargeurSolive() == 9.25){
            index_ranger_tab_Guide_Solive = 4;
        }
        else if (MesureChampSaisie.getLargeurSolive() == 11.25){
            index_ranger_tab_Guide_Solive = 5;
        }
        else{
            MesureChampSaisie.setSoliveValide(false);
            return false;
        }
        int index_colonne_tab_Guide_Solive = 0;
        if (MesureChampSaisie.getEspacementSolive() <= 8f){
            index_colonne_tab_Guide_Solive = 1;
        }
        if (MesureChampSaisie.getEspacementSolive() <= 12f & MesureChampSaisie.getEspacementSolive() > 8f){
            index_colonne_tab_Guide_Solive = 2;
        }
        if (MesureChampSaisie.getEspacementSolive() <= 16f & MesureChampSaisie.getEspacementSolive() > 12f){
            index_colonne_tab_Guide_Solive = 3;
        }
        if (MesureChampSaisie.getEspacementSolive() <= 24f & MesureChampSaisie.getEspacementSolive() > 16f){
            index_colonne_tab_Guide_Solive = 4;
        }
        if (MesureChampSaisie.getEspacementSolive() > 24f){
            MesureChampSaisie.setSoliveValide(false);
            return false;
        }

        float longueurSansPAF = (MesureChampSaisie.getNbPoutre() - 1) * MesureChampSaisie.getEspacementPoutre()
                + MesureChampSaisie.getEpaisseurPoutre() * MesureChampSaisie.getNbPliesPoutreSimple_2();

        if (tabGuideSolive[index_ranger_tab_Guide_Solive][index_colonne_tab_Guide_Solive] < MesureChampSaisie.getEspacementPoutre()){
            MesureChampSaisie.setSoliveValide(false);
            return false;
        }
        MesureChampSaisie.setSoliveValide(true);

        float porteAFaux = MesureChampSaisie.getLongueurPatio() - longueurSansPAF;
        if (tabGuideSolive[index_ranger_tab_Guide_Solive][5] < porteAFaux){
            MesureChampSaisie.setPorteAFauxValide(false);
            return false;
        }
        MesureChampSaisie.setPorteAFauxValide(true);

        double espacementPoutre = Math.ceil(MesureChampSaisie.getEspacementPoutre());
        double porteSolive = Math.ceil(espacementPoutre / 12);

        int index_ranger_tabGuidePoutre;
        if (porteSolive <= 4.0){
            index_ranger_tabGuidePoutre = 1;
        }
        else if (porteSolive == 5.0){
            index_ranger_tabGuidePoutre = 2;
        }
        else if (porteSolive == 6.0){
            index_ranger_tabGuidePoutre = 3;
        }
        else if (porteSolive == 7.0){
            index_ranger_tabGuidePoutre = 4;
        }
        else if (porteSolive == 8.0){
            index_ranger_tabGuidePoutre = 5;
        }
        else if (porteSolive == 9.0){
            index_ranger_tabGuidePoutre = 6;
        }
        else if (porteSolive == 10.0){
            index_ranger_tabGuidePoutre = 7;
        }
        else if (porteSolive == 11.0){
            index_ranger_tabGuidePoutre = 8;
        }
        else if (porteSolive == 12.0){
            index_ranger_tabGuidePoutre = 9;
        }
        else if (porteSolive == 13.0){
            index_ranger_tabGuidePoutre = 10;
        }
        else if (porteSolive == 14.0){
            index_ranger_tabGuidePoutre = 11;
        }
        else if (porteSolive == 15.0){
            index_ranger_tabGuidePoutre = 12;
        }
        else if (porteSolive == 16.0){
            index_ranger_tabGuidePoutre = 13;
        }
        else{
            MesureChampSaisie.setPoutreSimpleValide(false);
            return false;
        }

        int index_colonne_tabGuidePoutre = 0;
        if (MesureChampSaisie.getEspacementPoteau() <= 4*12){
            index_colonne_tabGuidePoutre = 2;
        }
        if (MesureChampSaisie.getEspacementPoteau() <= 6*12 & MesureChampSaisie.getEspacementPoteau() > 4*12){
            index_colonne_tabGuidePoutre = 4;
        }
        if (MesureChampSaisie.getEspacementPoteau() <= 8*12 & MesureChampSaisie.getEspacementPoteau() > 6*12){
            index_colonne_tabGuidePoutre = 6;
        }
        if (MesureChampSaisie.getEspacementPoteau() > 8*12){
            MesureChampSaisie.setPoutreSimpleValide(false);
            return false;
        }

        boolean verificationPoutre = true;

        double largeurPoutreVersionDouble = Math.ceil(MesureChampSaisie.getLargeurPoutre());
        float largeurPoutre = (float) largeurPoutreVersionDouble;
        int nbPliePoutreSimple = MesureChampSaisie.getNbPliesPoutreSimple_1();

        if ((tabGuidePoutre[index_ranger_tabGuidePoutre][index_colonne_tabGuidePoutre] > largeurPoutre)){
            MesureChampSaisie.setPoutreSimpleValide(false);
            verificationPoutre = false;
        }
        else{
            MesureChampSaisie.setPoutreSimpleValide(true);
        }
        if (tabGuidePoutre[index_ranger_tabGuidePoutre][index_colonne_tabGuidePoutre - 1] > nbPliePoutreSimple){
            MesureChampSaisie.setPlieSimpleValide(false);
            verificationPoutre = false;
        }
        else{
            MesureChampSaisie.setPlieSimpleValide(true);
        }

        if ((!verificationPoutre) & (MesureChampSaisie.getLargeurPoutre() >= 7.25f) & (MesureChampSaisie.getNbPliesPoutreSimple_1() == 3) &
                (tabGuidePoutre[index_ranger_tabGuidePoutre][index_colonne_tabGuidePoutre] >= 9.25f)
                & (tabGuidePoutre[index_ranger_tabGuidePoutre][index_colonne_tabGuidePoutre - 1] == 2)){
            verificationPoutre = true;
            MesureChampSaisie.setPoutreSimpleValide(true);
            MesureChampSaisie.setPlieSimpleValide(true);
        }

        if (!verificationPoutre){
            return false;
        }

        int index_ranger_tabGuidePoutreDouble;
        if (porteSolive <= 4.0){
            index_ranger_tabGuidePoutreDouble = 1;
        }
        else if (porteSolive == 5.0){
            index_ranger_tabGuidePoutreDouble = 2;
        }
        else if (porteSolive == 6.0){
            index_ranger_tabGuidePoutreDouble = 3;
        }
        else if (porteSolive == 7.0){
            index_ranger_tabGuidePoutreDouble = 4;
        }
        else if (porteSolive == 8.0){
            index_ranger_tabGuidePoutreDouble = 5;
        }
        else if (porteSolive == 9.0){
            index_ranger_tabGuidePoutreDouble = 6;
        }
        else if (porteSolive == 10.0){
            index_ranger_tabGuidePoutreDouble = 7;
        }
        else if (porteSolive == 11.0){
            index_ranger_tabGuidePoutreDouble = 8;
        }
        else if (porteSolive == 12.0){
            index_ranger_tabGuidePoutreDouble = 9;
        }
        else if (porteSolive == 13.0){
            index_ranger_tabGuidePoutreDouble = 10;
        }
        else if (porteSolive == 14.0){
            index_ranger_tabGuidePoutreDouble = 11;
        }
        else if (porteSolive == 15.0){
            index_ranger_tabGuidePoutreDouble = 12;
        }
        else if (porteSolive == 16.0){
            index_ranger_tabGuidePoutreDouble = 13;
        }
        else{
            MesureChampSaisie.setPoutreDoubleValide(false);
            return false;
        }

        int index_colonne_tabGuidePoutreDouble = 0;
        if (MesureChampSaisie.getEspacementPoteau() <= 4*12){
            index_colonne_tabGuidePoutreDouble = 2;
        }
        else if (MesureChampSaisie.getEspacementPoteau() <= 6*12 & MesureChampSaisie.getEspacementPoteau() > 4*12){
            index_colonne_tabGuidePoutreDouble = 4;
        }
        else if (MesureChampSaisie.getEspacementPoteau() <= 8*12 & MesureChampSaisie.getEspacementPoteau() > 6*12){
            index_colonne_tabGuidePoutreDouble = 6;
        }
        if (MesureChampSaisie.getEspacementPoteau() > 8*12){
            MesureChampSaisie.setPoutreDoubleValide(false);
            return false;
        }

        boolean verificationPoutreDouble = true;

        if ((tabGuidePoutreDouble[index_ranger_tabGuidePoutreDouble][index_colonne_tabGuidePoutreDouble] > largeurPoutre)){
            MesureChampSaisie.setPoutreDoubleValide(false);
            verificationPoutreDouble = false;
        }
        else{
            MesureChampSaisie.setPoutreDoubleValide(true);
        }
        if (tabGuidePoutreDouble[index_ranger_tabGuidePoutreDouble][index_colonne_tabGuidePoutreDouble - 1] > MesureChampSaisie.getNbPliesPoutreDouble()){
            MesureChampSaisie.setPlieDoubleValide(false);
            verificationPoutreDouble = false;
        }
        else{
            MesureChampSaisie.setPlieDoubleValide(true);
        }

        if ((!verificationPoutreDouble) & (MesureChampSaisie.getLargeurPoutre() >= 7.25f) & (MesureChampSaisie.getNbPliesPoutreDouble() == 3) &
                (tabGuidePoutreDouble[index_ranger_tabGuidePoutreDouble][index_colonne_tabGuidePoutreDouble] >= 9.25f)
                & (tabGuidePoutreDouble[index_ranger_tabGuidePoutreDouble][index_colonne_tabGuidePoutreDouble - 1] == 2)){
            verificationPoutreDouble = true;
            MesureChampSaisie.setPlieDoubleValide(true);
            MesureChampSaisie.setPoutreDoubleValide(true);
        }

        if ((!verificationPoutreDouble) & (MesureChampSaisie.getLargeurPoutre() >= 9.25f) & (MesureChampSaisie.getNbPliesPoutreDouble() == 3) &
                (tabGuidePoutreDouble[index_ranger_tabGuidePoutreDouble][index_colonne_tabGuidePoutreDouble] >= 11.25f)
                & (tabGuidePoutreDouble[index_ranger_tabGuidePoutreDouble][index_colonne_tabGuidePoutreDouble - 1] == 2)){
            verificationPoutreDouble = true;
            MesureChampSaisie.setPlieDoubleValide(true);
            MesureChampSaisie.setPoutreDoubleValide(true);
        }

        if (!verificationPoutreDouble & MesureChampSaisie.getNbPoutre() != 2){
            return false;
        }

        if (MesureChampSaisie.getLongueurPoteau() > 78f & MesureChampSaisie.getLargeurPoteau() == 3.5f){
            MesureChampSaisie.setPoteauxValide(false);
            return false;
        }
        MesureChampSaisie.setPoteauxValide(true);

        return !((MesureChampSaisie.getEpaisseurPlanche() == 1.25f) & MesureChampSaisie.getEspacementSolive() > 12f);
    }

    private boolean verifierNombrePoteauParPoutre(int nombrePoteauxParPoutre,float espacementPoteau,int epaisseurPoteaux){
        return epaisseurPoteaux <= (espacementPoteau/(nombrePoteauxParPoutre-1));
    }

    public InformationPatio construirePationMoinsCher(InformationPatio InfoPatio){
        InfoPatio.setEpaisseurPoteau(3.5f);
        InfoPatio.setLargeurPoteau(3.5f);
        double nbPoteauNonArrondie = InfoPatio.getLongueurPoutre() / 96;
        int nbPoteauParPoutre = (int) Math.ceil(nbPoteauNonArrondie) + 1;
        float espacemementpoteau = InfoPatio.getLongueurPoutre() / (nbPoteauParPoutre - 1);
        InfoPatio.setEspacementPoteau(espacemementpoteau);
        InfoPatio.setNbPoteauParPoutre(nbPoteauParPoutre);
        if (InfoPatio.getEpaisseurPlanche() == 1.5f){
            ajusterEspacementSolive(InfoPatio, 24);
        }
        else{
            ajusterEspacementSolive(InfoPatio, 12);
        }

        float listePrix[] = {InfoPatio.getPrix2x4(), InfoPatio.getPrix2x6(), InfoPatio.getPrix2x8()
        , InfoPatio.getPrix2x10(), InfoPatio.getPrix2x12()};
        Arrays.sort(listePrix);
        float largeurSolive = determinerSoliveMoinsCher(InfoPatio, listePrix);
        float espacementPoutre = determinerEspacementPoutre(InfoPatio, largeurSolive);
        InfoPatio.setEspacementPoutre(espacementPoutre);
        InfoPatio.setLargeurSolive(largeurSolive);
        ajusterHauteurPatio(InfoPatio, InfoPatio.getHauteurPatio());
        creationPatioValide(InfoPatio, listePrix, largeurSolive, 1, 1);
        nbPoteauParPoutre = determinerNombreDePoteauParPoutre(InfoPatio, listePrix);
        espacemementpoteau = InfoPatio.getLongueurPoutre() / (nbPoteauParPoutre - 1);
        InfoPatio.setEspacementPoteau(espacemementpoteau);
        InfoPatio.setNbPoteauParPoutre(nbPoteauParPoutre);
        espacementPoutre = determinerEspacementPoutre(InfoPatio, largeurSolive);
        InfoPatio.setEspacementPoutre(espacementPoutre);
        if (largeurSolive != determinerPiece(InfoPatio, listePrix[0])){
            largeurSolive = determinerSoliveMoinsCher(InfoPatio, listePrix);
            espacementPoutre = determinerEspacementPoutre(InfoPatio, largeurSolive);
            InfoPatio.setEspacementPoutre(espacementPoutre);
            InfoPatio.setLargeurSolive(largeurSolive);
            ajusterHauteurPatio(InfoPatio, InfoPatio.getHauteurPatio());
        }
        InfoPatio.setEpaisseurPoteau(3.5f);
        InfoPatio.setLargeurPoteau(3.5f);
        creationPatioValide(InfoPatio, listePrix, largeurSolive, 1, 1);
        if (InfoPatio.getNbPliesPoutreDouble() == 3 || InfoPatio.getNbPliesPoutreSimple_1() == 3)
        {
            InfoPatio.setEpaisseurPoteau(5.5f);
            InfoPatio.setLargeurPoteau(5.5f);
            InfoPatio.calculPrixTotal();
        }
        return InfoPatio;
    }

    private void ajusterEspacePoutre(InformationPatio InfoPatio , float espacementPoutre){
        int nbSoliveParEspace = InfoPatio.getNbSolive() / (InfoPatio.getNbPoutre() - 1);
        float longueurPatio = InfoPatio.getLongueurPatio();
        float longueurCalculee = espacementPoutre;
        int nbSoliveSurLongueur;
        int nbPoutre = 2;
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
        float longueurSansPAF;
        float PorteAFaux;
        float SolivePorteAFaux;
        if (nbPoutre == 2){
            longueurSansPAF = (nbPoutre - 1) * espacementPoutre
                    + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
            PorteAFaux = InfoPatio.getLongueurPatio() - longueurSansPAF;
            SolivePorteAFaux = InfoPatio.getLongueurPatio() - InfoPatio.getEpaisseurPlanche();
        }
        else {
            longueurSansPAF = (nbPoutre - 1) * espacementPoutre
                    + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2()
                    - (InfoPatio.getEpaisseurPoutre() / 2) * - (3 - InfoPatio.getNbPliesPoutreDouble());
            PorteAFaux = InfoPatio.getLongueurPatio() - longueurSansPAF;
            SolivePorteAFaux = PorteAFaux + espacementPoutre + 0.75f - 0.75f * (InfoPatio.getNbPliesPoutreSimple_2() - 2) + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
        }
        int nbSolive = nbSoliveParEspace * nbSoliveSurLongueur;
        float longueurSoliveMaison = espacementPoutre +
                (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_1()) / 2
                + (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble()) / 2;
        float longueurSoliveCentre = espacementPoutre + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble();
        InfoPatio.setNbSolive(nbSolive);
        InfoPatio.setNbPoutre(nbPoutre);
        InfoPatio.setSolivePorteAFaux(SolivePorteAFaux);
        InfoPatio.setEspacementPoutre(espacementPoutre);
        InfoPatio.setLongueurSoliveMaison(longueurSoliveMaison);
        InfoPatio.setLongueurSoliveCentre(longueurSoliveCentre);
        InfoPatio.setPorteAFaux(PorteAFaux);
        InfoPatio.setPorteAFauxPourNbPoutre(PorteAFaux);
        InfoPatio.calculPrixTotal();
    }

    private void ajusterEspacementSolive(InformationPatio InfoPatio, float espacementSolive){
        float epaisseurSolive = InfoPatio.getEpaisseurSolive();
        float largeurPatio = InfoPatio.getLargeurPatio();
        float largeurCalculee = epaisseurSolive;
        int nbSoliveParEspacement;
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
        int nbSolive = nbSoliveParEspacement * (InfoPatio.getNbPoutre() - 1);
        InfoPatio.setEspacementSolive(espacementSolive);
        InfoPatio.setNbSolive(nbSolive);
        InfoPatio.calculPrixTotal();
    }

    private float determinerPiece(InformationPatio InfoPatio, float prix)
    {
        if (prix == InfoPatio.getPrix2x4() & prix != InfoPatio.getPrix2x6()
        & prix != InfoPatio.getPrix2x8() & prix != InfoPatio.getPrix2x10()
        & prix != InfoPatio.getPrix2x12()) {return 3.5f;}
        else if (prix == InfoPatio.getPrix2x6() & prix != InfoPatio.getPrix2x8()
                & prix != InfoPatio.getPrix2x10()
                & prix != InfoPatio.getPrix2x12()) {return 5.5f;}
        else if (prix == InfoPatio.getPrix2x8() & prix != InfoPatio.getPrix2x10()
                & prix != InfoPatio.getPrix2x12()) {return 7.25f;}
        else if (prix == InfoPatio.getPrix2x10() & prix != InfoPatio.getPrix2x12()) {return 9.25f;}
        else {return 11.25f;}
    }

    private float determinerEspacementPoutre(InformationPatio InfoPatio, float largeurSolive){
        float espacementPoutre;
        if (InfoPatio.getEpaisseurPlanche() == 1.5f && InfoPatio.getEspacementSolive() > 16){
            if (largeurSolive == 3.5f) {espacementPoutre = 61f;}
            else if (largeurSolive == 5.5f) {espacementPoutre = 96f;}
            else if (largeurSolive == 7.25f) {espacementPoutre = 122f;}
            else if (largeurSolive == 9.25f) {espacementPoutre = 149f;}
            else {espacementPoutre = 172f;}
        }
        else if (InfoPatio.getEpaisseurPlanche() == 1.5f && InfoPatio.getEspacementSolive() <= 16)
        {
            if (largeurSolive == 3.5f) {espacementPoutre = 70f;}
            else if (largeurSolive == 5.5f) {espacementPoutre = 109f;}
            else if (largeurSolive == 7.25f) {espacementPoutre = 144f;}
            else if (largeurSolive == 9.25f) {espacementPoutre = 182f;}
            else {espacementPoutre = 192f;}
        }
        else{
            if (largeurSolive == 3.5f) {espacementPoutre = 77f;}
            else if (largeurSolive == 5.5f) {espacementPoutre = 120f;}
            else if (largeurSolive == 7.25f) {espacementPoutre = 158f;}
            else if (largeurSolive == 9.25f) {espacementPoutre = 192f;}
            else {espacementPoutre = 192f;}
        }

        if (espacementPoutre > InfoPatio.getLongueurPatio() - InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2()){
            espacementPoutre = InfoPatio.getLongueurPatio() - InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
        }
        return espacementPoutre;
    }

    private void ajusterPorteAFaux(InformationPatio InfoPatio, float longueurPorteAFaux){
        float longueurSansPAF = InfoPatio.getLongueurPatio() - longueurPorteAFaux;
        int nbPoutre = InfoPatio.getNbPoutre();
        int nbSoliveColonne = InfoPatio.getNbSolive() / (InfoPatio.getNbPoutre() - 1);
        float espacementPoutre = (longueurSansPAF -
                InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2())
                / (nbPoutre - 1);
        float espacementMax = determinerEspacementPoutre(InfoPatio, InfoPatio.getLargeurSolive());
        while (espacementPoutre > espacementMax){
            nbPoutre++;
            espacementPoutre = (longueurSansPAF -
                    InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2())
                    / (nbPoutre - 1);
        }
        int nbSolives = nbSoliveColonne * (nbPoutre - 1);
        InfoPatio.setNbSolive(nbSolives);
        InfoPatio.setNbPoutre(nbPoutre);
        InfoPatio.setEspacementPoutre(espacementPoutre);
        InfoPatio.setPorteAFaux(longueurPorteAFaux);
        float longueurSoliveMaison = espacementPoutre +
                (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_1()) / 2
                + (InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble()) / 2;
        float longueurSoliveCentre = espacementPoutre + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreDouble();
        float SolivePorteAFaux;
        if (InfoPatio.getNbPliesPoutreSimple_2() != 1){
            SolivePorteAFaux = longueurPorteAFaux + InfoPatio.getEspacementPoutre() - 0.75f * (InfoPatio.getNbPliesPoutreSimple_2() - 2) + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
        }
        else{
            SolivePorteAFaux = longueurPorteAFaux + InfoPatio.getEspacementPoutre() + InfoPatio.getEpaisseurPoutre() * InfoPatio.getNbPliesPoutreSimple_2();
        }
        InfoPatio.setLongueurSoliveMaison(longueurSoliveMaison);
        InfoPatio.setLongueurSoliveCentre(longueurSoliveCentre);
        InfoPatio.setSolivePorteAFaux(SolivePorteAFaux);
        InfoPatio.setPorteAFauxPourNbPoutre(longueurPorteAFaux);
        InfoPatio.calculPrixTotal();
    }

    private void ajusterHauteurPatio(InformationPatio InfoPatio, float hauteur){
        float epaisseurPlanche = InfoPatio.getEpaisseurPlanche();
        float largeurSolive = InfoPatio.getLargeurSolive();
        float largeurPoutre = InfoPatio.getLargeurPoutre();
        float longueurPoteau = hauteur - epaisseurPlanche - largeurSolive - largeurPoutre;
        InfoPatio.setHauteurPatio(hauteur);
        InfoPatio.setLongueurPoteau(longueurPoteau);
        InfoPatio.calculPrixTotal();
    }

    private void creationPatioValide(InformationPatio InfoPatio, float listePrix[],
                                     float largeurSolive, int nbPliePoutreSimple, int nbPliePoutreDouble){
        int compteurSolive = 1;
        int compteurPoutreSimple = 1;
        int compteurPoutreDouble = 1;
        InfoPatio.setNbPliesPoutreSimple_1(nbPliePoutreSimple);
        InfoPatio.setNbPliesPoutreSimple_2(nbPliePoutreSimple);
        InfoPatio.setNbPliesPoutreDouble(nbPliePoutreDouble);
        float largeurPoutre = determinerPiece(InfoPatio, listePrix[0]);
        InfoPatio.setLargeurPoutre(largeurPoutre);
        ajusterEspacePoutre(InfoPatio, InfoPatio.getEspacementPoutre());
        while (!(validerPation(InfoPatio))){
            if (!(InfoPatio.getSoliveValide())){
                largeurSolive = determinerPiece(InfoPatio, listePrix[compteurSolive]);
                InfoPatio.setLargeurSolive(largeurSolive);
                ajusterEspacePoutre(InfoPatio, InfoPatio.getEspacementPoutre());
                ajusterHauteurPatio(InfoPatio, InfoPatio.getHauteurPatio());
                compteurSolive++;
            }

            else if(!(InfoPatio.getPorteAFauxValide())){
                float longueurPorteAFaux;
                if (largeurSolive == 3.5f) {longueurPorteAFaux = 8f;}
                else if (largeurSolive == 5.5f) {longueurPorteAFaux = 16f;}
                else if (largeurSolive == 7.25f) {longueurPorteAFaux = 16f;}
                else if (largeurSolive == 9.25f) {longueurPorteAFaux = 24f;}
                else {longueurPorteAFaux = 24f;}
                ajusterPorteAFaux(InfoPatio, longueurPorteAFaux);
            }

            else if (!(InfoPatio.getPoutreSimpleValide())){
                largeurPoutre = determinerPiece(InfoPatio, listePrix[compteurPoutreSimple]);
                InfoPatio.setLargeurPoutre(largeurPoutre);
                ajusterHauteurPatio(InfoPatio, InfoPatio.getHauteurPatio());
                compteurPoutreSimple++;
            }

            else if (!(InfoPatio.getPlieSimpleValide())){
                nbPliePoutreSimple++;
                InfoPatio.setNbPliesPoutreSimple_1(nbPliePoutreSimple);
                InfoPatio.setNbPliesPoutreSimple_2(nbPliePoutreSimple);
                ajusterEspacePoutre(InfoPatio, InfoPatio.getEspacementPoutre());
            }

            else if(!(InfoPatio.getPoutreDoubleValide())){
                largeurPoutre = determinerPiece(InfoPatio, listePrix[compteurPoutreDouble]);
                InfoPatio.setLargeurPoutre(largeurPoutre);
                ajusterHauteurPatio(InfoPatio, InfoPatio.getHauteurPatio());
                compteurPoutreDouble++;
            }

            else if(!(InfoPatio.getPlieDoubleValide())){
                nbPliePoutreDouble++;
                InfoPatio.setNbPliesPoutreDouble(nbPliePoutreDouble);
                ajusterEspacePoutre(InfoPatio, InfoPatio.getEspacementPoutre());
            }

            else if (!(InfoPatio.getPoteauxValide())){
                InfoPatio.setLargeurPoteau(5.5f);
                InfoPatio.setEpaisseurPoteau(5.5f);
            }
        }
        InfoPatio.calculPrixTotal();
    }

    private float determinerSoliveMoinsCher(InformationPatio InfoPatio, float listePrix[]){
        float listePrixParSolive[] = {0, 0, 0, 0, 0};
        float espacementPoutre = determinerEspacementPoutre(InfoPatio, 3.5f);
        ajusterEspacePoutre(InfoPatio, espacementPoutre);
        InfoPatio.setLargeurSolive(3.5f);
        ajusterHauteurPatio(InfoPatio, InfoPatio.getHauteurPatio());
        InfoPatio.setEpaisseurPoteau(3.5f);
        InfoPatio.setLargeurPoteau(3.5f);
        creationPatioValide(InfoPatio, listePrix, 3.5f, 1, 1);
        float prixSiSolive2x4 = InfoPatio.getPrixTotal();
        if (InfoPatio.getLargeurSolive() != 3.5f){
            prixSiSolive2x4 = Float.POSITIVE_INFINITY;
        }
        listePrixParSolive[0] = prixSiSolive2x4;
        espacementPoutre = determinerEspacementPoutre(InfoPatio, 5.5f);
        ajusterEspacePoutre(InfoPatio, espacementPoutre);
        InfoPatio.setLargeurSolive(5.5f);
        ajusterHauteurPatio(InfoPatio, InfoPatio.getHauteurPatio());
        InfoPatio.setEpaisseurPoteau(3.5f);
        InfoPatio.setLargeurPoteau(3.5f);
        creationPatioValide(InfoPatio, listePrix, 5.5f, 1, 1);
        float prixSiSolive2x6 = InfoPatio.getPrixTotal();
        if (InfoPatio.getLargeurSolive() != 5.5f){
            prixSiSolive2x6 = Float.POSITIVE_INFINITY;
        }
        listePrixParSolive[1] = prixSiSolive2x6;
        espacementPoutre = determinerEspacementPoutre(InfoPatio, 7.25f);
        ajusterEspacePoutre(InfoPatio, espacementPoutre);
        InfoPatio.setLargeurSolive(7.25f);
        ajusterHauteurPatio(InfoPatio, InfoPatio.getHauteurPatio());
        InfoPatio.setEpaisseurPoteau(3.5f);
        InfoPatio.setLargeurPoteau(3.5f);
        creationPatioValide(InfoPatio, listePrix, 7.25f, 1, 1);
        float prixSiSolive2x8 = InfoPatio.getPrixTotal();
        if (InfoPatio.getLargeurSolive() != 7.25f){
            prixSiSolive2x8 = Float.POSITIVE_INFINITY;
        }
        listePrixParSolive[2] = prixSiSolive2x8;
        espacementPoutre = determinerEspacementPoutre(InfoPatio, 9.25f);
        ajusterEspacePoutre(InfoPatio, espacementPoutre);
        InfoPatio.setLargeurSolive(9.25f);
        ajusterHauteurPatio(InfoPatio, InfoPatio.getHauteurPatio());
        InfoPatio.setEpaisseurPoteau(3.5f);
        InfoPatio.setLargeurPoteau(3.5f);
        creationPatioValide(InfoPatio, listePrix, 9.25f, 1, 1);
        float prixSiSolive2x10 = InfoPatio.getPrixTotal();
        if (InfoPatio.getLargeurSolive() != 9.25f){
            prixSiSolive2x10 = Float.POSITIVE_INFINITY;
        }
        listePrixParSolive[3] = prixSiSolive2x10;
        InfoPatio.setLargeurSolive(11.25f);
        ajusterHauteurPatio(InfoPatio, InfoPatio.getHauteurPatio());
        espacementPoutre = determinerEspacementPoutre(InfoPatio, 11.25f);
        ajusterEspacePoutre(InfoPatio, espacementPoutre);
        InfoPatio.setEpaisseurPoteau(3.5f);
        InfoPatio.setLargeurPoteau(3.5f);
        creationPatioValide(InfoPatio, listePrix, 11.25f, 1, 1);
        float prixSiSolive2x12 = InfoPatio.getPrixTotal();
        if (InfoPatio.getLargeurSolive() != 11.25f){
            prixSiSolive2x12 = Float.POSITIVE_INFINITY;
        }
        listePrixParSolive[4] = prixSiSolive2x12;

        float prixMin = Float.POSITIVE_INFINITY;
        for (float v : listePrixParSolive) {
            if (v < prixMin) {
                prixMin = v;
            }
        }
        if (prixMin == listePrixParSolive[0]){
            return 3.5f;
        }
        else if (prixMin == listePrixParSolive[1]){
            return 5.5f;
        }
        else if (prixMin == listePrixParSolive[2]){
            return 7.25f;
        }
        else if (prixMin == listePrixParSolive[3]){
            return 9.25f;
        }
        else{
            return 11.25f;
        }
    }

    private int determinerNombreDePoteauParPoutre(InformationPatio InfoPatio, float listPrix[]){
        float prixTotal8PiedEspace = InfoPatio.getPrixTotal();
        int nbPoteauInitial = InfoPatio.getNbPoteauParPoutre();
        double nbPoteauNonArrondie = InfoPatio.getLongueurPoutre() / 48;
        int nbPoteauParPoutre = (int) Math.ceil(nbPoteauNonArrondie) + 1;
        float espacemementpoteau = InfoPatio.getLongueurPoutre() / (nbPoteauParPoutre - 1);
        InfoPatio.setEspacementPoteau(espacemementpoteau);
        InfoPatio.setNbPoteauParPoutre(nbPoteauParPoutre);
        creationPatioValide(InfoPatio, listPrix, InfoPatio.getLargeurSolive(), 1, 1);
        float prixTotal4PiedEspace = InfoPatio.getPrixTotal();
        if (!(prixTotal4PiedEspace < prixTotal8PiedEspace)) {
            nbPoteauParPoutre = nbPoteauInitial;
        }
        return nbPoteauParPoutre;
    }
}
