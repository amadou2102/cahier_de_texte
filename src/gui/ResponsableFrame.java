package gui;

import dao.ResponsableDAO;
import models.Responsable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ResponsableFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ResponsableFrame() {
        setTitle("Liste des Responsables");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());

        // En-têtes de colonnes
        String[] columns = {"ID", "Nom", "Prénom", "Email"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);

        // Boutons
        JPanel btnPanel = new JPanel();
        JButton btnSupprimer = new JButton("Supprimer");
        JButton btnActualiser = new JButton("Actualiser");
        JButton btnRetour = new JButton("Retour");
        JButton btnAjouter = new JButton("Ajouter");

        btnPanel.add(btnAjouter);
        btnPanel.add(btnSupprimer);
        btnPanel.add(btnActualiser);
        btnPanel.add(btnRetour);

        // Actions
        btnActualiser.addActionListener(e -> chargerDonnees());
        btnSupprimer.addActionListener(e -> supprimerSelection());
        btnAjouter.addActionListener(e -> afficherFormulaireAjout());
        btnRetour.addActionListener(e -> {
            dispose(); // Ferme la fenêtre actuelle
            new AccueilFrame(); // ← remplace ceci par ta vraie page d'accueil
        });

        // Ajouter composants
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);

        add(panel);
        chargerDonnees();
        setVisible(true);
    }

    private void chargerDonnees() {
        model.setRowCount(0); // Effacer
        List<Responsable> responsables = ResponsableDAO.listerResponsable();
        for (Responsable r : responsables) {
            model.addRow(new Object[]{r.getId(), r.getNom(), r.getPrenom(), r.getEmail()});
        }
    }

    private void supprimerSelection() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) model.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Supprimer ce responsable ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (ResponsableDAO.supprimerResponsable(id)) {
                    JOptionPane.showMessageDialog(this, "Responsable supprimé avec succès");
                    chargerDonnees();
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un responsable");
        }
    }

    private void afficherFormulaireAjout() {
        JTextField nomField = new JTextField(10);
        JTextField prenomField = new JTextField(10);
        JTextField emailField = new JTextField(15);
        JPasswordField passField = new JPasswordField(10);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nom :"));
        panel.add(nomField);
        panel.add(new JLabel("Prénom :"));
        panel.add(prenomField);
        panel.add(new JLabel("Email :"));
        panel.add(emailField);
        panel.add(new JLabel("Mot de passe :"));
        panel.add(passField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Ajouter un responsable", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            boolean ok = ResponsableDAO.ajouterResponsable(
                    nomField.getText(),
                    prenomField.getText(),
                    emailField.getText(),
                    new String(passField.getPassword())
            );
            if (ok) {
                JOptionPane.showMessageDialog(this, "Responsable ajouté");
                chargerDonnees();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout");
            }
        }
    }
}
