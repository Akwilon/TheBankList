package dao.impl;

import dao.ConnectionPool;
import dao.ConnectionPoolException;
import dao.ConnectionPoolNotInitializedException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolImpl implements ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPoolImpl.class);
    private static volatile ConnectionPoolImpl instance;


    private static String url = "jdbc:mysql://localhost/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String login = "root";
    private static String password = "admin";
    private int startPoolSize = 5;
    private int maxPoolSize = 10;


    private BlockingQueue<Connection> freeConnections;
    private List<Connection> allConnections;
    private AtomicInteger currentPoolSize;


    private ConnectionPoolImpl(String url, String login, String password) throws ConnectionPoolException, InterruptedException {
        ConnectionPoolImpl.url = url;
        ConnectionPoolImpl.login = login;
        ConnectionPoolImpl.password = password;
        initializePool(startPoolSize, maxPoolSize);
    }

    public static ConnectionPoolImpl getInstance() throws ConnectionPoolException, InterruptedException {
        ConnectionPoolImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectionPoolImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectionPoolImpl(url, login, password);
                }
            }
        }
        return localInstance;
    }


    private Connection createConnection() throws ConnectionPoolException {
        try {
            Connection connection = DriverManager.getConnection(url, login, password);
            allConnections.add(connection);
            LOGGER.debug("Connection created");
            return connection;
        } catch (SQLException e) {
            currentPoolSize.decrementAndGet();
            LOGGER.fatal("Failed to create connection", e);
            throw new ConnectionPoolException("Failed to create connection", e);
        }
    }

    private void initializePool(int startPoolSize, int maxPoolSize) throws ConnectionPoolException, InterruptedException {
        allConnections = new CopyOnWriteArrayList<>();
        freeConnections = new LinkedBlockingQueue<>(maxPoolSize);
        currentPoolSize = new AtomicInteger(startPoolSize);
        for (int i = 0; i < startPoolSize; i++) {
            Connection pooledConnection = createConnection();
            freeConnections.put(pooledConnection);
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
        } else if (currentPoolSize.getAndUpdate(n -> (n < maxPoolSize) ? ++n : n) < maxPoolSize) {
            connection = createConnection();
            return connection;
        } else {
            throw new ConnectionPoolException("Timeout exceeded while trying to acquire connection from connection pool");
        }
    }

    public void releaseConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ex) {
            LOGGER.warn("Connection to database was not properly closed, causing memory leak", ex);
        }
    }

    public static void setUrl(String uri) {
        url = uri;
    }

    public void setLogin(String login1) {
        login = login1;
    }

    public void setPassword(String password1) {
        password = password1;
    }
}
