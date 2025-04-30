package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Basedonnee {
    private static final String URL = "jdbc:mysql://localhost:3306/CAHIERTEXTE1";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Change si nécessaire

    public static Connection getConnection() {
        try {
            // 🔁 Charger le driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 🔌 Connexion
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connexion réussie à la base de données.");
            return conn;

        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver JDBC non trouvé : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ Erreur de connexion : " + e.getMessage());
        }
        return null;
    }
}
