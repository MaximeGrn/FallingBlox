package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.controleur.Gravite;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** Panneau d'information affichant la pièce suivante et d'autres informations dans le jeu **/
public class PanneauInformation extends JPanel implements PropertyChangeListener {

    /*** Attributs ***/
    private JPanel panneauPieceSuivante;
    private VuePiece vuePieceSuivante;
    private VueScore vueScore;
    private VueLevel vueLevel;

    /*** Constructeurs ***/
    public PanneauInformation(Puits puits, Gravite gravite) {
        // Utilisation d'un GridLayout pour organiser les composants
        this.setLayout(new GridLayout(4, 1));
        this.setPreferredSize(new Dimension(70, 70));
        puits.addPropertyChangeListener(this);
        // Création du panneau pour la pièce suivante
        this.panneauPieceSuivante = new JPanel() {
            private final int taille = 10; // Initialisation de taille

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (vuePieceSuivante != null) {
                    Graphics2D g2d = (Graphics2D) g.create();

                    // Calculer le décalage pour centrer la pièce
                    int largeurPiecePixels = vuePieceSuivante.getLargeurPixels();
                    int decalageX = (getWidth() - largeurPiecePixels) / 2;

                    // Ajuster le décalage pour les pièces avec une largeur paire
                    if (largeurPiecePixels % (2 * taille) == 0) {
                        decalageX = decalageX + (taille / 2); // Décaler d'une demi-taille d'élément
                        g2d.translate(decalageX / 2, 0);
                    } else {
                        g2d.translate(decalageX / 2, 0);
                    }

                    vuePieceSuivante.afficherPiece(g2d);
                    g2d.dispose();
                }
            }
        };
        this.vuePieceSuivante = new VuePiece(puits.getPieceSuivante(), 10);
        this.add(panneauPieceSuivante);
        this.vueScore = new VueScore(puits);
        this.add(vueScore);
        this.vueLevel = new VueLevel(puits, gravite);
        this.add(vueLevel);
    }

    public PanneauInformation(Puits puits) { // Permet juste de valider l'Assignment Center car il faut un constructeur avec un seul paramètre
        this(puits, new Gravite(new VuePuits(puits)));
    }

    /*** Méthodes ***/
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Puits.MODIFICATION_PIECE_SUIVANTE)) {
            if (evt.getNewValue() != null) {
                vuePieceSuivante = new VuePiece((Piece) evt.getNewValue(), 10);
            } else {
                vuePieceSuivante = null;
            }
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (vuePieceSuivante != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            vuePieceSuivante.afficherPiece(g2d);
            g2d.dispose();
        }
    }
}