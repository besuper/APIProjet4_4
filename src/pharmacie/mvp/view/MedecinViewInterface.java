package pharmacie.mvp.view;

import pharmacie.designpatterns.builder.Medecin;
import pharmacie.mvp.presenter.MedecinPresenter;

import java.util.List;

public interface MedecinViewInterface {

    void setPresenter(MedecinPresenter presenter);

    void setListDatas(List<Medecin> objs);

    void affMsg(String msg);

    void start();

    Medecin selectionner(List<Medecin> list);

}
