package fr.eseo.e3.poo.projet.blox.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Classe de test pour la classe Tas.
 */
public class TasTest {

    /**
     * Test le constructeur Tas(Puits).
     */
    @Test
    public void testConstructeurPuits() {
        Puits puits = new Puits();
        Tas tas = new Tas(puits);
        assertNotNull(tas, "Le tas ne doit pas être null.");
        assertSame(puits, tas.getPuits(), "Le puits associé au tas doit être le même que celui passé en argument.");
        assertTrue(tas.getElements().isEmpty(), "Le tas doit être vide après construction avec le constructeur par défaut.");
    }

    /**
     * Test le constructeur Tas(Puits, int).
     */
    @Test
    public void testConstructeurPuitsNbElements() {
        Puits puits = new Puits();
        int nbElements = 5;
        Tas tas = new Tas(puits, nbElements);
        assertNotNull(tas, "Le tas ne doit pas être null.");
        assertSame(puits, tas.getPuits(), "Le puits associé au tas doit être le même que celui passé en argument.");
        assertEquals(nbElements, tas.getElements().size(), "Le nombre d'éléments dans le tas doit être égal à nbElements.");
    }

    /**
     * Test le constructeur Tas(Puits, int, int).
     */
    @Test
    public void testConstructeurPuitsNbElementsNbLignes() {
        Puits puits = new Puits();
        int nbElements = 8;
        int nbLignes = 2;
        Tas tas = new Tas(puits, nbElements, nbLignes);
        assertNotNull(tas, "Le tas ne doit pas être null.");
        assertSame(puits, tas.getPuits(), "Le puits associé au tas doit être le même que celui passé en argument.");
        assertEquals(nbElements, tas.getElements().size(), "Le nombre d'éléments dans le tas doit être égal à nbElements.");
    }

    /**
     * Test le constructeur Tas(Puits, int, int, Random).
     */
    @Test
    public void testConstructeurPuitsNbElementsNbLignesRandom() {
        Puits puits = new Puits();
        int nbElements = 12;
        int nbLignes = 3;
        Random random = new Random(123); // Graine fixe pour reproductibilité
        Tas tas = new Tas(puits, nbElements, nbLignes, random);
        assertNotNull(tas, "Le tas ne doit pas être null.");
        assertSame(puits, tas.getPuits(), "Le puits associé au tas doit être le même que celui passé en argument.");
        assertEquals(nbElements, tas.getElements().size(), "Le nombre d'éléments dans le tas doit être égal à nbElements.");
    }

    /**
     * Test la méthode elementExists.
     */
    @Test
    public void testElementExists() {
        Puits puits = new Puits();
        Tas tas = new Tas(puits, 5);
        for (Element element : tas.getElements()) {
            assertTrue(tas.elementExists(element.getCoordonnees().getAbscisse(), element.getCoordonnees().getOrdonnee()),
                    "elementExists devrait retourner true pour un élément présent dans le tas.");
        }
        assertFalse(tas.elementExists(0, puits.getProfondeur() - 1),
                "elementExists devrait retourner false pour un élément non présent dans le tas.");
    }

    /**
     * Test la gestion d'une IllegalArgumentException si le nombre d'éléments est trop grand.
     */
    @Test
    public void testTropDElements() {
        Puits puits = new Puits(10, 20);
        assertThrows(IllegalArgumentException.class, () -> new Tas(puits, 201, 10),
                "Une IllegalArgumentException devrait être levée si le nombre d'éléments est trop grand.");
    }
}
