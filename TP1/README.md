# Activité Pratique N°1 - Injection des dépendances
## Partie 1
###  Implémentation de l'interface Metier utilisant le couplage faible
![ImplementationStaticMetier.png](screenshots/ImplementationStaticMetier.png)
### Présentation par instanciation static
Code:

![PresentationStatic.png](screenshots/PresentationStatic.png)

Results:

![ResultPresStatic.png](screenshots/ResultPresStatic.png)
### Présentation par instanciation dynamique
Utilisant la class DaoImpV1:

![fichierConfigDaov1.png](screenshots/fichierConfigDaov1.png)

![ResultDaoV1.png](screenshots/ResultDaoV1.png)

Utilisant la class DaoImpV2:

![fichierConfigDaov2.png](screenshots/fichierConfigDaov2.png)

![ResultDaoV2.png](screenshots/ResultDaoV2.png)
### Présentation par Spring
#### Version XML
Fichier de configuration

![FichierConfigurationXml.png](screenshots/FichierConfigurationXml.png)

![ResultExecution.png](screenshots/ResultExecution.png)
#### Version Annotations
