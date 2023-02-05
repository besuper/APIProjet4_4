package pharmacie;

import java.util.ArrayList;
import java.util.List;

public class Prescription {

    private int id;
    private String datePrescription;
    private Medecin medecin;
    private Patient patient;

    private List<Infos> infos = new ArrayList<>();

    public Prescription(int id, String datePrescription, Medecin medecin, Patient patient) {
        this.id = id;
        this.datePrescription = datePrescription;
        this.medecin = medecin;
        this.patient = patient;
    }

    public String getDatePrescription() {
        return datePrescription;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public Patient getPatient() {
        return patient;
    }

    public List<Infos> getInfos() {
        return infos;
    }
}
