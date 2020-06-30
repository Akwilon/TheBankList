package service;

import bean.Account;
import dao.ConnectionPoolException;
import dao.ConnectionPoolNotInitializedException;
import dao.Dao;
import dao.impl.AccountDao;
import dao.impl.ConnectionPoolImpl;
import org.apache.log4j.Logger;

import java.util.List;

public class AllAcountService {
    private static final Logger LOGGER = Logger.getLogger(AllAcountService.class);
    private AllAcountService(){
    }


    public static List<Account> getAllaccounts(){
        List<Account> res = null;
        try {
            return res = new AccountDao().getAll();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Pool was not conected:" + e);
        } catch (ConnectionPoolNotInitializedException e) {
            LOGGER.error("Pool was not initialized:" + e);
        }
        return res;
    }

}
