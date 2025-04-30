package main;

import gui.AffecterCoursFrame;
import gui.GestionPersonnelFrame;
import gui.LoginFrame;
//import gui.ResponsableFrame;
import utils.Basedonnee;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);  // ✅ C’est tout !
        });


    }

}
