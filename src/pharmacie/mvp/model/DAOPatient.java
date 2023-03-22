package pharmacie.mvp.model;

import pharmacie.metier.Patient;
import java.util.List;

// CRUD
public interface DAOPatient {

    Patient addPatient(Patient patient);

    boolean modifierPatient(Patient patient, String key, Object value);

    boolean removePatient(Patient patient);

    List<Patient> getPatients();

}
