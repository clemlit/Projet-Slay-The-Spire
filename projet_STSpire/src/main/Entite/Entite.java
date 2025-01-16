package main.Entite;

/**
 * La classe abstraite Entite représente une entité générique dans un contexte de jeu. 
 * Les entités ont des points de vie (pv), un blocage, des statuts de force, faiblesse, vulnérabilité et fragilité.
 * Cette classe contient des méthodes pour manipuler ces caractéristiques telles que recevoir des dégâts, augmenter le blocage, etc.
 * Les entités peuvent être de différents types, et les classes dérivées doivent implémenter la méthode toString().
 */
public abstract class Entite {

    /** Points de vie actuels de l'entité */
    protected int pv;

    /** Points de vie maximum de l'entité */
    protected int pvMax;

    /** Points de blocage de l'entité */
    protected int block;

    /** Force de l'entité */
    protected int force = 0;

    /** Faiblesse de l'entité */
    protected int faiblesse = 0;

    /** Vulnérabilité de l'entité */
    protected int vulnerable = 0;

    /** Fragilité de l'entité */
    protected int fragile = 0;

    /**
     * Constructeur de la classe Entite.
     * 
     * @param pv    Points de vie initiaux de l'entité.
     * @param block Points de blocage initiaux de l'entité.
     */
    public Entite(int pv, int block) {
        this.pv = pv;
        this.pvMax = pv;
        this.block = block;
    }

    /**
     * Getter pour les points de vie actuels.
     * 
     * @return Les points de vie actuels de l'entité.
     */
    public int getPv() {
        return pv;
    }

    /**
     * Getter pour la force de l'entité.
     * 
     * @return La force de l'entité.
     */
    public int getForce() {
        return force;
    }

    /**
     * Getter pour la faiblesse de l'entité.
     * 
     * @return La faiblesse de l'entité.
     */
    public int getFaiblesse() {
        return faiblesse;
    }

    /**
     * Getter pour les points de blocage de l'entité.
     * 
     * @return Les points de blocage de l'entité.
     */
    public int getBlock() {
        return block;
    }

    /**
     * Méthode pour recevoir des dégâts.
     * 
     * @param degats Les dégâts à infliger à l'entité.
     */
    public void recevoirDegats(int degats) {
        if (vulnerable > 0)
            degats += degats / 2;
        block -= degats;
        if (block < 0) {
            pv += block;
            block = 0;
            if (pv < 0)
                pv = 0;
        }
    }

    /**
     * Méthode pour augmenter le blocage de l'entité.
     * 
     * @param block Le blocage à ajouter à l'entité.
     */
    public void augmenterBlock(int block) {
        if (fragile > 0)
            block = block * 3 / 4;
        if (block > 0) {
            this.block += block;
        }
    }

    /**
     * Méthode pour augmenter la force de l'entité.
     * 
     * @param nb La quantité à ajouter à la force de l'entité.
     */
    public void incrementeForce(int nb) {
        force += nb;
    }

    /**
     * Méthode pour augmenter la faiblesse de l'entité.
     * 
     * @param nb La quantité à ajouter à la faiblesse de l'entité.
     */
    public void incrementeFaiblesse(int nb) {
        faiblesse += nb;
    }

    /**
     * Méthode pour augmenter la vulnérabilité de l'entité.
     * 
     * @param nb La quantité à ajouter à la vulnérabilité de l'entité.
     */
    public void incrementeVulnerable(int nb) {
        vulnerable += nb;
    }

    /**
     * Méthode pour augmenter la fragilité de l'entité.
     * 
     * @param nb La quantité à ajouter à la fragilité de l'entité.
     */
    public void incrementeFragile(int nb) {
        fragile += nb;
    }

    /**
     * Méthode appelée à la fin de chaque tour pour gérer les effets temporaires.
     */
    public void finDeTour() {
        if (faiblesse > 0)
            faiblesse--;
        if (vulnerable > 0)
            vulnerable--;
        if (fragile > 0)
            fragile--;
    }

    /**
     * Méthode abstraite à implémenter dans les classes dérivées.
     * Elle renvoie une représentation sous forme de chaîne de caractères de l'entité.
     * 
     * @return La représentation de l'entité en tant que chaîne de caractères.
     */
    @Override
    public abstract String toString();
}