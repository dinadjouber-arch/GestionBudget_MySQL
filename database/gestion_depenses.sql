CREATE DATABASE gestion_depenses;
USE gestion_depenses;


CREATE TABLE categorie (
    id_categorie INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    description VARCHAR(255)
);


CREATE TABLE moyen_paiement (
    id_paiement INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL UNIQUE
);


CREATE TABLE depense (
    id_depense INT AUTO_INCREMENT PRIMARY KEY,
    montant DECIMAL(10,2) NOT NULL,
    date_depense DATE NOT NULL,
    description VARCHAR(255),
    id_categorie INT NOT NULL,
    id_paiement INT NOT NULL,

    FOREIGN KEY (id_categorie)
        REFERENCES categorie(id_categorie)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    FOREIGN KEY (id_paiement)
        REFERENCES moyen_paiement(id_paiement)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);


CREATE TABLE budget (
    id_budget INT AUTO_INCREMENT PRIMARY KEY,
    montant DECIMAL(10,2) NOT NULL,
    mois INT NOT NULL,
    annee INT NOT NULL
);


CREATE TABLE revenu (
    id_revenu INT AUTO_INCREMENT PRIMARY KEY,
    montant DECIMAL(10,2) NOT NULL,
    date_revenu DATE NOT NULL,
    description VARCHAR(255)
);



