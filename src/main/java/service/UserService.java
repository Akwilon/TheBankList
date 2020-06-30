package service;

import bean.User;
import dao.ConnectionPoolException;
import dao.ConnectionPoolNotInitializedException;
import dao.Dao;
import dao.impl.UserDao;
import org.apache.log4j.Logger;

public class UserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    private UserService(){

    }


    public static User getUserById(int ID){
        User user = null;
        Dao<User> dao = new UserDao();
        try {
            if (dao.getByID(ID).isPresent()){
                user = dao.getByID(ID).get();
                return user;
            } else {
                LOGGER.error("No such user ID: " + ID);
            }
        } catch (ConnectionPoolException e) {
            LOGGER.error("Pool was not conected:" + e);
        } catch (ConnectionPoolNotInitializedException e) {
            LOGGER.error("Pool was not initialized:" + e);
        }
        return user;
    }

}
