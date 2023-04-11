package pharmacie.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe qui représente un médecin
 *
 * @author Jayson
 * @see Prescription
 */
public class Medecin {

    /**
     * Identifiant unique du médecin
     */
    private final int id;

    /**
     * Matricule
     */
    private String matricule;

    /**
     * Nom
     */
    private String nom;

    /**
     * Prénom
     */
    private String prenom;

    /**
     * Numéro de téléphone
     */
    private String tel;

    /**
     * Liste des prescriptions données
     */
    private List<Prescription> prescription = new ArrayList<>();

    /**
     * Constructeur de la classe Medecin
     *
     * @param id        Identifiant unique du médecin
     * @param matricule Son matricule
     * @param nom       Son nom
     * @param prenom    Son prénom
     * @param tel       Le numéro de téléphone
     */
    public Medecin(int id, String matricule, String nom, String prenom, String tel) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
    }

    /**
     * Retourne l'identifiant unique du médecin
     *
     * @return Identifiant unique du médecin
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le matricule
     *
     * @return Le matricule
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Définir le matricule du médecin
     *
     * @param matricule Matricule
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Retourne le nom du médecin
     *
     * @return Nom du médecin
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définir le nom du médecin
     *
     * @param nom Nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prénom du médecin
     *
     * @return Prénom du médecin
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définir le prénom du médecin
     *
     * @param prenom Prénom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne le numéro de téléphone
     *
     * @return Numéro de téléphone
     */
    public String getTel() {
        return tel;
    }

    /**
     * Définir le numéro de téléphone
     *
     * @param tel Numéro de téléphone
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * Retourne la liste des prescriptions données
     *
     * @return Liste des prescriptions données
     */
    public List<Prescription> getPrescription() {
        return prescription;
    }

    /**
     * Définir la liste des prescriptions
     *
     * @param prescription Liste de prescriptions
     */
    public void setPrescription(List<Prescription> prescription) {
        this.prescription = prescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medecin medecin = (Medecin) o;
        return Objects.equals(matricule, medecin.matricule);
    }

    @Override
    public String toString() {
        return "Medecin{" +
                "id=" + id +
                ", matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricule);
    }
}
