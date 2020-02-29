---
title: FAQ
tags: project,glo2003,h2020
---

# Foire Aux Questions


## Nouveauté


1. Pouvez-vous fournir plus de détails entourant la ligne du temps à utiliser dans le cadre de la réservation d'un séjour?
  - Voici une figure illustrant le détail d'une réservation
    - **Date d'arrivée:** 26 mars 2020
    - **Nombre de nuits:** 3
    - **Paiement au propriétaire:** 29 mars 2020 à minuit
    - **Date de début du séjour:** 26 mars 2020
    - **Date de fin du séjour:** 29 mars 2020
    ![](https://i.imgur.com/bDHPu30.png)
1. Pouvez-vous fournir plus de détails entourant la ligne du temps à utiliser dans le cadre de l'annulation d'un séjour?
  - Voici une figure illustrant le détail d'une annulation
    - **Date d'arrivée:** 26 mars 2020
    - **Nombre de nuits:** 3
    - **Date de fin de la période d'annulation avec remboursement intégral:** 18 mars 2020 à minuit
    - **Date de fin de la période d'annulation avec remboursement partiel:** 24 mars 2020 à minuit
  ![](https://i.imgur.com/ePvUg9h.png)


## Précédemment


1. Devons-nous retourner un array d'erreurs?
    - Non, nous testerons les cas d'erreur de manière isolée.
    - Notez toutefois qu'il s'agit d'une bonne pratique, dans un contexte professionnel, de retourner en une seule réponse l'ensemble des erreurs de syntaxe de la requête reçue. Cela permet au client de l'API de plus rapidement corriger sa requête et obtenir sa réponse. D'autre part, cela évitera aussi de surcharger notre système avec des requêtes inutiles.
1. Existe-t'il un mécanisme efficient en Spark Java pour faire de la gestion d'erreurs et d'exceptions?
    - Oui, nous vous encourageons fortement à vous renseigner sur l'ensemble des fonctionnalités offertes par Spark.
    - En ce qui concerne la gestion d'erreurs et d'exceptions ainsi que le mapping de celles-ci vers des réponses HTTP, vous pouvez consulter [http://sparkjava.com/documentation#exception-mapping](http://sparkjava.com/documentation#exception-mapping).
1. Avez-vous des pistes pour guider la division de user stories en issues?
    - Identifier les principales portions des user stories
        - Désérialisation de la requête
        - Validation des informations
        - Orchestration des objets du domaine
        - Règles d'affaires
        - Side Effects (Sauvegarde nouveau state)
        - Sérialisation de la réponse
    - S'assurer que les tâches identifiées ont
        - Portée adéquate
        - Complexité raisonnable
        - Durée relativement courte [3 heures - 3 jours]
1. Comment fonctionne les tableaux Kanban des projets GitHub?
    - Vous n'avez qu'à créer des issues. Une fois ces dernières créées, ajouter des cartes dans votre tableaux. Les cartes seront automatiquement créées à partir des issues que vous aurez fait précédemment.
1. Quel est le plus gros danger qui nous guette d'un point de vue architectural?
    - Assurez-vous de ne pas rendre tout votre code static puisque vous devrez l'utiliser au sein de méthodes static Spark.
    - En temps normal, si vous utilisez l'approche par `RouteGroup` telle que montré durant le laboratoire, vous ne devriez pas avoir de problème.
    - L'handicap avec les méthodes statiques est qu'elles sont très difficiles voir impossible à tester.
1. Comment bien gérer les tags de notre repository?
    - Évitez de recréer de nouveaux tags à chaque fois.
    - Ne conservez que les tags qui ont une valeur pour votre équipe/projet.
    - Évitez les tags qui ont une valeur spacio-temporelle
1. Est-il nécessaire de produire un Front-End?
    - Non, vous aurez suffisamment de travail à faire avec la création de votre Front-End.
1. Devons-nous valider le format du `bedNumber` passé en paramètre de route?
    - Non, l'erreur 404 Not Found suffira dans ce scénario.
1. Pouvons-nous persister des objets au niveau de nos `in-memory repositories`?
    - Oui! En fait, c'est exactement le but d'un `repository` d'abstraire les détails de la persistance.
    - Dans un contexte où nous aurions une vraie base de données, cette interface ne changerait pas et nous utiliserions un *Object-Relational Mapping* (ORM) afin de passer de nos objets vers des représentations "primitive" de nos objets.
1. Lorsque nous réservons une chambre, est-ce que la réservation est complète pour la chambre où une autre colonie peut s'y ajouter considérant qu'il reste suffisamment de place?
    - Pour l'instant, non. Les réservations sont toujours complètes.
    - Ceci est comparable à louer une chambre pour 4 personnes dans un hôtel pour y séjourner seul.
1. Quelle erreur devons-nous renvoyer si les packages *SweetTooth* et *AllYouCanDrink* sont offerts sans *Bloodthirsty*?
    - L'erreur à retourner dans ce cas serait `CANT_OFFER_SWEET_TOOTH_PACKAGE`.
1. Avez-vous une suggestion pour bien approcher le problème des filtres multiples afin de lister les lits de la plateforme?
    - Oui, je vous suggère de mettre en place une variante du *Query Object Pattern*.
    - Considérant la multitude de combinaisons possibles, il serait aussi bon d'y intégrer un *Builder* pour construire vos queries.
    - L'idée est d'avoir un objet du domaine qui sera passé au repository avec lequel le repository pourra vérifier si un item qu'il contient correspond aux critères demandés ou non.
    - Liens utiles
        - [https://martinfowler.com/eaaCatalog/queryObject.html](https://martinfowler.com/eaaCatalog/queryObject.html)
        - [https://lostechies.com/chadmyers/2008/08/02/query-objects-with-the-repository-pattern/](https://lostechies.com/chadmyers/2008/08/02/query-objects-with-the-repository-pattern/)
        - [https://codereview.stackexchange.com/questions/114126/query-builder-pattern-with-use-of-fluentapi](https://codereview.stackexchange.com/questions/114126/query-builder-pattern-with-use-of-fluentapi)
