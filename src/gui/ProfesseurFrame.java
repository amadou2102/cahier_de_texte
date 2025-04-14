package gui;

import dao.ProfesseurDao;

import javax.swing.*;
import java.awt.*;

public class ProfesseurFrame extends JFrame {
    private GestionPersonnelFrame parent;

    private JTextField nomField;
    private JTextField prenomField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public ProfesseurFrame(GestionPersonnelFrame parent) {
        this.parent = parent;
        setTitle("Ajouter un Professeur");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Nom :"));
        nomField = new JTextField();
        add(nomField);

        add(new JLabel("Prénom :"));
        prenomField = new JTextField();
        add(prenomField);

        add(new JLabel("Email :"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Mot de passe :"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton ajouterBtn = new JButton("Ajouter");
        ajouterBtn.addActionListener(e -> {
            String nom = nomField.getText().trim();
            String prenom = prenomField.getText().trim();
            String email = emailField.getText().trim();
            String mdp = new String(passwordField.getPassword());

            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
                return;
            }

            boolean success = ProfesseurDao.ajouterProfesseur(nom, prenom, email, mdp);
            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Professeur ajouté !");
                parent.chargerPersonnel(); // Rafraîchit la liste dans la fenêtre parent
                dispose(); // Fermer la fenêtre
            } else {
                JOptionPane.showMessageDialog(this, "❌ Erreur lors de l’ajout.");
            }
        });

        add(new JLabel());
        add(ajouterBtn);
    }


}

