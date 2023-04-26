package pharmacie.mvp.view;

import pharmacie.designpatterns.builder.Infos;
import pharmacie.designpatterns.builder.Prescription;
import pharmacie.mvp.presenter.PrescriptionPresenter;
import pharmacie.utilitaires.Utilitaire;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static pharmacie.utilitaires.Utilitaire.saisie;

public class PrescriptionViewConsole implements PrescriptionViewInterface {

    private PrescriptionPresenter presenter;

    private List<Prescription> prescriptions;
    private final Scanner scanner = new Scanner(System.in);

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PrescriptionViewConsole() {
    }

    public void menu() {
        menu:
        do {
            System.out.println("1.Ajout\n2.Recherche\n3.Modification\n4.Suppression\n5.Tous\n6.Fin");

            int ch = saisie("choix : ", Integer::parseInt);

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
        int idPrescription = saisie("ID de la prescription recherché: ", Integer::parseInt);

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

        String date = Utilitaire.modifyIfNotBlank("Date de prescription ", Utilitaire.getDateFrench(prescription.getDatePrescription()));
        LocalDate datePrescription = LocalDate.parse(date, formatter);

        Prescription newPrescription = new Prescription.PrescriptionBuilder()
                .setId(prescription.getId())
                .setDatePrescription(datePrescription)
                .setMedecin(prescription.getMedecin())
                .setPatient(prescription.getPatient())
                .build();

        presenter.update(newPrescription);
    }

    public void suppression() {
        int idPrescription = saisie("ID de la prescription recherché: ", Integer::parseInt);

        Prescription modifierPrescription = new Prescription.PrescriptionBuilder()
                .setId(idPrescription)
                .build();

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
