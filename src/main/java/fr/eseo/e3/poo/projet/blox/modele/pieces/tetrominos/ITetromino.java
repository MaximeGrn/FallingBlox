package fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;

public class ITetromino extends Tetromino {

    /*** Constructeur ***/
    public ITetromino(Coordonnees coordonnees, Couleur couleur) {
        super(coordonnees, couleur);
    }

    /*** Getters et Setters ***/
    @Override
    protected void setElements(Coordonnees coordonnees, Couleur couleur) {

        Element[] elements = getElements();
        elements[0] = new Element(coordonnees, couleur);
        elements[1] = new Element(coordonnees.getAbscisse(), coordonnees.getOrdonnee()-1, couleur);
        elements[2] = new Element(coordonnees.getAbscisse(), coordonnees.getOrdonnee() - 2, couleur);
        elements[3] = new Element(coordonnees.getAbscisse() , coordonnees.getOrdonnee() + 1, couleur);
    }

    /*** Méthodes ***/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName() + " :\n");
        Element[] elements=getElements();
        for (Element element : elements) {
            sb.append("\t").append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}