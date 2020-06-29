package dao;

public class ConnectionPoolNotInitializedException extends Exception {
    public ConnectionPoolNotInitializedException(String message) {
        super(message);
    }

    public ConnectionPoolNotInitializedException(Exception cause) {
        super(cause);
    }

    public ConnectionPoolNotInitializedException(String message, Exception cause) {
        super(message, cause);
    }
}
