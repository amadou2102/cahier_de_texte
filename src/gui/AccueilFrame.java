
package gui;

import javax.swing.*;
import java.awt.*;

public class AccueilFrame extends JFrame {

    public AccueilFrame(String role, String nomUtilisateur, int idProfesseur) {
        setTitle("Accueil - " + role);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Bienvenue " + nomUtilisateur + " (" + role + ")");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        switch (role.toLowerCase()) {
            case "professeur":

                JButton btnVoirMesCours = new JButton("Voir mes cours");
                btnVoirMesCours.addActionListener(e -> new MesCoursFrame(idProfesseur).setVisible(true));
                buttonPanel.add(btnVoirMesCours);

                //mettre en action le bouton ajouter une seance
                JButton ajouterSeanceButton = new JButton("Ajouter une séance");

                ajouterSeanceButton.addActionListener(e -> {
                    SeanceFrame frame = new SeanceFrame(idProfesseur);
                    frame.setVisible(true);

                });

                buttonPanel.add(ajouterSeanceButton);

                //voir la liste des seance ajouté
                JButton btnVoirMesSeances = new JButton("Voir mes séances");
                btnVoirMesSeances.addActionListener(e -> new MesSeancesFrame(idProfesseur).setVisible(true));
                buttonPanel.add(btnVoirMesSeances);
                break;
            case "responsable":
                JButton btnValider = new JButton("Valider les séances");
                btnValider.addActionListener(e -> new ValidationSeanceFrame().setVisible(true));
                buttonPanel.add(btnValider);


                JButton btnCahierTexte = new JButton("Voir cahier de texte");
                btnCahierTexte.addActionListener(e -> new VoirCahierTexteFrame().setVisible(true));
                buttonPanel.add(btnCahierTexte);



                JButton btnVoirSeances = new JButton("Voir les séances par cours");
                btnVoirSeances.addActionListener(e -> new VoirSeancesParCoursFrame().setVisible(true));
                buttonPanel.add(btnVoirSeances);



                break;
            case "chef de département":
                // 🔹 Bouton : Gérer les professeurs
                JButton btnGererProfs = new JButton("Gérer les Professeurs");
                btnGererProfs.addActionListener(e -> new GestionPersonnelFrame().setVisible(true));
                buttonPanel.add(btnGererProfs);

                // 🔹 Bouton : Gérer les responsables
                JButton btnGererResponsables = new JButton("Gérer les Responsables");
                btnGererResponsables.addActionListener(e -> new GestionPersonnelFrame().setVisible(true));
                buttonPanel.add(btnGererResponsables);

                JButton btnVoirSeancesCours = new JButton("Voir les séances par cours");
                btnVoirSeancesCours.addActionListener(e -> new VoirSeancesParCoursFrame().setVisible(true));
                buttonPanel.add(btnVoirSeancesCours);


                JButton btnAffecterCours = new JButton("Affecter les cours");
                btnAffecterCours.addActionListener(e -> new AffecterCoursFrame().setVisible(true));
                buttonPanel.add(btnAffecterCours);



                buttonPanel.add(new JButton("Générer les fiches PDF"));


                break;
            default:
                buttonPanel.add(new JLabel("Aucune action disponible."));
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
        add(panel);


    }

    public AccueilFrame(String professeur, String email, String idProfesseur) {
    }
}
