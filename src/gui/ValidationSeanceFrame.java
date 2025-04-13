
package gui;

import dao.SeanceDao;
import models.Seance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ValidationSeanceFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ValidationSeanceFrame() {
        setTitle("Validation des séances");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel(new String[]{"ID", "Date", "Durée", "Contenu", "État"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton validerBtn = new JButton("Valider la séance sélectionnée");
        validerBtn.addActionListener(e -> validerSeance());

        JPanel btnPanel = new JPanel();
        btnPanel.add(validerBtn);
        add(btnPanel, BorderLayout.SOUTH);

        chargerSeances();
    }

    private void chargerSeances() {
        model.setRowCount(0);
        List<Seance> seances = SeanceDao.getSeancesNonValidees();
        for (Seance s : seances) {
            model.addRow(new Object[]{
                    s.getIdSeance(),
                    s.getDateSeance(),
                    s.getDureeSeance(),
                    s.getContenueSeance(),
                    s.getEtat()
            });
        }
    }

    private void validerSeance() {
        int selected = table.getSelectedRow();
        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une séance.");
            return;
        }

        int idSeance = (int) model.getValueAt(selected, 0);

        boolean success = SeanceDao.ajouterSeance(idSeance);
        if (success) {
            JOptionPane.showMessageDialog(this, "✅ Séance validée !");
            chargerSeances();
        } else {
            JOptionPane.showMessageDialog(this, "❌ Erreur lors de la validation.");
        }
    }

}
