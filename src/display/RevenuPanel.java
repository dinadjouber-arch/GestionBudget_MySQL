package display;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RevenuPanel extends JPanel {

    private JTable table;

    public RevenuPanel() {

        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(20, 20, 20, 20));


        // Titre


        JLabel titre = new JLabel("Gestion des revenus");
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 28));

        add(titre, BorderLayout.NORTH);


        // Tableau


        String[] colonnes = {
                "ID",
                "Date",
                "Montant",
                "Source",
                "Description"
        };

        Object[][] donnees = {
                {1, "01/07/2026", "1800.00 €", "Salaire", "Salaire mensuel"},
                {2, "05/07/2026", "150.00 €", "Prime", "Prime exceptionnelle"},
                {3, "10/07/2026", "80.00 €", "Remboursement", "Remboursement d'un ami"}
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