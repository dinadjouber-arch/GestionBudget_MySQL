package display;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BudgetPanel extends JPanel {

    private JTable table;

    public BudgetPanel() {

        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(20, 20, 20, 20));


        // Titre


        JLabel titre = new JLabel("Gestion des budgets");
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 28));

        add(titre, BorderLayout.NORTH);


        // Tableau


        String[] colonnes = {
                "ID",
                "Catégorie",
                "Budget mensuel",
                "Dépensé",
                "Restant"
        };

        Object[][] donnees = {
                {1, "Alimentation", "500.00 €", "320.00 €", "180.00 €"},
                {2, "Transport", "150.00 €", "80.00 €", "70.00 €"},
                {3, "Loisirs", "200.00 €", "120.00 €", "80.00 €"},
                {4, "Santé", "100.00 €", "40.00 €", "60.00 €"}
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