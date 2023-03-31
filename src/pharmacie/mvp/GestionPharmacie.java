package pharmacie.mvp;

import pharmacie.metier.Medecin;
import pharmacie.metier.Medicament;
import pharmacie.metier.Patient;
import pharmacie.mvp.model.DAO;
import pharmacie.mvp.model.MedecinModel;
import pharmacie.mvp.model.MedicamentModel;
import pharmacie.mvp.model.PatientModel;
import pharmacie.mvp.presenter.MedecinPresenter;
import pharmacie.mvp.presenter.MedicamentPresenter;
import pharmacie.mvp.presenter.PatientPresenter;
import pharmacie.mvp.view.MedecinViewConsole;
import pharmacie.mvp.view.MedicamentViewConsole;
import pharmacie.mvp.view.PatientViewConsole;
import pharmacie.mvp.view.ViewInterface;
import pharmacie.utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestionPharmacie {

    // Patients
    DAO<Patient> daoPatient;
    ViewInterface<PatientPresenter> viewPatient;
    PatientPresenter pp;

    // Medecins
    DAO<Medecin> daoMedecin;
    ViewInterface<MedecinPresenter> viewMedecin;
    MedecinPresenter mp;

    // Medicament
    DAO<Medicament> daoMedicament;
    ViewInterface<MedicamentPresenter> viewMedicament;
    MedicamentPresenter mmp;

    public void gestion() {
        daoPatient = new PatientModel();
        viewPatient = new PatientViewConsole();
        pp = new PatientPresenter(daoPatient, viewPatient);

        daoMedecin = new MedecinModel();
        viewMedecin = new MedecinViewConsole();
        mp = new MedecinPresenter(daoMedecin, viewMedecin);

        daoMedicament = new MedicamentModel();
        viewMedicament = new MedicamentViewConsole();
        mmp = new MedicamentPresenter(daoMedicament, viewMedicament);

        List<String> loptions = Arrays.asList("patients", "medecins", "medicaments", "fin");

        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch) {
                case 1 -> pp.start();
                case 2 -> mp.start();
                case 3 -> mmp.start();
                case 4 -> System.exit(0);
            }
        } while (true);
    }

    public static void main(String[] args) {
        GestionPharmacie gp = new GestionPharmacie();
        gp.gestion();
    }

}
