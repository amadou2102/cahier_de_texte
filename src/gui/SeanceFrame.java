
package gui;

import dao.SeanceDao;
import dao.coursDao;
import models.Cours;
import models.Seance;


import javax.swing.*;
import java.awt.*;

public class SeanceFrame extends JFrame {

    private JTextField dateField;
    private JTextField dureeField;

    private JTextArea contenuArea;
    private JButton ajouterButton;
    private JComboBox<Cours> coursCombo;


    public SeanceFrame(int idProfesseur) {
        setTitle("Ajouter une Séance");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 🔽 Liste déroulante des cours
        panel.add(new JLabel("Cours :"));
        coursCombo = new JComboBox<>();
        for (Cours c : coursDao.getCoursParProfesseur(idProfesseur)) {
            coursCombo.addItem(c);
        }
        panel.add(coursCombo);
        // Date
        panel.add(new JLabel("Date (AAAA-MM-JJ) :"));
        dateField = new JTextField();
        panel.add(dateField);

        // Durée
        panel.add(new JLabel("Durée (ex: 2h) :"));
        dureeField = new JTextField();
        panel.add(dureeField);


        // Contenu
        panel.add(new JLabel("Contenu de la séance :"));
        contenuArea = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(contenuArea);
        panel.add(scrollPane);

        // Bouton
        ajouterButton = new JButton("Ajouter");
        panel.add(ajouterButton);

        // Action du bouton
      /*  ajouterButton.addActionListener(e -> {
            String date = dateField.getText();
            String duree = dureeField.getText();
            String contenu = contenuArea.getText();



            // Id du cours à remplacer plus tard dynamiquement
            int idCours = 1;

            if (date.isEmpty() || duree.isEmpty() || contenu.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Création de l'objet séance
            Seance seance = new Seance();

            // Insertion via DAO
            //SeanceDao dao = new SeanceDao();
          //  boolean success = dao.Seance(seance);

            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Séance ajoutée avec succès !");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Échec de l'ajout de la séance.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(panel, BorderLayout.CENTER);
    }
*/

        ajouterButton.addActionListener(e -> {
            String date = dateField.getText().trim();
            String duree = dureeField.getText().trim();
            String contenu = contenuArea.getText().trim();

            Cours cours = (Cours) coursCombo.getSelectedItem();
            if (cours == null) {
                JOptionPane.showMessageDialog(this, "Aucun cours sélectionné !");
                return;
            }
            int idCours = cours.getIdCours();


            if (date.isEmpty() || contenu.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String nomSeance = JOptionPane.showInputDialog(this, "Entrez le nom de la séance :");
            if (nomSeance == null || nomSeance.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nom de séance requis !");
                return;
            }

            boolean success = SeanceDao.ajouterSeance(nomSeance, date, contenu, idCours);

            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Séance ajoutée avec succès !");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Échec de l'ajout de la séance.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(panel, BorderLayout.CENTER);


    }



}
