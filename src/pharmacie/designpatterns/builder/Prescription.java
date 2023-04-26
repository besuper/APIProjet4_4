package pharmacie.designpatterns.builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui représente une prescription
 *
 * @author Jayson
 * @see Medecin
 * @see Patient
 */
public class Prescription {

    /**
     * Identifiant unique d'une prescription
     */
    protected int id;

    /**
     * Date de prescription
     */
    protected LocalDate datePrescription;

    /**
     * Médecin qui a prescrit
     */
    protected Medecin medecin;

    /**
     * Le patient concerné par la prescription
     */
    protected Patient patient;

    /**
     * Liste d'info sur les médicaments
     */
    protected List<Infos> infos = new ArrayList<>();

    /**
     * Constructeur de la Prescription
     *
     * @param id               Identifiant unique
     * @param datePrescription Date de la prescription
     * @param medecin          Medecin qui prescrit
     * @param patient          Patient concerné
     */
    private Prescription(PrescriptionBuilder builder) {
        this.id = builder.id;
        this.datePrescription = builder.datePrescription;
        this.medecin = builder.medecin;
        this.patient = builder.patient;
        this.infos = builder.infos;
    }

    /**
     * Retourne l'identifiant unique de la prescription
     *
     * @return Identifiant unique
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne la date de prescription
     *
     * @return Date de prescription
     */
    public LocalDate getDatePrescription() {
        return datePrescription;
    }

    /**
     * Retourne le médecin qui a prescrit
     *
     * @return Medecin
     */
    public Medecin getMedecin() {
        return medecin;
    }

    /**
     * Retourne le patient concerné
     *
     * @return Patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Retourne une liste de Info
     *
     * @return Liste Info
     */
    public List<Infos> getInfos() {
        return infos;
    }

    public void setInfos(List<Infos> infos) {
        this.infos = infos;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", datePrescription=" + datePrescription +
                ", medecin=" + medecin +
                ", patient=" + patient +
                '}';
    }

    public static class PrescriptionBuilder {

        protected int id;
        protected LocalDate datePrescription;
        protected Medecin medecin;
        protected Patient patient;
        protected List<Infos> infos = new ArrayList<>();

        public PrescriptionBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public PrescriptionBuilder setDatePrescription(LocalDate datePrescription) {
            this.datePrescription = datePrescription;
            return this;
        }

        public PrescriptionBuilder setMedecin(Medecin medecin) {
            this.medecin = medecin;
            return this;
        }

        public PrescriptionBuilder setPatient(Patient patient) {
            this.patient = patient;
            return this;
        }

        public PrescriptionBuilder setInfos(List<Infos> infos) {
            this.infos = infos;
            return this;
        }

        public Prescription build() {
            return new Prescription(this);
        }
    }

}
