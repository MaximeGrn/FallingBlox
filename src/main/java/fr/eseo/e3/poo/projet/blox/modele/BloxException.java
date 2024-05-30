package fr.eseo.e3.poo.projet.blox.modele;

/** Exception personnalisée pour gérer les situations exceptionnelles dans le jeu Falling Blox **/
public class BloxException extends Exception {

    /*** Attributs ***/
    public static final int BLOX_COLLISION = 1;      // Code pour une collision
    public static final int BLOX_SORTIE_PUITS = 2;   // Code pour une sortie du puits
    private final int type;                          // Type de l'exception

    /*** Constructeurs ***/
    public BloxException(String message, int type) {
        super(message); // Appelle le constructeur de la classe parent (Exception)
        this.type = type;
    }

    /*** Getters et Setters ***/
    public int getType() {
        return type;
    }
}