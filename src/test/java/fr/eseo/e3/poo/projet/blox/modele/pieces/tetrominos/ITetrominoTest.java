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

public class ITetrominoTest {

    /*** Test des Constructeurs ***/
    @Test
    void testConstructeur() {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.CYAN;
        ITetromino iTetromino = new ITetromino(coordonnees, couleur);

        assertEquals(coordonnees, iTetromino.getElements()[0].getCoordonnees());
        assertEquals(couleur, iTetromino.getElements()[0].getCouleur());
    }

    /*** Test des Getters et Setters ***/
    @Test
    void testGetElements() {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.CYAN;
        ITetromino iTetromino = new ITetromino(coordonnees, couleur);
        Element[] elementsAttendus = {
                new Element(2, 3, couleur),
                new Element(2, 2, couleur),
                new Element(2, 1, couleur),
                new Element(2, 4, couleur)
        };

        assertArrayEquals(elementsAttendus, iTetromino.getElements());
    }

    @Test
    void testSetElements() {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.CYAN;
        ITetromino iTetromino = new ITetromino(coordonnees, couleur);

        Coordonnees nouvellesCoordonnees = new Coordonnees(5, 6);
        Couleur nouvelleCouleur = Couleur.ROUGE;
        iTetromino.setElements(nouvellesCoordonnees, nouvelleCouleur);

        Element[] elementsAttendus = {
                new Element(5, 6, nouvelleCouleur),
                new Element(5, 5, nouvelleCouleur),
                new Element(5, 4, nouvelleCouleur),
                new Element(5, 7, nouvelleCouleur)
        };
        assertArrayEquals(elementsAttendus, iTetromino.getElements());
    }

    @Test
    void testSetPosition() {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.CYAN;
        ITetromino iTetromino = new ITetromino(coordonnees, couleur);
        iTetromino.setPosition(5, 6);

        Element[] elementsAttendus = {
                new Element(5, 6, couleur),
                new Element(5, 5, couleur),
                new Element(5, 4, couleur),
                new Element(5, 7, couleur)
        };
        assertArrayEquals(elementsAttendus, iTetromino.getElements());
    }

    @Test
    void testSetPuits() {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.CYAN;
        ITetromino iTetromino = new ITetromino(coordonnees, couleur);

        Puits puits = new Puits();
        iTetromino.setPuits(puits);

        assertEquals(puits, iTetromino.getPuits());
    }

    /*** Test des méthodes ***/
    @Test
    void testTournerHoraire() {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.CYAN;
        ITetromino iTetromino = new ITetromino(coordonnees, couleur);

        try {
            iTetromino.tourner(true);
        } catch (BloxException e) {
            throw new RuntimeException(e);
        }
        assertArrayEquals(new Element[]{
                new Element(2, 3, couleur),
                new Element(3, 3, couleur),
                new Element(4, 3, couleur),
                new Element(1, 3, couleur)
        }, iTetromino.getElements());
    }

    @Test
    void testTournerAntiHoraire() {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.CYAN;
        ITetromino iTetromino = new ITetromino(coordonnees, couleur);

        try {
            iTetromino.tourner(false);
        } catch (BloxException e) {
            throw new RuntimeException(e);
        }
        assertArrayEquals(new Element[]{
                new Element(2, 3, couleur),
                new Element(1, 3, couleur),
                new Element(0, 3, couleur),
                new Element(3, 3, couleur)
        }, iTetromino.getElements());
    }

    @Test
    void testDeplacerDe() throws BloxException {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.CYAN;
        ITetromino iTetromino = new ITetromino(coordonnees, couleur);

        Puits puits = new Puits(10, 20);
        iTetromino.setPuits(puits);

        // Déplacement vers la droite (valide)
        iTetromino.deplacerDe(1, 0);
        assertArrayEquals(new Element[]{
                new Element(3, 3, couleur),
                new Element(3, 2, couleur),
                new Element(3, 1, couleur),
                new Element(3, 4, couleur)
        }, iTetromino.getElements());

        // Déplacement vers le bas (valide)
        iTetromino.deplacerDe(0, 1);
        assertArrayEquals(new Element[]{
                new Element(3, 4, couleur),
                new Element(3, 3, couleur),
                new Element(3, 2, couleur),
                new Element(3, 5, couleur)
        }, iTetromino.getElements());

        // Déplacement vers la gauche (valide)
        iTetromino.deplacerDe(-1, 0);
        assertArrayEquals(new Element[]{
                new Element(2, 4, couleur),
                new Element(2, 3, couleur),
                new Element(2, 2, couleur),
                new Element(2, 2, couleur)
        }, iTetromino.getElements());

        // Déplacement vers le haut (invalide)
        assertThrows(IllegalArgumentException.class, () -> iTetromino.deplacerDe(0, -1));

        // Déplacement de 2 cases vers la droite (invalide)
        assertThrows(IllegalArgumentException.class, () -> iTetromino.deplacerDe(2, 0));

        // Déplacement hors du puits à droite (BloxException)
        assertThrows(BloxException.class, () -> {
            for (int i = 0; i < 8; i++) {
                iTetromino.deplacerDe(1, 0);
            }
        });

        // Déplacement hors du puits à gauche (BloxException) - Après la rotation
        try {
            iTetromino.tourner(true);
        } catch (BloxException e) {
            throw new RuntimeException(e);
        }
        assertThrows(BloxException.class, () -> iTetromino.deplacerDe(-3, 0));
    }

    @Test
    void testCollisionAvecTasRotation() throws BloxException {
        Puits puits = new Puits(10, 20);
        ITetromino iTetromino = new ITetromino(new Coordonnees(5, 18), Couleur.CYAN);
        iTetromino.setPuits(puits);

        puits.getTas().ajouterElements(new OTetromino(new Coordonnees(4, 19), Couleur.ROUGE));

        assertThrows(BloxException.class, () -> iTetromino.tourner(true));
    }

    @Test
    void testCollisionAvecFondRotation() throws BloxException {
        Puits puits = new Puits(10, 20);
        ITetromino iTetromino = new ITetromino(new Coordonnees(5, 19), Couleur.CYAN);
        iTetromino.setPuits(puits);

        assertThrows(BloxException.class, () -> iTetromino.tourner(true));
    }

    /*** Test des méthodes toString ***/
    @Test
    void testToString() {
        Coordonnees coordonnees = new Coordonnees(2, 3);
        Couleur couleur = Couleur.CYAN;
        ITetromino iTetromino = new ITetromino(coordonnees, couleur);
        String expected = "ITetromino :\n" +
                "\t(2, 3) - CYAN\n" +
                "\t(2, 2) - CYAN\n" +
                "\t(2, 1) - CYAN\n" +
                "\t(2, 4) - CYAN\n";
        assertEquals(expected, iTetromino.toString());
    }
}