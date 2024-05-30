package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.controleur.PieceDeplacement;
import fr.eseo.e3.poo.projet.blox.controleur.PieceRotation;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** Classe responsable de l'affichage graphique du Puits **/
public class VuePuits extends JPanel implements PropertyChangeListener, FocusListener {

    /*** Attributs ***/
    public static final int TAILLE_PAR_DEFAUT = 15;
    private PieceDeplacement pieceDeplacement;
    private Puits puits;
    private int taille;
    private VuePiece vuePiece;
    private final VueTas vueTas;

    /*** Constructeurs ***/
    public VuePuits(Puits nouveauPuits) {
        this(nouveauPuits, TAILLE_PAR_DEFAUT);
    }

    public VuePuits(Puits nouveauPuits, int taille) {
        setPuits(nouveauPuits, taille);
        this.vueTas = new VueTas(this);
    }

    /*** Méthodes ***/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g.create();
        fillBackground(g2D);
        dessinerGrille(g2D);
        if (vuePiece != null) {
            vuePiece.afficherPiece(g2D);
        }
        if (vueTas != null) {
            vueTas.afficher(g2D);
        }
        g2D.dispose();
    }

    private void fillBackground(Graphics2D g2D) {
        g2D.setColor(Color.WHITE);
        g2D.fillRect(0, 0, taille * puits.getLargeur(), taille * puits.getProfondeur());
    }

    public void dessinerGrille(Graphics2D g2D) {
        g2D.setColor(Color.LIGHT_GRAY);
        for (int x = 0; x < puits.getLargeur(); x++) {
            for (int y = 0; y < puits.getProfondeur(); y++) {
                g2D.drawRect(x * taille, y * taille, taille, taille);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Puits.MODIFICATION_PIECE_ACTUELLE)) {
            if (evt.getNewValue() != null) {
                setVuePiece(new VuePiece((Piece) evt.getNewValue(), taille));
            }
        }
        repaint();
    }

    @Override
    public void focusGained(FocusEvent e) {
        // Rien à faire ici
    }

    @Override
    public void focusLost(FocusEvent e) {
        // Rien à faire ici
    }

    /*** Getters et Setters ***/
    public Puits getPuits() {
        return puits;
    }

    public void setPuits(Puits nouveauPuits) {
        setPuits(nouveauPuits, TAILLE_PAR_DEFAUT);
    }

    public void setPuits(Puits nouveauPuits, int taille) {
        if (this.puits != null) {
            this.puits.removePropertyChangeListener(this);
        }

        this.puits = nouveauPuits;
        this.taille = taille;

        if (nouveauPuits != null) {
            nouveauPuits.addPropertyChangeListener(this);
            setPreferredSize(new Dimension(nouveauPuits.getLargeur() * taille, nouveauPuits.getProfondeur() * taille));
            if (pieceDeplacement == null) {
                pieceDeplacement = new PieceDeplacement(this);
                addMouseMotionListener(pieceDeplacement);
                addMouseWheelListener(pieceDeplacement);
                addMouseListener(pieceDeplacement);
            } else {
                pieceDeplacement.setPuits(nouveauPuits);
            }

            // Supprimer l'ancienne instance de PieceRotation comme MouseListener
            for (MouseListener listener : getMouseListeners()) {
                if (listener instanceof PieceRotation) {
                    removeMouseListener(listener);
                    break;
                }
            }

            // Enregistrer une nouvelle instance de PieceRotation comme MouseListener
            PieceRotation pieceRotation = new PieceRotation(this);
            addMouseListener(pieceRotation);
        } else {
            setPreferredSize(new Dimension(0, 0));
            if (pieceDeplacement != null) {
                removeMouseMotionListener(pieceDeplacement);
                removeMouseWheelListener(pieceDeplacement);
                removeMouseListener(pieceDeplacement);
            }
            // Supprimer l'instance de PieceRotation comme MouseListener
            for (MouseListener listener : getMouseListeners()) {
                if (listener instanceof PieceRotation) {
                    removeMouseListener(listener);
                    break;
                }
            }
        }
        vuePiece = null;
        repaint();
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
        this.setPreferredSize(new Dimension(puits.getLargeur() * taille, puits.getProfondeur() * taille));
        repaint();
    }

    public VuePiece getVuePiece() {
        return vuePiece;
    }

    private void setVuePiece(VuePiece vuePiece) {
        this.vuePiece = vuePiece;
        repaint();
    }
}