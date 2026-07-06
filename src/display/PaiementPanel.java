package display;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PaiementPanel extends JPanel {

    private JTable table;

    public PaiementPanel() {

        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(20, 20, 20, 20));


        // Titre


        JLabel titre = new JLabel("Gestion des moyens de paiement");
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 28));

        add(titre, BorderLayout.NORTH);


        // Tableau


        String[] colonnes = {
                "ID",
                "Nom",
                "Description"
        };

        Object[][] donnees = {
                {1, "Carte bancaire", "Paiement par carte"},
                {2, "Espèces", "Paiement en liquide"},
                {3, "Virement", "Paiement par virement bancaire"},
                {4, "Chèque", "Paiement par chèque"}
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