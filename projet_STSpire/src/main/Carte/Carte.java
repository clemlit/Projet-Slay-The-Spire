package main.Carte;

import java.util.ArrayList;

import main.Entite.Entite;
import main.Entite.Hero;

/**
 * Classe des Carte à jouer par le héros
 */

public class Carte {
    private int energieCost;
    private String nom;
    private ArrayList<Effet> effets;
    private Hero hero;
    private String type;
    private boolean exil;
    private String cible;

    /**
     * Contructeur des cartes.
     * @param energieCost   Coup en Energie de la carte
     * @param nom           Nom de la carte
     * @param hero          Hero associé à la carte
     * @param type          Type de carte (Attaque / Defense / Passive (inflige statut))
     * @param exil          Vrai si la carte doit aller dans l'exil après utilisateion, Faux sinon
     * @param cible         Ciblage de la carte (unique / toutes / aléatoire) null si la carte ne cible aucune entité.
     */
    private Carte(int energieCost, String nom, Hero hero, String type, boolean exil, String cible) {
        this.energieCost = energieCost;
        this.nom = nom;
        this.effets = new ArrayList<Effet>();
        this.hero = hero;
        this.type = type;
        this.exil = exil;
        this.cible = cible;
    }

    /**
     * Getter de exil
     * @return valeur de exil
     */
    public boolean getExil(){
        return exil;
    }
    /**
     * Getter de Cible
     * @return valeur de cible
     */
    public String getCible(){
        return cible;
    }

    /**
     * Ajout d'un effet à la carte
     * @param effet     Effet de la carte à ajouter
     * @return          Vrai si l'ajout réussi, Faux sinon
     */
    public boolean addEffet(Effet effet){
        return this.effets.add(effet);
    }

    /**
     * Methode pour jouer les cartes applique tout les effets de la carte
     * @param cibles Tableau contenant toute les cibles de la carte
     */
    public void jouerCarte(Entite cibles[]){
        for (Effet effet : effets) {
            switch (effet.getEffet()) {
                case "degats":
                    degats(cibles, effet.getNb());
                    break;
                case "blocage":
                    blocage(effet.getNb());
                    break;
                case "vulnerable":
                    vulnerable(cibles, effet.getNb());
                    break;
                case "pioche":
                    pioche(effet.getNb());
                    break;
                case "faiblesse":
                    faiblesse(cibles, effet.getNb());
                    break;
                case "energie":
                    energie(effet.getNb());
                    break;
                case "perdPv":
                    perdPv(effet.getNb());
                    break;
                case "formeDemoniaque":
                    formeDemoniaque(effet.getNb());
                    break;
                case "gainForce":
                    gainForce(effet.getNb());
                    break;
                case "baisseForce":
                    baisseForce(cibles, effet.getNb());
                    break;
                case "plaie":
                    plaie(effet.getNb());
                    break;
        
                default:
                    System.err.println("ERREUR : EFFET ENTREE " + effet.getEffet());
                    break;
            }
        }
    }

    /**
     * Methode du calcule et d'application de l'effet degats infligé par la carte
     * @param cible     Toutes les cible de la carte
     * @param degats    Dégats de la carte
     */
    private void degats(Entite cible[], int degats){
        for (Entite entite : cible) {
            // Plaquage
            if (degats < 0){
                degats = hero.getBlock();
            }
            degats += hero.getForce();
            if(hero.getFaiblesse() > 0)
                degats = degats * 3 / 4;
            entite.recevoirDegats((degats) );
        }
    }

    /**
     * Methode d'application de l'effet Blocage
     * @param blocage   nombre de point de block à appliquer
     */
    private void blocage(int blocage){
        hero.augmenterBlock(blocage);
    }

    /**
     * Methode d'application de l'effet vulnerable
     * @param cible     Toutes les cible de la carte
     * @param nb        increment du statut vulnérable
     */
    private void vulnerable(Entite cible[], int nb){
        for (Entite entite : cible){
            entite.incrementeVulnerable(nb);
        }
    }

    /**
     * Methode d'application de l'effet Pioche
     * @param nb    nombre de carte à piocher
     */
    private void pioche(int nb){
        hero.getDeck().piocherCartes(nb);
    }

