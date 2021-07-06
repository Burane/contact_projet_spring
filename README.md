# contact_projet_spring

Ce projet est a rendre dans le cadre d'un module du CNAM, il doit comprendre un site fait avec Spring Boot, VueJS et MongoDB

- API REST et SPA serving : Spring Boot
- Frontend : VueJs
- Base de données : MongoDB

## Installation

Prérequis :
- [Gradle](https://gradle.org/)
- [NodeJs](https://nodejs.org/en/download/)
- [java 16](https://jdk.java.net/16/)
- [MongoDB](https://www.mongodb.com/fr-fr)


Placez-vous dans le répertoire du projet npm :
```bash
cd .\vueJS\contactApp\
```
Installer les dépendances
```npm
npm i
```
Lancer un serveur de développement (si vous n'avez pas envie de build le projet)
```npm
npm run dev
```
Build du projet en mode production pour générer une *single page application*
```npm
npm run build
```
**Configurer [application.properties](src/main/resources/application.properties) afin de convenir à votre BDD**

Lancer le projet Spring grâce à Gradle.

```groovie
gradlew bootRun
```

## Usage

Arrivé dans le dashboard, actualisez la page après chaque modifications (la mise à jour des données ne fonctionne pas pour une raison quelconque) 

## Contributing
Pull requests are welcome.
