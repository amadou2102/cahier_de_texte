package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Professeur;
import utils.Basedonnee;

public class ProfesseurDao {

    public static int getIdParEmail(String email) {
        int idProfesseur = -1;
        Connection con = Basedonnee.getConnection();

        String sql = "SELECT p.idProfesseur " +
                "FROM Professeur p " +
                "JOIN Utilisateurs u ON p.idUtilisateurs = u.idUtilisateurs " +
                "WHERE u.email = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                idProfesseur = rs.getInt("idProfesseur");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idProfesseur;
    }

    // Ajouter un nouveau professeur
    public static boolean ajouterProfesseur(String nom, String prenom, String email, String password) {
        try (Connection con = Basedonnee.getConnection()) {
            // 1. Insérer dans Utilisateurs
            String insertUser = "INSERT INTO Utilisateurs (nomUtilisateur, prenomUtilisateur, email, motDePasse, role) VALUES (?, ?, ?, ?, 'Professeur')";
            PreparedStatement pstUser = con.prepareStatement(insertUser, PreparedStatement.RETURN_GENERATED_KEYS);
            pstUser.setString(1, nom);
            pstUser.setString(2, prenom);
            pstUser.setString(3, email);
            pstUser.setString(4, password);
            pstUser.executeUpdate();

            ResultSet rs = pstUser.getGeneratedKeys();
            if (rs.next()) {
                int idUtilisateur = rs.getInt(1);

                // 2. Insérer dans Professeur
                String insertProf = "INSERT INTO Professeur (idUtilisateurs) VALUES (?)";
                PreparedStatement pstProf = con.prepareStatement(insertProf);
                pstProf.setInt(1, idUtilisateur);
                pstProf.executeUpdate();

                return true;
            }

        } catch (Exception e) {
            System.err.println("❌ Erreur ajout prof : " + e.getMessage());
        }
        return false;
    }

    // Lister les professeurs
    public static List<Professeur> listerProfesseurs() {
        List<models.Professeur> profs = new ArrayList<>();

        String sql = """
        SELECT p.idProfesseur, u.nomUtilisateur, u.prenomUtilisateur, u.email
        FROM Professeur p
        JOIN Utilisateurs u ON p.idUtilisateurs = u.idUtilisateurs
    """;

        try (Connection con = Basedonnee.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                profs.add(new models.Professeur(
                        rs.getInt("idProfesseur"),
                        rs.getString("nomUtilisateur"),
                        rs.getString("prenomUtilisateur"),
                        rs.getString("email")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return profs;
    }

    // Supprimer un professeur
    public static boolean supprimerProfesseur(int idProf) {
        try (Connection con = Basedonnee.getConnection()) {
            String sql = "DELETE FROM Professeur WHERE idProfesseur = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idProf);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



}
