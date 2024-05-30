package fr.eseo.e3.poo.projet.blox.modele.pieces;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;

public interface Piece {

    /*** Getters ***/
    Element[] getElements();

    Puits getPuits();

    /*** Setters ***/
    void setPosition(int abscisse, int ordonnee);

    void setPuits(Puits puits);

    /*** Méthodes ***/
    void deplacerDe(int abscisse, int ordonnee) throws BloxException;

    void tourner(boolean horaire) throws BloxException;
}
