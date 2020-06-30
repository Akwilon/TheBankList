package dao.impl;

import bean.Account;
import bean.User;
import dao.ConnectionPool;
import dao.ConnectionPoolException;
import dao.ConnectionPoolNotInitializedException;
import dao.Dao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDao implements Dao<Account> {
    private static final Logger LOGGER = Logger.getLogger(AccountDao.class);
    private static final String SQL_FIND_ALL_ACCOUNTS = "Select * from account";
    private static final String SQL_FIND_ACCOUNT_BY_ID = "Select * from account where account.accountID = ?";

    public Optional<Account> getByID(int ID) throws ConnectionPoolException, ConnectionPoolNotInitializedException {
        Account account = null;
        ConnectionPool pool = null;
        try {
            pool = ConnectionPoolImpl.getInstance();
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted exception in " + UserDao.class + "\n" + e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFound exception in " + UserDao.class + "\n" + e);
        }
        Connection connection = null;
        try {
            connection = pool.acquireConnection();
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFound exception in " + UserDao.class + "\n" + e);
        }
        try {
            ResultSet resultSet;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ACCOUNT_BY_ID);
            preparedStatement.setInt(1, ID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int accountID = resultSet.getInt("accountID");
                int accountValue = resultSet.getInt("account");
                int userID = resultSet.getInt("userID");
                account = new Account(accountID, accountValue, userID);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in getById AccountDAO " + e);
        }finally {
            pool.releaseConnection(connection);
        }
        return Optional.ofNullable(account);

    }

    public List<Account> getAll() throws ConnectionPoolException, ConnectionPoolNotInitializedException {
        List<Account> accountList = new ArrayList<>();
        ConnectionPool pool = null;
        try {
            pool = ConnectionPoolImpl.getInstance();
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted exception in " + UserDao.class + "\n" + e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFound exception in " + UserDao.class + "\n" + e);
        }
        Connection connection = null;
        try {
            connection = pool.acquireConnection();
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFound exception in " + UserDao.class + "\n" + e);
        }
        try {
            ResultSet resultSet;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_ACCOUNTS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int accountID = resultSet.getInt("accountID");
                int accountValue = resultSet.getInt("account");
                int userID = resultSet.getInt("userID");
                Account account = new Account(accountID, accountValue, userID);
                accountList.add(account);
            }
        } catch (SQLException e) {
            LOGGER.error("SQLException in getAll AccountDAO " + e);
        }finally {
            pool.releaseConnection(connection);
        }
        return accountList;
    }
}
