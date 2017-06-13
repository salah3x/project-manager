# Project Manager

```
Ce projet est une application web de gestion des projets.
```

## Fonctionalitées

Cette application permet au utilisateur de créer des projets.
Chaque projets contient des tache à réaliser.
Chaque tache est assignée à un utilisateur.

## Les Utilisateurs

Les utilisateurs ont des fonctionalitées différents selon ces roles;
**Un collaborateur** a le droit de confirmer les taches et voir les projets dont il contribue.
**Un manager** peut créer des projets, des taches et les assignée à **un collaborateur**, de plus il peut approuver les taches réalisée par le collaborateur.
**Un administrateur** a le droit de lire les messages envoyées au site et de donner le role **manager** à un collaborateur.

## Remarque sur les roles

On a supposé que l'**administrateur** est un manager et collaborateur.
De même un **manager** est un collaborateur.
(L'inverse n'est pas vrai.)

## Outils de developpement

*Java 8
*Spring boot 1.5.3.RELEASE
*H2 database
*Sring data jpa
*Spring MVC
*Thymeleaf
*Spring security

