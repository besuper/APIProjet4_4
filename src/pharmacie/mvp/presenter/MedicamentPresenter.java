package pharmacie.mvp.presenter;

import pharmacie.metier.Medicament;
import pharmacie.mvp.model.DAO;
import pharmacie.mvp.view.MedicamentViewInterface;

import java.util.List;

public class MedicamentPresenter {

    private final DAO<Medicament> model;
    private final MedicamentViewInterface view;

    public MedicamentPresenter(DAO model, MedicamentViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        updateList();
        view.start();
    }

    public void updateList() {
        List<Medicament> medicaments = model.getAll();
        view.setListDatas(medicaments);
    }

    public void addMedicament(Medicament medicament) {
        Medicament newMedicament = model.add(medicament);

        if (newMedicament == null) {
            view.affMsg("Erreur de création");
        } else {
            view.affMsg("Création de : " + newMedicament);
        }

        updateList();
    }

    public void removeMedicament(Medicament medicament) {
        boolean success = model.remove(medicament);

        if (success) {
            view.affMsg("Médicament supprimé");
        } else {
            view.affMsg("Erreur de suppression");
        }

        updateList();
    }

    public void modifierMedicamentInfo(Medicament medicament, String key, Object value) {
        Medicament updated = model.update(medicament, key, value);

        if (updated != null) {
            view.affMsg("Médicament modifié !");
        } else {
            view.affMsg("Erreur de modification");
        }

        updateList();
    }

    public Medicament selectionner() {
        return view.selectionner(model.getAll());
    }

}
