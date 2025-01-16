package main;

import java.util.ArrayList;
import java.util.Scanner;

import main.Carte.Carte;
import main.Entite.Hero;
import main.Rooms.CombatRoom;
import main.Rooms.FinalRoom;
import main.Rooms.RestRoom;

/**
 * La classe Game représente une partie du jeu composée de différentes salles.
 * Elle gère le déroulement de la partie en faisant entrer le héros dans chaque salle.
 */
public class Game {

    /** Tableau d'objets représentant les différentes salles du jeu */
    Object[] rooms;

    /** Le héros de la partie */
    Hero hero;

    /** Scanner pour la saisie utilisateur */
    private Scanner scanner;

    /**
     * Constructeur de la classe Game.
     * Initialise la partie avec le héros et les salles dans un ordre spécifié.
     * 
     * @param hero Le héros de la partie.
     */
    public Game(Hero hero) {
        this.hero = hero;
        this.scanner = new Scanner(System.in);  // Initialiser le scanner dans le constructeur

        // Initialiser les salles dans l'ordre spécifié
        rooms = new Object[]{
            new CombatRoom(hero), new CombatRoom(hero), new RestRoom(hero), new CombatRoom(hero),
            new CombatRoom(hero), new CombatRoom(hero), new RestRoom(hero), new CombatRoom(hero),
            new CombatRoom(hero), new CombatRoom(hero), new RestRoom(hero), new CombatRoom(hero),
            new CombatRoom(hero), new RestRoom(hero), new FinalRoom(hero)
        };
    }

    /**
     * Méthode pour commencer la partie.
     * Fait entrer le héros dans chaque salle et gère le déroulement de la partie.
     */
    public void start() {
        for (Object room : rooms) {
            if (room instanceof FinalRoom) {
                if (((FinalRoom) room).enter(this.scanner)) {
                    System.out.println("Félicitation ! Vous avez Gagné !");
                } else {
                    System.out.println("Vous avez perdu.");
                }
            } else if (room instanceof CombatRoom) {
                if (((CombatRoom) room).enter(this.scanner)) {
                    System.out.println("Le combat est terminé. Choisissez une récompense. ");

                    // Tirage et choix de la récompense
                    ArrayList<Carte> recompenses = new ArrayList<>();
                    for (int i = 0; i < 3; i++) {
                        Carte carte = hero.getDeck().piocherRareté();
                        if (carte != null) {
                            recompenses.add(carte);
                            System.out.println((i + 1) + ": " + carte.toString());
                        }
                    }

                    int choixCarte = 0;
                    do {
                        System.out.println(("1-3 pour choisir, 0 pour ne rien prendre"));
                        choixCarte = scanner.nextInt();
                    } while (choixCarte > 3 || choixCarte < 0);

                    if (choixCarte > 0)
                        hero.getDeck().add(recompenses.get(choixCarte - 1));
                } else {
                    System.out.println("Vous avez perdu.");
                    break;
                }
            } else {
                ((RestRoom) room).enter();
            }
        }
    }
}

