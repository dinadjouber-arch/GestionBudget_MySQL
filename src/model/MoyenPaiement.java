package model;

public class MoyenPaiement {

    private int idPaiement;
    private String nom;

    // Constructeur vide
    public MoyenPaiement() {
    }

    // Constructeur sans id
    public MoyenPaiement(String nom) {
        this.nom = nom;
    }

    // Constructeur complet
    public MoyenPaiement(int idPaiement, String nom) {
        this.idPaiement = idPaiement;
        this.nom = nom;
    }

    public int getIdPaiement() {
        return idPaiement;
    }

    public void setIdPaiement(int idPaiement) {
        this.idPaiement = idPaiement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}