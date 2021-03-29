package com.application.auth;

import com.application.auth.service.SecurityServiceImpl;
import com.application.auth.service.UserServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SecurityServiceImplTest extends TestCase {
    @Autowired
    SecurityServiceImpl securityService;

    @Autowired
    UserServiceImpl userService;

    @Test
    public void testAutoLogin() {
        userService.save(UserServiceImplTest.mockUser());
        securityService.autoLogin(UserServiceImplTest.mockUser().getUsername(),
                UserServiceImplTest.mockUser().getPassword());
        assertTrue(true);
    }
}
