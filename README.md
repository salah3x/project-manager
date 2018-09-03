# Project Manager

```
C'est une application web de gestion des projets.
```

## Fonctionalitées

Cette application permet aux utilisateurs de créer des projets.

Chaque projet contient des taches à réaliser.

Chaque tache est assignée à un collaborateur.

## La base de donnée

La base de donneé utilisée est **H2 Database** (in memory database).
La classe **Fakedata.java** dans le package config.databaseseed s'occupe d'initialiser, au démmarage de l'application, la base de donnée avec des enregistrements aléatoire en utilisant DIUS/javafaker(https://github.com/DiUS/java-faker).

## Les Utilisateurs (Rôles)

Les utilisateurs ont des fonctionalitées différents selon leurs roles.
- Un **collaborateur** a le droit de confirmer ses taches et voir les projets dont il contribue.
- Un **manager** peut créer des projets, des taches et les assignée à des collaborateurs, de plus il peut approuver les taches réalisées par le collaborateur.
- Un **administrateur** a le droit de lire les messages envoyées au site et d'assigner le role **manager** à un collaborateur.

## Remarque sur les rôles

On suppose que l'**administrateur** est un manager et collaborateur.

De même un **manager** est un collaborateur (L'inverse n'est pas vrai).

## Outils de developpement

* Java 8
* Gradle
* Spring boot 1.5.3.RELEASE
* H2 database
* Sring data jpa
* Spring MVC
* Thymeleaf
* Spring security

## Installation

* Télécharger ou cloner le code source depuis git repository
```
git clone http://github.com/salah3x/project-manager
```
* Naviguer vers le dossier téléchargé
```

cd [Path to project-manager]
```
* Démarer l'application :
```
./gradlew bootRun
```
ou éxécuter directement le jar
```
./gradlew bootRepackage
java -jar build/libs/project-manager-0.0.1-SNAPSHOT.jar
```
* Allez à http://localhost:8080/

**Remarque 1:**
La commande ./gradlew necessite une connexion internet et peut pendre du temps pour télécharger les dépendeces de l'application.

**Remarque 2:**
Pour démarer l'application sous windows utiliser le fichier gradlew.bat au lieu du gradlew.

