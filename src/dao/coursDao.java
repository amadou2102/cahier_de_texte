package dao;

import models.Cours;
import utils.Basedonnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class coursDao {
    // Ce que fait ce code :Il prend en paramètre l’ID du professeur Il cherche dans la table CoursProfesseur les cours associés
//Il retourne une liste d’objets Cours

    public List<Cours> getCoursByProfesseurId(int idProfesseur) {
        List<Cours> coursList = new ArrayList<>();

        String sql = """
            SELECT c.idCours, c.nomCours
            FROM cours c
            INNER JOIN coursprofesseur cp ON cp.idCours = c.idCours
            WHERE cp.idProfesseur = ?
        """;

        try (Connection conn = Basedonnee.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProfesseur);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idCours");
                String nom = rs.getString("nomCours");
                coursList.add(new Cours(id, nom));
            }

        } catch (Exception e) {
            System.err.println("❌ Erreur récupération cours : " + e.getMessage());
        }

        return coursList;
    }

    public static boolean affecterCours(int idCours, int idProfesseur) {
        String sql = "UPDATE Cours SET idProfesseur = ? WHERE idCours = ?";

        try (Connection conn = Basedonnee.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProfesseur);
            stmt.setInt(2, idCours);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.err.println("❌ Erreur lors de l'affectation du cours : " + e.getMessage());
            return false;
        }
    }


}
