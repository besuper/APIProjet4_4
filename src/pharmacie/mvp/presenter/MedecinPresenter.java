package pharmacie.mvp.presenter;

import pharmacie.designpatterns.builder.Medecin;
import pharmacie.mvp.model.DAO;
import pharmacie.mvp.view.MedecinViewInterface;

import java.util.List;

public class MedecinPresenter {

    private final DAO<Medecin> model;
    private final MedecinViewInterface view;

    public MedecinPresenter(DAO<Medecin> model, MedecinViewInterface view) {
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

            updateList();
        }
    }

    public void removeMedecin(Medecin medecin) {
        boolean success = model.remove(medecin);

        if (success) {
            view.affMsg("Medecin supprimé");

            updateList();
        } else {
            view.affMsg("Erreur de suppression");
        }
    }

    public void update(Medecin medecin) {
        Medecin updatedMedecin = model.update(medecin);

        if(updatedMedecin == null){
            view.affMsg("Erreur de modification");
        }else {
            view.affMsg("Medecin modifié");

            updateList();
        }
    }

    public Medecin selectionner() {
        return view.selectionner(model.getAll());
    }

    public Medecin read(int id){
        return model.read(id);
    }

}
