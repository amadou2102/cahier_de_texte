package gui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleCombo;
    private JLabel statusLabel;

    public LoginFrame() {
        setTitle("Connexion au Cahier de Texte");
        setSize(400, 600); // la taille de l interface
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🔹 Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        ImageIcon logoIcon = new ImageIcon("image/Logo.jpg"); // ← chemin vers ton logo
        Image img = logoIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(img));
        add(logoLabel, BorderLayout.NORTH);

        // Panel principal
        JPanel panel = new JPanel(new GridLayout(4, 4, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 15, 300, 40));

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Mot de passe:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        // Rôle
        panel.add(new JLabel("Rôle:"));
        roleCombo = new JComboBox<>(new String[] {
                "Responsable",
                "Professeur",
                "Chef_departement"
        });
        panel.add(roleCombo);

        JButton loginButton = new JButton("Se connecter");
        loginButton.addActionListener(e -> afficherMessage());
        panel.add(loginButton);

        panel.add(loginButton);

        statusLabel = new JLabel("");
        panel.add(statusLabel);

        add(panel, BorderLayout.CENTER);
    }

    // pour les parametres de retour
    public LoginFrame(String role, String email) {
    }

    /**
     * Méthode appelée lors du clic sur "Se connecter".
     * Gère la validation des champs et l'ouverture de la fenêtre d'accueil.
     */

    private void afficherMessage() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String role = (String) roleCombo.getSelectedItem();

        if (email.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Veuillez remplir tous les champs.");
            statusLabel.setForeground(Color.RED);
            return;
        }

        boolean isValid = dao.UtilisateurDAO.verifierConnexion(email, password, role);

        if (isValid) {
            statusLabel.setText("Connexion réussie !");
            statusLabel.setForeground(Color.GREEN);
            dispose();

            // Si Professeur → récupérer l’ID depuis la base
            if (role.equalsIgnoreCase("Professeur")) {
                int idProfesseur = dao.ProfesseurDao.getIdParEmail(email);
                new AccueilFrame(role, email, idProfesseur).setVisible(true);
            } else {
                new AccueilFrame(role, email, -1).setVisible(true);
            }
        } else {
            statusLabel.setText("Email, mot de passe ou rôle incorrect !");
            statusLabel.setForeground(Color.RED);
        }
    }

    /*
     * private void afficherMessage() { // actionPerformed équivalent
     * :contentReference[oaicite:1]{index=1}
     * String email = emailField.getText();
     * String motDePasse = new String(passwordField.getPassword());
     * String role = (String) roleCombo.getSelectedItem();
     * 
     * if (email.isEmpty() || motDePasse.isEmpty()) {
     * statusLabel.setText("Veuillez remplir tous les champs.");
     * statusLabel.setForeground(Color.RED);
     * } else {
     * statusLabel.setText("Connexion réussie !");
     * statusLabel.setForeground(Color.GREEN);
     * // Ferme la fenêtre de login et ouvre l'accueil
     * dispose();
     * //permet de recuperer l id du prof
     * if (role.equalsIgnoreCase("Professeur")) {
     * int idProfesseur =1; // à récupérer selon le user
     * new AccueilFrame(role, email, idProfesseur).setVisible(true);
     * }
     * else {
     * new AccueilFrame(role, email, -1).setVisible(true);
     * }
     * }
     * }
     */

}
