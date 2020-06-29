import bean.Account;
import dao.ConnectionPool;
import dao.ConnectionPoolException;
import dao.ConnectionPoolNotInitializedException;
import dao.impl.ConnectionPoolImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

        public static void main(String... args) throws ConnectionPoolException, InterruptedException, ConnectionPoolNotInitializedException, SQLException, ClassNotFoundException {
                Class.forName("com.mysql.jdbc.Driver");
                String uri  = "jdbc:mysql://localhost/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                String USER = "root";
                String PASSWORD = "admin";

                Connection connection = ConnectionPoolImpl.getInstance().acquireConnection();

                List<Account> accountData = new ArrayList<>();

                try {
                        ResultSet rs;
                        Statement statement = connection.createStatement();
                        rs = statement.executeQuery("SELECT * from test.account");
                        while (rs.next()) {
                                Integer account = rs.getInt("account");
                                Integer accountID = rs.getInt("accountID");
                                Integer userId = rs.getInt("userID");
                                Account accounts = new Account(accountID, account, userId);
                                accountData.add(accounts);
                        }
                } catch (SQLException throwables) {
                        throwables.printStackTrace();
                } finally {
                        try {
                                connection.close();
                        } catch (SQLException throwables) {
                                throwables.printStackTrace();
                        }
                }
                System.out.println(accountData);
        }

}
