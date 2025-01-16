package main.Entite;

/**
 * La classe PetitSlimePiquant représente un type de monstre appelé Petit Slime Piquant dans le jeu.
 * Il étend la classe Monstre et définit un comportement spécifique, à savoir la charge.
 */
public class petitSlimePiquant extends Monstre {

    /**
     * Constructeur de la classe PetitSlimePiquant.
     * Initialise un Petit Slime Piquant avec des points de vie (pv) égaux à 12 et un blocage initial de 0.
     */
    public petitSlimePiquant() {
        super("Petit Slime Piquant", 12, 0);
    }

    /**
     * Méthode pour effectuer l'action Charge du Petit Slime Piquant.
     * Charge inflige des dégâts au héros.
     * 
     * @param hero Le héros contre lequel le Petit Slime Piquant charge.
     */
    public void charge(Hero hero) {
        int degats = 5;
        degats = infliger(hero, degats);
        System.out.println("Le " + nom + " utilise Charge et inflige " + degats + " dégâts.");
    }

    /**
     * Méthode d'attaque du Petit Slime Piquant contre un héros.
     * Utilise la charge comme unique attaque.
     * 
     * @param hero Le héros contre lequel le Petit Slime Piquant attaque.
     */
    @Override
    public void attaque(Hero hero) {
        charge(hero);
    }
}
