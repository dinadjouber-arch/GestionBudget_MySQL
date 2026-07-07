package model;

import java.time.LocalDate;

public class Depense {

    private int idDepense;
    private double montant;
    private LocalDate dateDepense;
    private String description;

    private Categorie categorie;
    private MoyenPaiement moyenPaiement;

    public Depense() {
    }

    public Depense(int idDepense, double montant, LocalDate dateDepense,
                   String description, Categorie categorie,
                   MoyenPaiement moyenPaiement) {

        this.idDepense = idDepense;
        this.montant = montant;
        this.dateDepense = dateDepense;
        this.description = description;
        this.categorie = categorie;
        this.moyenPaiement = moyenPaiement;
    }

    public int getIdDepense() {
        return idDepense;
    }

    public void setIdDepense(int idDepense) {
        this.idDepense = idDepense;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDateDepense() {
        return dateDepense;
    }

    public void setDateDepense(LocalDate dateDepense) {
        this.dateDepense = dateDepense;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public MoyenPaiement getMoyenPaiement() {
        return moyenPaiement;
    }

    public void setMoyenPaiement(MoyenPaiement moyenPaiement) {
        this.moyenPaiement = moyenPaiement;
    }

    @Override
    public String toString() {
        return description + " - " + montant + " €";
    }
}