package fr.eseo.e3.poo.projet.blox.modele;

import fr.eseo.e3.poo.projet.blox.modele.pieces.Piece;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Tas {

    /*** Attributs ***/
    private Puits puits;
    private List<Element> elements;
    private int lignesSupprimees;
    private int totalLignesSupprimees;

    /*** Constructeurs ***/
    public Tas(Puits puits, int nbElements, int nbLignes, Random random) {
        this.puits = puits;
        this.elements = new ArrayList<>();
        construireTas(nbElements, nbLignes, random);
    }

    public Tas(Puits puits, int nbElements, int nbLignes) {
        this(puits, nbElements, nbLignes, new Random());
    }

    public Tas(Puits puits, int nbElements) {
        this(puits, nbElements, (nbElements / puits.getLargeur()) + 1);
    }

    public Tas(Puits puits) {
        this.puits = puits;
        this.elements = new ArrayList<>();
    }

    /*** Méthodes ***/
    private void construireTas(int nbElements, int nbLignes, Random random) {
        if (nbElements > puits.getLargeur() * nbLignes) {
            throw new IllegalArgumentException("Le nombre d'éléments est trop grand pour le Puits ou le nombre de lignes.");
        }

        int elementsPlaces = 0;
        while (elementsPlaces < nbElements) {
            int ordonnee = puits.getProfondeur() - random.nextInt(nbLignes) - 1;
            int abscisse = random.nextInt(puits.getLargeur());
            // Vérifie s'il n'y a pas déjà un élément à cette position
            if (!elementExists(abscisse, ordonnee)) {
                Couleur[] couleurs = Couleur.values();
                Couleur couleurAleatoire = couleurs[random.nextInt(couleurs.length)];
                elements.add(new Element(abscisse, ordonnee, couleurAleatoire));
                elementsPlaces++;
            }
        }
    }

    public boolean elementExists(int colonne, int ligne) {
        for (Element element : elements) {
            if (element.getCoordonnees().getAbscisse() == colonne && element.getCoordonnees().getOrdonnee() == ligne) {
                return true;
            }
        }
        return false;
    }

    public void ajouterElements(Piece piece) {
        Element[] elementsAPiece = piece.getElements();
        for (Element element : elementsAPiece) {
            Coordonnees coords = element.getCoordonnees();
            if (!elementExists(coords.getAbscisse(), coords.getOrdonnee())) {
                elements.add(element);
            }
        }
        lignesSupprimees = supprimerLignesCompletes();
    }

    private boolean ligneComplete(int ligne) {
        for (int colonne = 0; colonne < puits.getLargeur(); colonne++) {
            if (!elementExists(colonne, ligne)) {
                return false;
            }
        }
        return true;
    }

    public int supprimerLignesCompletes() {
        int lignesSupprimees = 0;
        int ligne = puits.getProfondeur() - 1;
        while (ligne >= 0) {
            if (ligneComplete(ligne)) {
                supprimerLigne(ligne);
                faireDescendreElements(ligne);
                lignesSupprimees++;
            } else {
                ligne--;
            }
        }
        totalLignesSupprimees += lignesSupprimees;
        return lignesSupprimees;
    }

    private void supprimerLigne(int ligne) {
        Iterator<Element> iterateur = elements.iterator();
        while (iterateur.hasNext()) {
            Element element = iterateur.next();
            if (element.getCoordonnees().getOrdonnee() == ligne) {
                iterateur.remove();
            }
        }
    }

    private void faireDescendreElements(int ligne) {
        for (Element element : elements) {
            if (element.getCoordonnees().getOrdonnee() < ligne) {
                element.deplacerDe(0, 1);
            }
        }
    }

    /*** Getters et Setters ***/
    public Puits getPuits() {
        return puits;
    }

    public List<Element> getElements() {
        return elements;
    }

    public int getLignesSupprimees() {
        return lignesSupprimees;
    }

    public int getTotalLignesSupprimees() {
        return totalLignesSupprimees;
    }
}