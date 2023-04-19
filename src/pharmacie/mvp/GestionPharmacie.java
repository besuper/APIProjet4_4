package pharmacie.mvp;

import pharmacie.metier.Medecin;
import pharmacie.metier.Medicament;
import pharmacie.metier.Patient;
import pharmacie.metier.Prescription;
import pharmacie.mvp.model.*;
import pharmacie.mvp.presenter.MedecinPresenter;
import pharmacie.mvp.presenter.MedicamentPresenter;
import pharmacie.mvp.presenter.PatientPresenter;
import pharmacie.mvp.presenter.PrescriptionPresenter;
import pharmacie.mvp.view.*;
import pharmacie.utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestionPharmacie {

    // Patients
    DAO<Patient> daoPatient;
    PatientViewInterface viewPatient;
    PatientPresenter pp;

    // Medecins
    DAO<Medecin> daoMedecin;
    MedecinViewInterface viewMedecin;
    MedecinPresenter mp;

    // Medicament
    DAO<Medicament> daoMedicament;
    MedicamentViewInterface viewMedicament;
    MedicamentPresenter mmp;

    // Prescriptions
    DAO<Prescription> daoPrescription;
    PrescriptionViewInterface viewPrescription;
    PrescriptionPresenter ppp;

    public void gestion() {
        daoPatient = new PatientModelDB();
        viewPatient = new PatientViewConsole();
        pp = new PatientPresenter(daoPatient, viewPatient);

        daoMedecin = new MedecinModel();
        viewMedecin = new MedecinViewConsole();
        mp = new MedecinPresenter(daoMedecin, viewMedecin);

        daoMedicament = new MedicamentModel();
        viewMedicament = new MedicamentViewConsole();
        mmp = new MedicamentPresenter(daoMedicament, viewMedicament);

        daoPrescription = new PrescriptionModel();
        viewPrescription = new PrescriptionViewConsole();
        ppp = new PrescriptionPresenter(daoPrescription, viewPrescription);
        ppp.setMedecinPresenter(mp);
        ppp.setPatientPresenter(pp);
        ppp.setMedicamentPresenter(mmp);

        List<String> loptions = Arrays.asList("Prescriptions", "Patients", "Medecins", "Médicaments", "fin");

        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch) {
                case 1 -> ppp.start();
                case 2 -> pp.start();
                case 3 -> mp.start();
                case 4 -> mmp.start();
                case 5 -> System.exit(0);
            }
        } while (true);
    }

    public static void main(String[] args) {
        GestionPharmacie gp = new GestionPharmacie();
        gp.gestion();
    }

}
