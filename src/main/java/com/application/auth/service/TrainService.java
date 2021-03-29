package com.application.auth.service;

import com.application.auth.model.Location;
import com.application.auth.model.Train;

import java.io.IOException;

public interface TrainService {

    Train[] populateTrainLocations() throws IOException;

    Train[] getTrainDetails(String trainId);

    Train[] updateLocation(Train[] trains, Location locationß);
}
