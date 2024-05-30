package fr.eseo.e3.poo.projet.blox;

import fr.eseo.e3.poo.projet.blox.controleur.ControleurClavier;
import fr.eseo.e3.poo.projet.blox.controleur.Gravite;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.pieces.UsineDePiece;
import fr.eseo.e3.poo.projet.blox.vue.PanneauInformation;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

/**
 * Extensions réalisées :
 *
 * 1. **Ajout des pièces manquantes (TTetromino, LTetromino, JTetromino, ZTetromino, STetromino) :**
 *    Les classes correspondantes ont été implémentées dans le package `fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos`.
 *    La classe `UsineDePiece` a été modifiée pour générer aléatoirement ces nouvelles pièces. Le mode cyclique
 *    respecte l'ordre d'ajout des pièces.
 *
 * 2. **Détection et suppression des lignes complètes :**
 *    La classe `Tas` a été modifiée pour détecter les lignes complètes, les supprimer et faire descendre les éléments
 *    situés au-dessus.
 *
 * 3. **Calcul et affichage du score :**
 *    Une classe `Score` a été créée pour gérer le calcul du score en fonction du nombre de lignes complétées.
 *    Le score est affiché dans le `PanneauInformation` via la classe `VueScore`.
 *
 * 4. **Contrôle des pièces via le clavier :**
 *    La classe `ControleurClavier` gère les événements du clavier pour déplacer et tourner la pièce actuelle.
 *    Les touches utilisées sont :
 *      - Flèche du bas : accélérer la descente
 *      - Flèche du haut : poser directement la pièce sur le tas
 *      - Flèche de gauche : déplacer à gauche
 *      - Flèche de droite : déplacer à droite
 *      - Espace : tourner la pièce
 *
 * 5. **Affichage du nombre total de lignes complétées :**
 *    La classe `Tas` stocke le nombre total de lignes complétées.
 *    Ce nombre est affiché dans le `PanneauInformation` via la classe `VueScore`.
 *
 * 6. **Calcul et affichage du niveau et augmentation de la vitesse :**
 *    Une classe `Level` a été créée pour gérer le niveau du jeu en fonction du score.
 *    Le niveau est affiché dans le `PanneauInformation` via la classe `VueLevel`.
 *    La vitesse de la gravité augmente à chaque niveau.
 *
 * 7. **Fin de jeu :**
 *    Le jeu se termine lorsque le tas atteint le haut du puits.
 *
 * 8. **Pop-up Game Over :**
 *    Un pop-up `GameOver` s'affiche lorsque la partie est perdue.
 *    Ce pop-up contient un bouton "Rejouer" pour relancer une nouvelle partie.
 */

/** Classe principale pour lancer le jeu Falling Blox **/
public class FallingBloxVersion1 {

    /*** Méthodes ***/
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Puits puits;
            if (args.length == 0) {
                // Aucun argument : puits vide
                puits = new Puits();
            } else if (args.length == 1) {
                // Un argument : nombre d'éléments dans le tas
                int nbElements = Integer.parseInt(args[0]);
                puits = new Puits(Puits.LARGEUR_PAR_DEFAUT, Puits.PROFONDEUR_PAR_DEFAUT, nbElements, nbElements / Puits.LARGEUR_PAR_DEFAUT + 1);
            } else if (args.length == 2) {
                // Deux arguments : nombre d'éléments et nombre de lignes pour le tas
                int nbElements = Integer.parseInt(args[0]);
                int nbLignes = Integer.parseInt(args[1]);
                puits = new Puits(Puits.LARGEUR_PAR_DEFAUT, Puits.PROFONDEUR_PAR_DEFAUT, nbElements, nbLignes);
            } else {
                System.err.println("Usage: java FallingBloxVersion1 [nbElements [nbLignes]]");
                return; // Termine le programme si les arguments sont incorrects
            }

            // Création des composants
            VuePuits vuePuits = new VuePuits(puits);
            Gravite gravite = new Gravite(vuePuits);
            PanneauInformation panneauInformation = new PanneauInformation(puits, gravite);
            ControleurClavier controleurClavier = new ControleurClavier(vuePuits);

            // Création de la fenêtre principale
            JFrame fenetre = new JFrame("Falling Blox");
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setResizable(false);
            fenetre.setLayout(new BorderLayout());

            // Ajout des composants à la fenêtre
            fenetre.add(vuePuits, BorderLayout.CENTER);
            fenetre.add(panneauInformation, BorderLayout.EAST);

            // Affichage de la fenêtre
            fenetre.pack();
            fenetre.setLocationRelativeTo(null);
            fenetre.setVisible(true);

            // Démarrage du jeu en ajoutant la première pièce
            Piece pieceActuelle = UsineDePiece.genererTetromino();
            pieceActuelle.setPuits(puits);
            pieceActuelle.setPosition(puits.getLargeur() / 2, -4);
            puits.setPieceActuelle(pieceActuelle);
            Piece pieceSuivante = UsineDePiece.genererTetromino();
            pieceSuivante.setPuits(puits);
            puits.setPieceSuivante(pieceSuivante);
            gravite.demarrer();
            vuePuits.addKeyListener(controleurClavier);

            // Permettre à la VuePuits de recevoir les événements du clavier
            vuePuits.setFocusable(true);
            vuePuits.requestFocusInWindow();
        });
    }
}