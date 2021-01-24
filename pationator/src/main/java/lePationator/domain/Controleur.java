package lePationator.domain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Stack;
import javax.imageio.ImageIO;


/**
 *
 * @author Hassen
 */
public class Controleur {

    private Patio patio;
    private Exporter3D exporter3D;
    private final double ratioZoom = 1.05;
    private double valZoom = 2.6;
    private Point pointdeZoom;
    private boolean isZoomer;
    private gestionnaireUndoRedo manager;
    private int niveauStack=0;
    private boolean dragActive=false;
    private boolean zoomActive=false;
    private Ingenieur ingenieur;
    private BufferedImage bi;
    private Dimension dimPatio;
    private Dimension dimPatioDepart;
    public Controleur() {
        manager= new gestionnaireUndoRedo();
        ingenieur = new Ingenieur();
        patio = new Patio();
        exporter3D = new Exporter3D();
        pointdeZoom= new Point(1154-15, 551-15);
        dimPatio = new Dimension(1154-15, 551-15);
        dimPatioDepart = new Dimension(1154-15, 551-15);
    }
    public void finDeDrag(){
        dimPatioDepart= new Dimension(dimPatio);
    }
    public void deplacerPatio(Point depart, Point fin){
        dimPatio = new Dimension(dimPatioDepart.width+(fin.x-depart.x), dimPatioDepart.height+(fin.y-depart.y));
    }
     public void sauvegarderPatioListeUndoRedo(){
        manager.addChangeable(this.patio.clonePatio());
        if(niveauStack==0){
            manager.setNodeParent(manager.getNodeIndex());
            niveauStack=niveauStack+1;
        }
    }
    
    public void clearListeUndoRedo(){
        manager.clear();
        this.niveauStack=0;
    }
    
    public boolean undo(){
        if(manager.canUndo()){
            manager.undo();
            this.patio=manager.statusPatio().clonePatio();
        }
        return manager.canUndo();
    }
    
    public boolean redo(){
        if(manager.canRedo() ){
            manager.redo();
            this.patio=manager.statusPatio().clonePatio();
        }
        return manager.canRedo();
    }

    public Graphics preparerGraphics(int w, int h){
        bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        return g;
    }
    public void exporterPNG(String path)throws Exception{
        RenderedImage rendImage = bi;
        ImageIO.write(rendImage,"png",new File(path));
    }
    // 2x4 2x6 5/4x6 2x8 2x10 2x12 4x4 6x6
    public void majPrixPieceBois(Object[] listPrix){
        patio.getInfoPatio().setPrix2x4(Float.valueOf(listPrix[0].toString()));
        patio.getInfoPatio().setPrix2x6(Float.valueOf(listPrix[1].toString()));
        patio.getInfoPatio().setPrix5Quartx6(Float.valueOf(listPrix[2].toString()));
        patio.getInfoPatio().setPrix2x8(Float.valueOf(listPrix[3].toString()));
        patio.getInfoPatio().setPrix2x10(Float.valueOf(listPrix[4].toString()));
        patio.getInfoPatio().setPrix2x12(Float.valueOf(listPrix[5].toString()));
    }
        
    public String getListePieceBois(){
        return "* "+patio.infoPlanches()+"\n* "+patio.infoSolives()+"\n* "+patio.infoPoutres()+"\n* "+patio.infoPoteaux();
    }
    
    public String getPrixTotal(){
        patio.getInfoPatio().calculPrixTotal();
        String prix =Float.toString(patio.getInfoPatio().getPrixTotal())+" $";
        return prix.replace('.', ',');
    }
    public float[] chargerPrixBois(){
        float[] list = {patio.getInfoPatio().getPrix2x4(),patio.getInfoPatio().getPrix2x6(),
            patio.getInfoPatio().getPrix5Quartx6(),patio.getInfoPatio().getPrix2x8(),
            patio.getInfoPatio().getPrix2x10(),patio.getInfoPatio().getPrix2x12(),
            0,0};
        return list;
    }
    public void initialiserZoom(){
        valZoom=2.6;
        dimPatio = new Dimension(1154-15, 551-15);
        dimPatioDepart = new Dimension(1154-15, 551-15);
        dragActive=false;
        
    }
    public void exporterSTL(String chemin) throws IOException{
        exporter3D.exporterFormatSTL(chemin,patio);
    }
    public boolean validerPatio(){
        return ingenieur.validerPation(patio.getInfoPatio());
    }
    
