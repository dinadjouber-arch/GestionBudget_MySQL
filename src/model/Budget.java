package model;

public class Budget {

    private int idBudget;
    private double montant;
    private int mois;
    private int annee;

    // Constructeur vide
    public Budget() {
    }

    // Constructeur sans id
    public Budget(double montant, int mois, int annee) {
        this.montant = montant;
        this.mois = mois;
        this.annee = annee;
    }

    // Constructeur complet
    public Budget(int idBudget, double montant, int mois, int annee) {
        this.idBudget = idBudget;
        this.montant = montant;
        this.mois = mois;
        this.annee = annee;
    }

    public int getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(int idBudget) {
        this.idBudget = idBudget;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "idBudget=" + idBudget +
                ", montant=" + montant +
                ", mois=" + mois +
                ", annee=" + annee +
                '}';
    }
}
