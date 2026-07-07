package Requetes;

import BDD.SQLConnexion;
import model.Budget;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BudgetRequetes {

    // Ajouter un budget
    public boolean ajouterBudget(Budget budget) {

        String sql = "INSERT INTO budget(montant, mois, annee) VALUES (?, ?, ?)";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, budget.getMontant());
            ps.setInt(2, budget.getMois());
            ps.setInt(3, budget.getAnnee());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Modifier un budget
    public boolean modifierBudget(Budget budget) {

        String sql = "UPDATE budget SET montant=?, mois=?, annee=? WHERE id_budget=?";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, budget.getMontant());
            ps.setInt(2, budget.getMois());
            ps.setInt(3, budget.getAnnee());
            ps.setInt(4, budget.getIdBudget());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Supprimer un budget
    public boolean supprimerBudget(int idBudget) {

        String sql = "DELETE FROM budget WHERE id_budget=?";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idBudget);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Récupérer tous les budgets
    public List<Budget> getTousLesBudgets() {

        List<Budget> liste = new ArrayList<>();

        String sql = "SELECT * FROM budget ORDER BY annee DESC, mois DESC";

        try (Connection conn = SQLConnexion.getConnexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Budget budget = new Budget();

                budget.setIdBudget(rs.getInt("id_budget"));
                budget.setMontant(rs.getDouble("montant"));
                budget.setMois(rs.getInt("mois"));
                budget.setAnnee(rs.getInt("annee"));

                liste.add(budget);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    // Rechercher un budget par son id
    public Budget rechercherParId(int idBudget) {

        String sql = "SELECT * FROM budget WHERE id_budget=?";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idBudget);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Budget budget = new Budget();

                budget.setIdBudget(rs.getInt("id_budget"));
                budget.setMontant(rs.getDouble("montant"));
                budget.setMois(rs.getInt("mois"));
                budget.setAnnee(rs.getInt("annee"));

                return budget;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public double getBudget() {

        String sql = "SELECT montant FROM budget LIMIT 1";

        try (Connection conn = SQLConnexion.getConnexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble("montant");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
