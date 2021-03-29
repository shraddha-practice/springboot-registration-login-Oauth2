package com.application.auth.web;

import com.application.auth.model.Location;
import com.application.auth.model.Train;
import com.application.auth.model.User;
import com.application.auth.service.TrainDetailsServiceImpl;
import com.application.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/")
public class APIController {

    @Autowired
    private TrainDetailsServiceImpl svcImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private TrainDetailsServiceImpl trainServiceImpl;

    @PutMapping(value = "/{trainNumber}/location")
    public Train[] putTrainLocation(@PathVariable("trainNumber") String trainId, @RequestBody Location location) {
        System.out.println(trainId);
        Train[] trains = svcImpl.getTrainDetails(trainId);
        svcImpl.updateLocation(trains, location);
        return trains;
    }

    @GetMapping("/getTrainInfo")
    public Train[] getTrain(@RequestParam String trainNumber) {
        Train[] train = svcImpl.getTrainDetails(trainNumber);
        return train;
    }

    @GetMapping("/userInfo")
    public User userInfo(@RequestParam String username) {
        User user = userServiceImpl.findByUsername(username);
        return user;
    }

    @GetMapping("/locations")
    public Train [] locations() {
        Train[] trains = trainServiceImpl.populateTrainLocations();
        return trains;
    }
}
