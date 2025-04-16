package dao;

import models.Classe;
import utils.Basedonnee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClasseDAO {

    public static List<Classe> getAllClasses() {
        List<Classe> classes = new ArrayList<>();
        String sql = "SELECT idClasse, nomClasse FROM Classe";

        try (Connection con = Basedonnee.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Classe classe = new Classe(
                        rs.getInt("idClasse"),
                        rs.getString("nomClasse")
                );
                classes.add(classe);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return classes;
    }

    /*public static void main(String[] args) {
        List<Classe> classes = ClasseDAO.getAllClasses();
        for (Classe c : classes) {
            System.out.println("✔ Classe : " + c.getNomClasse());
        }
    }*/
}
