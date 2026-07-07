package Requetes;

import BDD.SQLConnexion;
import model.MoyenPaiement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoyenPaiementRequetes {

    // Ajouter un moyen de paiement
    public boolean ajouterMoyenPaiement(MoyenPaiement moyenPaiement) {

        String sql = "INSERT INTO moyen_paiement(nom) VALUES (?)";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, moyenPaiement.getNom());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Modifier un moyen de paiement
    public boolean modifierMoyenPaiement(MoyenPaiement moyenPaiement) {

        String sql = "UPDATE moyen_paiement SET nom=? WHERE id_paiement=?";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, moyenPaiement.getNom());
            ps.setInt(2, moyenPaiement.getIdPaiement());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Supprimer un moyen de paiement
    public boolean supprimerMoyenPaiement(int idPaiement) {

        String sql = "DELETE FROM moyen_paiement WHERE id_paiement=?";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPaiement);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Récupérer tous les moyens de paiement
    public List<MoyenPaiement> getTousLesMoyensPaiement() {

        List<MoyenPaiement> liste = new ArrayList<>();

        String sql = "SELECT * FROM moyen_paiement ORDER BY nom";

        try (Connection conn = SQLConnexion.getConnexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                MoyenPaiement moyenPaiement = new MoyenPaiement();

                moyenPaiement.setIdPaiement(rs.getInt("id_paiement"));
                moyenPaiement.setNom(rs.getString("nom"));

                liste.add(moyenPaiement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return liste;
    }

    // Rechercher un moyen de paiement par son id
    public MoyenPaiement rechercherParId(int idPaiement) {

        String sql = "SELECT * FROM moyen_paiement WHERE id_paiement=?";

        try (Connection conn = SQLConnexion.getConnexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPaiement);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                MoyenPaiement moyenPaiement = new MoyenPaiement();

                moyenPaiement.setIdPaiement(rs.getInt("id_paiement"));
                moyenPaiement.setNom(rs.getString("nom"));

                return moyenPaiement;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}