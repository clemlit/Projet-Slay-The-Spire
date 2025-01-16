package main.Entite;
import java.util.Random;

/**
 * La classe Machouilleur représente un type de monstre appelé Machouilleur dans le jeu.
 * Il étend la classe Monstre et possède des comportements spécifiques tels que Grommeler, Charger et Mordre.
 */
public class Machouilleur extends Monstre {

    /** Indique si le Machouilleur est en phase de préparation */
    private boolean preparation = true;

    /**
     * Constructeur de la classe Machouilleur.
     * Initialise un Machouilleur avec des points de vie (pv) égaux à 40 et un blocage initial de 0.
     */
    public Machouilleur() {
        super("Machouilleur", 40, 0);
    }

    /**
     * Méthode d'attaque du Machouilleur contre un héros.
     * Le Machouilleur a différents mouvements possibles, tels que Grommeler, Charger et Mordre.
     * Le choix du mouvement dépend de la phase de préparation et d'une sélection aléatoire.
     * 
     * @param hero Le héros contre lequel le Machouilleur attaque.
     */
    public void attaque(Hero hero) {
        if (preparation) {
            charge(hero);
            return;
        }

        Random random = new Random();
        int choixMouvement = random.nextInt(100);

        if (choixMouvement < 45)
            gronder();
        else if (choixMouvement < 75)
            charge(hero);
        else
            morsure(hero);
    }

    /**
     * Méthode privée du Machouilleur pour effectuer l'action Gronder.
     * Gronder augmente le blocage du Machouilleur de 6 et sa force de 3.
     */
    private void gronder() {
        augmenterBlock(6);
        force += 3;
        System.out.println("Le " + nom + " utilise Gronder");
    }

    /**
     * Méthode privée du Machouilleur pour effectuer l'action Charge.
     * Charge inflige des dégâts au héros, augmente le blocage du Machouilleur de 5.
     * 
     * @param hero Le héros contre lequel le Machouilleur charge.
     */
    private void charge(Hero hero) {
        int degats = 7;
        degats = infliger(hero, degats);
        augmenterBlock(5);
        System.out.println("Le " + nom + " utilise Charge et inflige " + degats + " dégâts.");
    }

    /**
     * Méthode privée du Machouilleur pour effectuer l'action Morsure.
     * Morsure inflige des dégâts au héros et augmente la force du Machouilleur de 3.
     * 
     * @param hero Le héros contre lequel le Machouilleur mord.
     */
    private void morsure(Hero hero) {
        int degats = 11;
        degats = infliger(hero, degats);
        force += 3;
        System.out.println("Le " + nom + " utilise Morsure et inflige " + degats + " dégâts.");
    }
}
