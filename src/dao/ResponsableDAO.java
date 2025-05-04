package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import models.Professeur;
import models.Responsable;
import utils.Basedonnee;

public class ResponsableDAO {

    public static int getIdParEmail(String email) {
        int idResponsable = -1;
        Connection con = Basedonnee.getConnection();

        String sql = "SELECT p.idResponsable " +
                "FROM Responsable p " +
                "JOIN Utilisateurs u ON p.idUtilisateurs = u.idUtilisateurs " +
                "WHERE u.email = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                idResponsable = rs.getInt("idResponsable");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idResponsable;
    }

    // Ajouter un nouveau responsable
    public static boolean ajouterResponsable(String nom, String prenom, String email, String password) {
        try (Connection con = Basedonnee.getConnection()) {
            // 1. Insérer dans Utilisateurs
            String insertUser = "INSERT INTO Utilisateurs (nom, prenom, email,password, role) VALUES (?, ?, ?, ?, 'Professeur')";
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
                String insertResp = "INSERT INTO Responsable (idUtilisateurs) VALUES (?)";
                PreparedStatement pstResp = con.prepareStatement(insertResp);
                pstResp.setInt(1, idUtilisateur);
                pstResp.executeUpdate();

                return true;
            }

        } catch (Exception e) {
            System.err.println("❌ Erreur ajout RES: " + e.getMessage());
        }
        return false;
    }

    // Lister les professeurs
    // Lister les responsables
    public static List<Responsable> listerResponsable() {
        List<Responsable> respList = new ArrayList<>();

        String sql = """
        SELECT r.idResponsable, u.nom, u.prenom, u.email
        FROM Responsable r
        JOIN Utilisateurs u ON r.idUtilisateurs = u.idUtilisateurs
    """;

        try (Connection con = Basedonnee.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                respList.add(new Responsable(
                        rs.getInt("idResponsable"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return respList;
    }

    // Supprimer un responsable
    public static boolean supprimerResponsable(int idResponsable) {
        try (Connection con = Basedonnee.getConnection()) {
            String sql = "DELETE FROM Responsable WHERE idResponsable = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idResponsable);
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}








/*
public class ResponsableDAO {


    /*public static Responsable[] listerUtilisateursNonResponsables() {
        Responsable[] utilisateurs = ResponsableDAO.listerUtilisateursNonResponsables();
        for (Responsable u : utilisateurs) {
            JComboBox<Responsable> comboBox = null;
            comboBox.addItem(u);
        }

        String sql = """
            SELECT u.idUtilisateurs, u.nom, u.prenom, u.email
            FROM Utilisateurs u
            WHERE u.idUtilisateurs NOT IN (
                SELECT r.idUtilisateurs FROM Responsable r
            )
        """;

        try (Connection con = Basedonnee.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                utilisateurs.add(new Utilisateur(
                        rs.getInt("idUtilisateurs"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email")
                ));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return utilisateurs;

    }*/

  /*   public class ResponsbleDAO {


        public List<Responsable> listerResponsables() {
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
        }
    }

    public static int getIdParEmail(String email) {
        int idResponsable = -1;
        Connection con = Basedonnee.getConnection();

        String sql = "SELECT p.idResponsable " +
                "FROM Professeur p " +
                "JOIN Utilisateurs u ON p.idUtilisateurs = u.idUtilisateurs " +
                "WHERE u.email = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                idResponsable = rs.getInt("idResponsable");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idResponsable;
    }

    public static boolean ajouterResponsable(String nom, String prenom, String email, String password   ) {

        try (Connection con = Basedonnee.getConnection()) {
            // 1. Insérer dans Utilisateurs
            String insertUser = "INSERT INTO Utilisateurs (nom, prenom, email,password, role) VALUES (?, ?, ?, ?, 'Professeur')";
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
                String insertRes = "INSERT INTO Responsable (idUtilisateurs) VALUES (?)";
                PreparedStatement pstRes = con.prepareStatement(insertRes);
                pstRes.setInt(1, idUtilisateur);
                pstRes.executeUpdate();

                return true;
            }

        } catch (Exception e) {
            System.err.println("❌ Erreur ajout Res : " + e.getMessage());
        }
        return false;
    }

    // Ajouter un nouveau responsable
        public static boolean ajouterResponsble(String nom, String prenom, String email, String password) {
            try (Connection con = Basedonnee.getConnection()) {
                // 1. Insérer dans Utilisateurs
                String insertUser = "INSERT INTO Utilisateurs (nom, prenom, email,password, role) VALUES (?, ?, ?, ?, 'Professeur')";
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
                    String insertRes = "INSERT INTO Responsable (idUtilisateurs) VALUES (?)";
                    PreparedStatement pstRes = con.prepareStatement(insertRes);
                    pstRes.setInt(1, idUtilisateur);
                    pstRes.executeUpdate();

                    return true;
                }

            } catch (Exception e) {
                System.err.println("❌ Erreur ajout Res : " + e.getMessage());
            }
            return false;
        }

        // Lister les responsable
        public static List<Responsable> listerResponsables() {
            List<Responsable> profs = new ArrayList<>();

            String sql = """
        SELECT p.idResponsable, u.nom, u.prenom, u.email
        FROM Responsable p
        JOIN Utilisateurs u ON p.idUtilisateurs = u.idUtilisateurs
    """;

            try (Connection con = Basedonnee.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    profs.add(new Responsable(
                            rs.getInt("idResponsable"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("email")
                    ));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return profs;
        }

        // Supprimer un responsable
        public static boolean supprimerResponsable(int idRes) {
            try (Connection con = Basedonnee.getConnection()) {
                String sql = "DELETE FROM Professeur WHERE idProfesseur = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, idRes);
                return pst.executeUpdate() > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }






    }


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
    public static boolean ajouterResponsable(String email) {
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
*/


