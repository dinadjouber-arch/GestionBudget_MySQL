import BDD.SQLConnexion;
import display.FenetreAccueil;

import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {

        SQLConnexion c=new SQLConnexion();
        c.connexion();

        new FenetreAccueil();



    }
}
