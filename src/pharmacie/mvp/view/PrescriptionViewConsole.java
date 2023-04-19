package pharmacie.mvp.view;

import pharmacie.metier.Infos;
import pharmacie.metier.Prescription;
import pharmacie.mvp.presenter.PrescriptionPresenter;
import pharmacie.utilitaires.Utilitaire;

import java.time.LocalDate;
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
        int nl = Utilitaire.choixElt(prescriptions) - 1;

        Prescription prescription = prescriptions.get(nl);

        // TODO: Date de prescription

        Prescription newPrescription = new Prescription(prescription.getId(), LocalDate.now(), prescription.getMedecin(), prescription.getPatient());

        presenter.update(newPrescription);
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
    public void setListDatas(List<Prescription> patients) {
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
