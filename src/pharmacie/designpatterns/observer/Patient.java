package pharmacie.designpatterns.observer;

import pharmacie.metier.Prescription;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe qui représente un patient
 *
 * @author Jayson
 * @see Prescription
 */
public class Patient extends Subject{

    /**
     * Identifiant unique du patient
     */
    private final int id;

    /**
     * NSS du patient
     */
    private String nss;

    /**
     * Nom
     */
    private String nom;

    /**
     * Prénom
     */
    private String prenom;

    private String tel;

    /**
     * Date de naissance
     */
    private LocalDate dateNaissance;

    /**
     * Liste des prescriptions reçues
     */
    private List<Prescription> prescription = new ArrayList<>();

    /**
     * Constructeur de la classe Patient
     *
     * @param id            Identifiant unique
     * @param nss           NSS du patient
     * @param nom           Nom
     * @param prenom        Prénom
     * @param dateNaissance Date de naissance
     */
    public Patient(int id, String nss, String nom, String prenom, String tel, LocalDate dateNaissance) {
        this.id = id;
        this.nss = nss;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.tel = tel;
    }

    /**
     * Retourne l'identifiant unique du patient
     *
     * @return Identifiant unique du patient
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le NSS du patient
     *
     * @return NSS
     */
    public String getNss() {
        return nss;
    }

    /**
     * Définir le NSS du patient
     *
     * @param nss NSS
     */
    public void setNss(String nss) {
        this.nss = nss;
    }

    /**
     * Retourne le nom du patient
     *
     * @return Nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définir le nom du patient
     *
     * @param nom Nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prénom du patient
     *
     * @return Prénom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Définir le prénom du patient
     *
     * @param prenom Prénom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne la date de naissance du patient
     *
     * @return Date de naissance
     */
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Définir la date de naissance du patient
     *
     * @param dateNaissance Date de naissance
     */
    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Retourne la liste des prescriptions reçues
     *
     * @return Liste de prescriptions
     */
    public List<Prescription> getPrescription() {
        return prescription;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;

        notifyObservers();
    }

    /**
     * Définir la liste des prescriptions reçues
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
        Patient patient = (Patient) o;
        return Objects.equals(nss, patient.nss);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", nss='" + nss + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(nss);
    }


    @Override
    public String getNotification() {
        return "Nouveau numéro de téléphone de " + getNom() + " = " + getTel();
    }
}
