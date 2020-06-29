package dao;

import java.sql.Connection;

public interface ConnectionPool {



    Connection acquireConnection() throws ConnectionPoolException, ConnectionPoolNotInitializedException;


    void releaseConnection(Connection connection);

}
