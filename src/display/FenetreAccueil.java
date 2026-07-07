package display;

import javax.swing.*;
import java.awt.*;

public class FenetreAccueil extends JFrame {

    private CardLayout cardLayout;
    private JPanel panelCentre;

    public FenetreAccueil() {

        setTitle("Gestion des dépenses personnelles");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ==========================
        // Barre du haut
        // ==========================

        JPanel topPanel = new JPanel();

        JLabel titre = new JLabel("Gestion des dépenses personnelles");
        titre.setFont(new Font("Arial", Font.BOLD, 24));

        topPanel.add(titre);

        // ==========================
        // Menu de gauche
        // ==========================

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1, 10, 10));
        menuPanel.setPreferredSize(new Dimension(220, 0));

        JButton btnAccueil = new JButton("🏠 Accueil");
        JButton btnDepenses = new JButton("💸 Dépenses");
        JButton btnCategories = new JButton("📂 Catégories");
        JButton btnPaiements = new JButton("💳 Paiements");
        JButton btnBudget = new JButton("📈 Budget");
        JButton btnRevenus = new JButton("💰 Revenus");

        menuPanel.add(btnAccueil);
        menuPanel.add(btnDepenses);
        menuPanel.add(btnCategories);
        menuPanel.add(btnPaiements);
        menuPanel.add(btnBudget);
        menuPanel.add(btnRevenus);

        // ==========================
        // Zone centrale
        // ==========================

        cardLayout = new CardLayout();
        panelCentre = new JPanel(cardLayout);

        panelCentre.add(new AccueilPanel(), "Accueil");
        panelCentre.add(new DepensePanel(), "Depenses");
        panelCentre.add(new CategoriePanel(), "Categories");
        panelCentre.add(new PaiementPanel(), "Paiements");
        panelCentre.add(new BudgetPanel(), "Budget");
        panelCentre.add(new RevenuPanel(), "Revenus");

        add(topPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.WEST);
        add(panelCentre, BorderLayout.CENTER);

        // ==========================
        // Navigation
        // ==========================

        btnAccueil.addActionListener(e -> afficherPanel("Accueil"));

        btnDepenses.addActionListener(e -> afficherPanel("Depenses"));

        btnCategories.addActionListener(e -> afficherPanel("Categories"));

        btnPaiements.addActionListener(e -> afficherPanel("Paiements"));

        btnBudget.addActionListener(e -> afficherPanel("Budget"));

        btnRevenus.addActionListener(e -> afficherPanel("Revenus"));

        // Affiche l'accueil au démarrage
        afficherPanel("Accueil");

        setVisible(true);
    }

    private void afficherPanel(String nomPanel) {
        cardLayout.show(panelCentre, nomPanel);
    }
}