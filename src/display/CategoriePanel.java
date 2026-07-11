package display;

import Requetes.CategorieRequetes;
import esthetique.RoundedButton;
import model.Categorie;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CategoriePanel extends JPanel {

    private JTextField txtId;
    private JTextField txtNom;
    private JTextArea txtDescription;

    private RoundedButton btnAjouter;
    private RoundedButton btnModifier;
    private RoundedButton btnSupprimer;
    private RoundedButton btnActualiser;
    private RoundedButton btnVider;

    private JTable table;
    private DefaultTableModel modele;

    private CategorieRequetes categorieRequetes;

    public CategoriePanel() {

        categorieRequetes = new CategorieRequetes();

        setLayout(new BorderLayout(10,10));


        // Formulaire

        JPanel formulaire = new JPanel(new GridBagLayout());
        formulaire.setBorder(BorderFactory.createTitledBorder("Catégorie"));

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
        formulaire.add(new JLabel("Nom :"), gbc);

        gbc.gridx = 1;
        txtNom = new JTextField(20);
        formulaire.add(txtNom, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formulaire.add(new JLabel("Description :"), gbc);

        gbc.gridx = 1;
        txtDescription = new JTextArea(4,20);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);

        formulaire.add(new JScrollPane(txtDescription), gbc);

        add(formulaire, BorderLayout.NORTH);

        // Tableau


        modele = new DefaultTableModel();

        modele.addColumn("ID");
        modele.addColumn("Nom");
        modele.addColumn("Description");

        table = new JTable(modele);

        add(new JScrollPane(table), BorderLayout.CENTER);


        // Boutons


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

        add(panelBoutons, BorderLayout.SOUTH);

        chargerTable();


        // ActionListeners


        btnAjouter.addActionListener(e -> ajouterCategorie());

        btnModifier.addActionListener(e -> modifierCategorie());

        btnSupprimer.addActionListener(e -> supprimerCategorie());

        btnActualiser.addActionListener(e -> chargerTable());

        btnVider.addActionListener(e -> viderChamps());

        table.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {

                int ligne = table.getSelectedRow();

                txtId.setText(modele.getValueAt(ligne, 0).toString());
                txtNom.setText(modele.getValueAt(ligne, 1).toString());
                txtDescription.setText(modele.getValueAt(ligne, 2).toString());

            }

        });

    }

    // Vider les champs


    private void viderChamps() {

        txtId.setText("");
        txtNom.setText("");
        txtDescription.setText("");

        table.clearSelection();

    }


    // Charger le tableau


    private void chargerTable() {

        modele.setRowCount(0);

        List<Categorie> liste = categorieRequetes.getToutesLesCategories();

        for (Categorie categorie : liste) {

            modele.addRow(new Object[] {
                    categorie.getIdCategorie(),
                    categorie.getNom(),
                    categorie.getDescription()
            });

        }

    }


    // Ajouter une catégorie


    private void ajouterCategorie() {

        if (txtNom.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Veuillez saisir un nom.");

            return;
        }

        Categorie categorie = new Categorie();

        categorie.setNom(txtNom.getText().trim());
        categorie.setDescription(txtDescription.getText().trim());

        if (categorieRequetes.ajouterCategorie(categorie)) {

            JOptionPane.showMessageDialog(this,
                    "Catégorie ajoutée avec succès.");

            chargerTable();
            viderChamps();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Erreur lors de l'ajout.");
        }
    }


    // Modifier une catégorie


    private void modifierCategorie() {

        if (txtId.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Sélectionnez une catégorie.");

            return;
        }

        Categorie categorie = new Categorie();

        categorie.setIdCategorie(Integer.parseInt(txtId.getText()));
        categorie.setNom(txtNom.getText().trim());
        categorie.setDescription(txtDescription.getText().trim());

        if (categorieRequetes.modifierCategorie(categorie)) {

            JOptionPane.showMessageDialog(this,
                    "Catégorie modifiée avec succès.");

            chargerTable();
            viderChamps();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Erreur lors de la modification.");
        }
    }

    // ==========================
    // Supprimer une catégorie
    // ==========================

    private void supprimerCategorie() {

        if (txtId.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Sélectionnez une catégorie.");

            return;
        }

        int choix = JOptionPane.showConfirmDialog(
                this,
                "Voulez-vous vraiment supprimer cette catégorie ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (choix == JOptionPane.YES_OPTION) {

            int id = Integer.parseInt(txtId.getText());

            if (categorieRequetes.supprimerCategorie(id)) {

                JOptionPane.showMessageDialog(this,
                        "Catégorie supprimée avec succès.");

                chargerTable();
                viderChamps();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Erreur lors de la suppression.");
            }
        }
    }
}