    public void changerValeurPatio(ChampSaisie NomChamp, float valeurChamp){
        patio.getGabarit().updatePatio(NomChamp, valeurChamp);
    }
    public void changerValeurPatio(ChampSaisie NomChamp, int valeurChamp){
        patio.getGabarit().updatePatio(NomChamp, valeurChamp);
    }
    private String floatToChampSaisie(float n){
        String[] str = Float.toString(n).split("\\.");
        int pouce = Integer.parseInt(str[0]);
        int pied = Integer.parseInt(str[0])/12;
        return "0-"+Integer.toString(pouce)+"-"+patio.convertirFloatToFraction(Float.parseFloat("0."+str[1]));
    }
    public boolean[][] chargerVisibiliteTrans(){
        boolean[][] vT=
        {{patio.getSectionPlanche().isVisible(),patio.getSectionPlanche().isTransparent()},
        {patio.getSectionSolive().isVisible(),patio.getSectionSolive().isTransparent()},
        {patio.getSectionPoutre().isVisible(),patio.getSectionPoutre().isTransparent()},
        {patio.getSectionPoteau().isVisible(),patio.getSectionPoteau().isTransparent()}};
        return vT;
    }
    public Color[] chargerCouleurPatio(){
        Color[] c =
        {patio.getSectionPlanche().getColor(),
        patio.getSectionSolive().getColor(),
        patio.getSectionPoutre().getColor(),
        patio.getSectionPoteau().getColor()};
        return c;
    }
    public Vue getVue(){
        return patio.getVueActuelle();
    }
    public String[] chargerInfoPatio(){
        String[] infoPatio=
        {floatToChampSaisie(patio.getInfoPatio().getLargeurPatio()),
            floatToChampSaisie(patio.getInfoPatio().GetLongueurPatio()),
            floatToChampSaisie(patio.getInfoPatio().getHauteurPatio()),
            floatToChampSaisie(patio.getInfoPatio().getEspacementPlanche()),
            floatToChampSaisie(patio.getInfoPatio().getEspacementPoutre()),
            floatToChampSaisie(patio.getInfoPatio().getEspacementSolive()),
            Float.toString(patio.getInfoPatio().getEpaisseurPlanche()),
            Float.toString(patio.getInfoPatio().getLargeurPlanche()),
            Float.toString(patio.getInfoPatio().getLargeurSolive()),
            Float.toString(patio.getInfoPatio().getLargeurPoutre()),
            Integer.toString(patio.getInfoPatio().getNbPoutre()),
            Integer.toString(patio.getInfoPatio().getNbPoteauParPoutre()),
            floatToChampSaisie(patio.getInfoPatio().getPorteAFaux()),
            Integer.toString(patio.getInfoPatio().getNbPliesPoutreSimple_1()),
            Integer.toString(patio.getInfoPatio().getNbPliesPoutreDouble())
            }; 
        return infoPatio;
    }
    public void ouvrirProjet(String chemin)throws IOException,ClassNotFoundException{
        //Patio patio = null;
         FileInputStream fileInput = new FileInputStream(chemin);
         ObjectInputStream objetInput = new ObjectInputStream(fileInput);
         this.patio = (Patio) objetInput.readObject();
         objetInput.close();
         fileInput.close();
         //System.out.println(" On ne trouve pas le fichier en question");
         //System.out.println("One ne trouve pas l'objet Patio");
    }
    
    public void enregistrerProjet(String chemin)throws IOException{
         FileOutputStream fileOut =
         new FileOutputStream(chemin);
         ObjectOutputStream sortie = new ObjectOutputStream(fileOut);
         sortie.writeObject(this.patio);
         sortie.close();
         fileOut.close();
   }
    public void creerProjet(){
        patio = new Patio();
        patio.creePatio();
    }
    
