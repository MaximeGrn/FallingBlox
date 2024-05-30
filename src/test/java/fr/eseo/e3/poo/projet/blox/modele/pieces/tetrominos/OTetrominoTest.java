package fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OTetrominoTest {

    /*** Test des Constructeurs ***/
    @Test
    void testConstructeur() {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.JAUNE;
        OTetromino oTetromino = new OTetromino(coordonnees, couleur);

        assertEquals(coordonnees, oTetromino.getElements()[0].getCoordonnees());
        assertEquals(couleur, oTetromino.getElements()[0].getCouleur());
    }

    /*** Test des Getters et Setters ***/
    @Test
    void testGetElements() {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.JAUNE;
        OTetromino oTetromino = new OTetromino(coordonnees, couleur);
        Element[] elementsAttendus = {
                new Element(2, 3, couleur),
                new Element(3, 3, couleur),
                new Element(2, 2, couleur),
                new Element(3, 2, couleur)
        };

        assertArrayEquals(elementsAttendus, oTetromino.getElements());
    }

    @Test
    void testSetElements() {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.JAUNE;
        OTetromino oTetromino = new OTetromino(coordonnees, couleur);

        Coordonnees nouvellesCoordonnees = new Coordonnees(5, 6);
        Couleur nouvelleCouleur = Couleur.ROUGE;
        oTetromino.setElements(nouvellesCoordonnees, nouvelleCouleur);

        Element[] elementsAttendus = {
                new Element(5, 6, nouvelleCouleur),
                new Element(6, 6, nouvelleCouleur),
                new Element(5, 5, nouvelleCouleur),
                new Element(6, 5, nouvelleCouleur)
        };
        assertArrayEquals(elementsAttendus, oTetromino.getElements());
    }

    @Test
    void testSetPosition() {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.JAUNE;
        OTetromino oTetromino = new OTetromino(coordonnees, couleur);
        oTetromino.setPosition(5, 6);

        Element[] elementsAttendus = {
                new Element(5, 6, couleur),
                new Element(6, 6, couleur),
                new Element(5, 5, couleur),
                new Element(6, 5, couleur)
        };
        assertArrayEquals(elementsAttendus, oTetromino.getElements());
    }

    @Test
    void testSetPuits() {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.JAUNE;
        OTetromino oTetromino = new OTetromino(coordonnees, couleur);

        Puits puits = new Puits();
        oTetromino.setPuits(puits);

        assertEquals(puits, oTetromino.getPuits());
    }

    /*** Test des méthodes ***/
    @Test
    void testTourner() {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.JAUNE;
        OTetromino oTetromino = new OTetromino(coordonnees, couleur);

        oTetromino.tourner(true);
        assertArrayEquals(new Element[]{
                new Element(2, 3, couleur),
                new Element(3, 3, couleur),
                new Element(2, 2, couleur),
                new Element(3, 2, couleur)
        }, oTetromino.getElements());

        oTetromino.tourner(false);
        assertArrayEquals(new Element[]{
                new Element(2, 3, couleur),
                new Element(3, 3, couleur),
                new Element(2, 2, couleur),
                new Element(3, 2, couleur)
        }, oTetromino.getElements());
    }

    @Test
    void testDeplacerDe() throws BloxException {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.JAUNE;
        OTetromino oTetromino = new OTetromino(coordonnees, couleur);

        Puits puits = new Puits(10, 20);
        oTetromino.setPuits(puits);

        // Déplacement vers la droite (valide)
        oTetromino.deplacerDe(1, 0);
        assertArrayEquals(new Element[]{
                new Element(3, 3, couleur),
                new Element(4, 3, couleur),
                new Element(3, 2, couleur),
                new Element(4, 2, couleur)
        }, oTetromino.getElements());

        // Déplacement vers le bas (valide)
        oTetromino.deplacerDe(0, 1);
        assertArrayEquals(new Element[]{
                new Element(3, 4, couleur),
                new Element(4, 4, couleur),
                new Element(3, 3, couleur),
                new Element(4, 3, couleur)
        }, oTetromino.getElements());

        // Déplacement vers la gauche (valide)
        oTetromino.deplacerDe(-1, 0);
        assertArrayEquals(new Element[]{
                new Element(2, 4, couleur),
                new Element(3, 4, couleur),
                new Element(2, 3, couleur),
                new Element(3, 3, couleur)
        }, oTetromino.getElements());

        // Déplacement vers le haut (invalide)
        assertThrows(IllegalArgumentException.class, () -> oTetromino.deplacerDe(0, -1));

        // Déplacement de 2 cases vers la droite (invalide)
        assertThrows(IllegalArgumentException.class, () -> oTetromino.deplacerDe(2, 0));

        // Déplacement hors du puits à droite (BloxException)
        assertThrows(BloxException.class, () -> {
            for (int i = 0; i < 8; i++) {
                oTetromino.deplacerDe(1, 0);
            }
        });
    }

    /*** Test des méthodes toString ***/
    @Test
    void testToString() {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.JAUNE;
        OTetromino oTetromino = new OTetromino(coordonnees, couleur);
        String expected = "OTetromino :\n" +
                "\t(2, 3) - JAUNE\n" +
                "\t(3, 3) - JAUNE\n" +
                "\t(2, 2) - JAUNE\n" +
                "\t(3, 2) - JAUNE\n";
        assertEquals(expected, oTetromino.toString());
    }
}