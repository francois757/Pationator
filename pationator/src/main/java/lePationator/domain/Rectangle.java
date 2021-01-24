package lePationator.domain;

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author Douch Ayoub
 */
public class Rectangle implements java.io.Serializable{
    private float largeurEnPixel;
    private float longueurEnPixel;
    private float posXEnPixel;
    private float posYEnPixel;
    private float largeurUMesure;
    private float longueurUMesure;
    private float posXUMesure;
    private float posYUMesure;
    private Point pointZoom;
    
    private Vue vueDuRectangle;
    // private int posX3D...
    
    
    public boolean inRec(Point pointCurseur){
        return  pointCurseur.x>posXEnPixel &&
                pointCurseur.x<(posXEnPixel+longueurEnPixel) && pointCurseur.y>posYEnPixel &&
                pointCurseur.y<(posYEnPixel+largeurEnPixel);
    }

    public Rectangle(PieceBois piece, Vue vue) {
        switch (vue) {
            case PLAN:
                rectangleVuePlan(piece);
                break;
            case FACE:
                rectangleVueFace(piece);
                break;
            case COTE:
                rectangleVueCote(piece);
                break;
        }
        vueDuRectangle = vue;
        posXUMesure=posXEnPixel;
        posYUMesure=posYEnPixel;
        largeurUMesure=largeurEnPixel;
        longueurUMesure=longueurEnPixel;
    }
    private void rectangleVuePlan(PieceBois piece){
        int x=0, y=1, z=2;
        switch (piece.getNomPiece()) {
            case PLANCHE:
                posYEnPixel = -(piece.getPosXYZ()[7][y]);
                posXEnPixel = -(piece.getPosXYZ()[7][x]); 
                longueurEnPixel = piece.getLargeur();
                largeurEnPixel = piece.getLongueur();
                break;
            case SOLIVE_PAFAUX:
            case SOLIVE:
                posXEnPixel = -(piece.getPosXYZ()[2][y]);
                posYEnPixel = -(piece.getPosXYZ()[2][x]); 
                longueurEnPixel = piece.getLongueur();
                largeurEnPixel = piece.getEpaisseur();
                break;
            case POUTRE_DOUBLE:
            case POUTRE_PAFAUT:
            case POUTRE:
                posXEnPixel = -(piece.getPosXYZ()[1][x]); 
                posYEnPixel = -(piece.getPosXYZ()[1][y]);
                longueurEnPixel = piece.getEpaisseur();//piece.getPosXYZ()[1][x] - piece.getPosXYZ()[2][x];
                largeurEnPixel = piece.getLongueur();
                break;
            case POTEAU:
                posXEnPixel = -(piece.getPosXYZ()[2][x]);
                posYEnPixel = -(piece.getPosXYZ()[2][y]);
                longueurEnPixel = piece.getEpaisseur();
                largeurEnPixel = piece.getLargeur();
                break;
            case PLANCHECOTEE:
                posXEnPixel = -(piece.getPosXYZ()[7][x]); 
                posYEnPixel = -(piece.getPosXYZ()[7][y]);
                longueurEnPixel = piece.getEpaisseur();
                largeurEnPixel = piece.getLongueur();
                break;
        }
    }
    private void rectangleVueFace(PieceBois piece){
        int x=0, y=1, z=2;
        switch (piece.getNomPiece()) {
            case PLANCHE:
                posXEnPixel = -(piece.getPosXYZ()[7][y]);
                posYEnPixel = -(piece.getPosXYZ()[7][z]);
                longueurEnPixel = piece.getLongueur();
                largeurEnPixel = piece.getEpaisseur();
                break;
            case SOLIVE_PAFAUX:
            case SOLIVE:
                posXEnPixel = -(piece.getPosXYZ()[2][x]);
                posYEnPixel = -(piece.getPosXYZ()[2][z]);
                longueurEnPixel = piece.getEpaisseur();
                largeurEnPixel = piece.getLargeur();
                break;
            case POUTRE_DOUBLE:
            case POUTRE_PAFAUT:
            case POUTRE:
                posXEnPixel = -(piece.getPosXYZ()[1][y]);
                posYEnPixel = -(piece.getPosXYZ()[1][z]);
                longueurEnPixel = piece.getLongueur();
                largeurEnPixel = piece.getLargeur();
                break;
            case POTEAU:
                posXEnPixel = -(piece.getPosXYZ()[2][y]);
                posYEnPixel = -(piece.getPosXYZ()[2][z]);
                longueurEnPixel = piece.getLargeur();
                largeurEnPixel = piece.getLongueur();
                break;
            case PLANCHECOTEE:
                posXEnPixel = -(piece.getPosXYZ()[7][y]);
                posYEnPixel = -(piece.getPosXYZ()[7][z]);
                longueurEnPixel = piece.getLongueur();
                largeurEnPixel = piece.getLargeur();
                break;
        }
    }
    private void rectangleVueCote(PieceBois piece){
        int x=0, y=1, z=2;
        switch (piece.getNomPiece()) {
            case PLANCHE:
                posXEnPixel = -(piece.getPosXYZ()[5][x]);
                posYEnPixel = -(piece.getPosXYZ()[5][z]);
                longueurEnPixel = piece.getLargeur();
                largeurEnPixel = piece.getEpaisseur();
                break;
            case SOLIVE_PAFAUX:
            case SOLIVE:
                posXEnPixel = -(piece.getPosXYZ()[1][y]);
                posYEnPixel = -(piece.getPosXYZ()[1][z]);
                longueurEnPixel = piece.getLongueur();
                largeurEnPixel = piece.getLargeur();
                break;
            case POUTRE_DOUBLE:
            case POUTRE_PAFAUT:
            case POUTRE:
                posXEnPixel = -(piece.getPosXYZ()[0][x]);
                posYEnPixel = -(piece.getPosXYZ()[0][z]);
                longueurEnPixel = piece.getEpaisseur();//piece.getPosXYZ()[0][x] - piece.getPosXYZ()[3][x];
                largeurEnPixel = piece.getLargeur();
                break;
            case POTEAU:
                posXEnPixel = -(piece.getPosXYZ()[1][x]);
                posYEnPixel = -(piece.getPosXYZ()[1][z]);
                longueurEnPixel = piece.getLargeur();
                largeurEnPixel = piece.getLongueur();
                break;
            case PLANCHECOTEE:
                posXEnPixel = -(piece.getPosXYZ()[5][x]);
                posYEnPixel = -(piece.getPosXYZ()[5][z]);
                longueurEnPixel = piece.getEpaisseur();
                largeurEnPixel = piece.getLargeur();
                break;
        }
    }

    public int getLargeur() {
        return Math.round(largeurEnPixel);
    }

    public int getLongueur() {
        return Math.round(longueurEnPixel);
    }

    public int getPosX() {
        return Math.round(posXEnPixel);
    }

    public int getPosY() {
        return Math.round(posYEnPixel);
    }
    public void majRectangleEnPixel(double valZoom,Dimension dim){
        posXEnPixel = (float) (posXUMesure * valZoom)+ (float)dim.getWidth();
        posYEnPixel =(float) (posYUMesure * valZoom)+ (float)dim.getHeight();
        largeurEnPixel =(float) (largeurUMesure * valZoom);
        longueurEnPixel =(float) (longueurUMesure * valZoom);
            
    }
    public void initialiserRectangle(){
        posXEnPixel =posXUMesure;
        posYEnPixel =posYUMesure;
        largeurEnPixel =largeurUMesure;
        longueurEnPixel =longueurUMesure;
    }
    
}