    public void zoomer(Point point){
        valZoom *= ratioZoom;
        pointdeZoom = new Point(point);
        isZoomer =true;
        zoomActive=true;
    }
    
    public void dezoomer(Point point){
        valZoom /= ratioZoom;
        pointdeZoom = new Point(point);
        isZoomer = false;
        zoomActive=true;
    }
    
    public String infoPieceBois(Point pointCurseur){
        return patio.chercherPieceBoisVisible(pointCurseur);
    }
    
    public void majPiecesDeBois(){
        patio.creePatio();
    }
    
    public void boutonMagique(){
        patio.setInfoPatio(ingenieur.construirePationMoinsCher(patio.getInfoPatio()));
    }
    
    public void changerCouleur(Section section,Color couleur){
        patio.changerCouleur(section, couleur);
    }
    
    public void changerVisibilite(boolean visible,Section section){
        patio.changerVisibilite(visible, section);
    }
    public void changerTransparence(boolean transparent ,Section section){
        patio.changerTransparence(transparent, section);
    }
    public void changerVue(Vue vue){
        patio.setVueActuelle(vue);
    }

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }

    public Point getPointdeZoom() {
        return pointdeZoom;
    }

    public void setPointdeZoom(Point pointdeZoom) {
        this.pointdeZoom = pointdeZoom;
    }
    public Color getPlancheColor(){
        return patio.getSectionPlanche().getColor();
    }
    public Color getSoliveColor(){
        return patio.getSectionSolive().getColor();
    }
    public Color getPoutreColor(){
        return patio.getSectionPoutre().getColor();
    }
    public Color getPoteauColor(){
        return patio.getSectionPoteau().getColor();
    }
    public Gabarit getGabarit(){
        return patio.getGabarit();
    }
    public InformationPatio getInfoPatio(){
        return patio.getInfoPatio();
    }
    public boolean nbPlieValide(String n){
        return n.matches("1|2|3");
    }
    public boolean nbPoteauPoutreValide(String n){
        if(!n.matches("[0-9]+")){
            return false;
        }
        return Integer.valueOf(n)>1 ;
    }
    public float getFloatValeur(String champ){
        String[] items = champ.replaceAll(" ", "").split("-");
        if(items.length == 3){
            if(items[0].matches("[0-9]+") && items[1].matches("[0-9]+") && items[2].matches("0+|[0-9]+/0*[1-9]+")){
                return Float.valueOf(items[0])*12 + Float.valueOf(items[1]) + 
                        (items[2].split("/").length==1 ?Float.valueOf(items[2]):
                        Float.valueOf(items[2].split("/")[0]) / Float.valueOf(items[2].split("/")[1]));
            }else{
                return -1;
            }
        }else {
            return -1;
        }
    }
    public double getRatioZoom() {
        return ratioZoom;
    }

    public Exporter3D getExporter3D() {
        return exporter3D;
    }

    public void setExporter3D(Exporter3D exporter3D) {
        this.exporter3D = exporter3D;
    }
   
    public double getValZoom() {
        return valZoom;
    }

    public Dimension getDimPatio() {
        return dimPatio;
    }

    public void setDimPatio(Dimension dimPatio) {
        this.dimPatio = dimPatio;
    }

    public boolean isIsZoomer() {
        return isZoomer;
    }

    public Dimension getDimPatioDepart() {
        return dimPatioDepart;
    }

    public void setDimPatioDepart(Dimension dimPatioDepart) {
        this.dimPatioDepart = dimPatioDepart;
    }

    public boolean isDragActive() {
        return dragActive;
    }

    public void setDragActive(boolean dragActive) {
        this.dragActive = dragActive;
    }

    public boolean isZoomActive() {
        return zoomActive;
    }

    public void setZoomActive(boolean zoomActive) {
        this.zoomActive = zoomActive;
    }
           
}
