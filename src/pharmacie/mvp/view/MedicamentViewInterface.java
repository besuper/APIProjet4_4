package pharmacie.mvp.view;

import pharmacie.designpatterns.builder.Infos;
import pharmacie.designpatterns.builder.Medicament;
import pharmacie.designpatterns.builder.Prescription;
import pharmacie.mvp.presenter.MedicamentPresenter;

import java.util.List;

public interface MedicamentViewInterface {

    void setPresenter(MedicamentPresenter presenter);

    void setListDatas(List<Medicament> objs);

    void affMsg(String msg);

    void start();

    List<Infos> selectionner(List<Medicament> list, Prescription prescription);

}
