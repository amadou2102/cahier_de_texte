package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Basedonnee {
    private static final String URL = "jdbc:mysql://localhost:3306/CAHIERTEXTE1";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Mets ton mot de passe MySQL ici

    /*


    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Connexion réussie à la base de données.");

            } catch (SQLException e) {
                System.err.println("❌ Erreur de connexion : " + e.getMessage());
            }
        }
        return connection;
    }*/

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("❌ Erreur de connexion : " + e.getMessage());
            return null;
        }
    }

}
