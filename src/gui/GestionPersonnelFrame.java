    package gui;

    import dao.ProfesseurDao;
                                                            //import dao.ResponsableDAO;

    import models.Professeur;
    import models.Responsable;

    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    import java.awt.*;
    import java.util.List;

    public class GestionPersonnelFrame extends JFrame {
        private JTable table;
        private DefaultTableModel model;
                                                                    // private ResponsableDAO.ResponsbleDAO ResponsableDAO;

        public GestionPersonnelFrame() {
            setTitle("Gestion du Personnel");
            setSize(800, 450);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            model = new DefaultTableModel(new String[]{"ID", "Nom", "Prénom", "Email", "Rôle"}, 0);
            table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);

            JButton ajouterProfBtn = new JButton("Ajouter un Professeur");
            ajouterProfBtn.addActionListener(e -> new ProfesseurFrame(this).setVisible(true));

            JButton ajouterRespBtn = new JButton("Ajouter un Responsable");
            JButton supprimerBtn = new JButton("Supprimer le compte sélectionné");

            ajouterProfBtn.addActionListener(e -> new ProfesseurFrame(this).setVisible(true));
         //   ajouterRespBtn.addActionListener(e -> new ResponsableFrame(this).setVisible(true));
         //   supprimerBtn.addActionListener(e -> supprimerCompte());

            JPanel btnPanel = new JPanel();
            btnPanel.add(ajouterProfBtn);
            btnPanel.add(ajouterRespBtn);
            btnPanel.add(supprimerBtn);

            add(scrollPane, BorderLayout.CENTER);
            add(btnPanel, BorderLayout.SOUTH);

            chargerPersonnel();
        }

        public void chargerPersonnel() {
            model.setRowCount(0); // vider le tableau

            List<Professeur> profs = ProfesseurDao.listerProfesseurs();
            for (Professeur p : profs) {
                model.addRow(new Object[]{
                        p.getId(), p.getNom(), p.getPrenom(), p.getEmail(), "Professeur"
                });
            }

                                                                        /*    List<Responsable> resps = ResponsableDAO.listerResponsables();
            for (Responsable r : resps) {
                model.addRow(new Object[]{
                        r.getId(), r.getNom(), r.getPrenom(), r.getEmail(), "Responsable"
                });
            }
        }*/

        //private void supprimerCompte() {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Sélectionnez un compte.");
                return;
            }

            int idUtilisateur = (int) model.getValueAt(selectedRow, 0);
            String role = (String) model.getValueAt(selectedRow, 4);

            boolean success = false;

            if (role.equalsIgnoreCase("Professeur")) {
                success = ProfesseurDao.supprimerProfesseur(idUtilisateur);
            } else if (role.equalsIgnoreCase("Responsable")) {
   //             success = ResponsableDAO.supprimerResponsable(idUtilisateur);
            }

            if (success) {
                JOptionPane.showMessageDialog(this, "✅ Compte supprimé !");
                chargerPersonnel();
            } else {
                JOptionPane.showMessageDialog(this, "❌ Échec de la suppression.");
            }
        }
    }

