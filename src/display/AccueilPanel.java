package display;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

    public class AccueilPanel extends JPanel {

        private JLabel lblDepenses;
        private JLabel lblRevenus;
        private JLabel lblBudget;

        public AccueilPanel() {

            setLayout(new BorderLayout(20,20));
            setBorder(new EmptyBorder(20,20,20,20));


            // Titre


            JLabel titre = new JLabel("Tableau de bord");
            titre.setFont(new Font("Arial", Font.BOLD, 28));
            titre.setHorizontalAlignment(SwingConstants.CENTER);

            add(titre, BorderLayout.NORTH);


            // Centre


            JPanel centre = new JPanel(new GridLayout(1,3,20,20));

            lblDepenses = new JLabel("1250.50 €", SwingConstants.CENTER);
            lblDepenses.setFont(new Font("Arial", Font.BOLD, 24));

            JPanel panelDepenses = creerCarte("💸 Dépenses du mois", lblDepenses);

            lblRevenus = new JLabel("2300.00 €", SwingConstants.CENTER);
            lblRevenus.setFont(new Font("Arial", Font.BOLD, 24));

            JPanel panelRevenus = creerCarte("💰 Revenus du mois", lblRevenus);

            lblBudget = new JLabel("1049.50 €", SwingConstants.CENTER);
            lblBudget.setFont(new Font("Arial", Font.BOLD, 24));

            JPanel panelBudget = creerCarte("📈 Budget restant", lblBudget);

            centre.add(panelDepenses);
            centre.add(panelRevenus);
            centre.add(panelBudget);

            add(centre, BorderLayout.CENTER);


            // Bas


            JPanel bas = new JPanel();

            JButton btnDepense = new JButton("Ajouter une dépense");
            JButton btnRevenu = new JButton("Ajouter un revenu");

            btnDepense.setPreferredSize(new Dimension(180,40));
            btnRevenu.setPreferredSize(new Dimension(180,40));

            bas.add(btnDepense);
            bas.add(Box.createHorizontalStrut(20));
            bas.add(btnRevenu);

            add(bas, BorderLayout.SOUTH);
        }

        private JPanel creerCarte(String titre, JLabel valeur) {

            JPanel panel = new JPanel(new BorderLayout());

            panel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createLineBorder(Color.GRAY),
                    titre,
                    TitledBorder.CENTER,
                    TitledBorder.TOP));

            panel.add(valeur, BorderLayout.CENTER);

            return panel;
        }


        // Méthodes utiles


        public void setDepenses(double montant) {
            lblDepenses.setText(String.format("%.2f €", montant));
        }

        public void setRevenus(double montant) {
            lblRevenus.setText(String.format("%.2f €", montant));
        }

        public void setBudgetRestant(double montant) {
            lblBudget.setText(String.format("%.2f €", montant));
        }
    }

