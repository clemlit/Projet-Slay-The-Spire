/** package principal */
package main;

import java.io.File;

import librairies.StdDraw;
import ressources.Affichage;
import ressources.AssociationTouches;
import ressources.Config;

/**
 * La classe Jeu représente le jeu principal.
 * Elle gère la boucle principale du jeu, l'affichage, l'initialisation, et la mise à jour.
 */
public class Jeu {

    /**
     * Constructeur de la classe Jeu.
     * Initialise le jeu en lançant la boucle principale.
     * 
     * @throws Exception En cas d'erreur lors de l'exécution du jeu.
     */
    public Jeu() throws Exception {
    }

    /**
     * Méthode pour démarrer le jeu.
     * Gère la boucle principale du jeu.
     */
    public void start() {
        while (!isOver()) {
            display();
            initialDisplay();
            update();
            StdDraw.show();
            StdDraw.pause(20); // Pause pour ralentir la boucle principale
        }
    }

    /**
     * Méthode pour vérifier si le jeu est terminé.
     * 
     * @return true si le jeu est terminé, false sinon.
     */
    public boolean isOver() {
        return false;
    }

    /**
     * Méthode pour afficher l'état actuel du jeu.
     * Affiche le fond, le héros, l'énergie, et le nombre de cartes dans la pioche, la défausse, et l'exil.
     */
    public void display() {
        StdDraw.clear();

        // Affichage du fond
        String pathBackground = "pictures" + File.separator + "background.jpg";
        Affichage.image(0, Config.X_MAX, 0, Config.Y_MAX, pathBackground);

        // Affichage du héros
        String pathHeros = "pictures" + File.separator + "Ironclad.png";
        Affichage.image(Config.X_MAX * 0.2 - 183, Config.X_MAX * 0.2 + 183, Config.Y_MAX * 0.5 - 130, Config.Y_MAX * 0.5 + 130, pathHeros);

        // Affichage de l'énergie et le nombre de cartes de la pioche, de la défausse et en l'exil
        Affichage.texteGauche(0, Config.Y_MAX - 20, "Pioche : 10");
        Affichage.texteGauche(0, Config.Y_MAX - 45, "Energie : 3/3");
        Affichage.texteDroite(Config.X_MAX, Config.Y_MAX - 20, "Defausse : 0");
        Affichage.texteDroite(Config.X_MAX, Config.Y_MAX - 45, "Exil : 0");

        StdDraw.show(); // Montre à l'écran les changements demandés
    }

    /**
     * Méthode pour l'affichage initial du jeu.
     * Initialise les touches, la configuration et active le double buffering.
     */
    public void initialDisplay() {
        AssociationTouches.init();
        Config.init();
        StdDraw.enableDoubleBuffering(); // Rend l'affichage plus fluide : tout draw est mis en buffer et ne s'affiche qu'au prochain StdDraw.show();
        display();
    }

    /**
     * Méthode pour mettre à jour l'état du jeu.
     * Gère les entrées de l'utilisateur et effectue les actions correspondantes.
     */
    public void update() {
        String toucheSuivante = AssociationTouches.trouveProchaineEntree(); // Cette fonction boucle jusqu'à la prochaine entrée de l'utilisateur
        if (toucheSuivante.equals("Entrée")) {
            // TODO: déplacer le curseur vers le haut
            System.out.println("Entrée");
            display();
        }
        // TODO: Ajouter les autres touches utiles avec la classe AssociationTouches
        else {
            System.out.println("Autre touche");
            display();
        }
    }
}

