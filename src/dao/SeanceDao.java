package dao;

import models.Seance;
import utils.Basedonnee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeanceDao {

    // ✅ Ajout d'une séance
    public static boolean ajouterSeance(String date,String duree, String contenue, int idCours) {
        String sql = "INSERT INTO Seance ( dateSeance, dureeseance, contenueSeance, idCours,etat) VALUES (?, ?, ?, ?,?)";

        try (Connection conn = Basedonnee.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, date);
            stmt.setString(2,duree);
            stmt.setString(3, contenue);
            stmt.setInt(4, idCours);
            stmt.setString(5, "non validée");

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'ajout de la séance : " + e.getMessage());
            return false;
        }
    }

    /*
    public static boolean ajouterSeance(String date, String duree, String contenu, int idCours) {
        String sql = "INSERT INTO Seance (dateSeance, dureeSeance, contenueSeance, idCours, etat) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Basedonnee.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, date);           // ex: "2025-04-14"
            stmt.setString(2, duree);          // ex: "2h"
            stmt.setString(3, contenu);        // texte contenu
            stmt.setInt(4, idCours);           // id du cours
            stmt.setString(5, "non validée"); // état par défaut

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'ajout de la séance : " + e.getMessage());
            return false;
        }
    }*/

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
            System.err.println("❌ Erreur lors de la validation : " + e.getMessage());
            return false;
        }

    }

    public static List<Seance> getSeancesValideesParClasse(int idClasse) {
        List<Seance> seances = new ArrayList<>();
        Connection con = Basedonnee.getConnection();

        String sql = """
        SELECT s.idSeance, s.contenueSeance, s.dureeSeance, s.dateSeance, s.etat, c.nomCours
        FROM Seance s
        JOIN Cours c ON s.idCours = c.idCours
        JOIN Classe cl ON c.idClasse = cl.idClasse
        WHERE s.etat = 'validée' AND cl.idClasse = ?
    """;

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idClasse);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Seance s = new Seance();
                s.setIdSeance(rs.getInt("idSeance"));
                s.setContenueSeance(rs.getString("contenueSeance"));
                s.setDureeSeance(rs.getString("dureeSeance"));
                s.setDateSeance(rs.getString("dateSeance"));
                s.setEtat(rs.getString("etat"));
                s.setNomCours(rs.getString("nomCours")); // À ajouter dans le modèle si besoin
                seances.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seances;
    }

// PERMETTRE UN PROF DE VOIR CES SEANCES
    public static List<Seance> getSeancesParProfesseur(int idProfesseur) {
        List<Seance> seances = new ArrayList<>();
        Connection con = Basedonnee.getConnection();

        String sql = """
        SELECT s.idSeance, s.contenueSeance, s.dureeSeance, s.dateSeance, s.etat, c.nomCours
        FROM Seance s
        JOIN Cours c ON s.idCours = c.idCours
        WHERE c.idProfesseur = ?
    """;

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idProfesseur);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Seance s = new Seance();
                s.setIdSeance(rs.getInt("idSeance"));
                s.setContenueSeance(rs.getString("contenueSeance"));
                s.setDureeSeance(rs.getString("dureeSeance"));
                s.setDateSeance(rs.getString("dateSeance"));
                s.setEtat(rs.getString("etat"));
                s.setNomCours(rs.getString("nomCours"));
                seances.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seances;
    }

    public static List<Seance> getSeancesParCours(int idCours) {
        List<Seance> seances = new ArrayList<>();
        Connection con = Basedonnee.getConnection();

        String sql = """
        SELECT s.idSeance, s.dateSeance, s.dureeSeance, s.contenueSeance, s.etat, c.nomCours
        FROM Seance s
        JOIN Cours c ON s.idCours = c.idCours
        WHERE s.idCours = ?
    """;

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idCours);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Seance s = new Seance();
                s.setIdSeance(rs.getInt("idSeance"));
                s.setDateSeance(rs.getString("dateSeance"));
                s.setDureeSeance(rs.getString("dureeSeance"));
                s.setContenueSeance(rs.getString("contenueSeance"));
                s.setEtat(rs.getString("etat"));
                s.setNomCours(rs.getString("nomCours"));
                seances.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seances;
    }


}
