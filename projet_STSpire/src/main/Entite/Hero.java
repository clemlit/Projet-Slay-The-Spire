package main.Entite;

import main.Carte.Carte;
import main.Carte.Deck;
/**
 * La classe Hero représente le personnage jouable dans le jeu. Il étend la classe abstraite Entite.
 * Un héros possède des points de vie, un blocage, de l'énergie, un deck de cartes, et des caractéristiques spécifiques.
 * Cette classe contient des méthodes pour gérer le début du combat, le début du tour, la régénération en Rest Room, 
 * jouer une carte du deck, et d'autres fonctionnalités liées au héros.
 */
public class Hero extends Entite {

    /** Deck de cartes du héros */
    private Deck deck;

    /** Quantité d'énergie actuelle du héros */
    private int energie;

    /** Quantité maximale d'énergie du héros */
    private int energieMax;

    /** Niveau de forme démoniaque du héros */
    private int formeDemoniaque = 0;

    /**
     * Constructeur de la classe Hero.
     * 
     * @param pv          Points de vie initiaux du héros.
     * @param block       Points de blocage initiaux du héros.
     * @param deck        Deck de cartes du héros.
     * @param energieMax  Quantité maximale d'énergie du héros.
     * @param energie     Quantité initiale d'énergie du héros.
     */
    public Hero(int pv, int block, Deck deck, int energieMax, int energie) {
        super(pv, block);
        this.deck = deck;
        this.energie = energie;
        this.energieMax = energieMax;
    }

    /**
     * Initialise les caractéristiques du héros au début du combat.
     */
    public void debutCombat() {
        force = 0;
        faiblesse = 0;
        vulnerable = 0;
        fragile = 0;
        formeDemoniaque = 0;
        deck.debutCombat();
    }

    /**
     * Initialise les caractéristiques du héros au début de son tour.
     */
    public void debutDeTour() {
        force += formeDemoniaque;
        energie = energieMax;
        deck.completeMain();
    }

    /**
     * Getter pour la quantité d'énergie actuelle du héros.
     * 
     * @return La quantité d'énergie actuelle du héros.
     */
    public int getEnergie() {
        return energie;
    }

    /**
     * Méthode appelée à la fin de chaque tour pour gérer les effets temporaires et le deck.
     */
    @Override
    public void finDeTour() {
        super.finDeTour();
        deck.finDeTour();
    }

    /**
     * Getter pour le deck de cartes du héros.
     * 
     * @return Le deck de cartes du héros.
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Setter pour le deck de cartes du héros.
     * 
     * @param deck Le nouveau deck de cartes du héros.
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    /**
     * Méthode de régénération lors de l'arrivée en Rest Room.
     * 
     * @return La quantité de régénération effectuée.
     */
    public int regene() {
        int regene = (int) (pvMax * 0.3);
        pv = Math.min(pvMax, pv + regene);
        return regene;
    }

    /**
     * Méthode pour jouer une carte spécifique du deck.
     * 
     * @param carte   La carte à jouer.
     * @param cibles  Les entités cibles de la carte.
     * @return true si la carte a été jouée avec succès, sinon false.
     */
    public boolean jouerCarte(Carte carte, Entite cibles[]) {
        if (carte.getEnergieCost() < 0) {
            System.out.println("Vous ne pouvez pas jouer de plaie");
            return false;
        }

        if (energie >= carte.getEnergieCost()) {
            energie -= carte.getEnergieCost();
            carte.jouerCarte(cibles);
            return true;
        }

        System.out.println("Vous n'avez pas assez d'énergie pour jouer cette carte.");
        return false;
    }

    /**
     * Vérifie si le héros est mort.
     * 
     * @return true si le héros est mort, sinon false.
     */
    public boolean estMort() {
        return pv == 0;
    }

    /**
     * Incrémente la quantité d'énergie du héros.
     * 
     * @param energie La quantité d'énergie à ajouter.
     */
    public void incrementeEnergie(int energie) {
        this.energie += energie;
    }

    /**
     * Incrémente le niveau de forme démoniaque du héros.
     * 
     * @param nb Le montant à ajouter au niveau de forme démoniaque.
     */
    public void incrementeFormeDemoniaque(int nb) {
        this.formeDemoniaque += nb;
    }

    /**
     * Méthode pour réduire les points de vie du héros.
     * 
     * @param pv Les points de vie à retirer.
     */
    public void perdPv(int pv) {
        this.pv -= pv;
        if (this.pv < 0) {
            this.pv = 0;
        }
    }

    /**
     * Méthode pour obtenir une représentation sous forme de chaîne de caractères du héros.
     * 
     * @return La représentation du héros en tant que chaîne de caractères.
     */
    @Override
    public String toString() {
        String res = "Hero :\nPV : " + pv + "/" + pvMax + "\nBlock : " + block + "\nEnergie :" + energie + "\nStatut(s):";
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
        if (formeDemoniaque != 0) {
            res += "\nForme Demonique : " + formeDemoniaque;
            statut = true;
        }

        if (!statut) {
            res += "\nAucun";
        }
        return res;
    }
}
