package fr.eseo.e3.poo.projet.blox.modele.pieces;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.JTetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.LTetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.OTetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.STetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.TTetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.Tetromino;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ZTetromino;

import java.util.Random;

/** Classe utilitaire pour générer des pièces de Tetris de manière aléatoire ou cyclique **/

public class UsineDePiece {

    /*** Attributs ***/
    public static final int ALEATOIRE_COMPLET = 0;
    public static final int ALEATOIRE_PIECE = 1;
    public static final int CYCLIC = 2;
    private static int mode = ALEATOIRE_PIECE;
    private static Random random = new Random();
    private static int nextCyclicIndex = 0;

    /*** Constructeurs ***/
    private UsineDePiece() {
    }

    /*** Méthodes ***/
    public static Tetromino genererTetromino() {
        Piece piece = null;
        switch (mode) {
            case ALEATOIRE_COMPLET:
                piece = genererTetrominoAleatoireComplet();
                break;
            case CYCLIC:
                piece = genererTetrominoCyclique();
                break;
            case ALEATOIRE_PIECE:
                piece = genererTetrominoAleatoirePiece();
                break;
        }
        return (Tetromino) piece;
    }

    private static Tetromino genererTetrominoAleatoireComplet() {
        int typeAleatoire = random.nextInt(7);
        Couleur[] couleurs = Couleur.values();
        Couleur couleurAleatoire = couleurs[random.nextInt(couleurs.length)];
        return switch (typeAleatoire) {
            case 0 -> new OTetromino(new Coordonnees(2, 3), couleurAleatoire);
            case 1 -> new ITetromino(new Coordonnees(2, 3), couleurAleatoire);
            case 2 -> new TTetromino(new Coordonnees(2, 3), couleurAleatoire);
            case 3 -> new LTetromino(new Coordonnees(2, 3), couleurAleatoire);
            case 4 -> new JTetromino(new Coordonnees(2, 3), couleurAleatoire);
            case 5 -> new ZTetromino(new Coordonnees(2, 3), couleurAleatoire);
            case 6 -> new STetromino(new Coordonnees(2, 3), couleurAleatoire);
            default -> throw new IllegalStateException("Unexpected value: " + typeAleatoire);
        };
    }

    private static Tetromino genererTetrominoAleatoirePiece() {
        int typeAleatoire = random.nextInt(7);
        return switch (typeAleatoire) {
            case 0 -> new OTetromino(new Coordonnees(2, 3), Couleur.ROUGE);
            case 1 -> new ITetromino(new Coordonnees(2, 3), Couleur.ORANGE);
            case 2 -> new TTetromino(new Coordonnees(2, 3), Couleur.BLEU);
            case 3 -> new LTetromino(new Coordonnees(2, 3), Couleur.VERT);
            case 4 -> new JTetromino(new Coordonnees(2, 3), Couleur.JAUNE);
            case 5 -> new ZTetromino(new Coordonnees(2, 3), Couleur.CYAN);
            case 6 -> new STetromino(new Coordonnees(2, 3), Couleur.VIOLET);
            default -> throw new IllegalStateException("Unexpected value: " + typeAleatoire);
        };
    }

    private static Tetromino genererTetrominoCyclique() {
        Tetromino tetromino = switch (nextCyclicIndex) {
            case 0 -> new OTetromino(new Coordonnees(2, 3), Couleur.ROUGE);
            case 1 -> new ITetromino(new Coordonnees(2, 3), Couleur.ORANGE);
            case 2 -> new TTetromino(new Coordonnees(2, 3), Couleur.BLEU);
            case 3 -> new LTetromino(new Coordonnees(2, 3), Couleur.VERT);
            case 4 -> new JTetromino(new Coordonnees(2, 3), Couleur.JAUNE);
            case 5 -> new ZTetromino(new Coordonnees(2, 3), Couleur.CYAN);
            case 6 -> new STetromino(new Coordonnees(2, 3), Couleur.VIOLET);
            default -> throw new IllegalStateException("Unexpected value: " + nextCyclicIndex);
        };
        nextCyclicIndex = (nextCyclicIndex + 1) % 7;
        return tetromino;
    }

    /*** Getters et Setters ***/
    public static void setMode(int mode) {
        UsineDePiece.mode = mode;
        if (mode == CYCLIC) {
            nextCyclicIndex = 0;
        }
    }
}