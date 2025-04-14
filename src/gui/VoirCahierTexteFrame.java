package gui;

import dao.ClasseDAO;
import dao.SeanceDao;
import models.Classe;
import models.Seance;

import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
import java.awt.*;
        import java.util.List;

public class VoirCahierTexteFrame extends JFrame {
    private JComboBox<Classe> classeCombo;
    private JTable table;
    private DefaultTableModel model;

    public VoirCahierTexteFrame() {
        setTitle("Cahier de texte - Séances validées");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔹 Haut : choix de la classe
        JPanel topPanel = new JPanel();
        classeCombo = new JComboBox<>();
        chargerClasses();
        JButton btnRechercher = new JButton("Rechercher");
        btnRechercher.addActionListener(e -> chargerSeances());
        topPanel.add(new JLabel("Classe :"));
        topPanel.add(classeCombo);
        topPanel.add(btnRechercher);
        add(topPanel, BorderLayout.NORTH);

        // 🔹 Tableau
        model = new DefaultTableModel(new String[]{"Date", "Durée", "Contenu", "Cours"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void chargerClasses() {
        List<Classe> classes = ClasseDAO.getAllClasses();
        for (Classe c : classes) {
            classeCombo.addItem(c);
        }
    }

    private void chargerSeances() {
        model.setRowCount(0);
        Classe classe = (Classe) classeCombo.getSelectedItem();
        if (classe == null) return;

        List<Seance> seances = SeanceDao.getSeancesValideesParClasse(classe.getIdClasse());
        for (Seance s : seances) {
            model.addRow(new Object[]{
                    s.getDateSeance(),
                    s.getDureeSeance(),
                    s.getContenueSeance(),
                    s.getNomCours()
            });
        }
    }
}
