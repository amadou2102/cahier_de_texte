package gui;

import dao.SeanceDao;
import models.Seance;

import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
import java.awt.*;
        import java.util.List;

public class MesSeancesFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public MesSeancesFrame(int idProfesseur) {
        setTitle("Mes Séances");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Date", "Durée", "Contenu", "Cours", "État"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        chargerSeances(idProfesseur);
    }

    private void chargerSeances(int idProfesseur) {
        List<Seance> seances = SeanceDao.getSeancesParProfesseur(idProfesseur);
        for (Seance s : seances) {
            model.addRow(new Object[]{
                    s.getDateSeance(),
                    s.getDureeSeance(),
                    s.getContenueSeance(),
                    s.getNomCours(),
                    s.getEtat()
            });
        }
    }
}
