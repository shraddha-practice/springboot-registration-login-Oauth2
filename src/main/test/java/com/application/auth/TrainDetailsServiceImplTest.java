package com.application.auth;

import com.application.auth.model.Train;
import com.application.auth.service.TrainDetailsServiceImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainDetailsServiceImplTest extends TestCase {

    @Autowired
    TrainDetailsServiceImpl trainDetailsServiceImpl;

    @Test
    public void test() {

        Train[] train = trainDetailsServiceImpl.populateTrainLocations();
        assertTrue(null != train[0]);

    }
}