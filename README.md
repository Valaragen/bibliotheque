# Bibliotheque
## Description
### À propos
**Système d'information pour bibliotheque.**  
Le projet respecte une **architecture orientée service**, il est composé des **microservices** (**batch**, **book-api**, **webui**) et des **edges microservices**(**config-server**, **eureka-server**, **zuul-server**). Tous ces microservices sont des projets **spring-boot** créés pour tourner de manière **autonome** sur des **serveurs tomcat**.  
Le projet est **sécurisé** par des appels vers [**keycloak**](https://www.keycloak.org/about.html "À propos de keycloak"), un **serveur de gestion d'identité et d'accès Opensource**.  
### Les microservices
#### Edges microservices
* ##### config-server
  **Serveur de configuration**, il est lié à un [**repository github**](https://github.com/Valaragen/bibliotheque-config), il contient des **informations de configuration** des microservices du projet. **Tous les microservices**, tentent à leurs **lancement**, de récupérer des informations via ce serveur de configuration.  
  **Port** : 9101  
* ##### eureka-server
  **'Registration server'** , toutes les **instances** des microservices du projet **s'inscrivent** sur ce server à leur lancement. Les microservices passent par ce **registre** pour appeler d'autres microservices.  
  **Port** : 9102  
* ##### zuul-server
  **API gateway**, le point d'entrée unique pour les API et microservices back-end(**book-api**)
  **Port** : 9104
#### Microservices de la bibliotheque
* ##### book-api
  **API** permettant de **créer, lire, mettre à jour et/ou supprimer** des **livres**, **exemplaires** et **emprunts**.  
  **L'API contient** des **endpoints protégés**, reservés aux **utilisateurs authentifiés** ou aux **membres du staff**, pour **obtenir l'autorisation** d'appeler ces endpoints il faut **fournir un token d'accès valide** dans l'entête de la requête http.  
  Ce **token d'accès** est fourni par **keycloak** lors d'une **authentification réussie** via un **client autorisé** à lancer un **protocole d'authentification** (**ex: webui**)  
  **Base de données** : Postgresql  
  **Nom de la base de données** : MBOOK_DB  
  **Port** : 9001  
* ##### webui  
  **Site web pour les usagers de la bibliothèque**, permet de **consulter** la liste des livres disponibles à l'emprunt, **effectuer** une demande d'emprunt et de consulter ses emprunts.
  **Port** : 9000  
* ##### batch
  **Service pour les traitements automatisés**, permet d’envoyer des **mails de relance** aux usagers n’ayant pas rendu les livres en fin de période de prêt. L’envoi est automatique à la **fréquence d’un par jour tous les jours à 11h30**.  
  **Frameworks** : Spring Batch, Spring Mail  
  **Base de données** : H2  
  **Port** : 9005  
##### Informations complémentaires
Les appels entre microservices sont gérés avec [OpenFeign](https://spring.io/projects/spring-cloud-openfeign) et son load balancés avec Ribbon, ces deux outils fonctionnent avec Eureka
#### Keycloak
Non integré sur ce repository git, keycloak est necessaire au fonctionnement des microservices de la bibliothèque.  
**Version** : 8.0.1  
**Base de données** : Postgresql  
**Nom de la base de données** : KEYCLOAK_DB  
**Port** : 8080  
   
   
   

