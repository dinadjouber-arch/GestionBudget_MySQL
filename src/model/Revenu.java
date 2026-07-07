package model;

import java.time.LocalDate;

public class Revenu {

    private int idRevenu;
    private double montant;
    private LocalDate dateRevenu;
    private String description;

    // Constructeur vide
    public Revenu() {
    }

    // Constructeur sans id
    public Revenu(double montant, LocalDate dateRevenu, String description) {
        this.montant = montant;
        this.dateRevenu = dateRevenu;
        this.description = description;
    }

    // Constructeur complet
    public Revenu(int idRevenu, double montant, LocalDate dateRevenu, String description) {
        this.idRevenu = idRevenu;
        this.montant = montant;
        this.dateRevenu = dateRevenu;
        this.description = description;
    }

    public int getIdRevenu() {
        return idRevenu;
    }

    public void setIdRevenu(int idRevenu) {
        this.idRevenu = idRevenu;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDateRevenu() {
        return dateRevenu;
    }

    public void setDateRevenu(LocalDate dateRevenu) {
        this.dateRevenu = dateRevenu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Revenu{" +
                "idRevenu=" + idRevenu +
                ", montant=" + montant +
                ", dateRevenu=" + dateRevenu +
                ", description='" + description + '\'' +
                '}';
    }
}