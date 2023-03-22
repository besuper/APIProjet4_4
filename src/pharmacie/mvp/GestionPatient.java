package pharmacie.mvp;

import pharmacie.mvp.model.DAOPatient;
import pharmacie.mvp.model.PatientModel;
import pharmacie.mvp.presenter.PatientPresenter;
import pharmacie.mvp.view.PatientViewConsole;
import pharmacie.mvp.view.PatientViewInterface;

public class GestionPatient {

    public static void main(String[] args) {
        DAOPatient cm = new PatientModel();
        PatientViewInterface cv = new PatientViewConsole();

        PatientPresenter cp = new PatientPresenter(cm, cv);
        cp.start();
    }

}
