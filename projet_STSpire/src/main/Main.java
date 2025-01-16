package main;

import java.io.IOException;
import java.util.Scanner;

import librairies.StdDraw;
import main.Carte.Deck;
import main.Entite.Hero;

/**
 * @author Duchene Cecilia
 * @author Messager Clément
 */

public class Main {
	public static void main(String[] args) throws IOException {
		/*try{
			Jeu jeu= new Jeu();
			jeu.initialDisplay();
			StdDraw.show();  //StdDraw est utilise en mode buffer pour fluidifier l'affichage: utiliser StdDraw.show() pour afficher ce qui est dans le buffer
			while (!jeu.isOver()) {
				jeu.update();
 			}

		} catch (Exception e) {
			System.out.println("Erreur lors de l'execution du jeu : " + e.getMessage());
		}*/
		/*Hero h = new Hero(70,70,0,0);
        Monstre m1= new Monstre("petit slime piquant", 12,12,5,0);
		Monstre m2= new Monstre("petit slime piquant 2", 12,12,5,0);
        Arene arene = new Arene(h,monstres);
        arene.combat();*/
		
		boolean rejouer = true;
		Scanner sc = new Scanner(System.in);
		// Boucle sur les parties de jeu.
		do{
			// Création de l'instance du heros
			Hero hero = new Hero(70,0, null,3,3);
			Deck deckDuHero = new Deck(hero);
			hero.setDeck(deckDuHero);
			// Création d'une instance de la classe Game avec le héros
			Game game = new Game(hero);
			// Lancement de la partie
			game.start();
	
			System.out.println("Voulez-vous rejouer ? (Oui/Non)");
			// Lire la réponse de l'utilisateur
			String reponse = sc.nextLine().toLowerCase();
			if (reponse.equals("non")) {
				System.out.println("Merci d'avoir joué !");
				rejouer = false;
			}
		}
		while(rejouer);
		sc.close();
	}
} 
