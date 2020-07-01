package service;

import bean.Account;
import bean.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void getUserById() {

        User user = UserService.getUserById(10);
        Assert.assertNotNull(user);

    }
}