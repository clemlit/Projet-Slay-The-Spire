- Description du projet :

Ce projet consiste à reproduire le jeu « Slay The Spire », avec des monstres, un héro, un système de combat et un système de salles bien particulier.


- Système de salles :

Nous avons programmé un système de salle dans lesquelles on retrouve des salles de combat où un voir plusieurs monstres vont affronter le héro, des salles de repos dans lesquelles le héro peut regagner des points de vie, et une salle de boss où l’on peut retrrouver des monstres plutôt puissant. 


- Système de combat :

Les combats se déroulent en tour par tour, c’est-à-dire, le joueur va choisir une ou plusieurs cartes en fonction de l’énergie de son héro puis c’est au tour des monstres d’effectuer leurs actions suivant les patterns qu’ils ont. 
Le combat prend fin quand le héro est mort ou si tous les monstres sont mort, ensuite le joueur choisit sa récompense avant de changer de salle si il a gagné.


- Les cartes :

Dans notre projet, nous avons implementé toutes les cartes mise a disposition dans le sujet, des cartes communes aux cartes rare. 
Les cartes peuvent avoir des effets qui durent plusieurs tours, ces effets peut être des handicaps pour l’entité adverse a celle qui l’a lancé, mais ces effets peuvent être également des bonus pour le lanceur de cette carte. 
Une classe deck permet de gérer les cartes pendant et entre les tours dans les salles de combat. 
Ce deck est composé de 5 cartes au début de chaque tour, lorsque le joueur utilise une carte elle est enlever du deck car elle ne peut pas être rejouer durant le même tour, à la fin d’un tour les cartes qui non pas été utilisées resteront dans le deck et de nouvelle carte vont venir complété ce deck pour atteindre les 5 cartes necessaire.


-Les monstres :

Pour les monstres nous avons implémenté uniquement les monstres demandé jusqu’au 5.3, c’est-à-dire le petit slime acide, le petit slime piquant et le machouilleur avec leur pattern et leurs attaques propres à eux. 
Les monstres peuvent être exposé a différent effets positif et negatif, dû aux cartes jouer par le hero ou par leur propres attaques.





-Le héro :

Le héro est une entité qui va subir toutes les conséquences des différents choix du joueur. 
Il a de l’énergie qui va permettre d’ajouter de la stratégie, pour savoir quelles cartes jouer durant un tour, cette énergie est remise au maximum a chaque tour. 
Le héro peut être impacter par des effets durant les combats qui peuvent l’aider a gagné ou peuvent le faire perdre.
