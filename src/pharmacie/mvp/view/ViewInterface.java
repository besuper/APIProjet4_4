package pharmacie.mvp.view;

import java.util.List;

public interface ViewInterface<E> {

    void setPresenter(E presenter);

    void setListDatas(List objs);

    void affMsg(String msg);

    void start();

}
