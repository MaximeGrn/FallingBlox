package fr.eseo.e3.poo.projet.blox.modele.pieces;

import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.JTetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.LTetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.STetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.TTetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.Tetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ZTetromino;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/*** Classe de test pour la classe UsineDePiece ***/

public class UsineDePieceTest {

    /*** Test des méthodes ***/
    @Test
    public void testGenererTetrominoAleatoireComplet() {
        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_COMPLET);

        Set<Couleur> couleurs = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            Tetromino piece = UsineDePiece.genererTetromino();
            assertTrue(piece instanceof OTetromino || piece instanceof ITetromino || piece instanceof TTetromino
                    || piece instanceof LTetromino || piece instanceof JTetromino || piece instanceof ZTetromino
                    || piece instanceof STetromino, "Le type de pièce n'est pas correct");
            couleurs.add(piece.getElements()[0].getCouleur());
        }
        assertTrue(couleurs.size() > 1, "Les couleurs ne sont pas suffisamment aléatoires");
    }

    @Test
    public void testGenererTetrominoAleatoirePiece() {
        UsineDePiece.setMode(UsineDePiece.ALEATOIRE_PIECE);

        for (int i = 0; i < 100; i++) {
            Tetromino piece = UsineDePiece.genererTetromino();
            if (piece instanceof OTetromino) {
                assertEquals(Couleur.ROUGE, piece.getElements()[0].getCouleur(),
                        "La couleur de l'OTetromino devrait être ROUGE");
            } else if (piece instanceof ITetromino) {
                assertEquals(Couleur.ORANGE, piece.getElements()[0].getCouleur(),
                        "La couleur de l'ITetromino devrait être ORANGE");
            } else if (piece instanceof TTetromino) {
                assertEquals(Couleur.BLEU, piece.getElements()[0].getCouleur(),
                        "La couleur de l'TTetromino devrait être BLEU");
            } else if (piece instanceof LTetromino) {
                assertEquals(Couleur.VERT, piece.getElements()[0].getCouleur(),
                        "La couleur de l'LTetromino devrait être VERT");
            } else if (piece instanceof JTetromino) {
                assertEquals(Couleur.JAUNE, piece.getElements()[0].getCouleur(),
                        "La couleur de l'JTetromino devrait être JAUNE");
            } else if (piece instanceof ZTetromino) {
                assertEquals(Couleur.CYAN, piece.getElements()[0].getCouleur(),
                        "La couleur de l'ZTetromino devrait être CYAN");
            } else if (piece instanceof STetromino) {
                assertEquals(Couleur.VIOLET, piece.getElements()[0].getCouleur(),
                        "La couleur de l'STetromino devrait être VIOLET");
            } else {
                fail("Type de Tetromino inconnu");
            }
        }
    }

    @Test
    public void testGenererTetrominoCyclique() {
        UsineDePiece.setMode(UsineDePiece.CYCLIC);

        Tetromino piece1 = UsineDePiece.genererTetromino();
        Tetromino piece2 = UsineDePiece.genererTetromino();
        Tetromino piece3 = UsineDePiece.genererTetromino();
        Tetromino piece4 = UsineDePiece.genererTetromino();
        Tetromino piece5 = UsineDePiece.genererTetromino();
        Tetromino piece6 = UsineDePiece.genererTetromino();
        Tetromino piece7 = UsineDePiece.genererTetromino();
        Tetromino piece8 = UsineDePiece.genererTetromino();

        assertInstanceOf(OTetromino.class, piece1, "La première pièce devrait être un OTetromino");
        assertInstanceOf(ITetromino.class, piece2, "La deuxième pièce devrait être un ITetromino");
        assertInstanceOf(TTetromino.class, piece3, "La troisième pièce devrait être un TTetromino");
        assertInstanceOf(LTetromino.class, piece4, "La quatrième pièce devrait être un LTetromino");
        assertInstanceOf(JTetromino.class, piece5, "La cinquième pièce devrait être un JTetromino");
        assertInstanceOf(ZTetromino.class, piece6, "La sixième pièce devrait être un ZTetromino");
        assertInstanceOf(STetromino.class, piece7, "La septième pièce devrait être un STetromino");
        assertInstanceOf(OTetromino.class, piece8, "La huitième pièce devrait être un OTetromino");

        assertEquals(Couleur.ROUGE, piece1.getElements()[0].getCouleur(), "La couleur de l'OTetromino devrait être ROUGE");
        assertEquals(Couleur.ORANGE, piece2.getElements()[0].getCouleur(), "La couleur de l'ITetromino devrait être ORANGE");
        assertEquals(Couleur.BLEU, piece3.getElements()[0].getCouleur(), "La couleur de l'TTetromino devrait être BLEU");
        assertEquals(Couleur.VERT, piece4.getElements()[0].getCouleur(), "La couleur de l'LTetromino devrait être VERT");
        assertEquals(Couleur.JAUNE, piece5.getElements()[0].getCouleur(), "La couleur de l'JTetromino devrait être JAUNE");
        assertEquals(Couleur.CYAN, piece6.getElements()[0].getCouleur(), "La couleur de l'ZTetromino devrait être CYAN");
        assertEquals(Couleur.VIOLET, piece7.getElements()[0].getCouleur(), "La couleur de l'STetromino devrait être VIOLET");
    }

    @Test
    void testDefaultMode() {
        assertEquals(UsineDePiece.ALEATOIRE_PIECE, UsineDePiece.genererTetromino().getElements()[0].getCouleur());
    }

    @Test
    public void testGenererTetrominoDefaultCase() {
        int modeActuel = UsineDePiece.ALEATOIRE_PIECE;
        UsineDePiece.setMode(3);
        PrintStream systemOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        try {
            UsineDePiece.genererTetromino();
        } catch (IllegalStateException e) {
        }

        System.setOut(systemOut);

        String output = outputStream.toString().trim();
        assertEquals("AUCUN MODE TROUVE", output, "Le message affiché dans le cas default n'est pas correct.");

        UsineDePiece.setMode(modeActuel);
    }

    @Test
    public void testGenererTetrominoAleatoireCompletException() {
        try {
            UsineDePiece.setMode(UsineDePiece.ALEATOIRE_COMPLET);
            Field randomField = UsineDePiece.class.getDeclaredField("random");
            randomField.setAccessible(true);
            randomField.set(null, new Random(7));

            UsineDePiece.genererTetromino();

        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail("Exception inattendue : " + e.getClass().getSimpleName());
        }
    }

    @Test
    public void testGenererTetrominoAleatoirePieceException() {
        try {
            UsineDePiece.setMode(UsineDePiece.ALEATOIRE_PIECE);
            Field randomField = UsineDePiece.class.getDeclaredField("random");
            randomField.setAccessible(true);
            randomField.set(null, new Random(7));

            UsineDePiece.genererTetromino();

        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail("Exception inattendue : " + e.getClass().getSimpleName());
        }
    }

    @Test
    public void testGenererTetrominoCycliqueException() {
        try {
            UsineDePiece.setMode(UsineDePiece.CYCLIC);

            Field indexField = UsineDePiece.class.getDeclaredField("nextCyclicIndex");
            indexField.setAccessible(true);
            indexField.set(null, 7);

            UsineDePiece.genererTetromino();

        } catch (IllegalStateException e) {
        } catch (Exception e) {
            fail("Exception inattendue : " + e.getClass().getSimpleName());
        }
    }

    /*** Test des Getters et Setters ***/
    @Test
    public void testSetModeException() {
        assertThrows(IllegalArgumentException.class, () -> UsineDePiece.setMode(-1),
                "setMode() doit lever une IllegalArgumentException si le mode est invalide.");

        assertThrows(IllegalArgumentException.class, () -> UsineDePiece.setMode(4),
                "setMode() doit lever une IllegalArgumentException si le mode est invalide.");
    }
}