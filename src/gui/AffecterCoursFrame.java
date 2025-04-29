package gui;

import dao.ProfesseurDao;
import dao.coursDao;
import models.Cours;
import models.Professeur;

import javax.swing.*;
import java.awt.*;

public class AffecterCoursFrame extends JFrame {
    private JComboBox<Cours> comboCours;
    private JComboBox<Professeur> comboProfesseur;
    private JButton btnAffecter;
    private JLabel lblMessage;

    public AffecterCoursFrame() {
        setTitle("Affectation de Cours");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        // Composants
        JLabel lblCours = new JLabel("Choisir un Cours : ");
        comboCours = new JComboBox<>();
        for (Cours c : coursDao.getAllCours()) {
            comboCours.addItem(c);
        }

        JLabel lblProfesseur = new JLabel("Choisir un Professeur : ");
        comboProfesseur = new JComboBox<>();
        for (Professeur p : ProfesseurDao.listerProfesseurs()) {
            comboProfesseur.addItem(p);
        }

        btnAffecter = new JButton("Affecter");
        lblMessage = new JLabel("");
        lblMessage.setForeground(Color.RED);

        add(lblCours);
        add(comboCours);
        add(lblProfesseur);
        add(comboProfesseur);
        add(btnAffecter);
        add(lblMessage);

        btnAffecter.addActionListener(e -> {
            Cours cours = (Cours) comboCours.getSelectedItem();
            Professeur prof = (Professeur) comboProfesseur.getSelectedItem();

            if (cours != null && prof != null) {
                boolean ok = coursDao.affecterCours(cours.getIdCours(), prof.getId());
                if (ok) {
                    lblMessage.setText("✅ Cours affecté !");
                    lblMessage.setForeground(Color.GREEN);
                } else {
                    lblMessage.setText("❌ Échec de l'affectation.");
                    lblMessage.setForeground(Color.RED);
                }
            } else {
                lblMessage.setText("❌ Sélection invalide.");
            }
        });

        setVisible(true);
    }
}
