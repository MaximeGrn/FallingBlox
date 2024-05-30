package fr.eseo.e3.poo.projet.blox.controleur;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos.ITetromino;
import fr.eseo.e3.poo.projet.blox.vue.VuePuits;

/*** Classe de test pour la classe Gravite ***/
public class GraviteTest {

    /**
     * Test le fonctionnement de la gravité en affichant une pièce
     * qui descend automatiquement dans un puits.
     */
    private void testGravite() {
        SwingUtilities.invokeLater(() -> {
            // Création du puits et ajout d'une pièce
            Puits puits = new Puits(10, 20);
            puits.setPieceSuivante(new ITetromino(new Coordonnees(4, -2), Couleur.CYAN)); // Position initiale au-dessus du puits visible

            // Création de la VuePuits
            VuePuits vuePuits = new VuePuits(puits);

            // Création de la fenêtre
            JFrame fenetre = new JFrame("Test Gravité");
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.add(vuePuits);
            fenetre.pack();
            fenetre.setLocationRelativeTo(null);
            fenetre.setVisible(true);

            // Création et démarrage de la gravité
            Gravite gravite = new Gravite(vuePuits);
            gravite.setPeriodicite(100); // Pour une chute plus rapide (facultatif)
        });
    }

    /**
     * Méthode principale pour lancer le test.
     * @param args Les arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        GraviteTest test = new GraviteTest();
        test.testGravite();
    }
}