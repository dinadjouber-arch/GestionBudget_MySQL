package display;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DepensePanel extends JPanel {

    private JTable table;

    public DepensePanel() {

        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(20, 20, 20, 20));


        // Titre


        JLabel titre = new JLabel("Gestion des dépenses");
        titre.setFont(new Font("Arial", Font.BOLD, 28));
        titre.setHorizontalAlignment(SwingConstants.CENTER);

        add(titre, BorderLayout.NORTH);


        // Tableau


        String[] colonnes = {
                "Date",
                "Montant",
                "Catégorie",
                "Paiement",
                "Description"
        };

        Object[][] donnees = {
                {"06/07/2026", "25.00 €", "Alimentation", "Carte", "Courses"},
                {"05/07/2026", "12.50 €", "Transport", "Espèces", "Bus"}
        };

        table = new JTable(donnees, colonnes);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);


        // Boutons


        JPanel bas = new JPanel();

        JButton btnAjouter = new JButton("Ajouter");
        JButton btnModifier = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");

        bas.add(btnAjouter);
        bas.add(btnModifier);
        bas.add(btnSupprimer);

        add(bas, BorderLayout.SOUTH);
    }
}
