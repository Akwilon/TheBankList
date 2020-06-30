package dao.impl;

import dao.ConnectionPool;
import dao.ConnectionPoolException;
import dao.ConnectionPoolNotInitializedException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPoolImpl implements ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPoolImpl.class);
    private static volatile ConnectionPoolImpl instance;
    private String url = "jdbc:mysql://localhost/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String login = "root";
    private String password = "admin";
    private int startPoolSize = 5;


    private BlockingQueue<Connection> freeConnections;
    private List<Connection> allConnections;


    private ConnectionPoolImpl(String url, String login, String password) throws ConnectionPoolException, InterruptedException, ClassNotFoundException {
        this.url = url;
        this.login = login;
        this.password = password;
        initializePool(startPoolSize);
    }


    private ConnectionPoolImpl() throws InterruptedException, ConnectionPoolException, ClassNotFoundException {
        initializePool(startPoolSize);
    }

    public static ConnectionPoolImpl getInstance() throws ConnectionPoolException, InterruptedException, ClassNotFoundException {
        ConnectionPoolImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPoolImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPoolImpl();
                }
            }
        }
        return localInstance;
    }


    private Connection createConnection() throws ConnectionPoolException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.fatal("ClassNotFoundException", e);
        }
        try {
            Connection connection = DriverManager.getConnection(url, login, password);
            allConnections.add(connection);
            LOGGER.debug("Connection created");
            return connection;
        } catch (SQLException e) {
            LOGGER.fatal("Failed to create connection", e);
            throw new ConnectionPoolException("Failed to create connection", e);
        }
    }

    private void initializePool(int startPoolSize) throws ConnectionPoolException, InterruptedException {
        allConnections = new CopyOnWriteArrayList<>();
        freeConnections = new LinkedBlockingQueue<>(startPoolSize);
        for (int i = 0; i < startPoolSize; i++) {
            Connection connection = createConnection();
            freeConnections.put(connection);
        }
    }


    public Connection acquireConnection() throws ConnectionPoolException, ConnectionPoolNotInitializedException {
        Connection connection = null;
        try {
            connection = freeConnections.poll();
        } catch (NullPointerException e) {
            throw new ConnectionPoolNotInitializedException(e);
        }
        if (connection != null) {
            LOGGER.debug("Connection acquired");
            return connection;
        } else {
            throw new ConnectionPoolException("Timeout exceeded while trying to acquire connection from connection pool");
        }
    }

    public void releaseConnection(Connection connection) {
        try {
            connection.clearWarnings();
            connection.setAutoCommit(true);
            freeConnections.put(connection);
        } catch (SQLException e) {
            LOGGER.error("SQLException in releaseConncetion", e);
        } catch (InterruptedException e) {
            LOGGER.error("Thread was interrupted while trying to release connection to connection pool", e);
        }
    }

    public void setUrl(String uri) {
        this.url = uri;
    }

    public void setLogin(String login1) {
        this.login = login1;
    }

    public void setPassword(String password1) {
        this.password = password1;
    }

}
