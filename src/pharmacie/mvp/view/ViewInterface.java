package pharmacie.mvp.view;

import pharmacie.metier.Patient;
import pharmacie.mvp.presenter.PatientPresenter;

import java.util.List;

public interface ViewInterface<E> {

    void setPresenter(E presenter);

    void setListDatas(List objs);

    void affMsg(String msg);

    void start();

}
