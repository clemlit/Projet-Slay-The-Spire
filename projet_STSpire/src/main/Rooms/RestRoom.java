package main.Rooms;

import main.Entite.Hero;

/**
 * La classe RestRoom représente une salle de repos dans le jeu.
 * Elle permet au héros de récupérer des points de vie en entrant dans la salle de repos.
 */
public class RestRoom {

    /** Le héros entrant dans la salle de repos */
    Hero hero;

    /**
     * Constructeur de la classe RestRoom.
     * Initialise la salle de repos avec un héros.
     * 
     * @param hero Le héros entrant dans la salle de repos.
     */
    public RestRoom(Hero hero) {
        this.hero = hero;
    }

    /**
     * Méthode pour entrer dans la salle de repos.
     * Affiche un message d'entrée et récupère des points de vie pour le héros.
     */
    public void enter() {
        System.out.println("Vous entrez dans une salle de repos.");
        System.out.println("Vous avez récupéré " + hero.regene() + " points de vie.");
    }
}