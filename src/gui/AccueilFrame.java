
package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class AccueilFrame extends JFrame {
    private String role;
    private String Utilisateur;
    private int idProfesseur;

    public AccueilFrame(String role, String nomUtilisateur, int idProfesseur) {
        this.role = role;
        this.Utilisateur = Utilisateur;
        this.idProfesseur = idProfesseur;


        setTitle("Accueil - " + role);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ➡️ Ajouter ici la barre de menu Déconnexion
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");



        JMenuItem deconnexionItem = new JMenuItem("🚪 Déconnexion");
        deconnexionItem.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });


        menu.add(deconnexionItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        JMenu menuOptions = new JMenu();



       /* JLabel label = new JLabel("Bienvenue " + nomUtilisateur + " (" + role + ")");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10)); */

        // 👤 Profil utilisateur
        JMenuItem itemProfil = new JMenuItem("Mon Profil");
        itemProfil.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Email : " + nomUtilisateur + "\nRôle : " + role,
                "Profil", JOptionPane.INFORMATION_MESSAGE));
        menuOptions.add(itemProfil);

        // ❓ Aide / Support
        JMenuItem itemAide = new JMenuItem("Aide");
        itemAide.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "📧 Contactez le support : support@tonapp.com\n📱 WhatsApp : +221 77 123 45 67",
                "Aide", JOptionPane.INFORMATION_MESSAGE));
        menuOptions.add(itemAide);

        // 🎨 Thème sombre/clair (bascule)
        JCheckBoxMenuItem itemTheme = new JCheckBoxMenuItem("Thème sombre");
        itemTheme.addActionListener(e -> {
            boolean isDark = itemTheme.isSelected();
            UIManager.put("control", isDark ? Color.DARK_GRAY : Color.WHITE);
            UIManager.put("info", isDark ? Color.DARK_GRAY : Color.WHITE);
            UIManager.put("nimbusBase", isDark ? Color.BLACK : Color.LIGHT_GRAY);
            UIManager.put("nimbusBlueGrey", isDark ? Color.GRAY : Color.LIGHT_GRAY);
            UIManager.put("text", isDark ? Color.WHITE : Color.BLACK);
            SwingUtilities.updateComponentTreeUI(this);
        });
        menuOptions.add(itemTheme);

        //À propos
        JMenuItem itemAbout = new JMenuItem("À propos");
        itemAbout.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "Application Cahier de Texte\nVersion 1.0\nDéveloppé par ton nom",
                "À propos", JOptionPane.INFORMATION_MESSAGE));
        menuOptions.add(itemAbout);

        menuBar.add(menuOptions);
        setJMenuBar(menuBar);


        // Haut : Bienvenue
        JLabel welcomeLabel = new JLabel("Bienvenue " + nomUtilisateur + " (" + role + ")");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        welcomeLabel.setForeground(new Color(0, 102, 204));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.NORTH);

        // Centre : Boutons d'action
        JPanel centerPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        centerPanel.setBackground(new Color(245, 250, 255));

        if (role.equalsIgnoreCase("professeur")) {
            addButton(centerPanel, "📚 Voir mes cours", e -> new MesCoursFrame(idProfesseur).setVisible(true));
            addButton(centerPanel, "📝 Ajouter une séance", e -> new SeanceFrame(idProfesseur).setVisible(true));
            addButton(centerPanel, "📋 Voir mes séances", e -> new MesSeancesFrame(idProfesseur).setVisible(true));
        } else if (role.equalsIgnoreCase("responsable")) {
            addButton(centerPanel, "✅ Valider les séances", e -> new ValidationSeanceFrame().setVisible(true));
            addButton(centerPanel, "📖 Voir cahier de texte", e -> new VoirCahierTexteFrame().setVisible(true));
            addButton(centerPanel, "📚 Voir les séances par cours", e -> new VoirSeancesParCoursFrame().setVisible(true));
        } else if (role.equalsIgnoreCase("chef_departement")) {
            addButton(centerPanel, "👨‍🏫 Gérer Professeurs", e -> new GestionPersonnelFrame().setVisible(true));
            addButton(centerPanel, "📋 Gérer Responsables", e -> new GestionPersonnelFrame().setVisible(true));
            addButton(centerPanel, "📚 Voir séances par cours", e -> new VoirSeancesParCoursFrame().setVisible(true));
            addButton(centerPanel, "➕ Affecter Cours", e -> new AffecterCoursFrame().setVisible(true));
        } else {
            centerPanel.add(new JLabel("Aucune action disponible"));
        }

        add(centerPanel, BorderLayout.CENTER);
    }

    private void addButton(JPanel panel, String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(0, 153, 76));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.addActionListener(action);
        panel.add(button);
    }
    // Constructeur vide pour compatibilité (pas utiliser normalement)
    public AccueilFrame() {}

        /* switch (role.toLowerCase()) {
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
            case "chef_departement":
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

*/

}

