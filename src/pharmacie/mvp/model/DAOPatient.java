package pharmacie.mvp.model;

import pharmacie.metier.Medecin;
import pharmacie.metier.Patient;
import pharmacie.metier.Prescription;

import java.time.LocalDate;
import java.util.List;

public interface DAOPatient {

    Patient addPatient(Patient patient);

    boolean modifierPatient(Patient patient, String key, Object value);

    boolean removePatient(Patient patient);

    List<Patient> getPatients();

    List<Medecin> getMedecins(Patient patient);

    double calcTot(Patient patient);

    List<Prescription> prescriptionsDate(Patient patient, LocalDate debut, LocalDate fin);

}
