package pharmacie.designpatterns.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patient {

    /**
     * Identifiant unique du patient
     */
    protected final int id;

    /**
     * NSS du patient
     */
    protected String nss;

    /**
     * Nom
     */
    protected String nom;

    /**
     * Prénom
     */
    protected String prenom;

    /**
     * Date de naissance
     */
    protected LocalDate dateNaissance;

    /**
     * Liste des prescriptions reçues
     */
    protected List<Prescription> prescription = new ArrayList<>();

    private Patient(PatientBuilder builder) {
        this.id = builder.id;
        this.nss = builder.nss;
        this.nom = builder.nom;
        this.prenom = builder.prenom;
        this.dateNaissance = builder.dateNaissance;
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
     * Retourne le nom du patient
     *
     * @return Nom
     */
    public String getNom() {
        return nom;
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
     * Retourne la date de naissance du patient
     *
     * @return Date de naissance
     */
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Retourne la liste des prescriptions reçues
     *
     * @return Liste de prescriptions
     */
    public List<Prescription> getPrescription() {
        return prescription;
    }

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

    public static class PatientBuilder {

        protected int id;
        protected String nss;
        protected String nom;
        protected String prenom;
        protected LocalDate dateNaissance;

        public PatientBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public PatientBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public PatientBuilder setNss(String nss) {
            this.nss = nss;
            return this;
        }

        public PatientBuilder setDateNaissance(LocalDate dateNaissance) {
            this.dateNaissance = dateNaissance;
            return this;
        }

        public PatientBuilder setPrenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public Patient build(){
            return new Patient(this);
        }
    }

}
