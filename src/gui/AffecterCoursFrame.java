package gui;

import utils.accesBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class AffecterCoursFrame extends JFrame {
    private JComboBox<String> comboCours;
    private JComboBox<String> comboProfesseur;
    private JButton btnAffecter;
    private JLabel lblMessage;

    public AffecterCoursFrame(List<String> cours, List<String> professeurs) {
        setTitle("Affectation de Cours");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        // Initialisation des composants
        JLabel lblCours = new JLabel("Choisir un Cours : ");
        comboCours = new JComboBox<>(cours.toArray(new String[0]));

        JLabel lblProfesseur = new JLabel("Choisir un Professeur : ");
        comboProfesseur = new JComboBox<>(professeurs.toArray(new String[0]));

        btnAffecter = new JButton("Affecter");
        lblMessage = new JLabel("");
        lblMessage.setForeground(Color.RED);

        // Ajouter les composants à la fenêtre
        add(lblCours);
        add(comboCours);
        add(lblProfesseur);
        add(comboProfesseur);
        add(btnAffecter);
        add(lblMessage);

        // Ajouter action pour le bouton
        btnAffecter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String coursSelectionne = (String) comboCours.getSelectedItem();
                String professeurSelectionne = (String) comboProfesseur.getSelectedItem();

                if (coursSelectionne != null && professeurSelectionne != null) {
                    // Logique pour l'affectation (exemple simple)
                    lblMessage.setText("Cours '" + coursSelectionne + "' affecté à " + professeurSelectionne);
                    lblMessage.setForeground(Color.GREEN);
                } else {
                    lblMessage.setText("Erreur : Veuillez sélectionner un cours et un professeur.");
                }
            }
        });

        setVisible(true);
    }


}
