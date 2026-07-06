package display;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CategoriePanel extends JPanel {

    private JTable table;

    public CategoriePanel() {

        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(20, 20, 20, 20));


        // Titre


        JLabel titre = new JLabel("Gestion des catégories");
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 28));

        add(titre, BorderLayout.NORTH);


        // Tableau


        String[] colonnes = {
                "ID",
                "Nom de la catégorie",
                "Description"
        };

        Object[][] donnees = {
                {1, "Alimentation", "Courses et restaurants"},
                {2, "Transport", "Bus, métro, essence"},
                {3, "Loisirs", "Cinéma, jeux, sorties"},
                {4, "Santé", "Médecin, pharmacie"},
                {5, "Logement", "Loyer et charges"}
        };

        table = new JTable(donnees, colonnes);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);


        // Boutons


        JPanel panelBoutons = new JPanel();

        JButton btnAjouter = new JButton("Ajouter");
        JButton btnModifier = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");

        panelBoutons.add(btnAjouter);
        panelBoutons.add(btnModifier);
        panelBoutons.add(btnSupprimer);

        add(panelBoutons, BorderLayout.SOUTH);
    }
}