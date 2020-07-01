package service;


import bean.Account;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

class AllAcountServiceTest{
    @Test
    public void getAllaccounts() {
        List<Account> list = AllAcountService.getAllaccounts();
        Assert.assertNotNull(list);
    }
    @Test
    public   void  getRichestUserID() {
        Integer id = AllAcountService.getRichestUserID();
        Integer expected = 10;
        Assert.assertEquals(expected,id);
    }

    @Test
    public void getAllaccountsSize() {
        List<Account> list = AllAcountService.getAllaccounts();
        Assert.assertEquals(13,list.size());
    }

    public void getSumm(){
        Integer integer = AllAcountService.getSumAllAccount();
        Integer res = 906329;
        Assert.assertEquals(res,integer);
    }
}