package pharmacie.mvp.view;

import pharmacie.metier.Prescription;
import pharmacie.mvp.presenter.PrescriptionPresenter;

import java.util.List;

public interface PrescriptionViewInterface {

    void setPresenter(PrescriptionPresenter presenter);

    void setListDatas(List<Prescription> objs);

    void affMsg(String msg);

    void start();

}