    /**
     * Methode d'application de l'effet Faiblesse
     * @param cible     Toutes les cible de la carte
     * @param nb        incrément du statut faiblesse
     */
    private void faiblesse(Entite cible[], int nb){
        for (Entite entite : cible){
            entite.incrementeFaiblesse(nb);
        }
    }

    /**
     * Methode d'application de l'effet Energie
     * @param nb        Incrément de l'energie du heros
     */
    private void energie(int nb){
        hero.incrementeEnergie(nb);
    }

    /**
     * Methode d'application de l'effet PerdPv
     * @param nb    nombre de pv perdu
     */
    private void perdPv(int nb){
        hero.perdPv(nb);
    }

    /**
     * Methode d'application de l'effet FormeDemoniaque
     * @param nb    increment du statut forme Demoniaque
     */
    private void formeDemoniaque(int nb){
        hero.incrementeFormeDemoniaque(nb);
    }

    /**
     * Methode d'application de l'effet gainForce
     * @param nb    increment du statut force
     */
    private void gainForce(int nb){
        hero.incrementeForce(nb);
    }

    /**
     * Methode d'application de l'effet baisseForce
     * @param cible     Toutes les cibles de la carte
     * @param nb
     */
    private void baisseForce(Entite cible[], int nb){
        for (Entite entite : cible){
            entite.incrementeForce(-nb);
        }
    }

    /**
     * Methode d'application de l'effet plaie
     * @param nb    nombre de carte plaie à ajouter à la main
     */
    private void plaie(int nb){
        hero.getDeck().ajouterPlaies(nb);
    }

    /**
     * Getter du cout en energie de la carte
     */
    public int getEnergieCost() {
        return energieCost;
    }

    @Override
    public String toString() {
        String res = type + " - " + nom + " - Cout : " + energieCost;
        if(cible != null)
            res += " - Ciblage : " + cible;
        res += " - Exil : " + (exil ? "Oui" : "Non");
        for (Effet effet : effets) {
            res += "\n" + effet;
        }
        return res;
    }

    /**
     * Methode de création de la carte Frappe
     * @param hero      Heros associé à la carte
     * @return          carte Frappe
     */
    public static Carte Frappe(Hero hero) {
        Carte frappe = new Carte(1,"Frappe", hero ,"Attaque", false, "unique");
        frappe.addEffet(new Effet(6, "degats"));
        return frappe;
    }

    /**
     * Methode de création de la carte Defense
     * @param hero      Heros associé à la carte
     * @return          carte Défense
     */
    public static Carte Defense(Hero hero){
        Carte defense = new Carte(1,"Défense",hero, "Defense", false, null);
        defense.addEffet(new Effet(5, "blocage"));
        return defense;
    }

    /**
     * Methode de création de la carte Heurt
     * @param hero      Heros associé à la carte
     * @return          carte Heurt
     */
    public static Carte Heurt(Hero hero){
        Carte heurt = new Carte(2,"Heurt",hero, "Attaque", false, "unique");
        heurt.addEffet(new Effet(8, "degats"));
        heurt.addEffet(new Effet(2, "vulnerable"));
        return heurt;
    }

    /**
     * Methode de création de la carte Meme Pas Mal
     * @param hero      Heros associé à la carte
     * @return          carte Meme pas mal
     */
    public static Carte MemePasMal(Hero hero){
        Carte memePasMal = new Carte(1,"Même pas mal",hero, "Defense", false, null);
        memePasMal.addEffet(new Effet(8, "blocage"));
        memePasMal.addEffet(new Effet(1, "pioche"));
        return memePasMal;
    }

    /**
     * Methode de création de la carte Vague de Fer
     * @param hero      Heros associé à la carte
     * @return          carte Vague de Fer
     */
    public static Carte VagueDeFer(Hero hero){
        Carte vagueDeFer = new Carte(1,"Vague de fer",hero, "Defense", false, "unique");
        vagueDeFer.addEffet(new Effet(5, "blocage"));
        vagueDeFer.addEffet(new Effet(5, "degats"));
        return vagueDeFer;
    }

    
    /**
     * Methode de création de la carte Frappe du Pommeau
     * @param hero      Heros associé à la carte
     * @return          carte Frappe du Pommeau
     */ 
    public static Carte FrappeDuPommeau(Hero hero){
        Carte frappeDuPommeau = new Carte(1,"Frappe du Pommeau",hero, "Attaque", false, "unique");
        frappeDuPommeau.addEffet(new Effet(9, "degats"));
        frappeDuPommeau.addEffet(new Effet(1, "pioche"));
        return frappeDuPommeau;
    }

