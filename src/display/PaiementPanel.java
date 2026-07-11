package display;

import Requetes.MoyenPaiementRequetes;
import esthetique.RoundedButton;
import model.MoyenPaiement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PaiementPanel extends JPanel {

    private JTextField txtId;
    private JTextField txtNom;

    private RoundedButton btnAjouter;
    private RoundedButton btnModifier;
    private RoundedButton btnSupprimer;
    private RoundedButton btnActualiser;
    private RoundedButton btnVider;

    private JTable table;
    private DefaultTableModel modele;

    private MoyenPaiementRequetes paiementRequetes;

    public PaiementPanel() {

        paiementRequetes = new MoyenPaiementRequetes();

        setLayout(new BorderLayout(10,10));


        // Formulaire


        JPanel formulaire = new JPanel(new GridBagLayout());
        formulaire.setBorder(BorderFactory.createTitledBorder("Moyen de paiement"));

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

        add(formulaire, BorderLayout.NORTH);


        // Tableau


        modele = new DefaultTableModel();

        modele.addColumn("ID");
        modele.addColumn("Nom");

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


        btnAjouter.addActionListener(e -> ajouterPaiement());

        btnModifier.addActionListener(e -> modifierPaiement());

        btnSupprimer.addActionListener(e -> supprimerPaiement());

        btnActualiser.addActionListener(e -> chargerTable());

        btnVider.addActionListener(e -> viderChamps());

        table.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {

                int ligne = table.getSelectedRow();

                txtId.setText(modele.getValueAt(ligne, 0).toString());
                txtNom.setText(modele.getValueAt(ligne, 1).toString());

            }

        });

    }


    // Vider les champs


    private void viderChamps() {

        txtId.setText("");
        txtNom.setText("");

        table.clearSelection();

    }

    // ==========================
    // Charger le tableau
    // ==========================

    private void chargerTable() {

        modele.setRowCount(0);

        List<MoyenPaiement> liste = paiementRequetes.getTousLesMoyensPaiement();

        for (MoyenPaiement paiement : liste) {

            modele.addRow(new Object[]{
                    paiement.getIdPaiement(),
                    paiement.getNom()
            });

        }

    }


    // Ajouter


    private void ajouterPaiement() {

        if (txtNom.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Veuillez saisir un nom.");

            return;
        }

        MoyenPaiement paiement = new MoyenPaiement();

        paiement.setNom(txtNom.getText().trim());

        if (paiementRequetes.ajouterMoyenPaiement(paiement)) {

            JOptionPane.showMessageDialog(this,
                    "Moyen de paiement ajouté avec succès.");

            chargerTable();
            viderChamps();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Erreur lors de l'ajout.");

        }

    }


    // Modifier


    private void modifierPaiement() {

        if (txtId.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Sélectionnez un moyen de paiement.");

            return;
        }

        MoyenPaiement paiement = new MoyenPaiement();

        paiement.setIdPaiement(Integer.parseInt(txtId.getText()));
        paiement.setNom(txtNom.getText().trim());

        if (paiementRequetes.modifierMoyenPaiement(paiement)) {

            JOptionPane.showMessageDialog(this,
                    "Moyen de paiement modifié avec succès.");

            chargerTable();
            viderChamps();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Erreur lors de la modification.");

        }

    }


    // Supprimer


    private void supprimerPaiement() {

        if (txtId.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Sélectionnez un moyen de paiement.");

            return;
        }

        int choix = JOptionPane.showConfirmDialog(
                this,
                "Voulez-vous vraiment supprimer ce moyen de paiement ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (choix == JOptionPane.YES_OPTION) {

            int id = Integer.parseInt(txtId.getText());

            if (paiementRequetes.supprimerMoyenPaiement(id)) {

                JOptionPane.showMessageDialog(this,
                        "Moyen de paiement supprimé avec succès.");

                chargerTable();
                viderChamps();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Erreur lors de la suppression.");

            }

        }

    }

}