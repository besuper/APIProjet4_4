package pharmacie.mvp.model;

import pharmacie.metier.Medecin;
import pharmacie.metier.Patient;
import pharmacie.metier.Prescription;

import java.time.LocalDate;
import java.util.List;

public interface PatientSpecial {

    List<Medecin> getMedecins(Patient p);
    double calcTot(Patient p);

    List<Prescription> prescriptionsDate(Patient patient, LocalDate debut, LocalDate fin);

}
