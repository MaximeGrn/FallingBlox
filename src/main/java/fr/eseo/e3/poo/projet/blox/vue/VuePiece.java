package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;
import fr.eseo.e3.poo.projet.blox.modele.Element;

import java.awt.Color;
import java.awt.Graphics2D;

/** Classe responsable de l'affichage graphique des Pieces **/
public class VuePiece {

    /*** Attributs ***/
    public static final double MULTIPLIER_TEINTE = 0.3; // Valeur entre 0.0 et 1.0
    private final int taille;
    private final Piece piece;

    /*** Constructeurs ***/
    public VuePiece(Piece piece, int taille) {
        this.piece = piece;
        this.taille = taille;
    }

    /*** Méthodes ***/
    public void afficherPiece(Graphics2D g2D) {
        // Construit chaque élément d'une pièce et sa couleur
        for (int i = 0; i < piece.getElements().length; i++) {
            Element element = piece.getElements()[i];
            Color color = element.getCouleur().getCouleurPourAffichage();
            if (i == 0) { // Vérifie si c'est l'élément de référence (premier élément du tableau)
                color = teinte(color); // Applique la teinte uniquement à l'élément de référence
            }
            g2D.setColor(color);
            g2D.fill3DRect(
                    element.getCoordonnees().getAbscisse() * taille,
                    element.getCoordonnees().getOrdonnee() * taille,
                    taille,
                    taille,
                    true
            );
        }
    }

    public int getLargeurPixels() {
        int largeurMax = 0;
        for (Element element : piece.getElements()) {
            int x = element.getCoordonnees().getAbscisse() * taille;
            if (x > largeurMax) {
                largeurMax = x;
            }
        }
        return largeurMax + taille;
    }

    public static Color teinte(Color couleur) {
        int r = couleur.getRed();
        int g = couleur.getGreen();
        int b = couleur.getBlue();

        r = r + (int) ((255 - r) * MULTIPLIER_TEINTE);
        g = g + (int) ((255 - g) * MULTIPLIER_TEINTE);
        b = b + (int) ((255 - b) * MULTIPLIER_TEINTE);

        return new Color(Math.min(r, 255), Math.min(g, 255), Math.min(b, 255));
    }
}