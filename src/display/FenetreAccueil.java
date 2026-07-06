package display;

import javax.swing.*;
import java.awt.*;

public class FenetreAccueil extends JFrame {

    public FenetreAccueil() {

        setTitle("Gestion des dépenses");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        // Barre du haut


        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(1000, 70));

        JLabel title = new JLabel("Gestion des dépenses personnelles");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        topPanel.add(title);


        // Menu de gauche


        JPanel menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(220, 650));
        menuPanel.setLayout(new GridLayout(7, 1, 10, 10));

        JButton accueil = new JButton("🏠 Accueil");
        JButton depenses = new JButton("💸 Dépenses");
        JButton categories = new JButton("📂 Catégories");
        JButton paiements = new JButton("💳 Paiements");
        JButton budgets = new JButton("📈 Budgets");
        JButton revenus = new JButton("💰 Revenus");

        menuPanel.add(accueil);
        menuPanel.add(depenses);
        menuPanel.add(categories);
        menuPanel.add(paiements);
        menuPanel.add(budgets);
        menuPanel.add(revenus);


        // Zone centrale (CardLayout)


        CardLayout cardLayout = new CardLayout();
        JPanel panelCentre = new JPanel(cardLayout);

        // Page d'accueil
        panelCentre.add(new AccueilPanel(), "Accueil");

        // Pages temporaires
        panelCentre.add(new DepensePanel(), "Depenses");
        panelCentre.add(new CategoriePanel(), "Categories");
        panelCentre.add(new PaiementPanel(), "Paiements");
        panelCentre.add(new BudgetPanel(), "Budgets");
        panelCentre.add(new RevenuPanel(), "Revenus");


        // Ajout des composants


        add(topPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.WEST);
        add(panelCentre, BorderLayout.CENTER);


        // Navigation


        accueil.addActionListener(e ->
                cardLayout.show(panelCentre, "Accueil"));

        depenses.addActionListener(e ->
                cardLayout.show(panelCentre, "Depenses"));

        categories.addActionListener(e ->
                cardLayout.show(panelCentre, "Categories"));

        paiements.addActionListener(e ->
                cardLayout.show(panelCentre, "Paiements"));

        budgets.addActionListener(e ->
                cardLayout.show(panelCentre, "Budgets"));

        revenus.addActionListener(e ->
                cardLayout.show(panelCentre, "Revenus"));

        // Afficher l'accueil au démarrage
        cardLayout.show(panelCentre, "Accueil");

        setVisible(true);
    }

    // Méthode qui crée un panel provisoire
    private JPanel creerPanel(String texte) {

        JPanel panel = new JPanel(new GridBagLayout());

        JLabel label = new JLabel(texte);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(label);

        return panel;
    }
}