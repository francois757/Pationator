/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lePationator.domain.dessin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import lePationator.domain.Controleur;
import lePationator.domain.NomPiece;
import lePationator.domain.Patio;
import lePationator.domain.PieceBois;
import lePationator.domain.Rectangle;
import lePationator.domain.Vue;

/**
 *
 * @author Hassen
 */
public class DessinerVue {

    public DessinerVue(Controleur controleur, Graphics g,Dimension dim) {
        this.controleur = controleur;
        patio = controleur.getPatio();
        this.g = g;
        if(dim != null){
            dimension= new Dimension(dim.getSize());
        }
    }
  
    public void construirePatio( ){
        float longPatio = patio.getInfoPatio().GetLongueurPatio()*(float)controleur.getValZoom();
        float largPatio = patio.getInfoPatio().getLargeurPatio()*(float)controleur.getValZoom();
        float hautPatio = patio.getInfoPatio().getHauteurPatio()*(float)controleur.getValZoom();
        int diffLongZoom;
        int diffLargZoom;
        int diffHautZoom;
        w=controleur.getDimPatio().width;
        h=controleur.getDimPatio().height;
        if(controleur.isZoomActive()&&!controleur.isDragActive()){
            diffLongZoom = Math.round(longPatio-(longPatio/(float)controleur.getRatioZoom()));
            diffLargZoom = Math.round(largPatio-(largPatio/(float)controleur.getRatioZoom()));
            diffHautZoom = Math.round(hautPatio-(hautPatio/(float)controleur.getRatioZoom()));
            if(!controleur.isIsZoomer()){
                diffLongZoom = Math.round(longPatio-(longPatio*(float)controleur.getRatioZoom()));
            diffLargZoom = Math.round(largPatio-(largPatio*(float)controleur.getRatioZoom()));
            diffHautZoom = Math.round(hautPatio-(hautPatio*(float)controleur.getRatioZoom()));
            }
            if((patio.getVueActuelle() == Vue.PLAN || patio.getVueActuelle() == Vue.COTE)){
                w += diffLongZoom/2;
            }else if(patio.getVueActuelle() == Vue.FACE){
                w += diffLargZoom/2;
            }
            if(patio.getVueActuelle() == Vue.PLAN){
                h += diffLargZoom/2;
            }else if((patio.getVueActuelle() == Vue.FACE || patio.getVueActuelle() == Vue.COTE)){
                h += diffHautZoom/2;
            }
            dimension.setSize(w, h);
            controleur.setDimPatio(new Dimension(dimension));
            controleur.setDimPatioDepart(new Dimension(dimension));
            controleur.setZoomActive(false);
        }
        dimension.setSize(w, h);
        afficherPoteau();
        afficherPoutre();
        afficherSolive();
        afficherPlanche();
        
    }
    private boolean depassePanel(float lon,float larg,float haut){
        return ((dimension.getWidth()-lon*controleur.getValZoom()<0) && 
                (patio.getVueActuelle() == Vue.PLAN || patio.getVueActuelle() == Vue.COTE))||
                (dimension.getWidth()-larg*controleur.getValZoom()<0)||
                (dimension.getHeight()-larg*controleur.getValZoom()<0&& patio.getVueActuelle() == Vue.PLAN)||
                (dimension.getHeight()-haut*controleur.getValZoom()<0);
    }
    private void afficherPoutre(){
        Color couleur = patio.getSectionPoutre().getColor();
        Color couleurContour = Color.BLACK;
        if(patio.getSectionPoutre().isTransparent()){
            couleur = new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue(), 125);
            couleurContour = new Color(couleurContour.getRed(), couleurContour.getGreen(), couleurContour.getBlue(), 125);
        }
        Rectangle rec = null;
        for(PieceBois bois : patio.getSectionPoutre().getListePoutre()){
            switch (patio.getVueActuelle()) {
                case PLAN:
                    rec = bois.getRectangleVuePlan();
                    break;
                case FACE:
                    rec = bois.getRectangleVueFace();
                    break;
                case COTE:
                    rec = bois.getRectangleVueCote();
                    break;
            }
            if(rec != null){
                rec.majRectangleEnPixel(controleur.getValZoom(),dimension);
                if(patio.getSectionPoutre().isVisible()){
                    g.setColor(couleur);
                    g.fill3DRect(rec.getPosX(),rec.getPosY(),rec.getLongueur(),rec.getLargeur(),true);
                    g.setColor(couleurContour);
                    g.drawRect(rec.getPosX(),rec.getPosY(),rec.getLongueur(),rec.getLargeur());
                    if(null!=bois.getNomPiece()&& patio.getVueActuelle()!=Vue.FACE)switch (bois.getNomPiece()) {
                        case POUTRE:
                            int plie = patio.getInfoPatio().getNbPliesPoutreSimple_1();
                            for (int i = 1; i < plie; i++) {
                                g.drawLine(rec.getPosX()+Math.round(((rec.getLongueur()/plie)*i)),rec.getPosY(),
                                        rec.getPosX()+Math.round(((rec.getLongueur()/plie)*i)), rec.getPosY()+rec.getLargeur());
                            }
                            break;
                        case POUTRE_DOUBLE:
                            int plieD = patio.getInfoPatio().getNbPliesPoutreDouble();
                            for (int i = 1; i < plieD; i++) {
                                g.drawLine(rec.getPosX()+Math.round(((rec.getLongueur()/plieD)*i)),rec.getPosY(),
                                        rec.getPosX()+Math.round(((rec.getLongueur()/plieD)*i)), rec.getPosY()+rec.getLargeur());
                            }
                            break;
                        case POUTRE_PAFAUT:
                            int plieF = patio.getInfoPatio().getNbPliesPoutreSimple_2();
                            for (int i = 1; i <plieF ; i++) {
                                g.drawLine(rec.getPosX()+Math.round(((rec.getLongueur()/plieF)*i)),rec.getPosY(),
                                        rec.getPosX()+Math.round(((rec.getLongueur()/plieF)*i)), rec.getPosY()+rec.getLargeur());
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        
        
    }
    private void afficherSolive(){
        Color couleur = patio.getSectionSolive().getColor();
        Color couleurContour = Color.BLACK;
        if(patio.getSectionSolive().isTransparent()){
            couleur = new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue(), 125);
            couleurContour = new Color(couleurContour.getRed(), couleurContour.getGreen(), couleurContour.getBlue(), 125);
        }
        Rectangle rec = null;
        for(PieceBois bois : patio.getSectionSolive().getListeSolive()){
            switch (patio.getVueActuelle()) {
                case PLAN:
                    rec = bois.getRectangleVuePlan();
                    break;
                case FACE:
                    rec = bois.getRectangleVueFace();
                    break;
                case COTE:
                    rec = bois.getRectangleVueCote();
                    break;
            }
            if(rec != null){
                rec.majRectangleEnPixel(controleur.getValZoom(),dimension);
                if(patio.getSectionSolive().isVisible()){
                    g.setColor(couleur);
                    g.fill3DRect(rec.getPosX(),rec.getPosY(),rec.getLongueur(),rec.getLargeur(),true);
                    g.setColor(couleurContour);
                    g.drawRect(rec.getPosX(),rec.getPosY(),rec.getLongueur(),rec.getLargeur());
                }
            }
        }
        
    }
    private void afficherPlanche(){
        Color couleur = patio.getSectionPlanche().getColor();
        Color couleurContour = Color.BLACK;
        if(patio.getSectionPlanche().isTransparent()){
            couleur = new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue(), 125);
            couleurContour = new Color(couleurContour.getRed(), couleurContour.getGreen(), couleurContour.getBlue(), 125);
        }
        Rectangle rec = null;
        //ajouter planche de cotee
        switch (patio.getVueActuelle()) {
                case PLAN:
                    rec = patio.getSectionPlanche().getPlancheDecote().getRectangleVuePlan();
                    break;
                case FACE:
                    rec = patio.getSectionPlanche().getPlancheDecote().getRectangleVueFace();
                    break;
                case COTE:
                    rec = patio.getSectionPlanche().getPlancheDecote().getRectangleVueCote();
                    break;
            }
        if(rec != null){
            rec.majRectangleEnPixel(controleur.getValZoom(),dimension);
            if(patio.getSectionPlanche().isVisible()){
                g.setColor(couleur);
                g.fill3DRect(rec.getPosX(),rec.getPosY(),rec.getLongueur(),rec.getLargeur(),true);
                g.setColor(couleurContour);
                g.drawRect(rec.getPosX(),rec.getPosY(),rec.getLongueur(),rec.getLargeur());
            }
        }
        rec = null;
        for(PieceBois bois : patio.getSectionPlanche().getListePlanche()){
            switch (patio.getVueActuelle()) {
                case PLAN:
                    rec = bois.getRectangleVuePlan();
                    break;
                case FACE:
                    rec = bois.getRectangleVueFace();
                    break;
                case COTE:
                    rec = bois.getRectangleVueCote();
                    break;
            }
            if(rec != null){
                rec.majRectangleEnPixel(controleur.getValZoom(),dimension);
                if(patio.getSectionPlanche().isVisible()){
                    g.setColor(couleur);
                    g.fill3DRect(rec.getPosX(),rec.getPosY(),rec.getLongueur(),rec.getLargeur(),true);
                    g.setColor(couleurContour);
                    g.drawRect(rec.getPosX(),rec.getPosY(),rec.getLongueur(),rec.getLargeur());
                }
            }
        }
    }
    
    private void afficherPoteau(){
        Color couleur = patio.getSectionPoteau().getColor();
        Color couleurContour = Color.BLACK;
        
        if(patio.getSectionPoteau().isTransparent()){
            couleur = new Color(couleur.getRed(), couleur.getGreen(), couleur.getBlue(), 125);
            couleurContour = new Color(couleurContour.getRed(), couleurContour.getGreen(), couleurContour.getBlue(), 125);
        }
        
        Rectangle rec = null;
        for(PieceBois bois : patio.getSectionPoteau().getListePoteau()){
            switch (patio.getVueActuelle()) {
                case PLAN:
                    rec = bois.getRectangleVuePlan();
                    break;
                case FACE:
                    rec = bois.getRectangleVueFace();
                    break;
                case COTE:
                    rec = bois.getRectangleVueCote();
                    break;
            }
            if(rec != null){
                rec.majRectangleEnPixel(controleur.getValZoom(),dimension);
                if(patio.getSectionPoteau().isVisible()){
                    g.setColor(couleur);
                    g.fill3DRect(rec.getPosX(),rec.getPosY(),rec.getLongueur(),rec.getLargeur(),true);
                    g.setColor(couleurContour);
                    g.drawRect(rec.getPosX(),rec.getPosY(),rec.getLongueur(),rec.getLargeur());
                }
            }
        }
    }
    private Dimension dimension;
    private Patio patio;
    private Controleur controleur;
    private Graphics g;
    private double w ;
    private double h ;
}
