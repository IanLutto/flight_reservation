package com.lutomiah.flightreservation.fightreservation.controller;

import com.lutomiah.flightreservation.fightreservation.entities.Flight;
import com.lutomiah.flightreservation.fightreservation.models.ApiResponse;
import com.lutomiah.flightreservation.fightreservation.models.ReservationRequest;
import com.lutomiah.flightreservation.fightreservation.repos.FlightRepository;
import com.lutomiah.flightreservation.fightreservation.service.FlightService;
import com.lutomiah.flightreservation.fightreservation.service.ReservationService;
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

import java.util.Map;
import java.util.Optional;

@Controller
public class ReservationController {

    @Autowired
    FlightService flightService;
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    ReservationService reservationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);


    @RequestMapping(value = "/showReservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> showReservation(@RequestBody Map<String, String> requestMap){
        LOGGER.info("<<ID>> ", requestMap);
        ApiResponse apiResponse = new ApiResponse();
        ApiResponse error = new ApiResponse();
        Long flightId = Long.valueOf(requestMap.get("flightId").trim());
        LOGGER.info("##nOW lONG " + flightId);
        try {
            Optional<Flight> flight = flightRepository.findById(flightId);

            if (flight.isPresent()){
                apiResponse.setResponseCode("00");
                apiResponse.setResponseBody(flight);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }
            apiResponse.setResponseCode("01");
            apiResponse.setMessage("Flight not found!!!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/showCompleteReservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> completeReservation(@RequestBody ReservationRequest reservationRequest){
        LOGGER.info("<<ID>> ", reservationRequest);
        ApiResponse apiResponse = new ApiResponse();
        ApiResponse error = new ApiResponse();
        Long flightId = reservationRequest.getFlightId();
        LOGGER.info("##nOW lONG " + flightId);
        try {
            Optional<Flight> flight = flightRepository.findById(flightId);
            ResponseEntity<?> reservation = reservationService.bookFlight(reservationRequest);

            if (reservation != null){
                apiResponse.setResponseCode("00");
                apiResponse.setMessage("Reservation Created Successfully and the Id is " + flight);
                apiResponse.setResponseBody(reservation);
                return new ResponseEntity<>(apiResponse, HttpStatus.OK);
            }
            apiResponse.setResponseCode("01");
            apiResponse.setMessage("Failed to complete reservation");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
