# TicTacToe

Ce projet implémente le jeu **Tic Tac Toe** en Java.

## Prérequis

Installez la **version 1.8** de **Java**.\
\
Assurez-vous d'avoir le Java Development Kit (**JDK**) installé.\
\
Clonez ou téléchargez le dépôt sur votre machine locale.

## Compilation

Pour compiler le fichier source *TicTacToe.java*, assurez-vous d'être dans le répertoire "src", puis utilisez la commande suivante dans le terminal :

`javac TicTacToe.java`

## Exécution

Pour exécuter le programme, utilisez la commande suivante dans le terminal :

`java TicTacToe`

Il est également possible de spécifier la taille de la grille en argument lors de l'exécution du programme. Par exemple, pour une grille de taille 5x5, vous pouvez utiliser la commande suivante :

`java TicTacToe 5`

Si aucun argument n'est fourni, la taille par défaut de la grille sera de 3x3.

## Description

Dans Tic Tac Toe, deux joueurs choisissent leurs symboles (X ou O) et jouent à tour de rôle sur une grille carrée. Ici, l'utilisateur joue contre l'ordinateur. Le joeur ayant le symbole 'X' commence. Le premier joueur à aligner le nombre correct de symboles horizontalement, verticalement ou diagonalement gagne. Si la grille est remplie sans gagnant, c’est un match nul. L'utilisateur peut choisir de recommencer une nouvelle partie ou quitter le jeu. La taille de la grille est modifiable selon les préférences (par défaut 3x3).
