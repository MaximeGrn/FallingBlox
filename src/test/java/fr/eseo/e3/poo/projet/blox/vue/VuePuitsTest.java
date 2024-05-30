package fr.eseo.e3.poo.projet.blox.vue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.eseo.e3.poo.projet.blox.modele.Puits;

/**
 * Classe de test pour la classe VuePuits.
 */
public class VuePuitsTest {

    private Puits puits;
    private VuePuits vuePuits;

    /**
     * Initialise les objets puits et vuePuits avant chaque test.
     */
    @BeforeEach
    void setUp() {
        puits = new Puits();
        vuePuits = new VuePuits(puits);
    }

    /**
     * Test le constructeur avec un argument Puits.
     */
    @Test
    void testConstructeurAvecPuits() {
        assertNotNull(vuePuits, "La vue du puits ne doit pas être nulle");
        assertSame(puits, vuePuits.getPuits(), "Le puits associé à la vue doit être le même que celui passé en argument");
        assertEquals(VuePuits.TAILLE_PAR_DEFAUT, vuePuits.getTaille(), "La taille par défaut doit être utilisée");
    }

    /**
     * Test le constructeur avec deux arguments Puits et taille.
     */
    @Test
    void testConstructeurAvecPuitsEtTaille() {
        int taillePersonnalisee = 30;
        vuePuits = new VuePuits(puits, taillePersonnalisee);
        assertNotNull(vuePuits, "La vue du puits ne doit pas être nulle");
        assertSame(puits, vuePuits.getPuits(), "Le puits associé à la vue doit être le même que celui passé en argument");
        assertEquals(taillePersonnalisee, vuePuits.getTaille(), "La taille personnalisée doit être utilisée");
    }

    /**
     * Test les accesseurs (getters) et mutateurs (setters).
     */
    @Test
    void testGettersAndSetters() {
        Puits nouveauPuits = new Puits();
        int nouvelleTaille = 40;

        vuePuits.setPuits(nouveauPuits);
        vuePuits.setTaille(nouvelleTaille);

        assertSame(nouveauPuits, vuePuits.getPuits(), "Le puits n'a pas été correctement mis à jour");
        assertEquals(nouvelleTaille, vuePuits.getTaille(), "La taille n'a pas été correctement mise à jour");
    }


}
