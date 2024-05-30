package fr.eseo.e3.poo.projet.blox.modele;
import java.util.Objects;

public class Coordonnees {
    private int abscisse;
    private int ordonnee;

    /*** Constructeur **/
    public Coordonnees(int abscisse, int ordonnee) {
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }

    /*** Getters et Setters ***/
    public int getAbscisse() {
        return abscisse;
    }
    public int getOrdonnee() {
        return ordonnee;
    }

    public void setAbscisse(int abscisse) {
        this.abscisse = abscisse;
    }
    public void setOrdonnee(int ordonnee) {
        this.ordonnee = ordonnee;
    }

    /*** Méthodes ***/
    @Override
    public String toString() {
        return "(" + abscisse + ", " + ordonnee + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Coordonnees coord = (Coordonnees) o;
        return abscisse == coord.abscisse && ordonnee == coord.ordonnee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(abscisse, ordonnee);
    }
}
