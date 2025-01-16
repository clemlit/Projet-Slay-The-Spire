package main.Rooms;
import java.util.Random;
import java.util.Scanner;

import main.Carte.Carte;
import main.Entite.Entite;
import main.Entite.Hero;
import main.Entite.Monstre;
import main.Entite.petitSlimeAcide;
import main.Entite.petitSlimePiquant;

/**
 * La classe CombatRoom représente une salle de combat dans le jeu.
 * Elle contient un héros et un tableau de monstres.
 */
public class CombatRoom {

    /** Le héros de la salle de combat */
    Hero hero;

    /** Tableau de monstres dans la salle de combat */
    Monstre[] monstres;

    /**
     * Constructeur de la classe CombatRoom.
     * 
     * @param hero Le héros entrant dans la salle de combat.
     */
    public CombatRoom(Hero hero) {
        this.hero = hero;
    }

    /**
     * Méthode pour entrer dans la salle de combat.
     * Génère aléatoirement le nombre de monstres et les initialise.
     * Démarre ensuite le combat en appelant la méthode combat().
     * 
     * @param sc Scanner pour la saisie utilisateur.
     * @return true si le héros survit et gagne, false sinon.
     */
    public boolean enter(Scanner sc) {
        hero.debutCombat();
        System.out.println("Vous entrez dans une salle de combat !");
        Random random = new Random();
        int nombreDeMontres = random.nextInt(3) + 1; // Génère un nombre entre 1 et 3
        System.out.println(nombreDeMontres + " monstre(s) apparaît(ent)!");
        monstres = new Monstre[nombreDeMontres];

        // Initialiser les monstres ici
        for (int i = 0; i < monstres.length; i++) {
            switch (random.nextInt(2)) {
                case 0:
                    monstres[i] = new petitSlimePiquant();
                    break;
                case 1:
                    monstres[i] = new petitSlimeAcide();
                    break;
            }
        }

        if (!tousMonstresMorts() && !hero.estMort()) {
            return combat(sc);
        }
        return true;
    }

