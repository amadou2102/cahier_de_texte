
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
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // MenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem retourItem = new JMenuItem("🏠 Retour Accueil");
        retourItem.addActionListener(e -> {
            dispose();
            new AccueilFrame("Responsable", "Moi", -1).setVisible(true);
        });
        JMenuItem deconnexionItem = new JMenuItem("🚪 Déconnexion");
        deconnexionItem.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        menu.add(retourItem);
        menu.add(deconnexionItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    //TABLE
        model = new DefaultTableModel(new String[]{"ID", "Date", "Durée", "Contenu", "État"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        // add(scrollPane, BorderLayout.CENTER);

        JButton validerBtn = new JButton("Valider la séance sélectionnée");
        validerBtn.addActionListener(e -> validerSeance());

        JPanel btnPanel = new JPanel();
        btnPanel.add(validerBtn);
        add(new JLabel("Séances à valider :", JLabel.CENTER), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
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

        boolean success = SeanceDao.validerSeance(idSeance);;
        if (success) {
            JOptionPane.showMessageDialog(this, "✅ Séance validée !");
            chargerSeances();
        } else {
            JOptionPane.showMessageDialog(this, "❌ Erreur lors de la validation.");
        }
    }

}
