package main.Entite;

/**
 * La classe abstraite Monstre représente une entité de type monstre dans le jeu.
 * Elle étend la classe abstraite Entite et définit des comportements spécifiques pour les monstres.
 * Les monstres ont un nom et peuvent attaquer le héros.
 */
public abstract class Monstre extends Entite {

    /** Le nom du monstre */
    protected String nom;

    /**
     * Constructeur de la classe Monstre.
     * 
     * @param nom    Le nom du monstre.
     * @param pv     Points de vie initiaux du monstre.
     * @param block  Points de blocage initiaux du monstre.
     */
    public Monstre(String nom, int pv, int block) {
        super(pv, block);
        this.nom = nom;
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
     * Méthode abstraite pour définir l'attaque du monstre sur le héros.
     * 
     * @param hero Le héros contre lequel le monstre attaque.
     */
    public abstract void attaque(Hero hero);

    /**
     * Vérifie si la cible (entité) est morte.
     * 
     * @param cible L'entité cible.
     * @return true si la cible est morte, sinon false.
     */
    public boolean estMort(Entite cible) {
        return cible.pv == 0;
    }

    /**
     * Méthode pour obtenir une représentation sous forme de chaîne de caractères du monstre.
     * 
     * @return La représentation du monstre en tant que chaîne de caractères.
     */
    @Override
    public String toString() {
        String res = nom + " :\nPV : " + pv + "/" + pvMax + "\nBlock : " + block + "\nStatut(s):";
        boolean statut = false;

        if (force != 0) {
            res += "\nForce : " + force;
            statut = true;
        }
        if (faiblesse != 0) {
            res += "\nFaiblesse : " + faiblesse;
            statut = true;
        }
        if (vulnerable != 0) {
            res += "\nVulnérable : " + vulnerable;
            statut = true;
        }
        if (fragile != 0) {
            res += "\nFragile : " + fragile;
            statut = true;
        }

        if (!statut) {
            res += "Aucun";
        }
        return res;
    }

    /**
     * Méthode protégée pour infliger des dégâts au héros.
     * 
     * @param hero      Le héros contre lequel le monstre inflige des dégâts.
     * @param degats    Les dégâts à infliger au héros.
     * @return          Les dégâts infligés.
     */
    protected int infliger(Hero hero, int degats) {
        degats += force;
        if (faiblesse > 0)
            degats = degats * 3 / 4;
        hero.recevoirDegats(degats);
        return degats;
    }
}
