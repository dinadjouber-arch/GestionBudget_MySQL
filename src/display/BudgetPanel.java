package display;

import Requetes.BudgetRequetes;
import esthetique.RoundedButton;
import model.Budget;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BudgetPanel extends JPanel {

    private JTextField txtId;
    private JTextField txtMontant;
    private JTextField txtMois;
    private JTextField txtAnnee;

    private RoundedButton btnAjouter;
    private RoundedButton btnModifier;
    private RoundedButton btnSupprimer;
    private RoundedButton btnActualiser;
    private RoundedButton btnVider;

    private JTable table;
    private DefaultTableModel modele;

    private BudgetRequetes budgetRequetes;

    public BudgetPanel() {

        budgetRequetes = new BudgetRequetes();

        setLayout(new BorderLayout(10,10));

        JPanel formulaire = new JPanel(new GridLayout(4,2,10,10));

        formulaire.setBorder(BorderFactory.createTitledBorder("Budget"));

        formulaire.add(new JLabel("ID"));
        txtId = new JTextField();
        txtId.setEditable(false);
        formulaire.add(txtId);

        formulaire.add(new JLabel("Montant"));
        txtMontant = new JTextField();
        formulaire.add(txtMontant);

        formulaire.add(new JLabel("Mois"));
        txtMois = new JTextField();
        formulaire.add(txtMois);

        formulaire.add(new JLabel("Année"));
        txtAnnee = new JTextField();
        formulaire.add(txtAnnee);

        add(formulaire,BorderLayout.NORTH);

        modele = new DefaultTableModel();

        modele.addColumn("ID");
        modele.addColumn("Montant");
        modele.addColumn("Mois");
        modele.addColumn("Année");

        table = new JTable(modele);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane,BorderLayout.CENTER);

        JPanel panelBoutons = new JPanel(new FlowLayout());

        btnAjouter = new RoundedButton("Ajouter");
        btnAjouter.setBackground(Color.pink);

        btnModifier = new RoundedButton("Modifier");
        btnModifier.setBackground(Color.pink);

        btnSupprimer = new RoundedButton("Supprimer");
        btnSupprimer.setBackground(Color.pink);

        btnActualiser = new RoundedButton("Actualiser");
        btnActualiser.setBackground(Color.pink);

        btnVider = new RoundedButton("Vider");
        btnVider.setBackground(Color.pink);

        panelBoutons.add(btnAjouter);
        panelBoutons.add(btnModifier);
        panelBoutons.add(btnSupprimer);
        panelBoutons.add(btnActualiser);
        panelBoutons.add(btnVider);

        add(panelBoutons,BorderLayout.SOUTH);

        chargerTable();


        // ActionListeners


        btnAjouter.addActionListener(e -> ajouterBudget());

        btnModifier.addActionListener(e -> modifierBudget());

        btnSupprimer.addActionListener(e -> supprimerBudget());

        btnActualiser.addActionListener(e -> chargerTable());

        btnVider.addActionListener(e -> viderChamps());

        table.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {

                int ligne = table.getSelectedRow();

                txtId.setText(modele.getValueAt(ligne, 0).toString());
                txtMontant.setText(modele.getValueAt(ligne, 1).toString());
                txtMois.setText(modele.getValueAt(ligne, 2).toString());
                txtAnnee.setText(modele.getValueAt(ligne, 3).toString());

            }

        });

    }

    // ==========================
    // Vider les champs
    // ==========================

    private void viderChamps() {

        txtId.setText("");
        txtMontant.setText("");
        txtMois.setText("");
        txtAnnee.setText("");

        table.clearSelection();

    }


    // Charger le tableau


    private void chargerTable() {

        modele.setRowCount(0);

        List<Budget> liste = budgetRequetes.getTousLesBudgets();

        for (Budget budget : liste) {

            modele.addRow(new Object[]{
                    budget.getIdBudget(),
                    budget.getMontant(),
                    budget.getMois(),
                    budget.getAnnee()
            });

        }

    }


    // Ajouter


    private void ajouterBudget() {

        try {

            Budget budget = new Budget();

            budget.setMontant(Double.parseDouble(txtMontant.getText()));
            budget.setMois(Integer.parseInt(txtMois.getText()));
            budget.setAnnee(Integer.parseInt(txtAnnee.getText()));

            if (budgetRequetes.ajouterBudget(budget)) {

                JOptionPane.showMessageDialog(this,
                        "Budget ajouté avec succès.");

                chargerTable();
                viderChamps();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Erreur lors de l'ajout.");

            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this,
                    "Veuillez saisir des valeurs numériques valides.");

        }
    }


    // Modifier


    private void modifierBudget() {

        if (txtId.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Sélectionnez un budget.");

            return;
        }

        try {

            Budget budget = new Budget();

            budget.setIdBudget(Integer.parseInt(txtId.getText()));
            budget.setMontant(Double.parseDouble(txtMontant.getText()));
            budget.setMois(Integer.parseInt(txtMois.getText()));
            budget.setAnnee(Integer.parseInt(txtAnnee.getText()));

            if (budgetRequetes.modifierBudget(budget)) {

                JOptionPane.showMessageDialog(this,
                        "Budget modifié avec succès.");

                chargerTable();
                viderChamps();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Erreur lors de la modification.");

            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this,
                    "Veuillez saisir des valeurs numériques valides.");

        }
    }


    // Supprimer


    private void supprimerBudget() {

        if (txtId.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Sélectionnez un budget.");

            return;
        }

        int choix = JOptionPane.showConfirmDialog(
                this,
                "Voulez-vous vraiment supprimer ce budget ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (choix == JOptionPane.YES_OPTION) {

            int id = Integer.parseInt(txtId.getText());

            if (budgetRequetes.supprimerBudget(id)) {

                JOptionPane.showMessageDialog(this,
                        "Budget supprimé.");

                chargerTable();
                viderChamps();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Erreur lors de la suppression.");

            }
        }
    }

}