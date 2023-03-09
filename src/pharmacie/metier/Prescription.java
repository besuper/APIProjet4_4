package pharmacie.metier;

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
    private int id;

    /**
     * Date de prescription
     */
    private LocalDate datePrescription;

    /**
     * Médecin qui a prescrit
     */
    private Medecin medecin;

    /**
     * Le patient concerné par la prescription
     */
    private Patient patient;

    /**
     * Liste d'info sur les médicaments
     */
    private List<Infos> infos = new ArrayList<>();

    /**
     * Constructeur de la Prescription
     *
     * @param id               Identifiant unique
     * @param datePrescription Date de la prescription
     * @param medecin          Medecin qui prescrit
     * @param patient          Patient concerné
     */
    public Prescription(int id, LocalDate datePrescription, Medecin medecin, Patient patient) {
        this.id = id;
        this.datePrescription = datePrescription;
        this.medecin = medecin;
        this.patient = patient;
    }

    /**
     * Retourne l'identifiant unique de la prescription
     *
     * @return Identifiant unique
     */
    public int getId() {
        return id;
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
     * Définir la date de precription
     *
     * @param datePrescription Date de prescription
     */
    public void setDatePrescription(LocalDate datePrescription) {
        this.datePrescription = datePrescription;
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
     * Définir le médecin qui a prescrit
     *
     * @param medecin Medecin
     */
    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
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
     * Défénir le patient concerné
     *
     * @param patient Patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Retourne une liste de Info
     *
     * @return Liste Info
     */
    public List<Infos> getInfos() {
        return infos;
    }

    /**
     * Définir la liste d'info
     *
     * @param infos Liste Info
     */
    public void setInfos(List<Infos> infos) {
        this.infos = infos;
    }
}
