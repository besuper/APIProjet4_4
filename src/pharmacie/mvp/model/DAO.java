package pharmacie.mvp.model;

import java.util.List;

public interface DAO<E> {

    E add(E obj);

    E read(int id);

    E update(E obj);

    boolean remove(E obj);

    List<E> getAll();

}
