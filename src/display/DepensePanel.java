package display;

import Requetes.CategorieRequetes;
import Requetes.DepenseRequetes;
import Requetes.MoyenPaiementRequetes;
import esthetique.RoundedButton;
import model.Categorie;
import model.Depense;
import model.MoyenPaiement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DepensePanel extends JPanel {

    private JTextField txtId;
    private JTextField txtMontant;
    private JTextField txtDate;
    private JTextField txtDescription;

    private JComboBox<Categorie> cbCategorie;
    private JComboBox<MoyenPaiement> cbPaiement;

    private RoundedButton btnAjouter;
    private RoundedButton btnModifier;
    private RoundedButton btnSupprimer;
    private RoundedButton btnActualiser;
    private RoundedButton btnVider;

    private JTable table;
    private DefaultTableModel modele;

    private DepenseRequetes depenseRequetes;
    private CategorieRequetes categorieRequetes;
    private MoyenPaiementRequetes paiementRequetes;

    public DepensePanel() {

        depenseRequetes = new DepenseRequetes();
        categorieRequetes = new CategorieRequetes();
        paiementRequetes = new MoyenPaiementRequetes();

        setLayout(new BorderLayout(10,10));

        // Formulaire


        JPanel formulaire = new JPanel(new GridBagLayout());
        formulaire.setBorder(BorderFactory.createTitledBorder("Nouvelle dépense"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formulaire.add(new JLabel("ID :"), gbc);

        gbc.gridx = 1;
        txtId = new JTextField(20);
        txtId.setEditable(false);
        formulaire.add(txtId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formulaire.add(new JLabel("Montant :"), gbc);

        gbc.gridx = 1;
        txtMontant = new JTextField();
        formulaire.add(txtMontant, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formulaire.add(new JLabel("Date (AAAA-MM-JJ) :"), gbc);

        gbc.gridx = 1;
        txtDate = new JTextField();
        formulaire.add(txtDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formulaire.add(new JLabel("Description :"), gbc);

        gbc.gridx = 1;
        txtDescription = new JTextField();
        formulaire.add(txtDescription, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formulaire.add(new JLabel("Catégorie :"), gbc);

        gbc.gridx = 1;
        cbCategorie = new JComboBox<>();
        formulaire.add(cbCategorie, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formulaire.add(new JLabel("Paiement :"), gbc);

        gbc.gridx = 1;
        cbPaiement = new JComboBox<>();
        formulaire.add(cbPaiement, gbc);

        add(formulaire, BorderLayout.NORTH);

        // Tableau

        modele = new DefaultTableModel();

        modele.addColumn("ID");
        modele.addColumn("Montant");
        modele.addColumn("Date");
        modele.addColumn("Description");
        modele.addColumn("Catégorie");
        modele.addColumn("Paiement");

        table = new JTable(modele);

        add(new JScrollPane(table), BorderLayout.CENTER);


        // Boutons


        JPanel boutons = new JPanel(new FlowLayout());

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

        boutons.add(btnAjouter);
        boutons.add(btnModifier);
        boutons.add(btnSupprimer);
        boutons.add(btnActualiser);
        boutons.add(btnVider);

        add(boutons, BorderLayout.SOUTH);

        chargerCategories();
        chargerPaiements();
        chargerTable();


        // ActionListeners

        btnAjouter.addActionListener(e -> ajouterDepense());

        btnModifier.addActionListener(e -> modifierDepense());

        btnSupprimer.addActionListener(e -> supprimerDepense());

        btnActualiser.addActionListener(e -> chargerTable());

        btnVider.addActionListener(e -> viderChamps());

        table.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {

                int ligne = table.getSelectedRow();

                txtId.setText(modele.getValueAt(ligne, 0).toString());
                txtMontant.setText(modele.getValueAt(ligne, 1).toString());
                txtDate.setText(modele.getValueAt(ligne, 2).toString());
                txtDescription.setText(modele.getValueAt(ligne, 3).toString());

            }

        });

    }


    // Vider les champs


    private void viderChamps() {

        txtId.setText("");
        txtMontant.setText("");
        txtDate.setText("");
        txtDescription.setText("");

        if (cbCategorie.getItemCount() > 0) {
            cbCategorie.setSelectedIndex(0);
        }

        if (cbPaiement.getItemCount() > 0) {
            cbPaiement.setSelectedIndex(0);
        }

        table.clearSelection();

    }

    // Charger le tableau


    private void chargerTable() {

        modele.setRowCount(0);

        List<Depense> liste = depenseRequetes.getToutesLesDepenses();

        for (Depense depense : liste) {

            modele.addRow(new Object[]{
                    depense.getIdDepense(),
                    depense.getMontant(),
                    depense.getDateDepense(),
                    depense.getDescription(),
                    depense.getCategorie().getNom(),
                    depense.getMoyenPaiement().getNom()
            });

        }

    }

    // Charger les catégories

    private void chargerCategories() {

        cbCategorie.removeAllItems();

        List<Categorie> liste = categorieRequetes.getToutesLesCategories();

        for (Categorie categorie : liste) {
            cbCategorie.addItem(categorie);
        }
    }


    // Charger les moyens de paiement


    private void chargerPaiements() {

        cbPaiement.removeAllItems();

        List<MoyenPaiement> liste = paiementRequetes.getTousLesMoyensPaiement();

        for (MoyenPaiement paiement : liste) {
            cbPaiement.addItem(paiement);
        }
    }


// Ajouter

    private void ajouterDepense() {

        try {

            Depense depense = new Depense();

            depense.setMontant(Double.parseDouble(txtMontant.getText()));
            depense.setDateDepense(java.time.LocalDate.parse(txtDate.getText()));
            depense.setDescription(txtDescription.getText().trim());

            depense.setCategorie((Categorie) cbCategorie.getSelectedItem());
            depense.setMoyenPaiement((MoyenPaiement) cbPaiement.getSelectedItem());

            if (depenseRequetes.ajouterDepense(depense)) {

                JOptionPane.showMessageDialog(this,
                        "Dépense ajoutée avec succès.");

                chargerTable();
                viderChamps();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Erreur lors de l'ajout.");

            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    "Vérifiez les informations saisies.");

        }

    }


// Modifier

    private void modifierDepense() {

        if (txtId.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Sélectionnez une dépense.");

            return;

        }

        try {

            Depense depense = new Depense();

            depense.setIdDepense(Integer.parseInt(txtId.getText()));
            depense.setMontant(Double.parseDouble(txtMontant.getText()));
            depense.setDateDepense(java.time.LocalDate.parse(txtDate.getText()));
            depense.setDescription(txtDescription.getText().trim());

            depense.setCategorie((Categorie) cbCategorie.getSelectedItem());
            depense.setMoyenPaiement((MoyenPaiement) cbPaiement.getSelectedItem());

            if (depenseRequetes.modifierDepense(depense)) {

                JOptionPane.showMessageDialog(this,
                        "Dépense modifiée.");

                chargerTable();
                viderChamps();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Erreur lors de la modification.");

            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    "Vérifiez les informations saisies.");

        }

    }


// Supprimer


    private void supprimerDepense() {

        if (txtId.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Sélectionnez une dépense.");

            return;

        }

        int choix = JOptionPane.showConfirmDialog(
                this,
                "Voulez-vous vraiment supprimer cette dépense ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (choix == JOptionPane.YES_OPTION) {

            int id = Integer.parseInt(txtId.getText());

            if (depenseRequetes.supprimerDepense(id)) {

                JOptionPane.showMessageDialog(this,
                        "Dépense supprimée.");

                chargerTable();
                viderChamps();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Erreur lors de la suppression.");

            }

        }

    }}