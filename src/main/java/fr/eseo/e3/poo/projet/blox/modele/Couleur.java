package fr.eseo.e3.poo.projet.blox.modele;

import java.awt.Color;

public enum Couleur {

    /*** Attributs ***/
    ROUGE(Color.RED),
    ORANGE(Color.ORANGE),
    BLEU(Color.BLUE),
    VERT(Color.GREEN),
    JAUNE(Color.YELLOW),
    CYAN(Color.CYAN),
    VIOLET(Color.MAGENTA);

    private final Color couleurPourAffichage;

    /*** Constructeurs ***/
    private Couleur(Color couleurPourAffichage) {
        this.couleurPourAffichage = couleurPourAffichage;
    }

    /*** Getters et Setters ***/
    public Color getCouleurPourAffichage() {
        return couleurPourAffichage;
    }
}