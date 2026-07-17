package display;

import Requetes.BudgetRequetes;
import Requetes.DepenseRequetes;
import Requetes.RevenuRequetes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class AccueilPanel extends JPanel {

    private JLabel lblDepenses;
    private JLabel lblRevenus;
    private JLabel lblBudgetRestant;

    public AccueilPanel() {

        setLayout(new BorderLayout(20, 20));
        setBorder(new EmptyBorder(20, 20, 20, 20));


        // Titre


        JLabel titre = new JLabel("Tableau de bord");
        titre.setFont(new Font("Arial", Font.BOLD, 28));
        titre.setHorizontalAlignment(SwingConstants.CENTER);

        add(titre, BorderLayout.NORTH);

        // Cartes


        JPanel cartes = new JPanel(new GridLayout(1, 3, 20, 20));

        JPanel panelDepenses = creerCarte("Total des dépenses");
        JPanel panelRevenus = creerCarte("Total des revenus");
        JPanel panelBudget = creerCarte("Budget restant");

        lblDepenses = (JLabel) panelDepenses.getComponent(0);
        lblRevenus = (JLabel) panelRevenus.getComponent(0);
        lblBudgetRestant = (JLabel) panelBudget.getComponent(0);

        cartes.add(panelDepenses);
        cartes.add(panelRevenus);
        cartes.add(panelBudget);

        add(cartes, BorderLayout.CENTER);


        // Informations

        JTextArea infos = new JTextArea();

        infos.setEditable(false);
        infos.setFont(new Font("Segoe UI", Font.PLAIN, 17));

        infos.setBorder(BorderFactory.createCompoundBorder(
                new javax.swing.border.LineBorder(Color.pink, 1, true),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        infos.setText(
                        "  Bienvenue dans votre application de gestion des dépenses.\n\n" +
                        "  Utilisez le menu de gauche pour :\n\n" +
                        " •  Ajouter une dépense\n" +
                        " •  Gérer les catégories\n" +
                        " •  Gérer les moyens de paiement\n" +
                        " •  Gérer votre budget\n" +
                        " •  Ajouter des revenus\n\n"
        );

        add(infos, BorderLayout.SOUTH);

        chargerStatistiques();
    }

    private JPanel creerCarte(String titre) {

        JPanel panel = new JPanel(new BorderLayout());

        panel.setBorder(new TitledBorder(titre));

        JLabel valeur = new JLabel("0.00 €");
        valeur.setHorizontalAlignment(SwingConstants.CENTER);
        valeur.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(valeur, BorderLayout.CENTER);

        return panel;
    }

    public void chargerStatistiques() {

        DepenseRequetes depenseRequetes = new DepenseRequetes();
        RevenuRequetes revenuRequetes = new RevenuRequetes();
        BudgetRequetes budgetRequetes = new BudgetRequetes();

        double totalDepenses = depenseRequetes.getTotalDepenses();
        double totalRevenus = revenuRequetes.getTotalRevenus();
        double budget = budgetRequetes.getBudget();

        double budgetRestant = budget - totalDepenses;

        lblDepenses.setText(String.format("%.2f €", totalDepenses));
        lblRevenus.setText(String.format("%.2f €", totalRevenus));

        if(budgetRestant<0)
            lblBudgetRestant.setForeground(Color.RED);

        lblBudgetRestant.setText(String.format("%.2f €", budgetRestant));

    }


}