package display;

import Requetes.RevenuRequetes;
import esthetique.RoundedButton;
import model.Revenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RevenuPanel extends JPanel {

    private JTextField txtId;
    private JTextField txtMontant;
    private JTextField txtDate;
    private JTextField txtDescription;

    private JButton btnAjouter;
    private JButton btnModifier;
    private JButton btnSupprimer;
    private JButton btnActualiser;
    private JButton btnVider;

    private JTable table;
    private DefaultTableModel modele;

    private RevenuRequetes revenuRequetes;

    public RevenuPanel() {

        revenuRequetes = new RevenuRequetes();

        setLayout(new BorderLayout(10,10));

        // Formulaire


        JPanel formulaire = new JPanel(new GridBagLayout());
        formulaire.setBorder(BorderFactory.createTitledBorder("Revenu"));

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

        add(formulaire, BorderLayout.NORTH);


        // Tableau


        modele = new DefaultTableModel();

        modele.addColumn("ID");
        modele.addColumn("Montant");
        modele.addColumn("Date");
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


        btnAjouter.addActionListener(e -> ajouterRevenu());

        btnModifier.addActionListener(e -> modifierRevenu());

        btnSupprimer.addActionListener(e -> supprimerRevenu());

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

    // ==========================
    // Vider les champs
    // ==========================

    private void viderChamps() {

        txtId.setText("");
        txtMontant.setText("");
        txtDate.setText("");
        txtDescription.setText("");

        table.clearSelection();

    }

    // ==========================
    // Charger le tableau
    // ==========================

    private void chargerTable() {

        modele.setRowCount(0);

        List<Revenu> liste = revenuRequetes.getTousLesRevenus();

        for (Revenu revenu : liste) {

            modele.addRow(new Object[]{
                    revenu.getIdRevenu(),
                    revenu.getMontant(),
                    revenu.getDateRevenu(),
                    revenu.getDescription()
            });

        }

    }

    // ==========================
    // Ajouter un revenu
    // ==========================

    private void ajouterRevenu() {

        try {

            Revenu revenu = new Revenu();

            revenu.setMontant(Double.parseDouble(txtMontant.getText()));
            revenu.setDateRevenu(java.time.LocalDate.parse(txtDate.getText()));
            revenu.setDescription(txtDescription.getText().trim());

            if (revenuRequetes.ajouterRevenu(revenu)) {

                JOptionPane.showMessageDialog(this,
                        "Revenu ajouté avec succès.");

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

    // ==========================
    // Modifier un revenu
    // ==========================

    private void modifierRevenu() {

        if (txtId.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Sélectionnez un revenu.");

            return;

        }

        try {

            Revenu revenu = new Revenu();

            revenu.setIdRevenu(Integer.parseInt(txtId.getText()));
            revenu.setMontant(Double.parseDouble(txtMontant.getText()));
            revenu.setDateRevenu(java.time.LocalDate.parse(txtDate.getText()));
            revenu.setDescription(txtDescription.getText().trim());

            if (revenuRequetes.modifierRevenu(revenu)) {

                JOptionPane.showMessageDialog(this,
                        "Revenu modifié avec succès.");

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

    // ==========================
    // Supprimer un revenu
    // ==========================

    private void supprimerRevenu() {

        if (txtId.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Sélectionnez un revenu.");

            return;

        }

        int choix = JOptionPane.showConfirmDialog(
                this,
                "Voulez-vous vraiment supprimer ce revenu ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (choix == JOptionPane.YES_OPTION) {

            int id = Integer.parseInt(txtId.getText());

            if (revenuRequetes.supprimerRevenu(id)) {

                JOptionPane.showMessageDialog(this,
                        "Revenu supprimé avec succès.");

                chargerTable();
                viderChamps();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Erreur lors de la suppression.");

            }

        }

    }

}