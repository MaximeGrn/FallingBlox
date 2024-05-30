package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** Classe responsable de la rotation des pièces en fonction des clics de souris **/

public class PieceRotation extends MouseAdapter {

    /*** Attributs ***/
    private VuePuits vuePuits;
    private Puits puits;

    /*** Constructeurs ***/
    public PieceRotation(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
        this.puits = vuePuits.getPuits();
    }

    /*** Méthodes ***/
    @Override
    public void mouseClicked(MouseEvent event) {
        if (puits.getPieceActuelle() != null) {
            if (SwingUtilities.isLeftMouseButton(event)) {
                try {
                    puits.getPieceActuelle().tourner(false);
                } catch (BloxException e) {
                    throw new RuntimeException(e);
                }
            } else if (SwingUtilities.isRightMouseButton(event)) {
                try {
                    puits.getPieceActuelle().tourner(true);
                } catch (BloxException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}