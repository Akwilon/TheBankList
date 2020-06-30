package dao;

import java.sql.Connection;

public interface ConnectionPool {



    Connection acquireConnection() throws ConnectionPoolException, ConnectionPoolNotInitializedException, ClassNotFoundException;

    void releaseConnection(Connection connection);

}
