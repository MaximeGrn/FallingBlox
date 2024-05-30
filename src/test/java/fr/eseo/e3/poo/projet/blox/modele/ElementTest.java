package fr.eseo.e3.poo.projet.blox.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*** Classe de test pour la classe Element ***/

public class ElementTest {

    /*** Test des Constructeurs ***/
    @Test
    public void testConstructeurAvecCoordonneesEtCouleurParDefaut() {
        Coordonnees coordonnees = new Coordonnees(2, 5);
        Element element = new Element(coordonnees);
        assertEquals(coordonnees, element.getCoordonnees());
        assertEquals(Couleur.ROUGE, element.getCouleur());
    }

    @Test
    public void testConstructeurAvecAbscisseOrdonneeEtCouleurParDefaut() {
        Element element = new Element(2, 5);
        assertEquals(2, element.getCoordonnees().getAbscisse());
        assertEquals(5, element.getCoordonnees().getOrdonnee());
        assertEquals(Couleur.ROUGE, element.getCouleur());
    }

    @Test
    public void testConstructeurAvecCoordonneesEtCouleur() {
        Coordonnees coordonnees = new Coordonnees(2, 5);
        Couleur couleur = Couleur.BLEU;
        Element element = new Element(coordonnees, couleur);
        assertEquals(coordonnees, element.getCoordonnees());
        assertEquals(couleur, element.getCouleur());
    }

    @Test
    public void testConstructeurAvecAbscisseOrdonneeEtCouleur() {
        int abscisse = 2;
        int ordonnee = 5;
        Couleur couleur = Couleur.VERT;
        Element element = new Element(abscisse, ordonnee, couleur);
        assertEquals(abscisse, element.getCoordonnees().getAbscisse());
        assertEquals(ordonnee, element.getCoordonnees().getOrdonnee());
        assertEquals(couleur, element.getCouleur());
    }

    /*** Test des Getters et Setters ***/
    @Test
    public void testSetters() {
        Element element = new Element(0, 0, Couleur.JAUNE);
        Coordonnees nouvellesCoordonnees = new Coordonnees(8, 12);
        Couleur nouvelleCouleur = Couleur.VIOLET;
        element.setCoordonnees(nouvellesCoordonnees);
        element.setCouleur(nouvelleCouleur);
        assertEquals(nouvellesCoordonnees, element.getCoordonnees());
        assertEquals(nouvelleCouleur, element.getCouleur());
    }

    /*** Test des méthodes ***/
    @Test
    public void testDeplacerDe() {
        Element element = new Element(2, 3, Couleur.ROUGE);
        element.deplacerDe(1, 2);
        assertEquals(3, element.getCoordonnees().getAbscisse());
        assertEquals(5, element.getCoordonnees().getOrdonnee());
    }

    @Test
    public void testEquals() {
        Element e1 = new Element(1, 3, Couleur.ORANGE);
        Element e2 = new Element(1, 3, Couleur.ORANGE);
        Element e3 = new Element(4, 6, Couleur.BLEU);
        Element e4 = new Element(1, 3, Couleur.VERT);
        Object obj = new Object();

        assertTrue(e1.equals(e2));
        assertFalse(e1.equals(e3));
        assertFalse(e1.equals(null));
        assertFalse(e1.equals(obj));
        assertTrue(e1.equals(e1));
        assertFalse(e1.equals(e4));
    }

    @Test
    public void testHashCode() {
        Element e1 = new Element(1, 3, Couleur.ORANGE);
        Element e2 = new Element(1, 3, Couleur.ORANGE);
        Element e3 = new Element(4, 6, Couleur.BLEU);

        assertEquals(e1.hashCode(), e2.hashCode());
        assertNotEquals(e1.hashCode(), e3.hashCode());
    }

    /*** Test des méthodes toString ***/
    @Test
    public void testToString() {
        Element element = new Element(3, 8, Couleur.CYAN);
        assertEquals("(3, 8) - CYAN", element.toString());
    }
}