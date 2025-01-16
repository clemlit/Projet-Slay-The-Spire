package main.Rooms;

import java.util.Scanner;

import main.Entite.Hero;
import main.Entite.Machouilleur;
import main.Entite.Monstre;

/**
 * La classe FinalRoom représente une salle de boss final dans le jeu.
 * Elle hérite de la classe CombatRoom et initialise un boss final de type Machouilleur.
 */
public class FinalRoom extends CombatRoom {

    /**
     * Constructeur de la classe FinalRoom.
     * Initialise une salle de boss final avec un héros.
     * Crée un tableau de trois Machouilleurs en tant que boss final.
     * 
     * @param hero Le héros entrant dans la salle de boss final.
     */
    public FinalRoom(Hero hero) {
        super(hero);
        super.monstres = new Monstre[]{
            new Machouilleur(),
            new Machouilleur(),
            new Machouilleur(),
        };
    }

    /**
     * Méthode pour entrer dans la salle de boss final.
     * Affiche un message d'introduction et lance le combat contre les Machouilleurs.
     * 
     * @param sc Scanner pour la saisie utilisateur.
     * @return true si le héros survit et gagne, false sinon.
     */
    @Override
    public boolean enter(Scanner sc) {
        System.out.println("Vous entrez dans la salle du boss final !");
        System.out.println("Le boss final, " + monstres[0].getNom() + ", apparaît !");

        if (hero.estMort()) {
            System.out.println("Vous êtes mort. La partie est terminée.");
        } else {
            return super.combat(sc);
        }

        return true;
    }
}