package com.application.auth;


import com.application.auth.model.User;
import com.application.auth.web.UserController;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;

@RunWith(SpringRunner.class)
@SpringBootTest

public class UserControllerTest extends TestCase {
    @Autowired
    UserController userController;

    @Test
    public void registrationTest() {
        System.out.println(BindingResult.class.getName() + ".");


        User user = UserServiceImplTest.mockUser();


        DirectFieldBindingResult bindingResult = new DirectFieldBindingResult(user, "username");

        userController.registration(user, bindingResult);

        assertTrue(true);
    }

    @Test
    public void trainsTest() {
        Model model = new ConcurrentModel();

        assertNotNull(userController.locations(model));


    }
}
