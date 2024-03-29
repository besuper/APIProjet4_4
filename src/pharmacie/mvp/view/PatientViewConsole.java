package pharmacie.mvp.view;

import pharmacie.designpatterns.builder.Medecin;
import pharmacie.designpatterns.builder.Patient;
import pharmacie.designpatterns.builder.Prescription;
import pharmacie.mvp.presenter.PatientPresenter;
import pharmacie.utilitaires.Utilitaire;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import static pharmacie.utilitaires.Utilitaire.saisie;

public class PatientViewConsole implements PatientViewInterface {

    private PatientPresenter presenter;
    private List<Patient> patients;
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PatientViewConsole() {
    }

    public void menu() {
        menu:
        do {
            System.out.println("1.Ajout\n2.Recherche\n3.Modification\n4.Suppression\n5.Tous\n6.Fin");

            int ch = saisie("choix: ", Integer::parseInt);

            switch (ch) {
                case 1 -> ajout();
                case 2 -> recherche();
                case 3 -> modification();
                case 4 -> suppression();
                case 5 -> tous();
                case 6 -> {
                    break menu;
                }
                default -> System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }

    public void tous() {
        for (Patient p : this.patients) {
            System.out.println(p);
        }
    }

    public void ajout() {
        System.out.println("NSS: ");
        String nss = scanner.next();

        System.out.println("Nom: ");
        String nom = scanner.next();

        System.out.println("Prénom: ");
        String prenom = scanner.next();

        LocalDate dateNaissance = (LocalDate) Utilitaire.saisie("Date de naissance (jj/mm/aaaa): ", (Function<String, Object>) s -> LocalDate.parse(s, formatter));

        presenter.addPatient(new Patient.PatientBuilder()
                .setNss(nss)
                .setNom(nom)
                .setPrenom(prenom)
                .setDateNaissance(dateNaissance)
                .build());
    }

    public void recherche() {
        int idPatient = saisie("ID du patient recherché: ", Integer::parseInt);

        Patient p = presenter.read(idPatient);

        if (p == null) {
            System.out.println("Patient introuvable !");
        } else {
            System.out.println(p);

            opSpeciales(p);
        }
    }

    public void modification() {
        int nl = Utilitaire.choixListe(patients) - 1;

        Patient patient = patients.get(nl);

        String nss = Utilitaire.modifyIfNotBlank("NSS", patient.getNss());
        String nom = Utilitaire.modifyIfNotBlank("Nom", patient.getNom());
        String prenom = Utilitaire.modifyIfNotBlank("Prénom", patient.getPrenom());
        String date = Utilitaire.modifyIfNotBlank("Date de naissance ", Utilitaire.getDateFrench(patient.getDateNaissance()));
        LocalDate dateNaissance;

        try {
            dateNaissance = LocalDate.parse(date, formatter);
        } catch (Exception e) {
            System.out.println("Format de date invalide !");

            return;
        }

        Patient newPatient = new Patient.PatientBuilder()
                .setId(patient.getId())
                .setNss(nss)
                .setNom(nom)
                .setPrenom(prenom)
                .setDateNaissance(dateNaissance)
                .build();

        presenter.update(newPatient);
    }

    public void suppression() {
        int idPatient = saisie("ID du patient recherché: ", Integer::parseInt);

        Patient modifierPatient = new Patient.PatientBuilder()
                .setId(idPatient)
                .build();

        presenter.removePatient(modifierPatient);
    }

    private void opSpeciales(Patient patient) {
        do {
            System.out.println("1.liste des médecins\n2.montant prescriptions\n3.prescriptions\n4.menu principal");

            int ch = saisie("choix : ", Integer::parseInt);

            switch (ch) {
                case 1 -> listeMedecin(patient);
                case 2 -> prescriptionTotal(patient);
                case 3 -> prescriptionDate(patient);
                case 4 -> {
                    return;
                }
                default -> System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }

    public void listeMedecin(Patient p) {
        List<Medecin> medecins = presenter.listerMedecin(p);

        if (medecins.size() == 0) {
            System.out.println("Aucun médecin trouvé");
        } else {
            for (Medecin medecin : medecins) {
                System.out.println(medecin);
            }
        }
    }

    public void prescriptionTotal(Patient p) {
        double total = presenter.calcTot(p);

        System.out.println("Total de toutes les prescriptions: " + total);
    }

    public void prescriptionDate(Patient p) {
        System.out.println("Date de début (jj/mm/aaaa): ");
        String dateD = scanner.next();
        LocalDate dateDebut = LocalDate.parse(dateD, formatter);

        System.out.println("Date de fin (jj/mm/aaaa): ");
        String dateF = scanner.next();
        LocalDate dateFin = LocalDate.parse(dateF, formatter);

        List<Prescription> prescriptions = presenter.prescriptionsDate(p, dateDebut, dateFin);

        if (prescriptions.size() == 0) {
            System.out.println("Aucune prescriptions");
        } else {
            for (Prescription pres : prescriptions) {
                System.out.println(pres);
            }
        }
    }

    @Override
    public void setPresenter(PatientPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public void affMsg(String msg) {
        System.out.println(msg);
    }

    @Override
    public void start() {
        menu();
    }

    @Override
    public Patient selectionner(List<Patient> list) {
        int index = Utilitaire.choixListe(list);

        return list.get(index - 1);
    }
}
