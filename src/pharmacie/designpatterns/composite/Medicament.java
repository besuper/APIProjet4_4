package pharmacie.designpatterns.composite;

import java.util.Objects;

/**
 * Classe qui représente un médicament
 *
 * @author Jayson
 */
public class Medicament extends Element {


    /**
     * Code du médicament
     */
    private String code;

    /**
     * Description du médicament
     */
    private String description;

    /**
     * Prix unitaire
     */
    private double prixUnitaire;

    /**
     * Constructeur de la classe Medicament
     *
     * @param id           Identifiant unique
     * @param code         Code du médicament
     * @param nom          Nom
     * @param description  Description
     * @param prixUnitaire Prix unitaire
     */
    public Medicament(int id, String code, String nom, String description, double prixUnitaire) {
        super(id, nom);
        this.code = code;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
    }

    /**
     * Retourne le code du médicament
     *
     * @return Code du médicament
     */
    public String getCode() {
        return code;
    }

    /**
     * Définir le code du médicament
     *
     * @param code Code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Retourne la description du médicament
     *
     * @return Description du médicament
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définir la description du médicament
     *
     * @param description Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retourne le prix unitaire du médicament
     *
     * @return Prix unitaire du médicament
     */
    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    /**
     * Définir le prix unitaire du médicament
     *
     * @param prixUnitaire Prix unitaire
     */
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

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + super.getId() +
                ", code='" + code + '\'' +
                ", nom='" + super.getNom() + '\'' +
                ", description='" + description + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                '}';
    }
}
