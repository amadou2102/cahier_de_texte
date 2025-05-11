package main;

import gui.AffecterCoursFrame;
import gui.GestionPersonnelFrame;
import gui.LoginFrame;
//import gui.ResponsableFrame;
import utils.Basedonnee;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);  // ✅ C’est tout !
        });


    }

}
