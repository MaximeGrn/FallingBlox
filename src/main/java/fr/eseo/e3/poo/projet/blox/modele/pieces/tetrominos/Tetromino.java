package fr.eseo.e3.poo.projet.blox.modele.pieces.tetrominos;

import fr.eseo.e3.poo.projet.blox.modele.BloxException;
import fr.eseo.e3.poo.projet.blox.modele.Coordonnees;
import fr.eseo.e3.poo.projet.blox.modele.Couleur;
import fr.eseo.e3.poo.projet.blox.modele.Element;
import fr.eseo.e3.poo.projet.blox.modele.Puits;
import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

public abstract class Tetromino implements Piece {

    /*** Attributs ***/
    private Element[] elements;
    private Puits puits;

    /*** Constructeurs ***/
    public Tetromino(Coordonnees coordonnees, Couleur couleur) {
        elements = new Element[4];
        setElements(coordonnees, couleur);
    }

    /*** Méthodes ***/
    @Override
    public void deplacerDe(int deltaX, int deltaY) throws BloxException {
        // Vérification des valeurs du vecteur de déplacement
        if (deltaY < 0 || deltaY > 1 || Math.abs(deltaX) > 1) {
            throw new IllegalArgumentException("Le vecteur de déplacement doit représenter une direction valide : " +
                    "vers la gauche, la droite ou le bas.");
        }

        // Vérifications des collisions et sorties du Puits AVANT de déplacer les éléments
        for (Element element : elements) {
            int newX = element.getCoordonnees().getAbscisse() + deltaX;
            int newY = element.getCoordonnees().getOrdonnee() + deltaY;

            if (puits != null) { // Vérification seulement si associé à un Puits
                // Collision avec le fond du Puits
                if (newY >= puits.getProfondeur()) {
                    throw new BloxException("Collision avec le fond du puits", BloxException.BLOX_COLLISION);
                }
                // Collision avec le Tas
                if (puits.getTas().elementExists(newX, newY)) {
                    throw new BloxException("Collision avec le Tas", BloxException.BLOX_COLLISION);
                }
                // Sortie du Puits à gauche ou à droite
                if (newX < 0 || newX >= puits.getLargeur()) {
                    throw new BloxException("Sortie du Puits", BloxException.BLOX_SORTIE_PUITS);
                }
            }
        }
        // Déplacement des éléments SI aucune exception n'a été levée
        for (Element element : elements) {
            element.deplacerDe(deltaX, deltaY);
        }
    }

    @Override
    public void tourner(boolean horaire) throws BloxException {
        Coordonnees refCoord = elements[0].getCoordonnees();
        int dx = -refCoord.getAbscisse();
        int dy = -refCoord.getOrdonnee();

        // Calculer les nouvelles coordonnées de rotation pour chaque élément
        Coordonnees[] nouvellesCoordonnees = new Coordonnees[elements.length];
        for (int i = 1; i < elements.length; i++) {
            Element element = elements[i];
            int x = element.getCoordonnees().getAbscisse() + dx; // Appliquer la translation 1 pour x
            int y = element.getCoordonnees().getOrdonnee() + dy; // Appliquer la translation 1 pour y
            // Calculer la rotation
            int newX = horaire ? -y : y;
            int newY = horaire ? x : -x;
            newX -= dx; // Appliquer la translation 2 pour x
            newY -= dy; // Appliquer la translation 2 pour y

            nouvellesCoordonnees[i] = new Coordonnees(newX, newY);

            // Vérifier les collisions et les sorties du puits
            if (puits != null) {
                // Collision avec le fond du Puits
                if (newY >= puits.getProfondeur()) {
                    throw new BloxException("Collision avec le fond du puits lors de la rotation", BloxException.BLOX_COLLISION);
                }
                // Collision avec le Tas
                if (puits.getTas().elementExists(newX, newY)) {
                    throw new BloxException("Collision avec le Tas lors de la rotation", BloxException.BLOX_COLLISION);
                }
                // Sortie du Puits à gauche ou à droite
                if (newX < 0 || newX >= puits.getLargeur()) {
                    throw new BloxException("Sortie du Puits lors de la rotation", BloxException.BLOX_SORTIE_PUITS);
                }
            }
        }
        // Appliquer les nouvelles coordonnées de rotation
        for (int i = 1; i < elements.length; i++) {
            elements[i].setCoordonnees(nouvellesCoordonnees[i]);
        }
    }

    /*** Méthodes toString ***/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName() + ":\n");
        for (Element element : elements) {
            sb.append("\t").append(element.toString()).append("\n");
        }
        return sb.toString();
    }

    /*** Getters et Setters ***/
    @Override
    public Element[] getElements() {
        return elements;
    }

    @Override
    public Puits getPuits() {
        return puits;
    }

    @Override
    public void setPuits(Puits puits) {
        this.puits = puits;
    }

    @Override
    public void setPosition(int abscisse, int ordonnee) {
        int deltaX = abscisse - elements[0].getCoordonnees().getAbscisse();
        int deltaY = ordonnee - elements[0].getCoordonnees().getOrdonnee();
        for (Element element : elements) {
            element.setCoordonnees(new Coordonnees(
                    element.getCoordonnees().getAbscisse() + deltaX,
                    element.getCoordonnees().getOrdonnee() + deltaY
            ));
        }
    }

    protected abstract void setElements(Coordonnees coordonnees, Couleur couleur);
}