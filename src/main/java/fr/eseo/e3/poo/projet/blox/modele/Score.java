package fr.eseo.e3.poo.projet.blox.modele;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Score {

    /*** Attributs ***/
    public static final String MODIFICATION_SCORE = "score";
    private int score;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /*** Constructeurs ***/
    public Score() {
        this.score = 0;
    }

    /*** Méthodes ***/
    public void ajouterScore(int lignesCompletes) {
        int ancienScore = this.score;
        switch (lignesCompletes) {
            case 1:
                this.score += 10;
                break;
            case 2:
                this.score += 25;
                break;
            case 3:
                this.score += 40;
                break;
            case 4:
                this.score += 60;
                break;
            default:
                break;
        }
        pcs.firePropertyChange(MODIFICATION_SCORE, ancienScore, this.score);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    /*** Getters et Setters ***/
    public int getScore() {
        return score;
    }
}