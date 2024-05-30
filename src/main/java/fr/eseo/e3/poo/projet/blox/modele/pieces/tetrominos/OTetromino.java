package fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;

public class OTetromino extends Tetromino {

    /*** Constructeur ***/
    public OTetromino(Coordonnees coordonnees, Couleur couleur) {
        super(coordonnees, couleur);
    }

    /*** Getters et Setters ***/
    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {

        Element[] elements = getElements();
        elements[0] = new Element(coordonnees, couleur);
        elements[1] = new Element(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee(), couleur);
        elements[2] = new Element(coordonnees.getAbscisse(), coordonnees.getOrdonnee() - 1, couleur);
        elements[3] = new Element(coordonnees.getAbscisse() + 1, coordonnees.getOrdonnee() - 1, couleur);
    }

    /*** Méthodes ***/
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("OTetromino :\n");

        Element[] elements =getElements();
        for (Element element : elements) {
            builder.append("\t").append(element.toString()).append("\n");
        }
        return builder.toString();
    }

    @Override
    public void tourner(boolean horaire) {
        // Ne fait rien, les OTetrominos ne peuvent pas subir de rotation
    }
}