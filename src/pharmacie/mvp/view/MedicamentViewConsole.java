package pharmacie.mvp.view;

import pharmacie.metier.Medicament;
import pharmacie.mvp.presenter.MedicamentPresenter;
import pharmacie.utilitaires.Utilitaire;

import java.util.List;
import java.util.Scanner;

public class MedicamentViewConsole implements MedicamentViewInterface {

    private MedicamentPresenter presenter;
    private List<Medicament> medicaments;
    private final Scanner scanner = new Scanner(System.in);

    public MedicamentViewConsole() {
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
        for (Medicament p : this.medicaments) {
            System.out.println(p);
        }
    }

    public void ajout() {
        System.out.println("Code: ");
        String code = scanner.next();

        System.out.println("Nom: ");
        String nom = scanner.next();

        System.out.println("Description: ");
        String description = scanner.next();

        System.out.println("Prix unitaire: ");
        double prixUnitaire = scanner.nextDouble();

        presenter.addMedicament(new Medicament(0, code, nom, description, prixUnitaire));
    }

    public void recherche() {
        System.out.println("ID du médicament recherché: ");
        int idMedicament = scanner.nextInt();

        for (Medicament p : this.medicaments) {
            if (p.getId() == idMedicament) {
                System.out.println(p);
            }
        }
    }

    public void modification() {
        System.out.println("Id du médicament recherché: ");
        int idMedicament = scanner.nextInt();

        scanner.skip("\n");

        System.out.println("1.modifier code\n2.modifier nom\n3.modifier description\n4.modifier prix unitaire\n5.Retour");
        System.out.println("choix : ");

        int ch = scanner.nextInt();
        scanner.skip("\n");

        Medicament modifierMedicament = new Medicament(idMedicament, "", "", "", 0.0);

        switch (ch) {
            case 1:
                System.out.println("Code: ");

                presenter.modifierMedicamentInfo(modifierMedicament, "code", scanner.next());
                break;
            case 2:
                System.out.println("Nom: ");

                presenter.modifierMedicamentInfo(modifierMedicament, "nom", scanner.next());
                break;
            case 3:
                System.out.println("Prenom: ");

                presenter.modifierMedicamentInfo(modifierMedicament, "description", scanner.next());
                break;

            case 4:
                System.out.println("Prix unitaire: ");

                presenter.modifierMedicamentInfo(modifierMedicament, "prixunitaire", scanner.nextDouble());
                return;
            case 5:
                break;
            default:
                System.out.println("choix invalide recommencez ");
        }
    }

    public void suppression() {
        System.out.println("id du médicament recherché: ");

        int idMedicament = scanner.nextInt();
        scanner.skip("\n");

        Medicament modifierMedicament = new Medicament(idMedicament, "", "", "", 0.0);

        presenter.removeMedicament(modifierMedicament);
    }

    @Override
    public void setPresenter(MedicamentPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List medicaments) {
        this.medicaments = medicaments;
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
    public Medicament selectionner(List<Medicament> list) {
        int index = Utilitaire.choixListe(list);

        return list.get(index - 1);
    }
}
