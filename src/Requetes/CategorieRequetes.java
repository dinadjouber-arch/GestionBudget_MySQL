package Requetes;

import BDD.SQLConnexion;
import model.Categorie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieRequetes {

    // Ajouter une catégorie
    public boolean ajouterCategorie(Categorie categorie) {

        String sql = "INSERT INTO categorie(nom, description) VALUES (?, ?)";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categorie.getNom());
            ps.setString(2, categorie.getDescription());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Modifier une catégorie
    public boolean modifierCategorie(Categorie categorie) {

        String sql = "UPDATE categorie SET nom=?, description=? WHERE id_categorie=?";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categorie.getNom());
            ps.setString(2, categorie.getDescription());
            ps.setInt(3, categorie.getIdCategorie());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Supprimer une catégorie
    public boolean supprimerCategorie(int idCategorie) {

        String sql = "DELETE FROM categorie WHERE id_categorie=?";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCategorie);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Récupérer toutes les catégories
    public List<Categorie> getToutesLesCategories() {

        List<Categorie> liste = new ArrayList<>();

        String sql = "SELECT * FROM categorie ORDER BY nom";

        try (Connection conn = SQLConnexion.getConnexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Categorie categorie = new Categorie();

                categorie.setIdCategorie(rs.getInt("id_categorie"));
                categorie.setNom(rs.getString("nom"));
                categorie.setDescription(rs.getString("description"));

                liste.add(categorie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    // Rechercher une catégorie par son id
    public Categorie rechercherParId(int idCategorie) {

        String sql = "SELECT * FROM categorie WHERE id_categorie=?";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCategorie);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Categorie categorie = new Categorie();

                categorie.setIdCategorie(rs.getInt("id_categorie"));
                categorie.setNom(rs.getString("nom"));
                categorie.setDescription(rs.getString("description"));

                return categorie;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}