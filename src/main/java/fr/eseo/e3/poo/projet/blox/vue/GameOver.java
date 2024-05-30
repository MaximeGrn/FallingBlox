package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.FallingBloxVersion1;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/** Classe responsable de l'affichage graphique du pop-up Game Over **/
public class GameOver extends JDialog implements ActionListener {

    /*** Attributs ***/
    private final JButton rejouerBouton;

    /*** Constructeurs ***/
    public GameOver(JFrame parent, boolean modal) {
        super(parent, modal);
        setTitle("Game Over");
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Contenu du pop-up
        JPanel panneau = new JPanel(new GridLayout(2, 1));
        panneau.setBackground(new Color(255, 165, 0));
        JLabel message = new JLabel("Vous avez perdu !", SwingConstants.CENTER);
        message.setFont(new Font("Calibri", Font.BOLD, 20));
        message.setForeground(Color.WHITE);
        panneau.add(message);

        rejouerBouton = new JButton("Rejouer");
        rejouerBouton.addActionListener(this);
        rejouerBouton.setOpaque(true);
        rejouerBouton.setContentAreaFilled(true);
        rejouerBouton.setBorderPainted(false);
        rejouerBouton.setBackground(UIManager.getColor("Button.background"));

        // Ajout d'un MouseListener pour changer la couleur du bouton au survol
        rejouerBouton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                rejouerBouton.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                rejouerBouton.setBackground(UIManager.getColor(Color.RED));
            }
        });

        panneau.add(rejouerBouton);
        add(panneau);

        // Gestionnaire d'événement pour la fermeture de la fenêtre
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    /*** Méthodes ***/
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rejouerBouton) {
            JFrame parent = (JFrame) getParent();

            // Fermeture du pop-up et de la fenêtre parente
            dispose();
            parent.dispose();
            SwingUtilities.invokeLater(() -> {
                try {
                    Thread.sleep(100); // Délai pour permettre la fermeture complète des fenêtres
                } catch (InterruptedException ex) {
                    // Ignorer l'exception
                }
                FallingBloxVersion1.main(new String[0]);
            });
        }
    }
}