
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccueilFrame extends JFrame {

    public AccueilFrame(String role, String nomUtilisateur, int idProfesseur) {
        setTitle("Accueil - " + role);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Bienvenue " + nomUtilisateur + " (" + role + ")");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        switch (role.toLowerCase()) {
            case "professeur":
                buttonPanel.add(new JButton("Ajouter une séance"));
                buttonPanel.add(new JButton("Voir mes cours"));

                //mettre en action le bouton ajouter une seance
                JButton ajouterSeanceButton = new JButton("Ajouter une séance");

                ajouterSeanceButton.addActionListener(e -> {
                    SeanceFrame frame = new SeanceFrame();
                    frame.setVisible(true);
                });

                buttonPanel.add(ajouterSeanceButton);
                break;
            case "responsable":
                JButton btnValider = new JButton("Valider les séances");
                btnValider.addActionListener(e -> new ValidationSeanceFrame().setVisible(true));
                buttonPanel.add(btnValider);

                JButton btnVoirCahiers = new JButton("Voir les cahiers de texte");
                buttonPanel.add(btnVoirCahiers); // à connecter plus tard


                break;
            case "chef de département":
                // 🔹 Bouton : Gérer les professeurs
                JButton btnGererProfs = new JButton("Gérer les Professeurs");
                btnGererProfs.addActionListener(e -> new ProfesseurFrame().setVisible(true));
                buttonPanel.add(btnGererProfs);

                // 🔹 Bouton : Gérer les responsables
                JButton btnGererResponsables = new JButton("Gérer les Responsables");
                btnGererResponsables.addActionListener(e -> new ResponsableFrame().setVisible(true));
                buttonPanel.add(btnGererResponsables);

                buttonPanel.add(new JButton("Générer les fiches PDF"));
                buttonPanel.add(new JButton("Affecter les cours"));

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
