package pharmacie.mvp.view;

import pharmacie.metier.Medicament;
import pharmacie.mvp.presenter.MedicamentPresenter;

import java.util.List;

public interface MedicamentViewInterface {

    void setPresenter(MedicamentPresenter presenter);

    void setListDatas(List<Medicament> objs);

    void affMsg(String msg);

    void start();

    Medicament selectionner(List<Medicament> list);

}
