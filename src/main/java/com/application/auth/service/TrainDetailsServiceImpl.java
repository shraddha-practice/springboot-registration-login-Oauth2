package com.application.auth.service;

import com.application.auth.model.Location;
import com.application.auth.model.Train;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TrainDetailsServiceImpl implements TrainService {
    String URI_ALL_TRAINS = "https://rata.digitraffic.fi/api/v1/train-locations/latest/";
    String URI_TRAIN = "https://rata.digitraffic.fi/api/v1/train-locations/";

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss");


    @Override
    public Train[] populateTrainLocations() {
        System.out.println("Fixed Delay: " + dateFormat.format(LocalDateTime.now()));
        return objectMapper(callURIService(URI_ALL_TRAINS));
    }

    @Override
    public Train[] getTrainDetails(String trainId) {
        return objectMapper(callURIService(URI_TRAIN + LocalDate.now() + "/" + trainId + "/"));
    }

    @Override
    public Train[] updateLocation(Train[] trains, Location location) {
        trains[0].setLocation(location);
        return trains;
    }

    private String callURIService(String str) {

        ClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        ResponseEntity<String> response = restTemplate.getForEntity(str, String.class);
        return (response.getBody());
    }

    private Train[] objectMapper(String res) {

        ObjectMapper objectMapper = new ObjectMapper();
        Train[] train = new Train[0];
        try {
            train = objectMapper.readValue(res, Train[].class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return train;
    }


}
