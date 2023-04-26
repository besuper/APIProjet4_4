package pharmacie.mvp.view;

import pharmacie.metier.Infos;
import pharmacie.metier.Medicament;
import pharmacie.metier.Prescription;
import pharmacie.mvp.presenter.MedicamentPresenter;
import pharmacie.utilitaires.Utilitaire;

import java.util.ArrayList;
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

        Medicament med = presenter.read(idMedicament);

        if(med == null) {
            System.out.println("Médicament introuvable !");
        }else {
            System.out.println(med);
        }
    }

    public void modification() {
        int nl = Utilitaire.choixElt(medicaments) - 1;

        Medicament medicament = medicaments.get(nl);

        String code = Utilitaire.modifyIfNotBlank("code", medicament.getCode());
        String nom = Utilitaire.modifyIfNotBlank("nom", medicament.getNom());
        String description = Utilitaire.modifyIfNotBlank("description", medicament.getDescription());
        String prix = Utilitaire.modifyIfNotBlank("prix unitaire", String.valueOf(medicament.getPrixUnitaire()));
        double prixUnitaire = Double.parseDouble(prix);

        Medicament newMedicament = new Medicament(medicament.getId(), code, nom, description, prixUnitaire);

        presenter.update(newMedicament);
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
    public void setListDatas(List<Medicament> medicaments) {
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
    public List<Infos> selectionner(List<Medicament> list, Prescription prescription) {
        System.out.println("Selectionnez les médicaments (-1 pour arrêt)");

        int index;
        int quantity;
        List<Infos> infos = new ArrayList<>();

        while(true) {
            index = Utilitaire.choixListeM(list);

            if(index == -1) break;

            System.out.println("Quantité : ");
            quantity = scanner.nextInt();

            infos.add(new Infos(quantity, list.get(index - 1), prescription));
        }

        return infos;
    }
}
