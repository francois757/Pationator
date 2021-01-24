package lePationator.domain;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Douch Ayoub
 */
public class Exporter3D {
    private Patio patio;

    Exporter3D() {
    }
    
    
    public void exporterFormatSTL(String cheminFichier,Patio patio) throws IOException{
        FileWriter file;
        file = new FileWriter(cheminFichier);
        float[][] facetNormal= {{0.0f,0.0f,-1.0f},{0.0f,0.0f,-1.0f},{-1.0f,0.0f,0.0f},{-1.0f,0.0f,0.0f},{0.0f,1.0f,0.0f},{0.0f,1.0f,0.0f},
        {1.0f,0.0f,0.0f},{1.0f,0.0f,0.0f},{0.0f,-1.0f,0.0f},{0.0f,-1.0f,0.0f},{0.0f,0.0f,1.0f},{0.0f,0.0f,1.0f}};
        int[][] indexTrianglePlanche = {{1,2,0},{1,3,2},{1,7,3},{1,5,7},{3,6,2},{3,7,6},{0,2,6},{0,4,6},
            {1,0,4},{1,4,5},{5,4,6},{5,6,7}};
        int[][] indexTriangleSolive = {{4,6,7},{4,6,5},{4,1,0},{4,1,5},{5,6,2},{5,2,1},{7,2,3},{7,2,6},
            {4,0,3},{4,3,7},{0,2,3},{0,2,1}};
        ArrayList<PieceBois> listePlanche = patio.getSectionPlanche().getListePlanche();
        PieceBois plancheDeCotee = patio.getSectionPlanche().getPlancheDecote();
        ArrayList<PieceBois> listeSolive = patio.getSectionSolive().getListeSolive();
        ArrayList<PieceBois> listePoutre = patio.getSectionPoutre().getListeDePliePoutre();
        ArrayList<PieceBois> listePoteau = patio.getSectionPoteau().getListePoteau();
        int f = 0,k= 0, h=0;
        for (PieceBois pieceBois : listePlanche) {
            file.write("solid Planche"+ ++f +"\n");
            for (int i = 0; i < 12; i++) {
                file.write("facet normal ");
                file.write(Float.toString(facetNormal[i][0])+"  ");
                file.write(Float.toString(facetNormal[i][1])+"  ");
                file.write(Float.toString(facetNormal[i][2])+"  \n outer loop\n");
                file.write("vertex  ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTrianglePlanche[i][0]][0]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTrianglePlanche[i][0]][1]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTrianglePlanche[i][0]][2]) + "\n");
                file.write("vertex  ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTrianglePlanche[i][1]][0]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTrianglePlanche[i][1]][1]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTrianglePlanche[i][1]][2]) + "\n");
                file.write("vertex  ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTrianglePlanche[i][2]][0]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTrianglePlanche[i][2]][1]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTrianglePlanche[i][2]][2]) + "\n");
                file.write("endloop\n");
                file.write("endfacet\n");
            }
            file.write("endsolid Planche"+f +"\n");
        }
        file.write("solid PlancheCotee\n");
        for (int i = 0; i < 12; i++) {
            file.write("facet normal ");
            file.write(Float.toString(facetNormal[i][0])+"  ");
            file.write(Float.toString(facetNormal[i][1])+"  ");
            file.write(Float.toString(facetNormal[i][2])+"  \n outer loop\n");
            file.write("vertex  ");
            file.write(Float.toString(plancheDeCotee.getPosXYZ()[indexTrianglePlanche[i][0]][0]) + "   ");
            file.write(Float.toString(plancheDeCotee.getPosXYZ()[indexTrianglePlanche[i][0]][1]) + "   ");
            file.write(Float.toString(plancheDeCotee.getPosXYZ()[indexTrianglePlanche[i][0]][2]) + "\n");
            file.write("vertex  ");
            file.write(Float.toString(plancheDeCotee.getPosXYZ()[indexTrianglePlanche[i][1]][0]) + "   ");
            file.write(Float.toString(plancheDeCotee.getPosXYZ()[indexTrianglePlanche[i][1]][1]) + "   ");
            file.write(Float.toString(plancheDeCotee.getPosXYZ()[indexTrianglePlanche[i][1]][2]) + "\n");
            file.write("vertex  ");
            file.write(Float.toString(plancheDeCotee.getPosXYZ()[indexTrianglePlanche[i][2]][0]) + "   ");
            file.write(Float.toString(plancheDeCotee.getPosXYZ()[indexTrianglePlanche[i][2]][1]) + "   ");
            file.write(Float.toString(plancheDeCotee.getPosXYZ()[indexTrianglePlanche[i][2]][2]) + "\n");
            file.write("endloop\n");
            file.write("endfacet\n");
        }
        file.write("endsolid PlancheCotee\n");
        
        for (PieceBois pieceBois : listeSolive) {
            file.write("solid Solive"+ ++k +"\n");
            for (int i = 0; i < 12; i++) {
                file.write("facet normal ");
                file.write(Float.toString(facetNormal[i][0])+"  ");
                file.write(Float.toString(facetNormal[i][1])+"  ");
                file.write(Float.toString(facetNormal[i][2])+"  \n outer loop\n");
                file.write("vertex  ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][0]][1]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][0]][0]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][0]][2]) + "\n");
                file.write("vertex  ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][1]][1]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][1]][0]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][1]][2]) + "\n");
                file.write("vertex  ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][2]][1]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][2]][0]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][2]][2]) + "\n");
                file.write("endloop\n");
                file.write("endfacet\n");
            }
            file.write("endsolid Solive"+ k +"\n");
        }
        for (PieceBois pieceBois : listePoutre) {
            file.write("solid Poutre"+ ++h +"\n");
            for (int i = 0; i < 12; i++) {
                file.write("facet normal ");
                file.write(Float.toString(facetNormal[i][0])+"  ");
                file.write(Float.toString(facetNormal[i][1])+"  ");
                file.write(Float.toString(facetNormal[i][2])+"  \n outer loop\n");
                file.write("vertex  ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][0]][0]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][0]][1]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][0]][2]) + "\n");
                file.write("vertex  ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][1]][0]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][1]][1]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][1]][2]) + "\n");
                file.write("vertex  ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][2]][0]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][2]][1]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][2]][2]) + "\n");
                file.write("endloop\n");
                file.write("endfacet\n");
            }
            file.write("endsolid Poutre"+ h +"\n");
        }
        for (PieceBois pieceBois : listePoteau) {
            file.write("solid Poteau"+ ++k +"\n");
            for (int i = 0; i < 12; i++) {
                file.write("facet normal ");
                file.write(Float.toString(facetNormal[i][0])+"  ");
                file.write(Float.toString(facetNormal[i][1])+"  ");
                file.write(Float.toString(facetNormal[i][2])+"  \n outer loop\n");
                file.write("vertex  ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][0]][0]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][0]][1]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][0]][2]) + "\n");
                file.write("vertex  ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][1]][0]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][1]][1]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][1]][2]) + "\n");
                file.write("vertex  ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][2]][0]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][2]][1]) + "   ");
                file.write(Float.toString(pieceBois.getPosXYZ()[indexTriangleSolive[i][2]][2]) + "\n");
                file.write("endloop\n");
                file.write("endfacet\n");
            }
            file.write("endsolid Poteau"+ k +"\n");
        }
        
        if(file != null)file.close();
    }
}
