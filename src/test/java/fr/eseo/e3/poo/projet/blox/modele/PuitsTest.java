package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PuitsTest {

    /*** Attributs ***/
    private Puits puits;

    /*** Méthodes ***/
    @BeforeEach
    void setUp() {
        puits = new Puits();
    }

    /*** Test des Constructeurs ***/
    @Test
    void testConstructeurParDefaut() {
        assertEquals(Puits.LARGEUR_PAR_DEFAUT, puits.getLargeur());
        assertEquals(Puits.PROFONDEUR_PAR_DEFAUT, puits.getProfondeur());
        assertNull(puits.getPieceActuelle());
        assertNull(puits.getPieceSuivante());
        assertNotNull(puits.getTas());
        assertEquals(0, puits.getTas().getElements().size());
        assertNotNull(puits.getScore());
        assertEquals(0, puits.getScore().getScore());
        assertNotNull(puits.getLevel());
        assertEquals(1, puits.getLevel().getLevel());
    }

    @Test
    void testConstructeurAvecDimensions() {
        puits = new Puits(8, 18);
        assertEquals(8, puits.getLargeur());
        assertEquals(18, puits.getProfondeur());
        assertNull(puits.getPieceActuelle());
        assertNull(puits.getPieceSuivante());
        assertNotNull(puits.getTas());
        assertEquals(0, puits.getTas().getElements().size());
        assertNotNull(puits.getScore());
        assertEquals(0, puits.getScore().getScore());
        assertNotNull(puits.getLevel());
        assertEquals(1, puits.getLevel().getLevel());
    }

    @Test
    void testConstructeurAvecDimensionsInvalidesLargeurTropPetite() {
        assertThrows(IllegalArgumentException.class, () -> new Puits(4, 18));
    }

    @Test
    void testConstructeurAvecDimensionsInvalidesLargeurTropGrande() {
        assertThrows(IllegalArgumentException.class, () -> new Puits(16, 18));
    }

    @Test
    void testConstructeurAvecDimensionsInvalidesProfondeurTropPetite() {
        assertThrows(IllegalArgumentException.class, () -> new Puits(8, 14));
    }

    @Test
    void testConstructeurAvecDimensionsInvalidesProfondeurTropGrande() {
        assertThrows(IllegalArgumentException.class, () -> new Puits(8, 26));
    }

    @Test
    void testConstructeurDeCopie() {
        Puits puitsOriginal = new Puits(8, 18, 5, 2);
        ITetromino pieceActuelle = new ITetromino(new Coordonnees(3, 5), Couleur.BLEU);
        puitsOriginal.setPieceActuelle(pieceActuelle);
        OTetromino pieceSuivante = new OTetromino(new Coordonnees(1, 2), Couleur.ROUGE);
        puitsOriginal.setPieceSuivante(pieceSuivante);

        Puits puitsCopie = new Puits(puitsOriginal);

        assertEquals(puitsOriginal.getLargeur(), puitsCopie.getLargeur());
        assertEquals(puitsOriginal.getProfondeur(), puitsCopie.getProfondeur());
        assertNotSame(puitsOriginal.getPieceActuelle(), puitsCopie.getPieceActuelle());
        assertNotSame(puitsOriginal.getPieceSuivante(), puitsCopie.getPieceSuivante());
        assertEquals(pieceActuelle.getElements()[0].getCoordonnees(), puitsCopie.getPieceActuelle().getElements()[0].getCoordonnees());
        assertEquals(pieceSuivante.getElements()[0].getCoordonnees(), puitsCopie.getPieceSuivante().getElements()[0].getCoordonnees());
        assertEquals(puitsOriginal.getTas().getElements().size(), puitsCopie.getTas().getElements().size());
        for (int i = 0; i < puitsOriginal.getTas().getElements().size(); i++) {
            assertSame(puitsOriginal.getTas().getElements().get(i), puitsCopie.getTas().getElements().get(i));
        }
    }

    @Test
    void testConstructeurAvecDimensionsEtTas() {
        puits = new Puits(8, 18, 5, 2);
        assertEquals(8, puits.getLargeur());
        assertEquals(18, puits.getProfondeur());
        assertNotNull(puits.getTas());
        assertEquals(5, puits.getTas().getElements().size());
    }

    /*** Test des Getters et Setters ***/
    @Test
    void testSetLargeurValide() {
        puits.setLargeur(12);
        assertEquals(12, puits.getLargeur());
    }

    @Test
    void testSetLargeurInvalideTropPetite() {
        assertThrows(IllegalArgumentException.class, () -> puits.setLargeur(4));
    }

    @Test
    void testSetLargeurInvalideTropGrande() {
        assertThrows(IllegalArgumentException.class, () -> puits.setLargeur(16));
    }

    @Test
    void testSetProfondeurValide() {
        puits.setProfondeur(18);
        assertEquals(18, puits.getProfondeur());
    }

    @Test
    void testSetProfondeurInvalideTropPetite() {
        assertThrows(IllegalArgumentException.class, () -> puits.setProfondeur(14));
    }

    @Test
    void testSetProfondeurInvalideTropGrande() {
        assertThrows(IllegalArgumentException.class, () -> puits.setProfondeur(26));
    }

    @Test
    void testSetPieceSuivante() {
        Piece piece = new ITetromino(new Coordonnees(0, 0), Couleur.BLEU);
        puits.setPieceSuivante(piece);
        assertSame(piece, puits.getPieceSuivante());
        assertNull(puits.getPieceActuelle());
    }

    @Test
    void testSetPieceSuivanteLorsquePieceSuivanteDejaPresente() {
        Piece piece1 = new ITetromino(new Coordonnees(0, 0), Couleur.BLEU);
        Piece piece2 = new OTetromino(new Coordonnees(0, 0), Couleur.ROUGE);
        puits.setPieceSuivante(piece1);
        puits.setPieceSuivante(piece2);
        assertSame(piece2, puits.getPieceSuivante());
        assertSame(piece1, puits.getPieceActuelle());
        assertEquals(new Coordonnees(puits.getLargeur() / 2, -4), piece1.getElements()[0].getCoordonnees());
    }

    @Test
    void testSetPieceSuivanteLorsquePieceSuivanteNull() {
        puits.setPieceSuivante(null);
        assertNull(puits.getPieceSuivante());
    }

    @Test
    void testSetPieceActuelle() {
        Piece piece = new OTetromino(new Coordonnees(0, 0), Couleur.ROUGE);
        puits.setPieceActuelle(piece);
        assertSame(piece, puits.getPieceActuelle());
    }

    @Test
    void testSetPieceActuelleNull() {
        puits.setPieceActuelle(null);
        assertNull(puits.getPieceActuelle());
    }

    @Test
    void testSetScore() {
        Score score = new Score();
        puits.setScore(score);
        assertSame(score, puits.getScore());
    }

    @Test
    void testSetLevel() {
        Level level = new Level();
        puits.setLevel(level);
        assertSame(level, puits.getLevel());
    }

    /*** Test des méthodes ***/
    @Test
    void testGererCollision() {
        Piece piece = new ITetromino(new Coordonnees(puits.getLargeur() / 2, -2), Couleur.BLEU);
        puits.setPieceActuelle(piece);

        while (puits.getPieceActuelle() != null) {
            try {
                puits.gravite();
            } catch (BloxException e) {
                // Ignorer les exceptions pour le moment
            }
        }

        assertFalse(puits.getTas().getElements().isEmpty());
        assertSame(puits.getPieceSuivante(), puits.getPieceActuelle());
        assertNotNull(puits.getPieceSuivante());
    }

    @Test
    void testGraviteAvecCollisionEtGererCollision() {
        // Créer une pièce qui va entrer en collision avec le tas dès le premier appel à gravite()
        Piece piece = new ITetromino(new Coordonnees(puits.getLargeur() / 2, puits.getProfondeur() - 1), Couleur.BLEU);
        puits.setPieceActuelle(piece);

        // Appeler gravite(), ce qui devrait déclencher gererCollision()
        assertThrows(BloxException.class, puits::gravite);

        // Vérifier les effets de gererCollision() :
        assertFalse(puits.getTas().getElements().isEmpty()); // La pièce a été ajoutée au tas
        assertSame(puits.getPieceSuivante(), puits.getPieceActuelle()); // La pièce actuelle est la pièce suivante
        assertNotNull(puits.getPieceSuivante()); // Une nouvelle pièce suivante a été générée
    }

    @Test
    void testGraviteSansPieceActuelle() throws BloxException {
        puits.gravite();
        assertNull(puits.getPieceActuelle());
    }

    @Test
    void testGraviteAvecPieceActuelle() throws BloxException {
        Piece piece = new ITetromino(new Coordonnees(puits.getLargeur() / 2, -2), Couleur.BLEU);
        puits.setPieceActuelle(piece);

        puits.gravite();

        assertEquals(new Coordonnees(puits.getLargeur() / 2, -1), piece.getElements()[0].getCoordonnees());
    }

    @Test
    void testGraviteAvecCollision() {
        Piece piece = new ITetromino(new Coordonnees(puits.getLargeur() / 2, puits.getProfondeur() - 2), Couleur.BLEU);
        puits.setPieceActuelle(piece);
        assertThrows(BloxException.class, puits::gravite);
        assertFalse(puits.getTas().getElements().isEmpty());
    }

    @Test
    void testVerifierDefaite() throws Exception {
        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < puits.getLargeur(); i++) {
            elements.add(new Element(i, -1, Couleur.ROUGE));
        }
        puits.getTas().getElements().addAll(elements);

        Method verifierDefaiteMethod = Puits.class.getDeclaredMethod("verifierDefaite");
        verifierDefaiteMethod.setAccessible(true);
        boolean resultat = (boolean) verifierDefaiteMethod.invoke(puits);
        assertTrue(resultat);
    }

    @Test
    void testVerifierDefaiteSansDefaite() throws Exception {
        Method verifierDefaiteMethod = Puits.class.getDeclaredMethod("verifierDefaite");
        verifierDefaiteMethod.setAccessible(true);
        boolean resultat = (boolean) verifierDefaiteMethod.invoke(puits);
        assertFalse(resultat);
    }

    @Test
    void testEquals() {
        Puits puits1 = new Puits(10, 15);
        Puits puits2 = new Puits(10, 15);
        assertTrue(puits1.equals(puits2));

        puits1.setPieceSuivante(new ITetromino(new Coordonnees(0, 0), Couleur.BLEU));
        assertFalse(puits1.equals(puits2));

        puits2.setPieceSuivante(new ITetromino(new Coordonnees(0, 0), Couleur.BLEU));
        assertTrue(puits1.equals(puits2));

        puits1.setPieceActuelle(new OTetromino(new Coordonnees(5, 5), Couleur.ROUGE));
        assertFalse(puits1.equals(puits2));

        puits2.setPieceActuelle(new OTetromino(new Coordonnees(5, 5), Couleur.ROUGE));
        assertTrue(puits1.equals(puits2));
    }

    @Test
    void testAppendPieceToString() throws Exception {
        Method appendPieceToStringMethod = Puits.class.getDeclaredMethod("appendPieceToString", StringBuilder.class, Piece.class);
        appendPieceToStringMethod.setAccessible(true);

        StringBuilder sb = new StringBuilder();
        Piece piece = new OTetromino(new Coordonnees(0, 0), Couleur.ROUGE);

        appendPieceToStringMethod.invoke(puits, sb, piece);

        String expected = "OTetromino :\n" +
                "\t(0, 0) - ROUGE\n" +
                "\t(1, 0) - ROUGE\n" +
                "\t(0, -1) - ROUGE\n" +
                "\t(1, -1) - ROUGE\n";

        assertEquals(expected, sb.toString());
    }

    /*** Test des méthodes toString ***/
    @Test
    void testToStringPuitsVide() {
        String expected = "Puits : Dimension 10 x 15\n" +
                "Piece Actuelle : <aucune>\n" +
                "Piece Suivante : <aucune>\n";
        assertEquals(expected, puits.toString());
    }

    @Test
    void testToStringAvecPieceActuelle() {
        Coordonnees coordonnees = new Coordonnees(5, -4);
        Couleur couleur = Couleur.BLEU;
        ITetromino piece = new ITetromino(coordonnees, couleur);
        puits.setPieceActuelle(piece);

        String expected = "Puits : Dimension 10 x 15\n" +
                "Piece Actuelle : ITetromino :\n" +
                "\t(5, -4) - BLEU\n" +
                "\t(5, -5) - BLEU\n" +
                "\t(5, -6) - BLEU\n" +
                "\t(5, -3) - BLEU\n" +
                "Piece Suivante : <aucune>\n";
        assertEquals(expected, puits.toString());
    }

    @Test
    void testToStringAvecPieceActuelleEtPieceSuivante() {
        Coordonnees coordonnees1 = new Coordonnees(5, -4);
        Couleur couleur1 = Couleur.BLEU;
        ITetromino piece1 = new ITetromino(coordonnees1, couleur1);
        puits.setPieceActuelle(piece1);

        Coordonnees coordonnees2 = new Coordonnees(0, 0);
        Couleur couleur2 = Couleur.ROUGE;
        OTetromino piece2 = new OTetromino(coordonnees2, couleur2);
        puits.setPieceSuivante(piece2);

        String expected = "Puits : Dimension 10 x 15\n" +
                "Piece Actuelle : ITetromino :\n" +
                "\t(5, -4) - BLEU\n" +
                "\t(5, -5) - BLEU\n" +
                "\t(5, -6) - BLEU\n" +
                "\t(5, -3) - BLEU\n" +
                "Piece Suivante : OTetromino :\n" +
                "\t(0, 0) - ROUGE\n" +
                "\t(1, 0) - ROUGE\n" +
                "\t(0, -1) - ROUGE\n" +
                "\t(1, -1) - ROUGE\n";
        assertEquals(expected, puits.toString());
    }
}