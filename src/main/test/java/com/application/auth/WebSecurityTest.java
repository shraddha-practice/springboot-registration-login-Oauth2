package com.application.auth;

import com.application.auth.model.User;
import com.application.auth.service.UserServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebSecurityTest {
    User user = null;
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserServiceImpl userService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    /**
     * Initialize the application context to re-use in all test cases
     * */


    /**
     * Test the valid user
     */
    @Test
    public void testValidUser() {
        user = UserServiceImplTest.mockUser();
        userService.save(user);
        //Get the user by username from configured user details service
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

    }

    /**
     * Test the invalid user
     */
    @Test
    public void testInvalidUser() throws UsernameNotFoundException {
        boolean usernameFound = true;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername("sampleUser");

            Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authToken);

        } catch (Exception e) {
            usernameFound = false;
        }
        assertFalse(usernameFound);
    }


}
