package display;

import esthetique.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class FenetreAccueil extends JFrame {

    private CardLayout cardLayout;
    private JPanel panelCentre;


    private AccueilPanel accueilPanel;
    private DepensePanel depensePanel;
    private CategoriePanel categoriePanel;
    private PaiementPanel paiementPanel;
    private BudgetPanel budgetPanel;
    private RevenuPanel revenuPanel;

    public FenetreAccueil() {

        setTitle("Gestion des dépenses personnelles");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // Barre du haut

        JPanel topPanel = new JPanel();

        JLabel titre = new JLabel("Gestion des dépenses personnelles");
        titre.setFont(new Font("Arial", Font.BOLD, 24));
        topPanel.setBackground(Color.pink);

        topPanel.add(titre);

        // Menu de gauche

        JPanel menuPanel = new JPanel();

        menuPanel.setLayout(new GridLayout(6, 1, 10, 10));
        menuPanel.setPreferredSize(new Dimension(220, 0));

        RoundedButton btnAccueil = new RoundedButton("🏠 Accueil");
        btnAccueil.setBackground(Color.pink);


        RoundedButton btnDepenses = new RoundedButton("💸 Dépenses");
        btnDepenses.setBackground(Color.pink);


        RoundedButton btnCategories = new RoundedButton("📂 Catégories");
        btnCategories.setBackground(Color.pink);


        RoundedButton btnPaiements = new RoundedButton("💳 Paiements");
        btnPaiements.setBackground(Color.pink);


        RoundedButton btnBudget = new RoundedButton("📈 Budget");
        btnBudget.setBackground(Color.pink);


        RoundedButton btnRevenus = new RoundedButton("💰 Revenus");
        btnRevenus.setBackground(Color.pink);



        menuPanel.add(btnAccueil);
        menuPanel.add(btnDepenses);
        menuPanel.add(btnCategories);
        menuPanel.add(btnPaiements);
        menuPanel.add(btnBudget);
        menuPanel.add(btnRevenus);

        // Zone central

        cardLayout = new CardLayout();
        panelCentre = new JPanel(cardLayout);

        accueilPanel = new AccueilPanel();
        depensePanel = new DepensePanel();
        categoriePanel = new CategoriePanel();
        paiementPanel = new PaiementPanel();
        budgetPanel = new BudgetPanel();
        revenuPanel = new RevenuPanel();

        panelCentre.add(accueilPanel, "Accueil");
        panelCentre.add(depensePanel, "Depenses");
        panelCentre.add(categoriePanel, "Categories");
        panelCentre.add(paiementPanel, "Paiements");
        panelCentre.add(budgetPanel, "Budget");
        panelCentre.add(revenuPanel, "Revenus");

        add(topPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.WEST);
        add(panelCentre, BorderLayout.CENTER);

        // Navigation



        btnAccueil.addActionListener(e -> afficherPanel("Accueil"));
        btnDepenses.addActionListener(e -> afficherPanel("Depenses"));
        btnCategories.addActionListener(e -> afficherPanel("Categories"));
        btnPaiements.addActionListener(e -> afficherPanel("Paiements"));
        btnBudget.addActionListener(e -> afficherPanel("Budget"));
        btnRevenus.addActionListener(e -> afficherPanel("Revenus"));

        afficherPanel("Accueil");

        setVisible(true);
    }

    private void afficherPanel(String nomPanel) {

        if (nomPanel.equals("Accueil")) {
            accueilPanel.chargerStatistiques();
        }

        cardLayout.show(panelCentre, nomPanel);
    }
}