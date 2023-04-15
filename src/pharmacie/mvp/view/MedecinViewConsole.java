package pharmacie.mvp.view;

import pharmacie.metier.Medecin;
import pharmacie.mvp.presenter.MedecinPresenter;
import pharmacie.utilitaires.Utilitaire;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MedecinViewConsole implements MedecinViewInterface {

    private MedecinPresenter presenter;
    private List<Medecin> medecins;
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MedecinViewConsole() {
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
        for (Medecin p : this.medecins) {
            System.out.println(p);
        }
    }

    public void ajout() {
        System.out.println("Matricule: ");
        String matricule = scanner.next();

        System.out.println("Nom: ");
        String nom = scanner.next();

        System.out.println("Prénom: ");
        String prenom = scanner.next();

        System.out.println("Tel: ");
        String tel = scanner.next();

        presenter.addMedecin(new Medecin(0, matricule, nom, prenom, tel));
    }

    public void recherche() {
        System.out.println("ID du medecin recherché: ");
        int idMedecin = scanner.nextInt();

        for (Medecin p : this.medecins) {
            if (p.getId() == idMedecin) {
                System.out.println(p);
            }
        }
    }

    public void modification() {
        System.out.println("Id du medecin recherché: ");
        int idMedecin = scanner.nextInt();
        scanner.skip("\n");

        System.out.println("1.modifier matricule\n2.modifier nom\n3.modifier prenom\n4.modifier tel\n5.Retour");
        System.out.println("choix : ");

        int ch = scanner.nextInt();
        scanner.skip("\n");

        Medecin modifierPatient = new Medecin(idMedecin, "", "", "", "");

        switch (ch) {
            case 1:
                System.out.println("Matricule: ");

                presenter.modifierMedecinInfo(modifierPatient, "matricule", scanner.next());
                break;
            case 2:
                System.out.println("Nom: ");

                presenter.modifierMedecinInfo(modifierPatient, "nom", scanner.next());
                break;
            case 3:
                System.out.println("Prenom: ");

                presenter.modifierMedecinInfo(modifierPatient, "prenom", scanner.next());
                break;

            case 4:
                System.out.println("Tel: ");

                presenter.modifierMedecinInfo(modifierPatient, "tel", scanner.next());
                return;
            case 5:
                break;
            default:
                System.out.println("choix invalide recommencez ");
        }
    }

    public void suppression() {
        System.out.println("id du medecin recherché: ");

        int idMedecin = scanner.nextInt();
        scanner.skip("\n");

        Medecin modifierPatient = new Medecin(idMedecin, "", "", "", "");

        presenter.removeMedecin(modifierPatient);
    }

    @Override
    public void setPresenter(MedecinPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List medecins) {
        this.medecins = medecins;
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
    public Medecin selectionner(List<Medecin> list) {
        int index = Utilitaire.choixListe(list);

        return list.get(index - 1);
    }
}
