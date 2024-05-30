package fr.eseo.e3.poo.projet.blox.controleur;

import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Classe responsable de la simulation de la gravité pour les pièces dans le jeu **/

public class Gravite implements ActionListener {

    /*** Attributs ***/
    private Timer timer;
    private final VuePuits vuePuits;
    private final Puits puits;

    /*** Constructeurs ***/
    public Gravite(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
        this.puits = vuePuits.getPuits();
        int periodicite = 1000; // Périodicité initiale du timer en millisecondes
        this.timer = new Timer(periodicite, this);
        this.timer.start();
    }

    /*** Méthodes ***/
    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            puits.gravite();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        vuePuits.repaint(); // Rafraîchit la VuePuits après l'application de la gravité
    }

    public void demarrer() {
        timer.start();
    }

    public void arreter() {
        timer.stop();
    }

    /*** Getters et Setters ***/
    public int getPeriodicite() {
        return timer.getDelay();
    }

    public void setPeriodicite(int periodicite) {
        this.timer.setDelay(periodicite);
    }
}