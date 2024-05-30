package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/** Classe responsable du déplacement des pièces en fonction des mouvements de la souris **/
public class PieceDeplacement implements MouseMotionListener, MouseWheelListener, MouseListener {

    /*** Attributs ***/
    private VuePuits vuePuits;
    private Puits puits;
    private int colonne;

    /*** Constructeurs ***/
    public PieceDeplacement(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
        this.puits = vuePuits.getPuits();
        this.colonne = 0;
    }

    /*** Méthodes ***/
    @Override
    public void mouseMoved(MouseEvent e) {
        if (puits.getPieceActuelle() != null) {
            if (colonne == 0) {
                colonne = e.getX() / vuePuits.getTaille();
            }
            int divColonne = colonne;
            if (divColonne > e.getX() / vuePuits.getTaille()) {
                colonne = e.getX() / vuePuits.getTaille();
                try {
                    vuePuits.getPuits().getPieceActuelle().deplacerDe(-1, 0);
                } catch (BloxException ex) {
                    throw new RuntimeException(ex);
                }
                vuePuits.repaint();

            } else if (divColonne < e.getX() / vuePuits.getTaille()) {
                colonne = e.getX() / vuePuits.getTaille();
                try {
                    vuePuits.getPuits().getPieceActuelle().deplacerDe(1, 0);
                } catch (BloxException ex) {
                    throw new RuntimeException(ex);
                }
                vuePuits.repaint();
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (puits.getPieceActuelle() != null && e.getWheelRotation() > 0) {
            try {
                vuePuits.getPuits().getPieceActuelle().deplacerDe(0, 1);
            } catch (BloxException ex) {
                throw new RuntimeException(ex);
            }
            vuePuits.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        colonne = 0;
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        // Non implémenté
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        // Non implémenté
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Non implémenté
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Non implémenté
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Non implémenté
    }

    /*** Getters et Setters ***/
    public void setPuits(Puits puits) {
        this.puits = puits;
    }
}