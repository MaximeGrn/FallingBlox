package fr.eseo.e3.poo.projet.blox.controleur;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

public class ControleurClavier implements KeyListener {

    /*** Attributs ***/
    private Puits puits;
    private VuePuits vuePuits;

    /*** Constructeurs ***/
    public ControleurClavier(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
        this.puits = vuePuits.getPuits();
    }

    /*** Méthodes ***/
    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilisé
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (puits.getPieceActuelle() != null) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT: // Déplace la pièce vers la gauche
                    try {
                        puits.getPieceActuelle().deplacerDe(-1, 0);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case KeyEvent.VK_RIGHT: // Déplace la pièce vers la droite
                    try {
                        puits.getPieceActuelle().deplacerDe(1, 0);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case KeyEvent.VK_DOWN: // Accélère la chute de la pièce
                    try {
                        puits.getPieceActuelle().deplacerDe(0, 1);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case KeyEvent.VK_SPACE: // Permet de faire tourner la pièce
                    try {
                        puits.getPieceActuelle().tourner(false);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case KeyEvent.VK_UP: // Envoie directement la pièce sur le tas
                    try {
                        while (true) {
                            puits.getPieceActuelle().deplacerDe(0, 1);
                        }
                    } catch (BloxException ex) {
                        // La pièce est arrivée en bas
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                default:
                    break;
            }
            vuePuits.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Non utilisé
    }
}