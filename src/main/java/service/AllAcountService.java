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

    public static Integer getRichestUserID(){
        int ID = 0;
        try {
            List<Account> accountList = new AccountDao().getAll();
            int maxValue = Integer.MIN_VALUE;
            for (Account account : accountList) {
                if (account.getAccount() > maxValue) {
                    maxValue = account.getAccount();
                    ID = account.getUserID();
                }
            }
        } catch (ConnectionPoolException e) {
            LOGGER.error("Pool was not conected:" + e);
        } catch (ConnectionPoolNotInitializedException e) {
            LOGGER.error("Pool was not initialized:" + e);
        }
        return ID;
    }



    public static Integer getSumAllAccount(){
        Integer value = null;
        try {
            value = new AccountDao().getAll().stream().mapToInt(a -> a.getAccount()).sum();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Pool was not conected:" + e);
        } catch (ConnectionPoolNotInitializedException e) {
            LOGGER.error("Pool was not initialized:" + e);
        }
        return value;
    }

}
