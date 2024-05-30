package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.Score;

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

/** Classe responsable de l'affichage graphique du Score et des Lignes complétées **/
public class VueScore extends JPanel implements PropertyChangeListener {

    /*** Attributs ***/
    private final Score score;
    private int lignesSupprimees;
    private final Puits puits;

    /*** Constructeurs ***/
    public VueScore(Puits puits) {
        this.puits = puits;
        this.score = puits.getScore();
        this.lignesSupprimees = puits.getTas().getTotalLignesSupprimees();
        this.setPreferredSize(new Dimension(70, 40));
        score.addPropertyChangeListener(this);
    }

    /*** Méthodes ***/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(new Color(128, 0, 128));
        g2d.setFont(new Font("Calibri", Font.BOLD, 11));
        FontMetrics fm = g2d.getFontMetrics();
        int stringWidth = fm.stringWidth("Score: " + score.getScore());
        int x = (this.getWidth() - stringWidth) / 2;
        g2d.drawString("Score : " + score.getScore(), 5, 12);

        g2d.setColor(new Color(128, 0, 128));
        g2d.setFont(new Font("Calibri", Font.BOLD, 10));
        fm = g2d.getFontMetrics();
        String lignesString = "Lignes : " + lignesSupprimees;
        stringWidth = fm.stringWidth(lignesString);
        x = (this.getWidth() - stringWidth) / 2;
        g2d.drawString(lignesString, 5, 30);

        g2d.dispose();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Score.MODIFICATION_SCORE)) {
            lignesSupprimees = puits.getTas().getTotalLignesSupprimees();
            repaint();
        }
    }
}