    /**
     * Methode de création de la carte ...
     * @param hero     Heros associé à la carte
     * @return         carte Frappe Double
     */
    public static Carte FrappeDouble(Hero hero){
        Carte frappeDuPommeau = new Carte(1,"Frappe Double", hero, "Attaque", false, "unique");
        frappeDuPommeau.addEffet(new Effet(5, "degats"));
        frappeDuPommeau.addEffet(new Effet(5, "degats"));
        return frappeDuPommeau;
    }

    /**
     * Methode de création de la carte Enchainement
     * @param hero     Heros associé à la carte
     * @return carte Enchainement
     */
    public static Carte Enchainement(Hero hero){
        Carte Enchainement = new Carte(1,"Enchainement", hero, "Attaque", false, "toutes");
        Enchainement.addEffet(new Effet(8, "degats"));
        return Enchainement;
    }

    /**
     * Methode de création de la carte Epee Boomerang
     * @param hero      Heros associé à la carte
     * @return          carte Epee Boomerang
     */
    public static Carte EpeeBoomerang(Hero hero){
        Carte epeeBoomerang = new Carte(1,"Epee Boomerang", hero, "Attaque", false, "aleatoire");
        epeeBoomerang.addEffet(new Effet(3, "degats"));
        epeeBoomerang.addEffet(new Effet(3, "degats"));
        epeeBoomerang.addEffet(new Effet(3, "degats"));
        return epeeBoomerang;
    }

    /**
     * Methode de création de la carte Manchette
     * @param hero      Heros associé à la carte
     * @return          carte Manchette
     */
    public static Carte Manchette(Hero hero){
        Carte manchette = new Carte(2,"Manchette", hero, "Attaque", false, "unique");
        manchette.addEffet(new Effet(12, "degats"));
        manchette.addEffet(new Effet(2, "faiblesse"));
        return manchette;
    }

    /**
     * Methode de création de la carte Plaquage
     * @param hero     Heros associé à la carte
     * @return carte Plaquage
     */
    public static Carte Plaquage(Hero hero){
        Carte Plaquage = new Carte(1,"Plaquage", hero, "Attaque", false, "unique");
        Plaquage.addEffet(new Effet(-1, "degats"));
        return Plaquage;
    }

    /**
     * Methode de création de la carte Saignée
     * @param hero     Heros associé à la carte
     * @return carte Saignée 
     */
    public static Carte Saignee(Hero hero){
        Carte saignee = new Carte(0,"Saignée", hero, "Passive", false, null);
        saignee.addEffet(new Effet(3, "perdPv"));
        saignee.addEffet(new Effet(2, "energie"));
        return saignee;
    }

    /**
     * Methode de création de la carte Hémokinésie
     * @param hero     Heros associé à la carte
     * @return carte Hémokinésie
     */
    public static Carte Hemokinesie(Hero hero){
        Carte hemokinesie = new Carte(1,"Hémokinésie", hero, "Attaque", false, "unique");
        hemokinesie.addEffet(new Effet(2, "perdPv"));
        hemokinesie.addEffet(new Effet(15, "degats"));
        return hemokinesie;
    }

    /**
     * Methode de création de la carte Uppercut
     * @param hero     Heros associé à la carte
     * @return carte Uppercut
     */
    public static Carte Uppercut(Hero hero){
        Carte Uppercut = new Carte(2,"Uppercut", hero, "Attaque", false, "unique");
        Uppercut.addEffet(new Effet(13, "degats"));
        Uppercut.addEffet(new Effet(1, "faiblesse"));
        Uppercut.addEffet(new Effet(1, "vulnerable"));
        return Uppercut;
    }

    /**
     * Methode de création de la carte Volee de Coups
     * @param hero     Heros associé à la carte
     * @return carte Volee de Coups
     */
    public static Carte VoleeDeCoups(Hero hero){
        Carte voleeDeCoups = new Carte(1,"Volée de Coups", hero, "Attaque", true, "unique");
        voleeDeCoups.addEffet(new Effet(2, "degats"));
        voleeDeCoups.addEffet(new Effet(2, "degats"));
        voleeDeCoups.addEffet(new Effet(2, "degats"));
        voleeDeCoups.addEffet(new Effet(2, "degats"));
        return voleeDeCoups;
    }

