/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lePationator.gui;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import lePationator.domain.Controleur;
import lePationator.domain.dessin.DessinerVue;

/**
 *
 * @author Hassen
 */
public class PatioPanel extends JPanel{
     
    public void PatioPanel(){
    }
    
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        dessinerVue = new DessinerVue(controleur,g,this.getSize());
        dessinerVue.construirePatio();
        
        
    }
    
    
    private MainWindow mainWindow;
    private Controleur controleur;
    private  DessinerVue dessinerVue;

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        controleur = mainWindow.getControleur();
    }
}
