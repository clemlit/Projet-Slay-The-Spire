Duchene Cécilia
Messager Clément


- Description du projet :

Ce projet consiste à reproduire le jeu « Slay The Spire », avec des monstres, un héros, un système de combat et un système de salles bien particulier.


- Système de salles :

Nous avons programmé un système de salle dans lequelle on retrouve des salles de combat où un voir plusieurs monstres vont affronter le héros, des salles de repos dans lesquelles le héros peut regagner des points de vie, et une salle de boss où l’on peut retrouver des monstres plutôt puissants.
On a eu plusieurs soucis sur les salles où lors de la victoire sur la première salle les autres salles se faisaient automatiquement jusqu’à arriver à la salle du boss finale.


- Système de combat :

Les combats se déroulent en tour par tour, c’est-à-dire, le joueur va choisir une ou plusieurs cartes qu’il va utiliser en fonction de l’énergie de son héros, puis c’est au tour des monstres d’effectuer leurs actions suivant les patterns qu’ils ont. 
Le combat prend fin quand le héros est mort ou si tous les monstres sont mort, ensuite si le joueur a gagné, il choisit sa récompense tiré aléatoirement en fonction de sa rareté (Commune 60%, Non-commume 37%, Rare 3%) avant de changer de salle.


- Les cartes :

Dans notre projet, nous avons implémenté toutes les cartes mise à disposition dans le sujet, des cartes communes aux cartes rares. 
Les cartes peuvent avoir des effets et affecter les entités (monstres/hero). Les effets permettent aux cartes d'infliger des dégàts ou bien d'augmenter le bloquage. Ces effets peuvent être aussi des handicaps pour l’entité adverse (differents statuts) et peuvent être également des bonus pour le lanceur de cette carte. 
Une classe deck permet de gérer les cartes pendant et entre les tours dans les salles de combat.
Les cartes ont un coup en energie et des effets
La main est composé de 5 cartes tiré aléatoirement dans le deck melangé.
au début de chaque tour, le joueur complete sa main jusqu'à atteindre 5 cartes.
Lorsque le joueur utilise une carte elle est enlevé de la main pour etre mise dans la défausse, elle ne pourra pas être utiliser dans l'immediat.
Lorsque le deck est vide la défausse est ajouté au deck et melangé à nouveau.
à la fin d’un tour les cartes qui non pas été utilisées resteront dans la main et de nouvelle carte vont venir complété la main pour atteindre les 5 cartes nécessaires.
Certaines cartes une fois utilisées ne vont pas dans la défausse mais dans l'exil. Elles y resteront jusqu’à la fin du combat.
Une fois le combat fini le joueur selectionne une récompense, une nouvelle carte à ajouter au deck.
Enfin au début du prochain combat les cartes de la défausse, de l'exil et de la main sont remisent dans le deck et melangé à l'exception des cartes plaies.
On a eu des difficultés à mettre en place le système de carte et l’implémentation du deck.

- Les monstres :

Pour les monstres nous avons implémenté uniquement les monstres demandés jusqu’au 5.3, c’est-à-dire le petit slime acide, le petit slime piquant et le machouilleur avec leurs patterns et leurs attaques propres à eux. 
Les monstres peuvent être exposés à différent statut positifs et négatifs, dû aux cartes jouer par le héros ou par leur propres attaques.

- Le héros :

Le héros est une entité qui va subir toutes les conséquences des différents choix du joueur. 
Le joueur doit gérer son énergie limitée stratégiquement pour utiliser les cartes efficacement, cette énergie est remise au maximum à chaque debut de tour. 
Le héros peut être impacter par des statut durant les combats qui peuvent l’aider a gagné ou peuvent le faire perdre.

- Les Statuts:

Les differents statuts force, faiblesse, vulnérable, fragile, force demoniaque, sont implémenté et impacte la partie avec des bonus et des malus sur les différentes entité.
Ils sont lancés par les cartes ou les monstres.