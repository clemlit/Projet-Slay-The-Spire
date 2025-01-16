package main.Entite;

/**
 * La classe petitSlimeAcide représente un type de monstre appelé Petit Slime Acide dans le jeu.
 * Il étend la classe Monstre et définit des comportements spécifiques tels que Charge et Lécher.
 */
public class petitSlimeAcide extends Monstre {

    /** Cycle pour alterner entre Charge et Lécher */
    private int cycle = 0;

    /**
     * Constructeur de la classe PetitSlimeAcide.
     * Initialise un Petit Slime Acide avec des points de vie (pv) égaux à 8 et un blocage initial de 0.
     */
    public petitSlimeAcide() {
        super("Petit Slime Acide", 8, 0);
    }

    /**
     * Getter pour le nom du monstre.
     * 
     * @return Le nom du monstre.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode pour effectuer l'action Charge du Petit Slime Acide.
     * Charge inflige des dégâts au héros.
     * 
     * @param hero Le héros contre lequel le Petit Slime Acide charge.
     */
    public void charge(Hero hero) {
        int degats = 3;
        degats = infliger(hero, degats);
        System.out.println("Le " + nom + " utilise Charge et inflige " + degats + " dégâts.");
    }

    /**
     * Méthode pour effectuer l'action Lécher du Petit Slime Acide.
     * Lécher augmente la faiblesse du héros.
     * 
     * @param hero Le héros contre lequel le Petit Slime Acide lèche.
     */
    public void lécher(Hero hero) {
        int faible = 1;
        hero.incrementeFaiblesse(faible);
        System.out.println("Le " + nom + " utilise Lécher et inflige " + faible + " Faible.");
    }

    /**
     * Méthode d'attaque du Petit Slime Acide contre un héros.
     * Alterne entre Charge et Lécher en fonction du cycle.
     * 
     * @param hero Le héros contre lequel le Petit Slime Acide attaque.
     */
    @Override
    public void attaque(Hero hero) {
        if (cycle == 0) {
            charge(hero);
            cycle++;
        } else {
            lécher(hero);
            cycle--;
        }
    }
}
