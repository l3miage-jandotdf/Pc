Débutantes avec Junit, nous nous sommes retrouvées dépourvues devant un problème :

Nous avons créé nos tests et les avons fait marcher.
Mais, surprise générale de fin, la plupart de ces tests qui passent si bien lorsque nous les exécutons à la main
plantent avec les commandes maven... (nullPointerException)

C'est embêtant car l'exportation sur Sonar avec la commande "mvn clean install sonar:sonar" ne marche que si les tests passent !
Nous avons donc commenté les tests problématiques.

C'est possiblement un problème de configuration, ou d'incompréhension via le fonctionnement de maven.

Nous vous présentons nos excuses.