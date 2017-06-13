# Project Manager

```
C'est une application web de gestion des projets.
```

## Fonctionalitées

Cette application permet au utilisateur de créer des projets.
Chaque projet contient des taches à réaliser.
Chaque tache est assignée à un utilisateur.

## Les Utilisateurs

Les utilisateurs ont des fonctionalitées différents selon leurs roles.
Un **collaborateur** a le droit de confirmer ses taches et voir les projets dont il contribue.
Un **manager** peut créer des projets, des taches et les assignée à des collaborateurs, de plus il peut approuver les taches réalisées par le collaborateur.
Un **administrateur** a le droit de lire les messages envoyées au site et d'assigner le role **manager** à un collaborateur.

## Remarque sur les roles

On suppose que l'**administrateur** est un manager et collaborateur.
De même un **manager** est un collaborateur.
(L'inverse n'est pas vrai.)

## Outils de developpement

* Java 8
* Gradle
* Spring boot 1.5.3.RELEASE
* H2 database
* Sring data jpa
* Spring MVC
* Thymeleaf
* Spring security

