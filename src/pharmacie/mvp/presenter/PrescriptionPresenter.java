package pharmacie.mvp.presenter;

import pharmacie.metier.Medecin;
import pharmacie.metier.Patient;
import pharmacie.metier.Prescription;
import pharmacie.mvp.model.DAO;
import pharmacie.mvp.view.PrescriptionViewInterface;

import java.time.LocalDate;
import java.util.List;

public class PrescriptionPresenter {

    private final DAO<Prescription> model;
    private final PrescriptionViewInterface view;

    private MedecinPresenter medecinPresenter;
    private PatientPresenter patientPresenter;
    private MedicamentPresenter medicamentPresenter;

    public PrescriptionPresenter(DAO model, PrescriptionViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void setMedecinPresenter(MedecinPresenter medecinPresenter) {
        this.medecinPresenter = medecinPresenter;
    }

    public void setMedicamentPresenter(MedicamentPresenter medicamentPresenter) {
        this.medicamentPresenter = medicamentPresenter;
    }

    public void setPatientPresenter(PatientPresenter patientPresenter) {
        this.patientPresenter = patientPresenter;
    }

    public void start() {
        updateList();
        view.start();
    }

    public void updateList() {
        List<Prescription> prescriptions = model.getAll();
        view.setListDatas(prescriptions);
    }

    public void addPrescription() {
        Medecin medecin = medecinPresenter.selectionner();
        Patient patient = patientPresenter.selectionner();

        Prescription newPrescription = new Prescription(0, LocalDate.now(), medecin, patient);

        newPrescription = model.add(newPrescription);

        if (newPrescription == null) {
            view.affMsg("Erreur de création");
        } else {
            view.affMsg("Création de : " + newPrescription);
        }

        updateList();
    }

    public void removePrescription(Prescription prescription) {
        boolean success = model.remove(prescription);

        if (success) {
            view.affMsg("Prescription supprimé");
        } else {
            view.affMsg("Erreur de suppression");
        }

        updateList();
    }

    public Prescription read(int idPrescription) {
        return model.read(idPrescription);
    }

    public void modifierPrescriptionInfo(Prescription prescription, String key, Object value) {
        Prescription updated = model.update(prescription, key, value);

        if (updated != null) {
            view.affMsg("Prescription modifié !");
        } else {
            view.affMsg("Erreur de modification");
        }

        updateList();
    }

}