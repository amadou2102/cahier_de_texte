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
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tableau
      /*  model = new DefaultTableModel(new String[]{"ID Cours", "Nom du cours"}, 0);
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
    }*/

        // MenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem retourItem = new JMenuItem("🏠 Retour Accueil");
        retourItem.addActionListener(e -> {
            dispose();
            new AccueilFrame("Professeur", "Moi", idProfesseur).setVisible(true);
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

        // Liste des cours
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(245, 250, 255));

        DefaultListModel<String> model = new DefaultListModel<>();
        List<Cours> cours = coursDao.getCoursParProfesseur(idProfesseur);

        for (Cours c : cours) {
            model.addElement("📘 " + c.getNomCours());
        }

        JList<String> coursList = new JList<>(model);
        coursList.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(coursList);

        panel.add(new JLabel("Vos cours :", JLabel.CENTER), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
    }

}

