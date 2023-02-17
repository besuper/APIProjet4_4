package pharmacie;

import java.util.Objects;

public class Medicament {

    private int id;
    private String code, nom, description;
    private double prixUnitaire;

    public Medicament(int id, String code, String nom, String description, double prixUnitaire) {
        this.id = id;
        this.code = code;
        this.nom = nom;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicament that = (Medicament) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
