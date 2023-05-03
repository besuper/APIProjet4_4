package pharmacie.mvp.presenter;

import pharmacie.designpatterns.builder.Infos;
import pharmacie.designpatterns.builder.Medicament;
import pharmacie.designpatterns.builder.Prescription;
import pharmacie.mvp.model.DAO;
import pharmacie.mvp.view.MedicamentViewInterface;

import java.util.List;

public class MedicamentPresenter {

    private final DAO<Medicament> model;
    private final MedicamentViewInterface view;

    public MedicamentPresenter(DAO<Medicament> model, MedicamentViewInterface view) {
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

            updateList();
        }
    }

    public void removeMedicament(Medicament medicament) {
        boolean success = model.remove(medicament);

        if (success) {
            view.affMsg("Médicament supprimé");

            updateList();
        } else {
            view.affMsg("Erreur de suppression");
        }
    }

    public void update(Medicament medicament) {
        Medicament updatedMedicament = model.update(medicament);

        if(updatedMedicament == null){
            view.affMsg("Erreur de modification");
        }else {
            view.affMsg("Medicament modifié");

            updateList();
        }
    }

    public List<Infos> selectionner(Prescription prescription) {
        return view.selectionner(model.getAll(), prescription);
    }

    public Medicament read(int id) {
        return model.read(id);
    }

}
