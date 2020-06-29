package dao;

public class ConnectionPoolException extends Exception {


    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Exception cause) {
        super(cause);
    }

    public ConnectionPoolException(String message, Exception cause) {
        super(message, cause);
    }


}
