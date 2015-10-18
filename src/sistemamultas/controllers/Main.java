package sistemamultas.controllers;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel;
import sistemamultas.views.Principal;

public class Main {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ImageIcon icone = new ImageIcon(getClass().getResource("/sistemamultas/res/Money-Calculator-icon16.png"));
                try {
                    UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao carregar o tema");
                }
                Principal tela = new Principal(icone);
            }
        });
    }
}
