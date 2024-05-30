package fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.Test;
import java.awt.Color;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** Classe de test pour la classe Couleur **/
public class CouleurTest {

    @Test
    public void testCouleurRouge() {
        assertEquals(Color.RED, Couleur.ROUGE.getCouleurPourAffichage());
    }

    @Test
    public void testCouleurOrange() {
        assertEquals(Color.ORANGE, Couleur.ORANGE.getCouleurPourAffichage());
    }

    @Test
    public void testCouleurBleu() {
        assertEquals(Color.BLUE, Couleur.BLEU.getCouleurPourAffichage());
    }

    @Test
    public void testCouleurVert() {
        assertEquals(Color.GREEN, Couleur.VERT.getCouleurPourAffichage());
    }

    @Test
    public void testCouleurJaune() {
        assertEquals(Color.YELLOW, Couleur.JAUNE.getCouleurPourAffichage());
    }

    @Test
    public void testCouleurCyan() {
        assertEquals(Color.CYAN, Couleur.CYAN.getCouleurPourAffichage());
    }

    @Test
    public void testCouleurViolet() {
        assertEquals(Color.MAGENTA, Couleur.VIOLET.getCouleurPourAffichage());
    }
}
