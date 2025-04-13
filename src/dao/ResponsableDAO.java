package dao;

import models.Responsable;
import utils.Basedonnee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResponsableDAO {

    public static List<Responsable> listerResponsables() {
        List<Responsable> responsables = new ArrayList<>();
        Connection con = Basedonnee.getConnection();
        String sql = "SELECT r.idResponsable, u.nom, u.prenom, u.email " +
                "FROM Responsable r JOIN Utilisateurs u ON r.idUtilisateurs = u.idUtilisateurs";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                responsables.add(new Responsable(
                        rs.getInt("idResponsable"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return responsables;
    }

    // suprimer un responsable
    public static boolean supprimerResponsable(int idResponsable) {
        Connection con = Basedonnee.getConnection();
        String sql = "DELETE FROM Responsable WHERE idResponsable = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idResponsable);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
// ajouter un responsable
    public static boolean ajouterResponsable(String email, int idClasse) {
        Connection con = Basedonnee.getConnection();
        String sql = "INSERT INTO Responsable (idUtilisateurs,idClasse) " +
                "SELECT idUtilisateurs FROM Utilisateurs WHERE email = ? " +
                "AND idUtilisateurs NOT IN (SELECT idUtilisateurs FROM Responsable)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idClasse);  // Correctement passer l'idClasse
            pst.setString(2, email);
            int rowsInserted = pst.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
