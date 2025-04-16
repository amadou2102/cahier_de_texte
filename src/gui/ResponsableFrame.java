package gui;
/*
import dao.ProfesseurDao;
//import dao.ResponsableDAO;


import models.Classe;
import models.Responsable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ResponsableFrame extends JFrame {
    private GestionPersonnelFrame parent;

    private JTextField nomField;
    private JTextField prenomField;
    private JTextField emailField;
    private JPasswordField passwordField;


    public ResponsableFrame(GestionPersonnelFrame parent) {
        this.parent = parent;
        setTitle("Ajouter un Responsable");
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

            boolean success = ResponsableDAO.ajouterResponsable(nom, prenom, email, mdp);
            if (success) {
                JOptionPane.showMessageDialog(this, "✅ responsable ajouté !");
                parent.chargerPersonnel(); // Rafraîchit la liste dans la fenêtre parent
                dispose(); // Fermer la fenêtre
            } else {
                JOptionPane.showMessageDialog(this, "❌ Erreur lors de l’ajout.");
            }
        });

        add(new JLabel());
        add(ajouterBtn);

    }
/*
        JComboBox<Responsable> comboUtilisateurs = new JComboBox<>();
        for (Responsable r : ResponsableDAO.listerUtilisateursNonResponsables()) {
            comboUtilisateurs.addItem(r);
        }

    }




}



   /* private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<Classe> classeCombo;*/

 /*   public ResponsableFrame() {
        setTitle("Gestion des Responsables");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // --- TABLEAU ---
        tableModel = new DefaultTableModel(new String[]{"ID", "Nom", "Prénom", "Email"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // --- BOUTONS ---
        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(e -> ajouterResponsable());

        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.addActionListener(e -> supprimerResponsable());

        JPanel panelBoutons = new JPanel();
        panelBoutons.add(btnAjouter);
        panelBoutons.add(btnSupprimer);
        add(panelBoutons, BorderLayout.SOUTH);

        // --- ACTIONS ---



        chargerResponsables();
    }





    private void chargerResponsables() {
        tableModel.setRowCount(0); // vider le tableau
        List<Responsable> responsables = ResponsableDAO.listerResponsables();
        for (Responsable r : responsables) {
            tableModel.addRow(new Object[]{
                    r.getId(), r.getNom(), r.getPrenom(), r.getEmail()
            });
        }
    }

    private void ajouterResponsable() {
        private void ajouterResponsable() {
            String email = JOptionPane.showInputDialog(this, "Email du responsable :");

            if (email != null && !email.trim().isEmpty()) {
                boolean success = ResponsableDAO.ajouterResponsableParEmail(email.trim());

                if (success) {
                    JOptionPane.showMessageDialog(this, "✅ Responsable ajouté avec succès.");
                    chargerResponsables(); // Rafraîchir la table
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Échec : l'email n'existe pas ou est déjà utilisé.");
                }
            }
        }


    }

    private void supprimerResponsable() {
        int ligne = table.getSelectedRow();
        if (ligne >= 0) {
            int id = (int) tableModel.getValueAt(ligne, 0);
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Confirmer la suppression du responsable ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ResponsableDAO.supprimerResponsable(id);
                chargerResponsables();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un responsable à supprimer.");
        }
    }

  */
//}
