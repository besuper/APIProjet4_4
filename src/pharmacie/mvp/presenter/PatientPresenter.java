package pharmacie.mvp.presenter;

import pharmacie.metier.Medecin;
import pharmacie.metier.Patient;
import pharmacie.metier.Prescription;
import pharmacie.mvp.model.DAOPatient;
import pharmacie.mvp.view.PatientViewInterface;

import java.time.LocalDate;
import java.util.List;

public class PatientPresenter {

    private final DAOPatient model;
    private final PatientViewInterface view;

    public PatientPresenter(DAOPatient model, PatientViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        List<Patient> patients = model.getPatients();
        view.setListDatas(patients);
    }

    public List<Medecin> listerMedecin(Patient p) {
        return model.getMedecins(p);
    }

    public void addPatient(Patient patient) {
        Patient newPatient = model.addPatient(patient);

        if(newPatient == null) {
            view.affMsg("Erreur de création");
        }else {
            view.affMsg("Création de : " + newPatient);
        }

        start();
    }

    public void removePatient(Patient patient) {
        boolean success = model.removePatient(patient);

        if(success) {
            view.affMsg("Patient supprimé");
        }else {
            view.affMsg("Erreur de suppression");
        }

        start();
    }

    public void modifierPatientInfo(Patient patient, String key, Object value) {
        boolean updated = model.modifierPatient(patient, key, value);

        if(updated) {
            view.affMsg("Patient modifié !");
        }else {
            view.affMsg("Erreur de modification");
        }

        start();
    }

    public double calcTot(Patient p) {
        return model.calcTot(p);
    }

    public List<Prescription> prescriptionsDate(Patient patient, LocalDate debut, LocalDate fin) {
        return model.prescriptionsDate(patient, debut, fin);
    }

}
