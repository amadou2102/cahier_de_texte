package main;

import gui.AffecterCoursFrame;
import gui.LoginFrame;
import gui.ResponsableFrame;
import utils.Basedonnee;
import utils.accesBase;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Créer et afficher la fenêtre de login
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);

        // Vérifier la connexion à la base de données
        if (Basedonnee.getConnection() != null) {
            // Si la connexion est réussie, afficher SeanceFrame après un login réussi
            loginFrame.addLoginListener();
        } else {
            // Afficher un message d'erreur si la connexion échoue
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données.");
        }

        // Optionnel: Affichage de ResponsableFrame après un certain événement ou condition
        SwingUtilities.invokeLater(() -> new ResponsableFrame().setVisible(true));


        //fenetre affectation
        accesBase dbHelper = new accesBase();
        // Exemple de cours et professeurs (devrait venir de la base de données)
        List<String> cours = List.of("Mathématiques", "Physique", "Informatique");
        List<String> professeurs = List.of("Prof. A", "Prof. B", "Prof. C");

        new AffecterCoursFrame(cours, professeurs);
    }

}
