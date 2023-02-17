package pharmacie;

import java.util.Objects;

/**
 * Classe qui représente un médicament
 *
 * @author Jayson
 */
public class Medicament {

    /**
     * Identifiant unique du médicament
     */
    private int id;

    /**
     * Code du médicament
     */
    private String code;

    /**
     * Nom du médicament
     */
    private String nom;

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
        this.id = id;
        this.code = code;
        this.nom = nom;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
    }

    /**
     * Retourne l'identifiant unique du médicament
     *
     * @return Identifiant unique du médicament
     */
    public int getId() {
        return id;
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
     * Retourne le nom du médicament
     *
     * @return Nom du médicament
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définir le nom du médicament
     *
     * @param nom Nom
     */
    public void setNom(String nom) {
        this.nom = nom;
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
}
