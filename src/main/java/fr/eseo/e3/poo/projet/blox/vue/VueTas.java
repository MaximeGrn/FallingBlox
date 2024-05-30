package fr.eseo.e3.poo.projet.blox.vue;

import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Tas;
import java.awt.Color;
import java.awt.Graphics2D;

/** Classe responsable de l'affichage graphique du Tas **/
public class VueTas {

    /*** Attributs ***/
    public static final double MULTIPLIER_NUANCE = 0.4; // Valeur entre 0.0 et 1.0
    private final VuePuits vuePuits;
    private final Tas tas;
    private final int taille;

    /*** Constructeurs ***/
    public VueTas(VuePuits vuePuits) {
        this.vuePuits = vuePuits;
        this.tas = vuePuits.getPuits().getTas();
        this.taille = vuePuits.getTaille();
    }

    /*** Méthodes ***/
    public void afficher(Graphics2D g2d) {
        for (Element element : tas.getElements()) {
            Color couleurOriginale = element.getCouleur().getCouleurPourAffichage();
            Color couleurNuance = nuance(couleurOriginale);
            g2d.setColor(couleurNuance);
            g2d.fill3DRect(
                    element.getCoordonnees().getAbscisse() * taille,
                    element.getCoordonnees().getOrdonnee() * taille,
                    taille, taille, false); // Effet 3D enfoncé
        }
    }

    public static Color nuance(Color couleur) {
        int r = couleur.getRed();
        int g = couleur.getGreen();
        int b = couleur.getBlue();

        r = (int) (r * (1 - MULTIPLIER_NUANCE));
        g = (int) (g * (1 - MULTIPLIER_NUANCE));
        b = (int) (b * (1 - MULTIPLIER_NUANCE));

        return new Color(Math.max(r, 0), Math.max(g, 0), Math.max(b, 0));
    }

    /*** Getters et Setters ***/
    public VuePuits getVuePuits() {
        return vuePuits;
    }
}