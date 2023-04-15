package pharmacie.mvp.view;

import pharmacie.metier.Infos;
import pharmacie.metier.Prescription;
import pharmacie.mvp.presenter.PrescriptionPresenter;

import java.util.List;
import java.util.Scanner;

public class PrescriptionViewConsole implements PrescriptionViewInterface {

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
        presenter.addPrescription();
    }

    public void recherche() {
        System.out.println("ID de la prescription recherché: ");
        int idPrescription = scanner.nextInt();

        Prescription p = presenter.read(idPrescription);

        if (p == null) {
            System.out.println("Prescription introuvable !");
        } else {
            System.out.println(p);

            for(Infos info : p.getInfos()) {
                System.out.println(info);
            }
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
