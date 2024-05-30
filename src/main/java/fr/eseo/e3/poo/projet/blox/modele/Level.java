package fr.eseo.e3.poo.projet.blox.modele;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Level {

    /*** Attributs ***/
    public static final String MODIFICATION_LEVEL = "level";
    private int level;
    private int score;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /*** Constructeurs ***/
    public Level() {
        this.level = 1; // Niveau initial
        this.score = 0;
    }

    /*** Méthodes ***/
    public void setScore(int score) {
        int ancienLevel = this.level;
        this.score = score;
        this.level = score / 100 + 1; // Calcul du niveau en fonction du score
        // Déclencher un événement si le niveau a changé
        if (ancienLevel != this.level) {
            pcs.firePropertyChange(MODIFICATION_LEVEL, ancienLevel, this.level);
        }
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

    public int getLevel() {
        return level;
    }
}