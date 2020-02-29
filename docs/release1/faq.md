---
title: FAQ
tags: project,glo2003,h2020
---

# Frequently Asked Questions


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
