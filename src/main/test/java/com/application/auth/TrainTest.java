package com.application.auth;


import com.application.auth.web.APIController;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ConcurrentModel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainTest extends TestCase {
    @Autowired
    APIController apiController;

    @Test
    public void getTrainDetails() {
        String train = apiController.getTrains("1", new ConcurrentModel());
        assertNotNull(train);
    }
}
