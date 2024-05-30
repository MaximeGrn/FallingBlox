# Falling Blox : Une implémentation Java de Tetris

Ce projet est une implémentation du jeu classique Tetris, nommé ici "Falling Blox",  développée en Java en utilisant la version 11 du JDK. Il suit le modèle d'architecture MVC (Modèle-Vue-Contrôleur) pour une organisation claire et une maintenance facilitée du code.

## Fonctionnalités de base :

* Le jeu propose les pièces classiques de Tetris, incluant les tétrominos I et O.
* Le joueur peut contrôler les pièces avec la souris pour les déplacer latéralement et les faire tourner.
* La gravité fait descendre les pièces automatiquement à une vitesse constante.
* Les pièces s'empilent au fond du puits, formant le "Tas".
* Le jeu détecte et gère les collisions entre les pièces et le tas, ainsi que les sorties du puits.

## Extensions implémentées :

1. **Ajout de pièces:** Le jeu a été étendu pour inclure les 5 tétrominos restants (T, L, J, Z, S), enrichissant ainsi le gameplay. L'usine de pièces a été modifiée pour générer ces nouvelles pièces, en mode aléatoire ou cyclique.
2. **Suppression des lignes complètes:** Le jeu détecte et supprime les lignes complètes du tas, faisant descendre les lignes supérieures et récompensant le joueur.
3. **Score:** Le jeu calcule et affiche le score du joueur, qui augmente en fonction du nombre de lignes complétées simultanément.
4. **Nombre de lignes complétées:** Le jeu affiche le nombre total de lignes que le joueur a complétées.
5. **Niveau et vitesse:** Le jeu implémente un système de niveaux qui augmente en fonction du score du joueur. La vitesse de la gravité augmente à chaque niveau, rendant le jeu progressivement plus difficile.
6. **Fin de partie:** La partie se termine lorsque le tas atteint le haut du puits, indiquant que le joueur a perdu.
7. **Pop-up "Game Over":** Un pop-up s'affiche à la fin de la partie, indiquant au joueur qu'il a perdu. Un bouton "Rejouer" permet de relancer une nouvelle partie.

## Technologies utilisées :

* Java 11
* Swing (pour l'interface graphique)
* JUnit 5 (pour les tests unitaires)

## Instructions d'exécution :

Le jeu peut être lancé via la classe principale `FallingBloxVersion1`. Des arguments optionnels en ligne de commande permettent de configurer le tas initial du jeu. 

## Contributions :

Les contributions et les suggestions d'améliorations sont les bienvenues ! 

## Auteur :
Maxime Guerin

## Date :
Mai 2024
