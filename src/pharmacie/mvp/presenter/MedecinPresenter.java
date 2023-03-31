package pharmacie.mvp.presenter;

import pharmacie.metier.Medecin;
import pharmacie.mvp.model.DAO;
import pharmacie.mvp.view.ViewInterface;

import java.util.List;

public class MedecinPresenter {

    private final DAO<Medecin> model;
    private final ViewInterface<MedecinPresenter> view;

    public MedecinPresenter(DAO model, ViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        updateList();
        view.start();
    }

    public void updateList() {
        List<Medecin> medecins = model.getAll();
        view.setListDatas(medecins);
    }

    public void addMedecin(Medecin medecin) {
        Medecin newMedecin = model.add(medecin);

        if (newMedecin == null) {
            view.affMsg("Erreur de création");
        } else {
            view.affMsg("Création de : " + newMedecin);
        }

        updateList();
    }

    public void removeMedecin(Medecin medecin) {
        boolean success = model.remove(medecin);

        if (success) {
            view.affMsg("Medecin supprimé");
        } else {
            view.affMsg("Erreur de suppression");
        }

        updateList();
    }

    public void modifierMedecinInfo(Medecin medecin, String key, Object value) {
        Medecin updated = model.update(medecin, key, value);

        if (updated != null) {
            view.affMsg("Medecin modifié !");
        } else {
            view.affMsg("Erreur de modification");
        }

        updateList();
    }

}
