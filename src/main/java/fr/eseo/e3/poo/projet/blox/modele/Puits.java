package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.controleur.Gravite;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.vue.GameOver;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Puits {

    /*** Attributs ***/
    public static final String MODIFICATION_PIECE_ACTUELLE = "pieceActuelle";
    public static final String MODIFICATION_PIECE_SUIVANTE = "pieceSuivante";
    public static final int LARGEUR_PAR_DEFAUT = 10;
    public static final int PROFONDEUR_PAR_DEFAUT = 20;
    private static final int LARGEUR_MIN = 5;
    private static final int LARGEUR_MAX = 15;
    private static final int PROFONDEUR_MIN = 15;
    private static final int PROFONDEUR_MAX = 25;
    private Tas tas;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private Piece pieceActuelle = null;
    private Piece pieceSuivante = null;
    private int largeur;
    private int profondeur;
    private Score score;
    private Level level;
    private boolean gameOverAffiche = false;

    /*** Constructeurs ***/
    public Puits(int largeur, int profondeur, int elementsAjouter, int nbrLigneTas) {
        if (largeur > LARGEUR_MAX || largeur < LARGEUR_MIN) {
            throw new IllegalArgumentException("Largeur hors des limites");
        }
        if (profondeur > PROFONDEUR_MAX || profondeur < PROFONDEUR_MIN) {
            throw new IllegalArgumentException("Profondeur hors des limites");
        }
        this.largeur = largeur;
        this.profondeur = profondeur;
        this.pcs = new PropertyChangeSupport(this);
        this.tas = new Tas(this, elementsAjouter, nbrLigneTas);
        this.score = new Score();
        this.level = new Level();
    }

    public Puits() {
        this.largeur = LARGEUR_PAR_DEFAUT;
        this.profondeur = PROFONDEUR_PAR_DEFAUT;
        this.pcs = new PropertyChangeSupport(this);
        this.tas = new Tas(this);
        this.score = new Score();
        this.level = new Level();
    }

    public Puits(Puits puits) {
        this(puits.largeur, puits.profondeur);
        this.tas = new Tas(this, puits.tas.getElements().size());
        this.score = new Score();
        this.level = new Level();
    }

    public Puits(int largeur, int profondeur) {
        if (largeur > LARGEUR_MAX || largeur < LARGEUR_MIN) {
            throw new IllegalArgumentException("Largeur hors des limites");
        }
        if (profondeur > PROFONDEUR_MAX || profondeur < PROFONDEUR_MIN) {
            throw new IllegalArgumentException("Profondeur hors des limites");
        }
        this.largeur = largeur;
        this.profondeur = profondeur;
        this.pcs = new PropertyChangeSupport(this);
        this.tas = new Tas(this);
        this.score = new Score();
        this.level = new Level();
    }

    /*** Méthodes ***/
    public void gravite() throws BloxException {
        if (pieceActuelle != null) {
            try {
                pieceActuelle.deplacerDe(0, 1);
            } catch (BloxException e) {
                if (e.getType() == BloxException.BLOX_COLLISION) {
                    gererCollision();
                } else {
                    throw e;
                }
            } catch (Exception e) {
                throw e;
            }
        }
        verifierDefaite();
    }

    private void gererCollision() {
        if (pieceActuelle == null || pieceActuelle.getElements() == null) {
            System.err.println("Erreur : La pièce actuelle ou ses éléments sont null.");
            return;
        }
        tas.ajouterElements(pieceActuelle);
        score.ajouterScore(tas.getLignesSupprimees());
        level.setScore(score.getScore());

        pieceActuelle = pieceSuivante;
        if (pieceActuelle != null) {
            pieceActuelle.setPuits(this);
            pieceActuelle.setPosition(this.largeur / 2, -4);
            pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE, null, pieceActuelle);
        } else {
            System.err.println("Erreur : La nouvelle pièce actuelle est null.");
        }

        pieceSuivante = UsineDePiece.genererTetromino();
        if (pieceSuivante != null) {
            pieceSuivante.setPuits(this);
            pcs.firePropertyChange(MODIFICATION_PIECE_SUIVANTE, null, pieceSuivante);
        } else {
            System.err.println("Erreur : La nouvelle pièce suivante est null.");
        }
    }

    private boolean verifierDefaite() {
        if (!gameOverAffiche) {
            for (Element element : tas.getElements()) {
                if (element.getCoordonnees().getOrdonnee() < 0) {
                    for (PropertyChangeListener listener : pcs.getPropertyChangeListeners()) {
                        if (listener instanceof VuePuits) {
                            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor((VuePuits) listener);
                            GameOver gameOverPopup = new GameOver(topFrame, true);
                            gameOverPopup.setVisible(true);

                            for (ActionListener listener2 : topFrame.getListeners(ActionListener.class)) {
                                if (listener2 instanceof Gravite) {
                                    ((Gravite) listener2).arreter();
                                    break;
                                }
                            }
                            gameOverAffiche = true;
                            break;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /*** Méthodes toString ***/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Puits : Dimension ").append(largeur).append(" x ").append(profondeur).append("\n");

        sb.append("Piece Actuelle : ");
        if (pieceActuelle == null) {
            sb.append("<aucune>\n");
        } else {
            sb.append(pieceActuelleToString(pieceActuelle));
        }

        sb.append("Piece Suivante : ");
        if (pieceSuivante == null) {
            sb.append("<aucune>\n");
        } else {
            sb.append(pieceSuivanteToString(pieceSuivante));
        }
        return sb.toString();
    }

    private String pieceActuelleToString(Piece piece) {
        StringBuilder sb = new StringBuilder();
        sb.append(piece.getClass().getSimpleName()).append(" :\n");
        for (Element element : piece.getElements()) {
            sb.append("\t").append(element.toString()).append("\n");
        }
        return sb.toString();
    }

    private String pieceSuivanteToString(Piece piece) {
        StringBuilder sb = new StringBuilder();
        sb.append(piece.getClass().getSimpleName()).append(" :\n");
        for (Element element : piece.getElements()) {
            sb.append("\t").append(element.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj){
        if (((Puits)obj).getPieceActuelle() == null && ((Puits)obj).getPieceSuivante() == null){
            return  ((Puits)obj).getLargeur() == largeur && ((Puits)obj).getProfondeur() == profondeur;
        }else if (((Puits)obj).getPieceActuelle() == null){
            return  ((Puits)obj).getPieceSuivante().equals(pieceSuivante) &&
                    ((Puits)obj).getLargeur() == largeur && ((Puits)obj).getProfondeur() == profondeur;
        }else{
            return ((Puits)obj).getPieceActuelle().equals(pieceActuelle) &&
                    ((Puits)obj).getPieceSuivante().equals(pieceSuivante) &&
                    ((Puits)obj).getLargeur() == largeur && ((Puits)obj).getProfondeur() == profondeur;
        }
    }

    /*** Getters et Setters ***/
    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int nouvelleLargeur) {
        if (nouvelleLargeur < LARGEUR_MIN || nouvelleLargeur > LARGEUR_MAX) {
            throw new IllegalArgumentException("La largeur doit être comprise entre " + LARGEUR_MIN + " et " + LARGEUR_MAX);
        }
        this.largeur = nouvelleLargeur;
    }

    public int getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(int profondeur) {
        if (profondeur < PROFONDEUR_MIN || profondeur > PROFONDEUR_MAX) {
            throw new IllegalArgumentException("La profondeur doit être comprise entre " + PROFONDEUR_MIN + " et " + PROFONDEUR_MAX);
        }
        this.profondeur = profondeur;
    }

    public Tas getTas() {
        return tas;
    }

    public void setTas(Tas tas) {
        this.tas = tas;
    }

    public Piece getPieceActuelle() {
        return pieceActuelle;
    }

    public void setPieceActuelle(Piece pieceActuelle) {
        Piece anciennePieceActuelle = this.pieceActuelle;
        this.pieceActuelle = pieceActuelle;
        if (this.pieceActuelle != null) {
            this.pieceActuelle.setPuits(this);
        }
        pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE, anciennePieceActuelle, pieceActuelle);
    }

    public Piece getPieceSuivante() {
        return pieceSuivante;
    }

    public void setPieceSuivante(Piece piece) {
        Piece oldPieceActuelle = this.pieceActuelle;

        if (this.pieceSuivante != null) {
            this.pieceActuelle = this.pieceSuivante;
            this.pieceActuelle.setPosition(this.largeur / 2, -4);
            this.pieceActuelle.setPuits(this);
        }
        this.pieceSuivante = piece;
        if (this.pieceSuivante != null) {
            this.pieceSuivante.setPuits(this);
        }
        pcs.firePropertyChange(MODIFICATION_PIECE_ACTUELLE, oldPieceActuelle, this.pieceActuelle);
        pcs.firePropertyChange(MODIFICATION_PIECE_SUIVANTE, null, this.pieceSuivante);
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}