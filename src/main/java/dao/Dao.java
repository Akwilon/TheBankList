package dao;

import java.util.List;

public interface Dao <T> {

    public T getByID();

    public List<T> getAll();

}
