package fr.eseo.e3.poo.projet.blox.vue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.eseo.e3.poo.projet.blox.modele.Puits;

/**
 * Classe de test pour l'affichage de VuePuits.
 */
public class VuePuitsAffichageTest {

    /**
     * Test le constructeur VuePuits(Puits).
     */
    private void testConstructeurPuits() {
        JFrame fenetre = new JFrame("Puits"); // Crée une fenêtre avec le titre "Puits"
        VuePuits vuePuits = new VuePuits(new Puits()); // Crée une VuePuits avec un Puits par défaut
        fenetre.add(vuePuits); // Ajoute la VuePuits à la fenêtre

        // Dimensionne la fenêtre en fonction de la taille préférée de VuePuits
        fenetre.pack();

        // Centre la fenêtre à l'écran
        fenetre.setLocationRelativeTo(null);

        // Termine l'application lorsque la fenêtre est fermée
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Affiche la fenêtre
        fenetre.setVisible(true);
    }

    /**
     * Test le constructeur VuePuits(Puits, int).
     */
    private void testConstructeurPuitsTaille() {
        JFrame fenetre = new JFrame("Puits et taille"); // Crée une fenêtre avec le titre "Puits et taille"
        VuePuits vuePuits = new VuePuits(new Puits(), 30); // Crée une VuePuits avec une taille de 30 pixels par élément
        fenetre.add(vuePuits); // Ajoute la VuePuits à la fenêtre

        // Dimensionne la fenêtre en fonction de la taille préférée de VuePuits
        fenetre.pack();

        // Centre la fenêtre à l'écran
        fenetre.setLocationRelativeTo(null);

        // Termine l'application lorsque la fenêtre est fermée
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Affiche la fenêtre
        fenetre.setVisible(true);
    }

    /**
     * Méthode principale pour lancer les tests d'affichage.
     *
     * @param args Les arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VuePuitsAffichageTest test = new VuePuitsAffichageTest();
            test.testConstructeurPuits();
            test.testConstructeurPuitsTaille();
        });
    }
}
