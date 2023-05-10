package pharmacie.mvp.view;

import pharmacie.designpatterns.builder.*;
import pharmacie.mvp.presenter.MedecinPresenter;
import pharmacie.utilitaires.Utilitaire;

import java.util.List;
import java.util.Scanner;

import static pharmacie.utilitaires.Utilitaire.saisie;

public class MedecinViewConsole implements MedecinViewInterface {

    private MedecinPresenter presenter;
    private List<Medecin> medecins;
    private final Scanner scanner = new Scanner(System.in);

    public MedecinViewConsole() {
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

        presenter.addMedecin(new Medecin.MedecinBuilder()
                .setNom(nom)
                .setPrenom(prenom)
                .setMatricule(matricule)
                .setTel(tel)
                .build()
        );
    }

    public void recherche() {
        int idMedecin = saisie("ID du medecin recherché: ", Integer::parseInt);

        Medecin medecin = presenter.read(idMedecin);

        if(medecin == null){
            System.out.println("Medecin introuvable !");
        }else {
            System.out.println(medecin);
        }
    }

    public void modification() {
        int nl = Utilitaire.choixElt(medecins) - 1;

        Medecin medecin = medecins.get(nl);

        String matricule = Utilitaire.modifyIfNotBlank("matricule", medecin.getMatricule());
        String nom = Utilitaire.modifyIfNotBlank("nom", medecin.getNom());
        String prenom = Utilitaire.modifyIfNotBlank("prenom", medecin.getPrenom());
        String tel = Utilitaire.modifyIfNotBlank("tel", medecin.getTel());

        Medecin medecinUpdate = new Medecin.MedecinBuilder()
                .setId(medecin.getId())
                .setNom(nom)
                .setPrenom(prenom)
                .setMatricule(matricule)
                .setTel(tel)
                .build();

        presenter.update(medecinUpdate);
    }

    public void suppression() {
        int idMedecin = saisie("id du medecin recherché: ", Integer::parseInt);

        Medecin modifierPatient = new Medecin.MedecinBuilder()
                .setId(idMedecin)
                .build();

        presenter.removeMedecin(modifierPatient);
    }

    @Override
    public void setPresenter(MedecinPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Medecin> medecins) {
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
