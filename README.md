# Gestion de la Distribution et Production d'Énergie dans une Ville

![Énergie Ville](earth_view_elec.jpg)

L'énergie électrique est un élément vital pour le fonctionnement de nos sociétés modernes. La demande en électricité augmente constamment, tandis que les sources d'énergie évoluent, avec une demande croissante pour des sources d'énergie propres et renouvelables. Pour faire face à ces enjeux actuels, il est essentiel de prévoir la consommation et la production d'énergie de manière efficace et de gérer la distribution d'électricité dans les villes de manière intelligente. C'est là que notre projet intervient.

## Introduction

Ce projet de simulation de distribution et production d'énergie dans une ville offre un outil puissant pour comprendre et modéliser les besoins en énergie, la production et la distribution dans une ville. L'objectif est de permettre aux utilisateurs de simuler divers scénarios, de prévoir les besoins énergétiques et d'adapter les ressources de production en conséquence.

## Utilisation du Projet

### Configuration des Simulations

Pour utiliser ce projet, l'utilisateur doit modifier les valeurs des attributs de la classe `App` située au-dessus de la fonction `main`. Il existe huit simulations possibles, permettant de modéliser différents aspects de la consommation et de la production d'électricité :

1. Simulation de la consommation et production d'électricité d'une ville pour une journée.
2. Simulation de la consommation et production d'électricité d'une ville pour une année.
3. Simulation de la consommation d'électricité d'un appareil électrique pour une journée.
4. Simulation de la consommation d'électricité d'un appareil électrique pour une année.
5. Simulation de la consommation d'électricité d'un foyer pour une journée.
6. Simulation de la consommation d'électricité d'un foyer pour une année.
7. Simulation de la production d'électricité d'une centrale électrique pour une journée.
8. Simulation de la production d'électricité d'une centrale électrique pour une année.

### Personnalisation des Simulations

Pour chaque simulation, l'utilisateur peut personnaliser plusieurs aspects :

- Choix de la date de début de la simulation.
- Sélection de la météo automatique (basée sur la date) ou personnalisation de la météo.
- Choix de l'affichage des données, que ce soit dans le terminal ou dans un fichier au format CSV.

### Simulation Ville

Pour la simulation de la ville, l'utilisateur doit saisir le nombre de foyers et les centrales électriques disponibles. Il peut également choisir si les centrales s'allument en fonction du besoin ou restent constamment allumées.

### Simulation Appareil Électrique

Pour la simulation d'un appareil électrique, l'utilisateur peut choisir l'appareil électrique en remplaçant celui initialement sélectionné par un appareil disponible dans l'archive `code.ElectronicDevices.ElecDevice`. Il doit respecter la syntaxe appropriée.

### Simulation Centrale Électrique (Producteur)

Pour la simulation d'une centrale électrique, l'utilisateur peut choisir la centrale électrique en remplaçant celle initialement sélectionnée par une centrale disponible dans l'archive `code.Producer.AllTypeOfProducer`. La syntaxe doit être respectée.

### Simulation Foyer (Consommateur)

Cette simulation crée un foyer moyen de manière aléatoire, en se basant sur les valeurs moyennes des appareils présents dans les foyers en France.

Ce projet permet de mieux comprendre et anticiper les besoins en énergie d'une ville, la production d'électricité nécessaire et la gestion de la distribution d'énergie. Il offre aux utilisateurs un outil puissant pour prendre des décisions éclairées en matière de gestion de l'énergie.

