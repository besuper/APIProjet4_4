package pharmacie.mvp.view;

import pharmacie.metier.Prescription;
import pharmacie.mvp.presenter.PrescriptionPresenter;

import java.util.List;
import java.util.Scanner;

public class PrescriptionViewConsole implements ViewInterface<PrescriptionPresenter> {

    private PrescriptionPresenter presenter;
    private List<Prescription> prescriptions;
    private final Scanner scanner = new Scanner(System.in);

    public PrescriptionViewConsole() {
    }

    public void menu() {
        menu:
        do {
            System.out.println("1.Ajout\n2.Recherche\n3.Modification\n4.Suppression\n5.Tous\n6.Fin");
            System.out.println("choix : ");

            int ch = scanner.nextInt();
            scanner.skip("\n");

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
        for (Prescription p : this.prescriptions) {
            System.out.println(p);
        }
    }

    public void ajout() {
        /*System.out.println("NSS: ");
        String nss = scanner.next();

        System.out.println("Nom: ");
        String nom = scanner.next();

        System.out.println("Prénom: ");
        String prenom = scanner.next();

        System.out.println("Date de naissance (jj/mm/aaaa): ");
        String date = scanner.next();
        LocalDate dateNaissance = LocalDate.parse(date, formatter);

        presenter.addPatient(new Patient(0, nss, nom, prenom, dateNaissance));*/
    }

    public void recherche() {
        System.out.println("ID de la prescription recherché: ");
        int idPrescription = scanner.nextInt();

        Prescription p = presenter.read(idPrescription);

        if (p == null) {
            System.out.println("Prescription introuvable !");
        } else {
            System.out.println(p);

            //opSpeciales(p);
        }
    }

    public void modification() {
        System.out.println("Id de la prescription recherché: ");
        /*int idPatient = scanner.nextInt();
        scanner.skip("\n");

        System.out.println("1.modifier nss\n2.modifier nom\n3.modifier prenom\n4.modifier date de naissance\n5.Retour");
        System.out.println("choix : ");

        int ch = scanner.nextInt();
        scanner.skip("\n");

        Patient modifierPatient = new Patient(idPatient, "", "", "", null);

        switch (ch) {
            case 1:
                System.out.println("NSS: ");

                presenter.modifierPatientInfo(modifierPatient, "nss", scanner.next());
                break;
            case 2:
                System.out.println("Nom: ");

                presenter.modifierPatientInfo(modifierPatient, "nom", scanner.next());
                break;
            case 3:
                System.out.println("Prenom: ");

                presenter.modifierPatientInfo(modifierPatient, "prenom", scanner.next());
                break;

            case 4:
                System.out.println("Date de naissance (jj/mm/aaaa): ");
                String date = scanner.next();
                LocalDate dateNaissance = LocalDate.parse(date, formatter);

                presenter.modifierPatientInfo(modifierPatient, "datenaissance", dateNaissance);
                return;
            case 5:
                break;
            default:
                System.out.println("choix invalide recommencez ");
        }*/
    }

    public void suppression() {
        System.out.println("id de la prescription recherchée: ");

        int idPrescription = scanner.nextInt();
        scanner.skip("\n");

        Prescription modifierPrescription = new Prescription(idPrescription, null, null, null);

        presenter.removePrescription(modifierPrescription);
    }

    /*private void opSpeciales(Patient patient) {
        do {
            System.out.println("1.liste des médecins\n2.montant prescriptions\n3.prescriptions\n4.menu principal");
            System.out.println("choix : ");

            int ch = scanner.nextInt();
            scanner.skip("\n");

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
    }*/

    @Override
    public void setPresenter(PrescriptionPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List patients) {
        this.prescriptions = patients;
    }

    @Override
    public void affMsg(String msg) {
        System.out.println(msg);
    }

    @Override
    public void start() {
        menu();
    }
}
