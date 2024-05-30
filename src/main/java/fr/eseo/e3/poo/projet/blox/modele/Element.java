package fr.eseo.e3.poo.projet.blox.modele;

import java.util.Objects;

/** Représente un élément individuel (carré) d'une pièce dans le jeu Falling Blox **/

public class Element {

    /*** Attributs ***/
    private Coordonnees coordonnees;
    private Couleur couleur;

    /*** Constructeurs ***/
    public Element(Coordonnees coordonnees) {
        this(coordonnees, Couleur.values()[0]);
    }

    public Element(int abscisse, int ordonnee) {
        this(new Coordonnees(abscisse, ordonnee));
    }

    public Element(Coordonnees coordonnees, Couleur couleur) {
        this.coordonnees = coordonnees;
        this.couleur = couleur;
    }

    public Element(int abscisse, int ordonnee, Couleur couleur) {
        this(new Coordonnees(abscisse, ordonnee), couleur);
    }

    /*** Méthodes ***/
    public void deplacerDe(int deltaX, int deltaY) {
        coordonnees.setAbscisse(coordonnees.getAbscisse() + deltaX);
        coordonnees.setOrdonnee(coordonnees.getOrdonnee() + deltaY);
    }

    /*** Méthodes toString ***/
    @Override
    public String toString() {
        return String.format("%s - %s", coordonnees, couleur);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Element other = (Element) obj;
        return Objects.equals(coordonnees, other.coordonnees) && couleur == other.couleur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordonnees, couleur);
    }

    /*** Getters et Setters ***/
    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public void setCoordonnees(Coordonnees coordonnees) {
        this.coordonnees = coordonnees;
    }

    public Couleur getCouleur() {
        return couleur;
    }

    public void setCouleur(Couleur couleur) {
        this.couleur = couleur;
    }
}