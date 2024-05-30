package fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*** Classe de test pour la classe Coordonnees ***/

public class CoordonneesTest {

    /*** Test des Constructeurs ***/
    @Test
    public void testConstructeur() {
        Coordonnees coordonnees = new Coordonnees(5, 10);
        assertEquals(5, coordonnees.getAbscisse());
        assertEquals(10, coordonnees.getOrdonnee());
    }

    /*** Test des Getters et Setters ***/
    @Test
    public void testGettersEtSetters() {
        Coordonnees coordonnees = new Coordonnees(0, 0);
        coordonnees.setAbscisse(8);
        coordonnees.setOrdonnee(15);
        assertEquals(8, coordonnees.getAbscisse());
        assertEquals(15, coordonnees.getOrdonnee());
    }

    /*** Test des méthodes ***/
    @Test
    public void testEquals() {
        Coordonnees c1 = new Coordonnees(2, 4);
        Coordonnees c2 = new Coordonnees(2, 4);
        Coordonnees c3 = new Coordonnees(6, 8);
        Coordonnees c4 = new Coordonnees(2, 5);
        Object obj = new Object();

        assertTrue(c1.equals(c2));
        assertFalse(c1.equals(c3));
        assertFalse(c1.equals(null));
        assertFalse(c1.equals(obj));
        assertTrue(c1.equals(c1));

        Coordonnees c5 = c1;
        assertTrue(c1.equals(c5));
        assertFalse(c1.equals(c4));
    }

    @Test
    public void testHashCode() {
        Coordonnees c1 = new Coordonnees(2, 4);
        Coordonnees c2 = new Coordonnees(2, 4);
        Coordonnees c3 = new Coordonnees(6, 8);

        assertEquals(c1.hashCode(), c2.hashCode());
        assertNotEquals(c1.hashCode(), c3.hashCode());
    }

    /*** Test des méthodes toString ***/
    @Test
    public void testToString() {
        Coordonnees coordonnees = new Coordonnees(3, 7);
        assertEquals("(3, 7)", coordonnees.toString());
    }
}