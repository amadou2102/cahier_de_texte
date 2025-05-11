package gui;



import dao.ClasseDAO;
import dao.ResponsableDAO;
import models.Classe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ResponsableFrame extends JFrame {
    private GestionPersonnelFrame parent;

    private JTextField nomField;
    private JTextField prenomField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<Classe> classeCombo;

    public ResponsableFrame(GestionPersonnelFrame parent) {
        this.parent = parent;
        setTitle("Ajouter un Responsable");
        setSize(350, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));

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

        add(new JLabel("Classe :"));
        classeCombo = new JComboBox<Classe>();
        List<Classe> classes = ClasseDAO.getAllClasses();
        for (Classe c : classes) {
            classeCombo.addItem(c);
        }
        add(classeCombo);

        JButton ajouterBtn = new JButton("Ajouter");
        ajouterBtn.addActionListener(e -> {
            String nom = nomField.getText().trim();
            String prenom = prenomField.getText().trim();
            String email = emailField.getText().trim();
            String mdp = new String(passwordField.getPassword());
            Classe classe = (Classe) classeCombo.getSelectedItem();

            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || mdp.isEmpty() || classe == null) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
                return;
            }

            boolean success = ResponsableDAO.ajouterResponsable(nom, prenom, email, mdp, classe.getIdClasse());
            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Responsable ajouté !");
                parent.chargerPersonnel(); // Rafraîchit la liste dans la fenêtre parent
                dispose(); // Fermer la fenêtre
            } else {
                JOptionPane.showMessageDialog(this, "❌ Erreur lors de l'ajout.");
            }
        });

        JButton annulerBtn = new JButton("Annuler");
        annulerBtn.addActionListener(e -> dispose());

        add(annulerBtn);
        add(ajouterBtn);
    }
}

