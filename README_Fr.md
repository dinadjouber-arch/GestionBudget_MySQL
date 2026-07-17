# Gestionnaire de Budget

<img src="images/accueil.png" alt="Aperçu de l'application" width="850">

Une application de bureau développée en **Java Swing** permettant de gérer un budget personnel et de suivre ses dépenses grâce à une interface graphique intuitive connectée à une base de données **MySQL**.

---

# Fonctionnalités

*  Créer et modifier un budget.
*  Ajouter une nouvelle dépense.
*  Modifier une dépense existante.
*  Supprimer une dépense.
*  Organiser les dépenses par catégorie.
*  Gérer les moyens de paiement.
*  Afficher des statistiques sur les dépenses.
*  Calculer automatiquement le budget restant.
*  Afficher une alerte lorsque le budget devient négatif.
*  Enregistrer toutes les données dans une base de données MySQL.

---

# Technologies utilisées

* Java 17
* Java Swing
* JDBC
* MySQL
* IntelliJ IDEA

---

# Base de données

L'application s'appuie sur une base de données relationnelle **MySQL** composée des tables suivantes :

* `budget`
* `depense`
* `categorie`
* `moyen_paiement`
* `revenu`

Ces tables sont reliées entre elles afin d'assurer une gestion cohérente et efficace des données.

---


## Tableau de bord et Statistiques

*Interface Principale avec Visualisation des dépenses et de l'utilisation du budget.*

<img src="images/accueil.png" alt="Accueil" width="700">


---

## Gestion du budget

*Affichage du budget actuel et du montant restant.*

<img src="images/budget(vide).png" alt="Accueil" width="700">

---

## Liste des dépenses
*Formulaire permettant d'ajouter une nouvelle dépense et Tableau affichant toutes les dépenses enregistrées.*

<img src="images/depense(vide).png" alt="Accueil" width="700">

<img src="images/depense.png" alt="Accueil" width="700">

---

## Gestion des catégories

*Interface permettant d'ajouter et de gérer les catégories de dépenses.*

<img src="images/categorie(vide).png" alt="Accueil" width="700">
<img src="images/categorie.png" alt="Accueil" width="700">

---

## Gestion des moyens de paiement

*Interface permettant de gérer les différents moyens de paiement.*
<img src="images/paiement(vide).png" alt="Accueil" width="700">
<img src="images/paiement.png" alt="Accueil" width="700">



---

## Base de données vide

*État de l'application lorsqu'aucune dépense n'a encore été enregistrée.*

<img src="images/accueil(vide).png" alt="Accueil" width="700">

---

## Budget négatif

*Exemple de l'alerte affichée lorsque les dépenses dépassent le budget disponible.*

<img src="images/budgetnegatif.png" alt="Accueil" width="700">

---

# Améliorations futures

* Gestion des revenus.
* Filtrage des dépenses par mois.
* Recherche de dépenses.
* Export des données (PDF/Excel).
* Authentification des utilisateurs.
* Mode sombre.

---

# Installation

1. Cloner le dépôt.
2. Créer la base de données MySQL.
3. Importer le script SQL.
4. Configurer les informations de connexion à la base de données.
5. Lancer le projet.

---

# Auteur

Développé par **Pinguin** dans le cadre d'un projet universitaire afin de mettre en pratique la programmation Java, la programmation orientée objet, le développement d'interfaces graphiques avec Swing, JDBC et la gestion de bases de données MySQL.