    /**
     * Methode de création de la carte carte Voir Rouge
     * @param hero     Heros associé à la carte
     * @return carte Voir Rouge
     */
    public static Carte VoirRouge(Hero hero){
        Carte voirRouge = new Carte(1,"Voir Rouge", hero, "Passive", true, null);
        voirRouge.addEffet(new Effet(2, "energie"));
        return voirRouge;
    }

    /**
     * Methode de création de la carte Enflammer
     * @param hero     Heros associé à la carte
     * @return         carte Enflammer
     */
    public static Carte Enflammer(Hero hero){
        Carte enflammer = new Carte(1,"Enflammer", hero, "Passive", true, null);
        enflammer.addEffet(new Effet(2, "gainForce"));
        return enflammer;
    }
    
    /**
     * Methode de création de la carte Desarmement
     * @param hero     Heros associé à la carte
     * @return         carte Desarmement
     */
    public static Carte Desarement(Hero hero){
        Carte desarmement = new Carte(1,"Désarement", hero, "Passive", true, "unique");
        desarmement.addEffet(new Effet(2, "baisseForce"));
        return desarmement;
    }

    /**
     * Methode de création de la carte Onde de choc
     * @param hero     Heros associé à la carte
     * @return         carte Onde de choc
     */
    public static Carte OndeDeChoc(Hero hero){
        Carte ondeDeChoc = new Carte(2,"Onde de Choc", hero, "Passive", true, "toutes");
        ondeDeChoc.addEffet(new Effet(3, "faiblesse"));
        ondeDeChoc.addEffet(new Effet(3, "vulnerable"));
        return ondeDeChoc;
    }

    /**
     * Methode de création de la carte Tenacite
     * @param hero     Heros associé à la carte
     * @return         carte Tenacite
     */
    public static Carte Tenacite(Hero hero){
        Carte tenacite = new Carte(1,"Tenacite", hero, "Defense", false, null);
        tenacite.addEffet(new Effet(2, "plaie"));
        tenacite.addEffet(new Effet(15, "blocage"));
        return tenacite;
    }

    
    /**
     * Methode de création de la carte Gourdin
     * @param hero     Heros associé à la carte
     * @return         carte Gourdin
     */
    public static Carte Gourdin(Hero hero){
        Carte gourdin = new Carte(3,"Gourdin", hero, "Attaque", false, "unique");
        gourdin.addEffet(new Effet(32, "degats"));
        return gourdin;
    }

    /**
     * Methode de création de la carte Invincible
     * @param hero     Heros associé à la carte
     * @return         carte Invincible
     */
    public static Carte Invincible(Hero hero){
        Carte invincible = new Carte(2,"Invincible", hero, "Defense", true, null);
        invincible.addEffet(new Effet(30, "blocage"));
        return invincible;
    }

    /**
     * Methode de création de la carte Offrande
     * @param hero     Heros associé à la carte
     * @return         carte Offrande
     */
    public static Carte Offrande(Hero hero){
        Carte offrande = new Carte(0,"Offrande", hero, "Passive", true, null);
        offrande.addEffet(new Effet(6, "perdPv"));
        offrande.addEffet(new Effet(2, "energie"));
        offrande.addEffet(new Effet(3, "pioche"));
        return offrande;
    }

    /**
     * Methode de création de la carte Forme Demonique
     * @param hero     Heros associé à la carte
     * @return         carte Forme Demonique
     */
    public static Carte FormeDemoniaque(Hero hero){
        Carte formeDemoniaque = new Carte(0,"Forme Démoniaque", hero, "Passive", true, null);
        formeDemoniaque.addEffet(new Effet(2, "formeDemoniaque"));
        return formeDemoniaque;
    }
    /**
     * Methode de création de la carte Plaie
     * @param hero     Heros associé à la carte
     * @return         carte Plaie
     */
    public static Carte Plaie(Hero hero){
        return new Carte(-1,"Plaie", hero, "Pollution", false, null);
    }
}