    /**
     * Vérifie si tous les monstres dans la salle sont morts.
     * 
     * @return true si tous les monstres sont morts, false sinon.
     */
    public boolean tousMonstresMorts() {
        for (Monstre monstre : monstres) {
            if (monstre.getPv() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si le combat est en cours.
     * 
     * @return true si le héros est en vie ou s'il reste des monstres en vie, false sinon.
     */
    public boolean combatenCours() {
        if (hero.getPv() > 0) {
            return true;
        }
        for (Monstre monstre : monstres) {
            if (monstre.getPv() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode pour gérer le combat dans la salle.
     * Affiche les informations, permet au joueur de choisir des actions et gère les attaques des monstres.
     * 
     * @param sc Scanner pour la saisie utilisateur.
     * @return true si le héros survit et gagne, false sinon.
     */
    public boolean combat(Scanner sc) {
        while (combatenCours()) {
            hero.debutDeTour();
            int choixAction;
            do {
                System.out.println(hero);
                System.out.println();
                System.out.println("Monstre(s) :");
                for (int i = 0; i < monstres.length; i++) {
                    System.out.println((i + 1) + ": " + monstres[i]);
                    System.err.println();
                }
                System.out.println("Que voulez-vous faire ?");
                System.out.println("1. Choisir une carte");
                System.out.println("2. Finir le tour");

                choixAction = sc.nextInt();

                System.out.println();
                switch (choixAction) {
                    case 1:
                        choisirCartesJoueur(sc);
                        break;

                    case 2:
                        // Finir le tour
                        break;

                    default:
                        System.out.println("Choix invalide.");
                        break;
                }
            } while (choixAction != 2 && hero.getEnergie() != 0 && !hero.getDeck().getMain().isEmpty());

            // Attaque des monstres uniquement si le joueur a choisi de finir son tour ou n'a plus d'énergie
            System.out.println("Au tour des monstres");

            for (Monstre monstre : monstres) {
                if (monstre.getPv() > 0) {
                    monstre.attaque(hero);

                    if (hero.estMort()) {
                        System.out.println("Le(s) Monstre(s) a/ont gagné");
                        return false;
                    }
                }
            }
            System.out.println();
            if (tousMonstresMorts()) {
                System.out.println("Le héros a gagné");
                return true;
            }

            // Réinitialise l'énergie du héros à la fin de son tour et toutes les statistiques
            hero.finDeTour();
            for (Monstre monstre : monstres) {
                monstre.finDeTour();
            }
        }
        return true;
    }

    /**
     * Méthode pour permettre au joueur de choisir des cartes à jouer pendant son tour.
     * Affiche les cartes disponibles dans la main, permet au joueur de choisir une cible et joue la carte.
     * 
     * @param sc Scanner pour la saisie utilisateur.
     */
    private void choisirCartesJoueur(Scanner sc) {
        System.out.println("Choisissez vos cartes tant que vous avez assez d'énergie ou choisissez '2' pour finir le tour.");
        while (hero.getEnergie() > 0 && !hero.getDeck().getMain().isEmpty()) {
            System.out.println("Énergie restante : " + hero.getEnergie());
            System.out.println();

            // Afficher les cartes disponibles dans la main
            System.out.println("Cartes disponibles dans la main :");
            int i = 0;
            for (Carte carte : hero.getDeck().getMain()) {
                System.out.println((++i) + ": " + carte);
            }
           
            System.out.println();

            System.out.println("Nombres de carte restante dans le deck : " + hero.getDeck().deckSize());

            System.out.println("Choisissez une carte à jouer (1-" + hero.getDeck().getMain().size() + ") ou '0' pour arrêter :");
            int choixCarte = sc.nextInt();
            System.out.println();

            if (choixCarte >= 1 && choixCarte <= hero.getDeck().getMain().size()) {
                // Choix d'une carte parmi celles disponibles dans le deck
                Carte carteChoisie = hero.getDeck().get(choixCarte - 1);

                // Vérification du ciblage de la carte
                // Aucun ciblage ne concerne pas les monstres
                if (carteChoisie.getCible() == null) {
                    if (hero.jouerCarte(carteChoisie, null))
                        hero.getDeck().defausse(carteChoisie);
                }
                // Ciblage unique
                else if (carteChoisie.getCible().equals("unique")) {
                    // Demander le monstre à attaquer
                    System.out.println("Quel monstre voulez-vous attaquer ?");
                    for (int j = 0; j < monstres.length; j++) {
                        System.out.println((j + 1) + ": " + monstres[j]);
                        System.err.println();
                    }
                    int choixMonstre = sc.nextInt();
                    System.out.println();

                    if (choixMonstre >= 1 && choixMonstre <= monstres.length && monstres[choixMonstre - 1].getPv() > 0) {
                        Monstre monstreCible = monstres[choixMonstre - 1];
                        // Jouer la carte d'attaque avec le monstre choisi
                        Entite cible[] = { monstreCible };
                        if (hero.jouerCarte(carteChoisie, cible))
                            hero.getDeck().defausse(carteChoisie);

                    } else {
                        System.out.println("Numéro de monstre invalide. Choisissez à nouveau.");
                    }

                }
                // Ciblage aléatoire
                else if (carteChoisie.getCible().equals("aleatoire")) {
                    Random rand = new Random();
                    Entite cible[] = { monstres[rand.nextInt(monstres.length)] };
                    if (hero.jouerCarte(carteChoisie, cible))
                        hero.getDeck().defausse(carteChoisie);
                }
                // Ciblage de tous les monstres
                else {
                    if (hero.jouerCarte(carteChoisie, monstres.clone()))
                        hero.getDeck().defausse(carteChoisie);
                }
            } else if (choixCarte == 0) {
                // Arrêter de choisir des cartes
                break;
            } else {
                System.out.println("Choix invalide. Choisissez à nouveau.");
            }
        }

        if (hero.getEnergie() <= 0) {
            System.out.println("Énergie restante : " + "0");
        }

        if (hero.getDeck().getMain().isEmpty()) {
            System.out.println("Main vide");
            System.out.println();
        }
    }
}