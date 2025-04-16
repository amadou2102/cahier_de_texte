package gui;

import dao.coursDao;
import dao.SeanceDao;
import models.Cours;
import models.Seance;

import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
import java.awt.*;
        import java.util.List;

public class VoirSeancesParCoursFrame extends JFrame {

    private JComboBox<Cours> coursCombo;
    private JTable table;
    private DefaultTableModel model;

    public VoirSeancesParCoursFrame() {
        setTitle("Séances par cours");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        coursCombo = new JComboBox<>();
        chargerCours();
        JButton btnAfficher = new JButton("Afficher les séances");
        btnAfficher.addActionListener(e -> afficherSeances());

        topPanel.add(new JLabel("Cours :"));
        topPanel.add(coursCombo);
        topPanel.add(btnAfficher);
        add(topPanel, BorderLayout.NORTH);

        model = new DefaultTableModel(new String[]{"Date", "Durée", "Contenu", "État"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void chargerCours() {
        List<Cours> coursList = coursDao.getAllCours(); // À implémenter si besoin
        for (Cours c : coursList) {
            coursCombo.addItem(c);
        }
    }

    private void afficherSeances() {
        model.setRowCount(0);
        Cours cours = (Cours) coursCombo.getSelectedItem();
        if (cours == null) return;

        List<Seance> seances = SeanceDao.getSeancesParCours(cours.getIdCours());
        for (Seance s : seances) {
            model.addRow(new Object[]{
                    s.getDateSeance(),
                    s.getDureeSeance(),
                    s.getContenueSeance(),
                    s.getEtat()
            });
        }
    }
}
