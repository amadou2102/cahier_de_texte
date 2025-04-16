/*package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class accesBase {
    // Méthode pour récupérer la liste des cours depuis la base de données
        public List<String> getCoursFromDatabase() {
            List<String> cours = new ArrayList<>();
            String url = "jdbc:mysql://localhost:3306/nom_de_ta_base";
            String username = "ton_utilisateur";
            String password = "ton_mot_de_passe";

            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String query = "SELECT nomCours FROM Cours";
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {
                    while (rs.next()) {
                        cours.add(rs.getString("nomCours"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return cours;
        }

        // Méthode pour récupérer la liste des professeurs depuis la base de données
        public List<String> getProfesseursFromDatabase() {
            List<String> professeurs = new ArrayList<>();
            String url = "jdbc:mysql://localhost:3306/nom_de_ta_base";
            String username = "ton_utilisateur";
            String password = "ton_mot_de_passe";

            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String query = "SELECT nomProfesseur FROM Professeur";
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(query)) {
                    while (rs.next()) {
                        professeurs.add(rs.getString("nomProfesseur"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return professeurs;
        }
}

*/
