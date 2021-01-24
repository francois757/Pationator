package ca.ulaval.glo2004;


import lePationator.gui.MainWindow;
import lePationator.domain.Controleur;

public class App {
    //Exemple de creation d'une fenetre et d'un bouton avec swing. Lorsque vous allez creer votre propre GUI
    // Vous n'aurez pas besoin d'ecrire tout ce code, il sera genere automatiquement par intellij ou netbeans
    // Par contre vous aurez a creer les actions listener pour vos boutons et etc.
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
        System.out.print("Bonjour hello !! ");
        /*Pattern p = Pattern.compile("-");
        String[] str ;
        String[] items = " 154/45667 ".replaceAll(" ", "").split("-");
        for (String item : items) {
            System.out.print(item+"|");
            if(item.matches("0+|\\d+/[1-9]+")){
                System.out.print("item match:"+item);
                str = item.split("0+|\\d+/[1-9]+");
                //System.out.print("\nEn fait la division:"+ str[0]);
            }else{
                System.out.print("item ne match pas:"+item);
                
            }
        }*/
        /*float f= (float) 234.78;
        String str = Float.toString(f);
        float virgule = Float.parseFloat("0."+Float.toString(f).split("\\.")[1]);
        System.out.print("convertion float:"+virgule);*/
    }
}

