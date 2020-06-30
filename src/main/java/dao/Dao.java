package dao;

import java.util.List;
import java.util.Optional;

public interface Dao <T> {

    public Optional<T> getByID(int ID) throws ConnectionPoolException, ConnectionPoolNotInitializedException;

    public List<T> getAll() throws ConnectionPoolException, ConnectionPoolNotInitializedException;

}
