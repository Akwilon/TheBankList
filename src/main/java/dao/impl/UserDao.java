package dao.impl;

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

public class UserDao  implements Dao<User> {
    private static final Logger LOGGER = Logger.getLogger(UserDao.class);
    private static final String SQL_FIND_ALL_USERS = "Select * from user";
    private static final String SQL_FIND_USER_BY_ID = "Select * from user where user.userID = ?";

    public Optional<User> getByID(int ID) throws ConnectionPoolException, ConnectionPoolNotInitializedException {
        User user = null;
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
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
            preparedStatement.setInt(1, ID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int userID = resultSet.getInt("userID");
                user = new User(userID, name, surname);
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException in getById UserDAO " + e);
        }finally {
            pool.releaseConnection(connection);
        }
        return Optional.ofNullable(user);


    }

    public List<User> getAll() throws ConnectionPoolException, ConnectionPoolNotInitializedException {
        List<User> userList = new ArrayList<>();
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
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USERS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                Integer userID = resultSet.getInt("userID");
                User user = new User(userID, name, surname);
                userList.add(user);
            }
            resultSet.close();
            preparedStatement.close();
            pool.releaseConnection(connection);

        } catch (SQLException e) {
            LOGGER.error("SQLException in getAll UserDAO" + e);
        }finally {
            pool.releaseConnection(connection);
        }

        return userList;
    }
}
