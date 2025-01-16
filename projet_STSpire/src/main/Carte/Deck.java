package main.Carte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import main.Entite.Hero;
/**
 * La classe Deck represente le deck et toute les cartes en generaldu Heros dans la partie
 * Il contient le deck, la defausse, la zone d'exil, la main du joueur, et les differentes rareté des carte
 */
public class Deck {
    private ArrayList<Carte> defausse;
    private ArrayList<Carte> exil;
    private ArrayList<Carte> deck;
    private ArrayList<Carte> main;
    private ArrayList<Carte> communes;
    private ArrayList<Carte> nonCommunes;
    private ArrayList<Carte> rares;
    private Carte plaie;

    public Deck(Hero hero) {
        deck = new ArrayList<>();
        defausse = new ArrayList<>();
        exil = new ArrayList<>();
        main = new ArrayList<>();
        communes = new ArrayList<>();
        nonCommunes = new ArrayList<>();
        rares = new ArrayList<>();
        plaie = Carte.Plaie(hero);
        initialiserDeck(hero);
        melangerDeck();
        completeMain();; // Piocher 5 cartes au début
    }

    /**
     * Initialise le Deck du heros ainsi que toutes les raretés des carte
     * @param hero hero associé au deck
     */
    private void initialiserDeck(Hero hero) {
        // Ajouter des cartes communes
        communes.add(Carte.Frappe(hero));
        communes.add(Carte.Defense(hero));
        communes.add(Carte.Heurt(hero));
        communes.add(Carte.MemePasMal(hero));
        communes.add(Carte.VagueDeFer(hero));
        communes.add(Carte.FrappeDuPommeau(hero));
        communes.add(Carte.FrappeDouble(hero));
        communes.add(Carte.Enchainement(hero));
        communes.add(Carte.EpeeBoomerang(hero));
        communes.add(Carte.Manchette(hero));
        communes.add(Carte.Plaquage(hero));

        // Ajouter des cartes non-communes
        nonCommunes.add(Carte.Saignee(hero));
        nonCommunes.add(Carte.Hemokinesie(hero));
        nonCommunes.add(Carte.Uppercut(hero));
        nonCommunes.add(Carte.VoleeDeCoups(hero));
        nonCommunes.add(Carte.VoirRouge(hero));
        nonCommunes.add(Carte.Enflammer(hero));
        nonCommunes.add(Carte.Desarement(hero));
        nonCommunes.add(Carte.OndeDeChoc(hero));
        nonCommunes.add(Carte.Tenacite(hero));
        
        // Ajouter des cartes rares
        rares.add(Carte.Gourdin(hero));
        rares.add(Carte.Invincible(hero));
        rares.add(Carte.Offrande(hero));
        rares.add(Carte.FormeDemoniaque(hero));

        ArrayList<Carte> toutesLesCartes = new ArrayList<>();
        toutesLesCartes.addAll(communes);
        toutesLesCartes.addAll(nonCommunes);
        toutesLesCartes.addAll(rares);
        Collections.shuffle(toutesLesCartes);
        deck.addAll(toutesLesCartes.subList(0, 10)); // ajout 10 carte dans le deck
    }

    /**
     * Reinitialisation du deck en debut du combat
     * Les cartes de la main, la defausse et l'exil vont dans le deck pour le nouveau combat.
     * On enlève les cartes plaies
     */
    public void debutCombat(){
        while(main.remove(plaie));
        deck.addAll(main);
        main.clear();
        deck.addAll(exil);
        exil.clear();
        melangerDeck();
    }

    /**
     * Melange du deck la defausse va à nouveau formé le deck
     */
    public void melangerDeck() {
        deck.addAll(defausse);
        defausse.clear();
        Collections.shuffle(deck);
    }

    /**
     * recupère la taille du deck
     * @return la taille du deck
     */
    public int deckSize() {
        return deck.size();
    }

    /**
     * Getter de la Main
     * @return Retourne la main
     */
    public ArrayList<Carte> getMain(){
        return main;
    }

    /**
     * Recupèrer un élément de la main
     * @param index     index de la carte à recuperer dans la main
     * @return          La carte rechercher, Null sinon
     */
    public Carte get(int index) {
        if (index >= 0 && index < main.size()) {
            return main.get(index);
        } else {
            return null;
        }
    }

    /**
     * Procédure de fin de tour si le deck et vide on recupère la defausse pour former le nouveau deck
     */
    public void finDeTour(){
        if(deck.size() == 0)
            melangerDeck();
    }

    /**
     * Defausse ou Exile la Carte en fonction de sa spécificiter
     * @param carte     Carte à defausser ou exiler
     */
    public void defausse(Carte carte) {
        if(carte.getExil()){
            exil.add(carte);
        }
        else{
            defausse.add(carte);
        }
        main.remove(carte);
    }

    /**
     * Pioche une carte
     * @return  une carte si possible, null sinon
     */
    public Carte piocherCarte() {
        if (!deck.isEmpty()) {
            // Retirez la carte du haut du deck (première carte dans la liste)
            Carte cartePiochee = deck.remove(0);
            main.add(cartePiochee);
            return cartePiochee;
        } else {
            // Retournez null si le deck est vide
            return null;
        }
    }
    
    /**
     * Pioche jusqu'à obtenir 5 cartes ou que le deck soit vide
     */
    public void completeMain(){
        while(main.size() < 5){
            Carte carte = piocherCarte();
            if(carte == null){
                break;
            }
        }
    }

    /**
     * Pioche nb Carte
     * @param nb    nombre de carte à piocher
     */
    public void piocherCartes(int nb){
        for(int i = 0; i < nb; i++){
            if (piocherCarte() == null){
                System.out.println("Impossible de piocher " + (nb - i) + " carte(s)");
                break;
            }
        }
    }
    
    /**
     * Methode pour le tirage de la récompense
     * @return      une carte aléatoire selon sa rareté
     */
    public Carte piocherRareté(){
        Random random = new Random();
        int pourcentage = random.nextInt(100);
        if(pourcentage < 60 && !communes.isEmpty()){
            return communes.get(random.nextInt(communes.size()));
        }
        else if(pourcentage < 97 && !nonCommunes.isEmpty()){
            return nonCommunes.get(random.nextInt(nonCommunes.size()));
        }
        else if(!rares.isEmpty()){
            return rares.get(random.nextInt(rares.size()));
        }
        return null;
    }

    /**
     * Ajout d'une carte dans le decks
     * @param carte     Carte à ajouter
     * @return          Vrai si l'ajout est effectué, Faux sinon
     */
    public boolean add(Carte carte){
        return deck.add(carte);
    }

    /**
     * Ajout nb carte plaie dans la main
     * @param nb    Nombre de carte plaie à ajouter dans la main
     */
    public void ajouterPlaies(int nb){
        for(int i = 0; i < nb; i++)
            main.add(plaie);
    }
}