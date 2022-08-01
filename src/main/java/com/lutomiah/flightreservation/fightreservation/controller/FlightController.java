package com.lutomiah.flightreservation.fightreservation.controller;

import com.lutomiah.flightreservation.fightreservation.models.ApiResponse;
import com.lutomiah.flightreservation.fightreservation.models.ReservationRequest;
import com.lutomiah.flightreservation.fightreservation.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FlightController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    FlightService flightService;

    @RequestMapping(value = "/findFlights", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findFlight(@RequestBody ReservationRequest reservationRequest) {
        LOGGER.info("Filter Paylod {} ", reservationRequest);
        ApiResponse apiResponse = new ApiResponse();
        ApiResponse error = new ApiResponse();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/addFlight", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addFlight(@RequestBody ReservationRequest reservationRequest) {
        LOGGER.info("Filter Paylod {} ", reservationRequest);
        ApiResponse apiResponse = new ApiResponse();
        ApiResponse error = new ApiResponse();

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
