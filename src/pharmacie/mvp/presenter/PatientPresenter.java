package pharmacie.mvp.presenter;

import pharmacie.metier.Medecin;
import pharmacie.metier.Patient;
import pharmacie.metier.Prescription;
import pharmacie.mvp.model.DAO;
import pharmacie.mvp.model.PatientSpecial;
import pharmacie.mvp.view.ViewInterface;

import java.time.LocalDate;
import java.util.List;

public class PatientPresenter {

    private final DAO<Patient> model;
    private final ViewInterface<PatientPresenter> view;

    public PatientPresenter(DAO<Patient> model, ViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void updateList() {
        List<Patient> patients = model.getAll();
        view.setListDatas(patients);
    }

    public void start() {
        updateList();
        view.start();
    }

    public List<Medecin> listerMedecin(Patient p) {
        return ((PatientSpecial) model).getMedecins(p);
    }

    public void addPatient(Patient patient) {
        Patient newPatient = model.add(patient);

        if (newPatient == null) {
            view.affMsg("Erreur de création");
        } else {
            view.affMsg("Création de : " + newPatient);
        }

        updateList();
    }

    public void removePatient(Patient patient) {
        boolean success = model.remove(patient);

        if (success) {
            view.affMsg("Patient supprimé");
        } else {
            view.affMsg("Erreur de suppression");
        }

        updateList();
    }

    public void modifierPatientInfo(Patient patient, String key, Object value) {
        Patient updated = model.update(patient, key, value);

        if (updated != null) {
            view.affMsg("Patient modifié !");
        } else {
            view.affMsg("Erreur de modification");
        }

        updateList();
    }

    public Patient read(int idPatient) {
        return model.read(idPatient);
    }

    public double calcTot(Patient p) {
        return ((PatientSpecial) model).calcTot(p);
    }

    public List<Prescription> prescriptionsDate(Patient patient, LocalDate debut, LocalDate fin) {
        return ((PatientSpecial) model).prescriptionsDate(patient, debut, fin);
    }

}
