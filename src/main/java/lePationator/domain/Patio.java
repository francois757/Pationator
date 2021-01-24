package lePationator.domain;

import java.awt.Color;
import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Douch Ayoub
 */
public class Patio implements java.io.Serializable,modifiable{

    private Vue vueActuelle = Vue.PLAN;
    private SectionSolive sectionSolive;
    private SectionPoutre sectionPoutre;
    private SectionRecouvrement sectionPlanche;
    private SectionPoteau sectionPoteau;
    private InformationPatio infoPatio;
    private Gabarit gabarit;
    
    public Patio() {
        sectionPlanche = new SectionRecouvrement();
        sectionSolive = new SectionSolive();
        sectionPoutre = new SectionPoutre();
        sectionPoteau = new SectionPoteau();
        infoPatio = new InformationPatio();
        gabarit = new Gabarit(infoPatio);
    }
    
      public Patio clonePatio(){
        
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Patio) ois.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
  }
    public void undoPatio(){
		}
    public void redoPatio(){
		}
    private String convertNombreToFraction(float n){
        String[] str = Float.toString(n).split("\\.");
        int pouce = Integer.parseInt(str[0]);
        String fraction = convertirFloatToFraction(Float.parseFloat("0."+str[1]));
        if(fraction.equals("0")){
            fraction ="";
        }else{
            fraction ="et "+fraction+"''";
        }
        return Integer.toString(pouce)+"'' "+fraction;
    }
    public String infoPlanches(){
        String infoPlanche;
        String infoPlancheCotee= "2x"+(int)Math.ceil(infoPatio.getLargeurSolive());
        int nb=infoPatio.getNbPlanche();
        if(infoPatio.getEpaisseurPlanche() == 1.25f){
            infoPlanche = "5/4x6";
        }else{
            infoPlanche = "2x6";
        }
        if(infoPlanche.equals(infoPlancheCotee)){
            return Integer.toString(nb+1)+" planches(incluant la planche de côté) de "+infoPlanche+
                " et de longueur "+convertNombreToFraction(infoPatio.getLongueurPlanche());
        }else{
            return Integer.toString(nb)+" planches de "+infoPlanche+
                " et de longueur "+convertNombreToFraction(infoPatio.getLongueurPlanche())+"\n - "+
                    "Une planche de côté de "+infoPlancheCotee+
                " et de longueur "+convertNombreToFraction(infoPatio.getLongueurPlanche());
        }
    }
    public String infoPoutres(){
        int nbTotalPoutreAvecPlies = (infoPatio.getNbPliesPoutreSimple_1()*2)+(infoPatio.getNbPoutre()-2)*infoPatio.getNbPliesPoutreDouble();
        float longueur = infoPatio.getLongueurPoutre();
        /*if(infoPatio.getNbPoteauParPoutre()>2){
            nbTotalPoutreAvecPlies = nbTotalPoutreAvecPlies * (infoPatio.getNbPoteauParPoutre()-1);
            longueur = longueur/(infoPatio.getNbPoteauParPoutre()-1);
        }*/
        return nbTotalPoutreAvecPlies+" poutres(incluant les plies) de "+
        "2x"+(int) Math.ceil(infoPatio.getLargeurPoutre())+
                " et de longueur "+convertNombreToFraction(longueur);
    }
    public String infoPoteaux(){
        int nbTotalPoteaux = (infoPatio.getNbPoutre()-1)*infoPatio.getNbPoteauParPoutre();
        String infoPoteau = Integer.toString((int)Math.ceil(infoPatio.getLargeurPoteau()));
        infoPoteau +="x"+infoPoteau+ " et de longueur "+convertNombreToFraction(infoPatio.getLongueurPoteau());
        return nbTotalPoteaux+" poteaux de "+infoPoteau;
    }
    public String infoSolives(){
        int nbSection = infoPatio.getNbPoutre()-1;
        int nbSectionDouble = nbSection-2;
        int nbSoliveSection = infoPatio.getNbSolive()/nbSection;
        String longMaison = convertNombreToFraction(infoPatio.getLongueurSoliveMaison());
        String longDouble= convertNombreToFraction(infoPatio.getLongueurSoliveCentre());
        String longPAFaux = convertNombreToFraction(infoPatio.getSolivePorteAFaux());
        int nbSoliveDouble= (nbSoliveSection * nbSectionDouble);
        
        String dimSolive = " solives de "+"2x"+(int)Math.ceil(infoPatio.getLargeurSolive())+
                " et de longueur ";
        if(nbSectionDouble>0){
            //toutes les solives sont égeaux
            if(longMaison.equals(longDouble)&&longPAFaux.equals(longDouble)){
                return infoPatio.getNbSolive() + dimSolive + longMaison;
            //toutes les sections de solive ne sont pas égeaux
            }else if(!longMaison.equals(longDouble)&& !longPAFaux.equals(longDouble)&& !longMaison.equals(longPAFaux)){
                return nbSoliveSection+dimSolive+longMaison+"\n - "+nbSoliveDouble+dimSolive+longDouble+"\n - "+
                        nbSoliveSection+dimSolive+longPAFaux;
            //Solives maison et porte à faux égeaux
            }else if (longMaison.equals(longPAFaux)){
                return Integer.toString(nbSoliveSection*2)+dimSolive+longMaison+"\n - "+nbSoliveDouble+dimSolive+longDouble;
            //Solive maison et double sont égeaux
            }else if(longMaison.equals(longDouble)){
                return Integer.toString(nbSoliveSection+nbSoliveDouble)+dimSolive+longMaison+"\n - "+nbSoliveSection+dimSolive+longPAFaux;
            //Solive double et porte à faux égeaux
            }else if(longPAFaux.equals(longDouble)){
                return Integer.toString(nbSoliveSection+nbSoliveDouble)+dimSolive+longDouble+"\n - "+nbSoliveSection+dimSolive+longMaison;
            }
        }else{
            if (longMaison.equals(longPAFaux)){
                return infoPatio.getNbSolive() + dimSolive + longMaison;
            }else{
                return nbSoliveSection+dimSolive+longMaison+"\n - "+nbSoliveSection+dimSolive+longPAFaux;
            }
        }
        return "";
    }
    public String chercherPieceBoisVisible(Point pointMesure){
        ArrayList<PieceBois> listeToutesPieceBoisVisible=this.toutesPieceBoisVisible();
         int indiceDePieceBoisRecherche=-1;
         String informationDeLaPieceDeBois;
         
         for(int i=0;i<listeToutesPieceBoisVisible.size();i++){
              if(vueActuelle==Vue.PLAN){
                  if(listeToutesPieceBoisVisible.get(i).getRectangleVuePlan().inRec(pointMesure)){
                      indiceDePieceBoisRecherche=i;
                  }
              }
              if(vueActuelle==Vue.COTE){
                   if(listeToutesPieceBoisVisible.get(i).getRectangleVueCote().inRec(pointMesure)){
                      indiceDePieceBoisRecherche=i;
                  }
              }
              if(vueActuelle==Vue.FACE){
                   if(listeToutesPieceBoisVisible.get(i).getRectangleVueFace().inRec(pointMesure)){
                      indiceDePieceBoisRecherche=i;
                  }
              }
         }
         
         if(indiceDePieceBoisRecherche==-1){
             return "";
         }
         
         int valeurEntiereLongueur=(int) Math.floor(listeToutesPieceBoisVisible.get(indiceDePieceBoisRecherche).getLongueur());
         int valeurEntiereEpaisseur=(int) Math.ceil(infoPatio.getEpaisseurPoutre());
         int valeurEntiereLargeur=(int) Math.ceil(listeToutesPieceBoisVisible.get(indiceDePieceBoisRecherche).getLargeur());
         float largeur= listeToutesPieceBoisVisible.get(indiceDePieceBoisRecherche).getLargeur();
         float chiffreApresVirgule=(float) (listeToutesPieceBoisVisible.get(indiceDePieceBoisRecherche).getLongueur()-valeurEntiereLongueur);
         
         String fractionString="";
         if(chiffreApresVirgule!=0){
             fractionString=this.convertirFloatToFraction(chiffreApresVirgule)+"''"; 
         }
         String valeurEntiereLongueurString=Float.toString(valeurEntiereLongueur).replace(".0", "")+"''";        
         String valeurEntiereEpaisseurString=Float.toString(valeurEntiereEpaisseur).replace(".0", "");
         String valeurEntiereLargeurString=Float.toString(valeurEntiereLargeur).replace(".0", "");
         
         String NomPieceBois="";
         if(listeToutesPieceBoisVisible.get(indiceDePieceBoisRecherche).getNomPiece()==NomPiece.PLANCHE){
             NomPieceBois="Planche";
             if(infoPatio.getEpaisseurPlanche() == 1.25f){
                 valeurEntiereEpaisseurString = "5/4";
             }
             if(largeur!=3.5f&&largeur!=5.5f&&largeur!=7.25f&&largeur!=9.25f&&largeur!=11.25f){
                 valeurEntiereLargeurString="("+convertNombreToFraction(infoPatio.getLargeurPremierePlanche())+")";
             }
         }
         if(listeToutesPieceBoisVisible.get(indiceDePieceBoisRecherche).getNomPiece()==NomPiece.PLANCHECOTEE){
             NomPieceBois="Planche de côté";
             if(infoPatio.getEpaisseurPlanche() == 1.25f){
                 valeurEntiereEpaisseurString = "5/4";
             }
         }
         if(listeToutesPieceBoisVisible.get(indiceDePieceBoisRecherche).getNomPiece()==NomPiece.POUTRE){
            NomPieceBois="Poutre collé à la maison"; 
         }
         if(listeToutesPieceBoisVisible.get(indiceDePieceBoisRecherche).getNomPiece()==NomPiece.POUTRE_DOUBLE){
            NomPieceBois="Poutre supportant deux portées";
         }
         if(listeToutesPieceBoisVisible.get(indiceDePieceBoisRecherche).getNomPiece()==NomPiece.POUTRE_PAFAUT){
             NomPieceBois="Poutre porte-à-faux";
         }
         if(listeToutesPieceBoisVisible.get(indiceDePieceBoisRecherche).getNomPiece()==NomPiece.SOLIVE){
             NomPieceBois="Solive";
         }
         if(listeToutesPieceBoisVisible.get(indiceDePieceBoisRecherche).getNomPiece()==NomPiece.SOLIVE_PAFAUX){
             NomPieceBois="Solive porte-à-faux";
         }
         if(listeToutesPieceBoisVisible.get(indiceDePieceBoisRecherche).getNomPiece()==NomPiece.POTEAU){
             NomPieceBois="Poteau";
             valeurEntiereEpaisseurString = Integer.toString((int) Math.ceil(infoPatio.getEpaisseurPoteau()));
         }
         
         informationDeLaPieceDeBois=
         "Pièce de bois: "+
         NomPieceBois+
         "\n"+
         "Longueur:"+
         valeurEntiereLongueurString+" "+fractionString+
         "\n"+
         "Dimension:"+
         valeurEntiereEpaisseurString+" x "+valeurEntiereLargeurString+
         "\n"+
         "Type: S-P-F"+
         "\n";
         
         if(listeToutesPieceBoisVisible.get(indiceDePieceBoisRecherche).getNomPiece()==NomPiece.POUTRE){
             String resultat=informationDeLaPieceDeBois+"Nombre de pli: "+Float.toString(infoPatio.getNbPliesPoutreSimple_1()).replace(".0", "");
             return resultat;
         }
         if(listeToutesPieceBoisVisible.get(indiceDePieceBoisRecherche).getNomPiece()==NomPiece.POUTRE_DOUBLE){
             String resultat=informationDeLaPieceDeBois+"Nombre de pli: "+Float.toString(infoPatio.getNbPliesPoutreDouble()).replace(".0", "");
             return resultat;
         }
         if(listeToutesPieceBoisVisible.get(indiceDePieceBoisRecherche).getNomPiece()==NomPiece.POUTRE_PAFAUT){
             String resultat=informationDeLaPieceDeBois+"Nombre de pli: "+Float.toString(infoPatio.getNbPliesPoutreSimple_2()).replace(".0", "");
             return resultat;
         }
         
         
         return informationDeLaPieceDeBois;
        
    }
    
    
    public ArrayList<PieceBois> toutesPieceBoisVisible(){
         ArrayList<PieceBois> listeToutesPieceBoisVisible=new ArrayList<PieceBois>();
       
       if(sectionPoteau.isVisible()){
           listeToutesPieceBoisVisible.addAll(sectionPoteau.getListePoteau());
       }
       if(sectionPoutre.isVisible()){
           listeToutesPieceBoisVisible.addAll(sectionPoutre.getListePoutre());
       }
       if(sectionSolive.isVisible()){
           listeToutesPieceBoisVisible.addAll(sectionSolive.getListeSolive());
       }
       if(sectionPlanche.isVisible()){
           listeToutesPieceBoisVisible.add(sectionPlanche.getPlancheDecote());
           listeToutesPieceBoisVisible.addAll(sectionPlanche.getListePlanche());
       }
       
       return listeToutesPieceBoisVisible;
    }
    
    public void changerCouleur(Section section, Color couleur){
        
        switch (section) {
            case RECOUVREMENT:
                sectionPlanche.setColor(couleur);
                break;
            case SOLIVE:
                sectionSolive.setColor(couleur);
                break;
            case POUTRE:
                sectionPoutre.setColor(couleur);
                break;
            case POTEAU:
                sectionPoteau.setColor(couleur);
                break;
        }
    }
    
    public void changerVisibilite(boolean visible, Section section){
        switch (section) {
            case RECOUVREMENT:
                sectionPlanche.setVisible(visible);
                break;
            case SOLIVE:
                sectionSolive.setVisible(visible);
                break;
            case POUTRE:
                sectionPoutre.setVisible(visible);
                break;
            case POTEAU:
                sectionPoteau.setVisible(visible);
                break;
        }
    }
    public void changerTransparence(boolean transparent, Section section){
        switch (section) {
            case RECOUVREMENT:
                sectionPlanche.setTransparent(transparent);
                break;
            case SOLIVE:
                sectionSolive.setTransparent(transparent);
                break;
            case POUTRE:
                sectionPoutre.setTransparent(transparent);
                break;
            case POTEAU:
                sectionPoteau.setTransparent(transparent);
                break;
        }
    }
    
    public Rectangle[] getListeRectangle(){
        return null;
    }
    
    public void creePatio(){
        sectionPoteau.ajouterPoteaux(infoPatio);
        sectionPoutre.ajouterPoutres(infoPatio);
        sectionSolive.ajouterSolives(infoPatio);
        sectionPlanche.ajouterPlanches(infoPatio);
        sectionPlanche.ajouterPlanchesCotee(infoPatio);
    }

    public Vue getVueActuelle() {
        return vueActuelle;
    }

    public void setVueActuelle(Vue vueActuelle) {
        this.vueActuelle = vueActuelle;
    }

    public SectionSolive getSectionSolive() {
        return sectionSolive;
    }

    public void setSectionSolive(SectionSolive sectionSolive) {
        this.sectionSolive = sectionSolive;
    }

    public SectionPoutre getSectionPoutre() {
        return sectionPoutre;
    }

    public void setSectionPoutre(SectionPoutre sectionPoutre) {
        this.sectionPoutre = sectionPoutre;
    }

    public SectionRecouvrement getSectionPlanche() {
        return sectionPlanche;
    }

    public void setSectionPlanche(SectionRecouvrement sectionPlanche) {
        this.sectionPlanche = sectionPlanche;
    }

    public SectionPoteau getSectionPoteau() {
        return sectionPoteau;
    }

    public void setSectionPoteau(SectionPoteau sectionPoteau) {
        this.sectionPoteau = sectionPoteau;
    }

    public InformationPatio getInfoPatio() {
        return infoPatio;
    }

    public void setInfoPatio(InformationPatio infoPatio) {
        this.infoPatio = infoPatio;
    }

    public Gabarit getGabarit() {
        return gabarit;
    }

    public void setGabarit(Gabarit gabarit) {
        this.gabarit = gabarit;
    }
    public String convertirFloatToFraction(float nombreAconvertir){
        
        int factorDePrécision=1000;
        if(nombreAconvertir==0){
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        
        long partieEntiere = (long) nombreAconvertir;
        
        if (partieEntiere != 0) {
            fraction.append(partieEntiere);
        }
        
        nombreAconvertir =nombreAconvertir- partieEntiere;
        
        double erreurAbsolue = Math.abs(nombreAconvertir);
        int denominator = 1;
        for(int i=2;i<=factorDePrécision;i++) {
            double erreurRelative = Math.abs(nombreAconvertir - (double) Math.round(nombreAconvertir * i) / i);
            if (erreurRelative < erreurAbsolue) {
                erreurAbsolue = erreurRelative;
                denominator = i;
            }
        }
        if (denominator > 1)
            fraction.append(' ').append(Math.round(nombreAconvertir * denominator)).append('/') .append(denominator);
        return fraction.toString();
    }
       
   
}
