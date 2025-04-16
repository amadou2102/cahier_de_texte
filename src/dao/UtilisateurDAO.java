package dao;

import utils.Basedonnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UtilisateurDAO {

    public static boolean verifierConnexion(String email, String password, String role) {
        String sql = "SELECT * FROM Utilisateurs WHERE email = ? AND password = ? AND role = ?";

        try (Connection con = Basedonnee.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, email);
            pst.setString(2, password);
            pst.setString(3, role);

            ResultSet rs = pst.executeQuery();
            return rs.next(); // connexion OK si utilisateur trouvé

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
