package pharmacie.mvp.view;

import pharmacie.metier.Patient;
import pharmacie.mvp.presenter.PatientPresenter;

import java.util.List;

public interface PatientViewInterface {

    void setPresenter(PatientPresenter presenter);

    void setListDatas(List<Patient> objs);

    void affMsg(String msg);

    void start();

    Patient selectionner(List<Patient> list);

}
