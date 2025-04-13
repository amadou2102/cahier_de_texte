package dao;

import models.Seance;
import utils.Basedonnee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeanceDao {

    // ✅ Ajout d'une séance
    public static boolean ajouterSeance(int seance) {
        String sql = "INSERT INTO Seance (dateSeance, dureeSeance, contenueSeance, idCours, etat) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Basedonnee.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, String.valueOf(seance));
            stmt.setString(2, String.valueOf(seance));
            stmt.setString(3, String.valueOf(seance));
            stmt.setInt(4, seance);
            stmt.setString(5, "non validée"); // état par défaut

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'ajout de la séance : " + e.getMessage());
            return false;
        }
    }

    // ✅ Récupérer les séances non validées
    public static List<Seance> getSeancesNonValidees() {
        List<Seance> seances = new ArrayList<>();
        Connection con = Basedonnee.getConnection();

        String sql = """
            SELECT s.idSeance, s.contenueSeance, s.dureeSeance, s.dateSeance, s.etat
            FROM Seance s
            WHERE s.etat = 'non validée'
        """;

        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Seance s = new Seance();
                s.setIdSeance(rs.getInt("idSeance"));
                s.setContenueSeance(rs.getString("contenueSeance"));
                s.setDureeSeance(rs.getString("dureeSeance"));
                s.setDateSeance(rs.getString("dateSeance"));
                s.setEtat(rs.getString("etat"));
                seances.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seances;
    }


    public boolean Seance(Seance seance) {
        return false;
    }
//le bouton validation
    public static boolean validerSeance(int idSeance) {
        String sql = "UPDATE Seance SET etat = 'validée' WHERE idSeance = ?";

        try (Connection conn = Basedonnee.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idSeance);
            return pst.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
