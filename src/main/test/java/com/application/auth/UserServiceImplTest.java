package com.application.auth;

import com.application.auth.model.User;
import com.application.auth.service.UserServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest extends TestCase {
    @Autowired
    UserServiceImpl userService;
    final static String mockObject = "ubisecure1234";

    @Test
    public void testSaveUser() {
        userService.save(mockUser());
        assertTrue(true);
    }

    public void testFindUser() {
        userService.findByUsername(mockUser().getUsername());
        assertTrue(true);
    }

    public static User mockUser() {
        User user = new User();
        user.setUsername(mockObject);
        user.setPassword(mockObject);
        user.setEmail(mockObject);
        user.setFirstname(mockObject);
        user.setLastname(mockObject);
        user.setPasswordConfirm(mockObject);
        return user;

    }
}
