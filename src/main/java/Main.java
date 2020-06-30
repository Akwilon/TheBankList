import bean.Account;
import bean.User;
import dao.ConnectionPoolException;
import dao.ConnectionPoolNotInitializedException;
import dao.Dao;
import dao.impl.AccountDao;
import dao.impl.ConnectionPoolImpl;
import dao.impl.UserDao;

public class Main {

    public static void main(String... args) throws ConnectionPoolException, InterruptedException, ConnectionPoolNotInitializedException, ClassNotFoundException {
        Dao<User> userDao = new UserDao();
        User user = userDao.getByID(12).get();
        System.out.println(user);
    }

}
