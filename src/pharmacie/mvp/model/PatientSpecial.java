package pharmacie.mvp.model;

import pharmacie.designpatterns.builder.Medecin;
import pharmacie.designpatterns.builder.Patient;
import pharmacie.designpatterns.builder.Prescription;

import java.time.LocalDate;
import java.util.List;

public interface PatientSpecial {

    List<Medecin> getMedecins(Patient p);
    double calcTot(Patient p);

    List<Prescription> prescriptionsDate(Patient patient, LocalDate debut, LocalDate fin);

}
