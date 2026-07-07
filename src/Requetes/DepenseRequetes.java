package Requetes;

import BDD.SQLConnexion;
import model.Categorie;
import model.Depense;
import model.MoyenPaiement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepenseRequetes {

    // ==========================
    // Ajouter une dépense
    // ==========================

    public boolean ajouterDepense(Depense depense) {

        String sql = """
                INSERT INTO depense
                (montant, date_depense, description, id_categorie, id_paiement)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, depense.getMontant());
            ps.setDate(2, Date.valueOf(depense.getDateDepense()));
            ps.setString(3, depense.getDescription());

            ps.setInt(4,
                    depense.getCategorie().getIdCategorie());

            ps.setInt(5,
                    depense.getMoyenPaiement().getIdPaiement());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }


    // ==========================
    // Modifier une dépense
    // ==========================

    public boolean modifierDepense(Depense depense) {

        String sql = """
                UPDATE depense
                SET montant = ?,
                    date_depense = ?,
                    description = ?,
                    id_categorie = ?,
                    id_paiement = ?
                WHERE id_depense = ?
                """;

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, depense.getMontant());
            ps.setDate(2, Date.valueOf(depense.getDateDepense()));
            ps.setString(3, depense.getDescription());

            ps.setInt(4, depense.getCategorie().getIdCategorie());
            ps.setInt(5, depense.getMoyenPaiement().getIdPaiement());

            ps.setInt(6, depense.getIdDepense());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    // ==========================
    // Supprimer une dépense
    // ==========================

    public boolean supprimerDepense(int idDepense) {

        String sql = "DELETE FROM depense WHERE id_depense = ?";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idDepense);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }



    // ==========================
    // Récupérer toutes les dépenses
    // ==========================

    public List<Depense> getToutesLesDepenses() {

        List<Depense> liste = new ArrayList<>();

        String sql = """
            SELECT d.id_depense,
                   d.montant,
                   d.date_depense,
                   d.description,

                   c.id_categorie,
                   c.nom AS nom_categorie,
                   c.description AS description_categorie,

                   p.id_paiement,
                   p.nom AS nom_paiement

            FROM depense d

            INNER JOIN categorie c
                ON d.id_categorie = c.id_categorie

            INNER JOIN moyen_paiement p
                ON d.id_paiement = p.id_paiement

            ORDER BY d.date_depense DESC
            """;

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Categorie categorie = new Categorie();
                categorie.setIdCategorie(rs.getInt("id_categorie"));
                categorie.setNom(rs.getString("nom_categorie"));
                categorie.setDescription(rs.getString("description_categorie"));

                MoyenPaiement paiement = new MoyenPaiement();
                paiement.setIdPaiement(rs.getInt("id_paiement"));
                paiement.setNom(rs.getString("nom_paiement"));

                Depense depense = new Depense();

                depense.setIdDepense(rs.getInt("id_depense"));
                depense.setMontant(rs.getDouble("montant"));
                depense.setDateDepense(
                        rs.getDate("date_depense").toLocalDate());
                depense.setDescription(rs.getString("description"));

                depense.setCategorie(categorie);
                depense.setMoyenPaiement(paiement);

                liste.add(depense);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return liste;

    }

    // ==========================
    // Obtenir le total des dépenses
    // ==========================

    public double getTotalDepenses() {

        double total = 0;

        String sql = "SELECT SUM(montant) AS total FROM depense";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {

                total = rs.getDouble("total");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return total;
    }


    }