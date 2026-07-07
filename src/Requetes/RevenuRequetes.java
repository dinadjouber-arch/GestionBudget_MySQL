package Requetes;


import BDD.SQLConnexion;
import model.Revenu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RevenuRequetes {

    // Ajouter un revenu
    public boolean ajouterRevenu(Revenu revenu) {

        String sql = "INSERT INTO revenu(montant, date_revenu, description) VALUES (?, ?, ?)";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, revenu.getMontant());
            ps.setDate(2, Date.valueOf(revenu.getDateRevenu()));
            ps.setString(3, revenu.getDescription());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Modifier un revenu
    public boolean modifierRevenu(Revenu revenu) {

        String sql = "UPDATE revenu SET montant=?, date_revenu=?, description=? WHERE id_revenu=?";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, revenu.getMontant());
            ps.setDate(2, Date.valueOf(revenu.getDateRevenu()));
            ps.setString(3, revenu.getDescription());
            ps.setInt(4, revenu.getIdRevenu());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Supprimer un revenu
    public boolean supprimerRevenu(int idRevenu) {

        String sql = "DELETE FROM revenu WHERE id_revenu=?";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idRevenu);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Récupérer tous les revenus
    public List<Revenu> getTousLesRevenus() {

        List<Revenu> liste = new ArrayList<>();

        String sql = "SELECT * FROM revenu ORDER BY date_revenu DESC";

        try (Connection conn = SQLConnexion.getConnexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Revenu revenu = new Revenu();

                revenu.setIdRevenu(rs.getInt("id_revenu"));
                revenu.setMontant(rs.getDouble("montant"));
                revenu.setDateRevenu(rs.getDate("date_revenu").toLocalDate());
                revenu.setDescription(rs.getString("description"));

                liste.add(revenu);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    // Rechercher un revenu par son id
    public Revenu rechercherParId(int idRevenu) {

        String sql = "SELECT * FROM revenu WHERE id_revenu=?";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idRevenu);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Revenu revenu = new Revenu();

                revenu.setIdRevenu(rs.getInt("id_revenu"));
                revenu.setMontant(rs.getDouble("montant"));
                revenu.setDateRevenu(rs.getDate("date_revenu").toLocalDate());
                revenu.setDescription(rs.getString("description"));

                return revenu;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public double getTotalRevenus() {

        String sql = "SELECT SUM(montant) AS total FROM revenu";

        try (Connection conn = SQLConnexion.getConnexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}