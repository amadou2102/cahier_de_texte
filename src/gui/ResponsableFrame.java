package gui;

import dao.ResponsableDAO;
import models.Classe;
import models.Responsable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ResponsableFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<Classe> classeCombo;

    public ResponsableFrame() {
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
        JButton btnSupprimer = new JButton("Supprimer");

        JPanel panelBoutons = new JPanel();
        panelBoutons.add(btnAjouter);
        panelBoutons.add(btnSupprimer);
        add(panelBoutons, BorderLayout.SOUTH);

        // --- ACTIONS ---
        btnAjouter.addActionListener(e -> ajouterResponsable());
        btnSupprimer.addActionListener(e -> supprimerResponsable());

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
        new ResponsableFrame().setVisible(true);

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
}
