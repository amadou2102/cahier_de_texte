package gui;

import models.Cours;
import dao.coursDao;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MesCoursFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public MesCoursFrame(int idProfesseur) {
        setTitle("Mes cours");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tableau
        model = new DefaultTableModel(new String[]{"ID Cours", "Nom du cours"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        chargerCours(idProfesseur);
    }

    private void chargerCours(int idProfesseur) {
        List<Cours> coursList = coursDao.getCoursParProfesseur(idProfesseur);
        for (Cours c : coursList) {
            model.addRow(new Object[]{
                    c.getIdCours(),
                    c.getNomCours()
            });
        }
    }
}

