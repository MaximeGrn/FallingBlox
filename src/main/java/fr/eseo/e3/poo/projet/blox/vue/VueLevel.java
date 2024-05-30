package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.controleur.Gravite;
import fr.eseo.e3.poo.projet.blox.modele.Level;
import fr.eseo.e3.poo.projet.blox.modele.Puits;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** Classe responsable de l'affichage graphique du Niveau et de la vitesse de la gravité **/
public class VueLevel extends JPanel implements PropertyChangeListener {

    /*** Attributs ***/
    private Level level;
    private final Gravite gravite;

    /*** Constructeurs ***/
    public VueLevel(Puits puits, Gravite gravite) {
        this.level = puits.getLevel();
        this.gravite = gravite;
        this.setPreferredSize(new Dimension(70, 60));
        level.addPropertyChangeListener(this);
    }

    /*** Méthodes ***/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(128, 0, 128));
        g2d.setFont(new Font("Calibri", Font.BOLD, 12));

        // Afficher le niveau
        FontMetrics fm = g2d.getFontMetrics();
        String levelString = "Level : " + level.getLevel();
        g2d.drawString(levelString, 5, 10);

        // Afficher la vitesse de gravité (cases par seconde) avec retour à la ligne
        String speedLabel = "Speed :";
        String speedValue = String.format("%.1f c/s", 1000.0 / gravite.getPeriodicite());

        // Dessiner le label "Speed :"
        g2d.drawString(speedLabel, 5, 30);

        // Dessiner la valeur de la vitesse en dessous (centré)
        int stringWidth = fm.stringWidth(speedValue);
        int x = (this.getWidth() - stringWidth) / 2;
        g2d.drawString(speedValue, x, 48);

        g2d.dispose();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Level.MODIFICATION_LEVEL)) {
            // Mettre à jour le niveau et la gravité
            level = (Level) evt.getSource();
            int nouveauLevel = level.getLevel();
            int nouvellePeriodicite = 1000 - (nouveauLevel - 1) * 150; // Diminuer la périodicité

            // Ajuster la périodicité pour éviter des valeurs négatives
            nouvellePeriodicite = Math.max(nouvellePeriodicite, 100); // Minimum 100ms

            gravite.setPeriodicite(nouvellePeriodicite);
            repaint();
        }
    }
}